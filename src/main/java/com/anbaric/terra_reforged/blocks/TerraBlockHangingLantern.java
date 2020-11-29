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

public class TerraBlockHangingLantern extends Block
{
    public static int light;

    protected static VoxelShape TOP_SHAPE;
    protected static VoxelShape BOT_SHAPE;

    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

    public TerraBlockHangingLantern(Properties properties, double topShape, double botShape, int light)
    {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(HALF, DoubleBlockHalf.UPPER).with(LIT, false));
        this.TOP_SHAPE = buildShape(topShape);
        this.BOT_SHAPE = buildShape(botShape);
        this.light = light;
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos)
    {
        return state.get(LIT) ? this.light : 0;
    }

    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving)
    {
        if (!worldIn.isRemote && state.get(HALF) == DoubleBlockHalf.UPPER)
        {
            boolean isLit = state.get(LIT);
            if (isLit != worldIn.isBlockPowered(state.get(HALF) == DoubleBlockHalf.UPPER ? pos : pos.up()))
            {
                if (isLit)
                {
                    worldIn.getPendingBlockTicks().scheduleTick(pos, this, 4);
                    worldIn.getPendingBlockTicks().scheduleTick(pos.down(), this, 4);
                }
                else
                {
                    worldIn.setBlockState(pos, state.with(LIT, true), 3);
                    worldIn.setBlockState(pos.down(), state.with(LIT, true).with(HALF, DoubleBlockHalf.LOWER), 3);
                }
            }
        }
    }

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand)
    {
        if (state.get(LIT) && state.get(HALF) == DoubleBlockHalf.UPPER && !worldIn.isBlockPowered(pos))
        {
            worldIn.setBlockState(pos, state.with(LIT, false), 3);
            worldIn.setBlockState(pos.down(), state.with(LIT, false).with(HALF, DoubleBlockHalf.LOWER), 3);
        }
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return state.get(HALF) == DoubleBlockHalf.UPPER ? TOP_SHAPE : BOT_SHAPE;
    }

    public static VoxelShape buildShape(double radius)
    {
        return Block.makeCuboidShape(8.0D - radius, 0.0D, 8.0D - radius, 8.0D + radius, 16.0D, 8.0D + radius);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        DoubleBlockHalf doubleblockhalf = stateIn.get(HALF);
        if (facing.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.UPPER != (facing == Direction.DOWN) || facingState.getBlock() == this && facingState.get(HALF) != doubleblockhalf)
        {
            return doubleblockhalf == DoubleBlockHalf.UPPER && facing == Direction.UP && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
        else
        {
            return Blocks.AIR.getDefaultState();
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockPos blockpos = context.getPos();
        return blockpos.getY() > 0 && context.getWorld().getBlockState(blockpos.down()).isReplaceable(context) ? super.getStateForPlacement(context) : null;
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
    {
        worldIn.setBlockState(pos.down(), this.getDefaultState().with(HALF, DoubleBlockHalf.LOWER), 3);
    }

    public boolean isValidAnchor(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        BlockPos blockpos = pos.up();
        if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        {
            return worldIn.getBlockState(pos.up()).isSolid();
        }
        return worldIn.getBlockState(pos.up()).isSolid();
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        if (state.get(HALF) != DoubleBlockHalf.LOWER)
        {
            return isValidAnchor(state, worldIn, pos);
        }
        else
        {
            BlockState blockstate = worldIn.getBlockState(pos.up());
            return blockstate.getBlock() == this && blockstate.get(HALF) == DoubleBlockHalf.UPPER;
        }
    }

    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        super.harvestBlock(worldIn, player, pos, Blocks.AIR.getDefaultState(), te, stack);
    }

    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player)
    {
        DoubleBlockHalf doubleblockhalf = state.get(HALF);
        BlockPos        blockpos        = doubleblockhalf == DoubleBlockHalf.UPPER ? pos.down() : pos.up();
        BlockState      blockstate      = worldIn.getBlockState(blockpos);
        if (blockstate.getBlock() == this && blockstate.get(HALF) != doubleblockhalf)
        {
            worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
            worldIn.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
            if (!worldIn.isRemote && !player.isCreative())
            {
                spawnDrops(state, worldIn, pos, null, player, player.getHeldItemMainhand());
                spawnDrops(blockstate, worldIn, blockpos, null, player, player.getHeldItemMainhand());
            }
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(HALF, LIT);
    }
}