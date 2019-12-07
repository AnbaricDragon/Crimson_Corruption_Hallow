package com.anbaric.terra_reforged.util;

import com.anbaric.terra_reforged.blocks.TerraBlocks;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.item.AxeItem;
import net.minecraft.util.IItemProvider;

public class TerraCompat
{
    public static void setup()
    {
        registerStrippable(TerraBlocks.LOG_BOREAL, TerraBlocks.LOG_BOREAL_STRIPPED);
        registerStrippable(TerraBlocks.LOG_PALM, TerraBlocks.LOG_PALM_STRIPPED);
        registerStrippable(TerraBlocks.LOG_MAHOGANY, TerraBlocks.LOG_MAHOGANY_STRIPPED);
        registerStrippable(TerraBlocks.LOG_EBON, TerraBlocks.LOG_EBON_STRIPPED);
        registerStrippable(TerraBlocks.LOG_SHADE, TerraBlocks.LOG_SHADE_STRIPPED);
        registerStrippable(TerraBlocks.LOG_PEARL, TerraBlocks.LOG_PEARL_STRIPPED);
        registerStrippable(TerraBlocks.LOG_BOREAL_SOLID, TerraBlocks.LOG_BOREAL_SOLID_STRIPPED);
        registerStrippable(TerraBlocks.LOG_PALM_SOLID, TerraBlocks.LOG_PALM_SOLID_STRIPPED);
        registerStrippable(TerraBlocks.LOG_MAHOGANY_SOLID, TerraBlocks.LOG_MAHOGANY_SOLID_STRIPPED);
        registerStrippable(TerraBlocks.LOG_EBON_SOLID, TerraBlocks.LOG_EBON_SOLID_STRIPPED);
        registerStrippable(TerraBlocks.LOG_SHADE_SOLID, TerraBlocks.LOG_SHADE_SOLID_STRIPPED);
        registerStrippable(TerraBlocks.LOG_PEARL_SOLID, TerraBlocks.LOG_PEARL_SOLID_STRIPPED);

        registerFlammable(TerraBlocks.LOG_BOREAL, 5, 5);
        registerFlammable(TerraBlocks.LOG_PALM, 5, 5);
        registerFlammable(TerraBlocks.LOG_MAHOGANY, 5, 5);
        registerFlammable(TerraBlocks.LOG_EBON, 5, 5);
        registerFlammable(TerraBlocks.LOG_SHADE, 5, 5);
        registerFlammable(TerraBlocks.LOG_PEARL, 5, 5);
        registerFlammable(TerraBlocks.LOG_BOREAL_SOLID, 5, 5);
        registerFlammable(TerraBlocks.LOG_PALM_SOLID, 5, 5);
        registerFlammable(TerraBlocks.LOG_MAHOGANY_SOLID, 5, 5);
        registerFlammable(TerraBlocks.LOG_EBON_SOLID, 5, 5);
        registerFlammable(TerraBlocks.LOG_SHADE_SOLID, 5, 5);
        registerFlammable(TerraBlocks.LOG_PEARL_SOLID, 5, 5);
        registerFlammable(TerraBlocks.LOG_BOREAL_STRIPPED, 5, 5);
        registerFlammable(TerraBlocks.LOG_PALM_STRIPPED, 5, 5);
        registerFlammable(TerraBlocks.LOG_MAHOGANY_STRIPPED, 5, 5);
        registerFlammable(TerraBlocks.LOG_EBON_STRIPPED, 5, 5);
        registerFlammable(TerraBlocks.LOG_SHADE_STRIPPED, 5, 5);
        registerFlammable(TerraBlocks.LOG_PEARL_STRIPPED, 5, 5);
        registerFlammable(TerraBlocks.LOG_BOREAL_SOLID_STRIPPED, 5, 5);
        registerFlammable(TerraBlocks.LOG_PALM_SOLID_STRIPPED, 5, 5);
        registerFlammable(TerraBlocks.LOG_MAHOGANY_SOLID_STRIPPED, 5, 5);
        registerFlammable(TerraBlocks.LOG_EBON_SOLID_STRIPPED, 5, 5);
        registerFlammable(TerraBlocks.LOG_SHADE_SOLID_STRIPPED, 5, 5);
        registerFlammable(TerraBlocks.LOG_PEARL_SOLID_STRIPPED, 5, 5);
        registerFlammable(TerraBlocks.PLANK_BOREAL, 5, 20);
        registerFlammable(TerraBlocks.PLANK_PALM, 5, 20);
        registerFlammable(TerraBlocks.PLANK_MAHOGANY, 5, 20);
        registerFlammable(TerraBlocks.PLANK_EBON, 5, 20);
        registerFlammable(TerraBlocks.PLANK_SHADE, 5, 20);
        registerFlammable(TerraBlocks.PLANK_PEARL, 5, 20);
        registerFlammable(TerraBlocks.PLANK_SPOOKY, 5, 20);
        registerFlammable(TerraBlocks.PLANK_DYNASTY, 5, 20);
        registerFlammable(TerraBlocks.LEAF_BOREAL, 30, 60);
        registerFlammable(TerraBlocks.LEAF_PALM, 30, 60);
        registerFlammable(TerraBlocks.LEAF_MAHOGANY, 30, 60);
        registerFlammable(TerraBlocks.LEAF_EBON, 30, 60);
        registerFlammable(TerraBlocks.LEAF_SHADE, 30, 60);
        registerFlammable(TerraBlocks.LEAF_PEARL_RED, 30, 60);
        registerFlammable(TerraBlocks.LEAF_PEARL_YELLOW, 30, 60);
        registerFlammable(TerraBlocks.LEAF_PEARL_PINK, 30, 60);
        registerFlammable(TerraBlocks.LEAF_PEARL_MAGENTA, 30, 60);
        registerFlammable(TerraBlocks.LEAF_PEARL_CYAN, 30, 60);
        registerFlammable(TerraBlocks.LEAF_PEARL_BLUE, 30, 60);
        registerFlammable(TerraBlocks.LEAF_PEARL_GREEN, 30, 60);
        registerFlammable(TerraBlocks.LEAF_PEARL_PURPLE, 30, 60);
        registerFlammable(TerraBlocks.WALL_OAK, 5, 5);
        registerFlammable(TerraBlocks.WALL_SPRUCE, 5, 5);
        registerFlammable(TerraBlocks.WALL_BIRCH, 5, 5);
        registerFlammable(TerraBlocks.WALL_JUNGLE, 5, 5);
        registerFlammable(TerraBlocks.WALL_ACACIA, 5, 5);
        registerFlammable(TerraBlocks.WALL_DARKOAK, 5, 5);
        registerFlammable(TerraBlocks.WALL_BOREAL, 5, 5);
        registerFlammable(TerraBlocks.WALL_PALM, 5, 5);
        registerFlammable(TerraBlocks.WALL_MAHOGANY, 5, 5);
        registerFlammable(TerraBlocks.WALL_EBON, 5, 5);
        registerFlammable(TerraBlocks.WALL_SHADE, 5, 5);
        registerFlammable(TerraBlocks.WALL_PEARL, 5, 5);
        registerFlammable(TerraBlocks.WALL_BOREAL_LEAF, 30, 60);
        registerFlammable(TerraBlocks.WALL_PALM_LEAF, 30, 60);
        registerFlammable(TerraBlocks.WALL_MAHOGANY_LEAF, 30, 60);
        registerFlammable(TerraBlocks.WALL_EBON_LEAF, 30, 60);
        registerFlammable(TerraBlocks.WALL_SHADE_LEAF, 30, 60);
        registerFlammable(TerraBlocks.WALL_PEARL_LEAF, 30, 60);
        registerFlammable(TerraBlocks.WALL_OAK_PLANK, 5, 20);
        registerFlammable(TerraBlocks.WALL_SPRUCE_PLANK, 5, 20);
        registerFlammable(TerraBlocks.WALL_BIRCH_PLANK, 5, 20);
        registerFlammable(TerraBlocks.WALL_JUNGLE_PLANK, 5, 20);
        registerFlammable(TerraBlocks.WALL_ACACIA_PLANK, 5, 20);
        registerFlammable(TerraBlocks.WALL_DARKOAK_PLANK, 5, 20);
        registerFlammable(TerraBlocks.WALL_BOREAL_PLANK, 5, 20);
        registerFlammable(TerraBlocks.WALL_PALM_PLANK, 5, 20);
        registerFlammable(TerraBlocks.WALL_MAHOGANY_PLANK, 5, 20);
        registerFlammable(TerraBlocks.WALL_EBON_PLANK, 5, 20);
        registerFlammable(TerraBlocks.WALL_SHADE_PLANK, 5, 20);
        registerFlammable(TerraBlocks.WALL_PEARL_PLANK, 5, 20);
        registerFlammable(TerraBlocks.WALL_DYNASTY_PLANK, 5, 20);
        registerFlammable(TerraBlocks.WALL_SPOOKY_PLANK, 5, 20);
        registerFlammable(TerraBlocks.PLANT_THORN_JUNGLE, 20, 5);
        registerFlammable(TerraBlocks.PLANT_THORN_PURPLE, 20, 5);
        registerFlammable(TerraBlocks.PLANT_THORN_RED, 20, 5);


    }

    public static void registerStrippable(Block log, Block stripped_log) {
        AxeItem.BLOCK_STRIPPING_MAP = Maps.newHashMap(AxeItem.BLOCK_STRIPPING_MAP);
        AxeItem.BLOCK_STRIPPING_MAP.put(log, stripped_log);
    }

    public static void registerCompostable(float chance, IItemProvider itemIn) {
        ComposterBlock.CHANCES.put(itemIn.asItem(), chance);
    }

    public static void registerFlammable(Block blockIn, int encouragement, int flammability)
    {
        FireBlock fireblock = (FireBlock) Blocks.FIRE;
        fireblock.setFireInfo(blockIn, encouragement, flammability);
    }
}
