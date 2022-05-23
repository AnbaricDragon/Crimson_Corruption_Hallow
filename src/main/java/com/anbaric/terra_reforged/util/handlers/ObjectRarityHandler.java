package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

import java.util.function.Predicate;

public class ObjectRarityHandler
{
    public enum MetalDetectorEnum
    {
        LIFEFRUIT(LIFEFRUIT_PREDICATE, "overlay.terra_reforged.info_metal_lifefruit"),
        LIFECRYSTAL(LIFECRYSTAL_PREDICATE, "overlay.terra_reforged.info_metal_lifecrystal"),
        DYEPLANT(DYEPLANT_PREDICATE, "overlay.terra_reforged.info_metal_dyeplant"),
        CHLOROPHYTE(CHLOROPHYTE_PREDICATE, "overlay.terra_reforged.info_metal_chlorophyte"),
        TITANIUM(TITANIUM_PREDICATE, "overlay.terra_reforged.info_metal_titanium"),
        ADAMANTITE(ADAMANTITE_PREDICATE, "overlay.terra_reforged.info_metal_adamantite"),
        ORICHALCUM(ORICHALCUM_PREDICATE, "overlay.terra_reforged.info_metal_orichalcum"),
        MYTHRIL(MYTHRIL_PREDICATE, "overlay.terra_reforged.info_metal_mythril"),
        PALLADIUM(PALLADIUM_PREDICATE, "overlay.terra_reforged.info_metal_palladium"),
        COBALT(COBALT_PREDICATE, "overlay.terra_reforged.info_metal_cobalt"),
        CHEST(CHEST_PREDICATE, "overlay.terra_reforged.info_metal_chest"),
        NETHERITE(NETHERITE_PREDICATE, "overlay.terra_reforged.info_metal_netherite"),
        EMERALD(EMERALD_PREDICATE, "overlay.terra_reforged.info_metal_emerald"),
        SAPPHIRE(SAPPHIRE_PREDICATE, "overlay.terra_reforged.info_metal_sapphire"),
        RUBY(RUBY_PREDICATE, "overlay.terra_reforged.info_metal_ruby"),
        METEORITE(METEORITE_PREDICATE, "overlay.terra_reforged.info_metal_meteorite"),
        CRIMTANE(CRIMTANE_PREDICATE, "overlay.terra_reforged.info_metal_crimtane"),
        DEMONITE(DEMONITE_PREDICATE, "overlay.terra_reforged.info_metal_demonite"),
        DIAMOND(DIAMOND_PREDICATE, "overlay.terra_reforged.info_metal_diamond"),
        PLATINUM(PLATINUM_PREDICATE, "overlay.terra_reforged.info_metal_platinum"),
        GOLD(GOLD_PREDICATE, "overlay.terra_reforged.info_metal_gold"),
        REDSTONE(REDSTONE_PREDICATE, "overlay.terra_reforged.info_metal_redstone"),
        TUNGSTEN(TUNGSTEN_PREDICATE, "overlay.terra_reforged.info_metal_tungsten"),
        SILVER(SILVER_PREDICATE, "overlay.terra_reforged.info_metal_silver"),
        LAPIS(LAPIS_PREDICATE, "overlay.terra_reforged.info_metal_lapis"),
        QUARTZ(QUARTZ_PREDICATE, "overlay.terra_reforged.info_metal_quartz"),
        AMETHYST(AMETHYST_PREDICATE, "overlay.terra_reforged.info_metal_amethyst"),
        TOPAZ(TOPAZ_PREDICATE, "overlay.terra_reforged.info_metal_topaz"),
        LEAD(LEAD_PREDICATE, "overlay.terra_reforged.info_metal_lead"),
        IRON(IRON_PREDICATE, "overlay.terra_reforged.info_metal_iron"),
        TIN(TIN_PREDICATE, "overlay.terra_reforged.info_metal_tin"),
        COPPER(COPPER_PREDICATE, "overlay.terra_reforged.info_metal_copper"),
        COAL(COAL_PREDICATE, "overlay.terra_reforged.info_metal_coal"),
        DESERTFOSSIL(DESERTFOSSIL_PREDICATE, "overlay.terra_reforged.info_metal_fossil"),
        POT(POT_PREDICATE, "overlay.terra_reforged.info_metal_pot"),
        AIR(AIR_PREDICATE, "overlay.terra_reforged.info_metal_void");

        private Predicate qualifier;
        private String component;

        MetalDetectorEnum(Predicate predicate, String component)
        {
            this.qualifier = predicate;
            this.component = component;
        }

        public Predicate getQualifier()
        {
            return qualifier;
        }

        public String getString()
        {
            return component;
        }
    }

    public static Predicate<Block> LIFEFRUIT_PREDICATE = block -> is(TerraBlockRegistry.PLANT_LIFEFRUIT.get(), block);
    public static Predicate<Block> LIFECRYSTAL_PREDICATE = block -> is(TerraBlockRegistry.GRASS_MUSHROOM.get(), block);
    public static Predicate<Block> DYEPLANT_PREDICATE = block -> is(TerraTagRegistry.DYE_GROWERS, block);
    public static Predicate<Block> CHLOROPHYTE_PREDICATE = block -> is(TerraBlockRegistry.ORE_CHLOROPHYTE.get(), block);
    public static Predicate<Block> TITANIUM_PREDICATE = block -> is(TerraTagRegistry.ORE_TITANIUM, block);
    public static Predicate<Block> ADAMANTITE_PREDICATE = block -> is(TerraTagRegistry.ORE_ADAMANTITE, block);
    public static Predicate<Block> ORICHALCUM_PREDICATE = block -> is(TerraTagRegistry.ORE_ORICHALCUM, block);
    public static Predicate<Block> MYTHRIL_PREDICATE = block -> is(TerraTagRegistry.ORE_MYTHRIL, block);
    public static Predicate<Block> PALLADIUM_PREDICATE = block -> is(TerraTagRegistry.ORE_PALLADIUM, block);
    public static Predicate<Block> COBALT_PREDICATE = block -> is(TerraTagRegistry.ORE_COBALT, block);
    public static Predicate<Block> CHEST_PREDICATE = block -> block instanceof ChestBlock;
    public static Predicate<Block> METEORITE_PREDICATE = block -> is(TerraBlockRegistry.ORE_METEORITE.get(), block);
    public static Predicate<Block> NETHERITE_PREDICATE = block -> is(Tags.Blocks.ORES_NETHERITE_SCRAP, block);
    public static Predicate<Block> EMERALD_PREDICATE = block -> is(BlockTags.EMERALD_ORES, block);
    public static Predicate<Block> RUBY_PREDICATE = block -> is(TerraTagRegistry.ORE_RUBY, block);
    public static Predicate<Block> SAPPHIRE_PREDICATE = block -> is(TerraTagRegistry.ORE_SAPPHIRE, block);
    public static Predicate<Block> CRIMTANE_PREDICATE = block -> is(TerraTagRegistry.ORE_CRIMTANE, block);
    public static Predicate<Block> DEMONITE_PREDICATE = block -> is(TerraTagRegistry.ORE_DEMONITE, block);
    public static Predicate<Block> DIAMOND_PREDICATE = block -> is(BlockTags.DIAMOND_ORES, block);
    public static Predicate<Block> PLATINUM_PREDICATE = block -> is(TerraTagRegistry.ORE_PLATINUM, block);
    public static Predicate<Block> GOLD_PREDICATE = block -> is(BlockTags.GOLD_ORES, block);
    public static Predicate<Block> REDSTONE_PREDICATE = block -> is(BlockTags.REDSTONE_ORES, block);
    public static Predicate<Block> TUNGSTEN_PREDICATE = block -> is(TerraTagRegistry.ORE_TUNGSTEN, block);
    public static Predicate<Block> SILVER_PREDICATE = block -> is(TerraTagRegistry.ORE_SILVER, block);
    public static Predicate<Block> LAPIS_PREDICATE = block -> is(BlockTags.LAPIS_ORES, block);
    public static Predicate<Block> AMETHYST_PREDICATE = block -> is(TerraTagRegistry.CRYSTAL_AMETHYST, block);
    public static Predicate<Block> QUARTZ_PREDICATE = block -> is(Tags.Blocks.ORES_QUARTZ, block);
    public static Predicate<Block> TOPAZ_PREDICATE = block -> is(TerraTagRegistry.CRYSTAL_TOPAZ, block);
    public static Predicate<Block> LEAD_PREDICATE = block -> is(TerraTagRegistry.ORE_LEAD, block);
    public static Predicate<Block> IRON_PREDICATE = block -> is(BlockTags.IRON_ORES, block);
    public static Predicate<Block> TIN_PREDICATE = block -> is(TerraTagRegistry.ORE_TIN, block);
    public static Predicate<Block> COPPER_PREDICATE = block -> is(BlockTags.COPPER_ORES, block);
    public static Predicate<Block> COAL_PREDICATE = block -> is(BlockTags.COAL_ORES, block);
    public static Predicate<Block> DESERTFOSSIL_PREDICATE = block -> is(TerraBlockRegistry.STONE_FOSSIL.get(), block);
    public static Predicate<Block> POT_PREDICATE = block -> is(Blocks.FLOWER_POT, block);
    public static Predicate<Block> AIR_PREDICATE = block -> is(Blocks.AIR, block);

    public static boolean is(TagKey<Block> tag, Block block)
    {
        return block.builtInRegistryHolder().is(tag);
    }

    public static boolean is(Block tester, Block block)
    {
        return tester == block;
    }
}
