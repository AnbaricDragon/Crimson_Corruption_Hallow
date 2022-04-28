package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.capabilities.player_mana.TerraMana;
import com.anbaric.terra_reforged.util.Reference;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;

public class TerraOverlayRegistry
{
    private static final ResourceLocation ICON_GUI = new ResourceLocation("terra_reforged:textures/gui/gui_icon.png");

    public static void register()
    {
        OverlayRegistry.registerOverlayAbove(ForgeIngameGui.HOTBAR_ELEMENT, "terra_mana", MANA_LEVEL_ELEMENT);
    }

    public static final IIngameOverlay MANA_LEVEL_ELEMENT = OverlayRegistry.registerOverlayTop("Armor Level",(gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        if (!gui.minecraft.options.hideGui && gui.shouldDrawSurvivalElements())
        {
            gui.setupOverlayRenderState(true, false);
            LocalPlayer player = gui.minecraft.player;

            player.getCapability(TerraMana.TERRA_MANA_CAPABILITY).ifPresent(cap ->
            {
                Reference.print("Overlay found");
                int mana = cap.getCurrentMana();
                Reference.print("Mana count is " + mana);
                int right = 0;
                for (int crystals = 0; crystals < mana; crystals++)
                {
                    gui.blit(poseStack, right, screenHeight / 2, 11, 0, 10, 10);
                    right += 8;
                }
            });

//            AtomicDouble currentMana = new AtomicDouble(0);
//            player.getCapability(TerraMana.TERRA_MANA_CAPABILITY).ifPresent(cap ->
//            {
//                Reference.print("Capability found from OVERLAY");
//                currentMana.set(cap.getCurrentMana());
//            });
//
//            double mana = currentMana.get();
//            double maxMana = player.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).getValue();
//            int manaStorage = (int) Math.floor(maxMana / 10);
//
//            if (mana >= 0)
//            {
//                bind(ICON_GUI);
//                int manaPoisoned = player.hasEffect(TerraEffectRegistry.SILENCED.get()) ? 11 : 0;
//                int width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
//                int height = Minecraft.getInstance().getWindow().getGuiScaledHeight();
//                int right = 8;
//                int top = height - 50;
//
//                RenderSystem.enableBlend();
//                int underlay = 11 * (int) Math.floor(mana / 100);
//                for (int crystal = 0; crystal < Math.min(manaStorage, 10); crystal++)
//                {
//                    gui.blit(poseStack, width / 2 + right, top, underlay, manaPoisoned, 10, 10);
//                    right += 8;
//                }
//                right = 8;
//
//                for (int crystal = 0; crystal < Math.min(manaStorage, 10); crystal ++)
//                {
//                    int layerMana = (int) (mana % 100);
//                    int crystalMana = crystal * 10;
//                    if (layerMana > (crystalMana) + 10)
//                    {
//                        gui.blit(poseStack, width / 2 + right, top, underlay + 11, manaPoisoned, 10, 10);
//                    }
//                    else if (layerMana > crystalMana)
//                    {
//                        gui.blit(poseStack, width / 2 + right, top, underlay + 11, manaPoisoned, layerMana - crystalMana, 10);
//                    }
//                    right += 8;
//                }
//                RenderSystem.disableBlend();
//            }
        }
    });

    private static void bind(ResourceLocation res)
    {
        RenderSystem.setShaderTexture(0, res);
    }
}
