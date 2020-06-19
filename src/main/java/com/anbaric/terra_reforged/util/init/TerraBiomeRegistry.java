package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.biomes.*;
import com.anbaric.terra_reforged.biomes.corrupt.TerraBiomeForestCorrupt;
import com.anbaric.terra_reforged.biomes.corrupt.TerraBiomePlainsCorrupt;
import com.anbaric.terra_reforged.biomes.corrupt.TerraBiomeSnowCorrupt;
import com.anbaric.terra_reforged.biomes.crimson.TerraBiomeForestCrimson;
import com.anbaric.terra_reforged.biomes.crimson.TerraBiomePlainsCrimson;
import com.anbaric.terra_reforged.biomes.crimson.TerraBiomeSnowCrimson;
import com.anbaric.terra_reforged.biomes.hallow.TerraBiomeForestHallowed;
import com.anbaric.terra_reforged.biomes.hallow.TerraBiomePlainsHallowed;
import com.anbaric.terra_reforged.biomes.hallow.TerraBiomeSnowHallowed;
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
    public static final RegistryObject<Biome> SNOW_PURE = BIOMES.register("snow_pure", () -> new TerraBiomeSnowPure(new Biome.Builder().category(Biome.Category.ICY).parent("icy")));

    public static final RegistryObject<Biome> PLAINS_CORRUPT = BIOMES.register("plains_corrupt", () -> new TerraBiomePlainsCorrupt(new Biome.Builder().category(Biome.Category.PLAINS).parent("corrupt")));
    public static final RegistryObject<Biome> FOREST_CORRUPT = BIOMES.register("forest_corrupt", () -> new TerraBiomeForestCorrupt(new Biome.Builder().category(Biome.Category.FOREST).parent("corrupt")));
    public static final RegistryObject<Biome> SNOW_CORRUPT = BIOMES.register("snow_corrupt", () -> new TerraBiomeSnowCorrupt(new Biome.Builder().category(Biome.Category.ICY).parent("corrupt")));

    public static final RegistryObject<Biome> PLAINS_CRIMSON = BIOMES.register("plains_crimson", () -> new TerraBiomePlainsCrimson(new Biome.Builder().category(Biome.Category.PLAINS).parent("crimson")));
    public static final RegistryObject<Biome> FOREST_CRIMSON = BIOMES.register("forest_crimson", () -> new TerraBiomeForestCrimson(new Biome.Builder().category(Biome.Category.FOREST).parent("crimson")));
    public static final RegistryObject<Biome> SNOW_CRIMSON = BIOMES.register("snow_crimson", () -> new TerraBiomeSnowCrimson(new Biome.Builder().category(Biome.Category.ICY).parent("crimson")));

    public static final RegistryObject<Biome> PLAINS_HALLOWED = BIOMES.register("plains_hallowed", () -> new TerraBiomePlainsHallowed(new Biome.Builder().category(Biome.Category.PLAINS).parent("hallowed")));
    public static final RegistryObject<Biome> FOREST_HALLOWED = BIOMES.register("forest_hallowed", () -> new TerraBiomeForestHallowed(new Biome.Builder().category(Biome.Category.FOREST).parent("hallowed")));
    public static final RegistryObject<Biome> SNOW_HALLOWED = BIOMES.register("snow_hallowed", () -> new TerraBiomeSnowHallowed(new Biome.Builder().category(Biome.Category.ICY).parent("hallowed")));

    public static void registerBiomes()
    {
        registerBiome(TerraBiomeRegistry.MUD_JUNGLE.get(), 10, BiomeManager.BiomeType.WARM, Type.JUNGLE, Type.OVERWORLD);
        registerBiome(TerraBiomeRegistry.SNOW_PURE.get(), 10, BiomeManager.BiomeType.ICY, Type.COLD, Type.CONIFEROUS, Type.OVERWORLD);

        registerBiome(TerraBiomeRegistry.PLAINS_CORRUPT.get(), 10, BiomeManager.BiomeType.WARM, Type.PLAINS, Type.SPARSE, Type.OVERWORLD);
        registerBiome(TerraBiomeRegistry.FOREST_CORRUPT.get(), 10, BiomeManager.BiomeType.COOL, Type.FOREST, Type.DENSE, Type.OVERWORLD);
        registerBiome(TerraBiomeRegistry.SNOW_CORRUPT.get(), 10, BiomeManager.BiomeType.ICY, Type.COLD, Type.SNOWY, Type.CONIFEROUS, Type.OVERWORLD);

        registerBiome(TerraBiomeRegistry.PLAINS_CRIMSON.get(), 10, BiomeManager.BiomeType.WARM, Type.PLAINS, Type.SPARSE, Type.OVERWORLD);
        registerBiome(TerraBiomeRegistry.SNOW_CRIMSON.get(), 10, BiomeManager.BiomeType.ICY, Type.COLD, Type.SNOWY, Type.CONIFEROUS, Type.OVERWORLD);

        registerBiome(TerraBiomeRegistry.PLAINS_HALLOWED.get(), 10, BiomeManager.BiomeType.WARM, Type.PLAINS, Type.SPARSE, Type.OVERWORLD);
        registerBiome(TerraBiomeRegistry.SNOW_HALLOWED.get(), 10, BiomeManager.BiomeType.ICY, Type.COLD, Type.SNOWY, Type.CONIFEROUS, Type.OVERWORLD);
    }

    public static void registerBiome(Biome biome, int rarity, BiomeManager.BiomeType type , Type... types)
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
