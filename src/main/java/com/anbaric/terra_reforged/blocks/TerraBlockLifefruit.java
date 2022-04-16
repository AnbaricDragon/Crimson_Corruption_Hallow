package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TerraBlockLifefruit extends BushBlock
{
    protected static final VoxelShape LIFEFRUIT_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);

    public TerraBlockLifefruit(Properties properties)
    {
        super(properties);
    }

    public VoxelShape getShape(BlockState p_52419_, BlockGetter p_52420_, BlockPos p_52421_, CollisionContext p_52422_)
    {
        return LIFEFRUIT_SHAPE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter p_51043_, BlockPos p_51044_)
    {
        return state.is(TerraBlockRegistry.GRASS_JUNGLE.get());
    }
}
