package com.anbaric.terra_reforged.biomes;

import com.anbaric.terra_reforged.features.TerraBiomeFeatures;
import com.anbaric.terra_reforged.features.carvers.TerraWorldCarver;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraSurfaceBuilderRegistry;
import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class TerraBiomeSnowPure extends Biome
{
    private static final BlockState WATER = Blocks.WATER.getDefaultState();

    public TerraBiomeSnowPure(Builder biomeBuilder)
    {
        super(biomeBuilder
                .precipitation(Biome.RainType.SNOW).category(Biome.Category.ICY).depth(0.125F).scale(0.02F).temperature(0.0F).downfall(0.5F).waterColor(4159204).waterFogColor(329011).parent("jungle")
                .surfaceBuilder(TerraSurfaceBuilderRegistry.SNOW_PURE, TerraSurfaceBuilderRegistry.SNOW_PURE_CONFIG)
        );
        this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(TerraWorldCarver.TERRA_SNOW_CAVES, new ProbabilityConfig(0.7F)));
        TerraBiomeFeatures.addIceVariants(this, TerraBiomeFeatures.PURE, TerraBiomeFeatures.SNOW, TerraBiomeFeatures.ICE, TerraBiomeFeatures.PACKED_ICE);
        TerraBiomeFeatures.addPureOres(this);
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, Feature.MINESHAFT.withConfiguration(new MineshaftConfig((double)0.004F, MineshaftStructure.Type.NORMAL)).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(WATER)).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(4))));
    }
}
