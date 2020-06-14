package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.biomes.builders.TerraMudJungleBuilder;
import com.anbaric.terra_reforged.biomes.builders.TerraIceBiomeBuilder;
import com.anbaric.terra_reforged.features.TerraBiomeFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;


public class TerraSurfaceBuilderRegistry extends SurfaceBuilder
{
    public static final SurfaceBuilderConfig MUD_JUNGLE_CONFIG = new SurfaceBuilderConfig(TerraBiomeFeatures.GRASS_JUNGLE, TerraBiomeFeatures.SOIL_MUD, TerraBiomeFeatures.SOIL_MUD);
    public static final SurfaceBuilderConfig SNOW_PURE_CONFIG = new SurfaceBuilderConfig(TerraBiomeFeatures.SNOW, TerraBiomeFeatures.SNOW, TerraBiomeFeatures.SNOW);
    public static final SurfaceBuilderConfig SNOW_CORRUPT_CONFIG = new SurfaceBuilderConfig(TerraBiomeFeatures.CORRUPT_SNOW, TerraBiomeFeatures.CORRUPT_SNOW, TerraBiomeFeatures.CORRUPT_SNOW);
    public static final SurfaceBuilderConfig SNOW_CRIMSON_CONFIG = new SurfaceBuilderConfig(TerraBiomeFeatures.CRIMSON_SNOW, TerraBiomeFeatures.CRIMSON_SNOW, TerraBiomeFeatures.CRIMSON_SNOW);
    public static final SurfaceBuilderConfig SNOW_HALLOWED_CONFIG = new SurfaceBuilderConfig(TerraBiomeFeatures.HALLOWED_SNOW, TerraBiomeFeatures.HALLOWED_SNOW, TerraBiomeFeatures.HALLOWED_SNOW);

    public static final SurfaceBuilder<SurfaceBuilderConfig> MUD_JUNGLE = register("mud_jungle", new TerraMudJungleBuilder(SurfaceBuilderConfig::deserialize));
    public static final SurfaceBuilder<SurfaceBuilderConfig> SNOW_PURE = register("snow_pure", new TerraIceBiomeBuilder(SurfaceBuilderConfig::deserialize, TerraBiomeFeatures.SNOW, TerraBiomeFeatures.ICE, TerraBiomeFeatures.PACKED_ICE));
    public static final SurfaceBuilder<SurfaceBuilderConfig> SNOW_CORRUPT = register("snow_corrupt", new TerraIceBiomeBuilder(SurfaceBuilderConfig::deserialize, TerraBiomeFeatures.CORRUPT_SNOW, TerraBiomeFeatures.CORRUPT_ICE, TerraBiomeFeatures.CORRUPT_PACKED_ICE));
    public static final SurfaceBuilder<SurfaceBuilderConfig> SNOW_CRIMSON = register("snow_crimson", new TerraIceBiomeBuilder(SurfaceBuilderConfig::deserialize, TerraBiomeFeatures.CRIMSON_SNOW, TerraBiomeFeatures.CRIMSON_ICE, TerraBiomeFeatures.CRIMSON_PACKED_ICE));
    public static final SurfaceBuilder<SurfaceBuilderConfig> SNOW_HALLOWED = register("snow_hallowed", new TerraIceBiomeBuilder(SurfaceBuilderConfig::deserialize, TerraBiomeFeatures.HALLOWED_SNOW, TerraBiomeFeatures.HALLOWED_ICE, TerraBiomeFeatures.HALLOWED_PACKED_ICE));

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
