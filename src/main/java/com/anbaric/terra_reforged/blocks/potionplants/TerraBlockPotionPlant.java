package com.anbaric.terra_reforged.blocks.potionplants;

import com.anbaric.terra_reforged.util.init.TerraBiomeRegistry;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.ITag;
import net.minecraft.tags.Tag;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.DimensionType;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class TerraBlockPotionPlant extends BushBlock implements IGrowable
{
    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_2;
    public static VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 6.0D, 10.0D), Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 8.0D, 11.0D), Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 10.0D, 12.0D)};

    public ITag<Block> tag;

    protected TerraBlockPotionPlant(Block.Properties builder, ITag<Block> tag)
    {
        super(builder);
        this.tag = tag;
        this.setDefaultState(this.stateContainer.getBaseState().with(this.getAgeProperty(), Integer.valueOf(0)));
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
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
        return this.getDefaultState().with(this.getAgeProperty(), age);
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

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return state.getBlock().isIn(tag);
    }

    public boolean isInPlanter(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos.down()).getBlock().isIn(TerraTagRegistry.GENERAL_PLANTERS);
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        Biome biome = worldIn.getBiome(pos);
        if (isInPlanter(state, worldIn, pos)) return true;

        if (this == TerraBlockRegistry.PLANT_BLINKROOT.get())
        {
            return worldIn.getBlockState(pos.down()).isSolid();
        }
        else if (this == TerraBlockRegistry.PLANT_DAYBLOOM.get())
        {
            return this.isValidGround(worldIn.getBlockState(pos.down()), worldIn, pos) && (!biome.getRegistryName().toString().contains("corrupt") && !biome.getRegistryName().toString().contains("crimson"));
        }
        else if (this == TerraBlockRegistry.PLANT_DEATHWEED.get())
        {
            return this.isValidGround(worldIn.getBlockState(pos.down()), worldIn, pos) && (biome.getRegistryName().toString().contains("corrupt") || biome.getRegistryName().toString().contains("crimson"));
        }
        else if (this == TerraBlockRegistry.PLANT_FIREBLOSSOM.get() || this == TerraBlockRegistry.PLANT_MOONGLOW.get())
        {
            return this.isValidGround(worldIn.getBlockState(pos.down()), worldIn, pos);
        }
        else if (this == TerraBlockRegistry.PLANT_SHIVERTHORN.get())
        {
            return pos.getY() > 55 && this.isValidGround(worldIn.getBlockState(pos.down()), worldIn, pos);
        }
        else
        {
            return this == TerraBlockRegistry.PLANT_WATERLEAF.get() && pos.getY() > 60 && this.isValidGround(worldIn.getBlockState(pos.down()), worldIn, pos);
        }
    }

    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
    {
        if (entityIn instanceof RavagerEntity && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(worldIn, entityIn))
        {
            worldIn.destroyBlock(pos, true);
        }

        super.onEntityCollision(state, worldIn, pos, entityIn);
    }

    //TODO Make this use @CropsBlock

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

    @Override
    public void grow(ServerWorld p_225535_1_, Random p_225535_2_, BlockPos p_225535_3_, BlockState p_225535_4_)
    {
        this.grow(p_225535_1_, p_225535_3_, p_225535_4_);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(AGE);
    }
}