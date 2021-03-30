package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class TerraBiomeRegistry
{
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Reference.MODID);

    public static final RegistryObject<Biome> CORRUPT_PLAINS = BIOMES.register("corrupt_plains", BiomeMaker::makeVoidBiome);
    public static final RegistryObject<Biome> CORRUPT_DESERT = BIOMES.register("corrupt_desert", BiomeMaker::makeVoidBiome);
    public static final RegistryObject<Biome> CORRUPT_FOREST = BIOMES.register("corrupt_forest", BiomeMaker::makeVoidBiome);
    public static final RegistryObject<Biome> CORRUPT_MOUNTAIN = BIOMES.register("corrupt_mountain", BiomeMaker::makeVoidBiome);

    public static final RegistryObject<Biome> CRIMSON_PLAINS = BIOMES.register("crimson_plains", BiomeMaker::makeVoidBiome);
    public static final RegistryObject<Biome> CRIMSON_DESERT = BIOMES.register("crimson_desert", BiomeMaker::makeVoidBiome);
    public static final RegistryObject<Biome> CRIMSON_FOREST = BIOMES.register("crimson_forest", BiomeMaker::makeVoidBiome);
    public static final RegistryObject<Biome> CRIMSON_MOUNTAIN = BIOMES.register("crimson_mountain", BiomeMaker::makeVoidBiome);

    public static final RegistryObject<Biome> HALLOWED_PLAINS = BIOMES.register("hallowed_plains", BiomeMaker::makeVoidBiome);
    public static final RegistryObject<Biome> HALLOWED_DESERT = BIOMES.register("hallowed_desert", BiomeMaker::makeVoidBiome);
    public static final RegistryObject<Biome> HALLOWED_FOREST = BIOMES.register("hallowed_forest", BiomeMaker::makeVoidBiome);
    public static final RegistryObject<Biome> HALLOWED_MOUNTAIN = BIOMES.register("hallowed_mountain", BiomeMaker::makeVoidBiome);

    public static final RegistryObject<Biome> ASHEN_WASTES = BIOMES.register("ashen_wastes", BiomeMaker::makeVoidBiome);
}