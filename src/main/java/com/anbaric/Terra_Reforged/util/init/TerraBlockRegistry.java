package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.blocks.TerraBlockBiomeGrass;
import com.anbaric.terra_reforged.blocks.TerraBlockMudGrass;
import com.anbaric.terra_reforged.blocks.TerraBlockSand;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.TerraItemGroups;
import com.anbaric.terra_reforged.util.handlers.EnumHandler.EnumBiomeType;
import com.google.common.base.Preconditions;
import net.minecraft.block.Block;
import net.minecraft.block.SandBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
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
                setup(new TerraBlockSand(Block.Properties.create(Material.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND).tickRandomly(), EnumBiomeType.CRIMSON), "sand_crim"),
                setup(new TerraBlockSand(Block.Properties.create(Material.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND).tickRandomly(), EnumBiomeType.HALLOWED), "sand_pearl"),
                setup(new SandBlock(25, Block.Properties.create(Material.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND)), "sand_silt"),
                setup(new SandBlock(25, Block.Properties.create(Material.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND)), "sand_slush"),
                setup(new SandBlock(25, Block.Properties.create(Material.SAND).hardnessAndResistance(1.0F).sound(SoundType.SAND)), "sand_hard")
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_mud"),
//                setup(new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F, 3.0F).sound(SoundType.GROUND)), "soil_ash")

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
            // Create the new BlockItem with the block and it's properties
            final BlockItem blockItem = new BlockItem(block, TerraBlocksTab);
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
