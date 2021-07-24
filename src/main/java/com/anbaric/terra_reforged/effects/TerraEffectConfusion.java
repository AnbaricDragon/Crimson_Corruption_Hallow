package com.anbaric.terra_reforged.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;

public class TerraEffectConfusion extends Effect
{
    public TerraEffectConfusion(EffectType typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }
}
