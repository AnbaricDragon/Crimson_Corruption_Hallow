package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.items.TerraItemLilyPad;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.TerraItemGroups;
import com.anbaric.terra_reforged.util.handlers.MaterialHandler;
import net.minecraft.item.*;
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

    public static final RegistryObject<Item> PLANT_BLINKROOT = ITEMS.register("plant_blinkroot", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> PLANT_DAYBLOOM = ITEMS.register("plant_daybloom", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> PLANT_DEATHWEED = ITEMS.register("plant_deathweed", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> PLANT_FIREBLOSSOM = ITEMS.register("plant_fireblossom", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> PLANT_MOONGLOW = ITEMS.register("plant_moonglow", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> PLANT_SHIVERTHORN = ITEMS.register("plant_shiverthorn", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> PLANT_WATERLEAF = ITEMS.register("plant_waterleaf", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_MATERIALS_TAB)));

    //Misc
    public static final RegistryObject<Item> FRUIT_LIFEFRUIT = ITEMS.register("fruit_lifefruit", () -> new Item(new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> SEED_BLINKROOT = ITEMS.register("seed_blinkroot", () -> new BlockNamedItem(TerraBlockRegistry.PLANT_BLINKROOT.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> SEED_DAYBLOOM = ITEMS.register("seed_daybloom", () -> new BlockNamedItem(TerraBlockRegistry.PLANT_DAYBLOOM.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> SEED_DEATHWEED = ITEMS.register("seed_deathweed", () -> new BlockNamedItem(TerraBlockRegistry.PLANT_DEATHWEED.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> SEED_FIREBLOSSOM = ITEMS.register("seed_fireblossom", () -> new BlockNamedItem(TerraBlockRegistry.PLANT_FIREBLOSSOM.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> SEED_MOONGLOW = ITEMS.register("seed_moonglow", () -> new BlockNamedItem(TerraBlockRegistry.PLANT_MOONGLOW.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> SEED_SHIVERTHORN = ITEMS.register("seed_shiverthorn", () -> new BlockNamedItem(TerraBlockRegistry.PLANT_SHIVERTHORN.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> SEED_WATERLEAF = ITEMS.register("seed_waterleaf", () -> new BlockNamedItem(TerraBlockRegistry.PLANT_WATERLEAF.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> LILYPAD_CORRUPT = ITEMS.register("lilypad_corrupt", () -> new TerraItemLilyPad(TerraBlockRegistry.LILYPAD_CORRUPT.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> LILYPAD_CRIMSON = ITEMS.register("lilypad_crimson", () -> new TerraItemLilyPad(TerraBlockRegistry.LILYPAD_CRIMSON.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> LILYPAD_HALLOWED = ITEMS.register("lilypad_hallowed", () -> new TerraItemLilyPad(TerraBlockRegistry.LILYPAD_HALLOWED.get(), new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB)));

    //Tools

    //Pickaxes
    public static final RegistryObject<Item> PICKAXE_CACTUS = ITEMS.register("pickaxe_cactus", () -> new PickaxeItem(MaterialHandler.CACTUS, 1, -2.8F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_COPPER = ITEMS.register("pickaxe_copper", () -> new PickaxeItem(MaterialHandler.COPPER, 1, -2.8F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_TIN = ITEMS.register("pickaxe_tin", () -> new PickaxeItem(MaterialHandler.TIN, 1, -2.8F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_LEAD = ITEMS.register("pickaxe_lead", () -> new PickaxeItem(MaterialHandler.LEAD, 1, -2.8F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_SILVER = ITEMS.register("pickaxe_silver", () -> new PickaxeItem(MaterialHandler.SILVER, 1, -2.8F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_TUNGSTEN = ITEMS.register("pickaxe_tungsten", () -> new PickaxeItem(MaterialHandler.TUNGSTEN, 1, -2.8F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_GOLD = ITEMS.register("pickaxe_gold", () -> new PickaxeItem(MaterialHandler.GOLD, 1, -2.6F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_CANDY = ITEMS.register("pickaxe_candy", () -> new PickaxeItem(MaterialHandler.CANDY, 1, -2.6F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_FOSSIL = ITEMS.register("pickaxe_fossil", () -> new PickaxeItem(MaterialHandler.FOSSIL, 1, -2.6F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_BONE = ITEMS.register("pickaxe_bone", () -> new PickaxeItem(MaterialHandler.BONE, 1, -2.6F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_SHARK = ITEMS.register("pickaxe_shark", () -> new PickaxeItem(MaterialHandler.SHARK, 1, -2.6F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_PLATINUM = ITEMS.register("pickaxe_platinum", () -> new PickaxeItem(MaterialHandler.PLATINUM, 1, -2.6F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_NIGHTMARE = ITEMS.register("pickaxe_nightmare", () -> new PickaxeItem(MaterialHandler.DEMONITE, 1, -2.4F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_DEATHBRINGER = ITEMS.register("pickaxe_deathbringer", () -> new PickaxeItem(MaterialHandler.CRIMTANE, 1, -2.4F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_MOLTEN = ITEMS.register("pickaxe_molten", () -> new PickaxeItem(MaterialHandler.MOLTEN, 1, -3.5F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_COBALT = ITEMS.register("pickaxe_cobalt", () -> new PickaxeItem(MaterialHandler.COBALT, 1, -2.2F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_PALLADIUM = ITEMS.register("pickaxe_palladium", () -> new PickaxeItem(MaterialHandler.PALLADIUM, 1, -2.2F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_MYTHRIL = ITEMS.register("pickaxe_mythril", () -> new PickaxeItem(MaterialHandler.MYTHRIL, 1, -2.2F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_ORICHALCUM = ITEMS.register("pickaxe_orichalcum", () -> new PickaxeItem(MaterialHandler.ORICHALCUM, 1, -2.2F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_ADAMANTITE = ITEMS.register("pickaxe_adamantite", () -> new PickaxeItem(MaterialHandler.ADAMANTITE, 1, -2.0F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_TITANIUM = ITEMS.register("pickaxe_titanium", () -> new PickaxeItem(MaterialHandler.TITANIUM, 1, -2.0F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_SPECTRE = ITEMS.register("pickaxe_spectre", () -> new PickaxeItem(MaterialHandler.SPECTRE, 1, -2.0F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_CHLOROPHYTE = ITEMS.register("pickaxe_chlorophyte", () -> new PickaxeItem(MaterialHandler.CHLOROPHYTE, 1, -2.0F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_HALLOWED = ITEMS.register("pickaxe_hallowed", () -> new PickaxeItem(MaterialHandler.HALLOWED, 1, -2.0F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_SHROOMITE = ITEMS.register("pickaxe_shroomite", () -> new PickaxeItem(MaterialHandler.SHROOMITE, 1, -1.5F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_JUNGLE = ITEMS.register("pickaxe_jungle", () -> new PickaxeItem(MaterialHandler.JUNGLE, 1, -2.0F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_VORTEX = ITEMS.register("pickaxe_vortex", () -> new PickaxeItem(MaterialHandler.LUMINITE, 1, -2.0F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_NEBULA = ITEMS.register("pickaxe_nebula", () -> new PickaxeItem(MaterialHandler.LUMINITE, 1, -2.0F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_SOLAR = ITEMS.register("pickaxe_solar", () -> new PickaxeItem(MaterialHandler.LUMINITE, 1, -2.0F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_STARDUST = ITEMS.register("pickaxe_stardust", () -> new PickaxeItem(MaterialHandler.LUMINITE, 1, -2.0F, new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB)));
    //Weapons

    //Swords
    public static final RegistryObject<Item> SWORD_COPPER = ITEMS.register("sword_copper", () -> new SwordItem(MaterialHandler.COPPER,  3, -2.4F, new Item.Properties().group(TerraItemGroups.TERRA_WEAPONS_TAB)));
}