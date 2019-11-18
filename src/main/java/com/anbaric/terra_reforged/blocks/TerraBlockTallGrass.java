package com.anbaric.terra_reforged.blocks;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.Random;

public class TerraBlockTallGrass extends BushBlock implements IGrowable, net.minecraftforge.common.IShearable
{
    private static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public TerraBlockTallGrass(Block.Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        Block block = state.getBlock();
        return block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.FARMLAND || block == TerraBlocks.GRASS_CRIMSON || block == TerraBlocks.GRASS_CORRUPT || block == TerraBlocks.GRASS_HALLOWED || block == TerraBlocks.GRASS_JUNGLE || block == TerraBlocks.GRASS_MUSHROOM;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }

    /**
     * Whether this IGrowable can grow
     */
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
    {
        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        TerraBlockDoubleGrass doublePlant = (TerraBlockDoubleGrass) (this == TerraBlocks.TALLGRASS_CORRUPT ? TerraBlocks.TALLGRASSDOUBLE_CORRUPT : this == TerraBlocks.TALLGRASS_CRIMSON ? TerraBlocks.TALLGRASSDOUBLE_CRIMSON : TerraBlocks.TALLGRASSDOUBLE_HALLOWED);
        if (doublePlant.getDefaultState().isValidPosition(worldIn, pos) && worldIn.isAirBlock(pos.up()))
        {
            System.out.println("Attempting to grow from bonemeal");
            doublePlant.placeAt(worldIn, pos, 2);
        }
    }

    /**
     * Get the OffsetType for this Block. Determines if the model is rendered slightly offset.
     */
    public Block.OffsetType getOffsetType()
    {
        return Block.OffsetType.XYZ;
    }
}