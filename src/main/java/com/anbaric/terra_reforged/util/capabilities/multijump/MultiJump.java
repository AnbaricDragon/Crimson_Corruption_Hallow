package com.anbaric.terra_reforged.util.capabilities.multijump;

public class MultiJump implements IMultiJump
{
    private boolean hasCloudItem;
    //    private boolean hasBlizzardItem;
    //    private boolean hasSandstormItem;
    //    private boolean hasTsunamiItem;
    //    private boolean hasFartItem;

    private boolean usedCloudJump;
    //    private boolean usedBlizzardJump;
    //    private boolean usedSandstormJump;
    //    private boolean usedTsunamiJump;
    //    private boolean usedFartJump;

    public MultiJump()
    {
        this.hasCloudItem = false;
        //        this.hasBlizzardItem = false;
        //        this.hasSandstormItem = false;
        //        this.hasTsunamiItem = false;
        //        this.hasFartItem = false;

        this.usedCloudJump = false;
        //        this.usedBlizzardJump = false;
        //        this.usedSandstormJump = false;
        //        this.usedTsunamiJump = false;
        //        this.usedFartJump = false;
    }

    @Override public void setHasCloudItem(boolean hasCloudItem) { this.hasCloudItem = hasCloudItem; }
    @Override public boolean getHasCloudItem() { return hasCloudItem; }

    @Override public void setCanCloudJump(boolean canCloudJump) { this.usedCloudJump = canCloudJump; }
    @Override public boolean getCanCloudJump() { return !usedCloudJump; }

    //    @Override
    //    public boolean canBlizzardJump()
    //    {
    //        return hasBlizzardItem && !usedBlizzardJump;
    //    }
    //
    //    @Override
    //    public boolean canSnowstormJump()
    //    {
    //        return hasSandstormItem && !usedSandstormJump;
    //    }
    //
    //    @Override
    //    public boolean canTsunamiJump()
    //    {
    //        return hasTsunamiItem && !usedTsunamiJump;
    //    }
    //
    //    @Override
    //    public boolean canFartJump()
    //    {
    //        return hasFartItem && !usedFartJump;
    //    }

    @Override
    public void resetJumps()
    {
        this.usedCloudJump = false;
    }
}
