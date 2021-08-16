package com.anbaric.terra_reforged.capabilities.player;

public class PlayerMana implements IPlayerMana
{
    private int mana;
    private int manaCrystalsUsed;

    @Override
    public int getCurrentMana()
    {
        return this.mana;
    }

    @Override
    public void setCurrentMana(int mana)
    {
        this.mana = mana;
    }

    @Override
    public int getManaCrystalsUsed()
    {
        return this.manaCrystalsUsed;
    }

    @Override
    public void setManaCrystalsUsed(int manaCrystalsUsed)
    {
        this.manaCrystalsUsed = manaCrystalsUsed;
    }

    @Override
    public boolean spendMana(int maxMana, double discount, int cost)
    {
        int trueCost = (int) Math.floor(cost * discount);
        if (trueCost < this.mana)
        {
            this.mana -= trueCost;
            return true;
        }
        return false;
    }
}
