package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.biomes.builders.TerraMudJungleBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;


public class TerraSurfaceBuilderRegistry extends SurfaceBuilder
{

    private static final BlockState SOIL_MUD = TerraBlockRegistry.SOIL_MUD.get().getDefaultState();
    private static final BlockState GRASS_JUNGLE = TerraBlockRegistry.GRASS_JUNGLE.get().getDefaultState();
    public static final SurfaceBuilderConfig MUD_JUNGLE_CONFIG = new SurfaceBuilderConfig(GRASS_JUNGLE, SOIL_MUD, SOIL_MUD);
    public static final SurfaceBuilder<SurfaceBuilderConfig> MUD_JUNGLE = register("mud_jungle", new TerraMudJungleBuilder(SurfaceBuilderConfig::deserialize));

    public TerraSurfaceBuilderRegistry(Function function)
    {
        super(function);
    }

    private static <C extends ISurfaceBuilderConfig, F extends SurfaceBuilder<C>> F register(String key, F builderIn)
    {
        return (F) (Registry.<SurfaceBuilder<?>>register(Registry.SURFACE_BUILDER, key, builderIn));
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, ISurfaceBuilderConfig config)
    {

    }
}
