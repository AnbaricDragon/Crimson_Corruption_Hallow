package com.anbaric.terra_reforged.effects;

import com.anbaric.terra_reforged.capabilities.mana.TerraCapabilityPlayerMana;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
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
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity livingEntity, int amplifier, double health)
    {
        PlayerEntity player = livingEntity instanceof PlayerEntity ? (PlayerEntity) livingEntity : null;
        if (player == null) { return; }

        player.getCapability(TerraCapabilityPlayerMana.PLAYER_MANA_CAPABILITY).ifPresent(cap ->
        {
            int mana = cap.getCurrentMana();
            int maxMana = (int) player.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).getValue();
            int claimedMana = 0;
            switch (amplifier)
            {
                case 0:
                    claimedMana = 50;

                case 1:
                    claimedMana = 100;

                case 2:
                    claimedMana = 200;

                case 3:
                    claimedMana = 300;

                default:
                    claimedMana = 0;
            }
            cap.setCurrentMana(Math.min(mana + claimedMana, maxMana));
        });
    }
}
