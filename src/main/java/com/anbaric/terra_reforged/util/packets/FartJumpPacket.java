package com.anbaric.terra_reforged.util.packets;

import com.anbaric.terra_reforged.util.events.TerraJumpEvent;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class FartJumpPacket
{
    public FartJumpPacket(PacketBuffer buffer)
    {
    }

    public FartJumpPacket()
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
            context.get().enqueueWork(() -> {
                player.fallDistance = 0;
                TerraJumpEvent.jump(player);
                player.playSound(SoundEvents.BLOCK_WOOL_FALL, 1, 0.9F + player.getRNG().nextFloat() * 0.2F);
                for (int i = 0; i < 20; ++i)
                {
                    double motionX = player.getRNG().nextGaussian() * 0.02;
                    double motionY = player.getRNG().nextGaussian() * 0.02 + 0.20;
                    double motionZ = player.getRNG().nextGaussian() * 0.02;
                    player.getServerWorld().spawnParticle(ParticleTypes.POOF, player.getPosX(), player.getPosY(), player.getPosZ(), 1, motionX, motionY, motionZ, 0.15);
                }
            });
        }
        context.get().setPacketHandled(true);
    }
}