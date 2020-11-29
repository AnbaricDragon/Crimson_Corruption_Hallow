package com.anbaric.terra_reforged.capabilities.multijump;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class MultiJumpProvider implements ICapabilitySerializable<INBT>
{
    private final MultiJump defaultMultiJump = new MultiJump();
    private final LazyOptional<IMultiJump> optionalMultiJump = LazyOptional.of(() -> defaultMultiJump);

    public void invalidate() {optionalMultiJump.invalidate();}

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side)
    {
        return optionalMultiJump.cast();
    }

    @Override
    public INBT serializeNBT()
    {
        if (TerraCapabilityMultiJump.MULTI_JUMP_CAPABILITY == null)
        {
            return new CompoundNBT();
        }
        else
        {
            return (CompoundNBT) TerraCapabilityMultiJump.MULTI_JUMP_CAPABILITY.writeNBT(defaultMultiJump, null);
        }
    }

    @Override
    public void deserializeNBT(INBT nbt)
    {
        if (TerraCapabilityMultiJump.MULTI_JUMP_CAPABILITY != null)
        {
            TerraCapabilityMultiJump.MULTI_JUMP_CAPABILITY.readNBT(defaultMultiJump, null, nbt);
        }
    }
}
