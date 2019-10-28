package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.function.Supplier;

public class TerraItemGroups
{
    public static final ItemGroup TERRA_BLOCKS_GROUP = new TerraItemGroup(Reference.MODID, () -> new ItemStack(Items.LIGHT_BLUE_BANNER));

    public static class TerraItemGroup extends ItemGroup
    {
        private final Supplier<ItemStack> iconSupplier;

        public TerraItemGroup(final String name, final Supplier<ItemStack> iconSupplier)
        {
            super(name);
            this.iconSupplier = iconSupplier;
        }

        @Override
        public ItemStack createIcon()
        {
            return iconSupplier.get();
        }
    }
}
