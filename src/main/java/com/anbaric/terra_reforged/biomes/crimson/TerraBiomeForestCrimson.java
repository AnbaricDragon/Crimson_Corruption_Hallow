package com.anbaric.terra_reforged.biomes.crimson;

import com.anbaric.terra_reforged.features.TerraBiomeFeatures;
import com.anbaric.terra_reforged.util.init.TerraSurfaceBuilderRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;

public class TerraBiomeForestCrimson extends Biome
{
    public TerraBiomeForestCrimson(Builder biomeBuilder)
    {
        super(new Builder()
                .surfaceBuilder(TerraSurfaceBuilderRegistry.BASE_CRIMSON, TerraSurfaceBuilderRegistry.GRASS_CRIMSON_CONFIG).precipitation(RainType.RAIN).category(Category.FOREST)
                .depth(0.1F).scale(0.2F).temperature(0.7F).downfall(0.8F).waterColor(6821670).waterFogColor(7804710).parent("crimson"));
        this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
        this.addStructure(Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        this.addStructure(Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        TerraBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addStructures(this);
        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addMonsterRooms(this);
        DefaultBiomeFeatures.addDoubleFlowers(this);
        TerraBiomeFeatures.addDyeFlowers(this);
        TerraBiomeFeatures.addStoneVariants(this, TerraBiomeFeatures.CRIMSON);
        TerraBiomeFeatures.addCrimsonOres(this);
        TerraBiomeFeatures.addSedimentDisks(this, TerraBiomeFeatures.GRAVEL, TerraBiomeFeatures.CRIMSON_SAND);
        TerraBiomeFeatures.addForestTrees(this, TerraBiomeFeatures.SHADE_TREE_CONFIG, TerraBiomeFeatures.SHADE_TREE_CONFIG);
        DefaultBiomeFeatures.addDefaultFlowers(this);
        TerraBiomeFeatures.addDoubleGrass(this, TerraBiomeFeatures.CRIMSON_DOUBLE_TALL_GRASS_CONFIG);
        TerraBiomeFeatures.addTallGrass(this, TerraBiomeFeatures.CRIMSON_TALL_GRASS_CONFIG);
        TerraBiomeFeatures.addMushrooms(this, TerraBiomeFeatures.VICIOUS_MUSHROOM_CONFIG);
        TerraBiomeFeatures.addReedsAndPumpkins(this, TerraBiomeFeatures.CRIMSON_SUGAR_CANE_CONFIG);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addFreezeTopLayer(this);
    }
}
