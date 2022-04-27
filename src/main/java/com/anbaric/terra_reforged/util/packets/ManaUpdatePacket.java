package com.anbaric.terra_reforged.util.packets;

import com.anbaric.terra_reforged.capabilities.PlayerMana.PlayerMana;
import com.anbaric.terra_reforged.capabilities.PlayerMana.PlayerManaClient;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaUpdatePacket
{
    public final CompoundTag tag;

    public ManaUpdatePacket(CompoundTag tag)
    {
        this.tag = tag;
    }

    public ManaUpdatePacket(FriendlyByteBuf buf)
    {
        tag = buf.readNbt();
    }

    public void toBytes(FriendlyByteBuf buf)
    {
        buf.writeNbt(tag);
    }

    public static ManaUpdatePacket read(FriendlyByteBuf buf)
    {
        return new ManaUpdatePacket(buf.readNbt());
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            Player player = ctx.getSender();
            player.getCapability(PlayerMana.PLAYER_MANA_CAPABILITY).ifPresent(cap ->
            {
                PlayerManaClient.setCurrentMana(cap.getCurrentMana());
                PlayerManaClient.setMaxMana(cap.getCurrentMana());
            });
        });
        ctx.setPacketHandled(true);
        return true;
    }
}