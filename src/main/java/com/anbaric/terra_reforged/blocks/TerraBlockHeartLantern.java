package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.TerraTileEntityRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class TerraBlockHeartLantern extends Block
{
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
    public static final VoxelShape SHAPE = Block.makeCuboidShape(1, 1, 6, 14, 16, 10);

    public TerraBlockHeartLantern(Properties properties)
    {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState());
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return TerraTileEntityRegistry.LANTERN_HEART.get().create();
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos)
    {
        return state.get(LIT) ? 14 : 0;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving)
    {
        if (!worldIn.isRemote)
        {
            if (!isValidPosition(state,worldIn,pos))
            {
                worldIn.destroyBlock(pos, true);
            }
            boolean isLit = state.get(LIT);
            if (isLit != worldIn.isBlockPowered(pos))
            {
                if (isLit)
                {
                    worldIn.getPendingBlockTicks().scheduleTick(pos, this, 2);
                }
                else
                {
                    worldIn.setBlockState(pos, state.cycleValue(LIT), 2);
                }
            }
        }
    }

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand)
    {
        if (state.get(LIT) && !worldIn.isBlockPowered(pos))
        {
            worldIn.setBlockState(pos, state.cycleValue(LIT), 2);
        }
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos.up()).isSolid();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(LIT);
    }
}
