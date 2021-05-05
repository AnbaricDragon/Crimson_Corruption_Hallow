package com.anbaric.terra_reforged.effects;

import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

public class TerraEffectLoomingDeath extends Effect
{
    int chances = 3;

    public TerraEffectLoomingDeath(EffectType typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier)
    {
        return duration <= 1;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier)
    {
        if (entity instanceof BeeEntity)
        {
            BeeEntity bee = (BeeEntity) entity;
            if (bee.hasStung() || chances == 0)
            {
                bee.remove();
            }
            else
            {
                chances--;
                bee.addEffect(new EffectInstance(TerraEffectRegistry.LOOMING_DEATH.get(), 20, 0, false, false));
            }
        }
    }
}
