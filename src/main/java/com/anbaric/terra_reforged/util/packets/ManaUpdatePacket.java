package com.anbaric.terra_reforged.util.packets;

import com.anbaric.terra_reforged.capabilities.PlayerMana.PlayerManaClient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaUpdatePacket
{
    private final int currentMana;

    public ManaUpdatePacket(int currentMana)
    {
        this.currentMana = currentMana;
    }

    public ManaUpdatePacket(FriendlyByteBuf buf)
    {
        currentMana = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf)
    {
        buf.writeInt(currentMana);
    }

    public static ManaUpdatePacket read(FriendlyByteBuf buf)
    {
        return new ManaUpdatePacket(buf.readInt());
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> { PlayerManaClient.setCurrentMana(currentMana); });
        ctx.setPacketHandled(true);
        return true;
    }
}