package com.anbaric.terra_reforged.items;

import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import top.theillusivec4.curios.api.CuriosApi;

public class TerraItemObsidianSkull extends TerraItemAccessory
{
    public TerraItemObsidianSkull(Properties properties)
    {
        super(properties);
        MinecraftForge.EVENT_BUS.addListener(this::cancelFireDamage);
    }

    private void cancelFireDamage(LivingAttackEvent event)
    {
        CuriosApi.getCuriosHelper().findEquippedCurio(this, event.getEntityLiving()).ifPresent(found -> {
            DamageSource source = event.getSource();
            if (source.isFireDamage() && source != DamageSource.LAVA)
            {
                event.getEntityLiving().extinguish();
                event.setCanceled(true);
            }
        });
    }
}