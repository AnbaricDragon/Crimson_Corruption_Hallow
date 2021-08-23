package com.anbaric.terra_reforged.util.handlers;

import net.minecraft.item.ItemStack;

public class NBTHandler
{
    public static boolean verifyExistance(ItemStack stack, String tag)
    {
        return !stack.isEmpty() && stack.getOrCreateTag().contains(tag);
    }

    public static int getInt(ItemStack stack, String tag, int defaultExpected)
    {
        return verifyExistance(stack, tag) ? stack.getOrCreateTag().getInt(tag) : defaultExpected;
    }

    public static void setInt(ItemStack stack, String tag, int i) {
        stack.getOrCreateTag().putInt(tag, i);
    }
}
