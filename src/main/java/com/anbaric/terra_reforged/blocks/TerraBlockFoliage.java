package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class TerraBlockFoliage extends BushBlock
{
    protected static final VoxelShape MUSHROOM_SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
    protected static final VoxelShape MOSS_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

    public TerraBlockFoliage(Block.Properties builder)
    {
        super(builder.notSolid());
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        BlockState soilState = worldIn.getBlockState(pos.down());
        Block soil = worldIn.getBlockState(pos.down()).getBlock();

        if (this == TerraBlockRegistry.PLANT_MOSS_RED.get())
        {
            return soil == TerraBlockRegistry.STONE_MOSS_RED.get();
        }
        if (this == TerraBlockRegistry.PLANT_MOSS_YELLOW.get())
        {
            return soil == TerraBlockRegistry.STONE_MOSS_YELLOW.get();
        }
        if (this == TerraBlockRegistry.PLANT_MOSS_GREEN.get())
        {
            return soil == TerraBlockRegistry.STONE_MOSS_GREEN.get();
        }
        if (this == TerraBlockRegistry.PLANT_MOSS_BLUE.get())
        {
            return soil == TerraBlockRegistry.STONE_MOSS_BLUE.get();
        }
        if (this == TerraBlockRegistry.PLANT_MOSS_PURPLE.get())
        {
            return soil == TerraBlockRegistry.STONE_MOSS_PURPLE.get();
        }
        if (this == TerraBlockRegistry.PLANT_MOSS_FIRE.get())
        {
            return soil == TerraBlockRegistry.STONE_MOSS_FIRE.get();
        }
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

        return soilState.canSustainPlant(worldIn, pos.down(), Direction.UP, this);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        if (this == TerraBlockRegistry.PLANT_MOSS_RED.get() ||
            this == TerraBlockRegistry.PLANT_MOSS_YELLOW.get() ||
            this == TerraBlockRegistry.PLANT_MOSS_GREEN.get() ||
            this == TerraBlockRegistry.PLANT_MOSS_BLUE.get() ||
            this == TerraBlockRegistry.PLANT_MOSS_PURPLE.get() ||
            this == TerraBlockRegistry.PLANT_MOSS_FIRE.get())
        {
            return MOSS_SHAPE;
        }
        else if (this == TerraBlockRegistry.PLANT_MUSHROOM_VILE.get() ||
                 this == TerraBlockRegistry.PLANT_MUSHROOM_VICIOUS.get())
        {
            return MUSHROOM_SHAPE;
        }
        else
        {
            return VoxelShapes.empty();
        }
    }
}
