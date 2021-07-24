package com.anbaric.terra_reforged.util.packets;

import com.anbaric.terra_reforged.util.events.TerraJumpEvent;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
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
                    player.setMotion(player.getMotion().getX() * 1.1, 0, player.getMotion().getZ() * 1.1);
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