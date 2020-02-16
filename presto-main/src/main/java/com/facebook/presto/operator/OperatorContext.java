/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.presto.operator;

import com.facebook.presto.ExceededMemoryLimitException;
import com.facebook.presto.Session;
import com.facebook.presto.memory.AggregatedMemoryContext;
import com.facebook.presto.memory.LocalMemoryContext;
import com.facebook.presto.memory.MemoryTrackingContext;
import com.facebook.presto.memory.QueryContextVisitor;
import com.facebook.presto.spi.Page;
import com.facebook.presto.sql.planner.plan.PlanNodeId;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import io.airlift.log.Logger;
import io.airlift.stats.CounterStat;
import io.airlift.units.Duration;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

import static com.facebook.presto.operator.BlockedReason.WAITING_FOR_MEMORY;
import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkArgument;
import static io.airlift.units.DataSize.succinctBytes;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * Only {@link #getOperatorStats()} and revocable-memory-related operations are ThreadSafe
 */
public class OperatorContext
{
    private static final Logger log = Logger.get(OperatorContext.class);
    private static final ThreadMXBean THREAD_MX_BEAN = ManagementFactory.getThreadMXBean();

    private final int operatorId;
    private final PlanNodeId planNodeId;
    private final String operatorType;
    private final DriverContext driverContext;
    private final Executor executor;

    private final AtomicLong intervalWallStart = new AtomicLong();
    private final AtomicLong intervalCpuStart = new AtomicLong();
    private final AtomicLong intervalUserStart = new AtomicLong();

    private final AtomicLong addInputCalls = new AtomicLong();
    private final AtomicLong addInputWallNanos = new AtomicLong();
    private final AtomicLong addInputCpuNanos = new AtomicLong();
    private final AtomicLong addInputUserNanos = new AtomicLong();
    private final CounterStat inputDataSize = new CounterStat();
    private final CounterStat inputPositions = new CounterStat();

    private final AtomicLong getOutputCalls = new AtomicLong();
    private final AtomicLong getOutputWallNanos = new AtomicLong();
    private final AtomicLong getOutputCpuNanos = new AtomicLong();
    private final AtomicLong getOutputUserNanos = new AtomicLong();
    private final CounterStat outputDataSize = new CounterStat();
    private final CounterStat outputPositions = new CounterStat();

    private final AtomicReference<SettableFuture<?>> memoryFuture;
    private final AtomicReference<SettableFuture<?>> revocableMemoryFuture;
    private final AtomicReference<BlockedMonitor> blockedMonitor = new AtomicReference<>();
    private final AtomicLong blockedWallNanos = new AtomicLong();

    private final AtomicLong finishCalls = new AtomicLong();
    private final AtomicLong finishWallNanos = new AtomicLong();
    private final AtomicLong finishCpuNanos = new AtomicLong();
    private final AtomicLong finishUserNanos = new AtomicLong();

    private final SpillContext spillContext;
    private final AtomicReference<Supplier<OperatorInfo>> infoSupplier = new AtomicReference<>();
    private final boolean collectTimings;

    // memoryRevokingRequestedFuture is done iff memory revoking was requested for operator
    @GuardedBy("this")
    private SettableFuture<?> memoryRevokingRequestedFuture = SettableFuture.create();

    private final MemoryTrackingContext operatorMemoryContext;

    public OperatorContext(
            int operatorId,
            PlanNodeId planNodeId,
            String operatorType,
            DriverContext driverContext,
            Executor executor,
            MemoryTrackingContext operatorMemoryContext)
    {
        checkArgument(operatorId >= 0, "operatorId is negative");
        this.operatorId = operatorId;
        this.planNodeId = requireNonNull(planNodeId, "planNodeId is null");
        this.operatorType = requireNonNull(operatorType, "operatorType is null");
        this.driverContext = requireNonNull(driverContext, "driverContext is null");
        this.spillContext = new OperatorSpillContext(this.driverContext);
        this.executor = requireNonNull(executor, "executor is null");
        this.memoryFuture = new AtomicReference<>(SettableFuture.create());
        this.memoryFuture.get().set(null);
        this.revocableMemoryFuture = new AtomicReference<>(SettableFuture.create());
        this.revocableMemoryFuture.get().set(null);
        this.operatorMemoryContext = requireNonNull(operatorMemoryContext, "operatorMemoryContext is null");

        collectTimings = driverContext.isVerboseStats() && driverContext.isCpuTimerEnabled();
    }

    public int getOperatorId()
    {
        return operatorId;
    }

    public String getOperatorType()
    {
        return operatorType;
    }

    public DriverContext getDriverContext()
    {
        return driverContext;
    }

    public Session getSession()
    {
        return driverContext.getSession();
    }

    public boolean isDone()
    {
        return driverContext.isDone();
    }

    public void startIntervalTimer()
    {
        intervalWallStart.set(System.nanoTime());
        intervalCpuStart.set(currentThreadCpuTime());
        intervalUserStart.set(currentThreadUserTime());
    }

    public void recordAddInput(Page page)
    {
        addInputCalls.incrementAndGet();
        recordInputWallNanos(nanosBetween(intervalWallStart.get(), System.nanoTime()));
        addInputCpuNanos.getAndAdd(nanosBetween(intervalCpuStart.get(), currentThreadCpuTime()));
        addInputUserNanos.getAndAdd(nanosBetween(intervalUserStart.get(), currentThreadUserTime()));

        if (page != null) {
            inputDataSize.update(page.getSizeInBytes());
            inputPositions.update(page.getPositionCount());
        }
    }

    public void recordGeneratedInput(long sizeInBytes, long positions)
    {
        recordGeneratedInput(sizeInBytes, positions, 0);
    }

    public void recordGeneratedInput(long sizeInBytes, long positions, long readNanos)
    {
        inputDataSize.update(sizeInBytes);
        inputPositions.update(positions);
        recordInputWallNanos(readNanos);
    }

    public long recordInputWallNanos(long readNanos)
    {
        return addInputWallNanos.getAndAdd(readNanos);
    }

    public void recordGetOutput(Page page)
    {
        getOutputCalls.incrementAndGet();
        getOutputWallNanos.getAndAdd(nanosBetween(intervalWallStart.get(), System.nanoTime()));
        getOutputCpuNanos.getAndAdd(nanosBetween(intervalCpuStart.get(), currentThreadCpuTime()));
        getOutputUserNanos.getAndAdd(nanosBetween(intervalUserStart.get(), currentThreadUserTime()));

        if (page != null) {
            outputDataSize.update(page.getSizeInBytes());
            outputPositions.update(page.getPositionCount());
        }
    }

    public void recordGeneratedOutput(long sizeInBytes, long positions)
    {
        outputDataSize.update(sizeInBytes);
        outputPositions.update(positions);
    }

    public void recordBlocked(ListenableFuture<?> blocked)
    {
        requireNonNull(blocked, "blocked is null");

        BlockedMonitor monitor = new BlockedMonitor();

        BlockedMonitor oldMonitor = blockedMonitor.getAndSet(monitor);
        if (oldMonitor != null) {
            oldMonitor.run();
        }

        blocked.addListener(monitor, executor);
        // Do not register blocked with driver context.  The driver handles this directly.
    }

    public void recordFinish()
    {
        finishCalls.incrementAndGet();
        finishWallNanos.getAndAdd(nanosBetween(intervalWallStart.get(), System.nanoTime()));
        finishCpuNanos.getAndAdd(nanosBetween(intervalCpuStart.get(), currentThreadCpuTime()));
        finishUserNanos.getAndAdd(nanosBetween(intervalUserStart.get(), currentThreadUserTime()));
    }

    public ListenableFuture<?> isWaitingForMemory()
    {
        return memoryFuture.get();
    }

    public ListenableFuture<?> isWaitingForRevocableMemory()
    {
        return revocableMemoryFuture.get();
    }

    public void reserveMemory(long bytes)
    {
        updateMemoryFuture(operatorMemoryContext.updateUserMemory(bytes), memoryFuture);
    }

    public synchronized void reserveRevocableMemory(long bytes)
    {
        updateMemoryFuture(operatorMemoryContext.updateRevocableMemory(bytes), revocableMemoryFuture);
    }

    public LocalMemoryContext newLocalSystemMemoryContext()
    {
        return operatorMemoryContext.newSystemMemoryContext();
    }

    public AggregatedMemoryContext newAggregateSystemMemoryContext()
    {
        return operatorMemoryContext.newAggregateSystemMemoryContext();
    }

    public synchronized long getReservedRevocableBytes()
    {
        return operatorMemoryContext.getRevocableMemory();
    }

    private static void updateMemoryFuture(ListenableFuture<?> memoryPoolFuture, AtomicReference<SettableFuture<?>> targetFutureReference)
    {
        if (!memoryPoolFuture.isDone()) {
            SettableFuture<?> currentMemoryFuture = targetFutureReference.get();
            while (currentMemoryFuture.isDone()) {
                SettableFuture<?> settableFuture = SettableFuture.create();
                // We can't replace one that's not done, because the task may be blocked on that future
                if (targetFutureReference.compareAndSet(currentMemoryFuture, settableFuture)) {
                    currentMemoryFuture = settableFuture;
                }
                else {
                    currentMemoryFuture = targetFutureReference.get();
                }
            }

            SettableFuture<?> finalMemoryFuture = currentMemoryFuture;
            // Create a new future, so that this operator can un-block before the pool does, if it's moved to a new pool
            Futures.addCallback(memoryPoolFuture, new FutureCallback<Object>()
            {
                @Override
                public void onSuccess(Object result)
                {
                    finalMemoryFuture.set(null);
                }

                @Override
                public void onFailure(Throwable t)
                {
                    finalMemoryFuture.set(null);
                }
            });
        }
    }

    public synchronized void setRevocableMemoryReservation(long newRevocableMemoryReservation)
    {
        checkArgument(newRevocableMemoryReservation >= 0, "newRevocableMemoryReservation is negative");

        long delta = newRevocableMemoryReservation - getReservedRevocableBytes();

        if (delta > 0) {
            reserveRevocableMemory(delta);
        }
        else {
            operatorMemoryContext.updateRevocableMemory(delta);
        }
    }

    public void freeMemory(long bytes)
    {
        checkArgument(bytes >= 0, "bytes is negative");
        operatorMemoryContext.updateUserMemory(-bytes);
    }

    public void freeAllMemory()
    {
        operatorMemoryContext.close();
        verifyMemoryReservations();
    }

    private void verifyMemoryReservations()
    {
        if (operatorMemoryContext.getSystemMemory() != 0) {
            log.error("nonzero system memory reservation after close: %d", operatorMemoryContext.getSystemMemory());
        }
        if (operatorMemoryContext.getUserMemory() != 0) {
            log.error("nonzero user memory reservation after close: %d", operatorMemoryContext.getUserMemory());
        }
        if (operatorMemoryContext.getRevocableMemory() != 0) {
            log.error("nonzero revocable memory reservation after close: %d", operatorMemoryContext.getRevocableMemory());
        }
    }

    public long getPeakUserMemory()
    {
        return operatorMemoryContext.getPeakUserMemory();
    }

    public SpillContext getSpillContext()
    {
        return spillContext;
    }

    public void moreMemoryAvailable()
    {
        memoryFuture.get().set(null);
    }

    public void transferMemoryToTaskContext(long taskBytes)
    {
        long bytes = operatorMemoryContext.getUserMemory();
        LocalMemoryContext taskLocalMemoryContext = driverContext.getPipelineContext().getTaskContext().localUserMemoryContext();
        try {
            operatorMemoryContext.localUserMemoryContext().transferOwnership(taskLocalMemoryContext);
            taskLocalMemoryContext.addBytes(taskBytes - bytes);
        }
        catch (ExceededMemoryLimitException e) {
            // Since we can reserve an extra amount of memory above (when taskBytes > bytes) ExceededMemoryLimitException can be thrown
            // when reserving this extra amount. This will happen after "bytes" is moved from the operator context to the task context.
            // So, at this point "bytes" won't be tracked by the operator context and when the driver is destroyed it won't
            // be freed up from the memory pools.
            taskLocalMemoryContext.addBytes(-bytes);
            throw e;
        }
    }

    public void setMemoryReservation(long newMemoryReservation)
    {
        checkArgument(newMemoryReservation >= 0, "newMemoryReservation is negative");

        long delta = newMemoryReservation - operatorMemoryContext.getUserMemory();

        if (delta > 0) {
            reserveMemory(delta);
        }
        else {
            freeMemory(-delta);
        }
    }

    public boolean trySetMemoryReservation(long newMemoryReservation)
    {
        checkArgument(newMemoryReservation >= 0, "newMemoryReservation is negative");

        long delta = newMemoryReservation - operatorMemoryContext.getUserMemory();
        if (delta > 0) {
            return operatorMemoryContext.tryReserveUserMemory(delta);
        }
        freeMemory(-delta);
        return true;
    }

    public synchronized boolean isMemoryRevokingRequested()
    {
        return memoryRevokingRequestedFuture.isDone();
    }

    /**
     * Returns how much revocable memory will be revoked by the operator
     */
    public synchronized long requestMemoryRevoking()
    {
        boolean alreadyRequested = isMemoryRevokingRequested();
        if (!alreadyRequested && operatorMemoryContext.getRevocableMemory() > 0) {
            memoryRevokingRequestedFuture.set(null);
            return operatorMemoryContext.getRevocableMemory();
        }
        return 0;
    }

    public synchronized void resetMemoryRevokingRequested()
    {
        SettableFuture<?> currentFuture = memoryRevokingRequestedFuture;
        if (!currentFuture.isDone()) {
            return;
        }
        memoryRevokingRequestedFuture = SettableFuture.create();
    }

    public synchronized SettableFuture<?> getMemoryRevokingRequestedFuture()
    {
        return memoryRevokingRequestedFuture;
    }

    public void setInfoSupplier(Supplier<OperatorInfo> infoSupplier)
    {
        requireNonNull(infoSupplier, "infoProvider is null");
        this.infoSupplier.set(infoSupplier);
    }

    public CounterStat getInputDataSize()
    {
        return inputDataSize;
    }

    public CounterStat getInputPositions()
    {
        return inputPositions;
    }

    public CounterStat getOutputDataSize()
    {
        return outputDataSize;
    }

    public CounterStat getOutputPositions()
    {
        return outputPositions;
    }

    @Override
    public String toString()
    {
        return format("%s-%s", operatorType, planNodeId);
    }

    public OperatorStats getOperatorStats()
    {
        Supplier<OperatorInfo> infoSupplier = this.infoSupplier.get();
        OperatorInfo info = Optional.ofNullable(infoSupplier).map(Supplier::get).orElse(null);

        long inputPositionsCount = inputPositions.getTotalCount();

        return new OperatorStats(
                driverContext.getPipelineContext().getPipelineId(),
                operatorId,
                planNodeId,
                operatorType,

                1,

                addInputCalls.get(),
                new Duration(addInputWallNanos.get(), NANOSECONDS).convertToMostSuccinctTimeUnit(),
                new Duration(addInputCpuNanos.get(), NANOSECONDS).convertToMostSuccinctTimeUnit(),
                new Duration(addInputUserNanos.get(), NANOSECONDS).convertToMostSuccinctTimeUnit(),
                succinctBytes(inputDataSize.getTotalCount()),
                inputPositionsCount,
                (double) inputPositionsCount * inputPositionsCount,

                getOutputCalls.get(),
                new Duration(getOutputWallNanos.get(), NANOSECONDS).convertToMostSuccinctTimeUnit(),
                new Duration(getOutputCpuNanos.get(), NANOSECONDS).convertToMostSuccinctTimeUnit(),
                new Duration(getOutputUserNanos.get(), NANOSECONDS).convertToMostSuccinctTimeUnit(),
                succinctBytes(outputDataSize.getTotalCount()),
                outputPositions.getTotalCount(),

                new Duration(blockedWallNanos.get(), NANOSECONDS).convertToMostSuccinctTimeUnit(),

                finishCalls.get(),
                new Duration(finishWallNanos.get(), NANOSECONDS).convertToMostSuccinctTimeUnit(),
                new Duration(finishCpuNanos.get(), NANOSECONDS).convertToMostSuccinctTimeUnit(),
                new Duration(finishUserNanos.get(), NANOSECONDS).convertToMostSuccinctTimeUnit(),

                succinctBytes(operatorMemoryContext.getUserMemory()),
                succinctBytes(getReservedRevocableBytes()),
                succinctBytes(operatorMemoryContext.getSystemMemory()),
                memoryFuture.get().isDone() ? Optional.empty() : Optional.of(WAITING_FOR_MEMORY),
                info);
    }

    public <C, R> R accept(QueryContextVisitor<C, R> visitor, C context)
    {
        return visitor.visitOperatorContext(this, context);
    }

    private long currentThreadUserTime()
    {
        if (!collectTimings) {
            return 0;
        }
        return THREAD_MX_BEAN.getCurrentThreadUserTime();
    }

    private long currentThreadCpuTime()
    {
        if (!collectTimings) {
            return 0;
        }
        return THREAD_MX_BEAN.getCurrentThreadCpuTime();
    }

    private static long nanosBetween(long start, long end)
    {
        return Math.abs(end - start);
    }

    private class BlockedMonitor
            implements Runnable
    {
        private final long start = System.nanoTime();
        private boolean finished;

        @Override
        public synchronized void run()
        {
            if (finished) {
                return;
            }
            finished = true;
            blockedMonitor.compareAndSet(this, null);
            blockedWallNanos.getAndAdd(getBlockedTime());
        }

        public long getBlockedTime()
        {
            return nanosBetween(start, System.nanoTime());
        }
    }

    @ThreadSafe
    private class OperatorSpillContext
            implements SpillContext
    {
        private final DriverContext driverContext;
        private final AtomicLong reservedBytes = new AtomicLong();

        public OperatorSpillContext(DriverContext driverContext)
        {
            this.driverContext = driverContext;
        }

        @Override
        public void updateBytes(long bytes)
        {
            if (bytes >= 0) {
                reservedBytes.addAndGet(bytes);
                driverContext.reserveSpill(bytes);
            }
            else {
                reservedBytes.accumulateAndGet(-bytes, this::decrementSpilledReservation);
                driverContext.freeSpill(-bytes);
            }
        }

        private long decrementSpilledReservation(long reservedBytes, long bytesBeingFreed)
        {
            checkArgument(bytesBeingFreed >= 0);
            checkArgument(bytesBeingFreed <= reservedBytes, "tried to free %s spilled bytes from %s bytes reserved", bytesBeingFreed, reservedBytes);
            return reservedBytes - bytesBeingFreed;
        }

        @Override
        public void close()
        {
            // Only products of SpillContext.newLocalSpillContext() should be closed.
            throw new UnsupportedOperationException(format("%s should not be closed directly", getClass()));
        }

        @Override
        public String toString()
        {
            return toStringHelper(this)
                    .add("usedBytes", reservedBytes.get())
                    .toString();
        }
    }

    @VisibleForTesting
    public MemoryTrackingContext getOperatorMemoryContext()
    {
        return operatorMemoryContext;
    }
}
