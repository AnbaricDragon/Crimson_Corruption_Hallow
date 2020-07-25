package com.anbaric.terra_reforged.blocks.saplings;

import com.anbaric.terra_reforged.features.trees.TerraTreeBoreal;
import com.anbaric.terra_reforged.features.trees.TerraTreeMahogany;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class TerraBlockSaplingMahogany extends BushBlock implements IGrowable
{
    public static final IntegerProperty STAGE = BlockStateProperties.STAGE_0_1;
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

    public TerraBlockSaplingMahogany(Properties properties)
    {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(STAGE, Integer.valueOf(0)));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand)
    {
        super.tick(state, worldIn, pos, rand);
        if (!worldIn.isAreaLoaded(pos, 1))
        {
            return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        }
        if (worldIn.getLight(pos.up()) >= 9 && rand.nextInt(7) == 0)
        {
            this.grow(worldIn, pos, state, rand);
        }

    }

    public void grow(ServerWorld worldIn, BlockPos pos, BlockState state, Random rand)
    {
        if (state.get(STAGE) == 0)
        {
            worldIn.setBlockState(pos, state.cycle(STAGE), 4);
        }
        else
        {
            if (!net.minecraftforge.event.ForgeEventFactory.saplingGrowTree(worldIn, rand, pos))
            {
                return;
            }
            for (int i = 0; i < 4; i++)
            {
                if (worldIn.getBlockState(pos.offset(Direction.byHorizontalIndex(i))).getBlock() == this &&
                    worldIn.getBlockState(pos.offset(Direction.byHorizontalIndex(i)).offset(Direction.byHorizontalIndex(i + (i == 3 ? -3 : 1)))).getBlock() == this &&
                    worldIn.getBlockState(pos.offset(Direction.byHorizontalIndex(i)).offset(Direction.byHorizontalIndex(i + (i == 3 ? -3 : 1))).offset(Direction.byHorizontalIndex(i + (i == 2 ? -2 : 2)))).getBlock() == this)
                {
                    TerraTreeMahogany.generateTree(worldIn, pos, rand, TerraBlockRegistry.LEAF_MAHOGANY.get().getDefaultState());
                }
                else
                {
                    TerraTreeMahogany.generateTree(worldIn, pos, rand, TerraBlockRegistry.LEAF_BOREAL.get().getDefaultState());
                }
            }
        }
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
    {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        return (double) worldIn.rand.nextFloat() < 1.45D;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state)
    {
        this.grow(worldIn, pos, state, rand);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(STAGE);
    }
}