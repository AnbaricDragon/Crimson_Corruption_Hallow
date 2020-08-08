package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.features.TerraBiomeFeatures;
import com.anbaric.terra_reforged.features.landscape.TerraFeatureSnowLayer;
import com.anbaric.terra_reforged.features.vegetation.TerraTreeBoreal;
import com.anbaric.terra_reforged.features.vegetation.TerraTreeEbon;
import com.anbaric.terra_reforged.features.vegetation.TerraTreeMahogany;
import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TerraFeatureRegistry
{
    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, Reference.MODID);

    public static final RegistryObject<TerraTreeBoreal> TREE_BOREAL = FEATURES.register("tree_boreal", () -> new TerraTreeBoreal(NoFeatureConfig::deserialize));
    public static final RegistryObject<TerraTreeMahogany> TREE_MAHOGANY = FEATURES.register("tree_mahogany", () -> new TerraTreeMahogany(NoFeatureConfig::deserialize));
    public static final RegistryObject<TerraTreeEbon> TREE_EBON = FEATURES.register("tree_ebon", () -> new TerraTreeEbon(NoFeatureConfig::deserialize));

    public static final RegistryObject<TerraFeatureSnowLayer> CORRUPT_SNOW_LAYER = FEATURES.register("corrupt_snow_layer", () -> new TerraFeatureSnowLayer(NoFeatureConfig::deserialize, TerraBiomeFeatures.CORRUPT_ICE, TerraBiomeFeatures.CORRUPT_SNOW_LAYER));
    public static final RegistryObject<TerraFeatureSnowLayer> CRIMSON_SNOW_LAYER = FEATURES.register("crimson_snow_layer", () -> new TerraFeatureSnowLayer(NoFeatureConfig::deserialize, TerraBiomeFeatures.CRIMSON_ICE, TerraBiomeFeatures.CRIMSON_SNOW_LAYER));
    public static final RegistryObject<TerraFeatureSnowLayer> HALLOWED_SNOW_LAYER = FEATURES.register("hallowed_snow_layer", () -> new TerraFeatureSnowLayer(NoFeatureConfig::deserialize, TerraBiomeFeatures.HALLOWED_ICE, TerraBiomeFeatures.HALLOWED_SNOW_LAYER));
}
