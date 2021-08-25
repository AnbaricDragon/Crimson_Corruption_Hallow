package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraEntityModificationEvent
{
    @SubscribeEvent
    static void onArrowCreation(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof AbstractArrowEntity)
        {
            AbstractArrowEntity arrowEntity = (AbstractArrowEntity) event.getEntity();
            if (arrowEntity.getShooter() instanceof PlayerEntity)
            {
                PlayerEntity player = (PlayerEntity) arrowEntity.getShooter();
                if (player.getHeldItem(Hand.MAIN_HAND).getItem() == Items.BOW || player.getHeldItem(Hand.MAIN_HAND).getItem() == Items.CROSSBOW)
                {
                    if (CurioHandler.hasBauble(player, TerraItemRegistry.EMBLEM_RANGER.get(), TerraItemRegistry.EMBLEM_AVENGER.get()))
                    {
                        arrowEntity.setDamage(arrowEntity.getDamage() * (player.getAttribute(TerraAttributeRegistry.RANGED_DAMAGE.get()).getValue() + 1));
                    }
                }
            }
        }
    }
}
