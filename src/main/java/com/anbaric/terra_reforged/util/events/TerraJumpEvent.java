package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.handlers.NetworkHandler;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import com.anbaric.terra_reforged.util.packets.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraJumpEvent
{
    @OnlyIn(Dist.CLIENT)
    private boolean jumpState;
    @OnlyIn(Dist.CLIENT)
    private int rocketState;

    @OnlyIn(Dist.CLIENT)
    private boolean lastJumpState;

    @OnlyIn(Dist.CLIENT)
    private boolean hasfirstJump;

    @OnlyIn(Dist.CLIENT)
    private boolean hasCloudJump;
    @OnlyIn(Dist.CLIENT)
    private boolean hasBlizzardJump;
    @OnlyIn(Dist.CLIENT)
    private boolean hasSandstormJump;
    @OnlyIn(Dist.CLIENT)
    private boolean hasTsunamiJump;
    @OnlyIn(Dist.CLIENT)
    private boolean hasFartJump;

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    void onClientTick(TickEvent.ClientTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END)
        {
            ClientPlayerEntity player = Minecraft.getInstance().player;

            if (player != null)
            {
                if (player.isOnGround())
                {
                    hasfirstJump = true;
                    rocketState = CurioHandler.hasBauble(player, TerraTagRegistry.ROCKET_JUMPERS) ? 32 : 0;
                    hasFartJump = true;
                    hasTsunamiJump = true;
                    hasSandstormJump = true;
                    hasBlizzardJump = true;
                    hasCloudJump = true;
                }
                jumpState = player.movementInput.jump;
                if (jumpState != lastJumpState)
                {
                    if (jumpState && !player.isInWater())
                    {
                        int jumpModifier = 0;
                        if (CurioHandler.hasBauble(player, TerraItemRegistry.BALLOON_RED.get())) { jumpModifier++; }
                        if (CurioHandler.hasBauble(player, TerraTagRegistry.FROG_HIGH_JUMPERS)) { jumpModifier++; }
                        if (CurioHandler.hasBauble(player, TerraTagRegistry.CLOUD_HIGH_JUMPERS)) { jumpModifier++; }
                        if (CurioHandler.hasBauble(player, TerraTagRegistry.BLIZZARD_HIGH_JUMPERS)) { jumpModifier++; }
                        if (CurioHandler.hasBauble(player, TerraTagRegistry.SANDSTORM_HIGH_JUMPERS)) { jumpModifier++; }
                        if (CurioHandler.hasBauble(player, TerraTagRegistry.HONEY_HIGH_JUMPERS)) { jumpModifier++; }
                        if (CurioHandler.hasBauble(player, TerraTagRegistry.TSUNAMI_HIGH_JUMPERS)) { jumpModifier++; }
                        if (CurioHandler.hasBauble(player, TerraTagRegistry.FART_HIGH_JUMPERS)) { jumpModifier++; }

                        if (hasfirstJump)
                        {
                            float upwardsMotion = 0.1F * jumpModifier;
                            Vector3d playerMotion = player.getMotion();
                            player.setMotion(playerMotion.x, playerMotion.y + upwardsMotion, playerMotion.z);
                            hasfirstJump = false;
                        }
                        else if (!player.isOnGround() && rocketState > 0 && !hasfirstJump && CurioHandler.hasBauble(player, TerraTagRegistry.ROCKET_JUMPERS))
                        {
                            NetworkHandler.INSTANCE.sendToServer(new RocketJumpPacket());
                            player.playSound(SoundEvents.BLOCK_WOOL_STEP, 1, 0.9F + player.getRNG().nextFloat() * 0.2F);
                            rocket(player, jumpModifier);
                            rocketState--;
                            if (rocketState == 0) { lastJumpState = jumpState; }
                            return;
                        }
                        else if (!player.isOnGround() && hasFartJump && CurioHandler.hasBauble(player, TerraTagRegistry.FART_JUMPERS))
                        {
                            NetworkHandler.INSTANCE.sendToServer(new FartJumpPacket());
                            player.playSound(SoundEvents.ENTITY_PLAYER_HURT_DROWN, 1, 0.9F + player.getRNG().nextFloat() * 0.2F);
                            jump(player, jumpModifier);
                            hasFartJump = false;
                        }
                        else if (!player.isOnGround() && hasTsunamiJump && CurioHandler.hasBauble(player, TerraTagRegistry.TSUNAMI_JUMPERS))
                        {
                            NetworkHandler.INSTANCE.sendToServer(new TsunamiJumpPacket());
                            player.playSound(SoundEvents.AMBIENT_UNDERWATER_EXIT, 1, 0.9F + player.getRNG().nextFloat() * 0.2F);
                            jump(player, jumpModifier);
                            hasTsunamiJump = false;
                        }
                        else if (!player.isOnGround() && hasSandstormJump && CurioHandler.hasBauble(player, TerraTagRegistry.SANDSTORM_JUMPERS))
                        {
                            NetworkHandler.INSTANCE.sendToServer(new SandstormJumpPacket());
                            player.playSound(SoundEvents.BLOCK_SAND_STEP, 1, 0.9F + player.getRNG().nextFloat() * 0.2F);
                            jump(player, jumpModifier);
                            hasSandstormJump = false;
                        }
                        else if (!player.isOnGround() && hasBlizzardJump && CurioHandler.hasBauble(player, TerraTagRegistry.BLIZZARD_JUMPERS))
                        {
                            NetworkHandler.INSTANCE.sendToServer(new BlizzardJumpPacket());
                            player.playSound(SoundEvents.BLOCK_SNOW_STEP, 1, 0.9F + player.getRNG().nextFloat() * 0.2F);
                            jump(player, jumpModifier);
                            hasBlizzardJump = false;
                        }
                        else if (!player.isOnGround() && hasCloudJump && CurioHandler.hasBauble(player, TerraTagRegistry.CLOUD_JUMPERS))
                        {
                            NetworkHandler.INSTANCE.sendToServer(new CloudJumpPacket());
                            player.playSound(SoundEvents.BLOCK_WOOL_STEP, 1, 0.9F + player.getRNG().nextFloat() * 0.2F);
                            jump(player, jumpModifier);
                            hasCloudJump = false;
                        }
                    }
                }
                lastJumpState = jumpState;
            }
        }
    }

    public static void jump(PlayerEntity player, int jumpModifier)
    {
        double upwardsMotion = 0.46;
        upwardsMotion += 0.1 * jumpModifier;
        if (player.isPotionActive(Effects.JUMP_BOOST))
        {
            upwardsMotion += 0.1 * (player.getActivePotionEffect(Effects.JUMP_BOOST).getAmplifier() + 1);
        }
        Vector3d motion = player.getMotion();

        player.setMotion(player.getMotion().add(0, upwardsMotion - motion.y, 0));

        player.isAirBorne = true;
        net.minecraftforge.common.ForgeHooks.onLivingJump(player);

        player.addStat(Stats.JUMP);
        player.addExhaustion(player.isSprinting() ? 0.2F : 0.05F);
    }

    public static void rocket(PlayerEntity player, int jumpModifier)
    {
        double upwardsMotion = Math.max(jumpModifier / 10.0, 0.2);
        Vector3d motion = player.getMotion();

        player.setMotion(player.getMotion().add(0, upwardsMotion - motion.y, 0));

        player.isAirBorne = true;
    }
}