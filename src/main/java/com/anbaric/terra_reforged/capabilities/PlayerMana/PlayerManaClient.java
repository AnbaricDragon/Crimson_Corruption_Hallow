package com.anbaric.terra_reforged.capabilities.PlayerMana;

public class PlayerManaClient
{
    private static int currentMana;
    private static int manaCrystalsUsed;

    public static void setCurrentMana(int currentMana)
    {
        PlayerManaClient.currentMana = currentMana;
    }

    public static void setMaxMana(int manaCrystalsUsed)
    {
        PlayerManaClient.manaCrystalsUsed = manaCrystalsUsed;
    }

    public static int getCurrentMana()
    {
        return currentMana;
    }

    public static int getManaCrystalsUsed()
    {
        return manaCrystalsUsed;
    }
}
