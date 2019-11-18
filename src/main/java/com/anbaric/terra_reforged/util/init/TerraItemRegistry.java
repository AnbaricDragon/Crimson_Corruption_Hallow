package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.TerraItemGroups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@EventBusSubscriber(modid = Reference.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class TerraItemRegistry
{
    private static final Logger LOGGER = LogManager.getLogger(Reference.MODID + " Mod Event Subscriber");

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event)
    {
        final IForgeRegistry<Item> registry = event.getRegistry();

        ItemGroup materials = TerraItemGroups.TERRA_MATERIALS_TAB;
        ItemGroup tools = TerraItemGroups.TERRA_TOOLS_TAB;
        ItemGroup decorations = TerraItemGroups.TERRA_DECORATIONS_TAB;

        registry.registerAll
        (
            //Materials
            setup(new Item(new Item.Properties().group(materials)), "ingot_copper"),
            setup(new Item(new Item.Properties().group(materials)), "ingot_tin"),
            setup(new Item(new Item.Properties().group(materials)), "ingot_lead"),
            setup(new Item(new Item.Properties().group(materials)), "ingot_tungsten"),
            setup(new Item(new Item.Properties().group(materials)), "ingot_silver"),
            setup(new Item(new Item.Properties().group(materials)), "ingot_platinum"),
            setup(new Item(new Item.Properties().group(materials)), "ingot_demonite"),
            setup(new Item(new Item.Properties().group(materials)), "ingot_crimtane"),
            setup(new Item(new Item.Properties().group(materials)), "ingot_meteorite"),
            setup(new Item(new Item.Properties().group(materials)), "ingot_cobalt"),
            setup(new Item(new Item.Properties().group(materials)), "ingot_palladium"),
            setup(new Item(new Item.Properties().group(materials)), "ingot_mythril"),
            setup(new Item(new Item.Properties().group(materials)), "ingot_orichalcum"),
            setup(new Item(new Item.Properties().group(materials)), "ingot_hellstone"),
            setup(new Item(new Item.Properties().group(materials)), "ingot_adamantite"),
            setup(new Item(new Item.Properties().group(materials)), "ingot_titanium"),
            setup(new Item(new Item.Properties().group(materials)), "ingot_chlorophyte"),
            setup(new Item(new Item.Properties().group(materials)), "ingot_spectre"),
            setup(new Item(new Item.Properties().group(materials)), "ingot_hallowed"),
            setup(new Item(new Item.Properties().group(materials)), "ingot_shroomite"),
            setup(new Item(new Item.Properties().group(materials)), "ingot_luminite"),

            //Misc
            setup(new Item(new Item.Properties().group(tools)), "fruit_lifefruit"),
            setup(new Item(new Item.Properties().group(decorations)), "seed_blinkroot"),
            setup(new Item(new Item.Properties().group(decorations)), "seed_daybloom"),
            setup(new Item(new Item.Properties().group(decorations)), "seed_deathweed"),
            setup(new Item(new Item.Properties().group(decorations)), "seed_fireblossom"),
            setup(new Item(new Item.Properties().group(decorations)), "seed_moonglow"),
            setup(new Item(new Item.Properties().group(decorations)), "seed_shiverthorn"),
            setup(new Item(new Item.Properties().group(decorations)), "seed_waterleaf")
        );
        LOGGER.debug("Registered Items");
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
