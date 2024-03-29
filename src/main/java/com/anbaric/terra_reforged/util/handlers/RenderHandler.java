package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.items.models.TerraFlyingCarpetModel;
import com.anbaric.terra_reforged.items.models.TerraStagedWingModel;
import com.anbaric.terra_reforged.items.models.TerraWingModel;
import com.anbaric.terra_reforged.items.renders.TerraFlyingCarpetRenderer;
import com.anbaric.terra_reforged.items.renders.TerraStagedWingRenderer;
import com.anbaric.terra_reforged.items.renders.TerraWingRenderer;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import java.util.function.Supplier;

@Mod.EventBusSubscriber
(value = {Dist.CLIENT}, modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RenderHandler
{
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void setRenderLayer(FMLClientSetupEvent event)
    {
        RenderType translucent  = RenderType.translucent();
        RenderType cutoutMipped = RenderType.cutoutMipped();
        RenderType cutout       = RenderType.cutout();

        //Blocks
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.GRASS_JUNGLE.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.GRASS_MUSHROOM.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.STONE_MOSS_FIRE.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.MUSHROOM_CAP.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ICE_PURPLE.get(), translucent);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ICE_RED.get(), translucent);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ICE_PINK.get(), translucent);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ICE_THIN.get(), translucent);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.WALL_BOREAL_LEAF.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.WALL_PALM_LEAF.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.WALL_MAHOGANY_LEAF.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.WALL_EBON_LEAF.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.WALL_SHADE_LEAF.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.WALL_PEARL_LEAF.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.WALL_OAK_LEAF.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.WALL_SPRUCE_LEAF.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.WALL_BIRCH_LEAF.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.WALL_JUNGLE_LEAF.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.WALL_ACACIA_LEAF.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.WALL_DARKOAK_LEAF.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_GEM_RED.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_GEM_RED_WALL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_GEM_ORANGE.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_GEM_ORANGE_WALL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_GEM_YELLOW.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_GEM_YELLOW_WALL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_GEM_GREEN.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_GEM_GREEN_WALL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_GEM_BLUE.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_GEM_BLUE_WALL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_GEM_PURPLE.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_GEM_PURPLE_WALL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_GEM_WHITE.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_GEM_WHITE_WALL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_RAINBOW.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_RAINBOW_WALL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_ICE.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_ICE_WALL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_PINK.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_PINK_WALL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_BONE.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_BONE_WALL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_BRIGHT.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_BRIGHT_WALL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_DEMON.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_DEMON_WALL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_CURSED.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_CURSED_WALL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_ICHOR.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_ICHOR_WALL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_DESERT.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_DESERT_WALL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_CORAL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_CORAL_WALL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_CORRUPT.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_CORRUPT_WALL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_CRIMSON.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_CRIMSON_WALL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_HALLOWED.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_HALLOWED_WALL.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_JUNGLE.get(), cutout);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TORCH_JUNGLE_WALL.get(), cutout);
//        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TALL_LANTERN_FIREFLY.get(), translucent);
//        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.TALL_LANTERN_LIGHTNINGBUG.get(), translucent);
//        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.LANTERN_HEART.get(), cutout);
//
//        //Plants
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_BLINKROOT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_DAYBLOOM.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_DEATHWEED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_FIREBLOSSOM.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_MOONGLOW.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_SHIVERTHORN.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_WATERLEAF.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_MOSS_RED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_MOSS_YELLOW.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_MOSS_GREEN.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_MOSS_BLUE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_MOSS_PURPLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_MOSS_FIRE.get(), cutoutMipped);
//        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_MUSHROOM_GLOWING.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_MUSHROOM_VILE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_MUSHROOM_VICIOUS.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_LIFEFRUIT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_BLOODROOT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_DYE_CYAN.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_DYE_ORANGE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_DYE_PURPLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_DYE_RED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_THORN_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_THORN_PURPLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.PLANT_THORN_RED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.LEAF_BOREAL.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.LEAF_PALM.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.LEAF_MAHOGANY.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.LEAF_EBON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.LEAF_SHADE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.LEAF_PEARL_RED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.LEAF_PEARL_YELLOW.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.LEAF_PEARL_PINK.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.LEAF_PEARL_MAGENTA.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.LEAF_PEARL_CYAN.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.LEAF_PEARL_BLUE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.LEAF_PEARL_GREEN.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.LEAF_PEARL_PURPLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.CACTUS_CRIM.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.CACTUS_EBON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.CACTUS_PEARL.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.REED_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.REED_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.REED_HALLOWED.get(), cutoutMipped);
//        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.SAPLING_BOREAL.get(), cutoutMipped);
//        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.SAPLING_PALM.get(), cutoutMipped);
//        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.SAPLING_MAHOGANY.get(), cutoutMipped);
//        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.SAPLING_EBON.get(), cutoutMipped);
//        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.SAPLING_SHADE.get(), cutoutMipped);
//        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.SAPLING_PEARL.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.LILYPAD_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.LILYPAD_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.LILYPAD_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.VINE_BLUE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.VINE_CYAN.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.VINE_GREEN.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.VINE_MAGENTA.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.VINE_PINK.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.VINE_PURPLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.VINE_RED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.VINE_YELLOW.get(), cutoutMipped);
//
//        //Ores
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_TIN_PURE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_TIN_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_TIN_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_TIN_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_TIN_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_COPPER_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_COPPER_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_COPPER_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_COPPER_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_LEAD_PURE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_LEAD_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_LEAD_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_LEAD_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_LEAD_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_IRON_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_IRON_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_IRON_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_IRON_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_SILVER_PURE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_SILVER_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_SILVER_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_SILVER_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_SILVER_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_TUNGSTEN_PURE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_TUNGSTEN_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_TUNGSTEN_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_TUNGSTEN_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_TUNGSTEN_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_GOLD_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_GOLD_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_GOLD_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_GOLD_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_PLATINUM_PURE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_PLATINUM_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_PLATINUM_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_PLATINUM_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_PLATINUM_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_DEMONITE_PURE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_DEMONITE_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_DEMONITE_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_DEMONITE_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_DEMONITE_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_CRIMTANE_PURE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_CRIMTANE_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_CRIMTANE_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_CRIMTANE_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_CRIMTANE_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_COBALT_PURE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_COBALT_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_COBALT_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_COBALT_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_COBALT_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_PALLADIUM_PURE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_PALLADIUM_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_PALLADIUM_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_PALLADIUM_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_PALLADIUM_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_MYTHRIL_PURE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_MYTHRIL_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_MYTHRIL_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_MYTHRIL_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_MYTHRIL_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_ORICHALCUM_PURE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_ORICHALCUM_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_ORICHALCUM_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_ORICHALCUM_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_ORICHALCUM_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_ADAMANTITE_PURE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_ADAMANTITE_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_ADAMANTITE_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_ADAMANTITE_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_ADAMANTITE_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_TITANIUM_PURE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_TITANIUM_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_TITANIUM_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_TITANIUM_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_TITANIUM_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_COAL_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_COAL_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_COAL_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_COAL_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_LAPIS_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_LAPIS_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_LAPIS_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_LAPIS_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_REDSTONE_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_REDSTONE_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_REDSTONE_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_REDSTONE_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_AMBER_PURE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_AMBER_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_AMBER_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_AMBER_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_AMBER_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_AMETHYST_PURE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_AMETHYST_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_AMETHYST_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_AMETHYST_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_AMETHYST_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_TOPAZ_PURE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_TOPAZ_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_TOPAZ_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_TOPAZ_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_TOPAZ_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_SAPPHIRE_PURE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_SAPPHIRE_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_SAPPHIRE_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_SAPPHIRE_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_SAPPHIRE_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_RUBY_PURE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_RUBY_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_RUBY_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_RUBY_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_RUBY_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_AMBER_PURE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_AMBER_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_AMBER_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_AMBER_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_AMBER_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_EMERALD_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_EMERALD_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_EMERALD_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_EMERALD_JUNGLE.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_DIAMOND_CORRUPT.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_DIAMOND_CRIMSON.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_DIAMOND_HALLOWED.get(), cutoutMipped);
        ItemBlockRenderTypes.setRenderLayer(TerraBlockRegistry.ORE_DIAMOND_JUNGLE.get(), cutoutMipped);

        //Items
        CuriosRendererRegistry.register(TerraItemRegistry.FLYING_CARPET.get(), () -> new TerraFlyingCarpetRenderer("flying_carpet", TerraFlyingCarpetModel.createFlyingCarpet()));
        CuriosRendererRegistry.register(TerraItemRegistry.WINGS_FLEDGELING.get(), () -> new TerraWingRenderer("fledgeling_wings", TerraWingModel.createFledgelingWing()));
        CuriosRendererRegistry.register(TerraItemRegistry.WINGS_ANGEL.get(), () -> new TerraStagedWingRenderer("angel_wings", TerraStagedWingModel.createExtendedAngelWings(), TerraStagedWingModel.createRestingAngelWings()));
        //CuriosRendererRegistry.register(TerraItemRegistry.WINGS_DEMON.get(), () -> new TerraStagedWingRenderer("demon_wings", TerraStagedWingModel.createExtendedDemonWings(), TerraStagedWingModel.createRestingDemonWings()));
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        register(event, LayerHandler.FLYING_CARPET, TerraFlyingCarpetModel::createCarpetLayer);
        register(event, LayerHandler.FLEDGELING_WINGS, layer(TerraWingModel.createWingModel(), 64, 32));
        register(event, LayerHandler.ANGEL_WINGS_EXTENDED, TerraStagedWingModel::createExtendedWingLayer);
        register(event, LayerHandler.ANGEL_WINGS_RESTING, TerraStagedWingModel::createRestingWingLayer);
        register(event, LayerHandler.DEMON_WINGS_EXTENDED, TerraStagedWingModel::createExtendedWingLayer);
        register(event, LayerHandler.DEMON_WINGS_RESTING, TerraStagedWingModel::createRestingWingLayer);
    }

    public static ModelPart bakeLayer(ModelLayerLocation layerLocation) {
        return Minecraft.getInstance().getEntityModels().bakeLayer(layerLocation);
    }

    private static void register(EntityRenderersEvent.RegisterLayerDefinitions event, ModelLayerLocation layerLocation, Supplier<LayerDefinition> layer) {
        event.registerLayerDefinition(layerLocation, layer);
    }

    private static Supplier<LayerDefinition> layer(MeshDefinition mesh, int textureWidth, int textureHeight) {
        return () -> LayerDefinition.create(mesh, textureWidth, textureHeight);
    }
}