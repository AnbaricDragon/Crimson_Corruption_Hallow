package com.anbaric.terra_reforged.biomes.corrupt;

import com.anbaric.terra_reforged.features.TerraBiomeFeatures;
import com.anbaric.terra_reforged.util.init.TerraSurfaceBuilderRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TerraBiomeForestCorrupt extends Biome
{
    public TerraBiomeForestCorrupt(Builder biomeBuilder)
    {
        super(new Builder()
                .surfaceBuilder(TerraSurfaceBuilderRegistry.BASE_CORRUPT, TerraSurfaceBuilderRegistry.GRASS_CORRUPT_CONFIG)
                .precipitation(RainType.RAIN).category(Category.FOREST).depth(0.1F).scale(0.2F).temperature(0.7F).downfall(0.8F)
                .waterColor(1771829).waterFogColor(2955068).parent("corrupt"));
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
        TerraBiomeFeatures.addStoneVariants(this, TerraBiomeFeatures.CORRUPT);
        TerraBiomeFeatures.addCorruptOres(this);
        TerraBiomeFeatures.addSedimentDisks(this, TerraBiomeFeatures.GRAVEL, TerraBiomeFeatures.CORRUPT_SAND);
        TerraBiomeFeatures.addForestTrees(this, TerraBiomeFeatures.EBON_TREE_CONFIG, TerraBiomeFeatures.EBON_TREE_CONFIG);
        DefaultBiomeFeatures.addDefaultFlowers(this);
        TerraBiomeFeatures.addDoubleGrass(this, TerraBiomeFeatures.CORRUPT_DOUBLE_TALL_GRASS_CONFIG);
        TerraBiomeFeatures.addTallGrass(this, TerraBiomeFeatures.CORRUPT_TALL_GRASS_CONFIG);
        TerraBiomeFeatures.addMushrooms(this, TerraBiomeFeatures.VILE_MUSHROOM_CONFIG);
        TerraBiomeFeatures.addReedsAndPumpkins(this, TerraBiomeFeatures.CORRUPT_SUGAR_CANE_CONFIG);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addFreezeTopLayer(this);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColor(double posX, double posZ)
    {
        return 5636216;
    }
}
