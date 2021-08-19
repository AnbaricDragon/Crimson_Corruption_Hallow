package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.handlers.NetworkHandler;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import com.anbaric.terra_reforged.util.packets.movement.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
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
            ClientPlayerEntity player = Minecraft.getInstance().player;

            if (player != null)
            {
                ClientWorld world = player.clientLevel;

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
                    carpetState = CurioHandler.hasBauble(player, TerraItemRegistry.FLYING_CARPET.get()) ? 80 : 0;
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
                            float upwardsMotion = 0.1F * jumpModifier;
                            player.setDeltaMovement(player.getDeltaMovement().add(0, upwardsMotion, 0));
                        }
                        hasFirstJump = false;
                    }
                    else if (isPressed(player, world) && !player.isOnGround())
                    {
                        NetworkHandler.INSTANCE.sendToServer(new WallJumpPacket());
                        wallJump(player, jumpModifier);
                        player.setOnGround(true);
                    }
                    else if (rocketState > 0 && !player.isOnGround())
                    {
                        NetworkHandler.INSTANCE.sendToServer(new RocketJumpPacket());
                        player.playSound(SoundEvents.WOOL_STEP, 1, 0.9F + player.getRandom().nextFloat() * 0.2F);
                        rocket(player, jumpModifier);
                        rocketState--;
                        if (rocketState == 0) { lastJumpState = jumpState; }
                        return;
                    }
                    else if (hasFartJump && !player.isOnGround())
                    {
                        NetworkHandler.INSTANCE.sendToServer(new FartJumpPacket());
                        player.playSound(SoundEvents.PLAYER_HURT_DROWN, 1, 0.9F + player.getRandom().nextFloat() * 0.2F);
                        jump(player, jumpModifier);
                        hasFartJump = false;
                    }
                    else if (hasTsunamiJump && !player.isOnGround())
                    {
                        NetworkHandler.INSTANCE.sendToServer(new TsunamiJumpPacket());
                        player.playSound(SoundEvents.AMBIENT_UNDERWATER_EXIT, 1, 0.9F + player.getRandom().nextFloat() * 0.2F);
                        jump(player, jumpModifier);
                        hasTsunamiJump = false;
                    }
                    else if (hasSandstormJump && !player.isOnGround())
                    {
                        NetworkHandler.INSTANCE.sendToServer(new SandstormJumpPacket());
                        player.playSound(SoundEvents.SAND_STEP, 1, 0.9F + player.getRandom().nextFloat() * 0.2F);
                        jump(player, jumpModifier);
                        hasSandstormJump = false;
                    }
                    else if (hasBlizzardJump && !player.isOnGround())
                    {
                        NetworkHandler.INSTANCE.sendToServer(new BlizzardJumpPacket());
                        player.playSound(SoundEvents.SNOW_STEP, 1, 0.9F + player.getRandom().nextFloat() * 0.2F);
                        jump(player, jumpModifier);
                        hasBlizzardJump = false;
                    }
                    else if (hasCloudJump && !player.isOnGround())
                    {
                        NetworkHandler.INSTANCE.sendToServer(new CloudJumpPacket());
                        player.playSound(SoundEvents.WOOL_STEP, 1, 0.9F + player.getRandom().nextFloat() * 0.2F);
                        jump(player, jumpModifier);
                        hasCloudJump = false;
                    }
                    else if (carpetState > 0 && !player.isOnGround())
                    {
                        NetworkHandler.INSTANCE.sendToServer(new CarpetJumpPacket(true));
                        player.setDeltaMovement(player.getDeltaMovement().x() * 1.1, 0, player.getDeltaMovement().z() * 1.1);
                        player.setNoGravity(true);
                        player.fallDistance = 0;
                        carpetState--;
                        if (carpetState == 0) { lastJumpState = jumpState; player.setNoGravity(false); }
                        return;
                    }
                }
                if (!jumpState) { player.setNoGravity(false); NetworkHandler.INSTANCE.sendToServer(new CarpetJumpPacket(false)); }
                lastJumpState = jumpState;
            }
        }
    }

    private boolean isPressed(PlayerEntity player, World world)
    {
        Vector3d vecPos = player.position();
        BlockPos pos    = player.blockPosition();
        double   x      = vecPos.x();
        double   z      = vecPos.z();
        double   xi     = MathHelper.floor(x);
        double   zi     = MathHelper.floor(z);
        double   dotX   = MathHelper.abs((float) (x - xi));
        double   dotZ   = MathHelper.abs((float) (z - zi));
        return CurioHandler.hasBauble(player, TerraTagRegistry.WALL_CRAWLERS) && player.isCrouching() &&
            (((world.getBlockState(pos.relative(Direction.NORTH)).canOcclude() || world.getBlockState(pos.above().relative(Direction.NORTH)).canOcclude()) && dotZ <= 0.301) ||
             ((world.getBlockState(pos.relative(Direction.EAST)).canOcclude()  || world.getBlockState(pos.above().relative(Direction.EAST)).canOcclude() ) && dotX >= 0.699) ||
             ((world.getBlockState(pos.relative(Direction.SOUTH)).canOcclude() || world.getBlockState(pos.above().relative(Direction.SOUTH)).canOcclude()) && dotZ >= 0.699) ||
             ((world.getBlockState(pos.relative(Direction.WEST)).canOcclude()  || world.getBlockState(pos.above().relative(Direction.WEST)).canOcclude() ) && dotX <= 0.301));
    }

    private static Vector3d jumpDirection(PlayerEntity player, World world, double upwardsMotion)
    {
        Vector3d vecPos = player.position();
        BlockPos pos    = player.blockPosition();
        double   x      = vecPos.x();
        double   z      = vecPos.z();
        double   xi     = MathHelper.floor(x);
        double   zi     = MathHelper.floor(z);
        double   dotX   = MathHelper.abs((float) (x - xi));
        double   dotZ   = MathHelper.abs((float) (z - zi));

        boolean northWall = (world.getBlockState(pos.relative(Direction.NORTH)).canOcclude()|| world.getBlockState(pos.above().relative(Direction.NORTH)).canOcclude()) && dotZ <= 0.31;
        boolean eastWall  = (world.getBlockState(pos.relative(Direction.EAST)).canOcclude() || world.getBlockState(pos.above().relative(Direction.EAST)).canOcclude() ) && dotX >= 0.69;
        boolean southWall = (world.getBlockState(pos.relative(Direction.SOUTH)).canOcclude()|| world.getBlockState(pos.above().relative(Direction.SOUTH)).canOcclude()) && dotZ >= 0.69;
        boolean westWall  = (world.getBlockState(pos.relative(Direction.WEST)).canOcclude() || world.getBlockState(pos.above().relative(Direction.WEST)).canOcclude() ) && dotX <= 0.31;

        Vector3d finalDir = new Vector3d(eastWall ? -0.75 : (westWall ? 0.75 : 0), upwardsMotion, southWall ? -0.75 : (northWall ? 0.75 : 0));
        return finalDir;
    }

    public static void wallJump(PlayerEntity player, int jumpModifier)
    {
        double upwardsMotion = 0.46;
        upwardsMotion += 0.1 * jumpModifier;
        if (player.hasEffect(Effects.JUMP))
        {
            upwardsMotion += 0.1 * (player.getEffect(Effects.JUMP).getAmplifier() + 1);
        }
        double RAD2DEG = 57.29577951308232;
        Vector3d direction = jumpDirection(player, player.level, upwardsMotion);
        player.yRot = (float) MathHelper.wrapDegrees(MathHelper.atan2(-direction.x, direction.z) * RAD2DEG);
        player.setDeltaMovement(direction);

        player.hasImpulse = true;
        net.minecraftforge.common.ForgeHooks.onLivingJump(player);
        player.fallDistance = 0;

        player.awardStat(Stats.JUMP);
        player.causeFoodExhaustion(player.isSprinting() ? 0.2F : 0.05F);
    }

    public static void jump(PlayerEntity player, int jumpModifier)
    {
        double upwardsMotion = 0.46;
        upwardsMotion += 0.1 * jumpModifier;
        if (player.hasEffect(Effects.JUMP))
        {
            upwardsMotion += 0.1 * (player.getEffect(Effects.JUMP).getAmplifier() + 1);
        }
        Vector3d motion = player.getDeltaMovement();
        player.setDeltaMovement(player.getDeltaMovement().add(0, upwardsMotion - motion.y, 0));

        player.hasImpulse = true;
        net.minecraftforge.common.ForgeHooks.onLivingJump(player);
        player.fallDistance = 0;

        player.awardStat(Stats.JUMP);
        player.causeFoodExhaustion(player.isSprinting() ? 0.2F : 0.05F);
    }

    public static void rocket(PlayerEntity player, int jumpModifier)
    {
        double upwardsMotion = Math.max(jumpModifier / 10.0, 0.2);
        Vector3d motion = player.getDeltaMovement();
        player.fallDistance = 0;
        player.setDeltaMovement(player.getDeltaMovement().add(0, upwardsMotion - motion.y, 0));
        player.hasImpulse = true;
    }
}