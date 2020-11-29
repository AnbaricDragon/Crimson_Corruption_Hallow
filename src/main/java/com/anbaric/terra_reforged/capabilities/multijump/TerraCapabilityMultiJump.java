package com.anbaric.terra_reforged.capabilities.multijump;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class TerraCapabilityMultiJump
{
    @CapabilityInject(IMultiJump.class)
    public static final Capability<IMultiJump> MULTI_JUMP_CAPABILITY = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IMultiJump.class, new MultiJumpStorage(), MultiJump::new);
    }

    public static class MultiJumpStorage implements IStorage<IMultiJump>
    {
        @Override
        public INBT writeNBT(Capability<IMultiJump> capability, IMultiJump instance, Direction side)
        {
            CompoundNBT tag = new CompoundNBT();
            tag.putBoolean("has_cloud_item", instance.getHasCloudItem());
            tag.putBoolean("has_blizzard_item", instance.getHasBlizzardItem());
            tag.putBoolean("has_sandstorm_item", instance.getHasSandstormItem());
            tag.putBoolean("has_tsunami_item", instance.getHasTsunamiItem());
            tag.putBoolean("has_fart_item", instance.getHasFartItem());
            tag.putBoolean("can_cloud_jump", instance.getCanCloudJump());
            tag.putBoolean("can_blizzard_jump", instance.getCanCloudJump());
            tag.putBoolean("can_sandstorm_jump", instance.getCanCloudJump());
            tag.putBoolean("can_tsunami_jump", instance.getCanCloudJump());
            tag.putBoolean("can_fart_jump", instance.getCanCloudJump());
            return tag;
        }

        @Override
        public void readNBT(Capability<IMultiJump> capability, IMultiJump instance, Direction side, INBT nbt)
        {
            CompoundNBT tag = (CompoundNBT) nbt;
            instance.setHasCloudItem(tag.getBoolean("has_cloud_item"));
            instance.setHasBlizzardItem(tag.getBoolean("has_blizzard_item"));
            instance.setHasSandstormItem(tag.getBoolean("has_sandstorm_item"));
            instance.setHasTsunamiItem(tag.getBoolean("has_tsunami_item"));
            instance.setHasFartItem(tag.getBoolean("has_fart_item"));
            instance.setCanCloudJump(tag.getBoolean("can_cloud_jump"));
            instance.setCanBlizzardJump(tag.getBoolean("can_blizzard_jump"));
            instance.setCanSandstormJump(tag.getBoolean("can_sandstorm_jump"));
            instance.setCanTsunamiJump(tag.getBoolean("can_tsunami_jump"));
            instance.setCanFartJump(tag.getBoolean("can_fart_jump"));
        }
    }
}
