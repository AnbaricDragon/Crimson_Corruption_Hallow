package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.features.landscape.TerraFeatureLakes;
import com.anbaric.terra_reforged.features.structures.TerraFeatureDesertWell;
import com.anbaric.terra_reforged.features.vegetation.*;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.SpreadingHandler;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TerraFeatureRegistry
{
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Reference.MODID);

    public static final RegistryObject<TerraFeatureTreeBoreal> BOREAL_TREE = FEATURES.register("tree_boreal", TerraFeatureTreeBoreal::new);
    //    public static final RegistryObject<TerraFeaturePalmTree> PALM_TREE = FEATURES.register("tree_palm", TerraFeaturePalmTree::new);
    public static final RegistryObject<TerraFeatureTreeMahogany> MAHOGANY_TREE = FEATURES.register("tree_mahogany", TerraFeatureTreeMahogany::new);
    public static final RegistryObject<TerraFeatureTreeMahoganyLarge> MAHOGANY_TREE_LARGE = FEATURES.register("tree_mahogany_large", TerraFeatureTreeMahoganyLarge::new);
    public static final RegistryObject<TerraFeatureTreeEbon> EBON_TREE = FEATURES.register("tree_ebon", TerraFeatureTreeEbon::new);
    public static final RegistryObject<TerraFeatureTreeShade> SHADE_TREE = FEATURES.register("tree_shade", TerraFeatureTreeShade::new);
    public static final RegistryObject<TerraFeatureTreePearl> PEARL_TREE = FEATURES.register("tree_pearl", TerraFeatureTreePearl::new);
    public static final RegistryObject<TerraFeatureMushroomGlowing> MUSHROOM_GLOWING = FEATURES.register("mushroom_glowing", TerraFeatureMushroomGlowing::new);
    public static final RegistryObject<TerraFeatureCactus> CACTUS_EBON = FEATURES.register("cactus_ebon", () -> new TerraFeatureCactus(TerraBlockRegistry.CACTUS_EBON.get()));
    public static final RegistryObject<TerraFeatureCactus> CACTUS_CRIM = FEATURES.register("cactus_crim", () -> new TerraFeatureCactus(TerraBlockRegistry.CACTUS_CRIM.get()));
    public static final RegistryObject<TerraFeatureCactus> CACTUS_PEARL = FEATURES.register("cactus_pearl", () -> new TerraFeatureCactus(TerraBlockRegistry.CACTUS_PEARL.get()));
    public static final RegistryObject<TerraFeatureLakes> LAKE_CORRUPT = FEATURES.register("lake_corrupt", () -> new TerraFeatureLakes(SpreadingHandler.EnumBiomeType.CORRUPT));
    public static final RegistryObject<TerraFeatureLakes> LAKE_CRIMSON = FEATURES.register("lake_crimson", () -> new TerraFeatureLakes(SpreadingHandler.EnumBiomeType.CRIMSON));
    public static final RegistryObject<TerraFeatureLakes> LAKE_HALLOWED = FEATURES.register("lake_hallowed", () -> new TerraFeatureLakes(SpreadingHandler.EnumBiomeType.HALLOWED));
    public static final RegistryObject<TerraFeatureDesertWell> DESERT_WELL_CORRUPT = FEATURES.register("desert_well_corrupt", () -> new TerraFeatureDesertWell(SpreadingHandler.EnumBiomeType.CORRUPT));
    public static final RegistryObject<TerraFeatureDesertWell> DESERT_WELL_CRIMSON = FEATURES.register("desert_well_crimson", () -> new TerraFeatureDesertWell(SpreadingHandler.EnumBiomeType.CRIMSON));
    public static final RegistryObject<TerraFeatureDesertWell> DESERT_WELL_HALLOWED = FEATURES.register("desert_well_hallowed", () -> new TerraFeatureDesertWell(SpreadingHandler.EnumBiomeType.HALLOWED));
}