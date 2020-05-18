package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.blocks.*;
import com.anbaric.terra_reforged.blocks.potionplants.TerraBlockPotionPlant;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.TerraItemGroups;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TerraItemBlockRegistry
{
    private static final Logger LOGGER = LogManager.getLogger(Reference.MODID + " ItemBlock Registry");

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event)
    {
        final IForgeRegistry<Item> registry = event.getRegistry();
        // Automatically register BlockItems for all our Blocks
        TerraBlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
                // You can do extra filtering here if you don't want some blocks to have an BlockItem automatically registered for them
                .filter(block ->
                        !(block instanceof TerraBlockReeds) &&
                        !(block instanceof TerraBlockCactus) &&
                        !(block instanceof TerraBlockThornBush) &&
                        !(block instanceof TerraBlockPotionPlant) &&
                        block != TerraBlockRegistry.PLANT_MOSS_RED.get() &&
                        block != TerraBlockRegistry.PLANT_MOSS_FIRE.get() &&
                        block != TerraBlockRegistry.PLANT_MOSS_YELLOW.get() &&
                        block != TerraBlockRegistry.PLANT_MOSS_GREEN.get() &&
                        block != TerraBlockRegistry.PLANT_MOSS_BLUE.get() &&
                        block != TerraBlockRegistry.PLANT_MOSS_PURPLE.get() &&
                        block != TerraBlockRegistry.PLANT_LIFEFRUIT.get() &&
                        block != TerraBlockRegistry.ORE_COPPER_CORRUPT.get() && block != TerraBlockRegistry.ORE_COPPER_CRIMSON.get() && block != TerraBlockRegistry.ORE_COPPER_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_TIN_CORRUPT.get() && block != TerraBlockRegistry.ORE_TIN_CRIMSON.get() && block != TerraBlockRegistry.ORE_TIN_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_IRON_CORRUPT.get() && block != TerraBlockRegistry.ORE_IRON_CRIMSON.get() && block != TerraBlockRegistry.ORE_IRON_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_LEAD_CORRUPT.get() && block != TerraBlockRegistry.ORE_LEAD_CRIMSON.get() && block != TerraBlockRegistry.ORE_LEAD_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_SILVER_CORRUPT.get() && block != TerraBlockRegistry.ORE_SILVER_CRIMSON.get() && block != TerraBlockRegistry.ORE_SILVER_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_TUNGSTEN_CORRUPT.get() && block != TerraBlockRegistry.ORE_TUNGSTEN_CRIMSON.get() && block != TerraBlockRegistry.ORE_TUNGSTEN_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_GOLD_CORRUPT.get() && block != TerraBlockRegistry.ORE_GOLD_CRIMSON.get() && block != TerraBlockRegistry.ORE_GOLD_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_PLATINUM_CORRUPT.get() && block != TerraBlockRegistry.ORE_PLATINUM_CRIMSON.get() && block != TerraBlockRegistry.ORE_PLATINUM_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_DEMONITE_CORRUPT.get() && block != TerraBlockRegistry.ORE_DEMONITE_CRIMSON.get() && block != TerraBlockRegistry.ORE_DEMONITE_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_CRIMTANE_CORRUPT.get() && block != TerraBlockRegistry.ORE_CRIMTANE_CRIMSON.get() && block != TerraBlockRegistry.ORE_CRIMTANE_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_COBALT_CORRUPT.get() && block != TerraBlockRegistry.ORE_COBALT_CRIMSON.get() && block != TerraBlockRegistry.ORE_COBALT_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_PALLADIUM_CORRUPT.get() && block != TerraBlockRegistry.ORE_PALLADIUM_CRIMSON.get() && block != TerraBlockRegistry.ORE_PALLADIUM_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_MYTHRIL_CORRUPT.get() && block != TerraBlockRegistry.ORE_MYTHRIL_CRIMSON.get() && block != TerraBlockRegistry.ORE_MYTHRIL_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_ORICHALCUM_CORRUPT.get() && block != TerraBlockRegistry.ORE_ORICHALCUM_CRIMSON.get() && block != TerraBlockRegistry.ORE_ORICHALCUM_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_ADAMANTITE_CORRUPT.get() && block != TerraBlockRegistry.ORE_ADAMANTITE_CRIMSON.get() && block != TerraBlockRegistry.ORE_ADAMANTITE_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_TITANIUM_CORRUPT.get() && block != TerraBlockRegistry.ORE_TITANIUM_CRIMSON.get() && block != TerraBlockRegistry.ORE_TITANIUM_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_COAL_CORRUPT.get() && block != TerraBlockRegistry.ORE_COAL_CRIMSON.get() && block != TerraBlockRegistry.ORE_COAL_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_LAPIS_CORRUPT.get() && block != TerraBlockRegistry.ORE_LAPIS_CRIMSON.get() && block != TerraBlockRegistry.ORE_LAPIS_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_REDSTONE_CORRUPT.get() && block != TerraBlockRegistry.ORE_REDSTONE_CRIMSON.get() && block != TerraBlockRegistry.ORE_REDSTONE_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_TOPAZ_CORRUPT.get() && block != TerraBlockRegistry.ORE_TOPAZ_CRIMSON.get() && block != TerraBlockRegistry.ORE_TOPAZ_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_SAPPHIRE_CORRUPT.get() && block != TerraBlockRegistry.ORE_SAPPHIRE_CRIMSON.get() && block != TerraBlockRegistry.ORE_SAPPHIRE_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_RUBY_CORRUPT.get() && block != TerraBlockRegistry.ORE_RUBY_CRIMSON.get() && block != TerraBlockRegistry.ORE_RUBY_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_AMETHYST_CORRUPT.get() && block != TerraBlockRegistry.ORE_AMETHYST_CRIMSON.get() && block != TerraBlockRegistry.ORE_AMETHYST_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_DIAMOND_CORRUPT.get() && block != TerraBlockRegistry.ORE_DIAMOND_CRIMSON.get() && block != TerraBlockRegistry.ORE_DIAMOND_HALLOWED.get() &&
                        block != TerraBlockRegistry.ORE_EMERALD_CORRUPT.get() && block != TerraBlockRegistry.ORE_EMERALD_CRIMSON.get() && block != TerraBlockRegistry.ORE_EMERALD_HALLOWED.get() &&
                        block != TerraBlockRegistry.TORCH_GEM_RED.get() && block != TerraBlockRegistry.TORCH_GEM_RED_WALL.get() &&
                        block != TerraBlockRegistry.TORCH_GEM_ORANGE.get() && block != TerraBlockRegistry.TORCH_GEM_ORANGE_WALL.get() &&
                        block != TerraBlockRegistry.TORCH_GEM_YELLOW.get() && block != TerraBlockRegistry.TORCH_GEM_YELLOW_WALL.get() &&
                        block != TerraBlockRegistry.TORCH_GEM_GREEN.get() && block != TerraBlockRegistry.TORCH_GEM_GREEN_WALL.get() &&
                        block != TerraBlockRegistry.TORCH_GEM_BLUE.get() && block != TerraBlockRegistry.TORCH_GEM_BLUE_WALL.get() &&
                        block != TerraBlockRegistry.TORCH_GEM_PURPLE.get() && block != TerraBlockRegistry.TORCH_GEM_PURPLE_WALL.get() &&
                        block != TerraBlockRegistry.TORCH_GEM_WHITE.get() && block != TerraBlockRegistry.TORCH_GEM_WHITE_WALL.get() &&
                        block != TerraBlockRegistry.TORCH_RAINBOW.get() && block != TerraBlockRegistry.TORCH_RAINBOW_WALL.get() &&
                        block != TerraBlockRegistry.TORCH_ICE.get() && block != TerraBlockRegistry.TORCH_ICE_WALL.get() &&
                        block != TerraBlockRegistry.TORCH_BONE.get() && block != TerraBlockRegistry.TORCH_BONE_WALL.get() &&
                        block != TerraBlockRegistry.TORCH_BRIGHT.get() && block != TerraBlockRegistry.TORCH_BRIGHT_WALL.get() &&
                        block != TerraBlockRegistry.TORCH_DEMON.get() && block != TerraBlockRegistry.TORCH_DEMON_WALL.get() &&
                        block != TerraBlockRegistry.TORCH_CURSED.get() && block != TerraBlockRegistry.TORCH_CURSED_WALL.get() &&
                        block != TerraBlockRegistry.TORCH_ICHOR.get() && block != TerraBlockRegistry.TORCH_ICHOR_WALL.get())
                // Register the BlockItem for the block
                .forEach(block -> {
                    // Make the properties, and make it so that the item will be on our ItemGroup (CreativeTab)
                    final Item.Properties properties = new Item.Properties().group(
                            block instanceof LeavesBlock ||
                            block instanceof TerraBlockTallGrass ||
                            block instanceof DoublePlantBlock ||
                            block instanceof TerraBlockWall ||
                            block instanceof TerraBlockHangingPlant ||
                            block instanceof TerraBlockSnowLayer
                            ? TerraItemGroups.TERRA_DECORATIONS_TAB :

                            block instanceof TerraBlockHangingLantern
                            ? TerraItemGroups.TERRA_MECHANICS_TAB :

                            TerraItemGroups.TERRA_BLOCKS_TAB);
                    // Create the new BlockItem with the block and it's properties
                    final BlockItem blockItem = new BlockItem(block, properties);
                    // Set the new BlockItem's registry name to the block's registry name
                    blockItem.setRegistryName(block.getRegistryName());
                    // Register the BlockItem
                    registry.register(blockItem);
                });
        LOGGER.debug("Registered ItemBlocks");
    }
}
