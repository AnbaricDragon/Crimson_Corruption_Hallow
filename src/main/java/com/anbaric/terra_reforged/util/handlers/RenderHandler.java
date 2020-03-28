package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.blocks.TerraBlockOre;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
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
        RenderType cutout = RenderType.getCutout();

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

        //Ores

        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_TIN.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_COPPER.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_TIN.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_LEAD.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_IRON.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_SILVER.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_TUNGSTEN.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_GOLD.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_PLATINUM.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_METEORITE.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_DEMONITE.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_CRIMTANE.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_HELLSTONE.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_COBALT.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_PALLADIUM.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_MYTHRIL.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_ORICHALCUM.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_ADAMANTITE.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_TITANIUM.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_COAL.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_LAPIS.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_REDSTONE.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_AMETHYST.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_TOPAZ.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_SAPPHIRE.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_RUBY.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_EMERALD.get(), cutout);
        RenderTypeLookup.setRenderLayer(TerraBlockRegistry.ORE_DIAMOND.get(), cutout);
    }
}