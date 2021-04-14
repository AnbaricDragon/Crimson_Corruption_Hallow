package com.anbaric.terra_reforged.capabilities.hardmode;

import com.anbaric.terra_reforged.capabilities.multijump.IMultiJump;
import com.anbaric.terra_reforged.capabilities.multijump.MultiJump;
import com.anbaric.terra_reforged.capabilities.multijump.TerraCapabilityMultiJump;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class WorldProgressionProvider implements ICapabilitySerializable<INBT>
{
    private final WorldProgression defaultWorldProgression = new WorldProgression();
    private final LazyOptional<IWorldProgression> optionalWorldProgression = LazyOptional.of(() -> defaultWorldProgression);

    public void invalidate() {optionalWorldProgression.invalidate();}

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side)
    {
        if (capability == TerraCapabilityWorldProgression.WORLD_PROGRESSION_CAPABILITY)
        {
            return optionalWorldProgression.cast();
        }
        else return LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT()
    {
        if (TerraCapabilityWorldProgression.WORLD_PROGRESSION_CAPABILITY == null)
        {
            return new CompoundNBT();
        }
        else
        {
            return TerraCapabilityWorldProgression.WORLD_PROGRESSION_CAPABILITY.writeNBT(defaultWorldProgression, null);
        }
    }

    @Override
    public void deserializeNBT(INBT nbt)
    {
        if (TerraCapabilityWorldProgression.WORLD_PROGRESSION_CAPABILITY != null)
        {
            TerraCapabilityWorldProgression.WORLD_PROGRESSION_CAPABILITY.readNBT(defaultWorldProgression, null, nbt);
        }
    }
}
