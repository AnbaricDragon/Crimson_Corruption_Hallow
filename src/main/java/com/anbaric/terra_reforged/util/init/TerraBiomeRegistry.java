package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.biomes.MudJungleBiome;
import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.storage.ChunkLoader;
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

    public static final RegistryObject<Biome> MUD_JUNGLE = BIOMES.register("mud_jungle", () -> new MudJungleBiome(new Biome.Builder().category(Biome.Category.JUNGLE).parent("jungle")));

    public static void registerBiomes()
    {
        registerBiome(TerraBiomeRegistry.MUD_JUNGLE.get(), BiomeDictionary.Type.JUNGLE, Type.OVERWORLD);
    }

    public static void registerBiome(Biome biome, BiomeDictionary.Type... types)
    {
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(biome, 100));
    }

    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event)
    {
        registerBiomes();
    }

}
