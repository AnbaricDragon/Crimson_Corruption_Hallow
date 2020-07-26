package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.util.TerraItemGroups;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class TerraTagRegistry
{
    public static final Tag<Block> MOSS = new BlockTags.Wrapper(new ResourceLocation("moss"));

    public static final Tag<Item> INGOT_COPPER = new ItemTags.Wrapper(new ResourceLocation("forge:ingots/copper"));
    public static final Tag<Item> INGOT_TIN = new ItemTags.Wrapper(new ResourceLocation("forge:ingots/tin"));
    public static final Tag<Item> INGOT_LEAD = new ItemTags.Wrapper(new ResourceLocation("forge:ingots/lead"));
    public static final Tag<Item> INGOT_TUNGSTEN = new ItemTags.Wrapper(new ResourceLocation("forge:ingots/tungsten"));
    public static final Tag<Item> INGOT_SILVER = new ItemTags.Wrapper(new ResourceLocation("forge:ingots/silver"));
    public static final Tag<Item> INGOT_PLATINUM = new ItemTags.Wrapper(new ResourceLocation("forge:ingots/platinum"));
    public static final Tag<Item> INGOT_MYTHRIL = new ItemTags.Wrapper(new ResourceLocation("forge:ingots/mythril"));
    public static final Tag<Item> INGOT_TITANIUM = new ItemTags.Wrapper(new ResourceLocation("forge:ingots/titanium"));
}
