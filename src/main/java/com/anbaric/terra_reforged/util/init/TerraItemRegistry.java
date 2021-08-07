package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.items.*;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.TerraItemGroups;
import com.anbaric.terra_reforged.util.handlers.MaterialHandler;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class TerraItemRegistry
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);

    //Blocks
    public static final RegistryObject<Item> TORCH_BONE = ITEMS.register("torch_bone", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_BONE.get(), TerraBlockRegistry.TORCH_BONE_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_BRIGHT = ITEMS.register("torch_bright", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_BRIGHT.get(), TerraBlockRegistry.TORCH_BRIGHT_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_CORAL = ITEMS.register("torch_coral", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_CORAL.get(), TerraBlockRegistry.TORCH_CORAL_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_CORRUPT = ITEMS.register("torch_corrupt", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_CORRUPT.get(), TerraBlockRegistry.TORCH_CORRUPT_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_CRIMSON = ITEMS.register("torch_crimson", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_CRIMSON.get(), TerraBlockRegistry.TORCH_CRIMSON_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_CURSED = ITEMS.register("torch_cursed", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_CURSED.get(), TerraBlockRegistry.TORCH_CURSED_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_DEMON = ITEMS.register("torch_demon", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_DEMON.get(), TerraBlockRegistry.TORCH_DEMON_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_DESERT = ITEMS.register("torch_desert", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_DESERT.get(), TerraBlockRegistry.TORCH_DESERT_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_GEM_BLUE = ITEMS.register("torch_gem_blue", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_GEM_BLUE.get(), TerraBlockRegistry.TORCH_GEM_BLUE_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_GEM_GREEN = ITEMS.register("torch_gem_green", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_GEM_GREEN.get(), TerraBlockRegistry.TORCH_GEM_GREEN_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_GEM_ORANGE = ITEMS.register("torch_gem_orange", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_GEM_ORANGE.get(), TerraBlockRegistry.TORCH_GEM_ORANGE_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_GEM_PURPLE = ITEMS.register("torch_gem_purple", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_GEM_PURPLE.get(), TerraBlockRegistry.TORCH_GEM_PURPLE_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_GEM_RED = ITEMS.register("torch_gem_red", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_GEM_RED.get(), TerraBlockRegistry.TORCH_GEM_RED_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_GEM_WHITE = ITEMS.register("torch_gem_white", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_GEM_WHITE.get(), TerraBlockRegistry.TORCH_GEM_WHITE_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_GEM_YELLOW = ITEMS.register("torch_gem_yellow", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_GEM_YELLOW.get(), TerraBlockRegistry.TORCH_GEM_YELLOW_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_HALLOWED = ITEMS.register("torch_hallowed", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_HALLOWED.get(), TerraBlockRegistry.TORCH_HALLOWED_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_ICE = ITEMS.register("torch_ice", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_ICE.get(), TerraBlockRegistry.TORCH_ICE_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_ICHOR = ITEMS.register("torch_ichor", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_ICHOR.get(), TerraBlockRegistry.TORCH_ICHOR_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_JUNGLE = ITEMS.register("torch_jungle", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_JUNGLE.get(), TerraBlockRegistry.TORCH_JUNGLE_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_PINK = ITEMS.register("torch_pink", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_PINK.get(), TerraBlockRegistry.TORCH_PINK_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> TORCH_RAINBOW = ITEMS.register("torch_rainbow", () -> new WallOrFloorItem(TerraBlockRegistry.TORCH_RAINBOW.get(), TerraBlockRegistry.TORCH_RAINBOW_WALL.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    //Materials
    public static final RegistryObject<Item> INGOT_COPPER = ITEMS.register("ingot_copper", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_TIN = ITEMS.register("ingot_tin", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_LEAD = ITEMS.register("ingot_lead", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_TUNGSTEN = ITEMS.register("ingot_tungsten", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_SILVER = ITEMS.register("ingot_silver", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_PLATINUM = ITEMS.register("ingot_platinum", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_DEMONITE = ITEMS.register("ingot_demonite", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_CRIMTANE = ITEMS.register("ingot_crimtane", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_METEORITE = ITEMS.register("ingot_meteorite", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_COBALT = ITEMS.register("ingot_cobalt", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_PALLADIUM = ITEMS.register("ingot_palladium", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_MYTHRIL = ITEMS.register("ingot_mythril", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_ORICHALCUM = ITEMS.register("ingot_orichalcum", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_HELLSTONE = ITEMS.register("ingot_hellstone", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_ADAMANTITE = ITEMS.register("ingot_adamantite", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_TITANIUM = ITEMS.register("ingot_titanium", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_CHLOROPHYTE = ITEMS.register("ingot_chlorophyte", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_SPECTRE = ITEMS.register("ingot_spectre", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_HALLOWED = ITEMS.register("ingot_hallowed", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_SHROOMITE = ITEMS.register("ingot_shroomite", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> INGOT_LUMINITE = ITEMS.register("ingot_luminite", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));

    public static final RegistryObject<Item> PLANT_BLINKROOT = ITEMS.register("plant_blinkroot", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> PLANT_DAYBLOOM = ITEMS.register("plant_daybloom", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> PLANT_DEATHWEED = ITEMS.register("plant_deathweed", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> PLANT_FIREBLOSSOM = ITEMS.register("plant_fireblossom", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> PLANT_MOONGLOW = ITEMS.register("plant_moonglow", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> PLANT_SHIVERTHORN = ITEMS.register("plant_shiverthorn", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));
    public static final RegistryObject<Item> PLANT_WATERLEAF = ITEMS.register("plant_waterleaf", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_MATERIALS_TAB)));

    //Misc
    public static final RegistryObject<Item> MANA_CRYSTAL = ITEMS.register("mana_crystal", () -> new TerraItemManaCrystal(new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> FRUIT_LIFEFRUIT = ITEMS.register("fruit_lifefruit", () -> new Item(new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> SEED_BLINKROOT = ITEMS.register("seed_blinkroot", () -> new BlockNamedItem(TerraBlockRegistry.PLANT_BLINKROOT.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> SEED_DAYBLOOM = ITEMS.register("seed_daybloom", () -> new BlockNamedItem(TerraBlockRegistry.PLANT_DAYBLOOM.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> SEED_DEATHWEED = ITEMS.register("seed_deathweed", () -> new BlockNamedItem(TerraBlockRegistry.PLANT_DEATHWEED.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> SEED_FIREBLOSSOM = ITEMS.register("seed_fireblossom", () -> new BlockNamedItem(TerraBlockRegistry.PLANT_FIREBLOSSOM.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> SEED_MOONGLOW = ITEMS.register("seed_moonglow", () -> new BlockNamedItem(TerraBlockRegistry.PLANT_MOONGLOW.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> SEED_SHIVERTHORN = ITEMS.register("seed_shiverthorn", () -> new BlockNamedItem(TerraBlockRegistry.PLANT_SHIVERTHORN.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> SEED_WATERLEAF = ITEMS.register("seed_waterleaf", () -> new BlockNamedItem(TerraBlockRegistry.PLANT_WATERLEAF.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> LILYPAD_CORRUPT = ITEMS.register("lilypad_corrupt", () -> new TerraItemLilyPad(TerraBlockRegistry.LILYPAD_CORRUPT.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> LILYPAD_CRIMSON = ITEMS.register("lilypad_crimson", () -> new TerraItemLilyPad(TerraBlockRegistry.LILYPAD_CRIMSON.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));
    public static final RegistryObject<Item> LILYPAD_HALLOWED = ITEMS.register("lilypad_hallowed", () -> new TerraItemLilyPad(TerraBlockRegistry.LILYPAD_HALLOWED.get(), new Item.Properties().tab(TerraItemGroups.TERRA_DECORATIONS_TAB)));

    //Tools

    //Accessories
    public static final RegistryObject<Item> AGLET = ITEMS.register("aglet", () -> new TerraItemAccessorySpeed(0.05F));
    public static final RegistryObject<Item> ANKLET_OF_WIND = ITEMS.register("anklet_wind", () -> new TerraItemAccessorySpeed(0.10F));
    public static final RegistryObject<Item> BALLOON_BLIZZARD = ITEMS.register("balloon_blizzard", () -> new TerraItemExtraJumpBalloon());
    public static final RegistryObject<Item> BALLOON_BUNDLE = ITEMS.register("balloon_bundle", () -> new TerraItemBalloonBundle());
    public static final RegistryObject<Item> BALLOON_CLOUD = ITEMS.register("balloon_cloud", () -> new TerraItemExtraJumpBalloon());
    public static final RegistryObject<Item> BALLOON_FART = ITEMS.register("balloon_fart", () -> new TerraItemExtraJumpBalloon());
    public static final RegistryObject<Item> BALLOON_HONEY = ITEMS.register("balloon_honey", () -> new TerraItemHoneyBalloon());
    public static final RegistryObject<Item> BALLOON_PUFFERFISH = ITEMS.register("balloon_pufferfish", () -> new TerraItemBalloon());
    public static final RegistryObject<Item> BALLOON_RED = ITEMS.register("balloon_red", () -> new TerraItemBalloon());
    public static final RegistryObject<Item> BALLOON_SANDSTORM = ITEMS.register("balloon_sandstorm", () -> new TerraItemExtraJumpBalloon());
    public static final RegistryObject<Item> BALLOON_SHARK = ITEMS.register("balloon_shark", () -> new TerraItemExtraJumpBalloon());
    public static final RegistryObject<Item> BELT_BLACK = ITEMS.register("belt_black", () -> new TerraItemBlackBelt());
    public static final RegistryObject<Item> BOOTS_AMPHIBIAN = ITEMS.register("boots_amphibian", () -> new TerraItemBootsAmphibian());
    public static final RegistryObject<Item> BOOTS_DUNERIDER = ITEMS.register("boots_dunerider", () -> new TerraItemBootsDunerider());
    public static final RegistryObject<Item> BOOTS_FAIRY = ITEMS.register("boots_fairy", () -> new TerraItemBootsFairy());
    public static final RegistryObject<Item> BOOTS_FLOWER = ITEMS.register("boots_flower", () -> new TerraItemBootsFlower());
    public static final RegistryObject<Item> BOOTS_FLURRY = ITEMS.register("boots_flurry", () -> new TerraItemAccessorySpeed(0.20F));
    public static final RegistryObject<Item> BOOTS_FROSTSPARK = ITEMS.register("boots_frostspark", () -> new TerraItemBootsFrostspark());
    public static final RegistryObject<Item> BOOTS_HELLFIRE = ITEMS.register("boots_hellfire", () -> new TerraItemBootsLava());
    public static final RegistryObject<Item> BOOTS_HERMES = ITEMS.register("boots_hermes", () -> new TerraItemAccessorySpeed(0.20F));
    public static final RegistryObject<Item> BOOTS_ICE = ITEMS.register("boots_ice", () -> new TerraItemBootsIce());
    public static final RegistryObject<Item> BOOTS_LAVA = ITEMS.register("boots_lava", () -> new TerraItemBootsLava());
    public static final RegistryObject<Item> BOOTS_LIGHTNING = ITEMS.register("boots_lightning", () -> new TerraItemBootsLightning());
    public static final RegistryObject<Item> BOOTS_OBSIDIAN = ITEMS.register("boots_obsidian", () -> new TerraItemBootsObsidian());
    public static final RegistryObject<Item> BOOTS_ROCKET = ITEMS.register("boots_rocket", () -> new TerraItemBootsRocket());
    public static final RegistryObject<Item> BOOTS_SAILFISH = ITEMS.register("boots_sailfish", () -> new TerraItemAccessorySpeed(0.20F));
    public static final RegistryObject<Item> BOOTS_SPECTRE = ITEMS.register("boots_spectre", () -> new TerraItemBootsSpectre());
    public static final RegistryObject<Item> BOOTS_TERRASPARK = ITEMS.register("boots_terraspark", () -> new TerraItemBootsTerraspark());
    public static final RegistryObject<Item> BOOTS_WATER = ITEMS.register("boots_water", () -> new TerraItemBootsWater());
    public static final RegistryObject<Item> BOTTLE_BLIZZARD = ITEMS.register("bottle_blizzard", () -> new TerraItemBottle());
    public static final RegistryObject<Item> BOTTLE_CLOUD = ITEMS.register("bottle_cloud", () -> new TerraItemBottle());
    public static final RegistryObject<Item> BOTTLE_FART = ITEMS.register("bottle_fart", () -> new TerraItemBottle());
    public static final RegistryObject<Item> BOTTLE_SANDSTORM = ITEMS.register("bottle_sandstorm", () -> new TerraItemBottle());
    public static final RegistryObject<Item> BOTTLE_TSUNAMI = ITEMS.register("bottle_tsunami", () -> new TerraItemBottle());
    public static final RegistryObject<Item> CHARM_LAVA = ITEMS.register("charm_lava", () -> new TerraItemLavaCharm());
    public static final RegistryObject<Item> CHARM_MOLTEN = ITEMS.register("charm_molten", () -> new TerraItemMoltenCharm());
    public static final RegistryObject<Item> CLIMBING_CLAWS = ITEMS.register("climbing_claws", () -> new TerraItemClimbingGripper());
    public static final RegistryObject<Item> CLIMBING_GEAR = ITEMS.register("climbing_gear", () -> new TerraItemClimbingGear());
    public static final RegistryObject<Item> CLIMBING_SPIKES = ITEMS.register("climbing_spikes", () -> new TerraItemClimbingGripper());
    public static final RegistryObject<Item> FLIPPER = ITEMS.register("flipper", () -> new TerraItemFlipper());
    public static final RegistryObject<Item> FLYING_CARPET = ITEMS.register("flying_carpet", () -> new TerraItemFlyingCarpet());
    public static final RegistryObject<Item> FROG_FLIPPER = ITEMS.register("frog_flipper", () -> new TerraItemFrogFlipper());
    public static final RegistryObject<Item> FROG_GEAR = ITEMS.register("frog_gear", () -> new TerraItemFrogGear());
    public static final RegistryObject<Item> FROG_WEBBING = ITEMS.register("frog_webbing", () -> new TerraItemFrogWebbing());
    public static final RegistryObject<Item> GEAR_DIVING = ITEMS.register("gear_diving", () -> new TerraItemDivingGear());
    public static final RegistryObject<Item> GEAR_DIVING_ARCTIC = ITEMS.register("gear_diving_arctic", () -> new TerraItemDivingGearArctic());
    public static final RegistryObject<Item> GEAR_DIVING_JELLYFISH = ITEMS.register("gear_diving_jellyfish", () -> new TerraItemDivingGearJellyfish());
    public static final RegistryObject<Item> GEAR_NINJA = ITEMS.register("gear_ninja", () -> new TerraItemNinjaGear());
    public static final RegistryObject<Item> HONEYCOMB = ITEMS.register("honeycomb", () -> new TerraItemHoneycomb());
    public static final RegistryObject<Item> HORSESHOE_BALLOON_BLIZZARD = ITEMS.register("horseshoe_balloon_blizzard", () -> new TerraItemHorseshoeBalloon());
    public static final RegistryObject<Item> HORSESHOE_BALLOON_CLOUD = ITEMS.register("horseshoe_balloon_cloud", () -> new TerraItemHorseshoeBalloon());
    public static final RegistryObject<Item> HORSESHOE_BALLOON_FART = ITEMS.register("horseshoe_balloon_fart", () -> new TerraItemHorseshoeBalloon());
    public static final RegistryObject<Item> HORSESHOE_BALLOON_HONEY = ITEMS.register("horseshoe_balloon_honey", () -> new TerraItemAmberHorseshoeBalloon());
    public static final RegistryObject<Item> HORSESHOE_BALLOON_SANDSTORM = ITEMS.register("horseshoe_balloon_sandstorm", () -> new TerraItemHorseshoeBalloon());
    public static final RegistryObject<Item> HORSESHOE_BALLOON_SHARK = ITEMS.register("horseshoe_balloon_shark", () -> new TerraItemHorseshoeBalloon());
    public static final RegistryObject<Item> HORSESHOE_LUCKY = ITEMS.register("horseshoe_lucky", () -> new TerraItemLuckyHorseshoe());
    public static final RegistryObject<Item> HORSESHOE_OBSIDIAN = ITEMS.register("horseshoe_obsidian", () -> new TerraItemObsidianHorseshoe());
    public static final RegistryObject<Item> LEG_FROG = ITEMS.register("leg_frog", () -> new TerraItemFrogLeg());
    public static final RegistryObject<Item> NECKLACE_CROSS = ITEMS.register("necklace_cross", () -> new TerraItemCrossNecklace());
    public static final RegistryObject<Item> NECKLACE_JELLYFISH = ITEMS.register("necklace_jellyfish", () -> new TerraItemJellyfishNecklace());
    public static final RegistryObject<Item> NECKLACE_PANIC = ITEMS.register("necklace_panic", () -> new TerraItemPanicNecklace());
    public static final RegistryObject<Item> NECKLACE_SHARKTOOTH = ITEMS.register("necklace_sharktooth", () -> new TerraItemSharktoothNecklace());
    public static final RegistryObject<Item> NECKLACE_STARVEIL = ITEMS.register("necklace_starveil", () -> new TerraItemStarveil());
    public static final RegistryObject<Item> NECKLACE_STINGER = ITEMS.register("necklace_stinger", () -> new TerraItemStingerNecklace());
    public static final RegistryObject<Item> NECKLACE_SWEETHEART = ITEMS.register("necklace_sweetheart", () -> new TerraItemSweetheartNecklace());
    public static final RegistryObject<Item> NINJA_TABI = ITEMS.register("ninja_tabi", () -> new TerraItemNinjaTabi());
    public static final RegistryObject<Item> ROSE_OBSIDIAN = ITEMS.register("rose_obsidian", () -> new TerraItemObsidianRose());
    public static final RegistryObject<Item> SKULL_MAGMA = ITEMS.register("skull_magma", () -> new TerraItemMagmaSkull());
    public static final RegistryObject<Item> SKULL_MOLTEN = ITEMS.register("skull_molten", () -> new TerraItemMoltenSkull());
    public static final RegistryObject<Item> SKULL_OBSIDIAN = ITEMS.register("skull_obsidian", () -> new TerraItemObsidianSkull());
    public static final RegistryObject<Item> SKULL_ROSE = ITEMS.register("skull_rose", () -> new TerraItemObsidianSkullRose());
    public static final RegistryObject<Item> STONE_MAGMA = ITEMS.register("stone_magma", () -> new TerraItemMagmaStone());

    public static final RegistryObject<Item> VITAMINS = ITEMS.register("vitamins", () -> new TerraItemVitamins());
    public static final RegistryObject<Item> BANDAGE = ITEMS.register("bandage", () -> new TerraItemBandage());
    public static final RegistryObject<Item> NAZAR = ITEMS.register("nazar", () -> new TerraItemNazar());
    public static final RegistryObject<Item> TRIFOLD_MAP = ITEMS.register("trifold_map", () -> new TerraItemTrifoldMap());
    public static final RegistryObject<Item> ARMOR_POLISH = ITEMS.register("armor_polish", () -> new TerraItemArmorPolish());
    public static final RegistryObject<Item> BEZOAR = ITEMS.register("bezoar", () -> new TerraItemBezoar());
    public static final RegistryObject<Item> MEGAPHONE = ITEMS.register("megaphone", () -> new TerraItemMegaphone());
    public static final RegistryObject<Item> FAST_CLOCK = ITEMS.register("fast_clock", () -> new TerraItemFastClock());
    public static final RegistryObject<Item> BLINDFOLD = ITEMS.register("blindfold", () -> new TerraItemBlindfold());
    public static final RegistryObject<Item> ARMOR_BRACING = ITEMS.register("armor_bracing", () -> new TerraItemArmorBracing());
    public static final RegistryObject<Item> MEDICATED_BANDAGE = ITEMS.register("medicated_bandage", () -> new TerraItemMedicatedBandage());
    public static final RegistryObject<Item> COUNTERCURSE_MANTRA = ITEMS.register("countercurse_mantra", () -> new TerraItemCountercurseMantra());
    public static final RegistryObject<Item> THE_PLAN = ITEMS.register("the_plan", () -> new TerraItemPlan());
    public static final RegistryObject<Item> ANKH_CHARM = ITEMS.register("ankh_charm", () -> new TerraItemAnkhCharm());


    //Pickaxes
    public static final RegistryObject<Item> PICKAXE_CACTUS = ITEMS.register("pickaxe_cactus", () -> new PickaxeItem(MaterialHandler.CACTUS, 1, -2.8F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_COPPER = ITEMS.register("pickaxe_copper", () -> new PickaxeItem(MaterialHandler.COPPER, 1, -2.8F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_TIN = ITEMS.register("pickaxe_tin", () -> new PickaxeItem(MaterialHandler.TIN, 1, -2.8F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_LEAD = ITEMS.register("pickaxe_lead", () -> new PickaxeItem(MaterialHandler.LEAD, 1, -2.8F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_SILVER = ITEMS.register("pickaxe_silver", () -> new PickaxeItem(MaterialHandler.SILVER, 1, -2.8F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_TUNGSTEN = ITEMS.register("pickaxe_tungsten", () -> new PickaxeItem(MaterialHandler.TUNGSTEN, 1, -2.8F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_GOLD = ITEMS.register("pickaxe_gold", () -> new PickaxeItem(MaterialHandler.GOLD, 1, -2.6F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_CANDY = ITEMS.register("pickaxe_candy", () -> new PickaxeItem(MaterialHandler.CANDY, 1, -2.6F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_FOSSIL = ITEMS.register("pickaxe_fossil", () -> new PickaxeItem(MaterialHandler.FOSSIL, 1, -2.6F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_BONE = ITEMS.register("pickaxe_bone", () -> new PickaxeItem(MaterialHandler.BONE, 1, -2.6F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_SHARK = ITEMS.register("pickaxe_shark", () -> new PickaxeItem(MaterialHandler.SHARK, 1, -2.6F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_PLATINUM = ITEMS.register("pickaxe_platinum", () -> new PickaxeItem(MaterialHandler.PLATINUM, 1, -2.6F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_NIGHTMARE = ITEMS.register("pickaxe_nightmare", () -> new PickaxeItem(MaterialHandler.DEMONITE, 1, -2.4F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_DEATHBRINGER = ITEMS.register("pickaxe_deathbringer", () -> new PickaxeItem(MaterialHandler.CRIMTANE, 1, -2.4F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_MOLTEN = ITEMS.register("pickaxe_molten", () -> new PickaxeItem(MaterialHandler.MOLTEN, 1, -3.5F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_COBALT = ITEMS.register("pickaxe_cobalt", () -> new PickaxeItem(MaterialHandler.COBALT, 1, -2.2F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_PALLADIUM = ITEMS.register("pickaxe_palladium", () -> new PickaxeItem(MaterialHandler.PALLADIUM, 1, -2.2F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_MYTHRIL = ITEMS.register("pickaxe_mythril", () -> new PickaxeItem(MaterialHandler.MYTHRIL, 1, -2.2F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_ORICHALCUM = ITEMS.register("pickaxe_orichalcum", () -> new PickaxeItem(MaterialHandler.ORICHALCUM, 1, -2.2F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_ADAMANTITE = ITEMS.register("pickaxe_adamantite", () -> new PickaxeItem(MaterialHandler.ADAMANTITE, 1, -2.0F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_TITANIUM = ITEMS.register("pickaxe_titanium", () -> new PickaxeItem(MaterialHandler.TITANIUM, 1, -2.0F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_SPECTRE = ITEMS.register("pickaxe_spectre", () -> new PickaxeItem(MaterialHandler.SPECTRE, 1, -2.0F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_CHLOROPHYTE = ITEMS.register("pickaxe_chlorophyte", () -> new PickaxeItem(MaterialHandler.CHLOROPHYTE, 1, -2.0F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_HALLOWED = ITEMS.register("pickaxe_hallowed", () -> new PickaxeItem(MaterialHandler.HALLOWED, 1, -2.0F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_SHROOMITE = ITEMS.register("pickaxe_shroomite", () -> new PickaxeItem(MaterialHandler.SHROOMITE, 1, -1.5F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_JUNGLE = ITEMS.register("pickaxe_jungle", () -> new PickaxeItem(MaterialHandler.JUNGLE, 1, -2.0F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_VORTEX = ITEMS.register("pickaxe_vortex", () -> new PickaxeItem(MaterialHandler.LUMINITE, 1, -2.0F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_NEBULA = ITEMS.register("pickaxe_nebula", () -> new PickaxeItem(MaterialHandler.LUMINITE, 1, -2.0F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_SOLAR = ITEMS.register("pickaxe_solar", () -> new PickaxeItem(MaterialHandler.LUMINITE, 1, -2.0F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));
    public static final RegistryObject<Item> PICKAXE_STARDUST = ITEMS.register("pickaxe_stardust", () -> new PickaxeItem(MaterialHandler.LUMINITE, 1, -2.0F, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB)));

    //Axes



    //Weapons

    //Swords
    public static final RegistryObject<Item> SWORD_COPPER = ITEMS.register("sword_copper", () -> new SwordItem(MaterialHandler.COPPER,  3, -2.4F, new Item.Properties().tab(TerraItemGroups.TERRA_WEAPONS_TAB)));
    public static final RegistryObject<Item> SWORD_TERRA = ITEMS.register("sword_terra", () -> new TerraProjectileSword(MaterialHandler.TERRA,  30, -2.4F, new Item.Properties().tab(TerraItemGroups.TERRA_WEAPONS_TAB)));

    //Armor
    public static final RegistryObject<Item> HELMET_DIVING = ITEMS.register("helmet_diving", () -> new TerraItemDivingHelmet(ArmorMaterial.CHAIN, EquipmentSlotType.HEAD, new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB).stacksTo(1)));

}