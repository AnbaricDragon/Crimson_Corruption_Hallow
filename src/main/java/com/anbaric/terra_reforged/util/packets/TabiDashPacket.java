package com.anbaric.terra_reforged.util.packets;

import com.anbaric.terra_reforged.util.events.TerraDashEvent;
import com.anbaric.terra_reforged.util.events.TerraJumpEvent;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TabiDashPacket
{
    public TabiDashPacket()
    {

    }

    public TabiDashPacket(FriendlyByteBuf buffer)
    {

    }

    public void toBytes(FriendlyByteBuf buffer)
    {

    }

    public static TabiDashPacket read(FriendlyByteBuf buf)
    {
        return new TabiDashPacket();
    }

    public void handle(Supplier<NetworkEvent.Context> context)
    {
        ServerPlayer player = context.get().getSender();
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
