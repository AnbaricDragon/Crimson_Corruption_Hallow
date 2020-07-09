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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TerraBiomeForestHallowed extends Biome
{
    public TerraBiomeForestHallowed(Builder biomeBuilder)
    {
        super(new Builder()
                .surfaceBuilder(TerraSurfaceBuilderRegistry.BASE_HALLOWED, TerraSurfaceBuilderRegistry.GRASS_HALLOWED_CONFIG).precipitation(RainType.RAIN).category(Category.FOREST)
                .depth(0.1F).scale(0.2F).temperature(0.7F).downfall(0.8F).waterColor(16745727).waterFogColor(16758783).parent("hallowed"));
        this.addStructure(Feature.VILLAGE.withConfiguration(new VillageConfig("village/plains/town_centers", 6)));
        this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
        this.addStructure(Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        this.addStructure(Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        TerraBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addStructures(this);
        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addMonsterRooms(this);
        DefaultBiomeFeatures.addDoubleFlowers(this);
        TerraBiomeFeatures.addDyeFlowers(this);
        TerraBiomeFeatures.addStoneVariants(this, TerraBiomeFeatures.HALLOWED);
        TerraBiomeFeatures.addCrimsonOres(this);
        TerraBiomeFeatures.addSedimentDisks(this, TerraBiomeFeatures.GRAVEL, TerraBiomeFeatures.HALLOWED_SAND);
        TerraBiomeFeatures.addPearlTrees(this);
        DefaultBiomeFeatures.addDefaultFlowers(this);
        DefaultBiomeFeatures.addGrass(this);
        TerraBiomeFeatures.addReedsAndPumpkins(this, TerraBiomeFeatures.HALLOWED_SUGAR_CANE_CONFIG);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addFreezeTopLayer(this);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getSkyColor()
    {
        return 40177;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getGrassColor(double posX, double posZ)
    {
        return 56831;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getFoliageColor()

    {
        return 56831;
    }
}
