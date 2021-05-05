package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.block.AbstractBlock.Properties;

public class TerraBlockThinIce extends Block
{
    public TerraBlockThinIce(Properties properties)
    {
        super(properties);
    }

    public void destroyNotIce(World worldIn, BlockPos pos)
    {
        int i;
        for (i = 0; worldIn.getBlockState(pos.below(i)).getBlock() == TerraBlockRegistry.ICE_THIN.get(); i++)
        {
            worldIn.playSound(null, pos.below(i), SoundEvents.GLASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
            worldIn.setBlockAndUpdate(pos.below(i), Blocks.AIR.defaultBlockState());
        }
    }

    public void fallOn(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
    {
        Vector3d entityPos = entityIn.position();

        double x = entityPos.x;
        double z = entityPos.z;
        double xi = MathHelper.floor(entityPos.x);
        double zi = MathHelper.floor(entityPos.z);
        double dotX = MathHelper.abs((float) (x - xi));
        double dotZ = MathHelper.abs((float) (z - zi));

        if (entityIn instanceof PlayerEntity)
        {
            if (fallDistance >= 0.25)
            {
                destroyNotIce(worldIn, pos);

                if (dotZ <= 0.300)
                {
                    destroyNotIce(worldIn, pos.north());
                    if (dotX <= 0.300)
                    {
                        destroyNotIce(worldIn, pos.offset( -1, 0, -1));
                    }
                }
                if (dotX >= 0.700)
                {
                    destroyNotIce(worldIn, pos.east());
                    if (dotZ <= 0.300)
                    {
                        destroyNotIce(worldIn, pos.offset( 1, 0, -1));
                    }
                }
                if (dotZ >= 0.700)
                {
                    destroyNotIce(worldIn, pos.south());
                    if (dotX >= 0.700)
                    {
                        destroyNotIce(worldIn, pos.offset( 1, 0, 1));
                    }
                }
                if (dotX <= 0.300)
                {
                    destroyNotIce(worldIn, pos.west());
                    if (dotZ >= 0.700)
                    {
                        destroyNotIce(worldIn, pos.offset( -1, 0, 1));
                    }
                }
            }
        }
        entityIn.push(0, -0.25D, 0);
        super.fallOn(worldIn, pos, entityIn, fallDistance);
    }

    @OnlyIn(Dist.CLIENT)
    public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side)
    {
        return adjacentBlockState.getBlock() == this ? true : super.skipRendering(state, adjacentBlockState, side);
    }
}