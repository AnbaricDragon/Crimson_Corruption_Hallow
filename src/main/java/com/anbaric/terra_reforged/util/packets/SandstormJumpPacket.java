package com.anbaric.terra_reforged.util.packets;

import com.anbaric.terra_reforged.util.events.TerraJumpEvent;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class SandstormJumpPacket
{
    public SandstormJumpPacket(PacketBuffer buffer)
    {
    }

    public SandstormJumpPacket()
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
                player.playSound(SoundEvents.BLOCK_SAND_STEP, 1, 0.9F + player.getRNG().nextFloat() * 0.2F);
                for (int i = 0; i < 20; ++i)
                {
                    double motionX = player.getRNG().nextGaussian() * 0.02;
                    double motionY = player.getRNG().nextGaussian() * 0.02 + 0.20;
                    double motionZ = player.getRNG().nextGaussian() * 0.02;
                    player.getServerWorld().spawnParticle(new BlockParticleData(ParticleTypes.FALLING_DUST, Blocks.SAND.getDefaultState()),player.getPosX() + (player.getRNG().nextFloat() - 0.5), player.getPosY(), player.getPosZ() + (player.getRNG().nextFloat() - 0.5), 1, motionX, motionY, motionZ, 0.15);
                }
            });
        }
        context.get().setPacketHandled(true);
    }
}