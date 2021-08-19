package com.anbaric.terra_reforged.capabilities.mana;

import net.minecraft.entity.player.PlayerEntity;

public interface ITerraManaAwarePlayer
{
    int getCurrentMana();
    int getManaCrystals();

    static ITerraManaAwarePlayer get(PlayerEntity player)
    {
        return (ITerraManaAwarePlayer) player;
    }

    default PlayerEntity toVanilla()
    {
        return (PlayerEntity) this;
    }
}