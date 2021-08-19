package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.capabilities.mana.ITerraManaAwarePlayer;
import com.anbaric.terra_reforged.capabilities.mana.TerraCapabilityPlayerMana;
import com.anbaric.terra_reforged.capabilities.mana.IPlayerMana;
import net.minecraft.entity.player.PlayerEntity;

public class PlayerManaHandler
{
    public static IPlayerMana getManaHolder(PlayerEntity player) {
        return TerraCapabilityPlayerMana.getManaHolder(player).orElse(null);
    }

    public static IPlayerMana getManaHolder(ITerraManaAwarePlayer player) {
        return getManaHolder(player.toVanilla());
    }
}

