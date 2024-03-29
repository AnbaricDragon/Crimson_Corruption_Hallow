package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraAttributeAdditionEvent
{
    @SubscribeEvent
    static void onAttachAttributesPlayer(EntityAttributeModificationEvent event)
    {
        event.add(EntityType.PLAYER, Attributes.ATTACK_KNOCKBACK);

        event.add(EntityType.PLAYER, TerraAttributeRegistry.MANA_MAX.get());
        event.add(EntityType.PLAYER, TerraAttributeRegistry.MANA_DISCOUNT.get());

        event.add(EntityType.PLAYER, TerraAttributeRegistry.RANGED_DAMAGE.get());
        event.add(EntityType.PLAYER, TerraAttributeRegistry.MAGIC_DAMAGE.get());
        event.add(EntityType.PLAYER, TerraAttributeRegistry.MINION_DAMAGE.get());
        event.add(EntityType.PLAYER, TerraAttributeRegistry.MINION_MAX.get());
        event.add(EntityType.PLAYER, TerraAttributeRegistry.CRIT_CHANCE.get());
    }
}
