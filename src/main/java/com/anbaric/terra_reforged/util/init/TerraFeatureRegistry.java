package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.features.vegetation.TerraFeatureEbonTree;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.BiomeHandler;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class TerraFeatureRegistry
{
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Reference.MODID);

    public static final RegistryObject<TerraFeatureEbonTree> BOREAL_TREE = FEATURES.register("tree_ebon", () -> new TerraFeatureEbonTree());
}
