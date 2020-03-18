package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(value= {Dist.CLIENT}, modid = Reference.MODID, bus = EventBusSubscriber.Bus.MOD)
public class RenderHandler
{
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        RenderType translucent = RenderType.getTranslucent();
        RenderType cutoutMipped = RenderType.getCutoutMipped();

        //Blocks
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.GRASS_MUSHROOM.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.STONE_MOSS_FIRE.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ICE_PURPLE.get(), translucent);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ICE_RED.get(), translucent);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ICE_PINK.get(), translucent);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ICE_THIN.get(), translucent);
    }
}