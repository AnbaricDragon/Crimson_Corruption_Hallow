package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.capabilities.PlayerMana.PlayerMana;
import com.anbaric.terra_reforged.capabilities.PlayerMana.PlayerManaClient;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.*;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nullable;

import java.util.concurrent.atomic.AtomicInteger;

import static net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.ALL;

public class ManaGuiHandler extends Gui implements IIngameOverlay
{
    private final Minecraft mc;
    private Font font = null;
    public int left_height = 39;
    public int right_height = 49;
    private RenderGameOverlayEvent eventParent;
    private static final ResourceLocation ICON_GUI = new ResourceLocation("terra_reforged:textures/gui/gui_icon.png");
    public static final ManaGuiHandler INSTANCE = new ManaGuiHandler(Minecraft.getInstance());

    public ManaGuiHandler(Minecraft minecraft)
    {
        super(minecraft);

        this.mc = Minecraft.getInstance();
        this.font = mc.font;
        OverlayRegistry.registerOverlayTop("Mana ", (gui, mStack, partialTicks, screenWidth, screenHeight) ->
        {
            if (!gui.minecraft.options.hideGui && gui.shouldDrawSurvivalElements())
            {
                setupOverlayRenderState(true, false);
                renderMana(mc.getWindow().getGuiScaledWidth(), mc.getWindow().getGuiScaledHeight(), mStack);
            }
        });
    }

    public void setupOverlayRenderState(boolean blend, boolean depthText)
    {
        setupOverlayRenderState(blend, depthText, ICON_GUI);
    }

    public void setupOverlayRenderState(boolean blend, boolean depthTest, @Nullable ResourceLocation texture)
    {
        if (blend)
        {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
        }
        else
        {
            RenderSystem.disableBlend();
        }

        if (depthTest)
        {
            RenderSystem.enableDepthTest();
        }
        else
        {
            RenderSystem.disableDepthTest();
        }

        if (texture != null)
        {
            RenderSystem.enableTexture();
            bind(texture);
        }
        else
        {
            RenderSystem.disableTexture();
        }

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
    }


    public static void init()
    {
        MinecraftForge.EVENT_BUS.register(INSTANCE);
    }

    public void renderMana(int width, int height, PoseStack mStack)
    {
        minecraft.getProfiler().push("terra_mana");
        Player player = (Player) this.minecraft.getCameraEntity();
        RenderSystem.enableBlend();

//        AtomicInteger atomicMana = new AtomicInteger();
//        player.getCapability(PlayerMana.PLAYER_MANA_CAPABILITY).ifPresent(cap ->
//        {
//            atomicMana.set(cap.getCurrentMana());
//        });
//        int mana = atomicMana.get();
//        int maxMana = (int) player.getAttributeValue(TerraAttributeRegistry.MANA_MAX.get());
        int mana = PlayerManaClient.getCurrentMana();
        int maxMana = (int) player.getAttributeValue(TerraAttributeRegistry.MANA_MAX.get());

        int manaStorage = (int) Math.floor(maxMana / 10);

        if (mana >= 0)
        {
            int right        = 8;
            int top          = height - right_height;
            int manaPoisoned = player.hasEffect(TerraEffectRegistry.SILENCED.get()) ? 11 : 0;
            int underlay     = 11 * (int) Math.floor(mana / 100);

            for (int crystal = 0; crystal < Math.min(manaStorage, 10); crystal++)
            {
                blit(mStack, width / 2 + right, top, underlay, manaPoisoned, 10, 10);
                right += 8;
            }
            right = 8;

            for (int crystal = 0; crystal < Math.min(manaStorage, 10); crystal ++)
            {
                int layerMana = (int) (mana % 100);
                int crystalMana = crystal * 10;
                if (layerMana > (crystalMana) + 10)
                {
                    blit(mStack, width / 2 + right, top, underlay + 11, manaPoisoned, 10, 10);
                }
                else if (layerMana > crystalMana)
                {
                    blit(mStack, width / 2 + right, top, underlay + 11, manaPoisoned, layerMana - crystalMana, 10);
                }
                right += 8;
            }
        }
        RenderSystem.disableBlend();
        minecraft.getProfiler().pop();
    }

    @Override
    public void render(ForgeIngameGui gui, PoseStack pStack, float partialTicks, int width, int height)
    {
        this.screenWidth = this.minecraft.getWindow().getGuiScaledWidth();
        this.screenHeight = this.minecraft.getWindow().getGuiScaledHeight();
        eventParent = new RenderGameOverlayEvent(pStack, partialTicks, this.minecraft.getWindow());

        if (pre(ALL, pStack))
        {
            return;
        }

        font = minecraft.font;

        this.random.setSeed(tickCount * 312871L);

        OverlayRegistry.orderedEntries().forEach(entry -> {
            try
            {
                if (!entry.isEnabled())
                {
                    return;
                }
                IIngameOverlay overlay = entry.getOverlay();
                if (pre(overlay, pStack))
                {
                    return;
                }
                //overlay.render(this, pStack, partialTicks, screenWidth, screenHeight);
                post(overlay, pStack);
            }
            catch (Exception e)
            {

            }
        });

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        post(ALL, pStack);
    }


    private boolean pre(RenderGameOverlayEvent.ElementType type, PoseStack mStack)
    {
        return MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Pre(mStack, eventParent, type));
    }

    private void post(RenderGameOverlayEvent.ElementType type, PoseStack mStack)
    {
        MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Post(mStack, eventParent, type));
    }

    private boolean pre(IIngameOverlay overlay, PoseStack mStack)
    {
        return MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.PreLayer(mStack, eventParent, overlay));
    }

    private void post(IIngameOverlay overlay, PoseStack mStack)
    {
        MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.PostLayer(mStack, eventParent, overlay));
    }

    private void bind(ResourceLocation res)
    {
        RenderSystem.setShaderTexture(0, res);
    }
}
