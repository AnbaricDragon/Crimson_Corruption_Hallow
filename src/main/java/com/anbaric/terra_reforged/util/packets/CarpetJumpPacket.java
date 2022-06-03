package com.anbaric.terra_reforged.util.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CarpetJumpPacket
{
    public final boolean setGravity;

    public CarpetJumpPacket(boolean setGravity)
    {
        this.setGravity = setGravity;
    }

    public CarpetJumpPacket(FriendlyByteBuf buffer)
    {
        setGravity = buffer.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buffer)
    {
        buffer.writeBoolean(setGravity);
    }

    public static CarpetJumpPacket read(FriendlyByteBuf buf)
    {
        return new CarpetJumpPacket(buf.readBoolean());
    }

    public void handle(Supplier<NetworkEvent.Context> context)
    {
        ServerPlayer player = context.get().getSender();
        if (player != null)
        {
            context.get().enqueueWork(() -> {
                if (setGravity)
                {
                    player.setDeltaMovement(player.getDeltaMovement().x * 1.03, 0, player.getDeltaMovement().z * 1.03);
                    player.setNoGravity(setGravity);
                }
                else
                {
                    player.setNoGravity(false);
                }
            });
        }
        context.get().setPacketHandled(true);
    }
}