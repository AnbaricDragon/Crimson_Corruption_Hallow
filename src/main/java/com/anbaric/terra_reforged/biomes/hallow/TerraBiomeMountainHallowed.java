package com.anbaric.terra_reforged.biomes.hallow;

import com.anbaric.terra_reforged.features.TerraBiomeFeatures;
import com.anbaric.terra_reforged.util.init.TerraSurfaceBuilderRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;

public class TerraBiomeMountainHallowed extends Biome
{
    public TerraBiomeMountainHallowed(Builder biomeBuilder)
    {
        super(new Builder()
                .surfaceBuilder(TerraSurfaceBuilderRegistry.MOUNTAIN_HALLOWED, TerraSurfaceBuilderRegistry.GRASS_HALLOWED_CONFIG).precipitation(RainType.RAIN).category(Category.EXTREME_HILLS)
                .depth(1.0F).scale(0.5F).temperature(0.2F).downfall(0.3F).waterColor(16745727).waterFogColor(16758783).parent("hallowed"));
        this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
        this.addStructure(Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        TerraBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addStructures(this);
        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addMonsterRooms(this);
        TerraBiomeFeatures.addStoneVariants(this, TerraBiomeFeatures.HALLOWED);
        TerraBiomeFeatures.addCorruptOres(this);
        TerraBiomeFeatures.addSedimentDisks(this, TerraBiomeFeatures.GRAVEL, TerraBiomeFeatures.HALLOWED_SAND);
        TerraBiomeFeatures.addPearlTreesFlowersGrass(this);
        DefaultBiomeFeatures.addMushrooms(this);
        TerraBiomeFeatures.addReedsAndPumpkins(this, TerraBiomeFeatures.HALLOWED_SUGAR_CANE_CONFIG);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addFreezeTopLayer(this);
    }
}
