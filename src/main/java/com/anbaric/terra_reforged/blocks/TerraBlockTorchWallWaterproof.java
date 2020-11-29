package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraParticleRegistry;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;

public class TerraBlockTorchWallWaterproof extends TerraBlockTorchWaterproof implements IWaterLoggable
{
    public static final DirectionProperty HORIZONTAL_FACING;
    private static final Map<Direction, VoxelShape> SHAPES;

    public TerraBlockTorchWallWaterproof(Properties p_i48298_1_)
    {
        super(p_i48298_1_);
        this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    @Override
    public IParticleData getParticle()
    {
        if (this == TerraBlockRegistry.TORCH_CURSED_WALL.get()) { return TerraParticleRegistry.TORCH_FLAME_CURSED.get(); }
        else return this == TerraBlockRegistry.TORCH_ICHOR_WALL.get() ? TerraParticleRegistry.TORCH_FLAME_ICHOR.get() : ParticleTypes.FLAME;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return directionShape(state);
    }

    public static VoxelShape directionShape(BlockState state)
    {
        return SHAPES.get(state.get(HORIZONTAL_FACING));
    }

    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
    {
        Direction  facing = state.get(HORIZONTAL_FACING);
        BlockPos   oppositePos = pos.offset(facing.getOpposite());
        BlockState oppositeState = world.getBlockState(oppositePos);
        return oppositeState.isSolidSide(world, oppositePos, facing);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockState   state = this.getDefaultState();
        IWorldReader world = context.getWorld();
        BlockPos     pos = context.getPos();
        Direction[]  lvt_5_1_ = context.getNearestLookingDirections();
        Direction[]  var6     = lvt_5_1_;
        int          var7     = lvt_5_1_.length;

        FluidState waterlogged = context.getWorld().getFluidState(context.getPos());

        for (int var8 = 0; var8 < var7; ++var8)
        {
            Direction lvt_9_1_ = var6[var8];
            if (lvt_9_1_.getAxis().isHorizontal())
            {
                Direction lvt_10_1_ = lvt_9_1_.getOpposite();
                state = (BlockState) state.with(HORIZONTAL_FACING, lvt_10_1_);
                if (state.isValidPosition(world, pos))
                {
                    return state.with(WATERLOGGED, waterlogged.getFluid() == Fluids.WATER);
                }
            }
        }

        return null;
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        return facing.getOpposite() == stateIn.get(HORIZONTAL_FACING) && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : stateIn;
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
    {
        Direction facing  = (Direction) state.get(HORIZONTAL_FACING);
        double    lvt_6_1_  = (double) pos.getX() + 0.5D;
        double    lvt_8_1_  = (double) pos.getY() + 0.7D;
        double    lvt_10_1_ = (double) pos.getZ() + 0.5D;
        double    lvt_12_1_ = 0.22D;
        double    lvt_14_1_ = 0.27D;
        Direction lvt_16_1_ = facing.getOpposite();
        if (!state.get(WATERLOGGED))
        {
            world.addParticle(ParticleTypes.SMOKE, lvt_6_1_ + 0.27D * (double) lvt_16_1_.getXOffset(), lvt_8_1_ + 0.22D, lvt_10_1_ + 0.27D * (double) lvt_16_1_.getZOffset(), 0.0D, 0.0D, 0.0D);
            world.addParticle(this.getParticle(), lvt_6_1_ + 0.27D * (double) lvt_16_1_.getXOffset(), lvt_8_1_ + 0.22D, lvt_10_1_ + 0.27D * (double) lvt_16_1_.getZOffset(), 0.0D, 0.0D, 0.0D);
        }
    }

    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_)
    {
        return (BlockState) p_185499_1_.with(HORIZONTAL_FACING, p_185499_2_.rotate((Direction) p_185499_1_.get(HORIZONTAL_FACING)));
    }

    public BlockState mirror(BlockState p_185471_1_, Mirror p_185471_2_)
    {
        return p_185471_1_.rotate(p_185471_2_.toRotation((Direction) p_185471_1_.get(HORIZONTAL_FACING)));
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_)
    {
        p_206840_1_.add(HORIZONTAL_FACING, WATERLOGGED);
    }

    static
    {
        HORIZONTAL_FACING = HorizontalBlock.HORIZONTAL_FACING;
        SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.makeCuboidShape(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D), Direction.SOUTH, Block.makeCuboidShape(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D), Direction.WEST, Block.makeCuboidShape(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D), Direction.EAST, Block.makeCuboidShape(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));
    }
}