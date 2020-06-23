package com.anbaric.terra_reforged.biomes.builders;

import com.anbaric.terra_reforged.util.init.TerraSurfaceBuilderRegistry;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class TerraMountainBiomeBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{
    public final SurfaceBuilderConfig HIGH_CONFIG;
    public final SurfaceBuilderConfig LOW_CONFIG;
    public final SurfaceBuilder BUILDER;

    public TerraMountainBiomeBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> config, SurfaceBuilder builder, SurfaceBuilderConfig high, SurfaceBuilderConfig low)
    {
        super(config);
        this.HIGH_CONFIG = high;
        this.LOW_CONFIG = low;
        this.BUILDER = builder;
    }

    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
    {
        if (noise > 1.0D)
        {
            BUILDER.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, HIGH_CONFIG);
        }
        else
        {
            BUILDER.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, LOW_CONFIG);
        }

    }
}
