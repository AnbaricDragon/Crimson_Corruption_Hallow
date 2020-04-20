package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.TerraItemGroups;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.WallOrFloorItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class TerraItemRegistry
{
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MODID);

    //Blocks
    public static final RegistryObject<Item> TORCH_GEM_RED = ITEMS.register("torch_gem_red", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_GEM_RED.get(), TerraBlockRegistry.TORCH_GEM_RED_WALL.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_GEM_ORANGE = ITEMS.register("torch_gem_orange", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_GEM_ORANGE.get(), TerraBlockRegistry.TORCH_GEM_ORANGE_WALL.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_GEM_YELLOW = ITEMS.register("torch_gem_yellow", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_GEM_YELLOW.get(), TerraBlockRegistry.TORCH_GEM_YELLOW_WALL.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_GEM_GREEN = ITEMS.register("torch_gem_green", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_GEM_GREEN.get(), TerraBlockRegistry.TORCH_GEM_GREEN_WALL.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_GEM_BLUE = ITEMS.register("torch_gem_blue", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_GEM_BLUE.get(), TerraBlockRegistry.TORCH_GEM_BLUE_WALL.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_GEM_PURPLE = ITEMS.register("torch_gem_purple", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_GEM_PURPLE.get(), TerraBlockRegistry.TORCH_GEM_PURPLE_WALL.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_GEM_WHITE = ITEMS.register("torch_gem_white", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_GEM_WHITE.get(), TerraBlockRegistry.TORCH_GEM_WHITE_WALL.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_RAINBOW = ITEMS.register("torch_rainbow", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_RAINBOW.get(), TerraBlockRegistry.TORCH_RAINBOW_WALL.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_ICE = ITEMS.register("torch_ice", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_ICE.get(), TerraBlockRegistry.TORCH_ICE_WALL.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_BONE = ITEMS.register("torch_bone", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_BONE.get(), TerraBlockRegistry.TORCH_BONE_WALL.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_BRIGHT = ITEMS.register("torch_bright", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_BRIGHT.get(), TerraBlockRegistry.TORCH_BRIGHT_WALL.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_DEMON = ITEMS.register("torch_demon", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_DEMON.get(), TerraBlockRegistry.TORCH_DEMON_WALL.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_CURSED = ITEMS.register("torch_cursed", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_CURSED.get(), TerraBlockRegistry.TORCH_CURSED_WALL.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_ICHOR = ITEMS.register("torch_ichor", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_ICHOR.get(), TerraBlockRegistry.TORCH_ICHOR_WALL.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));

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