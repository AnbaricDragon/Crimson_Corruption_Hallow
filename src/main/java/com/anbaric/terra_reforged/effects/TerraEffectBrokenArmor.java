package com.anbaric.terra_reforged.effects;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class TerraEffectBrokenArmor extends Effect
{
    public TerraEffectBrokenArmor(EffectType typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier)
    {
        return modifier.getAmount() + ((amplifier + 1) / modifier.getAmount());
    }
}
