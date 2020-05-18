package com.anbaric.terra_reforged.biomes;

import com.anbaric.terra_reforged.util.init.TerraSurfaceBuilderRegistry;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class MudJungleBiome extends Biome
{
    private static final BlockState WATER = Blocks.WATER.getDefaultState();
    private static final BlockState MUD_GRASS = TerraBlockRegistry.GRASS_JUNGLE.get().getDefaultState();
    private static final BlockState MUD = TerraBlockRegistry.SOIL_MUD.get().getDefaultState();

    public MudJungleBiome(Builder biomeBuilder)
    {
        super(biomeBuilder
                .precipitation(RainType.RAIN).category(Category.JUNGLE).depth(0.1F).scale(0.2F).temperature(0.95F).downfall(0.9F).waterColor(4186852).waterFogColor(343859).parent("jungle")
                .surfaceBuilder(TerraSurfaceBuilderRegistry.MUD_JUNGLE, new SurfaceBuilderConfig(MUD_GRASS, MUD, MUD))

        );
        this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.CAVE, new ProbabilityConfig(0.45F)));
        DefaultBiomeFeatures.addStructures(this);
        DefaultBiomeFeatures.addStoneVariants(this);
        DefaultBiomeFeatures.addOres(this);
        DefaultBiomeFeatures.addSedimentDisks(this);
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(WATER)).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(4))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, MUD, 100)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(50, 0, 0, 256))));

    }
}
