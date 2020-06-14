package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraParticleRegistry;
import net.minecraft.block.*;
import net.minecraft.particles.ParticleTypes;
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
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import java.util.List;
import java.util.Random;

public class TerraBlockMudGrass extends Block implements IGrowable
{
    public TerraBlockMudGrass(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable)
    {
        PlantType type = plantable.getPlantType(world, pos);
        return type == PlantType.Plains;
    }

    private static boolean canSpread(BlockState state, IWorldReader world, BlockPos pos)
    {
        BlockPos   topPos   = pos.up();
        BlockState topState = world.getBlockState(topPos);
        if (topState.getBlock() == Blocks.SNOW && topState.get(SnowBlock.LAYERS) == 1)
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

    private static boolean noLava(BlockState state, IWorldReader world, BlockPos pos)
    {
        BlockPos blockpos = pos.up();
        return canSpread(state, world, pos) && !world.getFluidState(blockpos).isTagged(FluidTags.LAVA);
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
                worldIn.setBlockState(pos, TerraBlockRegistry.SOIL_MUD.get().getDefaultState());
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

                        if (targetBlock == TerraBlockRegistry.SOIL_MUD.get())
                        {
                            if (noWater(targetState, worldIn, targetPos) && noLava(targetState, worldIn, targetPos) && canSpread(targetState, worldIn, targetPos) && !worldIn.getBlockState(targetPos.up()).isOpaqueCube(worldIn, targetPos))
                            {
                                worldIn.setBlockState(targetPos, this.getDefaultState());
                            }
                        }
                    }
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        super.animateTick(stateIn, worldIn, pos, rand);
        if (rand.nextInt(5) == 0 && this == TerraBlockRegistry.GRASS_MUSHROOM.get())
        {
            worldIn.addParticle(TerraParticleRegistry.SPORE_MUSHROOM.get(), (double) pos.getX() + (double) rand.nextFloat(), (double) pos.getY() + 1.1D, (double) pos.getZ() + (double) rand.nextFloat(), 0.0D, 0.0D, 0.0D);
        }
    }

    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
    {
        return worldIn.getBlockState(pos.up()).isAir();
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        return true;
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


