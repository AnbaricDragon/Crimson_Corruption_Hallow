package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.effects.*;
import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TerraEffectRegistry
{
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Reference.MODID);

    public static final RegistryObject<MobEffect> INVINCIBILITY = EFFECTS.register("invincibility", () -> new TerraEffectInvincibility(MobEffectCategory.HARMFUL, 10859519).addAttributeModifier(Attributes.MOVEMENT_SPEED, "ae4302fc-42e8-4e9c-a081-1561cdf5de1c", -0.15F, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<MobEffect> LOOMING_DEATH = EFFECTS.register("looming_death", () -> new TerraEffectLoomingDeath(MobEffectCategory.HARMFUL, 0));
    public static final RegistryObject<MobEffect> BLEEDING = EFFECTS.register("bleeding", () -> new TerraEffectBleeding(MobEffectCategory.HARMFUL, 6684672));
    public static final RegistryObject<MobEffect> CURSED = EFFECTS.register("cursed", () -> new TerraEffectCursed(MobEffectCategory.HARMFUL, 0));
    public static final RegistryObject<MobEffect> CONFUSION = EFFECTS.register("confusion", () -> new TerraEffectConfusion(MobEffectCategory.HARMFUL, 11425210).addAttributeModifier(Attributes.MOVEMENT_SPEED, "5ba39c47-fe0b-4f6a-b7e0-b62361cf9f7d", -2.0F, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<MobEffect> BROKEN_ARMOR = EFFECTS.register("broken_armor", () -> new TerraEffectBrokenArmor(MobEffectCategory.HARMFUL, 0).addAttributeModifier(Attributes.ARMOR, "11984f64-8a3f-452d-92f8-45a7749acff5", -0.5, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<MobEffect> SILENCED = EFFECTS.register("silenced", () -> new TerraEffectSilenced(MobEffectCategory.HARMFUL, 0));
    public static final RegistryObject<MobEffect> POTION_SICKNESS = EFFECTS.register("potion_sickness", () -> new TerraEffectPotionSickness(MobEffectCategory.HARMFUL, 0));

    public static final RegistryObject<MobEffect> HONEY = EFFECTS.register("honey", () -> new TerraEffectHoney(MobEffectCategory.BENEFICIAL, 16763392));
    public static final RegistryObject<MobEffect> MANA_REGEN = EFFECTS.register("mana_regen", () -> new TerraEffectManaRegen(MobEffectCategory.BENEFICIAL, 4666085));
    public static final RegistryObject<MobEffect> WATER_WALKING = EFFECTS.register("water_walking", () -> new TerraEffectWaterWalking(MobEffectCategory.BENEFICIAL, 3093221));
    public static final RegistryObject<MobEffect> MANA_BURST = EFFECTS.register("mana_burst", () -> new TerraEffectManaBurst(MobEffectCategory.BENEFICIAL, 4666085));
}
