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

import com.facebook.presto.spi.Page;
import com.facebook.presto.spi.block.Block;

// This class exists as template for code generation and for testing
public class TwoChannelJoinProbe
        implements JoinProbe
{
    public static class TwoChannelJoinProbeFactory
            implements JoinProbeFactory
    {
        public TwoChannelJoinProbeFactory()
        {
        }

        @Override
        public JoinProbe createJoinProbe(LookupSource lookupSource, Page page)
        {
            return new TwoChannelJoinProbe(lookupSource, page);
        }
    }

    private final LookupSource lookupSource;
    private final int positionCount;
    private final Block blockA;
    private final Block blockB;
    private final Block probeBlockA;
    private final Block probeBlockB;
    private final Block[] probeBlocks;
    private final Page page;
    private final int[] probeOutputChannels;
    private final Page probePage;
    private int position = -1;

    public TwoChannelJoinProbe(LookupSource lookupSource, Page page)
    {
        this.lookupSource = lookupSource;
        this.positionCount = page.getPositionCount();
        this.blockA = page.getBlock(0);
        this.blockB = page.getBlock(1);
        this.probeBlockA = blockA;
        this.probeBlockB = blockB;
        this.probeBlocks = new Block[2];
        probeBlocks[0] = probeBlockA;
        probeBlocks[1] = probeBlockB;
        this.probeOutputChannels = new int[]{ 0, 1 };
        this.page = page;
        this.probePage = new Page(probeBlocks);
    }

    @Override
    public int getOutputChannelCount()
    {
        return 2;
    }

    @Override
    public int[] getOutputChannels()
    {
        return probeOutputChannels;
    }

    @Override
    public boolean advanceNextPosition()
    {
        position++;
        return position < positionCount;
    }

    @Override
    public long getCurrentJoinPosition()
    {
        if (currentRowContainsNull()) {
            return -1;
        }
        return lookupSource.getJoinPosition(position, probePage, page);
    }

    private boolean currentRowContainsNull()
    {
        if (probeBlockA.isNull(position)) {
            return true;
        }
        if (probeBlockB.isNull(position)) {
            return true;
        }
        return false;
    }

    @Override
    public int getPosition()
    {
        return position;
    }

    @Override
    public Page getPage()
    {
        return page;
    }
}
