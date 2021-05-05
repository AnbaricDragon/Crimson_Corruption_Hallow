package com.anbaric.terra_reforged.util.packets;

import com.anbaric.terra_reforged.util.events.TerraJumpEvent;
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

public class BlizzardJumpPacket
{
    public BlizzardJumpPacket(PacketBuffer buffer)
    {
    }

    public BlizzardJumpPacket()
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
                AtomicInteger jumpModifier = new AtomicInteger(0);
                CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem() == TerraItemRegistry.BALLOON_RED.get(), player).ifPresent(found -> jumpModifier.getAndIncrement());
                CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().is(TerraTagRegistry.CLOUD_HIGH_JUMPERS), player).ifPresent(found -> jumpModifier.getAndIncrement());
                CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().is(TerraTagRegistry.BLIZZARD_HIGH_JUMPERS), player).ifPresent(found -> jumpModifier.getAndIncrement());
                CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().is(TerraTagRegistry.SANDSTORM_HIGH_JUMPERS), player).ifPresent(found -> jumpModifier.getAndIncrement());
                CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().is(TerraTagRegistry.HONEY_HIGH_JUMPERS), player).ifPresent(found -> jumpModifier.getAndIncrement());
                CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().is(TerraTagRegistry.TSUNAMI_HIGH_JUMPERS), player).ifPresent(found -> jumpModifier.getAndIncrement());
                CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().is(TerraTagRegistry.FART_HIGH_JUMPERS), player).ifPresent(found -> jumpModifier.getAndIncrement());

                TerraJumpEvent.jump(player, jumpModifier.get());
                player.playSound(SoundEvents.SNOW_FALL, 1, 0.9F + player.getRandom().nextFloat() * 0.2F);
                for (int i = 0; i < 20; ++i)
                {
                    double motionX = player.getRandom().nextGaussian() * 0.02;
                    double motionY = player.getRandom().nextGaussian() * 0.02 + 0.20;
                    double motionZ = player.getRandom().nextGaussian() * 0.02;
                    player.getLevel().sendParticles(ParticleTypes.ITEM_SNOWBALL, player.getX() + (player.getRandom().nextFloat() - 0.5), player.getY(), player.getZ() + (player.getRandom().nextFloat() - 0.5), 1, motionX, motionY, motionZ, 0.15);
                }
            });
        }
        context.get().setPacketHandled(true);
    }
}