package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;

public class TerraItemFrogLeg extends TerraItemAccessory
{
    public TerraItemFrogLeg(Properties properties)
    {
        super(properties);
        MinecraftForge.EVENT_BUS.addListener(this::cancelFallDamage);
    }

    private void cancelFallDamage(LivingFallEvent event)
    {
        CuriosApi.getCuriosHelper().findEquippedCurio(this, event.getEntityLiving()).ifPresent(found -> event.setDistance(event.getDistance() - 3));
    }
}