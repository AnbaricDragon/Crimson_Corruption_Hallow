package com.anbaric.terra_reforged.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class TerraEffectLoomingDeath extends Effect
{
    public TerraEffectLoomingDeath(EffectType typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        return duration <= 1;
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier)
    {
        entityLivingBaseIn.onKillCommand();
    }
}
