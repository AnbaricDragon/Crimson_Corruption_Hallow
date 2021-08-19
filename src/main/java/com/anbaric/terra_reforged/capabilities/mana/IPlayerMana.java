package com.anbaric.terra_reforged.capabilities.mana;

import com.anbaric.terra_reforged.util.packets.ManaUpdatePacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IPlayerMana extends INBTSerializable<CompoundNBT>
{
    int getCurrentMana();
    void setCurrentMana(int mana);

    int getManaCrystalsUsed();
    void setManaCrystalsUsed(int crystalsUsed);

    boolean spendMana(int maxMana, double discount, int cost);

    void updateTracking();
    ManaUpdatePacket createUpdatePacket();
    void sendPlayerUpdatePacket(ServerPlayerEntity serverPlayer);
}