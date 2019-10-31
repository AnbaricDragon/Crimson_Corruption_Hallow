package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.util.handlers.EnumHandler.EnumBiomeBlockType;
import com.anbaric.terra_reforged.util.handlers.EnumHandler.EnumBiomeType;
import net.minecraft.block.*;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.lighting.LightEngine;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import java.util.List;
import java.util.Random;

public class TerraBlockBiomeGrass extends Block implements IGrowable
{
    private EnumBiomeType biome;
    private PlantType plantType;

    public TerraBlockBiomeGrass(Properties properties, EnumBiomeType biome)
    {
        super(properties);
        this.biome = biome;
        this.plantType = biome.getPlantType(biome);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable)
    {
        return plantable.getPlantType(world, pos) == this.plantType || plantable.getPlantType(world, pos) == PlantType.Plains;
    }

    private static boolean canSpread(BlockState state, IWorldReader world, BlockPos pos)
    {
        BlockPos topPos = pos.up();
        BlockState topState = world.getBlockState(topPos);
        if (topState.getBlock() == Blocks.SNOW && topState.get(SnowBlock.LAYERS) == 1)
        {
            return true;
        }
        else
        {
            int i = LightEngine.func_215613_a(world, state, pos, topState, topPos, Direction.UP, topState.getOpacity(world, topPos));
            return i < world.getMaxLightLevel() && TerraReforged.debugSpreading;
        }
    }

    private static boolean noWater(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos blockpos = pos.up();
        return canSpread(state, world, pos) && !world.getFluidState(blockpos).isTagged(FluidTags.WATER);
    }

    public void tick(BlockState state, World worldIn, BlockPos pos, Random random)
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
                        BlockPos targetPos = pos.add(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
                        BlockState targetState = worldIn.getBlockState(targetPos);
                        Block targetBlock = worldIn.getBlockState(targetPos).getBlock();

                        if (checkTransformable(targetBlock) && i == 0)
                        {
                            if (targetBlock == Blocks.DIRT || targetBlock == Blocks.GRASS_BLOCK)
                            {
                                if (noWater(targetState, worldIn, targetPos) && canSpread(targetState, worldIn, targetPos) && !worldIn.getBlockState(targetPos.up()).isOpaqueCube(worldIn, targetPos))
                                {
                                    worldIn.setBlockState(targetPos, transformedState(biome, targetBlock).getDefaultState());
                                }
                            }
                            else
                            {
                                worldIn.setBlockState(targetPos, transformedState(biome, targetBlock).getDefaultState());
                            }
                        }
                    }
                }
            }
        }
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
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        BlockPos topPos = pos.up();
        BlockState grassState = TerraBlocks.GRASS_CORRUPT.getDefaultState();

        for (int i = 0; i < 128; ++i)
        {
            BlockPos targetPos = topPos;
            int j = 0;

            while (true)
            {
                if (j >= i / 16)
                {
                    BlockState targetState = worldIn.getBlockState(targetPos);
                    if (targetState.getBlock() == grassState.getBlock() && rand.nextInt(10) == 0)
                    {
                        ((IGrowable) grassState.getBlock()).grow(worldIn, rand, targetPos, targetState);
                    }

                    if (!targetState.isAir())
                    {
                        break;
                    }

                    BlockState flowerState;
                    if (rand.nextInt(8) == 0)
                    {
                        List<ConfiguredFeature<?>> list = worldIn.getBiome(targetPos).getFlowers();
                        if (list.isEmpty())
                        {
                            break;
                        }

                        flowerState = ((FlowersFeature) ((DecoratedFeatureConfig) (list.get(0)).config).feature.feature).getRandomFlower(rand, targetPos);
                    }
                    else
                    {
                        flowerState = grassState;
                    }

                    if (flowerState.isValidPosition(worldIn, targetPos))
                    {
                        worldIn.setBlockState(targetPos, flowerState, 3);
                    }
                    break;
                }

                targetPos = targetPos.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
                if (worldIn.getBlockState(targetPos.down()).getBlock() != this || worldIn.getBlockState(targetPos).func_224756_o(worldIn, targetPos))
                {
                    break;
                }
                ++j;
            }
        }
    }
}


