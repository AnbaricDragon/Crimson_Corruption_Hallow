package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock;

public class TerraBlockHangingPlant extends BushBlock
{
    protected static final VoxelShape HANGING_SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);

    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public TerraBlockHangingPlant(AbstractBlock.Properties properties)
    {
        super(properties.noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.UPPER));
    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
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

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
    {
        worldIn.setBlock(pos.below(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER), 3);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        Block block = state.getBlock();
        return block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL || block == Blocks.FARMLAND || block == Blocks.SANDSTONE || block == Blocks.STONE || block == TerraBlockRegistry.GRASS_CRIMSON.get() || block == TerraBlockRegistry.GRASS_CORRUPT.get() || block == TerraBlockRegistry.GRASS_HALLOWED.get() || block == TerraBlockRegistry.STONE_CRIM.get() || block == TerraBlockRegistry.STONE_EBON.get() || block == TerraBlockRegistry.STONE_PEARL.get() || block == TerraBlockRegistry.SOIL_MUD.get() || block == TerraBlockRegistry.GRASS_MUSHROOM.get() || block == TerraBlockRegistry.GRASS_JUNGLE.get();
    }

    public boolean isValidAnchor(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        BlockPos blockpos = pos.above();
        if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        {
            return worldIn.getBlockState(blockpos).canSustainPlant(worldIn, blockpos, Direction.UP, this);
        }
        return mayPlaceOn(worldIn.getBlockState(blockpos), worldIn, blockpos);
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
            if (state.getBlock() != this)
            {
                return isValidAnchor(state, worldIn, pos);
            }
            else
            {
                return blockstate.getBlock() == this && blockstate.getValue(HALF) == DoubleBlockHalf.UPPER;
            }
        }
    }


    /**
     * Spawns the block's drops in the world. By the time this is called the Block has possibly been set to air via
     * Block.removedByPlayer
     */
    public void playerDestroy(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        super.playerDestroy(worldIn, player, pos, Blocks.AIR.defaultBlockState(), te, stack);
    }

    /**
     * Called before the Block is set to air in the world. Called regardless of if the player's tool can actually collect
     * this block
     */
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
                dropResources(state, worldIn, pos, (TileEntity) null, player, player.getMainHandItem());
                dropResources(blockstate, worldIn, blockpos, (TileEntity) null, player, player.getMainHandItem());
            }
        }

        super.playerWillDestroy(worldIn, pos, state, player);
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(HALF);
    }

    /**
     * Get the OffsetType for this Block. Determines if the model is rendered slightly offset.
     */
    public AbstractBlock.OffsetType getOffsetType()
    {
        return AbstractBlock.OffsetType.XZ;
    }

    /**
     * Return a random long to be passed to {@link IBakedModel#getQuads}, used for random model rotations
     */
    @OnlyIn(Dist.CLIENT)
    public long getSeed(BlockState state, BlockPos pos)
    {
        return MathHelper.getSeed(pos.getX(), pos.above(state.getValue(HALF) == DoubleBlockHalf.UPPER ? 0 : 1).getY(), pos.getZ());
    }
}