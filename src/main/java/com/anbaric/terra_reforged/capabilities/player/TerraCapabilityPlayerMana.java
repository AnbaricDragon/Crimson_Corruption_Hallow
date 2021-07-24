package com.anbaric.terra_reforged.capabilities.player;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class TerraCapabilityPlayerMana
{
    @CapabilityInject(IPlayerMana.class)
    public static final Capability<IPlayerMana> PLAYER_MANA = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IPlayerMana.class, new PlayerManaStorage(), PlayerMana::new);
    }

    public static class PlayerManaStorage implements IStorage<IPlayerMana>
    {
        @Override
        public INBT writeNBT(Capability<IPlayerMana> capability, IPlayerMana instance, Direction side)
        {
            CompoundNBT tag = new CompoundNBT();
            tag.putInt("current_mana", instance.getCurrentMana());

            return tag;
        }

        @Override
        public void readNBT(Capability<IPlayerMana> capability, IPlayerMana instance, Direction side, INBT nbt)
        {
            CompoundNBT tag = (CompoundNBT) nbt;
            instance.setCurrentMana(tag.getInt("current_mana"));
        }
    }
}
