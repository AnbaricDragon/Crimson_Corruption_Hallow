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
}
