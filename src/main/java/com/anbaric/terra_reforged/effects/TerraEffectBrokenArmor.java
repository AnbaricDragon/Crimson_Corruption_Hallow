package com.anbaric.terra_reforged.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class TerraEffectBrokenArmor extends MobEffect
{
    public TerraEffectBrokenArmor(MobEffectCategory typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public double getAttributeModifierValue(int amplifier, AttributeModifier modifier)
    {
        return modifier.getAmount() + ((amplifier + 1) / modifier.getAmount());
    }

}
