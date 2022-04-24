package com.anbaric.terra_reforged.util.packets;

import com.anbaric.terra_reforged.util.events.TerraJumpEvent;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class WallJumpPacket
{
    public final int jumpModifier;
    public WallJumpPacket(int jumpModifier)
    {
        this.jumpModifier = jumpModifier;
    }

    public WallJumpPacket(FriendlyByteBuf buffer)
    {
        jumpModifier = buffer.readInt();
    }

    public void toBytes(FriendlyByteBuf buffer)
    {
        buffer.writeInt(jumpModifier);
    }

    public static WallJumpPacket read(FriendlyByteBuf buf)
    {
        return new WallJumpPacket(buf.readInt());
    }

    public void handle(Supplier<NetworkEvent.Context> context)
    {
        ServerPlayer player = context.get().getSender();
        if (player != null)
        {
            context.get().enqueueWork(() ->
            {
                TerraJumpEvent.wallJump(player, jumpModifier);
            });
        }
        context.get().setPacketHandled(true);
    }
}