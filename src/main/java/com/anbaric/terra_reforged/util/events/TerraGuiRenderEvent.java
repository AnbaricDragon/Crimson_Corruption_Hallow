package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.mana.TerraCapabilityPlayerMana;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import com.google.common.util.concurrent.AtomicDouble;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.function.Predicate;

@OnlyIn(Dist.CLIENT) public class TerraGuiRenderEvent
{
    private static final Minecraft mc = Minecraft.getInstance();
    private static final ResourceLocation ICON_GUI = new ResourceLocation("terra_reforged:textures/gui/gui_icon.png");

    @SubscribeEvent(receiveCanceled = true)
    public static void renderGameOverlay(RenderGameOverlayEvent.Post event)
    {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ARMOR)
        {
            ClientPlayerEntity player        = mc.player;
            ItemStack          lavaProtector = CurioHandler.getBaubles(player, TerraItemRegistry.CHARM_LAVA.get(), TerraItemRegistry.BOOTS_LAVA.get(), TerraItemRegistry.BOOTS_HELLFIRE.get(), TerraItemRegistry.BOOTS_TERRASPARK.get());
            ItemStack          tabiDasher    = CurioHandler.getBaubles(player, TerraItemRegistry.NINJA_TABI.get());


            AtomicDouble currentMana = new AtomicDouble(0);
            double maxMana = player.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).getValue();

            player.getCapability(TerraCapabilityPlayerMana.PLAYER_MANA_CAPABILITY).ifPresent(cap -> {
                currentMana.set(cap.getCurrentMana());
            });

            double mana = currentMana.get();
            int storage = (int) Math.floor(maxMana / 10);
            //            System.out.println("Player has " + mana + " in " + storage + " slots");
            if (mana >= 0)
            {
                mc.getEntityRenderDispatcher().textureManager.bind(ICON_GUI);
                IngameGui ingameGui = mc.gui;
                int manaPoisoned = player.getActiveEffects().contains(TerraEffectRegistry.SILENCED.get()) ? 22 : 0;
                int width  = mc.getWindow().getGuiScaledWidth();
                int height = mc.getWindow().getGuiScaledHeight();
                int right = 8;
                int top = height - (ForgeIngameGui.right_height + 11);

                RenderSystem.enableBlend();
                int underLay = 11 * (int) Math.floor(mana / 100);
                for (int crystal = 0; crystal < Math.min(storage, 10); crystal ++)
                {
                    ingameGui.blit(event.getMatrixStack(), width / 2 + right, top, underLay, 0, 10, 10);
                    right += 8;
                }
                right = 8;

                for (int crystal = 0; crystal < Math.min(storage, 10); crystal ++)
                {
                    int layerMana = (int) (mana % 100);
                    int crystalMana = crystal * 10;
                    if (layerMana > (crystalMana) + 10)
                    {
                        ingameGui.blit(event.getMatrixStack(), width / 2 + right, top, underLay + 11, 0, 10, 10);
                    }
                    else if (layerMana > crystalMana)
                    {
                        ingameGui.blit(event.getMatrixStack(), width / 2 + right, top, underLay + 11, 0, layerMana - crystalMana, 10);
                    }
                    right += 8;
                }
                mc.getTextureManager().bind(AbstractGui.GUI_ICONS_LOCATION);
//                ingameGui.blit(event.getMatrixStack(), width / 2 + right, top, 0, 0, 10, 10);
                RenderSystem.disableBlend();
            }
            if (!lavaProtector.isEmpty())
            {
                CompoundNBT compound = lavaProtector.getTag();
                if (compound != null)
                {
                    float charge   = compound.getInt("charge");
                    float cooldown = compound.getInt("chargeCooldown");

                    if (charge < 140 || cooldown > 0)
                    {
                        mc.getEntityRenderDispatcher().textureManager.bind(ICON_GUI);
                        IngameGui ingameGui = mc.gui;

                        int width  = mc.getWindow().getGuiScaledWidth();
                        int height = mc.getWindow().getGuiScaledHeight();

                        int count = (int) Math.floor(charge / 14F);

                        int right = 80;
                        int top   = height - (ForgeIngameGui.right_height + 3);

                        RenderSystem.enableBlend();
                        for (int i = 0; i < count + 1; i++)
                        {
                            if (i == count)
                            {
                                float countFloat = charge / 14F + 10;
                                RenderSystem.color4f(1, 1, 1, (countFloat) % ((int) (countFloat)));
                            }

                            ingameGui.blit(event.getMatrixStack(), width / 2 + right, top, 0, 22, 10, 10);
                            right -= 8;
                            RenderSystem.color4f(1, 1, 1, 1);
                        }
                        ForgeIngameGui.left_height += 10;
                        mc.getTextureManager().bind(AbstractGui.GUI_ICONS_LOCATION);
                        RenderSystem.disableBlend();
                    }
                }
            }
            if (!tabiDasher.isEmpty())
            {
                CompoundNBT compound = tabiDasher.getTag();
                if (compound != null)
                {
                    float cooldown = compound.getInt("tabiCooldown");

                    if (cooldown == 0)
                    {
                        mc.getEntityRenderDispatcher().textureManager.bind(ICON_GUI);
                        IngameGui ingameGui = mc.gui;

                        int width  = mc.getWindow().getGuiScaledWidth();
                        int height = mc.getWindow().getGuiScaledHeight();

                        int count = 0;

                        int left = 80;
                        int top  = height - (ForgeIngameGui.right_height + 11);

                        RenderSystem.enableBlend();
                        ingameGui.blit(event.getMatrixStack(), width / 2 - left, top, 0, 0, 10, 10);
                        RenderSystem.color4f(1, 1, 1, 1);
                        ForgeIngameGui.left_height += 10;
                        mc.getTextureManager().bind(AbstractGui.GUI_ICONS_LOCATION);
                        RenderSystem.disableBlend();
                    }
                }
            }
        }
    }
}