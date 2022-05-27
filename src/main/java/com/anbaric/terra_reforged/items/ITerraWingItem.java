package com.anbaric.terra_reforged.items;

import net.minecraft.world.item.ItemStack;

public interface ITerraWingItem
{
    void setCooldown(ItemStack stack);

    int wingCooldown();
}
