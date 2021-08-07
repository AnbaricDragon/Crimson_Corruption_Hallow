package com.anbaric.terra_reforged.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class TerraBlockDoubleLantern extends Block
{
    public static int light;

    protected static VoxelShape TOP_SHAPE;
    protected static VoxelShape BOT_SHAPE;

    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

    public TerraBlockDoubleLantern(Properties properties, double topShape, double botShape, int light)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.UPPER).setValue(LIT, false));
        this.TOP_SHAPE = buildShape(topShape);
        this.BOT_SHAPE = buildShape(botShape);
        this.light = light;
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos)
    {
        return state.getValue(LIT) ? this.light : 0;
    }

    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving)
    {
        if (!worldIn.isClientSide && state.getValue(HALF) == DoubleBlockHalf.UPPER)
        {
            boolean isLit = state.getValue(LIT);
            if (isLit != worldIn.hasNeighborSignal(state.getValue(HALF) == DoubleBlockHalf.UPPER ? pos : pos.above()))
            {
                if (isLit)
                {
                    worldIn.getBlockTicks().scheduleTick(pos, this, 4);
                    worldIn.getBlockTicks().scheduleTick(pos.below(), this, 4);
                }
                else
                {
                    worldIn.setBlock(pos, state.setValue(LIT, true), 3);
                    worldIn.setBlock(pos.below(), state.setValue(LIT, true).setValue(HALF, DoubleBlockHalf.LOWER), 3);
                }
            }
        }
    }

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand)
    {
        if (state.getValue(LIT) && state.getValue(HALF) == DoubleBlockHalf.UPPER && !worldIn.hasNeighborSignal(pos))
        {
            worldIn.setBlock(pos, state.setValue(LIT, false), 3);
            worldIn.setBlock(pos.below(), state.setValue(LIT, false).setValue(HALF, DoubleBlockHalf.LOWER), 3);
        }
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return state.getValue(HALF) == DoubleBlockHalf.UPPER ? TOP_SHAPE : BOT_SHAPE;
    }

    public static VoxelShape buildShape(double radius)
    {
        return Block.box(8.0D - radius, 0.0D, 8.0D - radius, 8.0D + radius, 16.0D, 8.0D + radius);
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        DoubleBlockHalf doubleblockhalf = stateIn.getValue(HALF);
        if (facing.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.UPPER != (facing == Direction.DOWN) || facingState.getBlock() == this && facingState.getValue(HALF) != doubleblockhalf)
        {
            return doubleblockhalf == DoubleBlockHalf.UPPER && facing == Direction.UP && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
        else
        {
            return Blocks.AIR.defaultBlockState();
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockPos blockpos = context.getClickedPos();
        return blockpos.getY() > 0 && context.getLevel().getBlockState(blockpos.below()).canBeReplaced(context) ? super.getStateForPlacement(context) : null;
    }

    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
    {
        worldIn.setBlock(pos.below(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER), 3);
    }

    public boolean isValidAnchor(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        BlockPos blockpos = pos.above();
        if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        {
            return worldIn.getBlockState(pos.above()).canOcclude();
        }
        return worldIn.getBlockState(pos.above()).canOcclude();
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        if (state.getValue(HALF) != DoubleBlockHalf.LOWER)
        {
            return isValidAnchor(state, worldIn, pos);
        }
        else
        {
            BlockState blockstate = worldIn.getBlockState(pos.above());
            return blockstate.getBlock() == this && blockstate.getValue(HALF) == DoubleBlockHalf.UPPER;
        }
    }

    public void playerDestroy(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        super.playerDestroy(worldIn, player, pos, Blocks.AIR.defaultBlockState(), te, stack);
    }

    public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player)
    {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        BlockPos        blockpos        = doubleblockhalf == DoubleBlockHalf.UPPER ? pos.below() : pos.above();
        BlockState      blockstate      = worldIn.getBlockState(blockpos);
        if (blockstate.getBlock() == this && blockstate.getValue(HALF) != doubleblockhalf)
        {
            worldIn.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
            worldIn.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
            if (!worldIn.isClientSide && !player.isCreative())
            {
                dropResources(state, worldIn, pos, null, player, player.getMainHandItem());
                dropResources(blockstate, worldIn, blockpos, null, player, player.getMainHandItem());
            }
        }
        super.playerWillDestroy(worldIn, pos, state, player);
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(HALF, LIT);
    }
}