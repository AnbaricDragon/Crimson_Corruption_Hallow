package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.blocks.TerraBlockCactus;
import com.anbaric.terra_reforged.blocks.TerraBlockReeds;
import com.anbaric.terra_reforged.blocks.TerraBlockThornBush;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.TerraItemGroups;
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
                        block != TerraBlockRegistry.PLANT_MOSS_RED.get() &&
                        block != TerraBlockRegistry.PLANT_MOSS_FIRE.get() &&
                        block != TerraBlockRegistry.PLANT_MOSS_YELLOW.get() &&
                        block != TerraBlockRegistry.PLANT_MOSS_GREEN.get() &&
                        block != TerraBlockRegistry.PLANT_MOSS_BLUE.get() &&
                        block != TerraBlockRegistry.PLANT_MOSS_PURPLE.get())
                // Register the BlockItem for the block
                .forEach(block -> {
                    // Make the properties, and make it so that the item will be on our ItemGroup (CreativeTab)
                    final Item.Properties properties = new Item.Properties().group(TerraItemGroups.TERRA_BLOCKS_TAB);
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
