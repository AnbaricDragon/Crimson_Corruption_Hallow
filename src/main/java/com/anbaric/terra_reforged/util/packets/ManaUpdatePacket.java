package com.anbaric.terra_reforged.util.packets;

import com.anbaric.terra_reforged.capabilities.mana.IPlayerMana;
import com.anbaric.terra_reforged.util.handlers.ClientPacketHandler;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaUpdatePacket
{
    public final int entityId;
    public final CompoundNBT tag;

    public ManaUpdatePacket(int entityId, CompoundNBT tag)
    {
        this.entityId = entityId;
        this.tag = tag;
    }

    public ManaUpdatePacket(int entityId, IPlayerMana holder)
    {
        this(entityId, holder.serializeNBT());
    }

    public void write(PacketBuffer buf)
    {
        buf.writeInt(entityId);
        buf.writeNbt(tag);
    }

    public static ManaUpdatePacket read(PacketBuffer buf)
    {
        return new ManaUpdatePacket(buf.readInt(), buf.readNbt());
    }

    public void handle(Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> ClientPacketHandler.handleManaStatus(this));
        ctx.get().setPacketHandled(true);
    }
}