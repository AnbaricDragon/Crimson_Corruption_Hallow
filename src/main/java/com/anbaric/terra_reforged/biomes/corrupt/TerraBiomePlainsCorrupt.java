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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TerraBiomePlainsCorrupt extends Biome
{
    public TerraBiomePlainsCorrupt(Builder biomeBuilder)
    {
        super(new Builder()
                .surfaceBuilder(TerraSurfaceBuilderRegistry.BASE_CORRUPT, TerraSurfaceBuilderRegistry.GRASS_CORRUPT_CONFIG)
                .precipitation(RainType.RAIN).category(Category.PLAINS).depth(0.125F).scale(0.05F).temperature(0.8F).downfall(0.4F).waterColor(1771829).waterFogColor(2955068).parent("corrupt"));
        this.addStructure(Feature.VILLAGE.withConfiguration(new VillageConfig("village/plains/town_centers", 6)));
        this.addStructure(Feature.PILLAGER_OUTPOST.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
        this.addStructure(Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        TerraBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addStructures(this);
        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addMonsterRooms(this);
        DefaultBiomeFeatures.addPlainsTallGrass(this);
        TerraBiomeFeatures.addDyeFlowers(this);
        TerraBiomeFeatures.addStoneVariants(this, TerraBiomeFeatures.CORRUPT);
        TerraBiomeFeatures.addCrimsonOres(this);
        TerraBiomeFeatures.addSedimentDisks(this, TerraBiomeFeatures.GRAVEL, TerraBiomeFeatures.CORRUPT_SAND);
        TerraBiomeFeatures.addEbonTreesGrass(this);
        TerraBiomeFeatures.addMushrooms(this, TerraBiomeFeatures.VILE_MUSHROOM_CONFIG);
        TerraBiomeFeatures.addReedsAndPumpkins(this, TerraBiomeFeatures.CORRUPT_SUGAR_CANE_CONFIG);
        DefaultBiomeFeatures.addSprings(this);
        //TerraBiomeFeatures.addCorruptSnowLayer(this);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getSkyColor()
    {
        return 4660844;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getGrassColor(double posX, double posZ)
    {
        return 4597881;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getFoliageColor()

    {
        return 4597881;
    }
}
