package com.anbaric.terra_reforged.biomes.crimson;

import com.anbaric.terra_reforged.features.TerraBiomeFeatures;
import com.anbaric.terra_reforged.util.init.TerraSurfaceBuilderRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.SeaGrassConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

public class TerraBiomeSwampCrimson extends Biome
{
    public TerraBiomeSwampCrimson(Builder biomeBuilder)
    {
        super(new Builder()
                .surfaceBuilder(TerraSurfaceBuilderRegistry.SWAMP_CRIMSON, TerraSurfaceBuilderRegistry.GRASS_CRIMSON_CONFIG)
                .precipitation(RainType.RAIN).category(Category.SWAMP).depth(-0.2F).scale(0.1F).temperature(0.8F).downfall(0.9F)
                .waterColor(6821670).waterFogColor(7804710).parent("crimson"));
        this.addStructure(Feature.SWAMP_HUT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
        TerraBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addStructures(this);
        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addMonsterRooms(this);
        TerraBiomeFeatures.addDyeFlowers(this);
        TerraBiomeFeatures.addStoneVariants(this, TerraBiomeFeatures.CRIMSON);
        TerraBiomeFeatures.addCrimsonOres(this);
        DefaultBiomeFeatures.addSwampClayDisks(this);
        TerraBiomeFeatures.addShadeSwampVegetation(this);
        TerraBiomeFeatures.addExtraReedsAndPumpkins(this, TerraBiomeFeatures.CRIMSON_SUGAR_CANE_CONFIG);
        DefaultBiomeFeatures.addFossils(this);
        DefaultBiomeFeatures.addFreezeTopLayer(this);
    }
}
