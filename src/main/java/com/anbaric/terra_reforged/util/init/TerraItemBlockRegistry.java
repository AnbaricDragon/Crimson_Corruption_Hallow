package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.blocks.*;
import com.anbaric.terra_reforged.blocks.potionplants.TerraBlockPotionPlant;
import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TerraItemBlockRegistry
{
    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event)
    {
        final IForgeRegistry<Item> registry = event.getRegistry();
        // Automatically register BlockItems for all our Blocks
        TerraBlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)
                // You can do extra filtering here if you don't want some blocks to have an BlockItem automatically registered for them
                .filter(block ->
                        !(block instanceof TerraBlockPlantReeds) &&
                        !(block instanceof TerraBlockPlantCactus) &&
                        !(block instanceof TerraBlockPlantThorns) &&
                        !(block instanceof TerraBlockPotionPlant) &&
                        !(block instanceof WaterlilyBlock) &&
                        !(block instanceof TerraBlockTorch) &&
                        !(block instanceof TorchBlock) &&
                        block != TerraBlockRegistry.PLANT_MOSS_RED.get() &&
                        block != TerraBlockRegistry.PLANT_MOSS_FIRE.get() &&
                        block != TerraBlockRegistry.PLANT_MOSS_YELLOW.get() &&
                        block != TerraBlockRegistry.PLANT_MOSS_GREEN.get() &&
                        block != TerraBlockRegistry.PLANT_MOSS_BLUE.get() &&
                        block != TerraBlockRegistry.PLANT_MOSS_PURPLE.get() &&
                        block != TerraBlockRegistry.PLANT_LIFEFRUIT.get() &&
                        block != TerraBlockRegistry.ORE_COPPER_CORRUPT.get() && block != TerraBlockRegistry.ORE_COPPER_CRIMSON.get() && block != TerraBlockRegistry.ORE_COPPER_HALLOWED.get() && block != TerraBlockRegistry.ORE_COPPER_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_TIN_CORRUPT.get() && block != TerraBlockRegistry.ORE_TIN_CRIMSON.get() && block != TerraBlockRegistry.ORE_TIN_HALLOWED.get() && block != TerraBlockRegistry.ORE_TIN_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_IRON_CORRUPT.get() && block != TerraBlockRegistry.ORE_IRON_CRIMSON.get() && block != TerraBlockRegistry.ORE_IRON_HALLOWED.get() && block != TerraBlockRegistry.ORE_IRON_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_LEAD_CORRUPT.get() && block != TerraBlockRegistry.ORE_LEAD_CRIMSON.get() && block != TerraBlockRegistry.ORE_LEAD_HALLOWED.get() && block != TerraBlockRegistry.ORE_LEAD_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_SILVER_CORRUPT.get() && block != TerraBlockRegistry.ORE_SILVER_CRIMSON.get() && block != TerraBlockRegistry.ORE_SILVER_HALLOWED.get() && block != TerraBlockRegistry.ORE_SILVER_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_TUNGSTEN_CORRUPT.get() && block != TerraBlockRegistry.ORE_TUNGSTEN_CRIMSON.get() && block != TerraBlockRegistry.ORE_TUNGSTEN_HALLOWED.get() && block != TerraBlockRegistry.ORE_TUNGSTEN_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_GOLD_CORRUPT.get() && block != TerraBlockRegistry.ORE_GOLD_CRIMSON.get() && block != TerraBlockRegistry.ORE_GOLD_HALLOWED.get() && block != TerraBlockRegistry.ORE_GOLD_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_PLATINUM_CORRUPT.get() && block != TerraBlockRegistry.ORE_PLATINUM_CRIMSON.get() && block != TerraBlockRegistry.ORE_PLATINUM_HALLOWED.get() && block != TerraBlockRegistry.ORE_PLATINUM_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_DEMONITE_CORRUPT.get() && block != TerraBlockRegistry.ORE_DEMONITE_CRIMSON.get() && block != TerraBlockRegistry.ORE_DEMONITE_HALLOWED.get() && block != TerraBlockRegistry.ORE_DEMONITE_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_CRIMTANE_CORRUPT.get() && block != TerraBlockRegistry.ORE_CRIMTANE_CRIMSON.get() && block != TerraBlockRegistry.ORE_CRIMTANE_HALLOWED.get() && block != TerraBlockRegistry.ORE_CRIMTANE_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_COBALT_CORRUPT.get() && block != TerraBlockRegistry.ORE_COBALT_CRIMSON.get() && block != TerraBlockRegistry.ORE_COBALT_HALLOWED.get() && block != TerraBlockRegistry.ORE_COBALT_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_PALLADIUM_CORRUPT.get() && block != TerraBlockRegistry.ORE_PALLADIUM_CRIMSON.get() && block != TerraBlockRegistry.ORE_PALLADIUM_HALLOWED.get() && block != TerraBlockRegistry.ORE_PALLADIUM_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_MYTHRIL_CORRUPT.get() && block != TerraBlockRegistry.ORE_MYTHRIL_CRIMSON.get() && block != TerraBlockRegistry.ORE_MYTHRIL_HALLOWED.get() && block != TerraBlockRegistry.ORE_MYTHRIL_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_ORICHALCUM_CORRUPT.get() && block != TerraBlockRegistry.ORE_ORICHALCUM_CRIMSON.get() && block != TerraBlockRegistry.ORE_ORICHALCUM_HALLOWED.get() && block != TerraBlockRegistry.ORE_ORICHALCUM_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_ADAMANTITE_CORRUPT.get() && block != TerraBlockRegistry.ORE_ADAMANTITE_CRIMSON.get() && block != TerraBlockRegistry.ORE_ADAMANTITE_HALLOWED.get() && block != TerraBlockRegistry.ORE_ADAMANTITE_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_TITANIUM_CORRUPT.get() && block != TerraBlockRegistry.ORE_TITANIUM_CRIMSON.get() && block != TerraBlockRegistry.ORE_TITANIUM_HALLOWED.get() && block != TerraBlockRegistry.ORE_TITANIUM_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_COAL_CORRUPT.get() && block != TerraBlockRegistry.ORE_COAL_CRIMSON.get() && block != TerraBlockRegistry.ORE_COAL_HALLOWED.get() && block != TerraBlockRegistry.ORE_COAL_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_LAPIS_CORRUPT.get() && block != TerraBlockRegistry.ORE_LAPIS_CRIMSON.get() && block != TerraBlockRegistry.ORE_LAPIS_HALLOWED.get() && block != TerraBlockRegistry.ORE_LAPIS_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_REDSTONE_CORRUPT.get() && block != TerraBlockRegistry.ORE_REDSTONE_CRIMSON.get() && block != TerraBlockRegistry.ORE_REDSTONE_HALLOWED.get() && block != TerraBlockRegistry.ORE_REDSTONE_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_TOPAZ_CORRUPT.get() && block != TerraBlockRegistry.ORE_TOPAZ_CRIMSON.get() && block != TerraBlockRegistry.ORE_TOPAZ_HALLOWED.get() && block != TerraBlockRegistry.ORE_TOPAZ_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_SAPPHIRE_CORRUPT.get() && block != TerraBlockRegistry.ORE_SAPPHIRE_CRIMSON.get() && block != TerraBlockRegistry.ORE_SAPPHIRE_HALLOWED.get() && block != TerraBlockRegistry.ORE_SAPPHIRE_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_RUBY_CORRUPT.get() && block != TerraBlockRegistry.ORE_RUBY_CRIMSON.get() && block != TerraBlockRegistry.ORE_RUBY_HALLOWED.get() && block != TerraBlockRegistry.ORE_RUBY_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_AMETHYST_CORRUPT.get() && block != TerraBlockRegistry.ORE_AMETHYST_CRIMSON.get() && block != TerraBlockRegistry.ORE_AMETHYST_HALLOWED.get() && block != TerraBlockRegistry.ORE_AMETHYST_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_AMBER_CORRUPT.get() && block != TerraBlockRegistry.ORE_AMBER_CRIMSON.get() && block != TerraBlockRegistry.ORE_AMBER_HALLOWED.get() && block != TerraBlockRegistry.ORE_AMBER_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_DIAMOND_CORRUPT.get() && block != TerraBlockRegistry.ORE_DIAMOND_CRIMSON.get() && block != TerraBlockRegistry.ORE_DIAMOND_HALLOWED.get() && block != TerraBlockRegistry.ORE_DIAMOND_JUNGLE.get() &&
                        block != TerraBlockRegistry.ORE_EMERALD_CORRUPT.get() && block != TerraBlockRegistry.ORE_EMERALD_CRIMSON.get() && block != TerraBlockRegistry.ORE_EMERALD_HALLOWED.get() && block != TerraBlockRegistry.ORE_EMERALD_JUNGLE.get()
                                                )
                // Register the BlockItem for the block
                .forEach(block -> {
                    // Make the properties, and make it so that the item will be on our ItemGroup (CreativeTab)
                    final Item.Properties properties = new Item.Properties().tab(
                            block instanceof LeavesBlock ||
                            block instanceof DoublePlantBlock ||
                            block instanceof TerraBlockPlantHanging ||
                            block instanceof SnowLayerBlock ||
                            block instanceof TerraBlockEvilMushroom ||
                            block instanceof VineBlock
                                ? TerraReforged.TERRA_DECORATIONS_TAB :

//                          block instanceof TerraBlockDoubleLantern
//                              ? TerraReforged.TERRA_MECHANICS_TAB :

                                TerraReforged.TERRA_BLOCKS_TAB);
                    // Create the new BlockItem with the block and it's properties
                    final BlockItem blockItem = new BlockItem(block, properties);
                    // Set the new BlockItem's registry name to the block's registry name
                    blockItem.setRegistryName(block.getRegistryName());
                    // Register the BlockItem
                    registry.register(blockItem);
                });
    }
}
