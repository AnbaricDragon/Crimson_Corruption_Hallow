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

import net.minecraft.block.AbstractBlock.Properties;

public class TerraBlockTorchWallWaterproof extends TerraBlockTorchWaterproof implements IWaterLoggable
{
    public static final DirectionProperty HORIZONTAL_FACING;
    private static final Map<Direction, VoxelShape> SHAPES;

    public TerraBlockTorchWallWaterproof(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HORIZONTAL_FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public IParticleData getParticle()
    {
        if (this == TerraBlockRegistry.TORCH_CURSED_WALL.get()) { return TerraParticleRegistry.TORCH_FLAME_CURSED.get(); }
        if (this == TerraBlockRegistry.TORCH_CORAL_WALL.get()) { return TerraParticleRegistry.TORCH_FLAME_CORAL.get(); }
        else return this == TerraBlockRegistry.TORCH_ICHOR_WALL.get() ? TerraParticleRegistry.TORCH_FLAME_ICHOR.get() : ParticleTypes.FLAME;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return directionShape(state);
    }

    public static VoxelShape directionShape(BlockState state)
    {
        return SHAPES.get(state.getValue(HORIZONTAL_FACING));
    }

    public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos)
    {
        Direction  facing = state.getValue(HORIZONTAL_FACING);
        BlockPos   oppositePos = pos.relative(facing.getOpposite());
        BlockState oppositeState = world.getBlockState(oppositePos);
        return oppositeState.isFaceSturdy(world, oppositePos, facing);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockState   state = this.defaultBlockState();
        IWorldReader world = context.getLevel();
        BlockPos     pos = context.getClickedPos();
        Direction[]  lvt_5_1_ = context.getNearestLookingDirections();
        Direction[]  var6     = lvt_5_1_;
        int          var7     = lvt_5_1_.length;

        FluidState waterlogged = context.getLevel().getFluidState(context.getClickedPos());

        for (int var8 = 0; var8 < var7; ++var8)
        {
            Direction lvt_9_1_ = var6[var8];
            if (lvt_9_1_.getAxis().isHorizontal())
            {
                Direction lvt_10_1_ = lvt_9_1_.getOpposite();
                state = (BlockState) state.setValue(HORIZONTAL_FACING, lvt_10_1_);
                if (state.canSurvive(world, pos))
                {
                    return state.setValue(WATERLOGGED, waterlogged.getType() == Fluids.WATER);
                }
            }
        }

        return null;
    }

    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
        return facing.getOpposite() == stateIn.getValue(HORIZONTAL_FACING) && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : stateIn;
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
    {
        Direction facing  = (Direction) state.getValue(HORIZONTAL_FACING);
        double    lvt_6_1_  = (double) pos.getX() + 0.5D;
        double    lvt_8_1_  = (double) pos.getY() + 0.7D;
        double    lvt_10_1_ = (double) pos.getZ() + 0.5D;
        double    lvt_12_1_ = 0.22D;
        double    lvt_14_1_ = 0.27D;
        Direction lvt_16_1_ = facing.getOpposite();
        if (!state.getValue(WATERLOGGED))
        {
            world.addParticle(ParticleTypes.SMOKE, lvt_6_1_ + 0.27D * (double) lvt_16_1_.getStepX(), lvt_8_1_ + 0.22D, lvt_10_1_ + 0.27D * (double) lvt_16_1_.getStepZ(), 0.0D, 0.0D, 0.0D);
            world.addParticle(this.getParticle(), lvt_6_1_ + 0.27D * (double) lvt_16_1_.getStepX(), lvt_8_1_ + 0.22D, lvt_10_1_ + 0.27D * (double) lvt_16_1_.getStepZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    public BlockState rotate(BlockState state, Rotation rot)
    {
        return (BlockState) state.setValue(HORIZONTAL_FACING, rot.rotate((Direction) state.getValue(HORIZONTAL_FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn)
    {
        return state.rotate(mirrorIn.getRotation((Direction) state.getValue(HORIZONTAL_FACING)));
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(HORIZONTAL_FACING, WATERLOGGED);
    }

    static
    {
        HORIZONTAL_FACING = HorizontalBlock.FACING;
        SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.box(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D), Direction.SOUTH, Block.box(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D), Direction.WEST, Block.box(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D), Direction.EAST, Block.box(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));
    }
}