package com.anbaric.terra_reforged.util.capabilities.multijump;

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
            tag.putBoolean("can_cloud_jump", instance.getCanCloudJump());
            return tag;
        }

        @Override
        public void readNBT(Capability<IMultiJump> capability, IMultiJump instance, Direction side, INBT nbt)
        {
            CompoundNBT tag = (CompoundNBT) nbt;
            instance.setHasCloudItem(tag.getBoolean("has_cloud_item"));
            instance.setCanCloudJump(tag.getBoolean("can_cloud_jump"));
        }
    }
}
