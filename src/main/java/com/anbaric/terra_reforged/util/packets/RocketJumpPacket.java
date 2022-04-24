package com.anbaric.terra_reforged.util.packets;

import com.anbaric.terra_reforged.util.events.TerraJumpEvent;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RocketJumpPacket
{
    public final int jumpModifier;
    public RocketJumpPacket(int jumpModifier)
    {
        this.jumpModifier = jumpModifier;
    }

    public RocketJumpPacket(FriendlyByteBuf buffer)
    {
        jumpModifier = buffer.readInt();
    }

    public void toBytes(FriendlyByteBuf buffer)
    {
        buffer.writeInt(jumpModifier);
    }

    public static RocketJumpPacket read(FriendlyByteBuf buf)
    {
        return new RocketJumpPacket(buf.readInt());
    }

    public void handle(Supplier<NetworkEvent.Context> context)
    {
        ServerPlayer player = context.get().getSender();
        if (player != null)
        {
            context.get().enqueueWork(() ->
            {
                TerraJumpEvent.jump(player, jumpModifier);
                player.playSound(SoundEvents.WOOL_STEP, 1, 0.9F + player.getRandom().nextFloat() * 0.2F);
                for (int i = 0; i < 20; ++i)
                {
                    double motionX = player.getRandom().nextGaussian() * 0.2;
                    double motionY = player.getRandom().nextGaussian() * 0.2 + 0.20;
                    double motionZ = player.getRandom().nextGaussian() * 0.2;
                    player.getLevel().sendParticles(ParticleTypes.POOF, player.position().x, player.position().y, player.position().z, 1, motionX, motionY, motionZ, 0.15);
                }
            });
        }
        context.get().setPacketHandled(true);
    }
}