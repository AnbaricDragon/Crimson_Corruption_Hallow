package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraAttributeAdditionEvent
{
    @SubscribeEvent
    public static void onAttachAttributesPlayer(EntityAttributeModificationEvent event)
    {
        event.add(EntityType.PLAYER, TerraAttributeRegistry.MANA_MAX.get());
        event.add(EntityType.PLAYER, TerraAttributeRegistry.MANA_DISCOUNT.get());

        event.add(EntityType.PLAYER, TerraAttributeRegistry.RANGED_DAMAGE.get());
        event.add(EntityType.PLAYER, TerraAttributeRegistry.MAGIC_DAMAGE.get());
        event.add(EntityType.PLAYER, TerraAttributeRegistry.MINION_DAMAGE.get());
    }
}
