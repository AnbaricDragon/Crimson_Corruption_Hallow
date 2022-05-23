package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.capabilities.player_mana.TerraMana;
import com.anbaric.terra_reforged.items.TerraItemInfo;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.handlers.InfoFunctionHandler;
import com.google.common.util.concurrent.AtomicDouble;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;

public class TerraOverlayRegistry
{
    private static final ResourceLocation ICON_GUI = new ResourceLocation("terra_reforged:textures/gui/gui_icon.png");

    public static final Component[] WEATHER_COMPONENTS =
    {
        new TranslatableComponent("overlay.terra_reforged.info_weather_clear"),
        new TranslatableComponent("overlay.terra_reforged.info_weather_rain"),
        new TranslatableComponent("overlay.terra_reforged.info_weather_storm")
    };

    public static final Component[] MOON_PHASE_COMPONENTS =
    {
        new TranslatableComponent("overlay.terra_reforged.info_moon_0"),
        new TranslatableComponent("overlay.terra_reforged.info_moon_1"),
        new TranslatableComponent("overlay.terra_reforged.info_moon_2"),
        new TranslatableComponent("overlay.terra_reforged.info_moon_3"),
        new TranslatableComponent("overlay.terra_reforged.info_moon_4"),
        new TranslatableComponent("overlay.terra_reforged.info_moon_5"),
        new TranslatableComponent("overlay.terra_reforged.info_moon_6"),
        new TranslatableComponent("overlay.terra_reforged.info_moon_7")
    };

    public static void register()
    {
        OverlayRegistry.registerOverlayAbove(ForgeIngameGui.HOTBAR_ELEMENT, "terra_mana_overlay", MANA_LEVEL_ELEMENT);
        OverlayRegistry.registerOverlayAbove(ForgeIngameGui.HOTBAR_ELEMENT, "terra_lava_overlay", LAVA_PROTECTION_ELEMENT);
        OverlayRegistry.registerOverlayAbove(ForgeIngameGui.HOTBAR_ELEMENT, "terra_shield_overlay", SHIELD_DASH_ELEMENT);
        OverlayRegistry.registerOverlayAbove(ForgeIngameGui.HOTBAR_ELEMENT, "terra_info_overlay", ITEM_INFO_OVERLAY);
    }

    public static final IIngameOverlay MANA_LEVEL_ELEMENT = OverlayRegistry.registerOverlayTop("Mana Level", (gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        if (!gui.minecraft.options.hideGui && gui.shouldDrawSurvivalElements())
        {
            gui.setupOverlayRenderState(true, false);
            LocalPlayer player = gui.minecraft.player;

            AtomicDouble currentMana = new AtomicDouble(0);
            player.getCapability(TerraMana.TERRA_MANA_CAPABILITY).ifPresent(cap -> {
                currentMana.set(cap.getCurrentMana());
            });

            double mana        = currentMana.get();
            double maxMana     = player.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).getValue();
            int    manaStorage = (int) Math.floor(maxMana / 10);

            if (mana >= 0)
            {
                bind(ICON_GUI);
                int manaPoisoned = player.hasEffect(TerraEffectRegistry.SILENCED.get()) ? 11 : 0;
                int underwater   = player.isEyeInFluid(FluidTags.WATER) || player.getAirSupply() < 300 ? -10 : 0;
                int width        = Minecraft.getInstance().getWindow().getGuiScaledWidth();
                int height       = Minecraft.getInstance().getWindow().getGuiScaledHeight();
                int right        = 8;
                int top          = height - 50 + underwater;

                RenderSystem.enableBlend();
                int underlay = 11 * (int) Math.floor(mana / 100);
                for (int crystal = 0; crystal < Math.min(manaStorage, 10); crystal++)
                {
                    gui.blit(poseStack, width / 2 + right, top, underlay, manaPoisoned, 10, 10);
                    right += 8;
                }
                right = 8;

                for (int crystal = 0; crystal < Math.min(manaStorage, 10); crystal++)
                {
                    int layerMana   = (int) (mana % 100);
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

    public static final IIngameOverlay LAVA_PROTECTION_ELEMENT = OverlayRegistry.registerOverlayTop("Lava Protection Level", (gui, poseStack, partialTick, screenWidth, screenHeight) ->
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

                    if (charge < 140 || cooldown > 0)
                    {
                        int width  = Minecraft.getInstance().getWindow().getGuiScaledWidth();
                        int height = Minecraft.getInstance().getWindow().getGuiScaledHeight();

                        int count   = (int) Math.floor(charge / 14F);
                        int armored = gui.minecraft.player.getArmorValue() == 0 ? 0 : -10;

                        int left = width / 2 - 92;
                        int top  = (height - 50) + armored;

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
                    }
                }
            }
        }
    });

    public static final IIngameOverlay SHIELD_DASH_ELEMENT = OverlayRegistry.registerOverlayTop("Shield Dash Level", (gui, poseStack, partialTick, screenWidth, screenHeight) ->
    {
        if (!gui.minecraft.options.hideGui)
        {
            gui.setupOverlayRenderState(true, false);
            bind(ICON_GUI);
            LocalPlayer player = gui.minecraft.player;

            ItemStack shieldCharger = CurioHandler.getStack(player, TerraTagRegistry.TABI_DASHERS);

            if (!shieldCharger.isEmpty())
            {
                CompoundTag compound = shieldCharger.getTag();
                if (compound != null)
                {
                    int cooldown = compound.getInt("tabiCooldown");

                    int left = 100;
                    int top  = screenHeight - 50;

                    RenderSystem.enableBlend();
                    float countFloat = cooldown / 40 + 1;
                    RenderSystem.setShaderColor(1, 1, 1, countFloat % ((int) countFloat));
                    gui.blit(poseStack, screenWidth / 2 - left, top, 0, 0, 10, 10);
                    RenderSystem.disableBlend();
                }
            }
        }
    });

    public static final IIngameOverlay ITEM_INFO_OVERLAY = OverlayRegistry.registerOverlayTop("Item Info Level", (gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        if (!gui.minecraft.options.hideGui)
        {
            bind(ICON_GUI);
            LocalPlayer player = gui.minecraft.player;
            Window      window = gui.minecraft.getWindow();

            ItemStack offHand = player.getOffhandItem();
            if (offHand.is(TerraTagRegistry.INFO_TELLERS))
            {
                int boxWidth = 9;
                int boxLeft = ((screenWidth / 2) + (screenWidth / 4)) - (screenWidth / 35);
                int top  = screenHeight - (gui.getFont().lineHeight * 5);
                RenderSystem.enableBlend();
                RenderSystem.setShaderColor(1, 1, 1, 0.8F);

                //Make Box
                for (int x = 0; x <= boxWidth; x++)
                {
                    for (int y = 0; y <= 2; y++)
                    {
                        if (x == 0)
                        {
                            if (y == 0)
                            {
                                gui.blit(poseStack, boxLeft, top, 55, 0, 11, 11);
                            }
                            else if (y == 2)
                            {
                                gui.blit(poseStack, boxLeft, top, 55, 11, 11, 11);
                            }
                            else
                            {
                                gui.blit(poseStack, boxLeft, top, 55, 5, 11, 11);
                            }
                        }
                        else if (x == boxWidth)
                        {
                            if (y == 0)
                            {
                                gui.blit(poseStack, boxLeft, top, 66, 0, 11, 11);
                            }
                            else if (y == 2)
                            {
                                gui.blit(poseStack, boxLeft, top, 66, 11, 11, 11);
                            }
                            else
                            {
                                gui.blit(poseStack, boxLeft, top, 66, 5, 11, 11);
                            }
                        }
                        else
                        {
                            if (y == 0)
                            {
                                gui.blit(poseStack, boxLeft, top, 60, 0, 11, 11);
                            }
                            else if (y == 2)
                            {
                                gui.blit(poseStack, boxLeft, top, 60, 11, 11, 11);
                            }
                            else
                            {
                                gui.blit(poseStack, boxLeft, top, 60, 5, 11, 11);
                            }
                        }
                        top += 11;
                    }
                    top = window.getGuiScaledHeight() - (gui.getFont().lineHeight * 5);
                    boxLeft += 11;
                }

                //Count and Draw Pip Icons, Then Write Data
                CompoundTag compound = offHand.getShareTag();
                offHand.setTag(compound);
                CompoundTag test = offHand.getShareTag();
                int pipLeft = ((screenWidth >>> 1) + (screenWidth >>> 2)) - (screenWidth / 40) + ((boxWidth * 11) >>> 1);
                top = window.getGuiScaledHeight() - (gui.getFont().lineHeight * 3);
                RenderSystem.setShaderColor(1, 1, 1, 1);
                if (compound.equals(test))
                {
                    int functionIndex = compound.getInt("function_index");
                    int bitCount = Integer.bitCount(functionIndex);
                    int activeIndex = compound.getInt("active_index");

                    if (bitCount > 0)
                    {
                        int activePip = 1 << activeIndex;
                        int lowest_bit_index      = Integer.numberOfTrailingZeros(functionIndex);
                        int highest_available_bit = Integer.highestOneBit(functionIndex);

                        // Named constants are cool
                        int PIP_SMALL_WIDTH = 8;
                        int PIP_LARGE_WIDTH = 10;
                        int PIP_SPRITE_SIZE = 11;

                        // Calculate initial X offset into sprite sheet
                        int pip_sprite_offset = lowest_bit_index * PIP_SPRITE_SIZE + 77;

                        // Calculate the initial offset to render
                        // from, accounting for one pip being wider.
                        int pip_render_offset = pipLeft - ((bitCount - 1) * PIP_SMALL_WIDTH + PIP_LARGE_WIDTH >>> 1) + 4;

                        // Make mask for selected

                        for (
                                int pip_mask = 1 << lowest_bit_index; // Turn bit index into mask
                                pip_mask <= highest_available_bit; pip_mask <<= 1 // Shift mask
                        )
                        {
                            // Only render this pip if it's available
                            if ((pip_mask & functionIndex) != 0)
                            {
                                // Make the selected pip wider
                                int current_width = pip_mask != activePip ? PIP_SMALL_WIDTH : PIP_LARGE_WIDTH;
                                gui.blit(poseStack, pip_render_offset, top, pip_sprite_offset, pip_mask != activePip ? 0 : PIP_SPRITE_SIZE, current_width, PIP_SPRITE_SIZE);
                                // Move render position to the right according to width
                                pip_render_offset += current_width;
                            }
                            // Sprite sheet offset needs to be incremented regardless of whether anything was rendered
                            pip_sprite_offset += PIP_SPRITE_SIZE;
                        }
                    }

                    //Write Data

                    int dataLeft = (((screenWidth >> 1) + screenWidth / 4) - screenWidth / 35) + ((boxWidth * 11) >>> 1) + 6;
                    top = window.getGuiScaledHeight() - (gui.getFont().lineHeight * 4);
                    Font font = gui.getFont();
                    if (functionIndex >> InfoFunctionHandler.Functions.DEPTH_METER.ordinal() != 0 && activeIndex == InfoFunctionHandler.Functions.DEPTH_METER.ordinal())
                    {
                        int depth = (int) player.position().y;

                        Component toDisplay =
                            new TranslatableComponent("overlay.terra_reforged.info_depth",
                            new TextComponent(Integer.toString(
                                player.level.dimension() == Level.OVERWORLD ?
                                    depth - player.level.getSeaLevel() :
                                    depth)),
                            player.level.dimension() == Level.NETHER ?
                                new TranslatableComponent("overlay.terra_reforged.info_depth_nether") :
                            player.level.dimension() == Level.END ?
                                new TranslatableComponent("overlay.terra_reforged.info_depth_end") :
                            player.level.dimension() == Level.OVERWORLD ?
                                depth >= player.level.getSeaLevel() ?
                                    new TranslatableComponent("overlay.terra_reforged.info_depth_surface") :
                                    new TranslatableComponent("overlay.terra_reforged.info_depth_underground") :
                                new TranslatableComponent("overlay.terra_reforged.info_depth_unknown").withStyle(ChatFormatting.OBFUSCATED)
                        );
                        gui.getFont().draw(poseStack, toDisplay, dataLeft - (font.width(toDisplay) / 2), top, 0xFFFFFF);
                    }
                    else if (functionIndex >> InfoFunctionHandler.Functions.CLOCK.ordinal() != 0 && activeIndex == InfoFunctionHandler.Functions.CLOCK.ordinal())
                    {
                        int time = (int) ((player.level.getDayTime() % 24000) + 6000) % 24000;
                        int hours = time / 1000;
                        double MINUTES_PER_TICK = (double) 60 / 1000;
                        int minutes = (int) Math.floor((time % 1000) * MINUTES_PER_TICK);
                        int twelveFix = hours % 12 == 0 ? 12 : hours % 12;
                        Component toDisplay =
                            new TranslatableComponent("overlay.terra_reforged.info_clock",
                            new TranslatableComponent(Integer.toString(twelveFix)),
                            new TranslatableComponent(String.format("%0" + 2 + "d", minutes)),
                            hours < 12 ?
                                new TranslatableComponent("overlay.terra_reforged.info_clock_am") :
                                new TranslatableComponent("overlay.terra_reforged.info_clock_pm"));
                        //Component toDisplay =  new TranslatableComponent("overlay.terra_reforged.info_depth_surface", new TextComponent(Integer.toString(depth)));
                        gui.getFont().draw(poseStack, toDisplay, dataLeft - (font.width(toDisplay) / 2), top, 0xFFFFFF);
                    }
                    else if (functionIndex >> InfoFunctionHandler.Functions.COMPASS.ordinal() != 0 && activeIndex == InfoFunctionHandler.Functions.COMPASS.ordinal())
                    {
                        int xCoord = (int) player.position().x;
                        int zCoord = (int) player.position().z;

                        Component toDisplay =
                            new TranslatableComponent("overlay.terra_reforged.info_compass",
                            new TextComponent(Integer.toString(xCoord)),
                            new TextComponent(Integer.toString(zCoord)));
                        gui.getFont().draw(poseStack, toDisplay, dataLeft - (font.width(toDisplay) / 2), top, 0xFFFFFF);
                    }
                    else if (functionIndex >> InfoFunctionHandler.Functions.RADAR.ordinal() != 0 && activeIndex == InfoFunctionHandler.Functions.RADAR.ordinal())
                    {
                        int mobCount = compound.getInt(InfoFunctionHandler.Functions.RADAR.getTag());
                        Component toDisplay = new TranslatableComponent("overlay.terra_reforged.info_radar", new TextComponent(Integer.toString(mobCount)));
                        gui.getFont().draw(poseStack, toDisplay, dataLeft - (font.width(toDisplay) / 2), top, 0xFFFFFF);
                    }
                    else if (functionIndex >> InfoFunctionHandler.Functions.TALLY_COUNTER.ordinal() != 0 && activeIndex == InfoFunctionHandler.Functions.TALLY_COUNTER.ordinal())
                    {
                        String tallyMob = compound.getString(InfoFunctionHandler.Functions.TALLY_COUNTER.getTag() + "_name");
                        int tallyCount = compound.getInt(InfoFunctionHandler.Functions.TALLY_COUNTER.getTag() + "_number");
                        String translationName = tallyMob.replace(':', '.');
                        Component toDisplay = new TranslatableComponent("overlay.terra_reforged.info_tally", new TranslatableComponent("entity." + translationName), new TextComponent(Integer.toString(tallyCount)));
                        gui.getFont().draw(poseStack, toDisplay, dataLeft - (font.width(toDisplay) / 2), top, 0xFFFFFF);
                    }
                    else if (functionIndex >> InfoFunctionHandler.Functions.MOB_FINDER.ordinal() != 0 && activeIndex == InfoFunctionHandler.Functions.MOB_FINDER.ordinal())
                    {
                        //TODO Add rarity levels for monsters
                        Component toDisplay = new TextComponent("Function Not Found").withStyle(ChatFormatting.DARK_RED);
                        gui.getFont().draw(poseStack, toDisplay, dataLeft - (font.width(toDisplay) / 2), top, 0xFFFFFF);
                    }
                    else if (functionIndex >> InfoFunctionHandler.Functions.DPS_METER.ordinal() != 0 && activeIndex == InfoFunctionHandler.Functions.DPS_METER.ordinal())
                    {
                        int length = compound.getIntArray(InfoFunctionHandler.Functions.DPS_METER.getTag() + "_damage").length;
                        float damage = 0;
                        for (int i = 0; i < length; i++)
                        {
                            damage += compound.getIntArray(InfoFunctionHandler.Functions.DPS_METER.getTag() + "_damage")[i];
                        }
                        damage /= 5;
                        float roundedDamage = (Math.round(damage * 10)) / 10;
                        Component toDisplay = new TranslatableComponent("overlay.terra_reforged.info_dps", new TextComponent(Float.toString(roundedDamage)));
                        gui.getFont().draw(poseStack, toDisplay, dataLeft - (font.width(toDisplay) / 2), top, 0xFFFFFF);
                    }
                    else if (functionIndex >> InfoFunctionHandler.Functions.SPEED_METER.ordinal() != 0 && activeIndex == InfoFunctionHandler.Functions.SPEED_METER.ordinal())
                    {
                        double distance = compound.getDouble(InfoFunctionHandler.Functions.SPEED_METER.getTag() + "_distance");
                        double formattedDistance = (Math.round(distance * 10)) / 10;
                        Component toDisplay = new TranslatableComponent("overlay.terra_reforged.info_speed", new TextComponent(String.valueOf(formattedDistance)));
                        gui.getFont().draw(poseStack, toDisplay, dataLeft - (font.width(toDisplay) / 2), top, 0xFFFFFF);
                    }
                    else if (functionIndex >> InfoFunctionHandler.Functions.METAL_FINDER.ordinal() != 0 && activeIndex == InfoFunctionHandler.Functions.METAL_FINDER.ordinal())
                    {
                        Component toDisplay = new TranslatableComponent(compound.getString(InfoFunctionHandler.Functions.METAL_FINDER.getTag()));
                        gui.getFont().draw(poseStack, toDisplay, dataLeft - (font.width(toDisplay) / 2), top, 0xFFFFFF);
                    }
                    else if (functionIndex >> InfoFunctionHandler.Functions.FISH_FINDER.ordinal() != 0 && activeIndex == InfoFunctionHandler.Functions.FISH_FINDER.ordinal())
                    {
                        Component toDisplay = new TextComponent("Function Not Found").withStyle(ChatFormatting.DARK_RED);
                        gui.getFont().draw(poseStack, toDisplay, dataLeft - (font.width(toDisplay) / 2), top, 0xFFFFFF);
                    }
                    else if (functionIndex >> InfoFunctionHandler.Functions.WEATHER.ordinal() != 0 && activeIndex == InfoFunctionHandler.Functions.WEATHER.ordinal())
                    {
                        int weather = 0;
                        if (player.level.isRaining()) weather = 1;
                        if (player.level.isThundering()) weather = 2;
                        gui.getFont().draw(poseStack, WEATHER_COMPONENTS[weather], dataLeft - (font.width(WEATHER_COMPONENTS[weather]) / 2), top, 0xFFFFFF);
                    }
                    else if (functionIndex >> InfoFunctionHandler.Functions.MOON_FINDER.ordinal() != 0 && activeIndex == InfoFunctionHandler.Functions.MOON_FINDER.ordinal())
                    {
                        int moonPhase = player.level.getMoonPhase();
                        gui.getFont().draw(poseStack, MOON_PHASE_COMPONENTS[moonPhase], dataLeft - (font.width(MOON_PHASE_COMPONENTS[moonPhase]) / 2), top, 0xFFFFFF);
                    }
                }
                RenderSystem.disableBlend();
            }
        }
    });

    private static void bind(ResourceLocation res)
    {
        RenderSystem.setShaderTexture(0, res);
    }
}