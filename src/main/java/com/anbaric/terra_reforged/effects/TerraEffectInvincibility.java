package com.anbaric.terra_reforged.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class TerraEffectInvincibility extends MobEffect
{
    public TerraEffectInvincibility(MobEffectCategory typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier)
    {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier)
    {
        if (entity instanceof Player)
        {
            Player player = (Player) entity;
            float duration = player.getEffect(this).getDuration();
            player.getAbilities().flying = duration > 0;
        }
    }
}
