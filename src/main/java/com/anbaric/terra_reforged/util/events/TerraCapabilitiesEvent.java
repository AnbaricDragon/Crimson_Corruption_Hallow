package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.PlayerMana.PlayerMana;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraCapabilitiesEvent
{
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event)
    {
        event.register(PlayerMana.class);
    }

    //    @SubscribeEvent
    //    public static void onAttachCapabilitiesWorld(AttachCapabilitiesEvent<Level> event)
    //    {
    //        if (event.getObject().dimension() == Level.OVERWORLD)
    //        {
    //            WorldProgressionProvider provider = new WorldProgressionProvider();
    //            event.addCapability(new ResourceLocation(Reference.MODID, "world_progression"), provider);
    //            event.addListener(provider::invalidate);
    //        }
    //    }
}
