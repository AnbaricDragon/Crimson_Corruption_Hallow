package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.biomes.builders.*;
import com.anbaric.terra_reforged.features.TerraBiomeFeatures;
import net.minecraft.block.BlockState;
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
    public static final SurfaceBuilderConfig SAND_PURE_CONFIG = new SurfaceBuilderConfig(TerraBiomeFeatures.SAND, TerraBiomeFeatures.HARD_SAND, TerraBiomeFeatures.SANDSTONE);

    public static final SurfaceBuilderConfig GRASS_CORRUPT_CONFIG = new SurfaceBuilderConfig(TerraBiomeFeatures.GRASS_CORRUPT, DIRT, GRAVEL);
    public static final SurfaceBuilderConfig STONE_CORRUPT_CONFIG = new SurfaceBuilderConfig(TerraBiomeFeatures.CORRUPT_STONE, TerraBiomeFeatures.CORRUPT_STONE, GRAVEL);
    public static final SurfaceBuilderConfig SNOW_CORRUPT_CONFIG = new SurfaceBuilderConfig(TerraBiomeFeatures.CORRUPT_SNOW, TerraBiomeFeatures.CORRUPT_SNOW, TerraBiomeFeatures.CORRUPT_SNOW);
    public static final SurfaceBuilderConfig SAND_CORRUPT_CONFIG = new SurfaceBuilderConfig(TerraBiomeFeatures.CORRUPT_SAND, TerraBiomeFeatures.CORRUPT_HARDSAND, TerraBiomeFeatures.CORRUPT_SANDSTONE);

    public static final SurfaceBuilderConfig GRASS_CRIMSON_CONFIG = new SurfaceBuilderConfig(TerraBiomeFeatures.GRASS_CRIMSON, DIRT, GRAVEL);
    public static final SurfaceBuilderConfig STONE_CRIMSON_CONFIG = new SurfaceBuilderConfig(TerraBiomeFeatures.CRIMSON_STONE, TerraBiomeFeatures.CRIMSON_STONE, GRAVEL);
    public static final SurfaceBuilderConfig SNOW_CRIMSON_CONFIG = new SurfaceBuilderConfig(TerraBiomeFeatures.CRIMSON_SNOW, TerraBiomeFeatures.CRIMSON_SNOW, TerraBiomeFeatures.CRIMSON_SNOW);
    public static final SurfaceBuilderConfig SAND_CRIMSON_CONFIG = new SurfaceBuilderConfig(TerraBiomeFeatures.CRIMSON_SAND, TerraBiomeFeatures.CRIMSON_HARDSAND, TerraBiomeFeatures.CRIMSON_SANDSTONE);

    public static final SurfaceBuilderConfig GRASS_HALLOWED_CONFIG = new SurfaceBuilderConfig(TerraBiomeFeatures.GRASS_HALLOWED, DIRT, GRAVEL);
    public static final SurfaceBuilderConfig STONE_HALLOWED_CONFIG = new SurfaceBuilderConfig(TerraBiomeFeatures.HALLOWED_STONE, TerraBiomeFeatures.HALLOWED_STONE, GRAVEL);
    public static final SurfaceBuilderConfig SNOW_HALLOWED_CONFIG = new SurfaceBuilderConfig(TerraBiomeFeatures.HALLOWED_SNOW, TerraBiomeFeatures.HALLOWED_SNOW, TerraBiomeFeatures.HALLOWED_SNOW);
    public static final SurfaceBuilderConfig SAND_HALLOWED_CONFIG = new SurfaceBuilderConfig(TerraBiomeFeatures.HALLOWED_SAND, TerraBiomeFeatures.HALLOWED_HARDSAND, TerraBiomeFeatures.HALLOWED_SANDSTONE);

    public static final SurfaceBuilder<SurfaceBuilderConfig> MUD_JUNGLE = register("mud_jungle", new TerraMudJungleBuilder(SurfaceBuilderConfig::deserialize));

    public static final SurfaceBuilder<SurfaceBuilderConfig> SNOW_PURE = register("snow_pure", new TerraIceBiomeBuilder(SurfaceBuilderConfig::deserialize, TerraBiomeFeatures.SNOW, TerraBiomeFeatures.ICE, TerraBiomeFeatures.PACKED_ICE));
    public static final SurfaceBuilder<SurfaceBuilderConfig> DESERT_PURE = register("sand_pure", new TerraDesertBiomeBuilder(SurfaceBuilderConfig::deserialize));

    public static final SurfaceBuilder<SurfaceBuilderConfig> BASE_CORRUPT = register("base_corrupt", new TerraBiomeBuilder(SurfaceBuilderConfig::deserialize, TerraBiomeFeatures.CORRUPT_STONE));
    public static final SurfaceBuilder<SurfaceBuilderConfig> MOUNTAIN_CORRUPT = register("mountain_corrupt", new TerraMountainBiomeBuilder(SurfaceBuilderConfig::deserialize, BASE_CORRUPT, STONE_CORRUPT_CONFIG, GRASS_CORRUPT_CONFIG));
    public static final SurfaceBuilder<SurfaceBuilderConfig> SWAMP_CORRUPT = register("swamp_corrupt", new TerraSwampBiomeBuilder(SurfaceBuilderConfig::deserialize, TerraBiomeFeatures.CORRUPT_STONE));
    public static final SurfaceBuilder<SurfaceBuilderConfig> SNOW_CORRUPT = register("snow_corrupt", new TerraIceBiomeBuilder(SurfaceBuilderConfig::deserialize, TerraBiomeFeatures.CORRUPT_SNOW, TerraBiomeFeatures.CORRUPT_ICE, TerraBiomeFeatures.CORRUPT_PACKED_ICE));
    public static final SurfaceBuilder<SurfaceBuilderConfig> DESERT_CORRUPT = register("sand_corrupt", new TerraDesertBiomeBuilder(SurfaceBuilderConfig::deserialize));

    public static final SurfaceBuilder<SurfaceBuilderConfig> BASE_CRIMSON = register("base_crimson", new TerraBiomeBuilder(SurfaceBuilderConfig::deserialize, TerraBiomeFeatures.CRIMSON_STONE));
    public static final SurfaceBuilder<SurfaceBuilderConfig> MOUNTAIN_CRIMSON = register("mountain_crimson", new TerraMountainBiomeBuilder(SurfaceBuilderConfig::deserialize, BASE_CRIMSON, STONE_CRIMSON_CONFIG, GRASS_CRIMSON_CONFIG));
    public static final SurfaceBuilder<SurfaceBuilderConfig> SWAMP_CRIMSON = register("swamp_crimson", new TerraSwampBiomeBuilder(SurfaceBuilderConfig::deserialize, TerraBiomeFeatures.CRIMSON_STONE));
    public static final SurfaceBuilder<SurfaceBuilderConfig> SNOW_CRIMSON = register("snow_crimson", new TerraIceBiomeBuilder(SurfaceBuilderConfig::deserialize, TerraBiomeFeatures.CRIMSON_SNOW, TerraBiomeFeatures.CRIMSON_ICE, TerraBiomeFeatures.CRIMSON_PACKED_ICE));
    public static final SurfaceBuilder<SurfaceBuilderConfig> DESERT_CRIMSON = register("sand_crimson", new TerraDesertBiomeBuilder(SurfaceBuilderConfig::deserialize));

    public static final SurfaceBuilder<SurfaceBuilderConfig> BASE_HALLOWED = register("base_hallowed", new TerraBiomeBuilder(SurfaceBuilderConfig::deserialize, TerraBiomeFeatures.HALLOWED_STONE));
    public static final SurfaceBuilder<SurfaceBuilderConfig> MOUNTAIN_HALLOWED = register("mountain_hallowed", new TerraMountainBiomeBuilder(SurfaceBuilderConfig::deserialize, BASE_HALLOWED, STONE_HALLOWED_CONFIG, GRASS_HALLOWED_CONFIG));
    public static final SurfaceBuilder<SurfaceBuilderConfig> SWAMP_HALLOWED = register("swamp_hallowed", new TerraSwampBiomeBuilder(SurfaceBuilderConfig::deserialize, TerraBiomeFeatures.HALLOWED_STONE));
    public static final SurfaceBuilder<SurfaceBuilderConfig> SNOW_HALLOWED = register("snow_hallowed", new TerraIceBiomeBuilder(SurfaceBuilderConfig::deserialize, TerraBiomeFeatures.HALLOWED_SNOW, TerraBiomeFeatures.HALLOWED_ICE, TerraBiomeFeatures.HALLOWED_PACKED_ICE));
    public static final SurfaceBuilder<SurfaceBuilderConfig> DESERT_HALLOWED = register("sand_hallowed", new TerraDesertBiomeBuilder(SurfaceBuilderConfig::deserialize));

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