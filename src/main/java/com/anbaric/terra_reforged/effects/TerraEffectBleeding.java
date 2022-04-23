package com.anbaric.terra_reforged.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHealEvent;

public class TerraEffectBleeding extends MobEffect
{
    public TerraEffectBleeding(MobEffectCategory typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
        MinecraftForge.EVENT_BUS.addListener(this::preventRegen);
    }

    public void preventRegen(LivingHealEvent event)
    {
        LivingEntity entity = event.getEntityLiving();
        if (entity.hasEffect(this) && event.getAmount() <= 1.0F)
        {
            event.setCanceled(true);
        }
    }
}