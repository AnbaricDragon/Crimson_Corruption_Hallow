package com.anbaric.terra_reforged.effects;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.UUID;

public class TerraEffectChilled extends Effect
{
    public TerraEffectChilled(EffectType typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public Effect addAttributeModifier(Attribute attributeIn, String uuid, double amount, AttributeModifier.Operation operation)
    {
        AttributeModifier attributemodifier = new AttributeModifier(UUID.fromString(uuid), this::getDescriptionId, amount, operation);
        super.attributeModifiers.put(attributeIn, attributemodifier);
        return this;
    }
}
