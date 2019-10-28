package com.anbaric.terra_reforged;

import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = Reference.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class TerraEventSubscriber
{
    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)), "example_ore")
        );
    }

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll
        (
            setup(new Item(new Item.Properties()), "example_item")
        );
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name)
    {
        return setup(entry, new ResourceLocation(Reference.MODID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName)
    {
        entry.setRegistryName(registryName);
        return entry;
    }
}