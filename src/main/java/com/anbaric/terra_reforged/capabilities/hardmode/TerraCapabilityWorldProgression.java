package com.anbaric.terra_reforged.capabilities.hardmode;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class TerraCapabilityWorldProgression
{
    @CapabilityInject(IWorldProgression.class)
    public static final Capability<IWorldProgression> WORLD_PROGRESSION_CAPABILITY = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IWorldProgression.class, new WorldProgressionStorage(), WorldProgression::new);
    }

    public static class WorldProgressionStorage implements IStorage<IWorldProgression>
    {
        @Override
        public INBT writeNBT(Capability<IWorldProgression> capability, IWorldProgression instance, Direction side)
        {
            CompoundNBT tag = new CompoundNBT();
            tag.putInt("altars_broken", instance.getAltarsBroken());

            return tag;
        }

        @Override
        public void readNBT(Capability<IWorldProgression> capability, IWorldProgression instance, Direction side, INBT nbt)
        {
            CompoundNBT tag = (CompoundNBT) nbt;
            instance.setAltarsBroken(tag.getInt("altars_broken"));
        }
    }
}
