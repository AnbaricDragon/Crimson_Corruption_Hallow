package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.multijump.TerraCapabilityMultiJump;
import com.anbaric.terra_reforged.util.handlers.NetworkHandler;
import com.anbaric.terra_reforged.util.packets.CloudJumpPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.UUID;


public class TerraJumpEvent
{
    @OnlyIn(Dist.CLIENT)
    private boolean hasReleasedJumpKey;

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    void onClientTick(TickEvent.ClientTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END)
        {
            ClientPlayerEntity player = Minecraft.getInstance().player;

            if (player != null)
            {
                if ((player.isOnGround() || player.isOnLadder()) && !player.isInWater())
                {
                    hasReleasedJumpKey = false;
                }
                else
                {
                    if (!player.movementInput.jump)
                    {
                        hasReleasedJumpKey = true;
                    }
                    else
                    {
                        if (!player.abilities.isFlying && hasReleasedJumpKey)
                        {
                            player.getCapability(TerraCapabilityMultiJump.MULTI_JUMP_CAPABILITY).ifPresent(cap ->
                            {
                                if (cap.getCanCloudJump())
                                {
                                    cap.setCanCloudJump(false);
                                    NetworkHandler.INSTANCE.sendToServer(new CloudJumpPacket());
                                    cloudJump(player);
                                    player.fallDistance = 0;
                                    player.playSound(SoundEvents.BLOCK_WOOL_FALL, 1, 0.9F + player.getRNG().nextFloat() * 0.2F);
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    public static void cloudJump(PlayerEntity player)
    {
        double upwardsMotion = 0.5;
        if (player.isPotionActive(Effects.JUMP_BOOST))
        {
            upwardsMotion += 0.1 * (player.getActivePotionEffect(Effects.JUMP_BOOST).getAmplifier() + 1);
        }
        upwardsMotion *= player.isSprinting() ? 1.5 : 1;
        Vector3d motion    = player.getMotion();

        player.setMotion(player.getMotion().add(0, upwardsMotion - motion.y, 0));

        player.isAirBorne = true;
        net.minecraftforge.common.ForgeHooks.onLivingJump(player);

        player.addStat(Stats.JUMP);
        player.addExhaustion(player.isSprinting() ? 0.2F : 0.05F);
    }
}
