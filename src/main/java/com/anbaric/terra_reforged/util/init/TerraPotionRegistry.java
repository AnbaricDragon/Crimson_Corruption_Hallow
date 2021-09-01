package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TerraPotionRegistry
{
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, Reference.MODID);

    public static final RegistryObject<Potion> WATERWALKING = POTIONS.register("water_walking", () -> new Potion(new EffectInstance(TerraEffectRegistry.WATER_WALKING.get(), 3600)));
    public static final RegistryObject<Potion> WATERWALKING_LONG = POTIONS.register("water_walking_long", () -> new Potion("water_walking", new EffectInstance(TerraEffectRegistry.WATER_WALKING.get(), 9600)));
    public static final RegistryObject<Potion> WATERWALKING_STRONG = POTIONS.register("water_walking_strong", () -> new Potion("lava_walking", new EffectInstance(TerraEffectRegistry.WATER_WALKING.get(), 1800, 1)));

    public static final RegistryObject<Potion> MANA_BURST_LESSER = POTIONS.register("mana_burst_lesser", () -> new Potion("mana_burst_lesser", new EffectInstance(TerraEffectRegistry.MANA_BURST.get(), 1)));
    public static final RegistryObject<Potion> MANA_BURST = POTIONS.register("mana_burst", () -> new Potion(new EffectInstance(TerraEffectRegistry.MANA_BURST.get(), 1, 1)));
    public static final RegistryObject<Potion> MANA_BURST_GREATER = POTIONS.register("mana_burst_greater", () -> new Potion("mana_burst_greater", new EffectInstance(TerraEffectRegistry.MANA_BURST.get(), 1, 2)));
    public static final RegistryObject<Potion> MANA_BURST_SUPER = POTIONS.register("mana_burst_super", () -> new Potion("mana_burst_super", new EffectInstance(TerraEffectRegistry.MANA_BURST.get(), 1, 3)));

    public static final RegistryObject<Potion> HEALTH_MANA_BURST_LESSER = POTIONS.register("health_mana_burst_lesser", () -> new Potion("health_mana_burst_lesser", new EffectInstance(TerraEffectRegistry.MANA_BURST.get(), 1), new EffectInstance(Effects.INSTANT_HEALTH, 1)));
    public static final RegistryObject<Potion> HEALTH_MANA_BURST = POTIONS.register("health_mana_burst", () -> new Potion(new EffectInstance(TerraEffectRegistry.MANA_BURST.get(), 1, 1), new EffectInstance(Effects.INSTANT_HEALTH, 1, 1)));
    public static final RegistryObject<Potion> HEALTH_MANA_BURST_STRANGE = POTIONS.register("health_mana_burst_strange", () -> new Potion("health_mana_burst_strange", new EffectInstance(TerraEffectRegistry.MANA_BURST.get(), 1, 4), new EffectInstance(Effects.INSTANT_HEALTH, 1)));
}
