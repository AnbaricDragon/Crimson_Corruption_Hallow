package com.anbaric.terra_reforged.blocks.potionplants;

import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class TerraBlockFireblossom extends TerraBlockPotionPlant
{
    public TerraBlockFireblossom(Properties properties)
    {
        super(properties, TerraTagRegistry.FIREBLOSSOM_PLANTERS);
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
                if (world.getGameTime() >= 11615 && world.getGameTime() <= 13800 && !world.isRaining() && state.getValue(AGE) == 1) {world.setBlock(pos, this.defaultBlockState().setValue(AGE, 2), 3);}
                //TODO Make this check if it's in hell and stop it from reverting State
                if (world.getGameTime() <= 11615 || world.getGameTime() >= 13800 && state.getValue(AGE) == 2) {world.setBlock(pos, this.defaultBlockState().setValue(AGE, 1), 3);}
            }
        }
    }
}
