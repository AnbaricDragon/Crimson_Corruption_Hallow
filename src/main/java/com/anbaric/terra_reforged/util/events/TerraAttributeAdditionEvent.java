package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.player.TerraCapabilityPlayerMana;
import com.anbaric.terra_reforged.items.TerraItemManaCrystal;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import mezz.jei.events.PlayerJoinedWorldEvent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraAttributeAdditionEvent
{
    @SubscribeEvent
    public static void onAttachAttributesPlayer(EntityAttributeModificationEvent event)
    {
        System.out.println("Reached EntityAttributeModificationEvent");
        event.add(EntityType.PLAYER, TerraAttributeRegistry.MANA_MAX.get());
    }
}
