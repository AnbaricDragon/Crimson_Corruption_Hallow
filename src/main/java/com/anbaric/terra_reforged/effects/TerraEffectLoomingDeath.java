package com.anbaric.terra_reforged.effects;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class TerraEffectLoomingDeath extends MobEffect
{
    int chances = 3;

    public TerraEffectLoomingDeath(MobEffectCategory typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier)
    {
        return duration >= 0;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier)
    {
        if (entity instanceof Bee)
        {
            Bee bee = (Bee) entity;
            if (bee.hasStung() || chances == 0)
            {
                bee.remove(Entity.RemovalReason.UNLOADED_TO_CHUNK);
            }
            else
            {
                chances--;
                bee.addEffect(new MobEffectInstance(TerraEffectRegistry.LOOMING_DEATH.get(), 20, 0, false, false));
            }
        }
        else if (entity instanceof Player)
        {
            Player player = (Player) entity;
            if (!Reference.isAnbaric(player))
            {
                player.addEffect(new MobEffectInstance(MobEffects.WITHER, 999999, 1, false, true));
            }
            else
            {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20*60, 5, false, false));
            }
        }
    }
}
