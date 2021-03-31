package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.util.handlers.FeatureGenHandler;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraDefaultBiomeAdditionsEvent
{
    @SubscribeEvent
    public static void generateOres(final BiomeLoadingEvent event)
    {
        if (!event.getName().getNamespace().contains("terra_reforged"))
        {
            generateOre(event.getGeneration());
        }
    }

    private static void generateOre(BiomeGenerationSettingsBuilder settings)
    {
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_COPPER_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_TIN_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_IRON_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_LEAD_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_SILVER_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_TUNGSTEN_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_GOLD_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_PLATINUM_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_DEMONITE_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_CRIMTANE_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_COBALT_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_PALLADIUM_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_MYTHRIL_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_ORICHALCUM_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_ADAMANTITE_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_TITANIUM_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_AMBER_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_RUBY_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_SAPPHIRE_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_TOPAZ_GEN);
        settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureGenHandler.ORE_PURE_AMETHYST_GEN);
    }
}
