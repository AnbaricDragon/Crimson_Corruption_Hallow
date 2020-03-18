package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.blocks.*;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.TerraItemGroups;
import com.anbaric.terra_reforged.util.handlers.EnumHandler.EnumBiomeType;
import com.google.common.base.Preconditions;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.audio.Sound;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class TerraBlockRegistry
{
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MODID);

    //Soils
    public static final RegistryObject<Block> SOIL_MUD = BLOCKS.register("soil_mud", () -> new Block(Block.Properties.create(Material.EARTH).sound(SoundType.GROUND).hardnessAndResistance(0.5F, 3.0F)));
    public static final RegistryObject<Block> SOIL_ASH = BLOCKS.register("soil_ash", () -> new Block(Block.Properties.create(Material.EARTH).sound(SoundType.GROUND).hardnessAndResistance(0.5F, 3.0F)));
    public static final RegistryObject<Block> SNOW_CORRUPT = BLOCKS.register("snow_corrupt", () -> new TerraBlockSpreading(Block.Properties.create(Material.SNOW_BLOCK).sound(SoundType.SNOW).hardnessAndResistance(0.5F, 3.0F), EnumBiomeType.CORRUPT, TerraReforged.BOREAL));
    public static final RegistryObject<Block> SNOW_CRIMSON = BLOCKS.register("snow_crimson", () -> new TerraBlockSpreading(Block.Properties.create(Material.SNOW_BLOCK).sound(SoundType.SNOW).hardnessAndResistance(0.5F, 3.0F), EnumBiomeType.CRIMSON, TerraReforged.BOREAL));
    public static final RegistryObject<Block> SNOW_HALLOWED = BLOCKS.register("snow_hallowed", () -> new TerraBlockSpreading(Block.Properties.create(Material.SNOW_BLOCK).sound(SoundType.SNOW).hardnessAndResistance(0.5F, 3.0F), EnumBiomeType.HALLOWED, TerraReforged.BOREAL));
    public static final RegistryObject<Block> SNOW_CORRUPT_LAYER = BLOCKS.register("snow_corrupt_layer", () -> new TerraBlockSnowLayer(Block.Properties.create(Material.SNOW).sound(SoundType.SNOW).hardnessAndResistance(0.1F).tickRandomly()));
    public static final RegistryObject<Block> SNOW_CRIMSON_LAYER = BLOCKS.register("snow_crimson_layer", () -> new TerraBlockSnowLayer(Block.Properties.create(Material.SNOW).sound(SoundType.SNOW).hardnessAndResistance(0.1F).tickRandomly()));
    public static final RegistryObject<Block> SNOW_HALLOWED_LAYER = BLOCKS.register("snow_hallowed_layer", () -> new TerraBlockSnowLayer(Block.Properties.create(Material.SNOW).sound(SoundType.SNOW).hardnessAndResistance(0.1F).tickRandomly()));
    public static final RegistryObject<Block> GRASS_JUNGLE = BLOCKS.register("grass_jungle", () -> new TerraBlockMudGrass(Block.Properties.create(Material.ORGANIC).sound(SoundType.PLANT).hardnessAndResistance(0.5F, 3.0F).tickRandomly()));
    public static final RegistryObject<Block> GRASS_MUSHROOM = BLOCKS.register("grass_mushroom", () -> new TerraBlockMudGrass(Block.Properties.create(Material.ORGANIC).sound(SoundType.PLANT).hardnessAndResistance(0.5F, 3.0F).tickRandomly()));
    public static final RegistryObject<Block> GRASS_CORRUPT = BLOCKS.register("grass_corrupt", () -> new TerraBlockBiomeGrass(Block.Properties.create(Material.ORGANIC).sound(SoundType.WET_GRASS).hardnessAndResistance(0.5F, 3.0F).tickRandomly(), EnumBiomeType.CORRUPT));
    public static final RegistryObject<Block> GRASS_CRIMSON = BLOCKS.register("grass_crimson", () -> new TerraBlockBiomeGrass(Block.Properties.create(Material.ORGANIC).sound(SoundType.WET_GRASS).hardnessAndResistance(0.5F, 3.0F).tickRandomly(), EnumBiomeType.CRIMSON));
    public static final RegistryObject<Block> GRASS_HALLOWED = BLOCKS.register("grass_hallowed", () -> new TerraBlockBiomeGrass(Block.Properties.create(Material.ORGANIC).sound(SoundType.PLANT).hardnessAndResistance(0.5F, 3.0F).tickRandomly(), EnumBiomeType.HALLOWED));
    public static final RegistryObject<Block> SAND_EBON = BLOCKS.register("sand_ebon", () -> new TerraBlockSand(Block.Properties.create(Material.SAND).sound(SoundType.SAND).hardnessAndResistance(0.5F).tickRandomly(), EnumBiomeType.CORRUPT));
    public static final RegistryObject<Block> SAND_CRIM = BLOCKS.register("sand_crim", () -> new TerraBlockSand(Block.Properties.create(Material.SAND).sound(SoundType.SAND).hardnessAndResistance(0.5F).tickRandomly(), EnumBiomeType.CRIMSON));
    public static final RegistryObject<Block> SAND_PEARL = BLOCKS.register("sand_pearl", () -> new TerraBlockSand(Block.Properties.create(Material.SAND).sound(SoundType.SAND).hardnessAndResistance(0.5F).tickRandomly(), EnumBiomeType.HALLOWED));
    public static final RegistryObject<Block> SAND_SILT = BLOCKS.register("sand_silt", () -> new SandBlock(25, Block.Properties.create(Material.SAND).sound(SoundType.SAND).hardnessAndResistance(0.5F)));
    public static final RegistryObject<Block> SAND_SLUSH = BLOCKS.register("sand_slush", () -> new SandBlock(25, Block.Properties.create(Material.SAND).sound(SoundType.SAND).hardnessAndResistance(0.5F)));
    public static final RegistryObject<Block> SAND_HARD = BLOCKS.register("sand_hard", () -> new SandBlock(25, Block.Properties.create(Material.SAND).sound(SoundType.SAND).hardnessAndResistance(1.0F)));
    public static final RegistryObject<Block> SAND_HARDEBON = BLOCKS.register("sand_hardebon", () -> new TerraBlockSand(Block.Properties.create(Material.SAND).sound(SoundType.SAND).hardnessAndResistance(1.0F).tickRandomly(), EnumBiomeType.CORRUPT));
    public static final RegistryObject<Block> SAND_HARDCRIM = BLOCKS.register("sand_hardcrim", () -> new TerraBlockSand(Block.Properties.create(Material.SAND).sound(SoundType.SAND).hardnessAndResistance(1.0F).tickRandomly(), EnumBiomeType.CRIMSON));
    public static final RegistryObject<Block> SAND_HARDPEARL = BLOCKS.register("sand_hardpearl", () -> new TerraBlockSand(Block.Properties.create(Material.SAND).sound(SoundType.SAND).hardnessAndResistance(1.0F).tickRandomly(), EnumBiomeType.HALLOWED));

    //Stones
    public static final RegistryObject<Block> SANDSTONE_EBON = BLOCKS.register("sandstone_ebon", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).tickRandomly().hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> SANDSTONE_EBON_CHISELED = BLOCKS.register("sandstone_ebon_chiseled", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> SANDSTONE_EBON_SMOOTH = BLOCKS.register("sandstone_ebon_smooth", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> SANDSTONE_EBON_CUT = BLOCKS.register("sandstone_ebon_cut", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> SANDSTONE_CRIM = BLOCKS.register("sandstone_crim", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> SANDSTONE_CRIM_CHISELED = BLOCKS.register("sandstone_crim_chiseled", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> SANDSTONE_CRIM_SMOOTH = BLOCKS.register("sandstone_crim_smooth", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> SANDSTONE_CRIM_CUT = BLOCKS.register("sandstone_crim_cut", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> SANDSTONE_PEARL = BLOCKS.register("sandstone_pearl", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> SANDSTONE_PEARL_CHISELED = BLOCKS.register("sandstone_pearl_chiseled", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> SANDSTONE_PEARL_SMOOTH = BLOCKS.register("sandstone_pearl_smooth", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> SANDSTONE_PEARL_CUT = BLOCKS.register("sandstone_pearl_cut", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> STONE_EBON = BLOCKS.register("stone_ebon", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> COBBLESTONE_EBON = BLOCKS.register("cobblestone_ebon", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> COBBLESTONE_EBON_MOSSY = BLOCKS.register("cobblestone_ebon_mossy", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> STONEBRICK_EBON = BLOCKS.register("stonebrick_ebon", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> STONEBRICK_EBON_CRACKED = BLOCKS.register("stonebrick_ebon_cracked", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> STONEBRICK_EBON_CHISELED = BLOCKS.register("stonebrick_ebon_chiseled", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> STONEBRICK_EBON_MOSSY = BLOCKS.register("stonebrick_ebon_mossy", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> STONE_CRIM = BLOCKS.register("stone_crim", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> COBBLESTONE_CRIM = BLOCKS.register("cobblestone_crim", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> COBBLESTONE_CRIM_MOSSY = BLOCKS.register("cobblestone_crim_mossy", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> STONEBRICK_CRIM = BLOCKS.register("stonebrick_crim", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> STONEBRICK_CRIM_CRACKED = BLOCKS.register("stonebrick_crim_cracked", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> STONEBRICK_CRIM_CHISELED = BLOCKS.register("stonebrick_crim_chiseled", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> STONEBRICK_CRIM_MOSSY = BLOCKS.register("stonebrick_crim_mossy", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> STONE_PEARL = BLOCKS.register("stone_pearl", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> COBBLESTONE_PEARL = BLOCKS.register("cobblestone_pearl", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> COBBLESTONE_PEARL_MOSSY = BLOCKS.register("cobblestone_pearl_mossy", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> STONEBRICK_PEARL = BLOCKS.register("stonebrick_pearl", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> STONEBRICK_PEARL_CRACKED = BLOCKS.register("stonebrick_pearl_cracked", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> STONEBRICK_PEARL_CHISELED = BLOCKS.register("stonebrick_pearl_chiseled", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> STONEBRICK_PEARL_MOSSY = BLOCKS.register("stonebrick_pearl_mossy", () -> new TerraBlockSpreading(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.8F).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave));
    public static final RegistryObject<Block> STONE_MOSS_RED = BLOCKS.register("stone_moss_red", () -> new TerraBlockMoss(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).tickRandomly(), Blocks.FERN));
    public static final RegistryObject<Block> STONE_MOSS_YELLOW = BLOCKS.register("stone_moss_yellow", () -> new TerraBlockMoss(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).tickRandomly(), Blocks.FERN));
    public static final RegistryObject<Block> STONE_MOSS_GREEN = BLOCKS.register("stone_moss_green", () -> new TerraBlockMoss(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).tickRandomly(), Blocks.FERN));
    public static final RegistryObject<Block> STONE_MOSS_BLUE = BLOCKS.register("stone_moss_blue", () -> new TerraBlockMoss(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).tickRandomly(), Blocks.FERN));
    public static final RegistryObject<Block> STONE_MOSS_PURPLE = BLOCKS.register("stone_moss_purple", () -> new TerraBlockMoss(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).tickRandomly(), Blocks.FERN));
    public static final RegistryObject<Block> STONE_MOSS_FIRE = BLOCKS.register("stone_moss_fire", () -> new TerraBlockMoss(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).tickRandomly(), Blocks.FERN));
    public static final RegistryObject<Block> STONE_GRANITE = BLOCKS.register("stone_granite", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0F)));
    public static final RegistryObject<Block> STONE_MARBLE = BLOCKS.register("stone_marble", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0F)));
    public static final RegistryObject<Block> STONE_FOSSIL = BLOCKS.register("stone_fossil", () -> new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1.5F, 6.0F)));
    public static final RegistryObject<Block> ICE_PURPLE = BLOCKS.register("ice_purple", () -> new TerraBlockIce(Block.Properties.create(Material.ICE).sound(SoundType.GLASS).slipperiness(0.98F).tickRandomly().hardnessAndResistance(0.5F).notSolid(), EnumBiomeType.CORRUPT));
    public static final RegistryObject<Block> ICE_HARD_PURPLE = BLOCKS.register("ice_hard_purple", () -> new TerraBlockSpreading(Block.Properties.create(Material.PACKED_ICE).sound(SoundType.GLASS).hardnessAndResistance(0.8F), EnumBiomeType.CORRUPT, TerraReforged.BOREAL));
    public static final RegistryObject<Block> ICE_RED = BLOCKS.register("ice_red", () -> new TerraBlockIce(Block.Properties.create(Material.ICE).sound(SoundType.GLASS).slipperiness(0.98F).tickRandomly().hardnessAndResistance(0.5F).notSolid(), EnumBiomeType.CRIMSON));
    public static final RegistryObject<Block> ICE_HARD_RED = BLOCKS.register("ice_hard_red", () -> new TerraBlockSpreading(Block.Properties.create(Material.PACKED_ICE).sound(SoundType.GLASS).hardnessAndResistance(0.8F), EnumBiomeType.CRIMSON, TerraReforged.BOREAL));
    public static final RegistryObject<Block> ICE_PINK = BLOCKS.register("ice_pink", () -> new TerraBlockIce(Block.Properties.create(Material.ICE).sound(SoundType.GLASS).slipperiness(0.98F).tickRandomly().hardnessAndResistance(0.5F).notSolid(), EnumBiomeType.HALLOWED));
    public static final RegistryObject<Block> ICE_HARD_PINK = BLOCKS.register("ice_hard_pink", () -> new TerraBlockSpreading(Block.Properties.create(Material.PACKED_ICE).sound(SoundType.GLASS).hardnessAndResistance(0.8F), EnumBiomeType.HALLOWED, TerraReforged.BOREAL));
    public static final RegistryObject<Block> ICE_THIN = BLOCKS.register("ice_thin", () -> new TerraBlockThinIce(Block.Properties.create(Material.ICE).sound(SoundType.GLASS).slipperiness(0.98F).hardnessAndResistance(0.25F).notSolid()));

    //Woods
    public static final RegistryObject<Block> LOG_BOREAL = BLOCKS.register("log_boreal", () -> new LogBlock(MaterialColor.BROWN_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_PALM = BLOCKS.register("log_palm", () -> new LogBlock(MaterialColor.YELLOW_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_MAHOGANY = BLOCKS.register("log_mahogany", () -> new LogBlock(MaterialColor.NETHERRACK, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_EBON = BLOCKS.register("log_ebon", () -> new LogBlock(MaterialColor.PURPLE_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_SHADE = BLOCKS.register("log_shade", () -> new LogBlock(MaterialColor.RED_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_PEARL = BLOCKS.register("log_pearl", () -> new LogBlock(MaterialColor.PINK_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_BOREAL_SOLID = BLOCKS.register("log_boreal_solid", () -> new LogBlock(MaterialColor.BROWN_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_PALM_SOLID = BLOCKS.register("log_palm_solid", () -> new LogBlock(MaterialColor.YELLOW_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_MAHOGANY_SOLID = BLOCKS.register("log_mahogany_solid", () -> new LogBlock(MaterialColor.NETHERRACK, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_EBON_SOLID = BLOCKS.register("log_ebon_solid", () -> new LogBlock(MaterialColor.PURPLE_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_SHADE_SOLID = BLOCKS.register("log_shade_solid", () -> new LogBlock(MaterialColor.RED_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_PEARL_SOLID = BLOCKS.register("log_pearl_solid", () -> new LogBlock(MaterialColor.PINK_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_BOREAL_STRIPPED = BLOCKS.register("log_boreal_stripped", () -> new LogBlock(MaterialColor.BROWN_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_PALM_STRIPPED = BLOCKS.register("log_palm_stripped", () -> new LogBlock(MaterialColor.YELLOW_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_MAHOGANY_STRIPPED = BLOCKS.register("log_mahogany_stripped", () -> new LogBlock(MaterialColor.NETHERRACK, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_EBON_STRIPPED = BLOCKS.register("log_ebon_stripped", () -> new LogBlock(MaterialColor.PURPLE_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_SHADE_STRIPPED = BLOCKS.register("log_shade_stripped", () -> new LogBlock(MaterialColor.RED_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_PEARL_STRIPPED = BLOCKS.register("log_pearl_stripped", () -> new LogBlock(MaterialColor.PINK_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_BOREAL_SOLID_STRIPPED = BLOCKS.register("log_boreal_solid_stripped", () -> new LogBlock(MaterialColor.BROWN_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_PALM_SOLID_STRIPPED = BLOCKS.register("log_palm_solid_stripped", () -> new LogBlock(MaterialColor.YELLOW_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_MAHOGANY_SOLID_STRIPPED = BLOCKS.register("log_mahogany_solid_stripped", () -> new LogBlock(MaterialColor.NETHERRACK, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_EBON_SOLID_STRIPPED = BLOCKS.register("log_ebon_solid_stripped", () -> new LogBlock(MaterialColor.PURPLE_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_SHADE_SOLID_STRIPPED = BLOCKS.register("log_shade_solid_stripped", () -> new LogBlock(MaterialColor.RED_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_PEARL_SOLID_STRIPPED = BLOCKS.register("log_pearl_solid_stripped", () -> new LogBlock(MaterialColor.PINK_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> LOG_MUSHROOM = BLOCKS.register("log_mushroom", () -> new LogBlock(MaterialColor.CYAN_TERRACOTTA, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)));
    public static final RegistryObject<Block> MUSHROOM_CAP = BLOCKS.register("mushroom_cap", () -> new HugeMushroomBlock(Block.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(0.2F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PLANK_BOREAL = BLOCKS.register("plank_boreal", () -> new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.2F, 0.3F)));
    public static final RegistryObject<Block> PLANK_PALM = BLOCKS.register("plank_palm", () -> new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.2F, 0.3F)));
    public static final RegistryObject<Block> PLANK_MAHOGANY = BLOCKS.register("plank_mahogany", () -> new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.2F, 0.3F)));
    public static final RegistryObject<Block> PLANK_EBON = BLOCKS.register("plank_ebon", () -> new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.2F, 0.3F)));
    public static final RegistryObject<Block> PLANK_SHADE = BLOCKS.register("plank_shade", () -> new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.2F, 0.3F)));
    public static final RegistryObject<Block> PLANK_PEARL = BLOCKS.register("plank_pearl", () -> new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.2F, 0.3F)));
    public static final RegistryObject<Block> PLANK_SPOOKY = BLOCKS.register("plank_spooky", () -> new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.2F, 0.3F)));
    public static final RegistryObject<Block> PLANK_DYNASTY = BLOCKS.register("plank_dynasty", () -> new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.2F, 0.3F)));


    /*public static final RegistryObject<Block> PLANT_THORN_PURPLE = BLOCKS.register("plant_thorn_purple", () -> new TerraBlockThornBush(Block.Properties.create(Material.PLANTS).sound(SoundType.PLANT), 4.0F));
    public static final RegistryObject<Block> PLANT_THORN_RED = BLOCKS.register("plant_thorn_red", () -> new TerraBlockThornBush(Block.Properties.create(Material.PLANTS).sound(SoundType.PLANT), 4.0F));
    public static final RegistryObject<Block> PLANT_THORN_JUNGLE = BLOCKS.register("plant_thorn_jungle", () -> new TerraBlockThornBush(Block.Properties.create(Material.PLANTS).sound(SoundType.PLANT), 8.0F));

    public static final RegistryObject<Block> TALLGRASS_CORRUPT = BLOCKS.register("tallgrass_corrupt", () -> new TerraBlockTallGrass(Block.Properties.create(Material.PLANTS).sound(SoundType.PLANT).hardnessAndResistance(0.0F)));
    public static final RegistryObject<Block> TALLGRASS_CRIMSON = BLOCKS.register("tallgrass_crimson", () -> new TerraBlockTallGrass(Block.Properties.create(Material.PLANTS).sound(SoundType.PLANT).hardnessAndResistance(0.0F)));
    public static final RegistryObject<Block> TALLGRASS_HALLOWED = BLOCKS.register("tallgrass_hallowed", () -> new TerraBlockTallGrass(Block.Properties.create(Material.PLANTS).sound(SoundType.PLANT).hardnessAndResistance(0.0F)));
    public static final RegistryObject<Block> TALLGRASSDOUBLE_CORRUPT = BLOCKS.register("tallgrassdouble_corrupt", () -> new TerraBlockDoubleGrass(Block.Properties.create(Material.PLANTS).sound(SoundType.PLANT).hardnessAndResistance(0.0F)));
    public static final RegistryObject<Block> TALLGRASSDOUBLE_CRIMSON = BLOCKS.register("tallgrassdouble_crimson", () -> new TerraBlockDoubleGrass(Block.Properties.create(Material.PLANTS).sound(SoundType.PLANT).hardnessAndResistance(0.0F)));
    public static final RegistryObject<Block> TALLGRASSDOUBLE_HALLOWED = BLOCKS.register("tallgrassdouble_hallowed", () -> new TerraBlockDoubleGrass(Block.Properties.create(Material.PLANTS).sound(SoundType.PLANT).hardnessAndResistance(0.0F)));
*/
}
