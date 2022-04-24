package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.util.handlers.ChannelHandler;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import com.anbaric.terra_reforged.util.packets.*;
import com.mojang.math.Vector3d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
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
    private int carpetState;
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
            LocalPlayer player = Minecraft.getInstance().player;

            if (player != null)
            {
                ClientLevel world = player.clientLevel;

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
                    carpetState = CurioHandler.hasBauble(player, TerraItemRegistry.FLYING_CARPET.get()) ? 60 : 0;
                    hasFartJump = CurioHandler.hasBauble(player, TerraTagRegistry.FART_JUMPERS);
                    hasTsunamiJump = CurioHandler.hasBauble(player, TerraTagRegistry.TSUNAMI_JUMPERS);
                    hasSandstormJump = CurioHandler.hasBauble(player, TerraTagRegistry.SANDSTORM_JUMPERS);
                    hasBlizzardJump = CurioHandler.hasBauble(player, TerraTagRegistry.BLIZZARD_JUMPERS);
                    hasCloudJump = CurioHandler.hasBauble(player, TerraTagRegistry.CLOUD_JUMPERS);
                }

                jumpState = player.input.jumping;
                if (jumpState != lastJumpState && jumpState && !player.isInWater() && !player.isCreative())
                {
                    if (hasFirstJump)
                    {
                        if (player.isOnGround())
                        {
                            jump(player, jumpModifier);
                        }
                        hasFirstJump = false;
                    }
                    else if (isPressed(player, world) && !player.isOnGround())
                    {
                        ChannelHandler.INSTANCE.sendToServer(new WallJumpPacket(jumpModifier));
                        wallJump(player, jumpModifier);
                        player.setOnGround(true);
                    }
                    else if (rocketState > 0 && !player.isOnGround())
                    {
                        ChannelHandler.INSTANCE.sendToServer(new RocketJumpPacket(jumpModifier));
                        player.playSound(SoundEvents.CAMPFIRE_CRACKLE, 1, 0.9F + player.getRandom().nextFloat() * 0.2F);
                        rocket(player, jumpModifier);
                        rocketState--;
                        if (rocketState == 0) { lastJumpState = jumpState; }
                        return;
                    }
                    else if (hasFartJump && !player.isOnGround())
                    {
                        ChannelHandler.INSTANCE.sendToServer(new FartJumpPacket(jumpModifier));
                        player.playSound(SoundEvents.DROWNED_HURT_WATER, 1, 0.9F + player.getRandom().nextFloat() * 0.2F);
                        jump(player, jumpModifier);
                        hasFartJump = false;
                    }
                    else if (hasTsunamiJump && !player.isOnGround())
                    {
                        ChannelHandler.INSTANCE.sendToServer(new TsunamiJumpPacket(jumpModifier));
                        player.playSound(SoundEvents.AMBIENT_UNDERWATER_EXIT, 1, 0.9F + player.getRandom().nextFloat() * 0.2F);
                        jump(player, jumpModifier);
                        hasTsunamiJump = false;
                    }
                    else if (hasSandstormJump && !player.isOnGround())
                    {
                        ChannelHandler.INSTANCE.sendToServer(new SandstormJumpPacket(jumpModifier));
                        player.playSound(SoundEvents.SAND_STEP, 1, 0.9F + player.getRandom().nextFloat() * 0.2F);
                        jump(player, jumpModifier);
                        hasSandstormJump = false;
                    }
                    else if (hasBlizzardJump && !player.isOnGround())
                    {
                        ChannelHandler.INSTANCE.sendToServer(new BlizzardJumpPacket(jumpModifier));
                        player.playSound(SoundEvents.SNOW_STEP, 1, 0.9F + player.getRandom().nextFloat() * 0.2F);
                        jump(player, jumpModifier);
                        hasBlizzardJump = false;
                    }
                    else if (hasCloudJump && !player.isOnGround())
                    {
                        ChannelHandler.INSTANCE.sendToServer(new CloudJumpPacket(jumpModifier));
                        player.playSound(SoundEvents.WOOL_STEP, 1, 0.9F + player.getRandom().nextFloat() * 0.2F);
                        jump(player, jumpModifier);
                        hasCloudJump = false;
                    }
                    else if (carpetState > 0 && !player.isOnGround())
                    {
                        ChannelHandler.INSTANCE.sendToServer(new CarpetJumpPacket(true));
                        player.setDeltaMovement(player.getDeltaMovement().get(Direction.Axis.X) * 1.03, 0, player.getDeltaMovement().get(Direction.Axis.Z) * 1.03);
                        player.setNoGravity(true);
                        player.fallDistance = 0;
                        carpetState--;
                        if (carpetState == 0) { lastJumpState = jumpState; player.setNoGravity(false); }
                        return;
                    }
                }
                if (!jumpState) { player.setNoGravity(false); ChannelHandler.INSTANCE.sendToServer(new CarpetJumpPacket(false)); }
                lastJumpState = jumpState;
            }
        }
    }

    private boolean isPressed(Player player, Level world)
    {
        Vec3     vecPos = player.position();
        BlockPos pos    = player.getOnPos().above();
        double   x      = vecPos.get(Direction.Axis.X);
        double   z      = vecPos.get(Direction.Axis.Z);
        double   xi     = Math.floor(x);
        double   zi     = Math.floor(z);
        double   dotX   = Math.abs((float) (x - xi));
        double   dotZ   = Math.abs((float) (z - zi));
        return CurioHandler.hasBauble(player, TerraTagRegistry.WALL_GRIPPERS) && player.isCrouching() &&
               ((world.getBlockState(pos.relative(Direction.NORTH)).isFaceSturdy(world, pos.relative(Direction.NORTH), Direction.SOUTH) || (world.getBlockState(pos.above().relative(Direction.NORTH)).isFaceSturdy(world, pos.relative(Direction.NORTH), Direction.SOUTH)) && dotZ <= 0.301) ||
               ((world.getBlockState(pos.relative(Direction.EAST) ).isFaceSturdy(world, pos.relative(Direction.EAST) , Direction.WEST)  || (world.getBlockState(pos.above().relative(Direction.EAST) ).isFaceSturdy(world, pos.relative(Direction.EAST) , Direction.WEST) ) && dotX >= 0.699) ||
               ((world.getBlockState(pos.relative(Direction.SOUTH)).isFaceSturdy(world, pos.relative(Direction.SOUTH), Direction.NORTH) || (world.getBlockState(pos.above().relative(Direction.SOUTH)).isFaceSturdy(world, pos.relative(Direction.SOUTH), Direction.NORTH)) && dotZ >= 0.699) ||
               ((world.getBlockState(pos.relative(Direction.WEST) ).isFaceSturdy(world, pos.relative(Direction.WEST) , Direction.EAST)  || (world.getBlockState(pos.above().relative(Direction.WEST) ).isFaceSturdy(world, pos.relative(Direction.WEST) , Direction.EAST) ) && dotX <= 0.301)))));
    }

    private static Vec3 jumpDirection(Player player, Level world, double upwardsMotion)
    {
        Vec3 vecPos = player.position();
        BlockPos pos    = player.getOnPos().above();
        double   x      = vecPos.get(Direction.Axis.X);
        double   z      = vecPos.get(Direction.Axis.Z);
        double   xi     = Math.floor(x);
        double   zi     = Math.floor(z);
        double   dotX   = Math.abs((float) (x - xi));
        double   dotZ   = Math.abs((float) (z - zi));

        boolean northWall = (world.getBlockState(pos.relative(Direction.NORTH)).isFaceSturdy(world, pos.relative(Direction.NORTH), Direction.SOUTH) || world.getBlockState(pos.above().relative(Direction.NORTH)).isFaceSturdy(world, pos.relative(Direction.NORTH), Direction.SOUTH)) && dotZ <= 0.301;
        boolean eastWall  = (world.getBlockState(pos.relative(Direction.EAST) ).isFaceSturdy(world, pos.relative(Direction.EAST) , Direction.WEST)  || world.getBlockState(pos.above().relative(Direction.EAST) ).isFaceSturdy(world, pos.relative(Direction.EAST) , Direction.WEST) ) && dotX >= 0.699;
        boolean southWall = (world.getBlockState(pos.relative(Direction.SOUTH)).isFaceSturdy(world, pos.relative(Direction.SOUTH), Direction.NORTH) || world.getBlockState(pos.above().relative(Direction.SOUTH)).isFaceSturdy(world, pos.relative(Direction.SOUTH), Direction.NORTH)) && dotZ >= 0.699;
        boolean westWall  = (world.getBlockState(pos.relative(Direction.WEST) ).isFaceSturdy(world, pos.relative(Direction.WEST) , Direction.EAST)  || world.getBlockState(pos.above().relative(Direction.WEST) ).isFaceSturdy(world, pos.relative(Direction.WEST) , Direction.EAST) ) && dotX <= 0.301;

        Vec3 finalDir = new Vec3(eastWall ? -0.75 : (westWall ? 0.75 : 0), upwardsMotion, southWall ? -0.75 : (northWall ? 0.75 : 0));
        return finalDir;
    }

    public static void wallJump(Player player, int jumpModifier)
    {
        double upwardsMotion = 0.46;
        upwardsMotion += 0.1 * jumpModifier;
        if (player.hasEffect(MobEffects.JUMP))
        {
            upwardsMotion += 0.1 * (player.getEffect(MobEffects.JUMP).getAmplifier() + 1);
        }
        double RAD2DEG = 57.29577951308232;
        Vec3 direction = jumpDirection(player, player.level, upwardsMotion);
        player.yHeadRot = (float) Mth.wrapDegrees(Mth.atan2(-direction.x, direction.z) * RAD2DEG);
        player.setDeltaMovement(direction);

        player.setOnGround(false);
        net.minecraftforge.common.ForgeHooks.onLivingJump(player);
        player.fallDistance = 0;

        player.awardStat(Stats.JUMP);
        player.causeFoodExhaustion(player.isSprinting() ? 0.2F : 0.05F);
    }

    public static void jump(Player player, int jumpModifier)
    {
        double upwardsMotion = 0.46;
        upwardsMotion += 0.1 * jumpModifier;
        if (player.hasEffect(MobEffects.JUMP))
        {
            upwardsMotion += 0.1 * (player.getEffect(MobEffects.JUMP).getAmplifier() + 1);
        }
        Vec3 motion = player.getDeltaMovement();
        player.setDeltaMovement(motion.add(0, upwardsMotion - motion.y, 0));

        player.setOnGround(false);
        net.minecraftforge.common.ForgeHooks.onLivingJump(player);
        player.fallDistance = 0;

        player.awardStat(Stats.JUMP);
        player.causeFoodExhaustion(player.isSprinting() ? 0.2F : 0.05F);
    }

    public static void rocket(Player player, int jumpModifier)
    {
        double upwardsMotion = Math.max(jumpModifier / 10.0, 0.2);
        Vec3 motion = player.getDeltaMovement();
        player.fallDistance = 0;
        player.setDeltaMovement(motion.add(0, upwardsMotion - motion.y, 0));
        player.setOnGround(false);
    }
}
