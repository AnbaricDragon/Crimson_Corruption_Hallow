package com.anbaric.terra_reforged.items;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import top.theillusivec4.curios.api.CuriosApi;

public class TerraItemLuckyHorseshoe extends TerraItemAccessory
{
    public TerraItemLuckyHorseshoe(Properties properties)
    {
        super(properties);
        MinecraftForge.EVENT_BUS.addListener(this::cancelFallDamage);
    }

    private void cancelFallDamage(LivingFallEvent event)
    {
        CuriosApi.getCuriosHelper().findEquippedCurio(this, event.getEntityLiving()).ifPresent(found -> event.setCanceled(true));
    }
}
