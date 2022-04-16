package com.anbaric.terra_reforged.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import javax.annotation.Nullable;

public class TerraBlockPlantHanging extends BushBlock
{
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public TerraBlockPlantHanging(BlockBehaviour.Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.UPPER));
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos p_52899_)
    {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        if (direction.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.UPPER != (direction == Direction.DOWN) || neighborState.is(this) && neighborState.getValue(HALF) != doubleblockhalf)
        {
            return doubleblockhalf == DoubleBlockHalf.UPPER && direction == Direction.UP && !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, world, pos, p_52899_);
        }
        else
        {
            return Blocks.AIR.defaultBlockState();
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        BlockPos blockpos = context.getClickedPos();
        Level    level    = context.getLevel();
        return blockpos.getY() > level.getMinBuildHeight() + 1 && level.getBlockState(blockpos.below()).canBeReplaced(context) ? super.getStateForPlacement(context) : null;
    }

    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack item)
    {
        BlockPos blockpos = pos.below();
        world.setBlock(blockpos, copyWaterloggedFrom(world, blockpos, this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER)), 3);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos)
    {
        return state.isFaceSturdy(world, pos, Direction.DOWN);
    }

    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
    {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER)
        {
            return Block.canSupportCenter(world, pos.above(), Direction.DOWN) && !world.isWaterAt(pos);
        }
        else
        {
            BlockState blockstate = world.getBlockState(pos.above());
            if (state.getBlock() != this)
            {
                return mayPlaceOn(state, world, pos.above()); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            }
            return blockstate.is(this) && blockstate.getValue(HALF) == DoubleBlockHalf.UPPER;
        }
    }

    public static void placeAt(LevelAccessor world, BlockState state, BlockPos pos, int flag)
    {
        BlockPos blockpos = pos.below();
        world.setBlock(pos, copyWaterloggedFrom(world, pos, state.setValue(HALF, DoubleBlockHalf.UPPER)), flag);
        world.setBlock(blockpos, copyWaterloggedFrom(world, blockpos, state.setValue(HALF, DoubleBlockHalf.LOWER)), flag);
    }

    public static BlockState copyWaterloggedFrom(LevelReader world, BlockPos pos, BlockState state)
    {
        return state.hasProperty(BlockStateProperties.WATERLOGGED) ? state.setValue(BlockStateProperties.WATERLOGGED, world.isWaterAt(pos)) : state;
    }

    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player)
    {
        if (!world.isClientSide)
        {
            if (player.isCreative())
            {
                preventCreativeDropFromTopPart(world, pos, state, player);
            }
            else
            {
                dropResources(state, world, pos, (BlockEntity) null, player, player.getMainHandItem());
            }
        }

        super.playerWillDestroy(world, pos, state, player);
    }

    public void playerDestroy(Level p_52865_, Player p_52866_, BlockPos p_52867_, BlockState p_52868_, @Nullable BlockEntity p_52869_, ItemStack p_52870_)
    {
        super.playerDestroy(p_52865_, p_52866_, p_52867_, Blocks.AIR.defaultBlockState(), p_52869_, p_52870_);
    }

    protected static void preventCreativeDropFromTopPart(Level world, BlockPos pos, BlockState state, Player player)
    {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        if (doubleblockhalf == DoubleBlockHalf.LOWER)
        {
            BlockPos   blockpos   = pos.above();
            BlockState blockstate = world.getBlockState(blockpos);
            if (blockstate.is(state.getBlock()) && blockstate.getValue(HALF) == DoubleBlockHalf.UPPER)
            {
                BlockState blockstate1 = blockstate.hasProperty(BlockStateProperties.WATERLOGGED) && blockstate.getValue(BlockStateProperties.WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                world.setBlock(blockpos, blockstate1, 35);
                world.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
            }
        }

    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_52901_)
    {
        p_52901_.add(HALF);
    }

    public BlockBehaviour.OffsetType getOffsetType()
    {
        return BlockBehaviour.OffsetType.XZ;
    }

    public long getSeed(BlockState p_52891_, BlockPos p_52892_)
    {
        return Mth.getSeed(p_52892_.getX(), p_52892_.below(p_52891_.getValue(HALF) == DoubleBlockHalf.UPPER ? 0 : 1).getY(), p_52892_.getZ());
    }
}
