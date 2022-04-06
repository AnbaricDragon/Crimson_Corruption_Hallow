package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LayerLightEngine;

import java.util.Random;

public class TerraBlockMushroomGrass extends Block
{
    public TerraBlockMushroomGrass(Properties properties)
    {
        super(properties);
    }

    private static boolean canBeGrass(BlockState state, LevelReader world, BlockPos pos)
    {
        BlockPos   blockpos   = pos.above();
        BlockState blockstate = world.getBlockState(blockpos);
        if (blockstate.getFluidState().getAmount() == 8)
        {
            return false;
        }
        else
        {
            int i = LayerLightEngine.getLightBlockInto(world, state, pos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(world, blockpos));
            return i < world.getMaxLightLevel();
        }
    }

    private static boolean canPropagate(BlockState state, LevelReader world, BlockPos pos)
    {
        BlockPos blockpos = pos.above();
        return canBeGrass(state, world, pos) && !world.getFluidState(blockpos).is(FluidTags.WATER);
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random)
    {
        if (!canBeGrass(state, world, pos))
        {
            if (!world.isAreaLoaded(pos, 1))
            {
                return;
            }
            world.setBlockAndUpdate(pos, TerraBlockRegistry.SOIL_MUD.get().defaultBlockState());
        }
        else
        {
            if (!world.isAreaLoaded(pos, 3))
            {
                return;
            }
            if (world.getMaxLocalRawBrightness(pos.above()) >= 9)
            {
                BlockState blockstate = this.defaultBlockState();

                for (int i = 0; i < 4; ++i)
                {
                    BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (world.getBlockState(blockpos).is(TerraBlockRegistry.SOIL_MUD.get()) && canPropagate(blockstate, world, blockpos))
                    {
                        world.setBlockAndUpdate(blockpos, blockstate);
                    }
                }
            }
        }
    }
}
