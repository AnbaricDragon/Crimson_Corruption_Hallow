package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.handlers.NetworkHandler;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import com.anbaric.terra_reforged.util.packets.*;
import com.sun.javafx.geom.Vec3d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashSet;
import java.util.Set;

public class TerraJumpEvent
{
    @OnlyIn(Dist.CLIENT)
    private boolean jumpState;
    @OnlyIn(Dist.CLIENT)
    private int rocketState;
    @OnlyIn(Dist.CLIENT)
    private boolean lastJumpState;

    @OnlyIn(Dist.CLIENT)
    private boolean hasFirstJump;
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
                ClientWorld world = player.worldClient;

                int jumpModifier = 0;
                if (CurioHandler.hasBauble(player, TerraItemRegistry.BALLOON_RED.get())) { jumpModifier++; }
                if (CurioHandler.hasBauble(player, TerraTagRegistry.FROG_HIGH_JUMPERS)) { jumpModifier++; }
                if (CurioHandler.hasBauble(player, TerraTagRegistry.CLOUD_HIGH_JUMPERS)) { jumpModifier++; }
                if (CurioHandler.hasBauble(player, TerraTagRegistry.BLIZZARD_HIGH_JUMPERS)) { jumpModifier++; }
                if (CurioHandler.hasBauble(player, TerraTagRegistry.SANDSTORM_HIGH_JUMPERS)) { jumpModifier++; }
                if (CurioHandler.hasBauble(player, TerraTagRegistry.HONEY_HIGH_JUMPERS)) { jumpModifier++; }
                if (CurioHandler.hasBauble(player, TerraTagRegistry.TSUNAMI_HIGH_JUMPERS)) { jumpModifier++; }
                if (CurioHandler.hasBauble(player, TerraTagRegistry.FART_HIGH_JUMPERS)) { jumpModifier++; }

                if (player.isOnGround())
                {
                    hasFirstJump = true;
                    rocketState = CurioHandler.hasBauble(player, TerraTagRegistry.ROCKET_JUMPERS) ? 32 : 0;
                    hasFartJump = true;
                    hasTsunamiJump = true;
                    hasSandstormJump = true;
                    hasBlizzardJump = true;
                    hasCloudJump = true;
                }

                jumpState = player.movementInput.jump;
                if (jumpState != lastJumpState && jumpState && !player.isInWater() && !player.isCreative())
                {
                    if (hasFirstJump)
                    {
                        float upwardsMotion = 0.1F * jumpModifier;
                        player.setMotion(player.getMotion().add(0, upwardsMotion, 0));
                        hasFirstJump = false;
                    }
                    else if (isPressed(player, world))
                    {
                        NetworkHandler.INSTANCE.sendToServer(new WallJumpPacket());
                        wallJump(player, jumpModifier);
                    }
                    else if (!player.isOnGround() && !player.isCrouching() && rocketState > 0 && CurioHandler.hasBauble(player, TerraTagRegistry.ROCKET_JUMPERS))
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
                lastJumpState = jumpState;
            }
        }
    }

    private boolean isPressed(ClientPlayerEntity player, ClientWorld world)
    {
        Vector3d vecPos = player.getPositionVec();
        BlockPos pos    = player.getPosition();
        double   x      = vecPos.getX();
        double   z      = vecPos.getZ();
        double   xi     = MathHelper.floor(x);
        double   zi     = MathHelper.floor(z);
        double   dotX   = MathHelper.abs((float) (x - xi));
        double   dotZ   = MathHelper.abs((float) (z - zi));
        return CurioHandler.hasBauble(player, TerraTagRegistry.WALL_CRAWLERS) &&
               ((world.getBlockState(pos.offset(Direction.NORTH)).isSolid() && dotZ <= 0.31) ||
               (world.getBlockState(pos.offset(Direction.EAST)).isSolid() && dotX >= 0.69) ||
               (world.getBlockState(pos.offset(Direction.SOUTH)).isSolid() && dotZ >= 0.69) ||
               (world.getBlockState(pos.offset(Direction.WEST)).isSolid() && dotX <= 0.31));
    }

    public static void wallJump(PlayerEntity player, int jumpModifier)
    {
        double upwardsMotion = 0.46;
        upwardsMotion += 0.1 * jumpModifier;
        if (player.isPotionActive(Effects.JUMP_BOOST))
        {
            upwardsMotion += 0.1 * (player.getActivePotionEffect(Effects.JUMP_BOOST).getAmplifier() + 1);
        }
        float lookQuad = Math.abs(player.rotationYaw);
        player.rotationYaw = lookQuad <= 45.0F || lookQuad >= 135.0F ? player.getMirroredYaw(Mirror.LEFT_RIGHT) : player.getMirroredYaw(Mirror.FRONT_BACK);
        Vector3d motion = player.getMotion();
        Vector3d leapMotion = new Vector3d(Math.cos(player.rotationYaw) + 0.1, upwardsMotion, Math.sin(player.rotationYaw) + 0.1);
        player.setMotion(motion.add(leapMotion));

        player.isAirBorne = true;
        net.minecraftforge.common.ForgeHooks.onLivingJump(player);

        player.addStat(Stats.JUMP);
        player.addExhaustion(player.isSprinting() ? 0.2F : 0.05F);
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
        player.fallDistance = 0;

        player.addStat(Stats.JUMP);
        player.addExhaustion(player.isSprinting() ? 0.2F : 0.05F);
    }

    public static void rocket(PlayerEntity player, int jumpModifier)
    {
        double upwardsMotion = Math.max(jumpModifier / 10.0, 0.2);
        Vector3d motion = player.getMotion();
        player.fallDistance = 0;
        player.setMotion(player.getMotion().add(0, upwardsMotion - motion.y, 0));
        player.isAirBorne = true;
    }
}