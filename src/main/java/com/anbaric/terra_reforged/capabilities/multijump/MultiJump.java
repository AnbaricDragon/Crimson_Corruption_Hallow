package com.anbaric.terra_reforged.capabilities.multijump;

public class MultiJump implements IMultiJump
{
    private boolean hasCloudItem;
    private boolean hasBlizzardItem;
    private boolean hasSandstormItem;
    private boolean hasTsunamiItem;
    private boolean hasFartItem;

    private boolean hasCloudJump;
    private boolean hasBlizzardJump;
    private boolean hasSandstormJump;
    private boolean hasTsunamiJump;
    private boolean hasFartJump;

    public boolean getHasCloudItem()     { return this.hasCloudItem; }
    public boolean getHasBlizzardItem()  { return this.hasBlizzardItem; }
    public boolean getHasSandstormItem() { return this.hasSandstormItem; }
    public boolean getHasTsunamiItem()   { return this.hasTsunamiItem; }
    public boolean getHasFartItem()      { return this.hasFartItem; }

    public void setHasCloudItem(boolean hasItem)     { this.hasCloudItem = hasItem; }
    public void setHasBlizzardItem(boolean hasItem)  { this.hasBlizzardItem = hasItem; }
    public void setHasSandstormItem(boolean hasItem) { this.hasSandstormItem = hasItem; }
    public void setHasTsunamiItem(boolean hasItem)   { this.hasTsunamiItem = hasItem; }
    public void setHasFartItem(boolean hasItem)      { this.hasFartItem = hasItem; }

    public boolean getCanFartJump()
    {
        boolean canJump = this.hasFartItem && this.hasFartJump;
        return canJump;
    }

    public boolean getCanTsunamiJump()
    {
        boolean noPrevJumps = !this.hasFartJump;
        boolean canJump     = this.hasTsunamiItem && this.hasTsunamiJump;
        return canJump && noPrevJumps;
    }

    public boolean getCanSandstormJump()
    {
        boolean noPrevJumps = !this.hasFartJump && !this.hasTsunamiJump;
        boolean canJump     = this.hasSandstormItem && this.hasSandstormJump;
        return canJump && noPrevJumps;
    }

    public boolean getCanBlizzardJump()
    {
        boolean noPrevJumps = !this.hasFartJump && !this.hasTsunamiJump && !this.hasSandstormJump;
        boolean canJump     = this.hasBlizzardItem && this.hasBlizzardJump;
        return canJump && noPrevJumps;
    }

    public boolean getCanCloudJump()
    {
        boolean noPrevJumps = !this.hasFartJump && !this.hasTsunamiJump && !this.hasSandstormJump && !this.hasBlizzardJump;
        boolean canJump     = this.hasCloudItem && this.hasCloudJump;
        return canJump && noPrevJumps;
    }

    public void setCanCloudJump(boolean hasJump)     { this.hasCloudJump = hasJump; }
    public void setCanBlizzardJump(boolean hasJump)  { this.hasBlizzardJump = hasJump; }
    public void setCanSandstormJump(boolean hasJump) { this.hasSandstormJump = hasJump; }
    public void setCanTsunamiJump(boolean hasJump)   { this.hasTsunamiJump = hasJump; }
    public void setCanFartJump(boolean hasJump)      { this.hasFartJump = hasJump; }

    public void resetJumps()
    {
        this.hasCloudJump = this.hasCloudItem;
        this.hasBlizzardJump = this.hasBlizzardItem;
        this.hasSandstormJump = this.hasSandstormItem;
        this.hasTsunamiJump = this.hasTsunamiItem;
        this.hasFartJump = this.hasFartItem;
    }
}
