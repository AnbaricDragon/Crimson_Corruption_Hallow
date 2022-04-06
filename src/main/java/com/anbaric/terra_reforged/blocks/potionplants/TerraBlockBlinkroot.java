package com.anbaric.terra_reforged.blocks.potionplants;

import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class TerraBlockBlinkroot extends TerraBlockPotionPlant
{
    public TerraBlockBlinkroot(Properties properties)
    {
        super(properties, TerraTagRegistry.GENERAL_PLANTERS);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos)
    {
        return state.isFaceSturdy(world, pos, Direction.UP);
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
                if (Math.floor(world.getGameTime() / 2000) % 2 == 0 && state.getValue(AGE) == 1)
                {
                    world.setBlock(pos, this.defaultBlockState().setValue(AGE, 2), 1);
                }
                if (Math.floor(world.getGameTime() / 2000) % 2 != 0 && state.getValue(AGE) == 2)
                {
                    world.setBlock(pos, this.defaultBlockState().setValue(AGE, 1), 1);
                }
            }
        }
    }
}
