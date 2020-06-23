package com.anbaric.terra_reforged.biomes.hallow;

import com.anbaric.terra_reforged.features.TerraBiomeFeatures;
import com.anbaric.terra_reforged.util.init.TerraSurfaceBuilderRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.feature.structure.VillageConfig;

public class TerraBiomeDesertHallowed extends Biome
{
    public TerraBiomeDesertHallowed(Builder biomeBuilder)
    {
        super(new Builder()
                .surfaceBuilder(TerraSurfaceBuilderRegistry.DESERT_HALLOWED, TerraSurfaceBuilderRegistry.SAND_HALLOWED_CONFIG).precipitation(RainType.NONE).category(Category.DESERT)
                .depth(0.125F).scale(0.05F).temperature(2.0F).downfall(0.0F).waterColor(16745727).waterFogColor(16758783).parent("hallowed"));
        this.addStructure(Feature.VILLAGE.withConfiguration(new VillageConfig("village/desert/town_centers", 6)));
        this.addStructure(Feature.PILLAGER_OUTPOST.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        this.addStructure(Feature.DESERT_PYRAMID.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
        this.addStructure(Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        TerraBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addStructures(this);
        DefaultBiomeFeatures.addDesertLakes(this);
        DefaultBiomeFeatures.addMonsterRooms(this);
        TerraBiomeFeatures.addSandstoneVariants(this, TerraBiomeFeatures.HALLOWED, TerraBiomeFeatures.HALLOWED_SAND, TerraBiomeFeatures.HALLOWED_HARDSAND, TerraBiomeFeatures.HALLOWED_SANDSTONE);
        TerraBiomeFeatures.addHallowedOres(this);
        TerraBiomeFeatures.addSedimentDisks(this, TerraBiomeFeatures.SILT, TerraBiomeFeatures.HALLOWED_SAND);
        DefaultBiomeFeatures.addDefaultFlowers(this);
        TerraBiomeFeatures.addSparseTallGrass(this, TerraBiomeFeatures.HALLOWED_TALL_GRASS_CONFIG);
        DefaultBiomeFeatures.addMushrooms(this);
        DefaultBiomeFeatures.addDeadBushes(this);
        TerraBiomeFeatures.addExtraReedsPumpkinsCactus(this, TerraBiomeFeatures.HALLOWED_SUGAR_CANE_CONFIG, TerraBiomeFeatures.HALLOWED_CACTUS_CONFIG);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addDesertFeatures(this);
        DefaultBiomeFeatures.addFreezeTopLayer(this);
    }
}