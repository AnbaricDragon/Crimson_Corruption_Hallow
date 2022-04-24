package com.anbaric.terra_reforged.util.handlers;

import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.IReverseTag;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class CurioHandler
{
    public static ICapabilityProvider createProvider(final ICurio curio) {
        return new Provider(curio);
    }

    public static class Provider implements ICapabilityProvider {

        final LazyOptional<ICurio> capability;

        Provider(ICurio curio) {
            this.capability = LazyOptional.of(() -> curio);
        }

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            return CuriosCapability.ITEM.orEmpty(cap, this.capability);
        }
    }

    public static boolean hasBauble(Player player, Item... item)
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

    @SafeVarargs
    public static boolean hasBauble(Player player, TagKey<Item>... tagKey)
    {
        AtomicBoolean foundItem = new AtomicBoolean(false);
        for (TagKey<Item> tag : tagKey)
        {
            ForgeRegistries.ITEMS.tags().getTag(tag).forEach(item ->
            {
                if (hasBauble(player, item))
                {
                    foundItem.getAndSet(true);
                }
            });
        }
        return foundItem.get();
    }

    public static int countBaubles(Player player, TagKey<Item> tag)
    {
        AtomicInteger result = new AtomicInteger(0);
        ForgeRegistries.ITEMS.tags().getTag(tag).forEach(checkItem ->
        {
            if (hasBauble(player, checkItem))
            {
                result.incrementAndGet();
            }
        });
        return result.get();
    }

    public static boolean hasAllBaubles(Player player, Item... item)
    {
        boolean result = true;
        for (Item curioItem : item)
        {
            if (!hasBauble(player, curioItem))
            {
                result = false;
            }
        }
        return result;
    }

    public static List<ItemStack> getAllStacks(Player player, TagKey<Item> tag)
    {
        List<ItemStack> equippedList = new ArrayList<>();
        ForgeRegistries.ITEMS.tags().getTag(tag).forEach(checkItem ->
        {
            if (hasBauble(player, checkItem))
            {
                equippedList.add(getStack(player, checkItem));
            }
        });
        return equippedList;
    }


    public static boolean hasRedundancy(Player player, Item item)
    {
        AtomicBoolean hasRedundancy = new AtomicBoolean(false);
        ForgeRegistries.ITEMS.tags().getReverseTag(item).map(IReverseTag::getTagKeys).orElseGet(Stream::of).forEach(tagKey ->
        {
            ForgeRegistries.ITEMS.tags().getTag(tagKey).forEach(checkItem ->
            {
                if (item != checkItem)
                {
                    if (hasBauble(player, checkItem))
                    {
                        hasRedundancy.set(true);
                    }
                }
            });
        });
        return hasRedundancy.get();
    }

    public static ItemStack getStack(Player player, Item item)
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

    public static ItemStack getStack(Player player, TagKey<Item> tag)
    {
        AtomicReference<ItemStack> result = new AtomicReference<>(ItemStack.EMPTY);
        ForgeRegistries.ITEMS.tags().getTag(tag).forEach(item ->
        {
            if (hasBauble(player, item))
            {
                result.set(getStack(player, item));
            }
        });
        return result.get();
    }
}
