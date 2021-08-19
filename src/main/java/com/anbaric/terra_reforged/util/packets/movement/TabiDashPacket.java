package com.anbaric.terra_reforged.util.packets.movement;

import com.anbaric.terra_reforged.util.events.TerraDashEvent;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class TabiDashPacket
{
    public TabiDashPacket(PacketBuffer buffer)
    {

    }

    public TabiDashPacket()
    {

    }

    public void encode(PacketBuffer buffer)
    {

    }

    public void handle(Supplier<NetworkEvent.Context> context)
    {
        ServerPlayerEntity player = context.get().getSender();
        if (player != null)
        {
            context.get().enqueueWork(() ->
            {
                TerraDashEvent.tabiDash(player);
            });
        }
        context.get().setPacketHandled(true);
    }
}