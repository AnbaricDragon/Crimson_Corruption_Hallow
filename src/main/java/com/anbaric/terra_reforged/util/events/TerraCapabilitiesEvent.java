package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.hardmode.WorldProgressionProvider;
import com.anbaric.terra_reforged.capabilities.multijump.MultiJumpProvider;
import com.anbaric.terra_reforged.util.Reference;
//import com.anbaric.terra_reforged.util.capabilities.multijump.MultiJumpProvider;
//import com.anbaric.terra_reforged.util.capabilities.multijump.TerraCapabilityMultiJump;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraCapabilitiesEvent
{
    @SubscribeEvent
    public static void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof PlayerEntity)
        {
            MultiJumpProvider provider = new MultiJumpProvider();
            event.addCapability(new ResourceLocation(Reference.MODID, "multi_jump"), provider);
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
