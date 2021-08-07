package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraParticleRegistry;
import net.minecraft.block.*;
import net.minecraft.client.particle.Particle;
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
import org.apache.commons.lang3.tuple.Triple;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

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
        return this == TerraBlockRegistry.GRASS_MUSHROOM.get() ? type == TerraReforged.MUSHROOM || type == PlantType.PLAINS : type == PlantType.PLAINS;
    }

    private static boolean canSpread(BlockState state, IWorldReader world, BlockPos pos)
    {
        BlockPos   topPos   = pos.above();
        BlockState topState = world.getBlockState(topPos);
        if (topState.getBlock() == Blocks.SNOW && topState.getValue(SnowBlock.LAYERS) == 1)
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

    private static boolean noLava(BlockState state, IWorldReader world, BlockPos pos)
    {
        BlockPos blockpos = pos.above();
        return canSpread(state, world, pos) && !world.getFluidState(blockpos).is(FluidTags.LAVA);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        int maxShrooms = 10;

        if (!worldIn.isClientSide)
        {
            if (!worldIn.isAreaLoaded(pos, 3))
            {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            }
            if (!canSpread(state, worldIn, pos))
            {
                worldIn.setBlockAndUpdate(pos, TerraBlockRegistry.SOIL_MUD.get().defaultBlockState());
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

                        if (targetBlock == TerraBlockRegistry.SOIL_MUD.get())
                        {
                            if (noWater(targetState, worldIn, targetPos) && noLava(targetState, worldIn, targetPos) && canSpread(targetState, worldIn, targetPos) && !worldIn.getBlockState(targetPos.above()).isSolidRender(worldIn, targetPos))
                            {
                                worldIn.setBlockAndUpdate(targetPos, this.defaultBlockState());
                            }
                        }
                    }
                }
            }
            if (random.nextInt(250) == 0 && this == TerraBlockRegistry.GRASS_MUSHROOM.get() && worldIn.getBlockState(pos.above()).isAir(worldIn, pos))
            {
                Iterator cubicRange = BlockPos.betweenClosed(pos.offset(-8, -1, -8), pos.offset(8, 1, 8)).iterator();

                while (cubicRange.hasNext())
                {
                    BlockPos blockpos = (BlockPos) cubicRange.next();
                    if (worldIn.getBlockState(blockpos).getBlock() == TerraBlockRegistry.PLANT_MUSHROOM_GLOWING.get())
                    {
                        --maxShrooms;
                        if (maxShrooms <= 0)
                        {
                            return;
                        }
                    }
                }
                if (maxShrooms > 0)
                {
                    worldIn.setBlockAndUpdate(pos.above(), TerraBlockRegistry.PLANT_MUSHROOM_GLOWING.get().defaultBlockState());
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
            //TODO
            worldIn.addParticle(TerraParticleRegistry.MUSHROOM_SPORE_GLOWING.get(), (double) pos.getX() + (double) rand.nextFloat(), (double) pos.getY() + 1.1D, (double) pos.getZ() + (double) rand.nextFloat(), 0.0D, 0.0D, 0.0D);
        }
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
    public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) { }
}
