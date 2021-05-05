package com.anbaric.terra_reforged.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class TerraEffectHoney extends Effect
{
    public TerraEffectHoney(EffectType typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier)
    {
        int k = 50 >> amplifier;
        if (k > 0)
        {
            return duration % k == 0;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier)
    {
        if (entity.getHealth() < entity.getMaxHealth())
        {
            entity.heal(1.0F);
        }
    }
}