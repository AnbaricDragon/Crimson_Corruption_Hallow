package com.anbaric.terra_reforged.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TerraBlockThinIce extends Block
{
    public TerraBlockThinIce(Properties properties)
    {
        super(properties);
    }

    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    public boolean destroyNotIce(World worldIn, BlockPos pos)
    {
        BlockState blockstate = worldIn.getBlockState(pos);
        Block block = blockstate.getBlock();

        if (block != TerraBlocks.ICE_THIN)
        {
            return false;
        }
        else
        {
            worldIn.playEvent(2001, pos, Block.getStateId(blockstate));
            return worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }

    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
    {
        double x = entityIn.posX;
        double z = entityIn.posZ;
        double xi = MathHelper.floor(entityIn.posX);
        double zi = MathHelper.floor(entityIn.posZ);
        double dotX = MathHelper.abs((float) (x - xi));
        double dotZ = MathHelper.abs((float) (z - zi));

        if (entityIn instanceof PlayerEntity)
        {
            if (fallDistance >= 0.6)
            {
                worldIn.destroyBlock(pos, false);

                if (dotZ <= 0.300)
                {
                    destroyNotIce(worldIn, pos.north());
                    if (dotX <= 0.300)
                    {
                        destroyNotIce(worldIn, pos.add( -1, 0, -1));
                    }
                }
                if (dotX >= 0.700)
                {
                    destroyNotIce(worldIn, pos.east());
                    if (dotZ <= 0.300)
                    {
                        destroyNotIce(worldIn, pos.add( 1, 0, -1));
                    }
                }
                if (dotZ >= 0.700)
                {
                    destroyNotIce(worldIn, pos.south());
                    if (dotX >= 0.700)
                    {
                        destroyNotIce(worldIn, pos.add( 1, 0, 1));
                    }
                }
                if (dotX <= 0.300)
                {
                    destroyNotIce(worldIn, pos.west());
                    if (dotZ >= 0.700)
                    {
                        destroyNotIce(worldIn, pos.add( -1, 0, 1));
                    }
                }
            }
        }
        entityIn.addVelocity(0, -0.25D, 0);
        super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side)
    {
        return adjacentBlockState.getBlock() == this ? true : super.isSideInvisible(state, adjacentBlockState, side);
    }
}