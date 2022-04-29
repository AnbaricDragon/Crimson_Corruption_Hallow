package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.capabilities.player_mana.TerraMana;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.google.common.util.concurrent.AtomicDouble;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import org.lwjgl.opengl.GL11;

public class TerraOverlayRegistry
{
    private static final ResourceLocation ICON_GUI = new ResourceLocation("terra_reforged:textures/gui/gui_icon.png");

    public static void register()
    {
        OverlayRegistry.registerOverlayAbove(ForgeIngameGui.HOTBAR_ELEMENT, "terra_mana_overlay", MANA_LEVEL_ELEMENT);
        OverlayRegistry.registerOverlayAbove(ForgeIngameGui.HOTBAR_ELEMENT, "terra_lava_overlay", LAVA_PROTECTION_ELEMENT);
    }

    public static final IIngameOverlay MANA_LEVEL_ELEMENT = OverlayRegistry.registerOverlayTop("Mana Level", (gui, poseStack, partialTick, screenWidth, screenHeight) ->
    {
        if (!gui.minecraft.options.hideGui && gui.shouldDrawSurvivalElements())
        {
            gui.setupOverlayRenderState(true, false);
            LocalPlayer player = gui.minecraft.player;

            AtomicDouble currentMana = new AtomicDouble(0);
            player.getCapability(TerraMana.TERRA_MANA_CAPABILITY).ifPresent(cap ->
            {
                currentMana.set(cap.getCurrentMana());
            });

            double mana = currentMana.get();
            double maxMana = player.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).getValue();
            int manaStorage = (int) Math.floor(maxMana / 10);

            if (mana >= 0)
            {
                bind(ICON_GUI);
                int manaPoisoned = player.hasEffect(TerraEffectRegistry.SILENCED.get()) ? 11 : 0;
                int underwater = player.isEyeInFluid(FluidTags.WATER) || player.getAirSupply() < 300 ? -10 : 0;
                int width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
                int height = Minecraft.getInstance().getWindow().getGuiScaledHeight();
                int right = 9;
                int top = height - 50 + underwater;

                RenderSystem.enableBlend();
                int underlay = 11 * (int) Math.floor(mana / 100);
                for (int crystal = 0; crystal < Math.min(manaStorage, 10); crystal++)
                {
                    gui.blit(poseStack, width / 2 + right, top, underlay, manaPoisoned, 10, 10);
                    right += 8;
                }
                right = 8;

                for (int crystal = 0; crystal < Math.min(manaStorage, 10); crystal ++)
                {
                    int layerMana = (int) (mana % 100);
                    int crystalMana = crystal * 10;
                    if (layerMana > (crystalMana) + 10)
                    {
                        gui.blit(poseStack, width / 2 + right, top, underlay + 11, manaPoisoned, 10, 10);
                    }
                    else if (layerMana > crystalMana)
                    {
                        gui.blit(poseStack, width / 2 + right, top, underlay + 11, manaPoisoned, layerMana - crystalMana, 10);
                    }
                    right += 8;
                }
                RenderSystem.disableBlend();
            }
        }
    });

    public static final IIngameOverlay LAVA_PROTECTION_ELEMENT = OverlayRegistry.registerOverlayTop("Lava Protection Level",(gui, poseStack, partialTick, screenWidth, screenHeight) ->
    {
        if (!gui.minecraft.options.hideGui && gui.shouldDrawSurvivalElements())
        {
            gui.setupOverlayRenderState(true, false);
            bind(ICON_GUI);
            LocalPlayer player = gui.minecraft.player;

            ItemStack lavaProtector = CurioHandler.getStack(player, TerraTagRegistry.LAVA_PROTECTORS);

            if (!lavaProtector.isEmpty())
            {
                CompoundTag compound = lavaProtector.getTag();
                if (compound != null)
                {
                    float charge   = compound.getInt("charge");
                    float cooldown = compound.getInt("chargeCooldown");

//                    if (charge < 140 || cooldown > 0)
//                    {
                        int width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
                        int height = Minecraft.getInstance().getWindow().getGuiScaledHeight();

                        int count = (int) Math.floor(charge / 14F);
                        int armored = gui.minecraft.player.getArmorValue() == 0 ? 0 : -10;

                        int left = width / 2 - 92;
                        int top = (height - 50) + armored;

                        RenderSystem.enableBlend();
                        for (int i = 0; i < count + 1; i++)
                        {
                            if (i == count)
                            {
                                float countFloat = charge / 14F + 10;
                                RenderSystem.setShaderColor(1, 1, 1, (countFloat) % ((int) (countFloat)));
                            }

                            gui.blit(poseStack, left, top, 0, 22, 10, 10);
                            left += 8;
                            RenderSystem.setShaderColor(1, 1, 1, 1);
                        }
                        RenderSystem.disableBlend();
//                    }
                }
            }
        }
    });

    private static void bind(ResourceLocation res)
    {
        RenderSystem.setShaderTexture(0, res);
    }
}
