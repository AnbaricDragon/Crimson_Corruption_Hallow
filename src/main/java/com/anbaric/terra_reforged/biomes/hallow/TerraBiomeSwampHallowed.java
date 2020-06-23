package com.anbaric.terra_reforged.biomes.hallow;

import com.anbaric.terra_reforged.features.TerraBiomeFeatures;
import com.anbaric.terra_reforged.util.init.TerraSurfaceBuilderRegistry;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.GrassColors;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class TerraBiomeSwampHallowed extends Biome
{
    public TerraBiomeSwampHallowed(Builder biomeBuilder)
    {
        super(new Builder()
                .surfaceBuilder(TerraSurfaceBuilderRegistry.SWAMP_HALLOWED, TerraSurfaceBuilderRegistry.GRASS_HALLOWED_CONFIG)
                .precipitation(RainType.RAIN).category(Category.SWAMP).depth(-0.2F).scale(0.1F).temperature(0.8F).downfall(0.9F)
                .waterColor(16745727).waterFogColor(16758783).parent("hallowed"));
        this.addStructure(Feature.SWAMP_HUT.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
        TerraBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addStructures(this);
        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addMonsterRooms(this);
        TerraBiomeFeatures.addDyeFlowers(this);
        TerraBiomeFeatures.addStoneVariants(this, TerraBiomeFeatures.HALLOWED);
        TerraBiomeFeatures.addHallowedOres(this);
        DefaultBiomeFeatures.addSwampClayDisks(this);
        TerraBiomeFeatures.addPearlSwampVegetation(this);
        TerraBiomeFeatures.addExtraReedsAndPumpkins(this, TerraBiomeFeatures.HALLOWED_SUGAR_CANE_CONFIG);
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SEAGRASS.withConfiguration(new SeaGrassConfig(64, 0.6D)).withPlacement(Placement.TOP_SOLID_HEIGHTMAP.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
        DefaultBiomeFeatures.addFossils(this);
        DefaultBiomeFeatures.addFreezeTopLayer(this);
    }

    public int getHallowedFoliageColor(double temp, double rain)
    {
        if (temp < 0.50)
        {
            if (rain < 0.25)
            {
                return 13085763;
            }
            else if (rain < 0.5)
            {
                return 4420295;
            }
            else if (rain < 0.75)
            {
                return 13058914;
            }
            else
            {
                return 9716679;
            }
        }
        else
        {
            if (rain < 0.25)
            {
                return 4441933;
            }
            else if (rain < 0.5)
            {
                return 7095239;
            }
            else if (rain < 0.75)
            {
                return 12550089;
            }
            else
            {
                return 4376508;
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getFoliageColor()
    {
        double temp = (double)MathHelper.clamp(this.getDefaultTemperature(), 0.0F, 1.0F);
        double rain = (double)MathHelper.clamp(this.getDownfall(), 0.0F, 1.0F);
        return getHallowedFoliageColor(temp, rain);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getGrassColor(double posX, double posZ)
    {
        return 4896970;
    }
}