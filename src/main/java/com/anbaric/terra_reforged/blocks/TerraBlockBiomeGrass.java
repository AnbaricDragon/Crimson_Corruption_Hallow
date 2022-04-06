package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.handlers.SpreadingHandler;
import com.anbaric.terra_reforged.util.handlers.SpreadingHandler.EnumBiomeType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.lighting.LayerLightEngine;

import java.util.List;
import java.util.Random;

public class TerraBlockBiomeGrass extends SpreadingSnowyDirtBlock implements BonemealableBlock
{
    private EnumBiomeType biome;

    public TerraBlockBiomeGrass(Properties properties, EnumBiomeType biomeType)
    {
        super(properties);
        this.biome = biomeType;
    }

    public boolean isValidBonemealTarget(BlockGetter p_53692_, BlockPos p_53693_, BlockState p_53694_, boolean p_53695_) {
        return p_53692_.getBlockState(p_53693_.above()).isAir();
    }

    public boolean isBonemealSuccess(Level p_53697_, Random p_53698_, BlockPos p_53699_, BlockState p_53700_) {
        return true;
    }

    public void performBonemeal(ServerLevel world, Random random, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = Blocks.GRASS.defaultBlockState();

        label46:
        for(int i = 0; i < 128; ++i) {
            BlockPos blockpos1 = blockpos;

            for(int j = 0; j < i / 16; ++j) {
                blockpos1 = blockpos1.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (!world.getBlockState(blockpos1.below()).is(this) || world.getBlockState(blockpos1).isCollisionShapeFullBlock(world, blockpos1)) {
                    continue label46;
                }
            }

            BlockState blockstate1 = world.getBlockState(blockpos1);
            if (blockstate1.is(blockstate.getBlock()) && random.nextInt(10) == 0) {
                ((BonemealableBlock)blockstate.getBlock()).performBonemeal(world, random, blockpos1, blockstate1);
            }

            if (blockstate1.isAir()) {
                Holder<PlacedFeature> holder;
                if (random.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> list = world.getBiome(blockpos1).value().getGenerationSettings().getFlowerFeatures();
                    if (list.isEmpty()) {
                        continue;
                    }

                    holder = ((RandomPatchConfiguration)list.get(0).config()).feature();
                } else {
                    holder = VegetationPlacements.GRASS_BONEMEAL;
                }

                holder.value().place(world, world.getChunkSource().getGenerator(), random, blockpos1);
            }
        }
    }

    private static boolean canBeGrass(BlockState state, LevelReader world, BlockPos pos)
    {
        BlockPos   blockpos   = pos.above();
        BlockState blockstate = world.getBlockState(blockpos);
        if (blockstate.is(BlockTags.SNOW) && blockstate.getValue(SnowLayerBlock.LAYERS) == 1)
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

    private static boolean canPropagate(BlockState p_56828_, LevelReader p_56829_, BlockPos p_56830_)
    {
        BlockPos blockpos = p_56830_.above();
        return canBeGrass(p_56828_, p_56829_, p_56830_) && !p_56829_.getFluidState(blockpos).is(FluidTags.WATER);
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random)
    {
        if (!canBeGrass(state, world, pos))
        {
            if (!world.isAreaLoaded(pos, 1))
            {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            }
            world.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
        }
        else
        {
            if (!world.isAreaLoaded(pos, 3))
            {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            }
            if (world.getMaxLocalRawBrightness(pos.above()) >= 9)
            {
                BlockState blockstate = this.defaultBlockState(); //.setValue(SNOWY, world.getBlockState(pos.above()).getBlock() instanceof SnowLayerBlock);

                for (int i = 0; i < 4; ++i)
                {
                    BlockPos targetPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    Block targetBlock = world.getBlockState(targetPos).getBlock();

                    if (checkTransformable(targetBlock) && canPropagate(blockstate, world, targetPos))
                    {
                        if (targetBlock instanceof StairBlock)
                        {
                            world.setBlock(targetPos, transformedState(biome, targetBlock).defaultBlockState()
                                            .setValue(StairBlock.FACING, world.getBlockState(targetPos).getValue(StairBlock.FACING))
                                            .setValue(StairBlock.HALF, world.getBlockState(targetPos).getValue(StairBlock.HALF))
                                            .setValue(StairBlock.SHAPE, world.getBlockState(targetPos).getValue(StairBlock.SHAPE))
                                            .setValue(StairBlock.WATERLOGGED, world.getBlockState(targetPos).getValue(StairBlock.WATERLOGGED))
                                    , 3);
                            return;
                        }
                        if (targetBlock instanceof SlabBlock)
                        {
                            world.setBlock(targetPos, transformedState(biome, targetBlock).defaultBlockState()
                                            .setValue(SlabBlock.TYPE, world.getBlockState(targetPos).getValue(SlabBlock.TYPE))
                                            .setValue(SlabBlock.WATERLOGGED, world.getBlockState(targetPos).getValue(SlabBlock.WATERLOGGED))
                                    , 3);
                            return;
                        }
                        if (targetBlock instanceof SpreadingSnowyDirtBlock)
                        {
                            world.setBlockAndUpdate(targetPos, transformedState(biome, targetBlock).defaultBlockState().setValue(SNOWY, world.getBlockState(targetPos.above()).is(BlockTags.SNOW)));
                            return;
                        }
                        world.setBlockAndUpdate(targetPos, transformedState(biome, targetBlock).defaultBlockState());
                    }
                }
            }
        }
        if (world.getBlockState(pos.above()).is(BlockTags.SNOW) && !state.getValue(SNOWY))
        {
            state.setValue(SNOWY, true);
        }
    }

    public Block transformedState(EnumBiomeType type, Block target)
    {
        Block result = target;
        for (SpreadingHandler.EnumBiomeBlockType block : SpreadingHandler.EnumBiomeBlockType.values())
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
        for (SpreadingHandler.EnumBiomeBlockType block : SpreadingHandler.EnumBiomeBlockType.values())
        {
            if (block.pure == target)
            {
                check = true;
            }
        }
        return check;
    }
}
