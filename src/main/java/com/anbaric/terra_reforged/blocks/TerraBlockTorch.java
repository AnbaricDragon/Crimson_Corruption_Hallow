package com.anbaric.terra_reforged.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Random;

public class TerraBlockTorch extends Block implements SimpleWaterloggedBlock
{
    protected static final int AABB_STANDING_OFFSET = 2;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape AABB = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);
    protected final ParticleOptions flameParticle;

    public TerraBlockTorch(BlockBehaviour.Properties p_57491_, ParticleOptions p_57492_)
    {
        super(p_57491_);
        this.flameParticle = p_57492_;
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.FALSE));
    }

    public VoxelShape getShape(BlockState p_57510_, BlockGetter p_57511_, BlockPos p_57512_, CollisionContext p_57513_)
    {
        return AABB;
    }

    public FluidState getFluidState(BlockState p_56397_)
    {
        return p_56397_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_56397_);
    }

    public boolean placeLiquid(LevelAccessor p_56368_, BlockPos p_56369_, BlockState p_56370_, FluidState p_56371_)
    {
        return SimpleWaterloggedBlock.super.placeLiquid(p_56368_, p_56369_, p_56370_, p_56371_);
    }

    public boolean canPlaceLiquid(BlockGetter p_56363_, BlockPos p_56364_, BlockState p_56365_, Fluid p_56366_)
    {
        return SimpleWaterloggedBlock.super.canPlaceLiquid(p_56363_, p_56364_, p_56365_, p_56366_);
    }

    public BlockState updateShape(BlockState p_57503_, Direction p_57504_, BlockState p_57505_, LevelAccessor p_57506_, BlockPos p_57507_, BlockPos p_57508_)
    {
        if (p_57503_.getValue(WATERLOGGED))
        {
            p_57506_.scheduleTick(p_57507_, Fluids.WATER, Fluids.WATER.getTickDelay(p_57506_));
        }
        return p_57504_ == Direction.DOWN && !this.canSurvive(p_57503_, p_57506_, p_57507_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_57503_, p_57504_, p_57505_, p_57506_, p_57507_, p_57508_);
    }

    public boolean canSurvive(BlockState p_57499_, LevelReader p_57500_, BlockPos p_57501_)
    {
        return canSupportCenter(p_57500_, p_57501_.below(), Direction.UP);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(blockpos);
        return super.getStateForPlacement(context).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    public void animateTick(BlockState p_57494_, Level p_57495_, BlockPos p_57496_, Random p_57497_)
    {
        if (!p_57494_.getValue(WATERLOGGED))
        {
            double d0 = (double) p_57496_.getX() + 0.5D;
            double d1 = (double) p_57496_.getY() + 0.7D;
            double d2 = (double) p_57496_.getZ() + 0.5D;
            p_57495_.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            p_57495_.addParticle(this.flameParticle, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_56388_) {
        p_56388_.add(WATERLOGGED);
    }

    public boolean isPathfindable(BlockState p_56376_, BlockGetter p_56377_, BlockPos p_56378_, PathComputationType p_56379_)
    {
        switch (p_56379_)
        {
            case WATER:
                return p_56377_.getFluidState(p_56378_).is(FluidTags.WATER);
            default:
                return false;
        }
    }
}