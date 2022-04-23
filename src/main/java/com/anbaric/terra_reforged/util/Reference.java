package com.anbaric.terra_reforged.util;

import net.minecraft.resources.ResourceLocation;

public class Reference
{
    public static final String MODID = "terra_reforged";

    public static ResourceLocation path(String path)
    {
        return new ResourceLocation(MODID, path);
    }
    public static void print(String print)
    {
        System.out.println(print);;
    }
}