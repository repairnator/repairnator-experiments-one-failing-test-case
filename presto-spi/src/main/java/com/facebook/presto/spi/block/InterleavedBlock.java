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
package com.facebook.presto.spi.block;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;

public class InterleavedBlock
        extends AbstractInterleavedBlock
{
    private static final int INSTANCE_SIZE = ClassLayout.parseClass(InterleavedBlock.class).instanceSize();

    private final Block[] blocks;
    private final InterleavedBlockEncoding blockEncoding;
    private final int start;
    private final int positionCount;
    private final long retainedSizeInBytes;

    private final AtomicLong sizeInBytes;

    public InterleavedBlock(Block[] blocks)
    {
        super(blocks.length);
        this.blocks = blocks;

        long sizeInBytes = 0;
        long retainedSizeInBytes = INSTANCE_SIZE;
        int positionCount = 0;
        int firstSubBlockPositionCount = blocks[0].getPositionCount();
        for (int i = 0; i < getBlockCount(); i++) {
            sizeInBytes += blocks[i].getSizeInBytes();
            retainedSizeInBytes += blocks[i].getRetainedSizeInBytes();
            positionCount += blocks[i].getPositionCount();

            if (firstSubBlockPositionCount != blocks[i].getPositionCount()) {
                throw new IllegalArgumentException("length of sub blocks differ: block 0: " + firstSubBlockPositionCount + ", block " + i + ": " + blocks[i].getPositionCount());
            }
        }

        this.blockEncoding = computeBlockEncoding();
        this.start = 0;
        this.positionCount = positionCount;
        this.sizeInBytes = new AtomicLong(sizeInBytes);
        this.retainedSizeInBytes = retainedSizeInBytes;
    }

    private InterleavedBlock(Block[] blocks, int start, int positionCount, long retainedSizeInBytes, InterleavedBlockEncoding blockEncoding)
    {
        super(blocks.length);
        this.blocks = blocks;
        this.start = start;
        this.positionCount = positionCount;
        this.retainedSizeInBytes = retainedSizeInBytes;
        this.blockEncoding = blockEncoding;
        this.sizeInBytes = new AtomicLong(-1);
    }

    @Override
    public Block getRegion(int position, int length)
    {
        validateRange(position, length);
        return new InterleavedBlock(blocks, toAbsolutePosition(position), length, retainedSizeInBytes, blockEncoding);
    }

    @Override
    protected Block getBlock(int blockIndex)
    {
        if (blockIndex < 0) {
            throw new IllegalArgumentException("position is not valid");
        }

        return blocks[blockIndex];
    }

    @Override
    protected int toAbsolutePosition(int position)
    {
        return position + start;
    }

    @Override
    public InterleavedBlockEncoding getEncoding()
    {
        return blockEncoding;
    }

    @Override
    public int getPositionCount()
    {
        return positionCount;
    }

    @Override
    public long getSizeInBytes()
    {
        long sizeInBytes = this.sizeInBytes.get();
        if (sizeInBytes < 0) {
            sizeInBytes = 0;
            for (int i = 0; i < getBlockCount(); i++) {
                sizeInBytes += blocks[i].getRegionSizeInBytes(start / blocks.length, positionCount / blocks.length);
            }
            this.sizeInBytes.set(sizeInBytes);
        }
        return sizeInBytes;
    }

    @Override
    public long getRetainedSizeInBytes()
    {
        return retainedSizeInBytes;
    }

    @Override
    public void retainedBytesForEachPart(BiConsumer<Object, Long> consumer)
    {
        consumer.accept(blocks, retainedSizeInBytes - INSTANCE_SIZE);
        consumer.accept(this, (long) INSTANCE_SIZE);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("InterleavedBlock{");
        sb.append("columns=").append(getBlockCount());
        sb.append(", positionCountPerBlock=").append(getPositionCount() / getBlockCount());
        sb.append('}');
        return sb.toString();
    }
}
