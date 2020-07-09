package com.anbaric.terra_reforged.biomes.hallow;

import com.anbaric.terra_reforged.features.TerraBiomeFeatures;
import com.anbaric.terra_reforged.features.carvers.TerraWorldCarver;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraSurfaceBuilderRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TerraBiomeSnowHallowed extends Biome
{
    private static final BlockState WATER = Blocks.WATER.getDefaultState();

    public TerraBiomeSnowHallowed(Builder biomeBuilder)
    {
        super(biomeBuilder
                .precipitation(RainType.SNOW).category(Category.ICY).depth(0.125F).scale(0.02F).temperature(0.0F).downfall(0.5F).waterColor(16745727).waterFogColor(16758783).parent("hallowed")
                .surfaceBuilder(TerraSurfaceBuilderRegistry.SNOW_HALLOWED, TerraSurfaceBuilderRegistry.SNOW_HALLOWED_CONFIG)
        );
        this.addStructure(Feature.VILLAGE.withConfiguration(new VillageConfig("village/plains/town_centers", 6)));
        this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
        this.addStructure(Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        this.addStructure(Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(TerraWorldCarver.TERRA_SNOW_CAVES, new ProbabilityConfig(0.7F)));
        TerraBiomeFeatures.addIceVariants(this, TerraBiomeFeatures.HALLOWED, TerraBiomeFeatures.HALLOWED_SNOW, TerraBiomeFeatures.HALLOWED_ICE, TerraBiomeFeatures.HALLOWED_PACKED_ICE);
        TerraBiomeFeatures.addHallowedOres(this);
        TerraBiomeFeatures.addSedimentDisks(this, TerraBiomeFeatures.SLUSH, TerraBiomeFeatures.HALLOWED_SAND);
        TerraBiomeFeatures.addDyeFlowers(this);
//        TerraBiomeFeatures.addHallowedSnowLayer(this);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public int getSkyColor()
    {
        return 8829681;
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
