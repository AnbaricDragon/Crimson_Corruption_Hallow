package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.trees.Tree;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Random;
import java.util.function.Supplier;

public class TerraBlockSapling extends BushBlock implements IGrowable
{
    public static final IntegerProperty STAGE = BlockStateProperties.STAGE_0_1;
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
    private final Supplier<Tree> tree;

    public TerraBlockSapling(Supplier<Tree> treeIn, Properties properties)
    {
        super(properties);
        this.tree = treeIn;
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
            return;
        }
        if (worldIn.getLight(pos.up()) >= 9 && rand.nextInt(7) == 0)
        {
            this.grow(worldIn, pos, state, rand);
        }
    }

    public void grow(ServerWorld serverWorld, BlockPos pos, BlockState state, Random rand)
    {
        if (state.get(STAGE) == 0)
        {
            serverWorld.setBlockState(pos, state.cycle(STAGE), 4);
        }
        else
        {
            if (!ForgeEventFactory.saplingGrowTree(serverWorld, rand, pos))
            {
                return;
            }
            this.tree.get().place(serverWorld, serverWorld.getChunkProvider().getChunkGenerator(), pos, state, rand);
        }
    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        Block target = state.getBlock();
        if (this == TerraBlockRegistry.SAPLING_BOREAL.get()) {return target == Blocks.SNOW_BLOCK || target == TerraBlockRegistry.SNOW_CORRUPT.get() || target == TerraBlockRegistry.SNOW_CRIMSON.get() || target == TerraBlockRegistry.SNOW_HALLOWED.get() || target == Blocks.DIRT || target == Blocks.GRASS_BLOCK;}
        else if (this == TerraBlockRegistry.SAPLING_PALM.get()) {return target == Blocks.SAND || target == TerraBlockRegistry.SAND_EBON.get() || target == TerraBlockRegistry.SAND_CRIM.get() || target == TerraBlockRegistry.SAND_PEARL.get() || target == Blocks.DIRT || target == Blocks.GRASS_BLOCK;}
        else if (this == TerraBlockRegistry.SAPLING_MAHOGANY.get()) {return target == TerraBlockRegistry.SOIL_MUD.get() || target == TerraBlockRegistry.GRASS_JUNGLE.get() || target == TerraBlockRegistry.GRASS_MUSHROOM.get();}
        else if (this == TerraBlockRegistry.SAPLING_EBON.get()) {return target == TerraBlockRegistry.GRASS_CORRUPT.get() || target == Blocks.DIRT || target == Blocks.GRASS_BLOCK;}
        else if (this == TerraBlockRegistry.SAPLING_SHADE.get()) {return target == TerraBlockRegistry.GRASS_CRIMSON.get() || target == Blocks.DIRT || target == Blocks.GRASS_BLOCK;}
        else return this == TerraBlockRegistry.SAPLING_PEARL.get() ? target == TerraBlockRegistry.GRASS_HALLOWED.get() || target == Blocks.DIRT || target == Blocks.GRASS_BLOCK : target == Blocks.GRASS_BLOCK || target == Blocks.DIRT;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        BlockPos blockpos = pos.down();
        if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        {
            return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
        }
        return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
    }

    @Override
    public void grow(ServerWorld serverWorld, Random rand, BlockPos pos, BlockState state)
    {
        this.grow(serverWorld, pos, state, rand);
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
    {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        return (double) worldIn.rand.nextFloat() < 0.45D;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(STAGE);
    }
}