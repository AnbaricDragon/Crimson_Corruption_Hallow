package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.features.carvers.TerraCarverCaveGeneral;
import com.anbaric.terra_reforged.features.carvers.TerraCarverRavineGeneral;
import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TerraCarverRegistry
{
    public static final DeferredRegister<WorldCarver<?>> CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, Reference.MODID);

    public static final RegistryObject<WorldCarver<ProbabilityConfig>> CARVER_CAVE_GENERAL = CARVERS.register("carver_cave_general", () -> new TerraCarverCaveGeneral(ProbabilityConfig.CODEC, 256));
    public static final RegistryObject<WorldCarver<ProbabilityConfig>> CARVER_RAVINE_GENERAL = CARVERS.register("carver_ravine_general", () -> new TerraCarverRavineGeneral(ProbabilityConfig.CODEC));
}
