package com.anbaric.terra_reforged.capabilities.PlayerMana;

public class PlayerManaClient
{
    private static int currentMana;
    private static int maxMana;

    public static void setCurrentMana(int currentMana)
    {
        PlayerManaClient.currentMana = currentMana;
    }

    public static void setMaxMana(int maxMana)
    {
        PlayerManaClient.maxMana = maxMana;
    }

    public static int getPlayer()
    {
        return currentMana;
    }

    public static int getCurrentMana()
    {
        return currentMana;
    }

    public static int getMaxMana()
    {
        return maxMana;
    }
}
