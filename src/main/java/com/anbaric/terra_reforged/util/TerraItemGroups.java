package com.anbaric.terra_reforged.util;

import com.anbaric.terra_reforged.blocks.TerraBlocks;
import com.anbaric.terra_reforged.items.TerraItems;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public final class TerraItemGroups
{
    public static final ItemGroup TERRA_BLOCKS_TAB = new TerraItemGroup("terra_blocks_tab", () -> new ItemStack(TerraBlocks.SOIL_MUD));
    public static final ItemGroup TERRA_DECORATIONS_TAB = new TerraItemGroup("terra_decorations_tab", () -> new ItemStack(Blocks.ACACIA_LEAVES));
    public static final ItemGroup TERRA_MATERIALS_TAB = new TerraItemGroup("terra_materials_tab", () -> new ItemStack(TerraItems.INGOT_CHLOROPHYTE));
    public static final ItemGroup TERRA_MECHANICS_TAB = new TerraItemGroup("terra_mechanics_tab", () -> new ItemStack(Items.REDSTONE));
    public static final ItemGroup TERRA_MONSTERS_TAB = new TerraItemGroup("terra_monsters_tab", () -> new ItemStack(Items.FOX_SPAWN_EGG));
    public static final ItemGroup TERRA_TOOLS_TAB = new TerraItemGroup("terra_tools_tab", () -> new ItemStack(Items.DIAMOND_PICKAXE));


    public static final class TerraItemGroup extends ItemGroup {

        @Nonnull
        private final Supplier<ItemStack> iconSupplier;

        public TerraItemGroup(@Nonnull final String name, @Nonnull final Supplier<ItemStack> iconSupplier) {
            super(name);
            this.iconSupplier = iconSupplier;
        }

        @Override
        @Nonnull
        public ItemStack createIcon() {
            return iconSupplier.get();
        }

    }

}