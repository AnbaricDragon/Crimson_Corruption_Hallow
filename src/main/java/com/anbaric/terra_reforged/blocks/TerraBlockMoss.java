package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LayerLightEngine;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class TerraBlockMoss extends Block
{
    private Block moss;

    public TerraBlockMoss(Properties properties, Block moss)
    {
        super(properties);
        this.moss = moss;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level world, BlockPos pos, Player player, boolean willHarvest, FluidState fluid)
    {
        if (world.getBlockState(pos.above()).getBlock() == moss)
        {
            world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        }
        world.setBlockAndUpdate(pos, Blocks.STONE.defaultBlockState());
        return false;
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable)
    {
        return plantable.getPlantType(world, pos) == TerraReforged.MOSS;
    }

    private boolean canBeMoss(BlockState state, LevelReader world, BlockPos pos)
    {
        BlockPos   blockpos   = pos.above();
        BlockState blockstate = world.getBlockState(blockpos);
        if (blockstate.getBlock() == moss || blockstate.getBlock() == Blocks.AIR)
        {
            return true;
        }
        else return !(blockstate.getFluidState().getAmount() > 0);
    }

    private boolean canPropagate(BlockState state, LevelReader world, BlockPos pos)
    {
        BlockPos blockpos = pos.above();
        return canBeMoss(state, world, pos) && !world.getFluidState(blockpos).is(FluidTags.WATER);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random)
    {
        if (!canBeMoss(state, world, pos))
        {
            if (!world.isAreaLoaded(pos, 1))
            {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            }
            world.setBlockAndUpdate(pos, Blocks.STONE.defaultBlockState());
        }
        else
        {
            if (!world.isAreaLoaded(pos, 3))
            {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            }

            BlockState blockstate = this.defaultBlockState();

            for (int i = 0; i < 4; ++i)
            {
                BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
                if (world.getBlockState(blockpos).is(BlockTags.STONE_ORE_REPLACEABLES) && canPropagate(blockstate, world, blockpos))
                {
                    world.setBlockAndUpdate(blockpos, this.defaultBlockState());
                }
            }
            if (random.nextFloat() < 0.1 && world.getBlockState(pos.above()).is(Blocks.AIR))
            {
                world.setBlockAndUpdate(pos.above(), moss.defaultBlockState());
            }
        }
    }
}
