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
    public static RegistryKey<Biome> CORRUPT_PLAINS = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(Reference.MODID,"corrupt_plains"));
    public static RegistryKey<Biome> CORRUPT_DESERT = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(Reference.MODID,"corrupt_desert"));

    public static void addBiomes()
    {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(CORRUPT_PLAINS, 1));
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(CORRUPT_DESERT, 1));
    }
}
