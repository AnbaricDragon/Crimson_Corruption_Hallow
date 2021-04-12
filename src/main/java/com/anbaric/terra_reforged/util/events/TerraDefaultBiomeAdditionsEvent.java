package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.util.handlers.FeatureGenHandler;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraDefaultBiomeAdditionsEvent
{
    @SubscribeEvent
    public static void generateFeatures(final BiomeLoadingEvent event)
    {
        if (!event.getName().getNamespace().contains("terra_reforged"))
        {
            generateOres(event.getGeneration());
            generateSoils(event.getGeneration(), event.getCategory());
            generateTrees(event.getGeneration(), event.getClimate().temperature, event.getClimate().temperature > 1.9D);
            generatePlants(event.getGeneration(), event.getGeneration().getSurfaceBuilder().get() == SurfaceBuilder.DEFAULT, event.getCategory() == Biome.Category.ICY, event.getCategory() == Biome.Category.BEACH || event.getCategory() == Biome.Category.DESERT);
        }
    }

    private static void generateSoils(BiomeGenerationSettingsBuilder settings, Biome.Category category)
    {
        if (category == Biome.Category.ICY)
        {
            settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_SNOW_GEN);
            settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_SLUSH_GEN);
            settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_ICE_GEN);
            settings.withFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, FeatureGenHandler.TRAP_ICE_THIN);
        }
        else if (category == Biome.Category.TAIGA)
        {
            settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_SNOW_GEN);
            settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_SLUSH_GEN);
        }
        else
        {
            settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_SILT_GEN);
        }
    }

    private static void generateTrees(BiomeGenerationSettingsBuilder settings, double boreal, boolean palm)
    {
        if (boreal < 0.0D || boreal == 0.25D) { settings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureGenHandler.BOREAL_TREE_TAIGA_GEN); }
        else if (boreal == 0.0D) { settings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureGenHandler.BOREAL_TREE_TUNDRA_GEN); }
        if (palm) { settings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureGenHandler.PALM_TREE_GEN); }
    }

    private static void generatePlants(BiomeGenerationSettingsBuilder settings, boolean daybloom, boolean shiverthorn, boolean waterleaf)
    {
        settings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureGenHandler.PLANT_BLINKROOT_GEN);
        if (daybloom) { settings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureGenHandler.PLANT_DAYBLOOM_GEN); }
        if (shiverthorn) { settings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureGenHandler.PLANT_SHIVERTHORN_GEN); }
        if (waterleaf) { settings.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureGenHandler.PLANT_WATERLEAF_GEN); }
    }

    private static void generateOres(BiomeGenerationSettingsBuilder settings)
    {
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_COPPER_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_TIN_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_IRON_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_LEAD_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_SILVER_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_TUNGSTEN_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_GOLD_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PLATINUM_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_COBALT_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PALLADIUM_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_MYTHRIL_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_ORICHALCUM_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_ADAMANTITE_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_TITANIUM_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_AMBER_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_RUBY_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_SAPPHIRE_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_TOPAZ_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_AMETHYST_GEN);
    }
}
