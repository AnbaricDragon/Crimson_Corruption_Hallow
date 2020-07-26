package com.anbaric.terra_reforged.biomes.builders;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraSurfaceBuilderRegistry;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.OctavesNoiseGenerator;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class TerraMudJungleBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{

    private static final BlockState AIR = Blocks.AIR.getDefaultState();
    private static final BlockState SOIL_MUD = TerraBlockRegistry.SOIL_MUD.get().getDefaultState();
    private static final BlockState GRASS_JUNGLE = TerraBlockRegistry.GRASS_JUNGLE.get().getDefaultState();

    public TerraMudJungleBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> function)
    {
        super(function);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
    {
        int              chunkPosX = x & 15;
        int              chunkPosZ = z & 15;
        BlockPos.Mutable targetPos = new BlockPos.Mutable();

        for (int iter = startHeight - 1; iter >= 0; --iter)
        {
            targetPos.setPos(chunkPosX, iter, chunkPosZ);
            BlockState targetState = chunkIn.getBlockState(targetPos);

            if (targetState.getBlock() == defaultBlock.getBlock())
            {
                if (iter == startHeight - 1)
                {
                    chunkIn.setBlockState(targetPos, GRASS_JUNGLE, false);
                }
                else
                {
                    if (!chunkIn.getBlockState(targetPos).isAir())
                    {
                        chunkIn.setBlockState(targetPos, SOIL_MUD, false);
                    }
                }
            }
        }
    }
}
