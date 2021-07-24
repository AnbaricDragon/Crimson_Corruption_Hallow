package com.anbaric.terra_reforged.effects;

import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;

import javax.annotation.Nullable;

public class TerraEffectInvincibility extends Effect
{
    public TerraEffectInvincibility(EffectType typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        return true;
    }

    @Override
    public void performEffect(LivingEntity entity, int amplifier)
    {
        if (entity instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) entity;
            float duration = player.getActivePotionEffect(this).getDuration();
            player.abilities.allowFlying = duration > 0;
        }
    }
}
