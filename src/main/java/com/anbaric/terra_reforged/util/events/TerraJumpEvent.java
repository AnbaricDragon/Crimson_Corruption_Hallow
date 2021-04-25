package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.multijump.TerraCapabilityMultiJump;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.concurrent.atomic.AtomicInteger;

public class TerraJumpEvent
{
    @OnlyIn(Dist.CLIENT)
    private boolean jumpState;

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
                        AtomicInteger jumpModifier = new AtomicInteger(0);
                        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem() == TerraItemRegistry.BALLOON_RED.get(), player).ifPresent(found -> jumpModifier.getAndIncrement());
                        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.CLOUD_HIGH_JUMPERS), player).ifPresent(found -> jumpModifier.getAndIncrement());
                        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.BLIZZARD_HIGH_JUMPERS), player).ifPresent(found -> jumpModifier.getAndIncrement());
                        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.SANDSTORM_HIGH_JUMPERS), player).ifPresent(found -> jumpModifier.getAndIncrement());
                        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.HONEY_HIGH_JUMPERS), player).ifPresent(found -> jumpModifier.getAndIncrement());
                        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.TSUNAMI_HIGH_JUMPERS), player).ifPresent(found -> jumpModifier.getAndIncrement());
                        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.FART_HIGH_JUMPERS), player).ifPresent(found -> jumpModifier.getAndIncrement());

                        if (hasfirstJump)
                        {
                            float upwardsMotion = 0.1F * jumpModifier.get();
                            Vector3d playerMotion = player.getMotion();
                            player.setMotion(playerMotion.x, playerMotion.y + upwardsMotion, playerMotion.z);
                            hasfirstJump = false;
                        }
                        else if (!player.isOnGround() && hasFartJump && CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.FART_JUMPERS), player).isPresent()) { NetworkHandler.INSTANCE.sendToServer(new FartJumpPacket()); player.playSound(SoundEvents.ENTITY_PLAYER_HURT_DROWN, 1, 0.9F + player.getRNG().nextFloat() * 0.2F); jump(player, jumpModifier.get()); hasFartJump = false; }
                        else if (!player.isOnGround() && hasTsunamiJump && CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.TSUNAMI_JUMPERS), player).isPresent()) { NetworkHandler.INSTANCE.sendToServer(new TsunamiJumpPacket()); player.playSound(SoundEvents.AMBIENT_UNDERWATER_EXIT, 1, 0.9F + player.getRNG().nextFloat() * 0.2F); jump(player, jumpModifier.get()); hasTsunamiJump = false; }
                        else if (!player.isOnGround() && hasSandstormJump && CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.SANDSTORM_JUMPERS), player).isPresent()) { NetworkHandler.INSTANCE.sendToServer(new SandstormJumpPacket()); player.playSound(SoundEvents.BLOCK_SAND_FALL, 1, 0.9F + player.getRNG().nextFloat() * 0.2F); jump(player, jumpModifier.get()); hasSandstormJump = false; }
                        else if (!player.isOnGround() && hasBlizzardJump && CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.BLIZZARD_JUMPERS), player).isPresent()) { NetworkHandler.INSTANCE.sendToServer(new BlizzardJumpPacket()); player.playSound(SoundEvents.BLOCK_SNOW_FALL, 1, 0.9F + player.getRNG().nextFloat() * 0.2F); jump(player, jumpModifier.get()); hasBlizzardJump = false; }
                        else if (!player.isOnGround() && hasCloudJump && CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.CLOUD_JUMPERS), player).isPresent()) { NetworkHandler.INSTANCE.sendToServer(new CloudJumpPacket()); player.playSound(SoundEvents.BLOCK_WOOL_FALL, 1, 0.9F + player.getRNG().nextFloat() * 0.2F); jump(player, jumpModifier.get()); hasCloudJump = false; }
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
}