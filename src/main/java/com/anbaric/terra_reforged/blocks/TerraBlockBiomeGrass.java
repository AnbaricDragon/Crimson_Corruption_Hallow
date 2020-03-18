package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.util.handlers.EnumHandler.EnumBiomeBlockType;
import com.anbaric.terra_reforged.util.handlers.EnumHandler.EnumBiomeType;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.fluid.IFluidState;
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
                BlockState  blockstate  = world.getBlockState(pos.offset(direction));
                IFluidState ifluidstate = world.getFluidState(pos.offset(direction));
                if (ifluidstate.isTagged(FluidTags.WATER) || blockstate.getBlock() == Blocks.FROSTED_ICE)
                {
                    reed = true;
                }
            }
        }
        return plantable.getPlantType(world, pos) == PlantType.Plains || reed;
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
            if (!canSpread(state, worldIn, pos))
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
//                        if (targetBlock == Blocks.TALL_GRASS)
//                        {
//                            TerraBlockDoubleGrass doublePlant = (TerraBlockDoubleGrass) (this == TerraBlockRegistry.GRASS_CORRUPT.get() ? TerraBlocks.TALLGRASSDOUBLE_CORRUPT : this == TerraBlocks.GRASS_CRIMSON ? TerraBlocks.TALLGRASSDOUBLE_CRIMSON : TerraBlocks.TALLGRASSDOUBLE_HALLOWED);
//                            if (doublePlant.getDefaultState().isValidPosition(worldIn, targetPos))
//                            {
//                                System.out.println("Attempting to spread to" + targetBlock);
//                                doublePlant.placeAt(worldIn, targetPos, 2);
//                            }
//                        }
                        if (checkTransformable(targetBlock))
                        {
                            if (TerraReforged.debugSpreading)
                            {
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
            return stateIn.with(SNOWY, Boolean.valueOf(block == Blocks.SNOW_BLOCK || block == Blocks.SNOW/* || block == TerraBlockRegistry.SNOW_CORRUPT_LAYER.get() || block == TerraBlockRegistry.SNOW_CRIMSON_LAYER.get() || block == TerraBlockRegistry.SNOW_HALLOWED_LAYER.get()*/));
        }
    }

    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        Block block = context.getWorld().getBlockState(context.getPos().up()).getBlock();
        return this.getDefaultState().with(SNOWY, Boolean.valueOf(block == Blocks.SNOW_BLOCK || block == Blocks.SNOW/* || block == TerraBlockRegistry.SNOW_CORRUPT_LAYER.get() || block == TerraBlockRegistry.SNOW_CRIMSON_LAYER.get() || block == TerraBlockRegistry.SNOW_HALLOWED_LAYER.get()*/));
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
    public void grow(ServerWorld p_225535_1_, Random p_225535_2_, BlockPos p_225535_3_, BlockState p_225535_4_)
    {
        BlockPos   blockpos   = p_225535_3_.up();
        BlockState blockstate = this.getDefaultState();

        label48:
        for (int i = 0; i < 128; ++i)
        {
            BlockPos blockpos1 = blockpos;

            for (int j = 0; j < i / 16; ++j)
            {
                blockpos1 = blockpos1.add(p_225535_2_.nextInt(3) - 1, (p_225535_2_.nextInt(3) - 1) * p_225535_2_.nextInt(3) / 2, p_225535_2_.nextInt(3) - 1);
                if (p_225535_1_.getBlockState(blockpos1.down()).getBlock() != this || p_225535_1_.getBlockState(blockpos1).isCollisionShapeOpaque(p_225535_1_, blockpos1))
                {
                    continue label48;
                }
            }

            BlockState blockstate2 = p_225535_1_.getBlockState(blockpos1);
            if (blockstate2.getBlock() == blockstate.getBlock() && p_225535_2_.nextInt(10) == 0)
            {
                ((IGrowable) blockstate.getBlock()).grow(p_225535_1_, p_225535_2_, blockpos1, blockstate2);
            }

            if (blockstate2.isAir())
            {
                BlockState blockstate1;
                if (p_225535_2_.nextInt(8) == 0)
                {
                    List<ConfiguredFeature<?, ?>> list = p_225535_1_.getBiome(blockpos1).getFlowers();
                    if (list.isEmpty())
                    {
                        continue;
                    }

                    ConfiguredFeature<?, ?> configuredfeature = ((DecoratedFeatureConfig) ((ConfiguredFeature) list.get(0)).config).feature;
                    blockstate1 = ((FlowersFeature) configuredfeature.feature).getFlowerToPlace(p_225535_2_, blockpos1, configuredfeature.config);
                }
                else
                {
                    blockstate1 = blockstate;
                }

                if (blockstate1.isValidPosition(p_225535_1_, blockpos1))
                {
                    p_225535_1_.setBlockState(blockpos1, blockstate1, 3);
                }
            }
        }
    }
}


