package com.anbaric.terra_reforged.effects;

import com.anbaric.terra_reforged.capabilities.mana.TerraCapabilityPlayerMana;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.InstantEffect;

import javax.annotation.Nullable;

public class TerraEffectManaBurst extends InstantEffect
{
    public TerraEffectManaBurst(EffectType typeIn, int liquidColorIn)
    {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entity, int amplifier)
    {
        PlayerEntity player = entity instanceof PlayerEntity ? (PlayerEntity) entity : null;
        if (player == null) { return; }
        entity.getCapability(TerraCapabilityPlayerMana.PLAYER_MANA_CAPABILITY).ifPresent(cap ->
        {
            int mana = cap.getCurrentMana();
            int maxMana = (int) player.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).getValue();
            cap.setCurrentMana(Math.min((amplifier == 0 ? 50 : amplifier * 100) + mana, maxMana));
        });
    }

    @Override
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity livingEntity, int amplifier, double health)
    {
        PlayerEntity player = livingEntity instanceof PlayerEntity ? (PlayerEntity) livingEntity : null;
        if (player == null) { return; }

        player.getCapability(TerraCapabilityPlayerMana.PLAYER_MANA_CAPABILITY).ifPresent(cap ->
        {
            int mana = cap.getCurrentMana();
            int maxMana = (int) player.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).getValue();
            int claimedMana = amplifier == 0 ? 50 : amplifier * 100;
            if (source instanceof AreaEffectCloudEntity)
            {
                claimedMana /= 50;
            }
            cap.setCurrentMana(Math.min(mana + claimedMana, maxMana));
        });
    }
}
