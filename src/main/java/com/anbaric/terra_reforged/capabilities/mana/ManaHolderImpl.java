package com.anbaric.terra_reforged.capabilities.mana;

import com.anbaric.terra_reforged.util.handlers.NetworkHandler;
import com.anbaric.terra_reforged.util.packets.ManaUpdatePacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.network.PacketDistributor;

public class ManaHolderImpl implements IPlayerMana
{
    private final PlayerEntity player;
    private int currentMana = 0;
    private int manaCrystals = 0;

    public ManaHolderImpl(PlayerEntity player)
    {
        this.player = player;
    }

    @Override
    public int getCurrentMana()
    {
        return currentMana;
    }

    @Override
    public void setCurrentMana(int newMana)
    {
        this.currentMana = newMana;
        this.updateTracking();
    }

    @Override
    public int getManaCrystalsUsed()
    {
        return manaCrystals;
    }

    @Override
    public void setManaCrystalsUsed(int newManaCrystals)
    {
        this.manaCrystals = newManaCrystals;
        this.updateTracking();
    }

    @Override
    public boolean spendMana(int maxMana, double discount, int cost)
    {
        int trueCost = (int) Math.floor(cost * discount);
        if (trueCost < this.currentMana)
        {
            this.currentMana -= trueCost;
            return true;
        }
        return false;
    }

    @Override
    public void updateTracking()
    {
        if (player.level.isClientSide)
        {
            return;
        }
        NetworkHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), this.createUpdatePacket());
    }

    public ManaUpdatePacket createUpdatePacket()
    {
        return new ManaUpdatePacket(player.getId(), this);
    }

    @Override
    public void sendPlayerUpdatePacket(ServerPlayerEntity serverPlayer)
    {
        NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), this.createUpdatePacket());
    }

    @Override
    public CompoundNBT serializeNBT()
    {
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("current_mana", currentMana);
        tag.putInt("mana_crystals", manaCrystals);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundNBT tag)
    {
        this.currentMana = tag.getInt("current_mana");
        this.manaCrystals = tag.getInt("mana_crystals");
    }
}
