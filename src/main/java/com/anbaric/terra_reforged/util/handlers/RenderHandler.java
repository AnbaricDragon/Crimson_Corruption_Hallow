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
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.WALL_BOREAL_LEAF.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.WALL_PALM_LEAF.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.WALL_MAHOGANY_LEAF.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.WALL_EBON_LEAF.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.WALL_SHADE_LEAF.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.WALL_PEARL_LEAF.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.WALL_OAK_LEAF.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.WALL_SPRUCE_LEAF.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.WALL_BIRCH_LEAF.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.WALL_JUNGLE_LEAF.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.WALL_ACACIA_LEAF.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.WALL_DARKOAK_LEAF.get(), cutoutMipped);

        //Plants
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_BLINKROOT.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_DAYBLOOM.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_DEATHWEED.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_FIREBLOSSOM.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_MOONGLOW.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_SHIVERTHORN.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_WATERLEAF.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_MOSS_RED.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_MOSS_YELLOW.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_MOSS_GREEN.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_MOSS_BLUE.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_MOSS_PURPLE.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_MOSS_FIRE.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_MUSHROOM_GLOWING.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_MUSHROOM_VILE.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_MUSHROOM_VICIOUS.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_LIFEFRUIT.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_BLOODROOT.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_DYE_CYAN.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_DYE_ORANGE.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_DYE_PURPLE.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_DYE_RED.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_THORN_JUNGLE.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_THORN_PURPLE.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.PLANT_THORN_RED.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.LEAF_BOREAL.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.LEAF_PALM.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.LEAF_MAHOGANY.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.LEAF_EBON.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.LEAF_SHADE.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.LEAF_PEARL_RED.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.LEAF_PEARL_YELLOW.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.LEAF_PEARL_PINK.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.LEAF_PEARL_MAGENTA.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.LEAF_PEARL_CYAN.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.LEAF_PEARL_BLUE.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.LEAF_PEARL_GREEN.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.LEAF_PEARL_PURPLE.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.CACTUS_CRIM.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.CACTUS_EBON.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.CACTUS_PEARL.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.REED_CORRUPT.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.REED_CRIMSON.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.REED_HALLOWED.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.TALLGRASS_CORRUPT.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.TALLGRASS_CRIMSON.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.TALLGRASS_HALLOWED.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.TALLGRASSDOUBLE_CORRUPT.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.TALLGRASSDOUBLE_CRIMSON.get(), cutoutMipped);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.TALLGRASSDOUBLE_HALLOWED.get(), cutoutMipped);
    }
}