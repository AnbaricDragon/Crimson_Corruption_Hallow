package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TerraPotionRegistry
{
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, Reference.MODID);

    public static final RegistryObject<Potion> WATERWALKING = POTIONS.register("water_walking", () -> new Potion(new EffectInstance(TerraEffectRegistry.WATER_WALKING.get(), 3600)));
    public static final RegistryObject<Potion> WATERWALKING_LONG = POTIONS.register("water_walking_long", () -> new Potion("water_walking", new EffectInstance(TerraEffectRegistry.WATER_WALKING.get(), 9600)));
    public static final RegistryObject<Potion> WATERWALKING_STRONG = POTIONS.register("water_walking_strong", () -> new Potion("water_walking", new EffectInstance(TerraEffectRegistry.WATER_WALKING.get(), 1800, 1)));
}
