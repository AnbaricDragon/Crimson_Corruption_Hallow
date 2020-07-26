package com.anbaric.terra_reforged.util;

import com.anbaric.terra_reforged.features.TerraBiomeFeatures;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.item.AxeItem;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class TerraVanillaCompat
{
    public static void setupStripping()
    {
        registerStrippable(TerraBlockRegistry.LOG_BOREAL.get(), TerraBlockRegistry.LOG_BOREAL_STRIPPED.get());
        registerStrippable(TerraBlockRegistry.LOG_PALM.get(), TerraBlockRegistry.LOG_PALM_STRIPPED.get());
        registerStrippable(TerraBlockRegistry.LOG_MAHOGANY.get(), TerraBlockRegistry.LOG_MAHOGANY_STRIPPED.get());
        registerStrippable(TerraBlockRegistry.LOG_EBON.get(), TerraBlockRegistry.LOG_EBON_STRIPPED.get());
        registerStrippable(TerraBlockRegistry.LOG_SHADE.get(), TerraBlockRegistry.LOG_SHADE_STRIPPED.get());
        registerStrippable(TerraBlockRegistry.LOG_PEARL.get(), TerraBlockRegistry.LOG_PEARL_STRIPPED.get());
        registerStrippable(TerraBlockRegistry.LOG_BOREAL_SOLID.get(), TerraBlockRegistry.LOG_BOREAL_SOLID_STRIPPED.get());
        registerStrippable(TerraBlockRegistry.LOG_PALM_SOLID.get(), TerraBlockRegistry.LOG_PALM_SOLID_STRIPPED.get());
        registerStrippable(TerraBlockRegistry.LOG_MAHOGANY_SOLID.get(), TerraBlockRegistry.LOG_MAHOGANY_SOLID_STRIPPED.get());
        registerStrippable(TerraBlockRegistry.LOG_EBON_SOLID.get(), TerraBlockRegistry.LOG_EBON_SOLID_STRIPPED.get());
        registerStrippable(TerraBlockRegistry.LOG_SHADE_SOLID.get(), TerraBlockRegistry.LOG_SHADE_SOLID_STRIPPED.get());
        registerStrippable(TerraBlockRegistry.LOG_PEARL_SOLID.get(), TerraBlockRegistry.LOG_PEARL_SOLID_STRIPPED.get());
    }

    public static void setupFlammable()
    {
        registerFlammable(TerraBlockRegistry.LOG_BOREAL.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_PALM.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_MAHOGANY.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_EBON.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_SHADE.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_PEARL.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_BOREAL_SOLID.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_PALM_SOLID.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_MAHOGANY_SOLID.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_EBON_SOLID.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_SHADE_SOLID.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_PEARL_SOLID.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_BOREAL_STRIPPED.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_PALM_STRIPPED.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_MAHOGANY_STRIPPED.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_EBON_STRIPPED.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_SHADE_STRIPPED.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_PEARL_STRIPPED.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_BOREAL_SOLID_STRIPPED.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_PALM_SOLID_STRIPPED.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_MAHOGANY_SOLID_STRIPPED.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_EBON_SOLID_STRIPPED.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_SHADE_SOLID_STRIPPED.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.LOG_PEARL_SOLID_STRIPPED.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.PLANK_BOREAL.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.PLANK_PALM.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.PLANK_MAHOGANY.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.PLANK_EBON.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.PLANK_SHADE.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.PLANK_PEARL.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.PLANK_SPOOKY.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.PLANK_DYNASTY.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.LEAF_BOREAL.get(), 30, 60);
        registerFlammable(TerraBlockRegistry.LEAF_PALM.get(), 30, 60);
        registerFlammable(TerraBlockRegistry.LEAF_MAHOGANY.get(), 30, 60);
        registerFlammable(TerraBlockRegistry.LEAF_EBON.get(), 30, 60);
        registerFlammable(TerraBlockRegistry.LEAF_SHADE.get(), 30, 60);
        registerFlammable(TerraBlockRegistry.LEAF_PEARL_RED.get(), 30, 60);
        registerFlammable(TerraBlockRegistry.LEAF_PEARL_YELLOW.get(), 30, 60);
        registerFlammable(TerraBlockRegistry.LEAF_PEARL_PINK.get(), 30, 60);
        registerFlammable(TerraBlockRegistry.LEAF_PEARL_MAGENTA.get(), 30, 60);
        registerFlammable(TerraBlockRegistry.LEAF_PEARL_CYAN.get(), 30, 60);
        registerFlammable(TerraBlockRegistry.LEAF_PEARL_BLUE.get(), 30, 60);
        registerFlammable(TerraBlockRegistry.LEAF_PEARL_GREEN.get(), 30, 60);
        registerFlammable(TerraBlockRegistry.LEAF_PEARL_PURPLE.get(), 30, 60);
        registerFlammable(TerraBlockRegistry.WALL_OAK.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.WALL_SPRUCE.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.WALL_BIRCH.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.WALL_JUNGLE.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.WALL_ACACIA.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.WALL_DARKOAK.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.WALL_BOREAL.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.WALL_PALM.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.WALL_MAHOGANY.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.WALL_EBON.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.WALL_SHADE.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.WALL_PEARL.get(), 5, 5);
        registerFlammable(TerraBlockRegistry.WALL_BOREAL_LEAF.get(), 30, 60);
        registerFlammable(TerraBlockRegistry.WALL_PALM_LEAF.get(), 30, 60);
        registerFlammable(TerraBlockRegistry.WALL_MAHOGANY_LEAF.get(), 30, 60);
        registerFlammable(TerraBlockRegistry.WALL_EBON_LEAF.get(), 30, 60);
        registerFlammable(TerraBlockRegistry.WALL_SHADE_LEAF.get(), 30, 60);
        registerFlammable(TerraBlockRegistry.WALL_PEARL_LEAF.get(), 30, 60);
        registerFlammable(TerraBlockRegistry.WALL_OAK_PLANK.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.WALL_SPRUCE_PLANK.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.WALL_BIRCH_PLANK.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.WALL_JUNGLE_PLANK.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.WALL_ACACIA_PLANK.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.WALL_DARKOAK_PLANK.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.WALL_BOREAL_PLANK.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.WALL_PALM_PLANK.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.WALL_MAHOGANY_PLANK.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.WALL_EBON_PLANK.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.WALL_SHADE_PLANK.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.WALL_PEARL_PLANK.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.WALL_DYNASTY_PLANK.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.WALL_SPOOKY_PLANK.get(), 5, 20);
        registerFlammable(TerraBlockRegistry.PLANT_THORN_JUNGLE.get(), 20, 5);
        registerFlammable(TerraBlockRegistry.PLANT_THORN_PURPLE.get(), 20, 5);
        registerFlammable(TerraBlockRegistry.PLANT_THORN_RED.get(), 20, 5);
    }

    public static void setupOres()
    {
        for (Biome biome : ForgeRegistries.BIOMES)
        {
            if (!biome.getRegistryName().getNamespace().startsWith(Reference.MODID))
            {
                TerraBiomeFeatures.addVanillaOres(biome);
            }
        }
    }

    public static void setupDyePlants()
    {
        for (Biome biome : ForgeRegistries.BIOMES)
        {
            if (!biome.getRegistryName().getNamespace().startsWith(Reference.MODID))
            {
                TerraBiomeFeatures.addDyeFlowers(biome);
            }
        }
    }

    public static void setupTrees()
    {
        for (Biome biome : ForgeRegistries.BIOMES)
        {
            if (!biome.getRegistryName().getNamespace().startsWith(Reference.MODID))
            {
                if(biome.getPrecipitation() == Biome.RainType.SNOW)
                {

                }
            }
        }
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
