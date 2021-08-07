package com.anbaric.terra_reforged.util.handlers;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.tags.ITag;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.ChunkEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

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

    public static boolean hasBauble(PlayerEntity player, Item... item)
    {
        AtomicBoolean foundItem = new AtomicBoolean(false);
        for (Item curioItem : item)
        {
            CuriosApi.getCuriosHelper().findEquippedCurio(curioItem, player).ifPresent(found ->
            {
                foundItem.set(true);
            });
        }
        return foundItem.get();
    }

    public static boolean hasAllBaubles(PlayerEntity player, Item... item)
    {
        boolean hasAllItems = true;
        for (Item curioItem : item)
        {
            if (!hasBauble(player, curioItem))
            {
                hasAllItems = false;
            }
        }
        return hasAllItems;
    }

    public static boolean hasBauble(PlayerEntity player, ITag<Item> tag)
    {
        boolean foundItem = false;
        for (Item curioItem : tag.getValues())
        {
            if (hasBauble(player, curioItem))
            {
                foundItem = true;
            }
        }
        return foundItem;
    }

    public static ItemStack getBaubles(PlayerEntity player, Item... item)
    {
        for (Item curioItem : item)
        {
            if (!getBauble(player, curioItem).isEmpty())
            {
                return getBauble(player, curioItem);
            }
        }
        return ItemStack.EMPTY;
    }

    public static ItemStack getBauble(PlayerEntity player, Item item)
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
