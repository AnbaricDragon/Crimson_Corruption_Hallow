package com.anbaric.terra_reforged.effects;

import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

import java.util.UUID;

public class TerraEffectLoomingDeath extends Effect
{
    int chances = 3;

    public TerraEffectLoomingDeath(EffectType typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        return duration >= 0;
    }

    @Override
    public void performEffect(LivingEntity entity, int amplifier)
    {
        if (entity instanceof BeeEntity)
        {
            BeeEntity bee = (BeeEntity) entity;
            if (bee.hasStung())
            {
                bee.remove();
            }
            else
            {
                chances--;
                bee.addPotionEffect(new EffectInstance(TerraEffectRegistry.LOOMING_DEATH.get(), 20, 0, false, false));
            }
        }
        else if (!entity.getUniqueID().equals(UUID.fromString("f648da61-7d7c-449b-9fd7-05fa8eac0b0f")))
        {
            entity.addPotionEffect(new EffectInstance(Effects.WITHER, 999999, 1, false, true));
        }
        else
        {
            entity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 20*60, 5, false, false));
        }
    }
}
