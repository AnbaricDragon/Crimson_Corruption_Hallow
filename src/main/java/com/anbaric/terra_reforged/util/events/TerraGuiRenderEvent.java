package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.player_mana.TerraMana;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import com.google.common.util.concurrent.AtomicDouble;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class TerraGuiRenderEvent
{
    private static final ResourceLocation ICON_GUI = new ResourceLocation("terra_reforged:textures/gui/gui_icon.png");

    @SubscribeEvent(receiveCanceled = true)
    public static void renderGameOverlay(RenderGameOverlayEvent.Post event)
    {
        LocalPlayer player = Minecraft.getInstance().player;

        if (event.getType() == RenderGameOverlayEvent.ElementType.LAYER && !player.isSpectator() && !player.isCreative())
        {
            ItemStack lavaProtector = CurioHandler.getStack(player, TerraTagRegistry.LAVA_PROTECTORS);

            AtomicDouble currentMana = new AtomicDouble(0);
            player.getCapability(TerraMana.TERRA_MANA_CAPABILITY).ifPresent(cap ->
            {
                Reference.print("Capability found from EVENT");
                currentMana.set(cap.getCurrentMana());
            });

            double mana = currentMana.get();
            double maxMana = player.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).getValue();

            int manaStorage = (int) Math.floor(maxMana / 10);

            if (mana >= 0)
            {
                RenderSystem.setShaderTexture(0, ICON_GUI);
                Gui gui = Minecraft.getInstance().gui;

                int manaPoisoned = player.hasEffect(TerraEffectRegistry.SILENCED.get()) ? 11 : 0;
                int width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
                int height = Minecraft.getInstance().getWindow().getGuiScaledHeight();
                int right = 8;
                int top = height - 50;

                RenderSystem.enableBlend();
                int underlay = 11 * (int) Math.floor(mana / 100);
                for (int crystal = 0; crystal < Math.min(manaStorage, 10); crystal++)
                {
                    gui.blit(event.getMatrixStack(), width / 2 + right, top, underlay, manaPoisoned, 10, 10);
                    right += 8;
                }
                right = 8;

                for (int crystal = 0; crystal < Math.min(manaStorage, 10); crystal ++)
                {
                    int layerMana = (int) (mana % 100);
                    int crystalMana = crystal * 10;
                    if (layerMana > (crystalMana) + 10)
                    {
                        gui.blit(event.getMatrixStack(), width / 2 + right, top, underlay + 11, manaPoisoned, 10, 10);
                    }
                    else if (layerMana > crystalMana)
                    {
                        gui.blit(event.getMatrixStack(), width / 2 + right, top, underlay + 11, manaPoisoned, layerMana - crystalMana, 10);
                    }
                    right += 8;
                }
                RenderSystem.setShaderTexture(0, GuiComponent.GUI_ICONS_LOCATION);
                RenderSystem.disableBlend();
            }
        }
    }
}
