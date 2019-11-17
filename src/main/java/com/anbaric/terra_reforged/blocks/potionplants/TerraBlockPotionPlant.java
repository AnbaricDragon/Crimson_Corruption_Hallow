package com.anbaric.terra_reforged.blocks.potionplants;

import com.anbaric.terra_reforged.blocks.TerraBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.Random;

public class TerraBlockPotionPlant extends BushBlock implements IGrowable
{
    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_2;
    public static VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]
    {
        Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 6.0D, 10.0D),
        Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 8.0D, 11.0D),
        Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 10.0D, 12.0D)
    };

    public Block[][] BLOCKS = new Block[][]
    {
        {Blocks.DIRT, Blocks.GRASS_BLOCK, TerraBlocks.GRASS_CORRUPT, TerraBlocks.GRASS_CRIMSON, TerraBlocks.GRASS_HALLOWED, Blocks.STONE, TerraBlocks.STONE_EBON, TerraBlocks.STONE_CRIM, TerraBlocks.STONE_PEARL},
        {Blocks.GRASS_BLOCK, TerraBlocks.GRASS_HALLOWED},
        {TerraBlocks.GRASS_CORRUPT, TerraBlocks.GRASS_CRIMSON, TerraBlocks.STONE_EBON, TerraBlocks.STONE_CRIM},
        {TerraBlocks.SOIL_ASH},
        {TerraBlocks.SOIL_MUD, TerraBlocks.GRASS_JUNGLE, TerraBlocks.GRASS_MUSHROOM},
        {Blocks.SNOW, TerraBlocks.SNOW_CORRUPT, TerraBlocks.SNOW_CRIMSON, TerraBlocks.SNOW_HALLOWED, Blocks.ICE, TerraBlocks.ICE_PURPLE, TerraBlocks.ICE_RED, TerraBlocks.ICE_PINK, Blocks.PACKED_ICE, TerraBlocks.ICE_HARD_PURPLE, TerraBlocks.ICE_HARD_RED, TerraBlocks.ICE_HARD_PINK},
        {Blocks.SAND, TerraBlocks.SAND_HARD, TerraBlocks.SAND_PEARL, TerraBlocks.SAND_HARDPEARL}
    };

    public int blocksIndex;
    public Item seed;

    protected TerraBlockPotionPlant(Block.Properties builder, Integer arrayIndex, Item seed)
    {
        super(builder);
        this.seed = seed;
        this.blocksIndex = arrayIndex;
        this.setDefaultState(this.stateContainer.getBaseState().with(this.getAgeProperty(), Integer.valueOf(0)));
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }

    public Block[] getBlocks(Block[][] mainArray, Integer index)
    {
        return mainArray[index];
    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        Block[] validBlocks = getBlocks(BLOCKS, blocksIndex);
        System.out.println(validBlocks);
        boolean isValid = false;
        for (Block block : validBlocks)
        {
            if (state.getBlock() == block)
            {
                isValid = true;
            }
        }
        return isValid;
    }

    public IntegerProperty getAgeProperty()
    {
        return AGE;
    }

    public int getMaxAge()
    {
        return 2;
    }

    protected int getAge(BlockState state)
    {
        return state.get(this.getAgeProperty());
    }

    public BlockState withAge(int age)
    {
        return this.getDefaultState().with(this.getAgeProperty(), Integer.valueOf(age));
    }

    public boolean isMaxAge(BlockState state)
    {
        return state.get(this.getAgeProperty()) >= this.getMaxAge();
    }

    public void grow(World worldIn, BlockPos pos, BlockState state)
    {
        int i = this.getAge(state);
        int j = this.getMaxAge();
        if (i > j)
        {
            i = j;
        }

        worldIn.setBlockState(pos, this.withAge(i), 2);
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        return this.isValidGround(worldIn.getBlockState(pos.down()), worldIn, pos);
    }

    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
    {
        if (entityIn instanceof RavagerEntity && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(worldIn, entityIn))
        {
            worldIn.destroyBlock(pos, true);
        }

        super.onEntityCollision(state, worldIn, pos, entityIn);
    }

    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state)
    {
        return new ItemStack(this.seed);
    }

    /**
     * Whether this IGrowable can grow
     */
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
    {
        return !this.isMaxAge(state);
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        return false;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        this.grow(worldIn, pos, state);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(AGE);
    }
}