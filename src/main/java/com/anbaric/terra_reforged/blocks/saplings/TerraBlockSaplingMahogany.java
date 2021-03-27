package com.anbaric.terra_reforged.blocks.saplings;

import com.anbaric.terra_reforged.util.init.TerraFeatureRegistry;
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
import net.minecraft.world.gen.feature.NoFeatureConfig;
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

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }

    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        if (worldIn.getLight(pos.up()) >= 9 && random.nextInt(7) == 0)
        {
            if (!worldIn.isAreaLoaded(pos, 1))
            {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light
            }
            this.placeTree(worldIn, pos, state, random);
        }
    }

    public void placeTree(ServerWorld world, BlockPos pos, BlockState state, Random rand)
    {
        boolean is2x2 = false;
        BlockPos growthPos = pos;
        if (state.get(STAGE) == 0)
        {
            world.setBlockState(pos, state.cycleValue(STAGE), 4);
        }
        else
        {
            if (!net.minecraftforge.event.ForgeEventFactory.saplingGrowTree(world, rand, pos))
            {
                return;
            }
            for (int i = 0; i < 4; i++)
            {
                BlockPos A = pos;
                BlockPos B = A.offset(Direction.byHorizontalIndex(i));
                BlockPos C = B.offset(Direction.byHorizontalIndex((i+1)%4));
                BlockPos D = C.offset(Direction.byHorizontalIndex((i+2)%4));

                Block blockA = world.getBlockState(A).getBlock();
                Block blockB = world.getBlockState(B).getBlock();
                Block blockC = world.getBlockState(C).getBlock();
                Block blockD = world.getBlockState(D).getBlock();

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
                TerraFeatureRegistry.MAHOGANY_TREE_LARGE.get().generate(world, world.getChunkProvider().getChunkGenerator(), rand, pos, NoFeatureConfig.NO_FEATURE_CONFIG);
            }
            else
            {
                TerraFeatureRegistry.MAHOGANY_TREE.get().generate(world, world.getChunkProvider().getChunkGenerator(), rand, pos, NoFeatureConfig.NO_FEATURE_CONFIG);
            }
        }
    }

    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
    {
        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        return (double) worldIn.rand.nextFloat() < 0.45D;
    }

    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state)
    {
        this.placeTree(worldIn, pos, state, rand);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(STAGE);
    }
}
