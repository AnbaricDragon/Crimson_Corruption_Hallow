package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.effects.TerraEffectChilled;
import com.anbaric.terra_reforged.effects.TerraEffectHoney;
import com.anbaric.terra_reforged.effects.TerraEffectInvincibility;
import com.anbaric.terra_reforged.effects.TerraEffectLoomingDeath;
import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.block.Block;
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

    public static final RegistryObject<Effect> CHILLED = EFFECTS.register("chilled", () -> new TerraEffectChilled(EffectType.HARMFUL, 10859519).addAttributesModifier(Attributes.MOVEMENT_SPEED, "ae4302fc-42e8-4e9c-a081-1561cdf5de1c", -0.20F, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static final RegistryObject<Effect> LOOMING_DEATH = EFFECTS.register("looming_death", () -> new TerraEffectLoomingDeath(EffectType.HARMFUL, 0));
    public static final RegistryObject<Effect> INVINCIBILITY = EFFECTS.register("invincibility", () -> new TerraEffectInvincibility(EffectType.BENEFICIAL, 12289024));
    public static final RegistryObject<Effect> HONEY = EFFECTS.register("honey", () -> new TerraEffectHoney(EffectType.BENEFICIAL, 16763392));
}
