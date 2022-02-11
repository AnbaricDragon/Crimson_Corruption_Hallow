package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.lighting.LayerLightEngine;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TerraBlockMudGrass extends SnowyDirtBlock implements BonemealableBlock
{
    public TerraBlockMudGrass(Properties properties)
    {
        super(properties);
    }

    private static boolean canBeGrass(BlockState state, LevelReader world, BlockPos pos)
    {
        BlockPos   blockpos   = pos.above();
        BlockState blockstate = world.getBlockState(blockpos);
        if (blockstate.getBlock() instanceof SnowLayerBlock && blockstate.getValue(SnowLayerBlock.LAYERS) == 1)
        {
            return true;
        }
        else if (blockstate.getFluidState().getAmount() == 8)
        {
            return false;
        }
        else
        {
            int i = LayerLightEngine.getLightBlockInto(world, state, pos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(world, blockpos));
            return i < world.getMaxLightLevel();
        }
    }

    private static boolean canPropagate(BlockState state, LevelReader world, BlockPos pos)
    {
        BlockPos blockpos = pos.above();
        return canBeGrass(state, world, pos) && !world.getFluidState(blockpos).is(FluidTags.WATER);
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random)
    {
        int maxShrooms = 10;

        if (!canBeGrass(state, world, pos))
        {
            if (!world.isAreaLoaded(pos, 1))
            {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            }
            world.setBlockAndUpdate(pos, TerraBlockRegistry.SOIL_MUD.get().defaultBlockState());
        }
        else
        {
            if (!world.isAreaLoaded(pos, 3))
            {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            }
            if (world.getMaxLocalRawBrightness(pos.above()) >= 9)
            {
                BlockState blockstate = this.defaultBlockState();

                for (int i = 0; i < 4; ++i)
                {
                    BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (world.getBlockState(blockpos).is(TerraBlockRegistry.SOIL_MUD.get()) && canPropagate(blockstate, world, blockpos))
                    {
                        world.setBlockAndUpdate(blockpos, blockstate.setValue(SNOWY, world.getBlockState(blockpos.above()).getBlock() instanceof SnowLayerBlock));
                    }
                }
            }
//            if (this == TerraBlockRegistry.GRASS_MUSHROOM.get() && random.nextFloat() < 0.075 && world.getBlockState(pos.above()).is(Blocks.AIR))
//            {
//                for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-8, -1, -8), pos.offset(8, 1, 8)))
//                {
//                    if (world.getBlockState(blockPos).getBlock() == TerraBlockRegistry.PLANT_MUSHROOM_GLOWING.get())
//                    {
//                        --maxShrooms;
//                        if (maxShrooms <= 0)
//                        {
//                            return;
//                        }
//                    }
//                }
//                if (maxShrooms > 0)
//                {
//                    world.setBlockAndUpdate(pos.above(), TerraBlockRegistry.PLANT_MUSHROOM_GLOWING.get().getDefaultState());
//                }
//            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, Random random)
    {
        super.animateTick(state, world, pos, random);
        if (random.nextFloat() < 0.2 && this == TerraBlockRegistry.GRASS_MUSHROOM.get())
        {
            world.addParticle(ParticleTypes.MYCELIUM, (double) pos.getX() + random.nextDouble(), (double) pos.getY() + 1.1D, (double) pos.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter p_50897_, BlockPos p_50898_, BlockState p_50899_, boolean p_50900_)
    {
        return p_50897_.getBlockState(p_50898_.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level p_50901_, Random p_50902_, BlockPos p_50903_, BlockState p_50904_)
    {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, Random random, BlockPos pos, BlockState state)
    {
        BlockPos   blockpos   = pos.above();
        BlockState blockstate = Blocks.GRASS.defaultBlockState();

        label46:
        for (int i = 0; i < 128; ++i)
        {
            BlockPos blockpos1 = blockpos;

            for (int j = 0; j < i / 16; ++j)
            {
                blockpos1 = blockpos1.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (!world.getBlockState(blockpos1.below()).is(this) || world.getBlockState(blockpos1).isCollisionShapeFullBlock(world, blockpos1))
                {
                    continue label46;
                }
            }

            BlockState blockstate1 = world.getBlockState(blockpos1);
            if (blockstate1.is(blockstate.getBlock()) && random.nextInt(10) == 0)
            {
                ((BonemealableBlock) blockstate.getBlock()).performBonemeal(world, random, blockpos1, blockstate1);
            }

            if (blockstate1.isAir())
            {
                PlacedFeature placedfeature;
                if (random.nextInt(8) == 0)
                {
                    List<ConfiguredFeature<?, ?>> list = world.getBiome(blockpos1).getGenerationSettings().getFlowerFeatures();
                    if (list.isEmpty())
                    {
                        continue;
                    }

                    placedfeature = ((RandomPatchConfiguration) list.get(0).config()).feature().get();
                }
                else
                {
                    placedfeature = VegetationPlacements.GRASS_BONEMEAL;
                }

                placedfeature.place(world, world.getChunkSource().getGenerator(), random, blockpos1);
            }
        }

    }
}
