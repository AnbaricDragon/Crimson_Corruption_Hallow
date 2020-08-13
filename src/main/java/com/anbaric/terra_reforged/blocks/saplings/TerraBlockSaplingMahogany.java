package com.anbaric.terra_reforged.blocks.saplings;

import com.anbaric.terra_reforged.features.vegetation.TerraTreeMahoganyGreat;
import com.anbaric.terra_reforged.features.vegetation.TerraTreeMahoganyLesser;
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
        boolean is2x2 = false;
        BlockPos growthPos = pos;
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
                BlockPos A = pos;
                BlockPos B = A.offset(Direction.byHorizontalIndex(i));
                BlockPos C = B.offset(Direction.byHorizontalIndex((i+1)%4));
                BlockPos D = C.offset(Direction.byHorizontalIndex((i+2)%4));

                Block blockA = worldIn.getBlockState(A).getBlock();
                Block blockB = worldIn.getBlockState(B).getBlock();
                Block blockC = worldIn.getBlockState(C).getBlock();
                Block blockD = worldIn.getBlockState(D).getBlock();

                if (blockA == this && blockB == this && blockC == this && blockD == this)
                {
                    switch(i)
                    {
                        case 0: growthPos = D; break;
                        case 1: growthPos = C; break;
                        case 2: growthPos = B; break;
                        case 3: growthPos = A; break;
                    }
                    is2x2 = true;
                }
            }
            if (is2x2)
            {
                TerraTreeMahoganyGreat.generateTree(worldIn, growthPos, rand);
            }
            else
            {
                TerraTreeMahoganyLesser.generateTree(worldIn, pos, rand);
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