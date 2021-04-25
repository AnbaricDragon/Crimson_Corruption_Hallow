package com.anbaric.terra_reforged.util.handlers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CurioHandler
{
    public static void register()
    {
        CapabilityManager.INSTANCE.register(ICurio.class, new Capability.IStorage<ICurio>()
        {
            @Override
            public INBT writeNBT(Capability<ICurio> capability, ICurio instance, Direction side)
            {
                return new CompoundNBT();
            }

            @Override
            public void readNBT(Capability<ICurio> capability, ICurio instance, Direction side, INBT nbt)
            {
            }
        }, CurioItemWrapper::new);
    }

    public static ICapabilityProvider createProvider(final ICurio curio)
    {
        return new Provider(curio);
    }

    private static class CurioItemWrapper implements ICurio
    {

    }

    public static class Provider implements ICapabilityProvider
    {

        final LazyOptional<ICurio> capability;

        Provider(ICurio curio)
        {
            this.capability = LazyOptional.of(() -> curio);
        }

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
        {
            return CuriosCapability.ITEM.orEmpty(cap, this.capability);
        }
    }

    public static ItemStack getBauble(Item item, PlayerEntity player)
    {
        return CuriosApi.getCuriosHelper().getCuriosHandler(player).map(ICuriosItemHandler::getCurios).map(map -> map.get("curio")).map(ICurioStacksHandler::getStacks).map(dynamicStackHandler ->
        {
            for (int i = 0; i < dynamicStackHandler.getSlots(); i++)
            {
                ItemStack stack = dynamicStackHandler.getStackInSlot(i);
                if (stack.getItem() == item)
                {
                    return stack;
                }
            }
            return ItemStack.EMPTY;
        }).orElse(ItemStack.EMPTY);
    }


}
