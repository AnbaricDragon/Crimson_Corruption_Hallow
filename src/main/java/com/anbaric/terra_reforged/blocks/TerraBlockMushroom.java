package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TerraBlockMushroom extends BushBlock
{
    protected static final VoxelShape MUSHROOM_SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);

    public TerraBlockMushroom(BlockBehaviour.Properties builder)
    {
        super(builder.noCollission());
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
    {
        return isValidPosition(state, world, pos) || super.canSurvive(state, world, pos);
    }

    public boolean isValidPosition(BlockState state, LevelReader world, BlockPos pos)
    {
        Block soil = world.getBlockState(pos.below()).getBlock();

        if (this == TerraBlockRegistry.PLANT_MUSHROOM_VILE.get())
        {
            return soil == TerraBlockRegistry.GRASS_CORRUPT.get() ||
                   soil == TerraBlockRegistry.STONE_EBON.get() ||
                   soil == Blocks.DIRT;
        }
        if (this == TerraBlockRegistry.PLANT_MUSHROOM_VICIOUS.get())
        {
            return soil == TerraBlockRegistry.GRASS_CRIMSON.get() ||
                   soil == TerraBlockRegistry.STONE_CRIM.get() ||
                   soil == Blocks.DIRT;
        }
        else return true;
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_)
    {
        return MUSHROOM_SHAPE;
    }
}
