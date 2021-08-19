package com.anbaric.terra_reforged.util.packets.movement;

import com.anbaric.terra_reforged.util.events.TerraJumpEvent;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class TsunamiJumpPacket
{
    public TsunamiJumpPacket()
    {
    }

    public TsunamiJumpPacket(PacketBuffer buffer)
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
                int jumpModifier = 0;
                if (CurioHandler.hasBauble(player, TerraItemRegistry.BALLOON_RED.get())) { jumpModifier++; }
                if (CurioHandler.hasBauble(player, TerraTagRegistry.FROG_HIGH_JUMPERS)) { jumpModifier++; }
                if (CurioHandler.hasBauble(player, TerraTagRegistry.CLOUD_HIGH_JUMPERS)) { jumpModifier++; }
                if (CurioHandler.hasBauble(player, TerraTagRegistry.BLIZZARD_HIGH_JUMPERS)) { jumpModifier++; }
                if (CurioHandler.hasBauble(player, TerraTagRegistry.SANDSTORM_HIGH_JUMPERS)) { jumpModifier++; }
                if (CurioHandler.hasBauble(player, TerraTagRegistry.HONEY_HIGH_JUMPERS)) { jumpModifier++; }
                if (CurioHandler.hasBauble(player, TerraTagRegistry.TSUNAMI_HIGH_JUMPERS)) { jumpModifier++; }
                if (CurioHandler.hasBauble(player, TerraTagRegistry.FART_HIGH_JUMPERS)) { jumpModifier++; }

                TerraJumpEvent.jump(player, jumpModifier);
                player.playSound(SoundEvents.AMBIENT_UNDERWATER_EXIT, 1, 0.9F + player.getRandom().nextFloat() * 0.2F);
                for (int i = 0; i < 160; ++i)
                {
                    double motionX = player.getRandom().nextGaussian() * 0.4;
                    double motionY = player.getRandom().nextGaussian() * 0.2 + 0.20;
                    double motionZ = player.getRandom().nextGaussian() * 0.4;
                    player.getLevel().sendParticles(ParticleTypes.FALLING_WATER, player.getX(), player.getY(), player.getZ(), 1, motionX, motionY, motionZ, 0.15);
                }
            });
        }
        context.get().setPacketHandled(true);
    }
}