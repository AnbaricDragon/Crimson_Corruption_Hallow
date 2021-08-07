package com.anbaric.terra_reforged.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHealEvent;

public class TerraEffectBleeding extends Effect
{
    public TerraEffectBleeding(EffectType typeIn, int liquidColorIn)
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