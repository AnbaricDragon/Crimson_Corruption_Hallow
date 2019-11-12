package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.blocks.*;
import com.anbaric.terra_reforged.structures.trees.*;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.TerraItemGroups;
import com.anbaric.terra_reforged.util.handlers.EnumHandler.EnumBiomeType;
import com.google.common.base.Preconditions;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@EventBusSubscriber(modid = Reference.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class TerraBlockRegistry
{
    private static final Logger LOGGER = LogManager.getLogger(Reference.MODID + " Mod Event Subscriber");

    @SubscribeEvent
    public static void onRegisterBlocks(final RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll
        (
            //Soils
                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
                setup(new TerraBlockBiomeGrass(Block.Properties.create(Material.ORGANIC).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.WET_GRASS).tickRandomly(), EnumBiomeType.CORRUPT), "grass_corrupt"),
                setup(new TerraBlockBiomeGrass(Block.Properties.create(Material.ORGANIC).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.WET_GRASS).tickRandomly(), EnumBiomeType.CRIMSON), "grass_crimson"),
                setup(new TerraBlockBiomeGrass(Block.Properties.create(Material.ORGANIC).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.PLANT).tickRandomly(), EnumBiomeType.HALLOWED), "grass_hallowed"),
                setup(new TerraBlockMudGrass(Block.Properties.create(Material.ORGANIC).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.PLANT).tickRandomly()), "grass_jungle"),
                setup(new TerraBlockMudGrass(Block.Properties.create(Material.ORGANIC).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.PLANT).tickRandomly()), "grass_mushroom"),
                setup(new TerraBlockSand(Block.Properties.create(Material.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND).tickRandomly(), EnumBiomeType.CORRUPT), "sand_ebon"),
                setup(new TerraBlockSand(Block.Properties.create(Material.SAND).hardnessAndResistance(1.0F).sound(SoundType.SAND).tickRandomly(), EnumBiomeType.CORRUPT), "sand_hardebon"),
                setup(new TerraBlockSand(Block.Properties.create(Material.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND).tickRandomly(), EnumBiomeType.CRIMSON), "sand_crim"),
                setup(new TerraBlockSand(Block.Properties.create(Material.SAND).hardnessAndResistance(1.0F).sound(SoundType.SAND).tickRandomly(), EnumBiomeType.CRIMSON), "sand_hardcrim"),
                setup(new TerraBlockSand(Block.Properties.create(Material.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND).tickRandomly(), EnumBiomeType.HALLOWED), "sand_pearl"),
                setup(new TerraBlockSand(Block.Properties.create(Material.SAND).hardnessAndResistance(1.0F).sound(SoundType.SAND).tickRandomly(), EnumBiomeType.HALLOWED), "sand_hardpearl"),
                setup(new SandBlock(25, Block.Properties.create(Material.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND)), "sand_silt"),
                setup(new SandBlock(25, Block.Properties.create(Material.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND)), "sand_slush"),
                setup(new SandBlock(25, Block.Properties.create(Material.SAND).hardnessAndResistance(1.0F).sound(SoundType.SAND)), "sand_hard"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.SNOW_BLOCK).hardnessAndResistance(0.2F).sound(SoundType.SNOW).tickRandomly(), EnumBiomeType.CORRUPT, TerraReforged.BOREAL), "snow_corrupt"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.SNOW_BLOCK).hardnessAndResistance(0.2F).sound(SoundType.SNOW).tickRandomly(), EnumBiomeType.CRIMSON, TerraReforged.BOREAL), "snow_crimson"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.SNOW_BLOCK).hardnessAndResistance(0.2F).sound(SoundType.SNOW).tickRandomly(), EnumBiomeType.HALLOWED, TerraReforged.BOREAL), "snow_hallowed"),
                setup(new TerraBlockSnowLayer(Block.Properties.create(Material.SNOW).tickRandomly().hardnessAndResistance(0.1F).sound(SoundType.SNOW)), "snow_corrupt_layer"),
                setup(new TerraBlockSnowLayer(Block.Properties.create(Material.SNOW).tickRandomly().hardnessAndResistance(0.1F).sound(SoundType.SNOW)), "snow_crimson_layer"),
                setup(new TerraBlockSnowLayer(Block.Properties.create(Material.SNOW).tickRandomly().hardnessAndResistance(0.1F).sound(SoundType.SNOW)), "snow_hallowed_layer"),
            //Stones
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave), "stone_ebon"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave), "cobblestone_ebon"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave), "cobblestone_ebon_mossy"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave), "stonebrick_ebon"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave), "stonebrick_ebon_cracked"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave), "stonebrick_ebon_chiseled"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave), "stonebrick_ebon_mossy"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CRIMSON, PlantType.Cave), "stone_crim"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CRIMSON, PlantType.Cave), "cobblestone_crim"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CRIMSON, PlantType.Cave), "cobblestone_crim_mossy"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CRIMSON, PlantType.Cave), "stonebrick_crim"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CRIMSON, PlantType.Cave), "stonebrick_crim_cracked"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CRIMSON, PlantType.Cave), "stonebrick_crim_chiseled"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CRIMSON, PlantType.Cave), "stonebrick_crim_mossy"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.HALLOWED, PlantType.Cave), "stone_pearl"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.HALLOWED, PlantType.Cave), "cobblestone_pearl"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.HALLOWED, PlantType.Cave), "cobblestone_pearl_mossy"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.HALLOWED, PlantType.Cave), "stonebrick_pearl"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.HALLOWED, PlantType.Cave), "stonebrick_pearl_cracked"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.HALLOWED, PlantType.Cave), "stonebrick_pearl_chiseled"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.HALLOWED, PlantType.Cave), "stonebrick_pearl_mossy"),
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)), "stone_granite"),
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)), "stone_marble"),
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE)), "stone_fossil"),

            //Ice
                setup(new TerraBlockIce(Block.Properties.create(Material.ICE).slipperiness(0.98F).tickRandomly().hardnessAndResistance(0.5F).sound(SoundType.GLASS), EnumBiomeType.CORRUPT), "ice_purple"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ICE).slipperiness(0.98F).tickRandomly().hardnessAndResistance(0.5F).sound(SoundType.GLASS), EnumBiomeType.CORRUPT, TerraReforged.BOREAL), "ice_hard_purple"),
                setup(new TerraBlockIce(Block.Properties.create(Material.ICE).slipperiness(0.98F).tickRandomly().hardnessAndResistance(0.5F).sound(SoundType.GLASS), EnumBiomeType.CRIMSON), "ice_red"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ICE).slipperiness(0.98F).tickRandomly().hardnessAndResistance(0.5F).sound(SoundType.GLASS), EnumBiomeType.CRIMSON, TerraReforged.BOREAL), "ice_hard_red"),
                setup(new TerraBlockIce(Block.Properties.create(Material.ICE).slipperiness(0.98F).tickRandomly().hardnessAndResistance(0.5F).sound(SoundType.GLASS), EnumBiomeType.HALLOWED), "ice_pink"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ICE).slipperiness(0.98F).tickRandomly().hardnessAndResistance(0.5F).sound(SoundType.GLASS), EnumBiomeType.HALLOWED, TerraReforged.BOREAL), "ice_hard_pink"),
                setup(new TerraBlockThinIce(Block.Properties.create(Material.ICE).slipperiness(0.98F).tickRandomly().hardnessAndResistance(0.25F).sound(SoundType.GLASS)), "ice_thin"),

            //Sandstones
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave), "sandstone_ebon"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave), "sandstone_ebon_chiseled"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave), "sandstone_ebon_smooth"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CORRUPT, PlantType.Cave), "sandstone_ebon_cut"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CRIMSON, PlantType.Cave), "sandstone_crim"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CRIMSON, PlantType.Cave), "sandstone_crim_chiseled"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CRIMSON, PlantType.Cave), "sandstone_crim_smooth"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.CRIMSON, PlantType.Cave), "sandstone_crim_cut"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.HALLOWED, PlantType.Cave), "sandstone_pearl"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.HALLOWED, PlantType.Cave), "sandstone_pearl_chiseled"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.HALLOWED, PlantType.Cave), "sandstone_pearl_smooth"),
                setup(new TerraBlockSpreading(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).harvestLevel(3).sound(SoundType.STONE).tickRandomly(), EnumBiomeType.HALLOWED, PlantType.Cave), "sandstone_pearl_cut"),

            //Moss Blocks
                setup(new TerraBlockMoss(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.6F).sound(SoundType.PLANT).tickRandomly(), Blocks.FERN), "stone_moss_red"),
                setup(new TerraBlockMoss(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.6F).sound(SoundType.PLANT).tickRandomly(), Blocks.FERN), "stone_moss_fire"),
                setup(new TerraBlockMoss(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.6F).sound(SoundType.PLANT).tickRandomly(), Blocks.FERN), "stone_moss_yellow"),
                setup(new TerraBlockMoss(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.6F).sound(SoundType.PLANT).tickRandomly(), Blocks.FERN), "stone_moss_green"),
                setup(new TerraBlockMoss(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.6F).sound(SoundType.PLANT).tickRandomly(), Blocks.FERN), "stone_moss_blue"),
                setup(new TerraBlockMoss(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.6F).sound(SoundType.PLANT).tickRandomly(), Blocks.FERN), "stone_moss_purple"),

            //Woods
                setup(new LogBlock(MaterialColor.PURPLE_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_ebon"),
                setup(new LogBlock(MaterialColor.PURPLE_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_ebon_stripped"),
                setup(new LogBlock(MaterialColor.PURPLE_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_ebon_solid"),
                setup(new LogBlock(MaterialColor.PURPLE_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_ebon_solid_stripped"),
                setup(new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "plank_ebon"),
                setup(new LogBlock(MaterialColor.RED_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_shade"),
                setup(new LogBlock(MaterialColor.RED_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_shade_stripped"),
                setup(new LogBlock(MaterialColor.RED_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_shade_solid"),
                setup(new LogBlock(MaterialColor.RED_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_shade_solid_stripped"),
                setup(new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "plank_shade"),
                setup(new LogBlock(MaterialColor.PINK_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_pearl"),
                setup(new LogBlock(MaterialColor.PINK_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_pearl_stripped"),
                setup(new LogBlock(MaterialColor.PINK_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_pearl_solid"),
                setup(new LogBlock(MaterialColor.PINK_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_pearl_solid_stripped"),
                setup(new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "plank_pearl"),
                setup(new LogBlock(MaterialColor.BROWN_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_boreal"),
                setup(new LogBlock(MaterialColor.BROWN_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_boreal_stripped"),
                setup(new LogBlock(MaterialColor.BROWN_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_boreal_solid"),
                setup(new LogBlock(MaterialColor.BROWN_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_boreal_solid_stripped"),
                setup(new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "plank_boreal"),
                setup(new LogBlock(MaterialColor.YELLOW_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_palm"),
                setup(new LogBlock(MaterialColor.YELLOW_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_palm_stripped"),
                setup(new LogBlock(MaterialColor.YELLOW_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_palm_solid"),
                setup(new LogBlock(MaterialColor.YELLOW_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_palm_solid_stripped"),
                setup(new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "plank_palm"),
                setup(new LogBlock(MaterialColor.NETHERRACK, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_mahogany"),
                setup(new LogBlock(MaterialColor.NETHERRACK, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_mahogany_stripped"),
                setup(new LogBlock(MaterialColor.NETHERRACK, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_mahogany_solid"),
                setup(new LogBlock(MaterialColor.NETHERRACK, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_mahogany_solid_stripped"),
                setup(new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "plank_mahogany"),
                setup(new LogBlock(MaterialColor.CYAN_TERRACOTTA, Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "log_mushroom"),
                setup(new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "plank_dynasty"),
                setup(new Block(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "plank_spooky"),

            //Foliage
                setup(new TerraBlockSapling(new TreeBoreal(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.PLANT)), "sapling_boreal"),
                setup(new TerraBlockSapling(new TreePalm(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.PLANT)), "sapling_palm"),
                setup(new TerraBlockSapling(new TreeMahogany(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.PLANT)), "sapling_mahogany"),
                setup(new TerraBlockSapling(new TreeEbon(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.PLANT)), "sapling_ebon"),
                setup(new TerraBlockSapling(new TreeShade(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.PLANT)), "sapling_shade"),
                setup(new TerraBlockSapling(new TreePearl(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0F).sound(SoundType.PLANT)), "sapling_pearl"),
                setup(new HugeMushroomBlock(Block.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(0.2F).sound(SoundType.WOOD)), "mushroom_cap"),
                setup(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "leaf_ebon"),
                setup(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "leaf_shade"),
                setup(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "leaf_mahogany"),
                setup(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "leaf_boreal"),
                setup(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "leaf_palm"),
                setup(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "leaf_pearl_red"),
                setup(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "leaf_pearl_yellow"),
                setup(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "leaf_pearl_pink"),
                setup(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "leaf_pearl_magenta"),
                setup(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "leaf_pearl_cyan"),
                setup(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "leaf_pearl_blue"),
                setup(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "leaf_pearl_green"),
                setup(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "leaf_pearl_purple"),
                setup(new TerraBlockCactus(Block.Properties.create(Material.CACTUS).tickRandomly().hardnessAndResistance(0.4F).sound(SoundType.CLOTH)), "cactus_ebon"),
                setup(new TerraBlockCactus(Block.Properties.create(Material.CACTUS).tickRandomly().hardnessAndResistance(0.4F).sound(SoundType.CLOTH)), "cactus_crim"),
                setup(new TerraBlockCactus(Block.Properties.create(Material.CACTUS).tickRandomly().hardnessAndResistance(0.4F).sound(SoundType.CLOTH)), "cactus_pearl")
        );
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event)
    {
        final IForgeRegistry<Item> registry = event.getRegistry();

        // We need to go over the entire registry so that we include any potential Registry Overrides
        for (final Block block : ForgeRegistries.BLOCKS.getValues())
        {
            final ResourceLocation blockRegistryName = block.getRegistryName();
            // An extra safe-guard against badly registered blocks
            Preconditions.checkNotNull(blockRegistryName, "Registry Name of Block \"" + block + "\" of class \"" + block.getClass().getName() + "\"is null! This is not allowed!");

            // Check that the blocks is from our mod, if not, continue to the next block
            if (!blockRegistryName.getNamespace().equals(Reference.MODID))
            {
                continue;
            }
            // Make the properties, and make it so that the item will be on our ItemGroup (CreativeTab)
            final Item.Properties TerraBlocksTab = new Item.Properties().group(TerraItemGroups.TERRA_BLOCKS_TAB);
            final Item.Properties TerraDecorationsTab = new Item.Properties().group(TerraItemGroups.TERRA_DECORATIONS_TAB);
            // Create the new BlockItem with the block and it's properties
            final BlockItem blockItem = new BlockItem(block,
                    block instanceof TerraBlockSnowLayer ||
                    block instanceof TerraBlockSapling ||
                    block instanceof HugeMushroomBlock ||
                    block instanceof LeavesBlock ||
                    block instanceof TerraBlockCactus
                        ? TerraDecorationsTab : TerraBlocksTab);
            // Setup the new BlockItem with the block's registry name and register it
            registry.register(setup(blockItem, blockRegistryName));
        }
        LOGGER.debug("Registered Items");
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name)
    {
        return setup(entry, new ResourceLocation(Reference.MODID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName)
    {
        entry.setRegistryName(registryName);
        return entry;
    }
}
