package com.anbaric.terra_reforged.util.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public class TerraTagRegistry
{
    public static final ITag.INamedTag<Block> MOSS = BlockTags.makeWrapperTag("terra_reforged:moss");

    public static final ITag.INamedTag<Item> INGOT_COPPER = ItemTags.makeWrapperTag("forge:ingots/copper");
    public static final ITag.INamedTag<Item> INGOT_TIN = ItemTags.makeWrapperTag("forge:ingots/tin");
    public static final ITag.INamedTag<Item> INGOT_LEAD = ItemTags.makeWrapperTag("forge:ingots/lead");
    public static final ITag.INamedTag<Item> INGOT_TUNGSTEN = ItemTags.makeWrapperTag("forge:ingots/tungsten");
    public static final ITag.INamedTag<Item> INGOT_SILVER = ItemTags.makeWrapperTag("forge:ingots/silver");
    public static final ITag.INamedTag<Item> INGOT_PLATINUM = ItemTags.makeWrapperTag("forge:ingots/platinum");
    public static final ITag.INamedTag<Item> INGOT_MYTHRIL = ItemTags.makeWrapperTag("forge:ingots/mythril");
    public static final ITag.INamedTag<Item> INGOT_TITANIUM = ItemTags.makeWrapperTag("forge:ingots/titanium");

    public static final ITag.INamedTag<Item> CLOUD_JUMPERS = ItemTags.makeWrapperTag("terra_reforged:cloud_jumpers");
    public static final ITag.INamedTag<Item> BEE_SPAWNERS = ItemTags.makeWrapperTag("terra_reforged:bee_spawners");
}
