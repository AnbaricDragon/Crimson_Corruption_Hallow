package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.TerraItemGroups;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class TerraItemRegistry
{
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MODID);

    //Materials
    public static final RegistryObject<Item> INGOT_COPPER = ITEMS.register("ingot_copper", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_TIN = ITEMS.register("ingot_tin", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_LEAD = ITEMS.register("ingot_lead", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_TUNGSTEN = ITEMS.register("ingot_tungsten", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_SILVER = ITEMS.register("ingot_silver", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_PLATINUM = ITEMS.register("ingot_platinum", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_DEMONITE = ITEMS.register("ingot_demonite", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_CRIMTANE = ITEMS.register("ingot_crimtane", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_METEORITE = ITEMS.register("ingot_meteorite", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_COBALT = ITEMS.register("ingot_cobalt", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_PALLADIUM = ITEMS.register("ingot_palladium", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_MYTHRIL = ITEMS.register("ingot_mythril", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_ORICHALCUM = ITEMS.register("ingot_orichalcum", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_HELLSTONE = ITEMS.register("ingot_hellstone", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_ADAMANTITE = ITEMS.register("ingot_adamantite", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_TITANIUM = ITEMS.register("ingot_titanium", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_CHLOROPHYTE = ITEMS.register("ingot_chlorophyte", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_SPECTRE = ITEMS.register("ingot_spectre", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_HALLOWED = ITEMS.register("ingot_hallowed", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_SHROOMITE = ITEMS.register("ingot_shroomite", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_LUMINITE = ITEMS.register("ingot_luminite", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));

    //Misc
    public static final RegistryObject<Item> FRUIT_LIFEFRUIT = ITEMS.register("fruit_lifefruit", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> SEED_BLINKROOT = ITEMS.register("seed_blinkroot", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> SEED_DAYBLOOM = ITEMS.register("seed_daybloom", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> SEED_DEATHWEED = ITEMS.register("seed_deathweed", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> SEED_FIREBLOSSOM = ITEMS.register("seed_fireblossom", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> SEED_MOONGLOW = ITEMS.register("seed_moonglow", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> SEED_SHIVERTHORN = ITEMS.register("seed_shiverthorn", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> SEED_WATERLEAF = ITEMS.register("seed_waterleaf", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
}