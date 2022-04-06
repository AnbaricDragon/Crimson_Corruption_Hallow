package com.anbaric.terra_reforged.blocks.potionplants;

import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class TerraBlockShiverthorn extends TerraBlockPotionPlant
{
    public TerraBlockShiverthorn(Properties properties)
    {
        super(properties, TerraTagRegistry.SHIVERTHORN_PLANTERS);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random)
    {
        if (!world.isClientSide)
        {
            if (!world.isAreaLoaded(pos, 3))
            {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            }
            if (random.nextFloat() < 0.05F && state.getValue(AGE) != 2)
            {
                world.setBlock(pos, state.setValue(AGE, state.getValue(AGE) + 1), 3);
            }
        }
    }
}
