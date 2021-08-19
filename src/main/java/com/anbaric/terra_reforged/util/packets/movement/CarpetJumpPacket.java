package com.anbaric.terra_reforged.util.packets.movement;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CarpetJumpPacket
{
    private boolean setGravity;

    public CarpetJumpPacket(boolean settingGravity)
    {
        this.setGravity = settingGravity;
    }

    public static CarpetJumpPacket decode(PacketBuffer buffer)
    {
        return new CarpetJumpPacket(buffer.readBoolean());
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeBoolean(setGravity);
    }

    public void handle(Supplier<NetworkEvent.Context> context)
    {
        ServerPlayerEntity player = context.get().getSender();
        if (player != null)
        {
            context.get().enqueueWork(() ->
            {
                if (setGravity)
                {
                    player.setDeltaMovement(player.getDeltaMovement().x() * 1.1, 0, player.getDeltaMovement().z() * 1.1);
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