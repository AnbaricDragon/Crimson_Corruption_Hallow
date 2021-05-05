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
        Block   plant = plantable.getPlant(world, pos.above()).getBlock();
        if (plant == Blocks.SUGAR_CANE || plant instanceof TerraBlockReeds)
        {
            for (Direction direction : Direction.Plane.HORIZONTAL)
            {
                BlockState blockstate  = world.getBlockState(pos.relative(direction));
                FluidState ifluidstate = world.getFluidState(pos.relative(direction));
                if (ifluidstate.is(FluidTags.WATER) || blockstate.getBlock() == Blocks.FROSTED_ICE)
                {
                    reed = true;
                }
            }
        }
        return plantable.getPlantType(world, pos) == PlantType.PLAINS || reed;
    }

    private static boolean canSpread(BlockState state, IWorldReader world, BlockPos pos)
    {
        BlockPos   topPos   = pos.above();
        BlockState topState = world.getBlockState(topPos);
        if (topState.getBlock() == Blocks.SNOW && topState.getValue(SnowBlock.LAYERS) == 1)
        {
            return true;
        }
        if (topState.getBlock() instanceof TerraBlockSnowLayer && topState.getValue(TerraBlockSnowLayer.LAYERS) < 8)
        {
            return true;
        }
        else
        {
            int i = LightEngine.getLightBlockInto(world, state, pos, topState, topPos, Direction.UP, topState.getLightBlock(world, topPos));
            return i < world.getMaxLightLevel();
        }
    }

    private static boolean noWater(BlockState state, IWorldReader world, BlockPos pos)
    {
        BlockPos blockpos = pos.above();
        return canSpread(state, world, pos) && !world.getFluidState(blockpos).is(FluidTags.WATER);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        if (!worldIn.isClientSide)
        {
            if (!worldIn.isAreaLoaded(pos, 3))
            {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            }
            if (!canSpread(state, worldIn, pos) || worldIn.getBlockState(pos.above()).getBlock() == Blocks.WATER)
            {
                worldIn.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
            }
            else
            {
                if (worldIn.getMaxLocalRawBrightness(pos.above()) >= 9)
                {
                    for (int i = 0; i < 4; ++i)
                    {
                        BlockPos   targetPos   = pos.offset(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
                        BlockState targetState = worldIn.getBlockState(targetPos);
                        Block      targetBlock = worldIn.getBlockState(targetPos).getBlock();

                        if (targetBlock == Blocks.DIRT)
                        {
                            if (noWater(targetState, worldIn, targetPos) && canSpread(targetState, worldIn, targetPos) && !worldIn.getBlockState(targetPos.above()).isSolidRender(worldIn, targetPos))
                            {
                                worldIn.setBlockAndUpdate(targetPos, this.defaultBlockState());
                            }
                        }
                        if (checkTransformable(targetBlock))
                        {
                            if (TerraReforged.debugSpreading)
                            {
                                if (targetBlock instanceof StairsBlock)
                                {
                                    worldIn.setBlockAndUpdate(targetPos, transformedState(biome, targetBlock).defaultBlockState().setValue(StairsBlock.FACING, worldIn.getBlockState(targetPos).getValue(StairsBlock.FACING)).setValue(StairsBlock.HALF, worldIn.getBlockState(targetPos).getValue(StairsBlock.HALF)).setValue(StairsBlock.SHAPE, worldIn.getBlockState(targetPos).getValue(StairsBlock.SHAPE)).setValue(StairsBlock.WATERLOGGED, worldIn.getBlockState(targetPos).getValue(StairsBlock.WATERLOGGED)));
                                    return;
                                }
                                if (targetBlock instanceof SlabBlock)
                                {
                                    worldIn.setBlockAndUpdate(targetPos, transformedState(biome, targetBlock).defaultBlockState().setValue(SlabBlock.TYPE, worldIn.getBlockState(targetPos).getValue(SlabBlock.TYPE)).setValue(SlabBlock.WATERLOGGED, worldIn.getBlockState(targetPos).getValue(SlabBlock.WATERLOGGED)));
                                    return;
                                }

                                worldIn.setBlockAndUpdate(targetPos, transformedState(biome, targetBlock).defaultBlockState());
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        if (facing != Direction.UP)
        {
            return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
        else
        {
            Block block = facingState.getBlock();
            return stateIn.setValue(SNOWY, block == Blocks.SNOW_BLOCK || block == Blocks.SNOW || block == TerraBlockRegistry.SNOW_CORRUPT_LAYER.get() || block == TerraBlockRegistry.SNOW_CRIMSON_LAYER.get() || block == TerraBlockRegistry.SNOW_HALLOWED_LAYER.get());
        }
    }

    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        Block block = context.getLevel().getBlockState(context.getClickedPos().above()).getBlock();
        return this.defaultBlockState().setValue(SNOWY, block == Blocks.SNOW_BLOCK || block == Blocks.SNOW || block == TerraBlockRegistry.SNOW_CORRUPT_LAYER.get() || block == TerraBlockRegistry.SNOW_CRIMSON_LAYER.get() || block == TerraBlockRegistry.SNOW_HALLOWED_LAYER.get());
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

    public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
    {
        return worldIn.getBlockState(pos.above()).isAir();
    }

    public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        return false;
    }

    @Override
    public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state)
    {
        BlockPos   blockpos   = pos.above();
        BlockState blockstate = Blocks.GRASS.defaultBlockState();

        label48:
        for (int i = 0; i < 128; ++i)
        {
            BlockPos blockpos1 = blockpos;

            for (int j = 0; j < i / 16; ++j)
            {
                blockpos1 = blockpos1.offset(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
                if (!worldIn.getBlockState(blockpos1.below()).is(this) || worldIn.getBlockState(blockpos1).isCollisionShapeFullBlock(worldIn, blockpos1))
                {
                    continue label48;
                }
            }

            BlockState blockstate2 = worldIn.getBlockState(blockpos1);
            if (blockstate2.is(blockstate.getBlock()) && rand.nextInt(10) == 0)
            {
                ((IGrowable) blockstate.getBlock()).performBonemeal(worldIn, rand, blockpos1, blockstate2);
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
                    blockstate1 = flowersfeature.getRandomFlower(rand, blockpos1, configuredfeature.config());
                }
                else
                {
                    blockstate1 = blockstate;
                }

                if (blockstate1.canSurvive(worldIn, blockpos1))
                {
                    worldIn.setBlock(blockpos1, blockstate1, 3);
                }
            }
        }

    }
}


