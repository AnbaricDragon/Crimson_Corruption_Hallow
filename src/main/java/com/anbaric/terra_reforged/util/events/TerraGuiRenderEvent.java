package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
public class TerraGuiRenderEvent
{
    private static final Minecraft mc = Minecraft.getInstance();
    private static final ResourceLocation ICON_LAVA = new ResourceLocation("terra_reforged:textures/gui/lava_icon.png");

    @SubscribeEvent(receiveCanceled = true)
    public static void renderGameOverlay(RenderGameOverlayEvent.Post event)
    {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ARMOR)
        {
            ClientPlayerEntity player        = mc.player;
            ItemStack          lavaProtector = CurioHandler.getBaubles(player, TerraItemRegistry.CHARM_LAVA.get(), TerraItemRegistry.BOOTS_LAVA.get(), TerraItemRegistry.BOOTS_HELLFIRE.get(), TerraItemRegistry.BOOTS_TERRASPARK.get());

            if (!lavaProtector.isEmpty())
            {
                CompoundNBT compound = lavaProtector.getTag();
                if (compound != null)
                {
                    float charge   = compound.getInt("charge");
                    float cooldown = compound.getInt("chargeCooldown");

                    if (charge < 140 || cooldown > 0)
                    {
                        mc.getRenderManager().textureManager.bindTexture(ICON_LAVA);
                        IngameGui ingameGui = mc.ingameGUI;

                        int width  = mc.getMainWindow().getScaledWidth();
                        int height = mc.getMainWindow().getScaledHeight();

                        int count = (int) Math.floor(charge / 14F);

                        int right = 80;
                        int top   = height - (ForgeIngameGui.right_height + 11);

                        RenderSystem.enableBlend();
                        for (int i = 0; i < count + 1; i++)
                        {
                            if (i == count)
                            {
                                float countFloat = charge / 14F + 10;
                                RenderSystem.color4f(1, 1, 1, (countFloat) % ((int) (countFloat)));
                            }

                            ingameGui.blit(event.getMatrixStack(), width / 2 + right, top, 0, 0, 10, 10);
                            right -= 8;
                            RenderSystem.color4f(1, 1, 1, 1);
                        }
                        ForgeIngameGui.left_height += 10;
                        mc.getTextureManager().bindTexture(AbstractGui.GUI_ICONS_LOCATION);
                        RenderSystem.disableBlend();
                    }
                }
            }
        }
    }
}