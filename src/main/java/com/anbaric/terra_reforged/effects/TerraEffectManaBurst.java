package com.anbaric.terra_reforged.effects;

import com.anbaric.terra_reforged.capabilities.PlayerMana.PlayerMana;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;

public class TerraEffectManaBurst extends InstantenousMobEffect
{
    public TerraEffectManaBurst(MobEffectCategory typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier)
    {
        Player player = entity instanceof Player ? (Player) entity : null;
        if (player == null) { return; }
        player.getCapability(PlayerMana.TERRA_MANA_CAPABILITY).ifPresent(cap ->
        {
            int mana = cap.getCurrentMana();
            int maxMana = (int) player.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).getValue();
            cap.setCurrentMana(Math.min((amplifier == 0 ? 50 : amplifier * 100) + mana, maxMana));
        });
    }

    @Override
    public void applyInstantenousEffect(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity livingEntity, int amplifier, double health)
    {
        Player player = livingEntity instanceof Player ? (Player) livingEntity : null;
        if (player == null) { return; }

        player.getCapability(PlayerMana.TERRA_MANA_CAPABILITY).ifPresent(cap ->
        {
            int mana = cap.getCurrentMana();
            int maxMana = (int) player.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).getValue();
            int claimedMana = amplifier == 0 ? 50 : amplifier * 100;
            if (source instanceof AreaEffectCloud)
            {
                claimedMana /= 2;
            }
            cap.setCurrentMana(Math.min(mana + claimedMana, maxMana));
        });
    }
}
