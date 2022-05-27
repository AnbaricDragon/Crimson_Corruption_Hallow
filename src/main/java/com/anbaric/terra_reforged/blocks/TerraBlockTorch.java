package com.anbaric.terra_reforged.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;
import java.util.function.Supplier;

public class TerraBlockTorch extends TorchBlock
{
    protected static final int AABB_STANDING_OFFSET = 2;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape AABB = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);
    protected final Supplier<ParticleOptions> flameParticle;

    public TerraBlockTorch(Properties properties, Supplier<ParticleOptions> particle)
    {
        super(properties, ParticleTypes.FLAME);
        this.flameParticle = particle;
    }

    @Override
    public void animateTick(BlockState p_57494_, Level p_57495_, BlockPos p_57496_, Random p_57497_)
    {
        double d0 = (double)p_57496_.getX() + 0.5D;
        double d1 = (double)p_57496_.getY() + 0.7D;
        double d2 = (double)p_57496_.getZ() + 0.5D;
        p_57495_.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        p_57495_.addParticle(getParticle(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    public ParticleOptions getParticle()
    {
        if (flameParticle.get() == null)
        {
            return ParticleTypes.FLAME;
        }
        else
        {
            return flameParticle.get();
        }
    }
}