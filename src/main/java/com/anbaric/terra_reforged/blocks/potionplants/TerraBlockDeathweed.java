package com.anbaric.terra_reforged.blocks.potionplants;

import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class TerraBlockDeathweed extends TerraBlockPotionPlant
{
    public TerraBlockDeathweed(Properties properties)
    {
        super(properties, TerraTagRegistry.DEATHWEED_PLANTERS);
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
            if (state.getValue(AGE) == 0 && random.nextFloat() < 0.05F)
            {
                world.setBlock(pos, state.setValue(AGE, 1), 1);
            }
            else
            {
                if (world.getMoonPhase() == 1.0f && !world.isDay() && state.getValue(AGE) == 1) {world.setBlock(pos, this.defaultBlockState().setValue(AGE, 2), 3);}
                if (world.getMoonPhase() != 1.0f && state.getValue(AGE) == 2)                   {world.setBlock(pos, this.defaultBlockState().setValue(AGE, 1), 3);}
            }
        }
    }
}
