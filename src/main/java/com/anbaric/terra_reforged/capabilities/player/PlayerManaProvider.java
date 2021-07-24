package com.anbaric.terra_reforged.capabilities.player;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerManaProvider implements ICapabilitySerializable<INBT>
{
    private final PlayerMana defaultPlayerMana = new PlayerMana();
    private final LazyOptional<IPlayerMana> optionalPlayerMana = LazyOptional.of(() -> defaultPlayerMana);

    public void invalidate()
    {
        optionalPlayerMana.invalidate();
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side)
    {
        if (capability == TerraCapabilityPlayerMana.PLAYER_MANA)
        {
            return optionalPlayerMana.cast();
        }
        else return LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT()
    {
        if (TerraCapabilityPlayerMana.PLAYER_MANA == null)
        {
            return new CompoundNBT();
        }
        else
        {
            return TerraCapabilityPlayerMana.PLAYER_MANA.writeNBT(defaultPlayerMana, null);
        }
    }

    @Override
    public void deserializeNBT(INBT nbt)
    {
        if (TerraCapabilityPlayerMana.PLAYER_MANA != null)
        {
            TerraCapabilityPlayerMana.PLAYER_MANA.readNBT(defaultPlayerMana, null, nbt);
        }
    }
}