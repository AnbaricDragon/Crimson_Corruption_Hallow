package com.anbaric.terra_reforged.util.capabilities.multijump;

public interface IMultiJump
{
    void setHasCloudItem(boolean hasCloudItem);
    boolean getHasCloudItem();

    void setCanCloudJump(boolean canCloudJump);
    boolean getCanCloudJump();

    void resetJumps();
}
