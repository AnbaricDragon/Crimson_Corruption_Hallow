package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.util.handlers.SpreadingHandler.EnumBiomeBlockType;
import com.anbaric.terra_reforged.util.handlers.SpreadingHandler.EnumBiomeType;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import java.util.List;
import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class TerraBlockBiomeGrass extends SnowyDirtBlock implements IGrowable
{
    private EnumBiomeType biome;

    public TerraBlockBiomeGrass(Properties properties, EnumBiomeType biome)
    {
        super(properties);
        this.biome = biome;
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable)
    {
        Boolean reed  = false;
        Block   plant = plantable.getPlant(world, pos.up()).getBlock();
        if (plant == Blocks.SUGAR_CANE || plant instanceof TerraBlockReeds)
        {
            for (Direction direction : Direction.Plane.HORIZONTAL)
            {
                BlockState blockstate  = world.getBlockState(pos.offset(direction));
                FluidState ifluidstate = world.getFluidState(pos.offset(direction));
                if (ifluidstate.isTagged(FluidTags.WATER) || blockstate.getBlock() == Blocks.FROSTED_ICE)
                {
                    reed = true;
                }
            }
        }
        return plantable.getPlantType(world, pos) == PlantType.PLAINS || reed;
    }

    private static boolean canSpread(BlockState state, IWorldReader world, BlockPos pos)
    {
        BlockPos   topPos   = pos.up();
        BlockState topState = world.getBlockState(topPos);
        if (topState.getBlock() == Blocks.SNOW && topState.get(SnowBlock.LAYERS) == 1)
        {
            return true;
        }
        if (topState.getBlock() instanceof TerraBlockSnowLayer && topState.get(TerraBlockSnowLayer.LAYERS) < 8)
        {
            return true;
        }
        else
        {
            int i = LightEngine.func_215613_a(world, state, pos, topState, topPos, Direction.UP, topState.getOpacity(world, topPos));
            return i < world.getMaxLightLevel();
        }
    }

    private static boolean noWater(BlockState state, IWorldReader world, BlockPos pos)
    {
        BlockPos blockpos = pos.up();
        return canSpread(state, world, pos) && !world.getFluidState(blockpos).isTagged(FluidTags.WATER);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        if (!worldIn.isRemote)
        {
            if (!worldIn.isAreaLoaded(pos, 3))
            {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            }
            if (!canSpread(state, worldIn, pos) || worldIn.getBlockState(pos.up()).getBlock() == Blocks.WATER)
            {
                worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
            }
            else
            {
                if (worldIn.getLight(pos.up()) >= 9)
                {
                    for (int i = 0; i < 4; ++i)
                    {
                        BlockPos   targetPos   = pos.add(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
                        BlockState targetState = worldIn.getBlockState(targetPos);
                        Block      targetBlock = worldIn.getBlockState(targetPos).getBlock();

                        if (targetBlock == Blocks.DIRT)
                        {
                            if (noWater(targetState, worldIn, targetPos) && canSpread(targetState, worldIn, targetPos) && !worldIn.getBlockState(targetPos.up()).isOpaqueCube(worldIn, targetPos))
                            {
                                worldIn.setBlockState(targetPos, this.getDefaultState());
                            }
                        }
                        if (checkTransformable(targetBlock))
                        {
                            if (TerraReforged.debugSpreading)
                            {
                                if (targetBlock instanceof StairsBlock)
                                {
                                    worldIn.setBlockState(targetPos, transformedState(biome, targetBlock).getDefaultState().with(StairsBlock.FACING, worldIn.getBlockState(targetPos).get(StairsBlock.FACING)).with(StairsBlock.HALF, worldIn.getBlockState(targetPos).get(StairsBlock.HALF)).with(StairsBlock.SHAPE, worldIn.getBlockState(targetPos).get(StairsBlock.SHAPE)).with(StairsBlock.WATERLOGGED, worldIn.getBlockState(targetPos).get(StairsBlock.WATERLOGGED)));
                                    return;
                                }
                                if (targetBlock instanceof SlabBlock)
                                {
                                    worldIn.setBlockState(targetPos, transformedState(biome, targetBlock).getDefaultState().with(SlabBlock.TYPE, worldIn.getBlockState(targetPos).get(SlabBlock.TYPE)).with(SlabBlock.WATERLOGGED, worldIn.getBlockState(targetPos).get(SlabBlock.WATERLOGGED)));
                                    return;
                                }

                                worldIn.setBlockState(targetPos, transformedState(biome, targetBlock).getDefaultState());
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        if (facing != Direction.UP)
        {
            return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
        else
        {
            Block block = facingState.getBlock();
            return stateIn.with(SNOWY, block == Blocks.SNOW_BLOCK || block == Blocks.SNOW || block == TerraBlockRegistry.SNOW_CORRUPT_LAYER.get() || block == TerraBlockRegistry.SNOW_CRIMSON_LAYER.get() || block == TerraBlockRegistry.SNOW_HALLOWED_LAYER.get());
        }
    }

    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        Block block = context.getWorld().getBlockState(context.getPos().up()).getBlock();
        return this.getDefaultState().with(SNOWY, block == Blocks.SNOW_BLOCK || block == Blocks.SNOW || block == TerraBlockRegistry.SNOW_CORRUPT_LAYER.get() || block == TerraBlockRegistry.SNOW_CRIMSON_LAYER.get() || block == TerraBlockRegistry.SNOW_HALLOWED_LAYER.get());
    }

    public Block transformedState(EnumBiomeType type, Block target)
    {
        Block result = target;
        for (EnumBiomeBlockType block : EnumBiomeBlockType.values())
        {
            if (block.pure == target)
            {
                result = block.getBiomeBlock(type);
            }
        }
        return result;
    }

    public boolean checkTransformable(Block target)
    {
        boolean check = false;
        for (EnumBiomeBlockType block : EnumBiomeBlockType.values())
        {
            if (block.pure == target)
            {
                check = true;
            }
        }
        return check;
    }

    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
    {
        return worldIn.getBlockState(pos.up()).isAir();
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        return false;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state)
    {
        BlockPos   blockpos   = pos.up();
        BlockState blockstate = Blocks.GRASS.getDefaultState();

        label48:
        for (int i = 0; i < 128; ++i)
        {
            BlockPos blockpos1 = blockpos;

            for (int j = 0; j < i / 16; ++j)
            {
                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
                if (!worldIn.getBlockState(blockpos1.down()).matchesBlock(this) || worldIn.getBlockState(blockpos1).hasOpaqueCollisionShape(worldIn, blockpos1))
                {
                    continue label48;
                }
            }

            BlockState blockstate2 = worldIn.getBlockState(blockpos1);
            if (blockstate2.matchesBlock(blockstate.getBlock()) && rand.nextInt(10) == 0)
            {
                ((IGrowable) blockstate.getBlock()).grow(worldIn, rand, blockpos1, blockstate2);
            }

            if (blockstate2.isAir())
            {
                BlockState blockstate1;
                if (rand.nextInt(8) == 0)
                {
                    List<ConfiguredFeature<?, ?>> list = worldIn.getBiome(blockpos1).getGenerationSettings().getFlowerFeatures();
                    if (list.isEmpty())
                    {
                        continue;
                    }

                    ConfiguredFeature<?, ?> configuredfeature = list.get(0);
                    FlowersFeature          flowersfeature    = (FlowersFeature) configuredfeature.feature;
                    blockstate1 = flowersfeature.getFlowerToPlace(rand, blockpos1, configuredfeature.getConfig());
                }
                else
                {
                    blockstate1 = blockstate;
                }

                if (blockstate1.isValidPosition(worldIn, blockpos1))
                {
                    worldIn.setBlockState(blockpos1, blockstate1, 3);
                }
            }
        }

    }
}


