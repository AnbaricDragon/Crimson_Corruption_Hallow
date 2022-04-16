package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.blocks.*;
import com.anbaric.terra_reforged.blocks.potionplants.*;
import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.anbaric.terra_reforged.util.handlers.SpreadingHandler.EnumBiomeType;

public class TerraBlockRegistry
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MODID);

    //Soils
    public static final RegistryObject<Block> SOIL_MUD = BLOCKS.register("soil_mud", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.GRAVEL).strength(0.7F)));
    public static final RegistryObject<Block> SOIL_ASH = BLOCKS.register("soil_ash", () -> new Block(BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.GRAVEL).strength(0.7F)));
    public static final RegistryObject<Block> SNOW_CORRUPT = BLOCKS.register("snow_corrupt", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.SNOW).randomTicks().sound(SoundType.SNOW).strength(0.5F, 3.0F), EnumBiomeType.CORRUPT, TerraReforged.BOREAL));
    public static final RegistryObject<Block> SNOW_CRIMSON = BLOCKS.register("snow_crimson", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.SNOW).randomTicks().sound(SoundType.SNOW).strength(0.5F, 3.0F), EnumBiomeType.CRIMSON, TerraReforged.BOREAL));
    public static final RegistryObject<Block> SNOW_HALLOWED = BLOCKS.register("snow_hallowed", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.SNOW).randomTicks().sound(SoundType.SNOW).strength(0.5F, 3.0F), EnumBiomeType.HALLOWED, TerraReforged.BOREAL));
    public static final RegistryObject<Block> SNOW_CORRUPT_LAYER = BLOCKS.register("snow_corrupt_layer", () -> new SnowLayerBlock(BlockBehaviour.Properties.of(Material.TOP_SNOW).randomTicks().strength(0.1F).requiresCorrectToolForDrops().sound(SoundType.SNOW).isViewBlocking((p_187417_, p_187418_, p_187419_) -> { return p_187417_.getValue(SnowLayerBlock.LAYERS) >= 8;})));
    public static final RegistryObject<Block> SNOW_CRIMSON_LAYER = BLOCKS.register("snow_crimson_layer", () -> new SnowLayerBlock(BlockBehaviour.Properties.of(Material.TOP_SNOW).randomTicks().strength(0.1F).requiresCorrectToolForDrops().sound(SoundType.SNOW).isViewBlocking((p_187417_, p_187418_, p_187419_) -> { return p_187417_.getValue(SnowLayerBlock.LAYERS) >= 8;})));
    public static final RegistryObject<Block> SNOW_HALLOWED_LAYER = BLOCKS.register("snow_hallowed_layer", () -> new SnowLayerBlock(BlockBehaviour.Properties.of(Material.TOP_SNOW).randomTicks().strength(0.1F).requiresCorrectToolForDrops().sound(SoundType.SNOW).isViewBlocking((p_187417_, p_187418_, p_187419_) -> { return p_187417_.getValue(SnowLayerBlock.LAYERS) >= 8;})));
    public static final RegistryObject<Block> GRASS_JUNGLE = BLOCKS.register("grass_jungle", () -> new TerraBlockJungleGrass(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().strength(0.6F).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> GRASS_MUSHROOM = BLOCKS.register("grass_mushroom", () -> new TerraBlockMushroomGrass(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().strength(0.6F).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> GRASS_CORRUPT = BLOCKS.register("grass_corrupt", () -> new TerraBlockBiomeGrass(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().strength(0.6F).sound(SoundType.GRASS), EnumBiomeType.CORRUPT));
    public static final RegistryObject<Block> GRASS_CRIMSON = BLOCKS.register("grass_crimson", () -> new TerraBlockBiomeGrass(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().strength(0.6F).sound(SoundType.GRASS), EnumBiomeType.CRIMSON));
    public static final RegistryObject<Block> GRASS_HALLOWED = BLOCKS.register("grass_hallowed", () -> new TerraBlockBiomeGrass(BlockBehaviour.Properties.of(Material.PLANT).randomTicks().strength(0.6F).sound(SoundType.GRASS), EnumBiomeType.HALLOWED));
    public static final RegistryObject<Block> SAND_EBON = BLOCKS.register("sand_ebon", () -> new TerraBlockSand(25, BlockBehaviour.Properties.of(Material.SAND).sound(SoundType.SAND).strength(0.5F).randomTicks(), EnumBiomeType.CORRUPT, PlantType.BEACH, PlantType.DESERT));
    public static final RegistryObject<Block> SAND_CRIM = BLOCKS.register("sand_crim", () -> new TerraBlockSand(30, BlockBehaviour.Properties.of(Material.SAND).sound(SoundType.SAND).strength(0.5F).randomTicks(), EnumBiomeType.CRIMSON, PlantType.BEACH, PlantType.DESERT));
    public static final RegistryObject<Block> SAND_PEARL = BLOCKS.register("sand_pearl", () -> new TerraBlockSand(40, BlockBehaviour.Properties.of(Material.SAND).sound(SoundType.SAND).strength(0.5F).randomTicks(), EnumBiomeType.HALLOWED, PlantType.BEACH, PlantType.DESERT));
    public static final RegistryObject<Block> SAND_SILT = BLOCKS.register("sand_silt", () -> new SandBlock(25, BlockBehaviour.Properties.of(Material.SAND).sound(SoundType.SAND).strength(0.5F)));
    public static final RegistryObject<Block> SAND_SLUSH = BLOCKS.register("sand_slush", () -> new SandBlock(25, BlockBehaviour.Properties.of(Material.SAND).sound(SoundType.SAND).strength(0.5F)));

    //Stones
    public static final RegistryObject<Block> SANDSTONE_EBON = BLOCKS.register("sandstone_ebon", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).randomTicks().strength(0.8F).randomTicks(), EnumBiomeType.CORRUPT, PlantType.CAVE));
    public static final RegistryObject<Block> SANDSTONE_EBON_CHISELED = BLOCKS.register("sandstone_ebon_chiseled", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.8F).randomTicks(), EnumBiomeType.CORRUPT, PlantType.CAVE));
    public static final RegistryObject<Block> SANDSTONE_EBON_SMOOTH = BLOCKS.register("sandstone_ebon_smooth", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.8F).randomTicks(), EnumBiomeType.CORRUPT, PlantType.CAVE));
    public static final RegistryObject<Block> SANDSTONE_EBON_CUT = BLOCKS.register("sandstone_ebon_cut", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.8F).randomTicks(), EnumBiomeType.CORRUPT, PlantType.CAVE));
    public static final RegistryObject<Block> SANDSTONE_CRIM = BLOCKS.register("sandstone_crim", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.8F).randomTicks(), EnumBiomeType.CRIMSON, PlantType.CAVE));
    public static final RegistryObject<Block> SANDSTONE_CRIM_CHISELED = BLOCKS.register("sandstone_crim_chiseled", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.8F).randomTicks(), EnumBiomeType.CRIMSON, PlantType.CAVE));
    public static final RegistryObject<Block> SANDSTONE_CRIM_SMOOTH = BLOCKS.register("sandstone_crim_smooth", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.8F).randomTicks(), EnumBiomeType.CRIMSON, PlantType.CAVE));
    public static final RegistryObject<Block> SANDSTONE_CRIM_CUT = BLOCKS.register("sandstone_crim_cut", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.8F).randomTicks(), EnumBiomeType.CRIMSON, PlantType.CAVE));
    public static final RegistryObject<Block> SANDSTONE_PEARL = BLOCKS.register("sandstone_pearl", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.8F).randomTicks(), EnumBiomeType.HALLOWED, PlantType.CAVE));
    public static final RegistryObject<Block> SANDSTONE_PEARL_CHISELED = BLOCKS.register("sandstone_pearl_chiseled", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.8F).randomTicks(), EnumBiomeType.HALLOWED, PlantType.CAVE));
    public static final RegistryObject<Block> SANDSTONE_PEARL_SMOOTH = BLOCKS.register("sandstone_pearl_smooth", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.8F).randomTicks(), EnumBiomeType.HALLOWED, PlantType.CAVE));
    public static final RegistryObject<Block> SANDSTONE_PEARL_CUT = BLOCKS.register("sandstone_pearl_cut", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.8F).randomTicks(), EnumBiomeType.HALLOWED, PlantType.CAVE));
    public static final RegistryObject<Block> STONE_EBON = BLOCKS.register("stone_ebon", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.CORRUPT, PlantType.CAVE));
    public static final RegistryObject<Block> STONE_EBON_SMOOTH = BLOCKS.register("stone_ebon_smooth", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.CORRUPT, PlantType.CAVE));
    public static final RegistryObject<Block> COBBLESTONE_EBON = BLOCKS.register("cobblestone_ebon", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.CORRUPT, PlantType.CAVE));
    public static final RegistryObject<Block> COBBLESTONE_EBON_MOSSY = BLOCKS.register("cobblestone_ebon_mossy", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.CORRUPT, PlantType.CAVE));
    public static final RegistryObject<Block> STONEBRICK_EBON = BLOCKS.register("stonebrick_ebon", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.CORRUPT, PlantType.CAVE));
    public static final RegistryObject<Block> STONEBRICK_EBON_CRACKED = BLOCKS.register("stonebrick_ebon_cracked", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.CORRUPT, PlantType.CAVE));
    public static final RegistryObject<Block> STONEBRICK_EBON_CHISELED = BLOCKS.register("stonebrick_ebon_chiseled", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.CORRUPT, PlantType.CAVE));
    public static final RegistryObject<Block> STONEBRICK_EBON_MOSSY = BLOCKS.register("stonebrick_ebon_mossy", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.CORRUPT, PlantType.CAVE));
    public static final RegistryObject<Block> STONE_CRIM = BLOCKS.register("stone_crim", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.CRIMSON, PlantType.CAVE));
    public static final RegistryObject<Block> STONE_CRIM_SMOOTH = BLOCKS.register("stone_crim_smooth", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.CRIMSON, PlantType.CAVE));
    public static final RegistryObject<Block> COBBLESTONE_CRIM = BLOCKS.register("cobblestone_crim", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.CRIMSON, PlantType.CAVE));
    public static final RegistryObject<Block> COBBLESTONE_CRIM_MOSSY = BLOCKS.register("cobblestone_crim_mossy", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.CRIMSON, PlantType.CAVE));
    public static final RegistryObject<Block> STONEBRICK_CRIM = BLOCKS.register("stonebrick_crim", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.CRIMSON, PlantType.CAVE));
    public static final RegistryObject<Block> STONEBRICK_CRIM_CRACKED = BLOCKS.register("stonebrick_crim_cracked", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.CRIMSON, PlantType.CAVE));
    public static final RegistryObject<Block> STONEBRICK_CRIM_CHISELED = BLOCKS.register("stonebrick_crim_chiseled", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.CRIMSON, PlantType.CAVE));
    public static final RegistryObject<Block> STONEBRICK_CRIM_MOSSY = BLOCKS.register("stonebrick_crim_mossy", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.CRIMSON, PlantType.CAVE));
    public static final RegistryObject<Block> STONE_PEARL = BLOCKS.register("stone_pearl", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.HALLOWED, PlantType.CAVE));
    public static final RegistryObject<Block> STONE_PEARL_SMOOTH = BLOCKS.register("stone_pearl_smooth", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.HALLOWED, PlantType.CAVE));
    public static final RegistryObject<Block> COBBLESTONE_PEARL = BLOCKS.register("cobblestone_pearl", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.HALLOWED, PlantType.CAVE));
    public static final RegistryObject<Block> COBBLESTONE_PEARL_MOSSY = BLOCKS.register("cobblestone_pearl_mossy", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.HALLOWED, PlantType.CAVE));
    public static final RegistryObject<Block> STONEBRICK_PEARL = BLOCKS.register("stonebrick_pearl", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.HALLOWED, PlantType.CAVE));
    public static final RegistryObject<Block> STONEBRICK_PEARL_CRACKED = BLOCKS.register("stonebrick_pearl_cracked", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.HALLOWED, PlantType.CAVE));
    public static final RegistryObject<Block> STONEBRICK_PEARL_CHISELED = BLOCKS.register("stonebrick_pearl_chiseled", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.HALLOWED, PlantType.CAVE));
    public static final RegistryObject<Block> STONEBRICK_PEARL_MOSSY = BLOCKS.register("stonebrick_pearl_mossy", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks(), EnumBiomeType.HALLOWED, PlantType.CAVE));
    public static final RegistryObject<Block> STONE_MOSS_RED = BLOCKS.register("stone_moss_red", () -> new TerraBlockMoss(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks()));
    public static final RegistryObject<Block> STONE_MOSS_YELLOW = BLOCKS.register("stone_moss_yellow", () -> new TerraBlockMoss(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks()));
    public static final RegistryObject<Block> STONE_MOSS_GREEN = BLOCKS.register("stone_moss_green", () -> new TerraBlockMoss(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks()));
    public static final RegistryObject<Block> STONE_MOSS_BLUE = BLOCKS.register("stone_moss_blue", () -> new TerraBlockMoss(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks()));
    public static final RegistryObject<Block> STONE_MOSS_PURPLE = BLOCKS.register("stone_moss_purple", () -> new TerraBlockMoss(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks()));
    public static final RegistryObject<Block> STONE_MOSS_FIRE = BLOCKS.register("stone_moss_fire", () -> new TerraBlockMoss(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F).randomTicks()));
    public static final RegistryObject<Block> STONE_GRANITE = BLOCKS.register("stone_granite", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> STONE_MARBLE = BLOCKS.register("stone_marble", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> STONE_FOSSIL = BLOCKS.register("stone_fossil", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> ICE_PURPLE = BLOCKS.register("ice_purple", () -> new TerraBlockIce(BlockBehaviour.Properties.of(Material.ICE).sound(SoundType.GLASS).friction(0.98F).randomTicks().strength(0.5F).noOcclusion(), EnumBiomeType.CORRUPT, TerraReforged.BOREAL));
    public static final RegistryObject<Block> ICE_HARD_PURPLE = BLOCKS.register("ice_hard_purple", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.ICE_SOLID).sound(SoundType.GLASS).strength(0.8F), EnumBiomeType.CORRUPT, TerraReforged.BOREAL));
    public static final RegistryObject<Block> ICE_RED = BLOCKS.register("ice_red", () -> new TerraBlockIce(BlockBehaviour.Properties.of(Material.ICE).sound(SoundType.GLASS).friction(0.98F).randomTicks().strength(0.5F).noOcclusion(), EnumBiomeType.CRIMSON, TerraReforged.BOREAL));
    public static final RegistryObject<Block> ICE_HARD_RED = BLOCKS.register("ice_hard_red", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.ICE_SOLID).sound(SoundType.GLASS).strength(0.8F), EnumBiomeType.CRIMSON, TerraReforged.BOREAL));
    public static final RegistryObject<Block> ICE_PINK = BLOCKS.register("ice_pink", () -> new TerraBlockIce(BlockBehaviour.Properties.of(Material.ICE).sound(SoundType.GLASS).friction(0.98F).randomTicks().strength(0.5F).noOcclusion(), EnumBiomeType.HALLOWED, TerraReforged.BOREAL));
    public static final RegistryObject<Block> ICE_HARD_PINK = BLOCKS.register("ice_hard_pink", () -> new TerraBlockSpreading(BlockBehaviour.Properties.of(Material.ICE_SOLID).sound(SoundType.GLASS).strength(0.8F), EnumBiomeType.HALLOWED, TerraReforged.BOREAL));
    public static final RegistryObject<Block> ICE_THIN = BLOCKS.register("ice_thin", () -> new TerraBlockThinIce(BlockBehaviour.Properties.of(Material.ICE).sound(SoundType.GLASS).friction(0.98F).strength(0.25F).noOcclusion()));

    //Woods
    public static final RegistryObject<Block> LOG_BOREAL = BLOCKS.register("log_boreal", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_PALM = BLOCKS.register("log_palm", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_MAHOGANY = BLOCKS.register("log_mahogany", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_EBON = BLOCKS.register("log_ebon", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PURPLE).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_SHADE = BLOCKS.register("log_shade", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_PEARL = BLOCKS.register("log_pearl", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_BOREAL_SOLID = BLOCKS.register("log_boreal_solid", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BROWN).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_PALM_SOLID = BLOCKS.register("log_palm_solid", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_MAHOGANY_SOLID = BLOCKS.register("log_mahogany_solid", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_EBON_SOLID = BLOCKS.register("log_ebon_solid", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PURPLE).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_SHADE_SOLID = BLOCKS.register("log_shade_solid", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_PEARL_SOLID = BLOCKS.register("log_pearl_solid", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_BOREAL_STRIPPED = BLOCKS.register("log_boreal_stripped", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BROWN).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_PALM_STRIPPED = BLOCKS.register("log_palm_stripped", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_MAHOGANY_STRIPPED = BLOCKS.register("log_mahogany_stripped", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_EBON_STRIPPED = BLOCKS.register("log_ebon_stripped", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PURPLE).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_SHADE_STRIPPED = BLOCKS.register("log_shade_stripped", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_PEARL_STRIPPED = BLOCKS.register("log_pearl_stripped", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_BOREAL_SOLID_STRIPPED = BLOCKS.register("log_boreal_solid_stripped", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BROWN).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_PALM_SOLID_STRIPPED = BLOCKS.register("log_palm_solid_stripped", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_MAHOGANY_SOLID_STRIPPED = BLOCKS.register("log_mahogany_solid_stripped", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_EBON_SOLID_STRIPPED = BLOCKS.register("log_ebon_solid_stripped", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PURPLE).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_SHADE_SOLID_STRIPPED = BLOCKS.register("log_shade_solid_stripped", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_PEARL_SOLID_STRIPPED = BLOCKS.register("log_pearl_solid_stripped", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> LOG_MUSHROOM = BLOCKS.register("log_mushroom", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_CYAN).sound(SoundType.WOOD).strength(2.0F)));
    public static final RegistryObject<Block> MUSHROOM_CAP = BLOCKS.register("mushroom_cap", () -> new HugeMushroomBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PLANK_BOREAL = BLOCKS.register("plank_boreal", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F)));
    public static final RegistryObject<Block> PLANK_PALM = BLOCKS.register("plank_palm", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F)));
    public static final RegistryObject<Block> PLANK_MAHOGANY = BLOCKS.register("plank_mahogany", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F)));
    public static final RegistryObject<Block> PLANK_EBON = BLOCKS.register("plank_ebon", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F)));
    public static final RegistryObject<Block> PLANK_SHADE = BLOCKS.register("plank_shade", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F)));
    public static final RegistryObject<Block> PLANK_PEARL = BLOCKS.register("plank_pearl", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F)));
    public static final RegistryObject<Block> PLANK_SPOOKY = BLOCKS.register("plank_spooky", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F)));
    public static final RegistryObject<Block> PLANK_DYNASTY = BLOCKS.register("plank_dynasty", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2.0F, 3.0F)));
//
//    //Foliage
    public static final RegistryObject<Block> PLANT_MOSS_RED = BLOCKS.register("plant_moss_red", () -> new TerraBlockPlantMoss(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> PLANT_MOSS_YELLOW = BLOCKS.register("plant_moss_yellow", () -> new TerraBlockPlantMoss(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> PLANT_MOSS_GREEN = BLOCKS.register("plant_moss_green", () -> new TerraBlockPlantMoss(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> PLANT_MOSS_BLUE = BLOCKS.register("plant_moss_blue", () -> new TerraBlockPlantMoss(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> PLANT_MOSS_PURPLE = BLOCKS.register("plant_moss_purple", () -> new TerraBlockPlantMoss(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> PLANT_MOSS_FIRE = BLOCKS.register("plant_moss_fire", () -> new TerraBlockPlantMoss(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> PLANT_BLINKROOT = BLOCKS.register("plant_blinkroot", () -> new TerraBlockBlinkroot(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.CROP).noCollission().randomTicks().strength(0.0F)));
    public static final RegistryObject<Block> PLANT_DAYBLOOM = BLOCKS.register("plant_daybloom", () -> new TerraBlockDaybloom(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.CROP).noCollission().randomTicks().strength(0.0F)));
    public static final RegistryObject<Block> PLANT_DEATHWEED = BLOCKS.register("plant_deathweed", () -> new TerraBlockDeathweed(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.CROP).noCollission().randomTicks().strength(0.0F)));
    public static final RegistryObject<Block> PLANT_FIREBLOSSOM = BLOCKS.register("plant_fireblossom", () -> new TerraBlockFireblossom(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.CROP).noCollission().randomTicks().strength(0.0F)));
    public static final RegistryObject<Block> PLANT_MOONGLOW = BLOCKS.register("plant_moonglow", () -> new TerraBlockMoonglow(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.CROP).noCollission().randomTicks().strength(0.0F)));
    public static final RegistryObject<Block> PLANT_SHIVERTHORN = BLOCKS.register("plant_shiverthorn", () -> new TerraBlockShiverthorn(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.CROP).noCollission().randomTicks().strength(0.0F)));
    public static final RegistryObject<Block> PLANT_WATERLEAF = BLOCKS.register("plant_waterleaf", () -> new TerraBlockWaterleaf(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.CROP).noCollission().randomTicks().strength(0.0F)));
//    public static final RegistryObject<Block> PLANT_MUSHROOM_GLOWING = BLOCKS.register("plant_mushroom_glowing", () -> new TerraBlockGlowingMushroom(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.GRASS).noCollission().strength(0.0F).randomTicks()));
    public static final RegistryObject<Block> PLANT_MUSHROOM_VILE = BLOCKS.register("plant_mushroom_vile", () -> new TerraBlockEvilMushroom(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS), TerraTagRegistry.CORRUPT_PLANTERS));
    public static final RegistryObject<Block> PLANT_MUSHROOM_VICIOUS = BLOCKS.register("plant_mushroom_vicious", () -> new TerraBlockEvilMushroom(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS), TerraTagRegistry.CRIMSON_PLANTERS));
    public static final RegistryObject<Block> PLANT_LIFEFRUIT = BLOCKS.register("plant_lifefruit", () -> new TerraBlockLifefruit(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.GRASS).noCollission().strength(0.0F)));
    public static final RegistryObject<Block> PLANT_BLOODROOT = BLOCKS.register("plant_bloodroot", () -> new TerraBlockPlantHanging(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.GRASS).noCollission().strength(0.0F)));
    public static final RegistryObject<Block> PLANT_DYE_PURPLE = BLOCKS.register("plant_dye_purple", () -> new DoublePlantBlock(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.GRASS).noCollission().strength(0.5F)));
    public static final RegistryObject<Block> PLANT_DYE_ORANGE = BLOCKS.register("plant_dye_orange", () -> new DoublePlantBlock(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.GRASS).noCollission().strength(0.5F)));
    public static final RegistryObject<Block> PLANT_DYE_CYAN = BLOCKS.register("plant_dye_cyan", () -> new DoublePlantBlock(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.GRASS).noCollission().strength(0.5F)));
    public static final RegistryObject<Block> PLANT_DYE_RED = BLOCKS.register("plant_dye_red", () -> new DoublePlantBlock(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.GRASS).noCollission().strength(0.5F)));
    public static final RegistryObject<Block> PLANT_THORN_PURPLE = BLOCKS.register("plant_thorn_purple", () -> new TerraBlockPlantThorns(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.GRASS).noCollission().randomTicks(), 4.0F));
    public static final RegistryObject<Block> PLANT_THORN_RED = BLOCKS.register("plant_thorn_red", () -> new TerraBlockPlantThorns(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.GRASS).noCollission().randomTicks(), 4.0F));
    public static final RegistryObject<Block> PLANT_THORN_JUNGLE = BLOCKS.register("plant_thorn_jungle", () -> new TerraBlockPlantThorns(BlockBehaviour.Properties.of(Material.PLANT).sound(SoundType.GRASS).noCollission().randomTicks(), 8.0F));
    public static final RegistryObject<Block> LEAF_BOREAL = BLOCKS.register("leaf_boreal", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> LEAF_PALM = BLOCKS.register("leaf_palm", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> LEAF_MAHOGANY = BLOCKS.register("leaf_mahogany", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> LEAF_EBON = BLOCKS.register("leaf_ebon", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> LEAF_SHADE = BLOCKS.register("leaf_shade", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> LEAF_PEARL_RED = BLOCKS.register("leaf_pearl_red", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> LEAF_PEARL_YELLOW = BLOCKS.register("leaf_pearl_yellow", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> LEAF_PEARL_PINK = BLOCKS.register("leaf_pearl_pink", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> LEAF_PEARL_MAGENTA = BLOCKS.register("leaf_pearl_magenta", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> LEAF_PEARL_CYAN = BLOCKS.register("leaf_pearl_cyan", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> LEAF_PEARL_BLUE = BLOCKS.register("leaf_pearl_blue", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> LEAF_PEARL_GREEN = BLOCKS.register("leaf_pearl_green", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> LEAF_PEARL_PURPLE = BLOCKS.register("leaf_pearl_purple", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> CACTUS_EBON = BLOCKS.register("cactus_ebon", () -> new TerraBlockPlantCactus(BlockBehaviour.Properties.of(Material.CACTUS).randomTicks().strength(0.4F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> CACTUS_CRIM = BLOCKS.register("cactus_crim", () -> new TerraBlockPlantCactus(BlockBehaviour.Properties.of(Material.CACTUS).randomTicks().strength(0.4F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> CACTUS_PEARL = BLOCKS.register("cactus_pearl", () -> new TerraBlockPlantCactus(BlockBehaviour.Properties.of(Material.CACTUS).randomTicks().strength(0.4F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> REED_CORRUPT = BLOCKS.register("reed_corrupt", () -> new TerraBlockPlantReeds(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> REED_CRIMSON = BLOCKS.register("reed_crimson", () -> new TerraBlockPlantReeds(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> REED_HALLOWED = BLOCKS.register("reed_hallowed", () -> new TerraBlockPlantReeds(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> LILYPAD_CORRUPT = BLOCKS.register("lilypad_corrupt", () -> new WaterlilyBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> LILYPAD_CRIMSON = BLOCKS.register("lilypad_crimson", () -> new WaterlilyBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> LILYPAD_HALLOWED = BLOCKS.register("lilypad_hallowed", () -> new WaterlilyBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> VINE_RED = BLOCKS.register("vine_red", () -> new VineBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().sound(SoundType.GRASS).randomTicks().noOcclusion()));
    public static final RegistryObject<Block> VINE_YELLOW = BLOCKS.register("vine_yellow", () -> new VineBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().sound(SoundType.GRASS).randomTicks().noOcclusion()));
    public static final RegistryObject<Block> VINE_PINK = BLOCKS.register("vine_pink", () -> new VineBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().sound(SoundType.GRASS).randomTicks().noOcclusion()));
    public static final RegistryObject<Block> VINE_MAGENTA = BLOCKS.register("vine_magenta", () -> new VineBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().sound(SoundType.GRASS).randomTicks().noOcclusion()));
    public static final RegistryObject<Block> VINE_CYAN = BLOCKS.register("vine_cyan", () -> new VineBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().sound(SoundType.GRASS).randomTicks().noOcclusion()));
    public static final RegistryObject<Block> VINE_BLUE = BLOCKS.register("vine_blue", () -> new VineBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().sound(SoundType.GRASS).randomTicks().noOcclusion()));
    public static final RegistryObject<Block> VINE_GREEN = BLOCKS.register("vine_green", () -> new VineBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().sound(SoundType.GRASS).randomTicks().noOcclusion()));
    public static final RegistryObject<Block> VINE_PURPLE = BLOCKS.register("vine_purple", () -> new VineBlock(BlockBehaviour.Properties.of(Material.PLANT).instabreak().sound(SoundType.GRASS).randomTicks().noOcclusion()));
//    public static final RegistryObject<Block> SAPLING_BOREAL = BLOCKS.register("sapling_boreal", () -> new TerraBlockSaplingBoreal(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
//    public static final RegistryObject<Block> SAPLING_PALM = BLOCKS.register("sapling_palm", () -> new TerraBlockSaplingPalm(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
//    public static final RegistryObject<Block> SAPLING_MAHOGANY = BLOCKS.register("sapling_mahogany", () -> new TerraBlockSaplingMahogany(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
//    public static final RegistryObject<Block> SAPLING_EBON = BLOCKS.register("sapling_ebon", () -> new TerraBlockSaplingEbon(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
//    public static final RegistryObject<Block> SAPLING_SHADE = BLOCKS.register("sapling_shade", () -> new TerraBlockSaplingShade(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
//    public static final RegistryObject<Block> SAPLING_PEARL = BLOCKS.register("sapling_pearl", () -> new TerraBlockSaplingPearl(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
//
//    //Furnature
//    public static final RegistryObject<Block> TALL_LANTERN_BONE = BLOCKS.register("tall_lantern_bone", () -> new TerraBlockDoubleLantern(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE), 1.0D, 4.0D, 14));
//    public static final RegistryObject<Block> TALL_LANTERN_FIREFLY = BLOCKS.register("tall_lantern_firefly", () -> new TerraBlockDoubleLantern(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GLASS), 1.0D, 6.0D, 14));
//    public static final RegistryObject<Block> TALL_LANTERN_LIGHTNINGBUG = BLOCKS.register("tall_lantern_lightningbug", () -> new TerraBlockDoubleLantern(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GLASS), 1.0D, 6.0D, 14));
//    public static final RegistryObject<Block> LANTERN_HEART = BLOCKS.register("lantern_heart", () -> new TerraBlockHeartLantern(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GLASS)));
//    public static final RegistryObject<Block> CHEST_GOLD = BLOCKS.register("chest_gold", () -> new ChestBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD), () -> { return TileEntityType.CHEST; }));
//
//    //Structure Blocks
    public static final RegistryObject<Block> BRICK_SNOW = BLOCKS.register("brick_snow", () -> new Block(BlockBehaviour.Properties.of(Material.SNOW).sound(SoundType.SNOW).strength(0.7F, 3.0F)));
    public static final RegistryObject<Block> BRICK_ICE = BLOCKS.register("brick_ice", () -> new Block(BlockBehaviour.Properties.of(Material.ICE).sound(SoundType.GLASS).strength(0.7F, 3.0F)));
    public static final RegistryObject<Block> BRICK_MUDSTONE = BLOCKS.register("brick_mudstone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> BRICK_ASHSTONE = BLOCKS.register("brick_ashstone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> BRICK_COPPER = BLOCKS.register("brick_copper", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> BRICK_TIN = BLOCKS.register("brick_tin", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> BRICK_SILVER = BLOCKS.register("brick_silver", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> BRICK_TUNGSTEN = BLOCKS.register("brick_tungsten", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> BRICK_GOLD = BLOCKS.register("brick_gold", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> BRICK_PLATINUM = BLOCKS.register("brick_platinum", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> BRICK_DEMONITE = BLOCKS.register("brick_demonite", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> BRICK_CRIMTANE = BLOCKS.register("brick_crimtane", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> BRICK_METEORITE = BLOCKS.register("brick_meteorite", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> BRICK_OBSIDIAN = BLOCKS.register("brick_obsidian", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(50.0F, 1200.0F)));
    public static final RegistryObject<Block> BRICK_HELLSTONE = BLOCKS.register("brick_hellstone", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> BRICK_COBALT = BLOCKS.register("brick_cobalt", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> BRICK_PALLADIUM = BLOCKS.register("brick_palladium", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> BRICK_MYTHRIL = BLOCKS.register("brick_mythril", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> BRICK_ORICHALCUM = BLOCKS.register("brick_orichalcum", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> BRICK_ADAMANTITE = BLOCKS.register("brick_adamantite", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> BRICK_TITANIUM = BLOCKS.register("brick_titanium", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> BRICK_CHLOROPHYTE = BLOCKS.register("brick_chlorophyte", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> BRICK_LUMINITE = BLOCKS.register("brick_luminite", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> BRICK_RAINBOW = BLOCKS.register("brick_rainbow", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> BRICK_SUNPLATE = BLOCKS.register("brick_sunplate", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> BRICK_STUCCO = BLOCKS.register("brick_stucco", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> BRICK_STUCCO_RED = BLOCKS.register("brick_stucco_red", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> BRICK_STUCCO_GREEN = BLOCKS.register("brick_stucco_green", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> BRICK_STUCCO_YELLOW = BLOCKS.register("brick_stucco_yellow", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> BRICK_FLESH = BLOCKS.register("brick_flesh", () -> new Block(BlockBehaviour.Properties.of(Material.SPONGE).sound(SoundType.GRASS).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> PLATE_TIN = BLOCKS.register("plate_tin", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> PLATE_COPPER = BLOCKS.register("plate_copper", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> PLATE_MARTIAN = BLOCKS.register("plate_martian", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> PLATE_SHROOMITE = BLOCKS.register("plate_shroomite", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.0F)));
    public static final RegistryObject<Block> BRICK_CRYSTAL = BLOCKS.register("brick_crystal", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> BRICK_GRANITE = BLOCKS.register("brick_granite", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> BRICK_MARBLE = BLOCKS.register("brick_marble", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(1.5F, 6.0F)));

    //Structure Misc
    public static final RegistryObject<Block> WALL_OAK = BLOCKS.register("wall_oak", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_SPRUCE = BLOCKS.register("wall_spruce", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_BIRCH = BLOCKS.register("wall_birch", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_JUNGLE = BLOCKS.register("wall_jungle", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_ACACIA = BLOCKS.register("wall_acacia", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_DARKOAK = BLOCKS.register("wall_darkoak", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_OAK_LEAF = BLOCKS.register("wall_oak_leaf", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_SPRUCE_LEAF = BLOCKS.register("wall_spruce_leaf", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_BIRCH_LEAF = BLOCKS.register("wall_birch_leaf", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_JUNGLE_LEAF = BLOCKS.register("wall_jungle_leaf", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_ACACIA_LEAF = BLOCKS.register("wall_acacia_leaf", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_DARKOAK_LEAF = BLOCKS.register("wall_darkoak_leaf", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_BOREAL = BLOCKS.register("wall_boreal", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_PALM = BLOCKS.register("wall_palm", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_MAHOGANY = BLOCKS.register("wall_mahogany", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_EBON = BLOCKS.register("wall_ebon", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_SHADE = BLOCKS.register("wall_shade", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_PEARL = BLOCKS.register("wall_pearl", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_BOREAL_LEAF = BLOCKS.register("wall_boreal_leaf", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_PALM_LEAF = BLOCKS.register("wall_palm_leaf", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_MAHOGANY_LEAF = BLOCKS.register("wall_mahogany_leaf", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_EBON_LEAF = BLOCKS.register("wall_ebon_leaf", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_SHADE_LEAF = BLOCKS.register("wall_shade_leaf", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_PEARL_LEAF = BLOCKS.register("wall_pearl_leaf", () -> new WallBlock(BlockBehaviour.Properties.of(Material.LEAVES).sound(SoundType.GRASS).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_OAK_PLANK = BLOCKS.register("wall_oak_plank", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_SPRUCE_PLANK = BLOCKS.register("wall_spruce_plank", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_BIRCH_PLANK = BLOCKS.register("wall_birch_plank", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_JUNGLE_PLANK = BLOCKS.register("wall_jungle_plank", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_ACACIA_PLANK = BLOCKS.register("wall_acacia_plank", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_DARKOAK_PLANK = BLOCKS.register("wall_dark_oak_plank", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_BOREAL_PLANK = BLOCKS.register("wall_boreal_plank", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_PALM_PLANK = BLOCKS.register("wall_palm_plank", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_MAHOGANY_PLANK = BLOCKS.register("wall_mahogany_plank", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_EBON_PLANK = BLOCKS.register("wall_ebon_plank", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_SHADE_PLANK = BLOCKS.register("wall_shade_plank", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_PEARL_PLANK = BLOCKS.register("wall_pearl_plank", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_DYNASTY_PLANK = BLOCKS.register("wall_dynasty_plank", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_SPOOKY_PLANK = BLOCKS.register("wall_spooky_plank", () -> new WallBlock(BlockBehaviour.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.5F, 6.0F)));
    public static final RegistryObject<Block> WALL_STONEBRICK_EBON = BLOCKS.register("wall_stonebrick_ebon", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F)));
    public static final RegistryObject<Block> WALL_STONEBRICK_CRIM = BLOCKS.register("wall_stonebrick_crim", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F)));
    public static final RegistryObject<Block> WALL_STONEBRICK_PEARL = BLOCKS.register("wall_stonebrick_pearl", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F)));
    public static final RegistryObject<Block> WALL_COBBLESTONE_EBON = BLOCKS.register("wall_cobblestone_ebon", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F)));
    public static final RegistryObject<Block> WALL_COBBLESTONE_CRIM = BLOCKS.register("wall_cobblestone_crim", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F)));
    public static final RegistryObject<Block> WALL_COBBLESTONE_PEARL = BLOCKS.register("wall_cobblestone_pearl", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F)));
    public static final RegistryObject<Block> WALL_SANDSTONE_EBON = BLOCKS.register("wall_sandstone_ebon", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F)));
    public static final RegistryObject<Block> WALL_SANDSTONE_CRIM = BLOCKS.register("wall_sandstone_crim", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F)));
    public static final RegistryObject<Block> WALL_SANDSTONE_PEARL = BLOCKS.register("wall_sandstone_pearl", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(2.0F, 6.0F)));
////
    public static final RegistryObject<Block> STAIR_PLANK_BOREAL = BLOCKS.register("stair_plank_boreal", () -> new StairBlock(() -> TerraBlockRegistry.PLANK_BOREAL.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.PLANK_BOREAL.get())));
    public static final RegistryObject<Block> STAIR_PLANK_PALM = BLOCKS.register("stair_plank_palm", () -> new StairBlock(() -> TerraBlockRegistry.PLANK_PALM.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.PLANK_PALM.get())));
    public static final RegistryObject<Block> STAIR_PLANK_MAHOGANY = BLOCKS.register("stair_plank_mahogany", () -> new StairBlock(() -> TerraBlockRegistry.PLANK_MAHOGANY.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.PLANK_MAHOGANY.get())));
    public static final RegistryObject<Block> STAIR_PLANK_EBON = BLOCKS.register("stair_plank_ebon", () -> new StairBlock(() -> TerraBlockRegistry.PLANK_EBON.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.PLANK_EBON.get())));
    public static final RegistryObject<Block> STAIR_PLANK_SHADE = BLOCKS.register("stair_plank_shade", () -> new StairBlock(() -> TerraBlockRegistry.PLANK_SHADE.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.PLANK_SHADE.get())));
    public static final RegistryObject<Block> STAIR_PLANK_PEARL = BLOCKS.register("stair_plank_pearl", () -> new StairBlock(() -> TerraBlockRegistry.PLANK_PEARL.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.PLANK_PEARL.get())));
    public static final RegistryObject<Block> STAIR_PLANK_DYNASTY = BLOCKS.register("stair_plank_dynasty", () -> new StairBlock(() -> TerraBlockRegistry.PLANK_DYNASTY.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.PLANK_DYNASTY.get())));
    public static final RegistryObject<Block> STAIR_PLANK_SPOOKY = BLOCKS.register("stair_plank_spooky", () -> new StairBlock(() -> TerraBlockRegistry.PLANK_DYNASTY.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.PLANK_SPOOKY.get())));
    public static final RegistryObject<Block> STAIR_STONE_EBON = BLOCKS.register("stair_stone_ebon", () -> new StairBlock(() -> TerraBlockRegistry.STONE_EBON.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.STONE_EBON.get())));
    public static final RegistryObject<Block> STAIR_STONE_CRIM = BLOCKS.register("stair_stone_crim", () -> new StairBlock(() -> TerraBlockRegistry.STONE_CRIM.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.STONE_CRIM.get())));
    public static final RegistryObject<Block> STAIR_STONE_PEARL = BLOCKS.register("stair_stone_pearl", () -> new StairBlock(() -> TerraBlockRegistry.STONE_PEARL.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.STONE_PEARL.get())));
    public static final RegistryObject<Block> STAIR_STONEBRICK_EBON = BLOCKS.register("stair_stonebrick_ebon", () -> new StairBlock(() -> TerraBlockRegistry.STONEBRICK_EBON.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.STONEBRICK_EBON.get())));
    public static final RegistryObject<Block> STAIR_STONEBRICK_CRIM = BLOCKS.register("stair_stonebrick_crim", () -> new StairBlock(() -> TerraBlockRegistry.STONEBRICK_CRIM.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.STONEBRICK_CRIM.get())));
    public static final RegistryObject<Block> STAIR_STONEBRICK_PEARL = BLOCKS.register("stair_stonebrick_pearl", () -> new StairBlock(() -> TerraBlockRegistry.STONEBRICK_PEARL.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.STONEBRICK_PEARL.get())));
    public static final RegistryObject<Block> STAIR_STONEBRICK_EBON_MOSSY = BLOCKS.register("stair_stonebrick_ebon_mossy", () -> new StairBlock(() -> TerraBlockRegistry.STONEBRICK_EBON_MOSSY.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.STONEBRICK_EBON_MOSSY.get())));
    public static final RegistryObject<Block> STAIR_STONEBRICK_CRIM_MOSSY = BLOCKS.register("stair_stonebrick_crim_mossy", () -> new StairBlock(() -> TerraBlockRegistry.STONEBRICK_CRIM_MOSSY.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.STONEBRICK_CRIM_MOSSY.get())));
    public static final RegistryObject<Block> STAIR_STONEBRICK_PEARL_MOSSY = BLOCKS.register("stair_stonebrick_pearl_mossy", () -> new StairBlock(() -> TerraBlockRegistry.STONEBRICK_PEARL_MOSSY.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.STONEBRICK_PEARL_MOSSY.get())));
    public static final RegistryObject<Block> STAIR_COBBLESTONE_EBON = BLOCKS.register("stair_cobblestone_ebon", () -> new StairBlock(() -> TerraBlockRegistry.COBBLESTONE_EBON.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.COBBLESTONE_EBON.get())));
    public static final RegistryObject<Block> STAIR_COBBLESTONE_CRIM = BLOCKS.register("stair_cobblestone_crim", () -> new StairBlock(() -> TerraBlockRegistry.COBBLESTONE_CRIM.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.COBBLESTONE_CRIM.get())));
    public static final RegistryObject<Block> STAIR_COBBLESTONE_PEARL = BLOCKS.register("stair_cobblestone_pearl", () -> new StairBlock(() -> TerraBlockRegistry.COBBLESTONE_PEARL.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.COBBLESTONE_PEARL.get())));
    public static final RegistryObject<Block> STAIR_COBBLESTONE_EBON_MOSSY = BLOCKS.register("stair_cobblestone_ebon_mossy", () -> new StairBlock(() -> TerraBlockRegistry.COBBLESTONE_EBON_MOSSY.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.COBBLESTONE_EBON_MOSSY.get())));
    public static final RegistryObject<Block> STAIR_COBBLESTONE_CRIM_MOSSY = BLOCKS.register("stair_cobblestone_crim_mossy", () -> new StairBlock(() -> TerraBlockRegistry.COBBLESTONE_CRIM_MOSSY.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.COBBLESTONE_CRIM_MOSSY.get())));
    public static final RegistryObject<Block> STAIR_COBBLESTONE_PEARL_MOSSY = BLOCKS.register("stair_cobblestone_pearl_mossy", () -> new StairBlock(() -> TerraBlockRegistry.COBBLESTONE_PEARL_MOSSY.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.COBBLESTONE_PEARL_MOSSY.get())));
    public static final RegistryObject<Block> STAIR_SANDSTONE_EBON = BLOCKS.register("stair_sandstone_ebon", () -> new StairBlock(() -> TerraBlockRegistry.SANDSTONE_EBON.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.SANDSTONE_EBON.get())));
    public static final RegistryObject<Block> STAIR_SANDSTONE_CRIM = BLOCKS.register("stair_sandstone_crim", () -> new StairBlock(() -> TerraBlockRegistry.SANDSTONE_CRIM.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.SANDSTONE_CRIM.get())));
    public static final RegistryObject<Block> STAIR_SANDSTONE_PEARL = BLOCKS.register("stair_sandstone_pearl", () -> new StairBlock(() -> TerraBlockRegistry.SANDSTONE_PEARL.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.SANDSTONE_PEARL.get())));
    public static final RegistryObject<Block> STAIR_SANDSTONE_EBON_SMOOTH = BLOCKS.register("stair_sandstone_ebon_smooth", () -> new StairBlock(() -> TerraBlockRegistry.SANDSTONE_EBON_SMOOTH.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.SANDSTONE_EBON_SMOOTH.get())));
    public static final RegistryObject<Block> STAIR_SANDSTONE_CRIM_SMOOTH = BLOCKS.register("stair_sandstone_crim_smooth", () -> new StairBlock(() -> TerraBlockRegistry.SANDSTONE_CRIM_SMOOTH.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.SANDSTONE_CRIM_SMOOTH.get())));
    public static final RegistryObject<Block> STAIR_SANDSTONE_PEARL_SMOOTH = BLOCKS.register("stair_sandstone_pearl_smooth", () -> new StairBlock(() -> TerraBlockRegistry.SANDSTONE_PEARL_SMOOTH.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.SANDSTONE_PEARL_SMOOTH.get())));
    public static final RegistryObject<Block> STAIR_BRICK_MUDSTONE = BLOCKS.register("stair_brick_mudstone", () -> new StairBlock(() -> TerraBlockRegistry.BRICK_MUDSTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.BRICK_MUDSTONE.get())));
    public static final RegistryObject<Block> STAIR_BRICK_GOLD = BLOCKS.register("stair_brick_gold", () -> new StairBlock(() -> TerraBlockRegistry.BRICK_GOLD.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.BRICK_GOLD.get())));
//
    public static final RegistryObject<Block> SLAB_PLANK_BOREAL = BLOCKS.register("slab_plank_boreal", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.PLANK_BOREAL.get())));
    public static final RegistryObject<Block> SLAB_PLANK_PALM = BLOCKS.register("slab_plank_palm", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.PLANK_PALM.get())));
    public static final RegistryObject<Block> SLAB_PLANK_MAHOGANY = BLOCKS.register("slab_plank_mahogany", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.PLANK_MAHOGANY.get())));
    public static final RegistryObject<Block> SLAB_PLANK_EBON = BLOCKS.register("slab_plank_ebon", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.PLANK_EBON.get())));
    public static final RegistryObject<Block> SLAB_PLANK_SHADE = BLOCKS.register("slab_plank_shade", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.PLANK_SHADE.get())));
    public static final RegistryObject<Block> SLAB_PLANK_PEARL = BLOCKS.register("slab_plank_pearl", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.PLANK_PEARL.get())));
    public static final RegistryObject<Block> SLAB_PLANK_DYNASTY = BLOCKS.register("slab_plank_dynasty", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.PLANK_DYNASTY.get())));
    public static final RegistryObject<Block> SLAB_PLANK_SPOOKY = BLOCKS.register("slab_plank_spooky", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.PLANK_SPOOKY.get())));
    public static final RegistryObject<Block> SLAB_STONE_EBON = BLOCKS.register("slab_stone_ebon", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.STONE_EBON.get())));
    public static final RegistryObject<Block> SLAB_STONE_CRIM = BLOCKS.register("slab_stone_crim", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.STONE_CRIM.get())));
    public static final RegistryObject<Block> SLAB_STONE_PEARL = BLOCKS.register("slab_stone_pearl", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.STONE_PEARL.get())));
    public static final RegistryObject<Block> SLAB_STONE_EBON_SMOOTH = BLOCKS.register("slab_stone_ebon_smooth", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.STONE_EBON_SMOOTH.get())));
    public static final RegistryObject<Block> SLAB_STONE_CRIM_SMOOTH = BLOCKS.register("slab_stone_crim_smooth", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.STONE_CRIM_SMOOTH.get())));
    public static final RegistryObject<Block> SLAB_STONE_PEARL_SMOOTH = BLOCKS.register("slab_stone_pearl_smooth", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.STONE_PEARL_SMOOTH.get())));
    public static final RegistryObject<Block> SLAB_STONEBRICK_EBON = BLOCKS.register("slab_stonebrick_ebon", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.STONEBRICK_EBON.get())));
    public static final RegistryObject<Block> SLAB_STONEBRICK_CRIM = BLOCKS.register("slab_stonebrick_crim", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.STONEBRICK_CRIM.get())));
    public static final RegistryObject<Block> SLAB_STONEBRICK_PEARL = BLOCKS.register("slab_stonebrick_pearl", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.STONEBRICK_PEARL.get())));
    public static final RegistryObject<Block> SLAB_STONEBRICK_EBON_MOSSY = BLOCKS.register("slab_stonebrick_ebon_mossy", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.STONEBRICK_EBON_MOSSY.get())));
    public static final RegistryObject<Block> SLAB_STONEBRICK_CRIM_MOSSY = BLOCKS.register("slab_stonebrick_crim_mossy", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.STONEBRICK_CRIM_MOSSY.get())));
    public static final RegistryObject<Block> SLAB_STONEBRICK_PEARL_MOSSY = BLOCKS.register("slab_stonebrick_pearl_mossy", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.STONEBRICK_PEARL_MOSSY.get())));
    public static final RegistryObject<Block> SLAB_COBBLESTONE_EBON = BLOCKS.register("slab_cobblestone_ebon", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.COBBLESTONE_EBON.get())));
    public static final RegistryObject<Block> SLAB_COBBLESTONE_CRIM = BLOCKS.register("slab_cobblestone_crim", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.COBBLESTONE_CRIM.get())));
    public static final RegistryObject<Block> SLAB_COBBLESTONE_PEARL = BLOCKS.register("slab_cobblestone_pearl", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.COBBLESTONE_PEARL.get())));
    public static final RegistryObject<Block> SLAB_COBBLESTONE_EBON_MOSSY = BLOCKS.register("slab_cobblestone_ebon_mossy", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.COBBLESTONE_EBON_MOSSY.get())));
    public static final RegistryObject<Block> SLAB_COBBLESTONE_CRIM_MOSSY = BLOCKS.register("slab_cobblestone_crim_mossy", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.COBBLESTONE_CRIM_MOSSY.get())));
    public static final RegistryObject<Block> SLAB_COBBLESTONE_PEARL_MOSSY = BLOCKS.register("slab_cobblestone_pearl_mossy", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.COBBLESTONE_PEARL_MOSSY.get())));
    public static final RegistryObject<Block> SLAB_SANDSTONE_EBON = BLOCKS.register("slab_sandstone_ebon", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.SANDSTONE_EBON.get())));
    public static final RegistryObject<Block> SLAB_SANDSTONE_CRIM = BLOCKS.register("slab_sandstone_crim", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.SANDSTONE_CRIM.get())));
    public static final RegistryObject<Block> SLAB_SANDSTONE_PEARL = BLOCKS.register("slab_sandstone_pearl", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.SANDSTONE_PEARL.get())));
    public static final RegistryObject<Block> SLAB_SANDSTONE_EBON_CUT = BLOCKS.register("slab_sandstone_ebon_cut", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.SANDSTONE_EBON_CUT.get())));
    public static final RegistryObject<Block> SLAB_SANDSTONE_CRIM_CUT = BLOCKS.register("slab_sandstone_crim_cut", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.SANDSTONE_CRIM_CUT.get())));
    public static final RegistryObject<Block> SLAB_SANDSTONE_PEARL_CUT = BLOCKS.register("slab_sandstone_pearl_cut", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.SANDSTONE_PEARL_CUT.get())));
    public static final RegistryObject<Block> SLAB_SANDSTONE_EBON_SMOOTH = BLOCKS.register("slab_sandstone_ebon_smooth", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.SANDSTONE_EBON_SMOOTH.get())));
    public static final RegistryObject<Block> SLAB_SANDSTONE_CRIM_SMOOTH = BLOCKS.register("slab_sandstone_crim_smooth", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.SANDSTONE_CRIM_SMOOTH.get())));
    public static final RegistryObject<Block> SLAB_SANDSTONE_PEARL_SMOOTH = BLOCKS.register("slab_sandstone_pearl_smooth", () -> new SlabBlock(BlockBehaviour.Properties.copy(TerraBlockRegistry.SANDSTONE_PEARL_SMOOTH.get())));

    public static final RegistryObject<Block> TORCH_GEM_RED = BLOCKS.register("torch_gem_red", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50886_) -> { return 14; }).sound(SoundType.STONE), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_GEM_RED_WALL = BLOCKS.register("torch_gem_red_wall", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE).dropsLike(TerraBlockRegistry.TORCH_GEM_RED.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_GEM_ORANGE = BLOCKS.register("torch_gem_orange", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50886_) -> { return 14; }).sound(SoundType.STONE), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_GEM_ORANGE_WALL = BLOCKS.register("torch_gem_orange_wall", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE).dropsLike(TerraBlockRegistry.TORCH_GEM_ORANGE.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_GEM_YELLOW = BLOCKS.register("torch_gem_yellow", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50886_) -> { return 14; }).sound(SoundType.STONE), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_GEM_YELLOW_WALL = BLOCKS.register("torch_gem_yellow_wall", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE).dropsLike(TerraBlockRegistry.TORCH_GEM_YELLOW.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_GEM_GREEN = BLOCKS.register("torch_gem_green", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50886_) -> { return 14; }).sound(SoundType.STONE), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_GEM_GREEN_WALL = BLOCKS.register("torch_gem_green_wall", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE).dropsLike(TerraBlockRegistry.TORCH_GEM_GREEN.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_GEM_BLUE = BLOCKS.register("torch_gem_blue", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50886_) -> { return 14; }).sound(SoundType.STONE), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_GEM_BLUE_WALL = BLOCKS.register("torch_gem_blue_wall", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE).dropsLike(TerraBlockRegistry.TORCH_GEM_BLUE.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_GEM_PURPLE = BLOCKS.register("torch_gem_purple", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50886_) -> { return 14; }).sound(SoundType.STONE), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_GEM_PURPLE_WALL = BLOCKS.register("torch_gem_purple_wall", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE).dropsLike(TerraBlockRegistry.TORCH_GEM_PURPLE.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_GEM_WHITE = BLOCKS.register("torch_gem_white", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50886_) -> { return 14; }).sound(SoundType.STONE), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_GEM_WHITE_WALL = BLOCKS.register("torch_gem_white_wall", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE).dropsLike(TerraBlockRegistry.TORCH_GEM_WHITE.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_RAINBOW = BLOCKS.register("torch_rainbow", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50886_) -> { return 14; }).sound(SoundType.STONE), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_RAINBOW_WALL = BLOCKS.register("torch_rainbow_wall", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE).dropsLike(TerraBlockRegistry.TORCH_RAINBOW.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_ICE = BLOCKS.register("torch_ice", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50886_) -> { return 14; }).sound(SoundType.GLASS), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_ICE_WALL = BLOCKS.register("torch_ice_wall", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE).dropsLike(TerraBlockRegistry.TORCH_ICE.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_PINK = BLOCKS.register("torch_pink", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50886_) -> { return 14; }).sound(SoundType.WOOD), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_PINK_WALL = BLOCKS.register("torch_pink_wall", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE).dropsLike(TerraBlockRegistry.TORCH_PINK.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_BONE = BLOCKS.register("torch_bone", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50886_) -> { return 14; }).sound(SoundType.BONE_BLOCK), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_BONE_WALL = BLOCKS.register("torch_bone_wall", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE).dropsLike(TerraBlockRegistry.TORCH_BONE.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_BRIGHT = BLOCKS.register("torch_bright", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50886_) -> { return 14; }).sound(SoundType.WOOD), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_BRIGHT_WALL = BLOCKS.register("torch_bright_wall", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE).dropsLike(TerraBlockRegistry.TORCH_BRIGHT.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_DEMON = BLOCKS.register("torch_demon", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50886_) -> { return 14; }).sound(SoundType.WOOD), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_DEMON_WALL = BLOCKS.register("torch_demon_wall", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE).dropsLike(TerraBlockRegistry.TORCH_DEMON.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_DESERT = BLOCKS.register("torch_desert", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50886_) -> { return 14; }).sound(SoundType.STONE), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_DESERT_WALL = BLOCKS.register("torch_desert_wall", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE).dropsLike(TerraBlockRegistry.TORCH_DESERT.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_CORRUPT = BLOCKS.register("torch_corrupt", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50886_) -> { return 14; }).sound(SoundType.WOOD), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_CORRUPT_WALL = BLOCKS.register("torch_corrupt_wall", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE).dropsLike(TerraBlockRegistry.TORCH_CORRUPT.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_CRIMSON = BLOCKS.register("torch_crimson", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50886_) -> { return 14; }).sound(SoundType.WOOD), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_CRIMSON_WALL = BLOCKS.register("torch_crimson_wall", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE).dropsLike(TerraBlockRegistry.TORCH_CRIMSON.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_HALLOWED = BLOCKS.register("torch_hallowed", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50886_) -> { return 14; }).sound(SoundType.WOOD), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_HALLOWED_WALL = BLOCKS.register("torch_hallowed_wall", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE).dropsLike(TerraBlockRegistry.TORCH_HALLOWED.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_JUNGLE = BLOCKS.register("torch_jungle", () -> new TorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50886_) -> { return 14; }).sound(SoundType.WOOD), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_JUNGLE_WALL = BLOCKS.register("torch_jungle_wall", () -> new WallTorchBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE).dropsLike(TerraBlockRegistry.TORCH_JUNGLE.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_CURSED = BLOCKS.register("torch_cursed", () -> new TerraBlockTorch(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_CURSED_WALL = BLOCKS.register("torch_cursed_wall", () -> new TerraBlockTorchWall(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE).dropsLike(TerraBlockRegistry.TORCH_CURSED.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_ICHOR = BLOCKS.register("torch_ichor", () -> new TerraBlockTorch(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_ICHOR_WALL = BLOCKS.register("torch_ichor_wall", () -> new TerraBlockTorchWall(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE).dropsLike(TerraBlockRegistry.TORCH_ICHOR.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_CORAL = BLOCKS.register("torch_coral", () -> new TerraBlockTorch(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.STONE), ParticleTypes.FLAME));
    public static final RegistryObject<Block> TORCH_CORAL_WALL = BLOCKS.register("torch_coral_wall", () -> new TerraBlockTorchWall(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((state) -> { return 14; }).sound(SoundType.CORAL_BLOCK).dropsLike(TerraBlockRegistry.TORCH_CORAL.get()), ParticleTypes.FLAME));

    //Structure Dungeon
    public static final RegistryObject<Block> DUNGEON_BLUE_FLOOR_BRICK = BLOCKS.register("dungeon_blue_floor_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_BLUE_FLOOR_TILE = BLOCKS.register("dungeon_blue_floor_tile", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_BLUE_FLOOR_SLAB = BLOCKS.register("dungeon_blue_floor_slab", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_BLUE_WALL_BASE = BLOCKS.register("dungeon_blue_wall_base", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_BLUE_WALL_TOP = BLOCKS.register("dungeon_blue_wall_top", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_BLUE_WALL_MID = BLOCKS.register("dungeon_blue_wall_mid", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_BLUE_WALL_BOT = BLOCKS.register("dungeon_blue_wall_bot", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_BLUE_PILLAR = BLOCKS.register("dungeon_blue_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_BLUE_BRICK = BLOCKS.register("dungeon_blue_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_BLUE_SLAB = BLOCKS.register("dungeon_blue_slab", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> WALL_DUNGEON_BLUE_BRICKS = BLOCKS.register("wall_dungeon_blue_bricks", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> WALL_DUNGEON_BLUE_BRICK = BLOCKS.register("wall_dungeon_blue_brick", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> WALL_DUNGEON_BLUE_TILE = BLOCKS.register("wall_dungeon_blue_tile", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> WALL_DUNGEON_BLUE_SLAB = BLOCKS.register("wall_dungeon_blue_slab", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> WALL_DUNGEON_BLUE_WALL = BLOCKS.register("wall_dungeon_blue_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> STAIR_DUNGEON_BLUE_BRICKS = BLOCKS.register("stair_dungeon_blue_bricks", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_BLUE_BRICK.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_BLUE_BRICK.get())));
    public static final RegistryObject<Block> STAIR_DUNGEON_BLUE_BRICK = BLOCKS.register("stair_dungeon_blue_brick", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_BLUE_FLOOR_BRICK.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_BLUE_FLOOR_BRICK.get())));
    public static final RegistryObject<Block> STAIR_DUNGEON_BLUE_TILE = BLOCKS.register("stair_dungeon_blue_tile", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_BLUE_FLOOR_TILE.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_BLUE_FLOOR_TILE.get())));
    public static final RegistryObject<Block> STAIR_DUNGEON_BLUE_SLAB = BLOCKS.register("stair_dungeon_blue_slab", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_BLUE_FLOOR_SLAB.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_BLUE_FLOOR_SLAB.get())));
    public static final RegistryObject<Block> STAIR_DUNGEON_BLUE_TRIM_TOP = BLOCKS.register("stair_dungeon_blue_trim_top", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_BLUE_WALL_TOP.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_BLUE_WALL_TOP.get())));
    public static final RegistryObject<Block> STAIR_DUNGEON_BLUE_TRIM_MID = BLOCKS.register("stair_dungeon_blue_trim_mid", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_BLUE_WALL_MID.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_BLUE_WALL_MID.get())));
    public static final RegistryObject<Block> STAIR_DUNGEON_BLUE_TRIM_BOT = BLOCKS.register("stair_dungeon_blue_trim_bot", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_BLUE_WALL_BOT.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_BLUE_WALL_BOT.get())));
    public static final RegistryObject<Block> SLAB_DUNGEON_BLUE = BLOCKS.register("slab_dungeon_blue", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> SLAB_DUNGEON_BLUE_BRICKS = BLOCKS.register("slab_dungeon_blue_bricks", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> SLAB_DUNGEON_BLUE_BRICK = BLOCKS.register("slab_dungeon_blue_brick", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> SLAB_DUNGEON_BLUE_TILE = BLOCKS.register("slab_dungeon_blue_tile", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLUE).strength(9.0F, 9.0F)));

    public static final RegistryObject<Block> DUNGEON_GREEN_FLOOR_BRICK = BLOCKS.register("dungeon_green_floor_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_GREEN_FLOOR_TILE = BLOCKS.register("dungeon_green_floor_tile", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_GREEN_FLOOR_SLAB = BLOCKS.register("dungeon_green_floor_slab", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_GREEN_WALL_BASE = BLOCKS.register("dungeon_green_wall_base", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_GREEN_WALL_TOP = BLOCKS.register("dungeon_green_wall_top", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_GREEN_WALL_MID = BLOCKS.register("dungeon_green_wall_mid", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_GREEN_WALL_BOT = BLOCKS.register("dungeon_green_wall_bot", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_GREEN_PILLAR = BLOCKS.register("dungeon_green_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_GREEN_BRICK = BLOCKS.register("dungeon_green_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_GREEN_SLAB = BLOCKS.register("dungeon_green_slab", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> WALL_DUNGEON_GREEN_BRICKS = BLOCKS.register("wall_dungeon_green_bricks", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> WALL_DUNGEON_GREEN_BRICK = BLOCKS.register("wall_dungeon_green_brick", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> WALL_DUNGEON_GREEN_TILE = BLOCKS.register("wall_dungeon_green_tile", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> WALL_DUNGEON_GREEN_SLAB = BLOCKS.register("wall_dungeon_green_slab", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> WALL_DUNGEON_GREEN_WALL = BLOCKS.register("wall_dungeon_green_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> STAIR_DUNGEON_GREEN_BRICKS = BLOCKS.register("stair_dungeon_green_bricks", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_GREEN_BRICK.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_GREEN_BRICK.get())));
    public static final RegistryObject<Block> STAIR_DUNGEON_GREEN_BRICK = BLOCKS.register("stair_dungeon_green_brick", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_GREEN_FLOOR_BRICK.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_GREEN_FLOOR_BRICK.get())));
    public static final RegistryObject<Block> STAIR_DUNGEON_GREEN_TILE = BLOCKS.register("stair_dungeon_green_tile", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_GREEN_FLOOR_TILE.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_GREEN_FLOOR_TILE.get())));
    public static final RegistryObject<Block> STAIR_DUNGEON_GREEN_SLAB = BLOCKS.register("stair_dungeon_green_slab", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_GREEN_FLOOR_SLAB.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_GREEN_FLOOR_SLAB.get())));
    public static final RegistryObject<Block> STAIR_DUNGEON_GREEN_TRIM_TOP = BLOCKS.register("stair_dungeon_green_trim_top", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_GREEN_WALL_TOP.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_GREEN_WALL_TOP.get())));
    public static final RegistryObject<Block> STAIR_DUNGEON_GREEN_TRIM_MID = BLOCKS.register("stair_dungeon_green_trim_mid", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_GREEN_WALL_MID.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_GREEN_WALL_MID.get())));
    public static final RegistryObject<Block> STAIR_DUNGEON_GREEN_TRIM_BOT = BLOCKS.register("stair_dungeon_green_trim_bot", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_GREEN_WALL_BOT.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_GREEN_WALL_BOT.get())));
    public static final RegistryObject<Block> SLAB_DUNGEON_GREEN = BLOCKS.register("slab_dungeon_green", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> SLAB_DUNGEON_GREEN_BRICKS = BLOCKS.register("slab_dungeon_green_bricks", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> SLAB_DUNGEON_GREEN_BRICK = BLOCKS.register("slab_dungeon_green_brick", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> SLAB_DUNGEON_GREEN_TILE = BLOCKS.register("slab_dungeon_green_tile", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GREEN).strength(9.0F, 9.0F)));

    public static final RegistryObject<Block> DUNGEON_RED_FLOOR_BRICK = BLOCKS.register("dungeon_red_floor_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_RED_FLOOR_TILE = BLOCKS.register("dungeon_red_floor_tile", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_RED_FLOOR_SLAB = BLOCKS.register("dungeon_red_floor_slab", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_RED_WALL_BASE = BLOCKS.register("dungeon_red_wall_base", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_RED_WALL_TOP = BLOCKS.register("dungeon_red_wall_top", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_RED_WALL_MID = BLOCKS.register("dungeon_red_wall_mid", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_RED_WALL_BOT = BLOCKS.register("dungeon_red_wall_bot", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_RED_PILLAR = BLOCKS.register("dungeon_red_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_RED_BRICK = BLOCKS.register("dungeon_red_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> DUNGEON_RED_SLAB = BLOCKS.register("dungeon_red_slab", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F)));
    public static final RegistryObject<Block> WALL_DUNGEON_RED_BRICKS = BLOCKS.register("wall_dungeon_red_bricks", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> WALL_DUNGEON_RED_BRICK = BLOCKS.register("wall_dungeon_red_brick", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> WALL_DUNGEON_RED_TILE = BLOCKS.register("wall_dungeon_red_tile", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> WALL_DUNGEON_RED_SLAB = BLOCKS.register("wall_dungeon_red_slab", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> WALL_DUNGEON_RED_WALL = BLOCKS.register("wall_dungeon_red_wall", () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> STAIR_DUNGEON_RED_BRICKS = BLOCKS.register("stair_dungeon_red_bricks", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_RED_BRICK.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_RED_BRICK.get())));
    public static final RegistryObject<Block> STAIR_DUNGEON_RED_BRICK = BLOCKS.register("stair_dungeon_red_brick", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_RED_FLOOR_BRICK.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_RED_FLOOR_BRICK.get())));
    public static final RegistryObject<Block> STAIR_DUNGEON_RED_TILE = BLOCKS.register("stair_dungeon_red_tile", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_RED_FLOOR_TILE.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_RED_FLOOR_TILE.get())));
    public static final RegistryObject<Block> STAIR_DUNGEON_RED_SLAB = BLOCKS.register("stair_dungeon_red_slab", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_RED_FLOOR_SLAB.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_RED_FLOOR_SLAB.get())));
    public static final RegistryObject<Block> STAIR_DUNGEON_RED_TRIM_TOP = BLOCKS.register("stair_dungeon_red_trim_top", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_RED_WALL_TOP.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_RED_WALL_TOP.get())));
    public static final RegistryObject<Block> STAIR_DUNGEON_RED_TRIM_MID = BLOCKS.register("stair_dungeon_red_trim_mid", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_RED_WALL_MID.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_RED_WALL_MID.get())));
    public static final RegistryObject<Block> STAIR_DUNGEON_RED_TRIM_BOT = BLOCKS.register("stair_dungeon_red_trim_bot", () -> new StairBlock(() -> TerraBlockRegistry.DUNGEON_RED_WALL_BOT.get().defaultBlockState(), BlockBehaviour.Properties.copy(TerraBlockRegistry.DUNGEON_RED_WALL_BOT.get())));
    public static final RegistryObject<Block> SLAB_DUNGEON_RED = BLOCKS.register("slab_dungeon_red", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> SLAB_DUNGEON_RED_BRICKS = BLOCKS.register("slab_dungeon_red_bricks", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> SLAB_DUNGEON_RED_BRICK = BLOCKS.register("slab_dungeon_red_brick", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(9.0F, 9.0F)));
    public static final RegistryObject<Block> SLAB_DUNGEON_RED_TILE = BLOCKS.register("slab_dungeon_red_tile", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(9.0F, 9.0F)));
//
//    //Structure Temple
    public static final RegistryObject<Block> TEMPLE_BRICK = BLOCKS.register("temple_brick", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(25.0F)));
    public static final RegistryObject<Block> TEMPLE_BRICK_MOSSY = BLOCKS.register("temple_brick_mossy", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(25.0F)));
    public static final RegistryObject<Block> TEMPLE_BRICK_CRACKED = BLOCKS.register("temple_brick_cracked", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(25.0F)));

    //Ores
    public static final RegistryObject<Block> ORE_COPPER_PURE = BLOCKS.register("ore_copper", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
    public static final RegistryObject<Block> ORE_COPPER_CORRUPT = BLOCKS.register("ore_copper_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
    public static final RegistryObject<Block> ORE_COPPER_CRIMSON = BLOCKS.register("ore_copper_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
    public static final RegistryObject<Block> ORE_COPPER_HALLOWED = BLOCKS.register("ore_copper_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
    public static final RegistryObject<Block> ORE_COPPER_JUNGLE = BLOCKS.register("ore_copper_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_TIN_PURE = BLOCKS.register("ore_tin", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_TIN_CORRUPT = BLOCKS.register("ore_tin_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_TIN_CRIMSON = BLOCKS.register("ore_tin_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_TIN_HALLOWED = BLOCKS.register("ore_tin_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_TIN_JUNGLE = BLOCKS.register("ore_tin_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_LEAD_PURE = BLOCKS.register("ore_lead", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_LEAD_CORRUPT = BLOCKS.register("ore_lead_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_LEAD_CRIMSON = BLOCKS.register("ore_lead_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_LEAD_HALLOWED = BLOCKS.register("ore_lead_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_LEAD_JUNGLE = BLOCKS.register("ore_lead_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_IRON_CORRUPT = BLOCKS.register("ore_iron_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_IRON_CRIMSON = BLOCKS.register("ore_iron_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_IRON_HALLOWED = BLOCKS.register("ore_iron_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_IRON_JUNGLE = BLOCKS.register("ore_iron_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_SILVER_PURE = BLOCKS.register("ore_silver", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F)));
//    public static final RegistryObject<Block> ORE_SILVER_CORRUPT = BLOCKS.register("ore_silver_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F)));
//    public static final RegistryObject<Block> ORE_SILVER_CRIMSON = BLOCKS.register("ore_silver_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F)));
//    public static final RegistryObject<Block> ORE_SILVER_HALLOWED = BLOCKS.register("ore_silver_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F)));
//    public static final RegistryObject<Block> ORE_SILVER_JUNGLE = BLOCKS.register("ore_silver_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(3.5F)));
//    public static final RegistryObject<Block> ORE_TUNGSTEN_PURE = BLOCKS.register("ore_tungsten", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F)));
//    public static final RegistryObject<Block> ORE_TUNGSTEN_CORRUPT = BLOCKS.register("ore_tungsten_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F)));
//    public static final RegistryObject<Block> ORE_TUNGSTEN_CRIMSON = BLOCKS.register("ore_tungsten_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F)));
//    public static final RegistryObject<Block> ORE_TUNGSTEN_HALLOWED = BLOCKS.register("ore_tungsten_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F)));
//    public static final RegistryObject<Block> ORE_TUNGSTEN_JUNGLE = BLOCKS.register("ore_tungsten_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(3.5F)));
//    public static final RegistryObject<Block> ORE_GOLD_CORRUPT = BLOCKS.register("ore_gold_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F)));
//    public static final RegistryObject<Block> ORE_GOLD_CRIMSON = BLOCKS.register("ore_gold_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F)));
//    public static final RegistryObject<Block> ORE_GOLD_HALLOWED = BLOCKS.register("ore_gold_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F)));
//    public static final RegistryObject<Block> ORE_GOLD_JUNGLE = BLOCKS.register("ore_gold_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(3.5F)));
//    public static final RegistryObject<Block> ORE_PLATINUM_PURE = BLOCKS.register("ore_platinum", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F)));
//    public static final RegistryObject<Block> ORE_PLATINUM_CORRUPT = BLOCKS.register("ore_platinum_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F)));
//    public static final RegistryObject<Block> ORE_PLATINUM_CRIMSON = BLOCKS.register("ore_platinum_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F)));
//    public static final RegistryObject<Block> ORE_PLATINUM_HALLOWED = BLOCKS.register("ore_platinum_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F)));
//    public static final RegistryObject<Block> ORE_PLATINUM_JUNGLE = BLOCKS.register("ore_platinum_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(3.5F)));
//    public static final RegistryObject<Block> ORE_METEORITE = BLOCKS.register("ore_meteorite", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F)));
//    public static final RegistryObject<Block> ORE_DEMONITE_PURE = BLOCKS.register("ore_demonite", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(10.0F)));
//    public static final RegistryObject<Block> ORE_DEMONITE_CORRUPT = BLOCKS.register("ore_demonite_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(10.0F)));
//    public static final RegistryObject<Block> ORE_DEMONITE_CRIMSON = BLOCKS.register("ore_demonite_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(10.0F)));
//    public static final RegistryObject<Block> ORE_DEMONITE_HALLOWED = BLOCKS.register("ore_demonite_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(10.0F)));
//    public static final RegistryObject<Block> ORE_DEMONITE_JUNGLE = BLOCKS.register("ore_demonite_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(10.0F)));
//    public static final RegistryObject<Block> ORE_CRIMTANE_PURE = BLOCKS.register("ore_crimtane", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(10.0F)));
//    public static final RegistryObject<Block> ORE_CRIMTANE_CORRUPT = BLOCKS.register("ore_crimtane_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(10.0F)));
//    public static final RegistryObject<Block> ORE_CRIMTANE_CRIMSON = BLOCKS.register("ore_crimtane_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(10.0F)));
//    public static final RegistryObject<Block> ORE_CRIMTANE_HALLOWED = BLOCKS.register("ore_crimtane_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(10.0F)));
//    public static final RegistryObject<Block> ORE_CRIMTANE_JUNGLE = BLOCKS.register("ore_crimtane_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(10.0F)));
//    public static final RegistryObject<Block> ORE_HELLSTONE = BLOCKS.register("ore_hellstone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_COBALT_PURE = BLOCKS.register("ore_cobalt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_COBALT_CORRUPT = BLOCKS.register("ore_cobalt_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_COBALT_CRIMSON = BLOCKS.register("ore_cobalt_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_COBALT_HALLOWED = BLOCKS.register("ore_cobalt_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_COBALT_JUNGLE = BLOCKS.register("ore_cobalt_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_PALLADIUM_PURE = BLOCKS.register("ore_palladium", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_PALLADIUM_CORRUPT = BLOCKS.register("ore_palladium_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_PALLADIUM_CRIMSON = BLOCKS.register("ore_palladium_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_PALLADIUM_HALLOWED = BLOCKS.register("ore_palladium_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_PALLADIUM_JUNGLE = BLOCKS.register("ore_palladium_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_MYTHRIL_PURE = BLOCKS.register("ore_mythril", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_MYTHRIL_CORRUPT = BLOCKS.register("ore_mythril_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_MYTHRIL_CRIMSON = BLOCKS.register("ore_mythril_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_MYTHRIL_HALLOWED = BLOCKS.register("ore_mythril_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_MYTHRIL_JUNGLE = BLOCKS.register("ore_mythril_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_ORICHALCUM_PURE = BLOCKS.register("ore_orichalcum", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_ORICHALCUM_CORRUPT = BLOCKS.register("ore_orichalcum_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_ORICHALCUM_CRIMSON = BLOCKS.register("ore_orichalcum_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_ORICHALCUM_HALLOWED = BLOCKS.register("ore_orichalcum_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_ORICHALCUM_JUNGLE = BLOCKS.register("ore_orichalcum_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_ADAMANTITE_PURE = BLOCKS.register("ore_adamantite", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_ADAMANTITE_CORRUPT = BLOCKS.register("ore_adamantite_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_ADAMANTITE_CRIMSON = BLOCKS.register("ore_adamantite_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_ADAMANTITE_HALLOWED = BLOCKS.register("ore_adamantite_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_ADAMANTITE_JUNGLE = BLOCKS.register("ore_adamantite_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_TITANIUM_PURE = BLOCKS.register("ore_titanium", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_TITANIUM_CORRUPT = BLOCKS.register("ore_titanium_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_TITANIUM_CRIMSON = BLOCKS.register("ore_titanium_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_TITANIUM_HALLOWED = BLOCKS.register("ore_titanium_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_TITANIUM_JUNGLE = BLOCKS.register("ore_titanium_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(20.0F)));
//    public static final RegistryObject<Block> ORE_CHLOROPHYTE = BLOCKS.register("ore_chlorophyte", () -> new TerraBlockChlorophyteOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(30.0F).randomTicks()));
//    public static final RegistryObject<Block> ORE_COAL_CORRUPT = BLOCKS.register("ore_coal_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_COAL_CRIMSON = BLOCKS.register("ore_coal_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_COAL_HALLOWED = BLOCKS.register("ore_coal_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_COAL_JUNGLE = BLOCKS.register("ore_coal_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_LAPIS_CORRUPT = BLOCKS.register("ore_lapis_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_LAPIS_CRIMSON = BLOCKS.register("ore_lapis_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_LAPIS_HALLOWED = BLOCKS.register("ore_lapis_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_LAPIS_JUNGLE = BLOCKS.register("ore_lapis_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_REDSTONE_CORRUPT = BLOCKS.register("ore_redstone_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_REDSTONE_CRIMSON = BLOCKS.register("ore_redstone_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_REDSTONE_HALLOWED = BLOCKS.register("ore_redstone_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_REDSTONE_JUNGLE = BLOCKS.register("ore_redstone_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_AMBER_PURE = BLOCKS.register("ore_amber", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_AMBER_CORRUPT = BLOCKS.register("ore_amber_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_AMBER_CRIMSON = BLOCKS.register("ore_amber_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_AMBER_HALLOWED = BLOCKS.register("ore_amber_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_AMBER_JUNGLE = BLOCKS.register("ore_amber_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_AMETHYST_PURE = BLOCKS.register("ore_amethyst", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_AMETHYST_CORRUPT = BLOCKS.register("ore_amethyst_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_AMETHYST_CRIMSON = BLOCKS.register("ore_amethyst_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_AMETHYST_HALLOWED = BLOCKS.register("ore_amethyst_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_AMETHYST_JUNGLE = BLOCKS.register("ore_amethyst_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_TOPAZ_PURE = BLOCKS.register("ore_topaz", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_TOPAZ_CORRUPT = BLOCKS.register("ore_topaz_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_TOPAZ_CRIMSON = BLOCKS.register("ore_topaz_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_TOPAZ_HALLOWED = BLOCKS.register("ore_topaz_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_TOPAZ_JUNGLE = BLOCKS.register("ore_topaz_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_SAPPHIRE_PURE = BLOCKS.register("ore_sapphire", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_SAPPHIRE_CORRUPT = BLOCKS.register("ore_sapphire_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_SAPPHIRE_CRIMSON = BLOCKS.register("ore_sapphire_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_SAPPHIRE_HALLOWED = BLOCKS.register("ore_sapphire_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_SAPPHIRE_JUNGLE = BLOCKS.register("ore_sapphire_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_RUBY_PURE = BLOCKS.register("ore_ruby", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_RUBY_CORRUPT = BLOCKS.register("ore_ruby_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_RUBY_CRIMSON = BLOCKS.register("ore_ruby_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_RUBY_HALLOWED = BLOCKS.register("ore_ruby_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_RUBY_JUNGLE = BLOCKS.register("ore_ruby_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_EMERALD_CORRUPT = BLOCKS.register("ore_emerald_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_EMERALD_CRIMSON = BLOCKS.register("ore_emerald_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_EMERALD_HALLOWED = BLOCKS.register("ore_emerald_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_EMERALD_JUNGLE = BLOCKS.register("ore_emerald_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_DIAMOND_CORRUPT = BLOCKS.register("ore_diamond_corrupt", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_DIAMOND_CRIMSON = BLOCKS.register("ore_diamond_crimson", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_DIAMOND_HALLOWED = BLOCKS.register("ore_diamond_hallowed", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
//    public static final RegistryObject<Block> ORE_DIAMOND_JUNGLE = BLOCKS.register("ore_diamond_jungle", () -> new TerraBlockOre(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GRAVEL).strength(3.0F)));

    //Misc
    //    public static final RegistryObject<Block> MISC_CLOUD = null;
    //    public static final RegistryObject<Block> MISC_RAINCLOUD = null;
    //    public static final RegistryObject<Block> MISC_HIVE = null;
    //    public static final RegistryObject<Block> MISC_HONEY = null;
    //    public static final RegistryObject<Block> MISC_CRISPYHONEY = null;
    //    public static final RegistryObject<Block> SLIME_ALT = null;
    //    public static final RegistryObject<Block> SLIME_FROZEN = null;
    //    public static final RegistryObject<Block> SLIME_BOUNCY = null;
    //    public static final RegistryObject<Block> MISC_ASPHALT = null;
    //    public static final RegistryObject<Block> MISC_SHINGLE_BLUE = null;
    //    public static final RegistryObject<Block> MISC_SHINGLE_RED = null;
    //    public static final RegistryObject<Block> MISC_COG = null;
    public static final RegistryObject<Block> MISC_CANDY_RED = BLOCKS.register("misc_candy_red", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
    public static final RegistryObject<Block> MISC_CANDY_GREEN = BLOCKS.register("misc_candy_green", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3.0F)));
    //    public static final RegistryObject<Block> MISC_BONE = null;
    //    public static final RegistryObject<Block> MISC_ROPE = null;
    //    public static final RegistryObject<Block> MISC_ROPE_VINE = null;
    //    public static final RegistryObject<Block> MISC_ROPE_WEB = null;
    //    public static final RegistryObject<Block> MISC_ROPE_SILK = null;
    //    public static final RegistryObject<Block> MISC_CHAIN = null;

    //Traps
    //    public static final RegistryObject<Block> TRAP_DART = null;
    //    public static final RegistryObject<Block> TRAP_SUPERDART = null;
    //    public static final RegistryObject<Block> TRAP_BALL = null;
    //    public static final RegistryObject<Block> TRAP_SPEAR = null;
    //    public static final RegistryObject<Block> TRAP_FLAME = null;
    //    public static final RegistryObject<Block> TRAP_SPIKE = null;
    //    public static final RegistryObject<Block> TRAP_SUPERSPIKE = null;
    //    public static final RegistryObject<Block> TRAP_GEYSER = null;
}

/*TODO

1: All blockstates moved to

{
  "multipart":
	[
    { "when": { "up": "true" }, "apply": { "model": "terra_reforged:block/wall_<name_here>_post" }},
		{ "when": { "north": "low" }, "apply": { "model": "terra_reforged:block/wall_<name_here>_side", "uvlock": true }},
    { "when": { "east": "low" }, "apply": { "model": "terra_reforged:block/wall_<name_here>_side", "y": 90, "uvlock": true }},
    { "when": { "south": "low" }, "apply": { "model": "terra_reforged:block/wall_<name_here>_side", "y": 180, "uvlock": true }},
    { "when": { "west": "low" }, "apply": { "model": "terra_reforged:block/wall_<name_here>_side", "y": 270, "uvlock": true }},
    { "when": { "north": "tall" }, "apply": { "model": "terra_reforged:block/wall_<name_here>_side_up", "uvlock": true }},
    { "when": { "east": "tall" }, "apply": { "model": "terra_reforged:block/wall_<name_here>_side_up", "y": 90, "uvlock": true }},
    { "when": { "south": "tall" }, "apply": { "model": "terra_reforged:block/wall_<name_here>_side_up", "y": 180, "uvlock": true }},
    { "when": { "west": "tall" }, "apply": { "model": "terra_reforged:block/wall_<name_here>_side_up", "y": 270, "uvlock": true }}
  ]
}

2: All wall posts change parent to

minecraft:block/template_wall_post

3: Delete all wall pillar model files

4: All wall sides change parent to

minecraft:block/template_wall_side

5: All wall side ups change parent to

minecraft:block/template_wall_side_tall

6: All wall inventories change parent to

minecraft:block/wall_inventory








 */