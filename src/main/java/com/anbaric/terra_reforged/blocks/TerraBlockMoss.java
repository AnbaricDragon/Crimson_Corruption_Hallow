package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LayerLightEngine;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class TerraBlockMoss extends Block
{
    public TerraBlockMoss(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable)
    {
        return plantable.getPlant(world, pos).getBlock() == getMoss();
    }

    public Block getMoss()
    {
        if (this == TerraBlockRegistry.STONE_MOSS_RED.get()) { return TerraBlockRegistry.PLANT_MOSS_RED.get();}
        else if (this == TerraBlockRegistry.STONE_MOSS_YELLOW.get()) { return TerraBlockRegistry.PLANT_MOSS_YELLOW.get();}
        else if (this == TerraBlockRegistry.STONE_MOSS_GREEN.get()) { return TerraBlockRegistry.PLANT_MOSS_GREEN.get();}
        else if (this == TerraBlockRegistry.STONE_MOSS_BLUE.get()) { return TerraBlockRegistry.PLANT_MOSS_BLUE.get();}
        else if (this == TerraBlockRegistry.STONE_MOSS_PURPLE.get()) { return TerraBlockRegistry.PLANT_MOSS_PURPLE.get();}
        else return TerraBlockRegistry.PLANT_MOSS_FIRE.get();
    }

    private static boolean canBeMoss(BlockState state, LevelReader world, BlockPos pos)
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
        return canBeMoss(state, world, pos) && !world.getFluidState(blockpos).is(FluidTags.WATER);
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random)
    {
        if (!canBeMoss(state, world, pos))
        {
            if (!world.isAreaLoaded(pos, 1))
            {
                return;
            }
            world.setBlockAndUpdate(pos, Blocks.STONE.defaultBlockState());
        }
        else
        {
            if (!world.isAreaLoaded(pos, 3))
            {
                return;
            }
            if (world.getBlockState(pos.above()).isAir())
            {
                world.setBlock(pos.above(), getMoss().defaultBlockState(), 3);
            }
            if (world.getMaxLocalRawBrightness(pos.above()) >= 9)
            {
                BlockState blockstate = this.defaultBlockState();

                for (int i = 0; i < 4; ++i)
                {
                    BlockPos targetPos = pos.offset(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
                    Block targetBlock = world.getBlockState(targetPos).getBlock();
                    if ((targetBlock == Blocks.STONE || targetBlock == Blocks.DIORITE || targetBlock == Blocks.GRANITE || targetBlock == Blocks.ANDESITE || targetBlock == Blocks.TUFF || targetBlock == Blocks.CALCITE) && canPropagate(blockstate, world, targetPos))
                    {
                        world.setBlockAndUpdate(targetPos, blockstate);
                    }
                }
            }
        }
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level world, BlockPos pos, Player player, boolean willHarvest, FluidState fluid)
    {
        if (world.getBlockState(pos.above()).getBlock() instanceof TerraBlockMoss)
        {
            world.setBlockAndUpdate(pos.above(), Blocks.AIR.defaultBlockState());
        }
        world.setBlockAndUpdate(pos, Blocks.STONE.defaultBlockState());
        return false;
    }
}
