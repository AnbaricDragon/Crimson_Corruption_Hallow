package com.anbaric.terra_reforged.biomes.corrupt;

import com.anbaric.terra_reforged.features.TerraBiomeFeatures;
import com.anbaric.terra_reforged.util.init.TerraSurfaceBuilderRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class TerraBiomeMountainCorrupt extends Biome
{
    public TerraBiomeMountainCorrupt(Builder biomeBuilder)
    {
        super(new Builder()
                .surfaceBuilder(TerraSurfaceBuilderRegistry.MOUNTAIN_CORRUPT, TerraSurfaceBuilderRegistry.GRASS_CORRUPT_CONFIG).precipitation(RainType.RAIN).category(Category.EXTREME_HILLS)
                .depth(1.0F).scale(0.5F).temperature(0.2F).downfall(0.3F).waterColor(1771829).waterFogColor(2955068).parent("corrupt"));
        this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
        this.addStructure(Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        TerraBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addStructures(this);
        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addMonsterRooms(this);
        TerraBiomeFeatures.addStoneVariants(this, TerraBiomeFeatures.CORRUPT);
        TerraBiomeFeatures.addCorruptOres(this);
        TerraBiomeFeatures.addSedimentDisks(this, TerraBiomeFeatures.GRAVEL, TerraBiomeFeatures.CORRUPT_SAND);
        TerraBiomeFeatures.addEbonTreesGrass(this);
        TerraBiomeFeatures.addMushrooms(this, TerraBiomeFeatures.VILE_MUSHROOM_CONFIG);
        TerraBiomeFeatures.addReedsAndPumpkins(this, TerraBiomeFeatures.CORRUPT_SUGAR_CANE_CONFIG);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addFreezeTopLayer(this);
    }
}
