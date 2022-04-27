package com.anbaric.terra_reforged.capabilities.PlayerMana;

import com.anbaric.terra_reforged.util.handlers.ChannelHandler;
import com.anbaric.terra_reforged.util.packets.ManaUpdatePacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class PlayerManaImplementation implements PlayerManaInterface {

    private static final String STORED_PLAYER_MANA = "player_mana";
    private static final String USED_MANA_CRYSTALS = "used_crystals";

    private int currentMana = 0;
    private int usedCrystals = 0;

    public PlayerManaImplementation() {}

    @Override
    public int getCurrentMana()
    {
        return this.currentMana;
    }

    @Override
    public void setCurrentMana(int mana)
    {
        this.currentMana = mana;
        createUpdatePacket();
    }

    @Override
    public int getManaCrystalsUsed()
    {
        return usedCrystals;
    }

    @Override
    public void setManaCrystalsUsed(int crystalsUsed)
    {
        this.usedCrystals = crystalsUsed;
    }

    @Override
    public boolean spendMana(int maxMana, double discount, int cost)
    {
        int trueCost = (int) Math.floor(cost * discount);
        if (trueCost < this.currentMana)
        {
            setCurrentMana(currentMana -= trueCost);
            return true;
        }
        return false;
    }

    @Override
    public void updateTracking()
    {

    }

    @Override
    public ManaUpdatePacket createUpdatePacket()
    {
        return new ManaUpdatePacket(getCurrentMana());
    }

    @Override
    public void sendPlayerUpdatePacket(ServerPlayer serverPlayer)
    {
        ChannelHandler.sendToPlayer(createUpdatePacket(), serverPlayer);
    }

    @Override
    public CompoundTag serializeNBT()
    {
        final CompoundTag tag = new CompoundTag();
        tag.putInt(STORED_PLAYER_MANA, currentMana);
        tag.putInt(USED_MANA_CRYSTALS, usedCrystals);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag)
    {
        this.currentMana = tag.getInt(STORED_PLAYER_MANA);
        this.usedCrystals = tag.getInt(USED_MANA_CRYSTALS);
    }
}