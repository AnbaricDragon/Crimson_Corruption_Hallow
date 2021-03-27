package com.anbaric.terra_reforged.util;

import net.minecraft.util.ResourceLocation;

public class Reference
{
    public static final String MODID = "terra_reforged";

    public static ResourceLocation path(String path)
    {
        return new ResourceLocation(MODID, path);
    }
}