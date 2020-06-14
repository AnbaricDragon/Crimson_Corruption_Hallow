package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.biomes.*;
import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TerraBiomeRegistry
{
    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, Reference.MODID);

    public static final RegistryObject<Biome> MUD_JUNGLE = BIOMES.register("mud_jungle", () -> new TerraBiomeMudJungle(new Biome.Builder().category(Biome.Category.JUNGLE).parent("jungle")));
    public static final RegistryObject<Biome> SNOW_PURE = BIOMES.register("snow_pure", () -> new TerraBiomeSnowPure(new Biome.Builder().category(Biome.Category.ICY).parent("taiga")));
    public static final RegistryObject<Biome> SNOW_CORRUPT = BIOMES.register("snow_corrupt", () -> new TerraBiomeSnowCorrupt(new Biome.Builder().category(Biome.Category.ICY).parent("corrupt")));
    public static final RegistryObject<Biome> SNOW_CRIMSON = BIOMES.register("snow_crimson", () -> new TerraBiomeSnowCrimson(new Biome.Builder().category(Biome.Category.ICY).parent("crimson")));
    public static final RegistryObject<Biome> SNOW_HALLOWED = BIOMES.register("snow_hallowed", () -> new TerraBiomeSnowHallow(new Biome.Builder().category(Biome.Category.ICY).parent("hallowed")));

    public static void registerBiomes()
    {
        registerBiome(TerraBiomeRegistry.MUD_JUNGLE.get(), 100, BiomeManager.BiomeType.WARM, BiomeDictionary.Type.JUNGLE, Type.OVERWORLD);
        registerBiome(TerraBiomeRegistry.SNOW_PURE.get(), 100, BiomeManager.BiomeType.ICY, BiomeDictionary.Type.COLD, Type.CONIFEROUS, Type.OVERWORLD);
        registerBiome(TerraBiomeRegistry.SNOW_CORRUPT.get(), 20, BiomeManager.BiomeType.ICY, BiomeDictionary.Type.COLD, Type.CONIFEROUS, Type.OVERWORLD);
        registerBiome(TerraBiomeRegistry.SNOW_CRIMSON.get(), 20, BiomeManager.BiomeType.ICY, BiomeDictionary.Type.COLD, Type.CONIFEROUS, Type.OVERWORLD);
        registerBiome(TerraBiomeRegistry.SNOW_HALLOWED.get(), 20, BiomeManager.BiomeType.ICY, BiomeDictionary.Type.COLD, Type.CONIFEROUS, Type.OVERWORLD);
    }

    public static void registerBiome(Biome biome, int rarity, BiomeManager.BiomeType type , BiomeDictionary.Type... types)
    {
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(biome, rarity));
    }

    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event)
    {
        registerBiomes();
    }
}
