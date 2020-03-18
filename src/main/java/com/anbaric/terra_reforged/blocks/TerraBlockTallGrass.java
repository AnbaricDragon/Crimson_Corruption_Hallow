package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

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
        return block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.FARMLAND || block == TerraBlockRegistry.GRASS_CRIMSON.get() || block == TerraBlockRegistry.GRASS_CORRUPT.get() || block == TerraBlockRegistry.GRASS_HALLOWED.get() || block == TerraBlockRegistry.GRASS_JUNGLE.get() || block == TerraBlockRegistry.GRASS_MUSHROOM.get();
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

    @Override
    public void grow(ServerWorld worldIn, Random p_225535_2_, BlockPos pos, BlockState p_225535_4_)
    {
//        TerraBlockDoubleGrass doublePlant = (TerraBlockDoubleGrass) (this == TerraBlockRegistry.TALLGRASS_CORRUPT.get() ? TerraBlockRegistry.TALLGRASSDOUBLE_CORRUPT.get() : this == TerraBlockRegistry.TALLGRASS_CRIMSON.get() ? TerraBlockRegistry.TALLGRASSDOUBLE_CRIMSON.get() : TerraBlockRegistry.TALLGRASSDOUBLE_HALLOWED.get());
//        if (doublePlant.getDefaultState().isValidPosition(worldIn, pos) && worldIn.isAirBlock(pos.up()))
//        {
//            System.out.println("Attempting to grow from bonemeal");
//            doublePlant.placeAt(worldIn, pos, 2);
//        }
    }

    /**
     * Get the OffsetType for this Block. Determines if the model is rendered slightly offset.
     */
    public Block.OffsetType getOffsetType()
    {
        return Block.OffsetType.XYZ;
    }
}