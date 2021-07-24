package com.anbaric.terra_reforged.capabilities.player;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IPlayerMana
{
    int getCurrentMana();
    void setCurrentMana(int mana);

    boolean spendMana(int maxMana, double discount, int cost);
}