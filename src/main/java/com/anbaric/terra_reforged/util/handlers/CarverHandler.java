package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.init.TerraCarverRegistry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class CarverHandler
{
    public static void addCarvers()
    {
        WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_CARVER, Reference.path("carver_cave_general"), TerraCarverRegistry.CARVER_CAVE_GENERAL.get().configured(new ProbabilityConfig(0.15F)));
        WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_CARVER, Reference.path("carver_ravine_general"), TerraCarverRegistry.CARVER_RAVINE_GENERAL.get().configured(new ProbabilityConfig(0.02F)));
    }
}
