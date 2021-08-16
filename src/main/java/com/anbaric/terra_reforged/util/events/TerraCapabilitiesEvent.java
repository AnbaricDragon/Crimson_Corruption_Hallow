package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.hardmode.WorldProgressionProvider;
import com.anbaric.terra_reforged.capabilities.player.PlayerManaProvider;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraCapabilitiesEvent
{
    @SubscribeEvent
    public static void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof PlayerEntity)
        {
            PlayerManaProvider provider = new PlayerManaProvider();
            event.addCapability(new ResourceLocation(Reference.MODID, "player_mana"), provider);
            event.addListener(provider::invalidate);
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesWorld(AttachCapabilitiesEvent<World> event)
    {
        if (event.getObject().dimension() == World.OVERWORLD)
        {
            WorldProgressionProvider provider = new WorldProgressionProvider();
            event.addCapability(new ResourceLocation(Reference.MODID, "world_progression"), provider);
            event.addListener(provider::invalidate);
        }
    }
}
