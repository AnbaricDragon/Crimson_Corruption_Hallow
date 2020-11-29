package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraParticleRegistry;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
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

public class TerraBlockTorchWall extends TerraBlockTorch
{
    public static final DirectionProperty HORIZONTAL_FACING;
    private static final Map<Direction, VoxelShape> SHAPES;

    public TerraBlockTorchWall(Block.Properties p_i48298_1_)
    {
        super(p_i48298_1_);
        this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    public IParticleData getParticle()
    {
        if (this == TerraBlockRegistry.TORCH_GEM_RED_WALL.get()) { return TerraParticleRegistry.TORCH_FLAME_RED.get(); }
        else if (this == TerraBlockRegistry.TORCH_GEM_ORANGE_WALL.get()) { return TerraParticleRegistry.TORCH_FLAME_ORANGE.get(); }
        else if (this == TerraBlockRegistry.TORCH_GEM_YELLOW_WALL.get()) { return TerraParticleRegistry.TORCH_FLAME_YELLOW.get(); }
        else if (this == TerraBlockRegistry.TORCH_GEM_GREEN_WALL.get()) { return TerraParticleRegistry.TORCH_FLAME_GREEN.get(); }
        else if (this == TerraBlockRegistry.TORCH_GEM_BLUE_WALL.get()) { return TerraParticleRegistry.TORCH_FLAME_BLUE.get(); }
        else if (this == TerraBlockRegistry.TORCH_GEM_PURPLE_WALL.get()) { return TerraParticleRegistry.TORCH_FLAME_PURPLE.get(); }
        else if (this == TerraBlockRegistry.TORCH_GEM_WHITE_WALL.get()) { return TerraParticleRegistry.TORCH_FLAME_WHITE.get(); }
        else if (this == TerraBlockRegistry.TORCH_RAINBOW_WALL.get()) { return TerraParticleRegistry.TORCH_FLAME_RAINBOW.get(); }
        else if (this == TerraBlockRegistry.TORCH_ICE_WALL.get()) { return TerraParticleRegistry.TORCH_FLAME_ICE.get(); }
        else if (this == TerraBlockRegistry.TORCH_BONE_WALL.get()) { return TerraParticleRegistry.TORCH_FLAME_BONE.get(); }
        else if (this == TerraBlockRegistry.TORCH_BRIGHT_WALL.get()) { return TerraParticleRegistry.TORCH_FLAME_BRIGHT.get(); }
        else return this == TerraBlockRegistry.TORCH_DEMON_WALL.get() ? TerraParticleRegistry.TORCH_FLAME_DEMON.get() : ParticleTypes.FLAME;
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
        Direction  facing        = state.get(HORIZONTAL_FACING);
        BlockPos   oppositePos   = pos.offset(facing.getOpposite());
        BlockState oppositeState = world.getBlockState(oppositePos);
        return oppositeState.isSolidSide(world, oppositePos, facing);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockState   state    = this.getDefaultState();
        IWorldReader world    = context.getWorld();
        BlockPos     pos      = context.getPos();
        Direction[]  lvt_5_1_ = context.getNearestLookingDirections();
        Direction[]  var6     = lvt_5_1_;
        int          var7     = lvt_5_1_.length;

        for (int var8 = 0; var8 < var7; ++var8)
        {
            Direction lvt_9_1_ = var6[var8];
            if (lvt_9_1_.getAxis().isHorizontal())
            {
                Direction lvt_10_1_ = lvt_9_1_.getOpposite();
                state = (BlockState) state.with(HORIZONTAL_FACING, lvt_10_1_);
                if (state.isValidPosition(world, pos))
                {
                    return state;
                }
            }
        }

        return null;
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        return facing.getOpposite() == stateIn.get(HORIZONTAL_FACING) && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : stateIn;
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState p_180655_1_, World p_180655_2_, BlockPos p_180655_3_, Random p_180655_4_)
    {
        Direction lvt_5_1_  = (Direction) p_180655_1_.get(HORIZONTAL_FACING);
        double    lvt_6_1_  = (double) p_180655_3_.getX() + 0.5D;
        double    lvt_8_1_  = (double) p_180655_3_.getY() + 0.7D;
        double    lvt_10_1_ = (double) p_180655_3_.getZ() + 0.5D;
        double    lvt_12_1_ = 0.22D;
        double    lvt_14_1_ = 0.27D;
        Direction lvt_16_1_ = lvt_5_1_.getOpposite();
        p_180655_2_.addParticle(ParticleTypes.SMOKE, lvt_6_1_ + 0.27D * (double) lvt_16_1_.getXOffset(), lvt_8_1_ + 0.22D, lvt_10_1_ + 0.27D * (double) lvt_16_1_.getZOffset(), 0.0D, 0.0D, 0.0D);
        p_180655_2_.addParticle(this.getParticle(), lvt_6_1_ + 0.27D * (double) lvt_16_1_.getXOffset(), lvt_8_1_ + 0.22D, lvt_10_1_ + 0.27D * (double) lvt_16_1_.getZOffset(), 0.0D, 0.0D, 0.0D);
    }

    public BlockState rotate(BlockState state, Rotation p_185499_2_)
    {
        return (BlockState) state.with(HORIZONTAL_FACING, p_185499_2_.rotate((Direction) state.get(HORIZONTAL_FACING)));
    }

    public BlockState mirror(BlockState state, Mirror p_185471_2_)
    {
        return state.rotate(p_185471_2_.toRotation((Direction) state.get(HORIZONTAL_FACING)));
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
    }

    static
    {
        HORIZONTAL_FACING = HorizontalBlock.HORIZONTAL_FACING;
        SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.makeCuboidShape(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D), Direction.SOUTH, Block.makeCuboidShape(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D), Direction.WEST, Block.makeCuboidShape(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D), Direction.EAST, Block.makeCuboidShape(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));
    }
}