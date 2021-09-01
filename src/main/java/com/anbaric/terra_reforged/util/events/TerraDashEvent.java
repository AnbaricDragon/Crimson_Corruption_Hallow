package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.handlers.NetworkHandler;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import com.anbaric.terra_reforged.util.packets.movement.TabiDashPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraDashEvent
{
    @OnlyIn(Dist.CLIENT)
    private boolean forwardState;
    @OnlyIn(Dist.CLIENT)
    private boolean leftState;
    @OnlyIn(Dist.CLIENT)
    private boolean backState;
    @OnlyIn(Dist.CLIENT)
    private boolean rightState;
    @OnlyIn(Dist.CLIENT)
    private boolean lastForwardState;
    @OnlyIn(Dist.CLIENT)
    private boolean lastLeftState;
    @OnlyIn(Dist.CLIENT)
    private boolean lastBackState;
    @OnlyIn(Dist.CLIENT)
    private boolean lastRightState;
    @OnlyIn(Dist.CLIENT)
    private int dashTimer;

    @OnlyIn(Dist.CLIENT)
    private boolean hasFirstClick;
    @OnlyIn(Dist.CLIENT)
    private boolean hasTabiDash;



    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    void onClientTick(TickEvent.ClientTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END)
        {
            ClientPlayerEntity player = Minecraft.getInstance().player;

            if (player != null)
            {
                ItemStack tabiStack = CurioHandler.getFromBaubles(player, TerraItemRegistry.NINJA_TABI.get(), TerraItemRegistry.GEAR_NINJA.get());
                int tabiCooldown = !tabiStack.isEmpty() ? tabiStack.getTag().getInt("tabiCooldown") : 50;

                hasFirstClick = dashTimer == 0;
                hasTabiDash = tabiCooldown == 0;

                forwardState = player.movementInput.forwardKeyDown;
                leftState = player.movementInput.leftKeyDown;
                backState = player.movementInput.backKeyDown;
                rightState = player.movementInput.rightKeyDown;

                if ((forwardState != lastForwardState && forwardState) ||
                    (leftState != lastLeftState && leftState) ||
                    (backState != lastBackState && backState) ||
                    (rightState != lastRightState && rightState))
                {
                    if (hasFirstClick)
                    {
                        hasFirstClick = false;
                        dashTimer = 5;
                    }
                    else if (player.isOnGround() && !player.isCrouching() && hasTabiDash && dashTimer > 0)
                    {
                        NetworkHandler.INSTANCE.sendToServer(new TabiDashPacket());
                        player.playSound(SoundEvents.BLOCK_WOOL_STEP, 1, 0.9F + player.getRNG().nextFloat() * 0.2F);
                        tabiStack.getTag().putInt("tabiCooldown", 40);
                        tabiDash(player);
                        dashTimer = 0;
                    }
                }
                if (dashTimer > 0) { dashTimer--; }
                lastForwardState = forwardState;
                lastLeftState = leftState;
                lastBackState = backState;
                lastRightState = rightState;
            }
        }
    }

    public static void tabiDash(PlayerEntity player)
    {
        player.setMotion(player.getMotion().normalize().mul(5, 0, 5));
    }
}