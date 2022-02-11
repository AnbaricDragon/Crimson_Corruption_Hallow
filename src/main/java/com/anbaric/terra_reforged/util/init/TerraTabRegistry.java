package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import net.minecraft.item.Items;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public final class TerraTabRegistry
{
    public static final CreativeModeTab TERRA_BLOCKS_TAB = new TerraItemGroup("terra_blocks_tab", () -> new ItemStack(TerraBlockRegistry.GRASS_JUNGLE.get()));
    public static final CreativeModeTab TERRA_DECORATIONS_TAB = new TerraItemGroup("terra_decorations_tab", () -> new ItemStack(TerraBlockRegistry.LEAF_PEARL_CYAN.get()));
    public static final CreativeModeTab TERRA_MATERIALS_TAB = new TerraItemGroup("terra_materials_tab", () -> new ItemStack(TerraItemRegistry.INGOT_COBALT.get()));
    public static final CreativeModeTab TERRA_MECHANICS_TAB = new TerraItemGroup("terra_mechanics_tab", () -> new ItemStack(Items.REDSTONE));
    public static final CreativeModeTab TERRA_MONSTERS_TAB = new TerraItemGroup("terra_monsters_tab", () -> new ItemStack(Items.FOX_SPAWN_EGG));
    public static final CreativeModeTab TERRA_TOOLS_TAB = new TerraItemGroup("terra_tools_tab", () -> new ItemStack(TerraItemRegistry.PICKAXE_CHLOROPHYTE.get()));
    public static final CreativeModeTab TERRA_WEAPONS_TAB = new TerraItemGroup("terra_weapons_tab", () -> new ItemStack(TerraItemRegistry.SWORD_TERRA.get()));

    public static final class TerraItemGroup extends CreativeModeTab
    {
        @Nonnull
        private final Supplier<ItemStack> iconSupplier;

        public TerraItemGroup(@Nonnull final String name, @Nonnull final Supplier<ItemStack> iconSupplier)
        {
            super(name);
            this.iconSupplier = iconSupplier;
        }

        @Override
        public ItemStack makeIcon()
        {
            return null;
        }
    }
}