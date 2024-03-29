package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class TerraTagRegistry
{
    public static final TagKey<Block> MOSS_PLANTERS = BlockTags.create(Reference.path("moss_planters"));
    public static final TagKey<Block> CORRUPT_PLANTERS = BlockTags.create(Reference.path("corrupt_planters"));
    public static final TagKey<Block> CRIMSON_PLANTERS = BlockTags.create(Reference.path("crimson_planters"));
    public static final TagKey<Block> DYE_GROWERS = BlockTags.create(Reference.path("dye_growers"));
//    public static final TagKey<Block> BOREAL_PLANTERS = BlockTags.create(Reference.path("boreal_planters"));
//    public static final TagKey<Block> PALM_PLANTERS = BlockTags.create(Reference.path("palm_planters"));
//    public static final TagKey<Block> MAHOGANY_PLANTERS = BlockTags.create(Reference.path("mahogany_planters"));
//    public static final TagKey<Block> EBON_PLANTERS = BlockTags.create(Reference.path("ebon_planters"));
//    public static final TagKey<Block> SHADE_PLANTERS = BlockTags.create(Reference.path("shade_planters"));
//    public static final TagKey<Block> PEARL_PLANTERS = BlockTags.create(Reference.path("pearl_planters"));
    public static final TagKey<Block> DAYBLOOM_PLANTERS = BlockTags.create(Reference.path("daybloom_planters"));
    public static final TagKey<Block> DEATHWEED_PLANTERS = BlockTags.create(Reference.path("deathweed_planters"));
    public static final TagKey<Block> FIREBLOSSOM_PLANTERS = BlockTags.create(Reference.path("fireblossom_planters"));
    public static final TagKey<Block> MOONGLOW_PLANTERS = BlockTags.create(Reference.path("moonglow_planters"));
    public static final TagKey<Block> SHIVERTHORN_PLANTERS = BlockTags.create(Reference.path("shiverthorn_planters"));
    public static final TagKey<Block> WATERLEAF_PLANTERS = BlockTags.create(Reference.path("waterleaf_planters"));
    public static final TagKey<Block> GENERAL_PLANTERS = BlockTags.create(Reference.path("general_planters"));
    public static final TagKey<Block> CACTUS_PLANTERS = BlockTags.create(Reference.path("cactus_planters"));
    public static final TagKey<Block> REED_PLANTERS = BlockTags.create(Reference.path("reed_planters"));

    public static final TagKey<Block> ORE_TIN = BlockTags.create(Reference.forgePath("ores/tin"));
    public static final TagKey<Block> ORE_LEAD = BlockTags.create(Reference.forgePath("ores/lead"));
    public static final TagKey<Block> ORE_SILVER = BlockTags.create(Reference.forgePath("ores/silver"));
    public static final TagKey<Block> ORE_TUNGSTEN = BlockTags.create(Reference.forgePath("ores/tungsten"));
    public static final TagKey<Block> ORE_PLATINUM = BlockTags.create(Reference.forgePath("ores/platinum"));
    public static final TagKey<Block> ORE_DEMONITE = BlockTags.create(Reference.path("ores/demonite"));
    public static final TagKey<Block> ORE_CRIMTANE = BlockTags.create(Reference.path("ores/crimtane"));
    public static final TagKey<Block> ORE_COBALT = BlockTags.create(Reference.forgePath("ores/cobalt"));
    public static final TagKey<Block> ORE_PALLADIUM = BlockTags.create(Reference.path("ores/palladium"));
    public static final TagKey<Block> ORE_MYTHRIL = BlockTags.create(Reference.forgePath("ores/mythril"));
    public static final TagKey<Block> ORE_ORICHALCUM = BlockTags.create(Reference.path("ores/orichalcum"));
    public static final TagKey<Block> ORE_ADAMANTITE = BlockTags.create(Reference.path("ores/adamantite"));
    public static final TagKey<Block> ORE_TITANIUM = BlockTags.create(Reference.forgePath("ores/titanium"));
    public static final TagKey<Block> CRYSTAL_TOPAZ = BlockTags.create(Reference.path("topaz_crystal"));
    public static final TagKey<Block> CRYSTAL_AMETHYST = BlockTags.create(Reference.path("amethyst_crystal"));
    public static final TagKey<Block> ORE_SAPPHIRE = BlockTags.create(Reference.forgePath("ores/sapphire"));
    public static final TagKey<Block> ORE_RUBY = BlockTags.create(Reference.forgePath("ores/runy"));
//    public static final TagKey<Block> MUSHROOM_PLANTERS = BlockTags.create(Reference.path("mushroom_planters"));
//    public static final TagKey<Block> CORRUPT_REPLACERS = BlockTags.create(Reference.path("gen/corrupt_replacers"));
//    public static final TagKey<Block> CRIMSON_REPLACERS = BlockTags.create(Reference.path("gen/crimson_replacers"));
//    public static final TagKey<Block> HALLOWED_REPLACERS = BlockTags.create(Reference.path("gen/hallowed_replacers"));
//    public static final TagKey<Block> JUNGLE_REPLACERS = BlockTags.create(Reference.path("gen/jungle_replacers"));
//    public static final TagKey<Block> GENERAL_REPLACERS = BlockTags.create(Reference.path("gen/general_replacers"));

    public static final TagKey<Item> INGOT_TIN = ItemTags.create(new ResourceLocation("forge:ingots/tin"));
    public static final TagKey<Item> INGOT_LEAD = ItemTags.create(new ResourceLocation("forge:ingots/lead"));
    public static final TagKey<Item> INGOT_TUNGSTEN = ItemTags.create(new ResourceLocation("forge:ingots/tungsten"));
    public static final TagKey<Item> INGOT_SILVER = ItemTags.create(new ResourceLocation("forge:ingots/silver"));
    public static final TagKey<Item> INGOT_PLATINUM = ItemTags.create(new ResourceLocation("forge:ingots/platinum"));
    public static final TagKey<Item> INGOT_MYTHRIL = ItemTags.create(new ResourceLocation("forge:ingots/mythril"));
    public static final TagKey<Item> INGOT_TITANIUM = ItemTags.create(new ResourceLocation("forge:ingots/titanium"));

    public static final TagKey<Item> FROG_FALLERS = ItemTags.create(Reference.path("frog_fallers"));
    public static final TagKey<Item> CLOUD_JUMPERS = ItemTags.create(Reference.path("cloud_jumpers"));
    public static final TagKey<Item> BLIZZARD_JUMPERS = ItemTags.create(Reference.path("blizzard_jumpers"));
    public static final TagKey<Item> SANDSTORM_JUMPERS = ItemTags.create(Reference.path("sandstorm_jumpers"));
    public static final TagKey<Item> TSUNAMI_JUMPERS = ItemTags.create(Reference.path("tsunami_jumpers"));
    public static final TagKey<Item> FART_JUMPERS = ItemTags.create(Reference.path("fart_jumpers"));
    public static final TagKey<Item> ROCKET_JUMPERS = ItemTags.create(Reference.path("rocket_jumpers"));
    public static final TagKey<Item> CLOUD_HIGH_JUMPERS = ItemTags.create(Reference.path("cloud_high_jumpers"));
    public static final TagKey<Item> BLIZZARD_HIGH_JUMPERS = ItemTags.create(Reference.path("blizzard_high_jumpers"));
    public static final TagKey<Item> SANDSTORM_HIGH_JUMPERS = ItemTags.create(Reference.path("sandstorm_high_jumpers"));
    public static final TagKey<Item> TSUNAMI_HIGH_JUMPERS = ItemTags.create(Reference.path("tsunami_high_jumpers"));
    public static final TagKey<Item> FART_HIGH_JUMPERS = ItemTags.create(Reference.path("fart_high_jumpers"));
    public static final TagKey<Item> HONEY_HIGH_JUMPERS = ItemTags.create(Reference.path("honey_high_jumpers"));
    public static final TagKey<Item> FROG_HIGH_JUMPERS = ItemTags.create(Reference.path("frog_high_jumpers"));
    public static final TagKey<Item> TABI_DASHERS = ItemTags.create(Reference.path("tabi_dashers"));
    public static final TagKey<Item> BEE_SPAWNERS = ItemTags.create(Reference.path("bee_spawners"));
    public static final TagKey<Item> STAR_SPAWNERS = ItemTags.create(Reference.path("star_spawners"));
    public static final TagKey<Item> PANIC_GIVERS = ItemTags.create(Reference.path("panic_givers"));
    public static final TagKey<Item> ARMOR_PASSERS = ItemTags.create(Reference.path("armor_passers"));
    public static final TagKey<Item> WATER_WALKERS = ItemTags.create(Reference.path("water_walkers"));
    public static final TagKey<Item> ICE_WALKERS = ItemTags.create(Reference.path("ice_walkers"));
    public static final TagKey<Item> LAVA_WALKERS = ItemTags.create(Reference.path("lava_walkers"));
    public static final TagKey<Item> LAVA_RESISTORS = ItemTags.create(Reference.path("lava_resistors"));
    public static final TagKey<Item> LAVA_PROTECTORS = ItemTags.create(Reference.path("lava_protectors"));
    public static final TagKey<Item> FALL_PROTECTORS = ItemTags.create(Reference.path("fall_protectors"));
    public static final TagKey<Item> WALL_GRIPPERS = ItemTags.create(Reference.path("wall_grippers"));
    public static final TagKey<Item> MANA_BRINGERS = ItemTags.create(Reference.path("mana_bringers"));
    public static final TagKey<Item> MANA_RESTORERS = ItemTags.create(Reference.path("mana_restorers"));
    public static final TagKey<Item> DAMAGE_DODGERS = ItemTags.create(Reference.path("damage_dodgers"));
    public static final TagKey<Item> ITEM_BRINGERS = ItemTags.create(Reference.path("item_bringers"));
    public static final TagKey<Item> POTION_REDUCERS = ItemTags.create(Reference.path("potion_reducers"));
    public static final TagKey<Item> BLEEDING_NEGATORS = ItemTags.create(Reference.path("bleeding_negators"));
    public static final TagKey<Item> BLINDNESS_NEGATORS = ItemTags.create(Reference.path("blindness_negators"));
    public static final TagKey<Item> BROKEN_ARMOR_NEGATORS = ItemTags.create(Reference.path("broken_armor_negators"));
    public static final TagKey<Item> BURNING_NEGATORS = ItemTags.create(Reference.path("burning_negators"));
    public static final TagKey<Item> CONFUSED_NEGATORS = ItemTags.create(Reference.path("cunfused_negators"));
    public static final TagKey<Item> CURSED_NEGATORS = ItemTags.create(Reference.path("cursed_negators"));
    public static final TagKey<Item> POISON_NEGATORS = ItemTags.create(Reference.path("poison_negators"));
    public static final TagKey<Item> SILENCED_NEGATORS = ItemTags.create(Reference.path("silenced_negators"));
    public static final TagKey<Item> SLOW_NEGATORS = ItemTags.create(Reference.path("slow_negators"));
    public static final TagKey<Item> WEAK_NEGATORS = ItemTags.create(Reference.path("weak_negators"));
    public static final TagKey<Item> BOOT_SPEEDERS = ItemTags.create(Reference.path("boot_speeders"));
    public static final TagKey<Item> WATER_SPEEDERS = ItemTags.create(Reference.path("water_speeders"));
    public static final TagKey<Item> FIRE_STARTERS = ItemTags.create(Reference.path("fire_starters"));
    public static final TagKey<Item> GUN_SNIPERS = ItemTags.create(Reference.path("gun_snipers"));
    public static final TagKey<Item> GUN_SCOPERS = ItemTags.create(Reference.path("gun_scopers"));
    public static final TagKey<Item> TALLY_TELLERS = ItemTags.create(Reference.path("tally_tellers"));
    public static final TagKey<Item> DPS_TELLERS = ItemTags.create(Reference.path("dps_tellers"));
    public static final TagKey<Item> SPEED_TELLERS = ItemTags.create(Reference.path("speed_tellers"));
    public static final TagKey<Item> INFO_TELLERS = ItemTags.create(Reference.path("info_tellers"));
    public static final TagKey<Item> FLIGHT_GIVERS = ItemTags.create(Reference.path("flight_givers"));


}
