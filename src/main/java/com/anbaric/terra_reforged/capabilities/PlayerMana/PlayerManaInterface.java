package com.anbaric.terra_reforged.capabilities.PlayerMana;

import com.anbaric.terra_reforged.util.packets.ManaUpdatePacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.INBTSerializable;

public interface PlayerManaInterface extends INBTSerializable<CompoundTag>
{
    int getCurrentMana();
    void setCurrentMana(int mana);

    int getManaCrystalsUsed();
    void setManaCrystalsUsed(int crystalsUsed);

    boolean spendMana(int maxMana, double discount, int cost);

    void updateTracking();
    ManaUpdatePacket createUpdatePacket();
    void sendPlayerUpdatePacket(ServerPlayer serverPlayer);
}
