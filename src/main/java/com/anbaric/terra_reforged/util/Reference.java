package com.anbaric.terra_reforged.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class Reference
{
    public static final String MODID = "terra_reforged";

    public static ResourceLocation path(String path)
    {
        return new ResourceLocation(MODID, path);
    }

    public static ResourceLocation forgePath(String path)
    {
        return new ResourceLocation("forge", path);
    }

    public static void print(String print)
    {
        System.out.println(print);;
    }

    public static boolean isAnbaric(Player player)
    {
        return player.getUUID().equals(UUID.fromString("f648da61-7d7c-449b-9fd7-05fa8eac0b0f"));
    }
}