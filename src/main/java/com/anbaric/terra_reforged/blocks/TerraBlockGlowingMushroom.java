package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.TerraReforged;
//import com.anbaric.terra_reforged.features.vegetation.TerraGlowingMushroom;
import com.anbaric.terra_reforged.features.vegetation.TerraFeatureMushroomGlowing;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.*;
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
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class TerraBlockGlowingMushroom extends BushBlock implements IGrowable, IPlantable
{
    public static final IntegerProperty STAGE = BlockStateProperties.STAGE;
    protected static final VoxelShape MUSHROOM_SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);


    public TerraBlockGlowingMushroom(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, 0));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return MUSHROOM_SHAPE;
    }

    protected boolean isValidGround(BlockState state)
    {
        Block target = state.getBlock();
        return target == TerraBlockRegistry.GRASS_MUSHROOM.get();
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        BlockPos blockpos = pos.below();
        if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        {
            return worldIn.getBlockState(blockpos).canSustainPlant(worldIn, pos, Direction.UP, this);
        }
        return isValidGround(worldIn.getBlockState(blockpos));
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand)
    {
        super.tick(state, worldIn, pos, rand);
        if (!worldIn.isAreaLoaded(pos, 1))
        {
            return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        }
        if (rand.nextInt(7) == 0)
        {
            this.performBonemeal(worldIn, rand, pos, state);
        }
    }

    @Override
    public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state)
    {
        if (state.getValue(STAGE) == 0)
        {
            worldIn.setBlock(pos, state.setValue(STAGE, 1), 4);
        }
        else
        {
            if (!net.minecraftforge.event.ForgeEventFactory.saplingGrowTree(worldIn, rand, pos))
            {
                return;
            }
            TerraFeatureMushroomGlowing.generateMushroom(worldIn, pos, rand);
        }
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos)
    {
        return TerraReforged.MUSHROOM;
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
    {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        return (double) worldIn.random.nextFloat() < 0.45D;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(STAGE);
    }
}