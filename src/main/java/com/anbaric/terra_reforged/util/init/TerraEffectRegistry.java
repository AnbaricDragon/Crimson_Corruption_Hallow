package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.effects.*;
import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TerraEffectRegistry
{
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Reference.MODID);

    public static final RegistryObject<Effect> CHILLED = EFFECTS.register("chilled", () -> new TerraEffectChilled(EffectType.HARMFUL, 10859519).addAttributesModifier(Attributes.MOVEMENT_SPEED, "ae4302fc-42e8-4e9c-a081-1561cdf5de1c", -0.15F, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<Effect> LOOMING_DEATH = EFFECTS.register("looming_death", () -> new TerraEffectLoomingDeath(EffectType.HARMFUL, 0));
    public static final RegistryObject<Effect> BLEEDING = EFFECTS.register("bleeding", () -> new TerraEffectBleeding(EffectType.HARMFUL, 6684672));
    public static final RegistryObject<Effect> CURSED = EFFECTS.register("cursed", () -> new TerraEffectCursed(EffectType.HARMFUL, 0));
    public static final RegistryObject<Effect> CONFUSION = EFFECTS.register("confusion", () -> new TerraEffectConfusion(EffectType.HARMFUL, 11425210).addAttributesModifier(Attributes.MOVEMENT_SPEED, "5ba39c47-fe0b-4f6a-b7e0-b62361cf9f7d", -2.0F, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<Effect> BROKEN_ARMOR = EFFECTS.register("broken_armor", () -> new TerraEffectBrokenArmor(EffectType.HARMFUL, 0).addAttributesModifier(Attributes.ARMOR, "11984f64-8a3f-452d-92f8-45a7749acff5", -0.5, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<Effect> SILENCED = EFFECTS.register("silenced", () -> new TerraEffectSilenced(EffectType.HARMFUL, 0));

    public static final RegistryObject<Effect> HONEY = EFFECTS.register("honey", () -> new TerraEffectHoney(EffectType.BENEFICIAL, 16763392));
    public static final RegistryObject<Effect> MANA_REGEN = EFFECTS.register("mana_regen", () -> new TerraEffectManaRegen(EffectType.BENEFICIAL, 4666085));
    public static final RegistryObject<Effect> WATER_WALKING = EFFECTS.register("water_walking", () -> new TerraEffectWaterWalking(EffectType.BENEFICIAL, 3093221));
    public static final RegistryObject<Effect> MANA_BURST = EFFECTS.register("mana_burst", () -> new TerraEffectManaBurst(EffectType.BENEFICIAL, 4666085));

}
