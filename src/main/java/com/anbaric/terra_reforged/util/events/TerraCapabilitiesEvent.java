package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.hardmode.WorldProgressionProvider;
import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraCapabilitiesEvent
{
    @SubscribeEvent
    public static void onAttachCapabilitiesWorld(AttachCapabilitiesEvent<World> event)
    {
        if (event.getObject().getDimensionKey() == World.OVERWORLD)
        {
            WorldProgressionProvider provider = new WorldProgressionProvider();
            event.addCapability(new ResourceLocation(Reference.MODID, "world_progression"), provider);
            event.addListener(provider::invalidate);
        }
    }
}
