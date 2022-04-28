package com.anbaric.terra_reforged.capabilities.player_mana;

import com.anbaric.terra_reforged.util.handlers.ChannelHandler;
import dev._100media.capabilitysyncer.core.PlayerCapability;
import dev._100media.capabilitysyncer.network.EntityCapabilityStatusPacket;
import dev._100media.capabilitysyncer.network.SimpleEntityCapabilityStatusPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.simple.SimpleChannel;

public class TerraManaCapability extends PlayerCapability
{
    private int currentMana = 50;
    private int manaCrystalsUsed = 0;

    protected TerraManaCapability(Player player)
    {
        super(player);
    }

    public int getCurrentMana()
    {
        return currentMana;
    }

    public int getManaCrystalsUsed() { return manaCrystalsUsed; }

    public void setCurrentMana(int currentMana, boolean sync)
    {
        this.currentMana = currentMana;
        if (sync)
        {
            // Send an update packet to all tracking clients
            this.updateTracking();
        }
    }

    public void setCurrentMana(int currentMana)
    {
        setCurrentMana(currentMana, true);
    }

    public void setManaCrystalsUsed(int manaCrystalsUsed)
    {
        this.manaCrystalsUsed = manaCrystalsUsed;
    }

    public boolean spendMana(double discount, int cost)
    {
        int trueCost = (int) Math.floor(cost * discount);
        if (trueCost < this.currentMana)
        {
            setCurrentMana(currentMana -= trueCost, true);
            return true;
        }
        return false;
    }

    @Override
    public CompoundTag serializeNBT(boolean savingToDisk)
    {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("currentMana", this.currentMana);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt, boolean readingFromDisk)
    {
        if (nbt.contains("currentMana", Tag.TAG_INT)) { this.currentMana = nbt.getInt("ExampleInt"); }
        else { this.currentMana = 50; }
    }

    @Override
    public EntityCapabilityStatusPacket createUpdatePacket()
    {
        return new SimpleEntityCapabilityStatusPacket(this.player.getId(), TerraMana.TERRA_MANA_CAPABILITY_RL, this);
    }

    @Override
    public SimpleChannel getNetworkChannel()
    {
        return ChannelHandler.INSTANCE;
    }
}
