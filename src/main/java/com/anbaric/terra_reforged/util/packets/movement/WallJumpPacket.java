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

import java.util.function.Supplier;

public class WallJumpPacket
{
    public WallJumpPacket(PacketBuffer buffer)
    {
    }

    public WallJumpPacket()
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

                TerraJumpEvent.wallJump(player, jumpModifier);
            });
        }
        context.get().setPacketHandled(true);
    }
}