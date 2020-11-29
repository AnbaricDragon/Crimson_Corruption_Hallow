package com.anbaric.terra_reforged.capabilities.multijump;

public interface IMultiJump
{
    boolean getHasCloudItem();
    boolean getHasBlizzardItem();
    boolean getHasSandstormItem();
    boolean getHasTsunamiItem();
    boolean getHasFartItem();

    void setHasCloudItem(boolean hasItem);
    void setHasBlizzardItem(boolean hasItem);
    void setHasSandstormItem(boolean hasItem);
    void setHasTsunamiItem(boolean hasItem);
    void setHasFartItem(boolean hasItem);

    boolean getCanCloudJump();
    boolean getCanBlizzardJump();
    boolean getCanSandstormJump();
    boolean getCanTsunamiJump();
    boolean getCanFartJump();

    void setCanCloudJump(boolean hasJump);
    void setCanBlizzardJump(boolean hasJump);
    void setCanSandstormJump(boolean hasJump);
    void setCanTsunamiJump(boolean hasJump);
    void setCanFartJump(boolean hasJump);

    void resetJumps();
}
