package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public class BiomeHandler
{
    public static RegistryKey<Biome> MUD_JUNGLE = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(Reference.MODID, "mud_jungle"));

    public static RegistryKey<Biome> CORRUPT_PLAINS = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(Reference.MODID,"corrupt_plains"));
    public static RegistryKey<Biome> CORRUPT_DESERT = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(Reference.MODID,"corrupt_desert"));
    public static RegistryKey<Biome> CORRUPT_FOREST = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(Reference.MODID,"corrupt_forest"));
    public static RegistryKey<Biome> CORRUPT_MOUNTAIN = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(Reference.MODID,"corrupt_mountain"));

    public static RegistryKey<Biome> CRIMSON_PLAINS = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(Reference.MODID,"crimson_plains"));
    public static RegistryKey<Biome> CRIMSON_DESERT = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(Reference.MODID,"crimson_desert"));
    public static RegistryKey<Biome> CRIMSON_FOREST = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(Reference.MODID,"crimson_forest"));
    public static RegistryKey<Biome> CRIMSON_MOUNTAIN = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(Reference.MODID,"crimson_mountain"));

    public static RegistryKey<Biome> HALLOWED_PLAINS = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(Reference.MODID,"hallowed_plains"));
    public static RegistryKey<Biome> HALLOWED_DESERT = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(Reference.MODID,"hallowed_desert"));
    public static RegistryKey<Biome> HALLOWED_FOREST = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(Reference.MODID,"hallowed_forest"));
    public static RegistryKey<Biome> HALLOWED_MOUNTAIN = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(Reference.MODID,"hallowed_mountain"));

    public static void addBiomes()
    {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(MUD_JUNGLE, 1));

        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(CORRUPT_PLAINS, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(CORRUPT_DESERT, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(CORRUPT_FOREST, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(CORRUPT_MOUNTAIN, 1));

        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(CRIMSON_PLAINS, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(CRIMSON_DESERT, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(CRIMSON_FOREST, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(CRIMSON_MOUNTAIN, 1));

        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(HALLOWED_PLAINS, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(HALLOWED_DESERT, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(HALLOWED_FOREST, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(HALLOWED_MOUNTAIN, 1));
    }
}
