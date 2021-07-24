package com.anbaric.terra_reforged.capabilities.player;

public class PlayerMana implements IPlayerMana
{
    private int mana;

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
