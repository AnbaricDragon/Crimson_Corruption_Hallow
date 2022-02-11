package com.anbaric.terra_reforged.blocks.potionplants;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ITag;
import net.minecraft.tags.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;
import net.minecraftforge.common.PlantType;

public class TerraBlockWaterleaf extends TerraBlockPotionPlant
{
    public TerraBlockWaterleaf(BlockBehaviour.Properties builder, Tag.Named<Block> tag)
    {
        super(builder, tag);
    }

    @Override
    public PlantType getPlantType(BlockGetter world, BlockPos pos)
    {
        return PlantType.DESERT;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
    {
        return (isValidPosition(state, world, pos) || isInPlanter(state, world, pos)) || super.canSurvive(state, world, pos);
    }

    public boolean isValidPosition(BlockState state, LevelReader world, BlockPos pos)
    {
        return world.getBlockState(pos.below()).is(tag) && pos.getY() > 55;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random)
    {
        if (!world.isClientSide)
        {
            if (!world.isAreaLoaded(pos, 3))
            {
                return;
            }
            if (state.getValue(AGE) == 0)
            {
                world.setBlockAndUpdate(pos, state.cycle(AGE));
            }
            else
            {
                if (world.isRaining() && state.getValue(AGE) == 1) { world.setBlockAndUpdate(pos, state.setValue(AGE, 2)); }
                if (!world.isRaining() && state.getValue(AGE) == 2) { world.setBlockAndUpdate(pos, state.setValue(AGE, 1)); }
            }
        }
    }
}
