package com.anbaric.terra_reforged.capabilities.PlayerMana;

import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class PlayerManaAttacher
{
    public static class PlayerManaProvider implements ICapabilityProvider, INBTSerializable<CompoundTag>
    {
        public static final ResourceLocation IDENTIFIER = Reference.path("player_mana");

        private final PlayerManaInterface backend = new PlayerManaImplementation();
        private final LazyOptional<PlayerManaInterface> optionalData = LazyOptional.of(() -> backend);

        @NotNull
        @Override
        public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side)
        {
            return PlayerMana.PLAYER_MANA_CAPABILITY.orEmpty(cap, this.optionalData);
        }

        void invalidate()
        {
            this.optionalData.invalidate();
        }

        @Override
        public CompoundTag serializeNBT()
        {
            return this.backend.serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag nbt)
        {
            this.backend.deserializeNBT(nbt);
        }
    }

    private PlayerManaAttacher()
    {
    }
}
