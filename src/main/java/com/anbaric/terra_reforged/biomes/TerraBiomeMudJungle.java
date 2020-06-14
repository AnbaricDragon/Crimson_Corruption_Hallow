package com.anbaric.terra_reforged.biomes;

import com.anbaric.terra_reforged.features.TerraBiomeFeatures;
import com.anbaric.terra_reforged.features.carvers.TerraWorldCarver;
import com.anbaric.terra_reforged.util.init.TerraSurfaceBuilderRegistry;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class TerraBiomeMudJungle extends Biome
{
    private static final BlockState AIR = Blocks.CAVE_AIR.getDefaultState();
    private static final BlockState WATER = Blocks.WATER.getDefaultState();

    public TerraBiomeMudJungle(Builder biomeBuilder)
    {
        super(biomeBuilder
                .precipitation(RainType.RAIN).category(Category.JUNGLE).depth(0.3F).scale(0.2F).temperature(0.95F).downfall(0.9F).waterColor(4186852).waterFogColor(343859).parent("jungle")
                .surfaceBuilder(TerraSurfaceBuilderRegistry.MUD_JUNGLE, TerraSurfaceBuilderRegistry.MUD_JUNGLE_CONFIG)
        );
        TerraBiomeFeatures.addStoneVariants(this, TerraBiomeFeatures.PURE);
        TerraBiomeFeatures.addSedimentDisks(this, AIR);
        this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(TerraWorldCarver.TERRA_JUNGLE_CAVES, new ProbabilityConfig(0.5F)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, Feature.MINESHAFT.withConfiguration(new MineshaftConfig((double)0.004F, MineshaftStructure.Type.NORMAL)).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(WATER)).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(4))));
        TerraBiomeFeatures.addPureOres(this);
        DefaultBiomeFeatures.addBamboo(this);
        DefaultBiomeFeatures.addJungleTreeForest(this);
        DefaultBiomeFeatures.addExtraDefaultFlowers(this);
        DefaultBiomeFeatures.addJungleGrass(this);
        DefaultBiomeFeatures.addMushrooms(this);
        DefaultBiomeFeatures.addReedsAndPumpkins(this);
        DefaultBiomeFeatures.addSprings(this);
        DefaultBiomeFeatures.addJunglePlants(this);

    }
}
