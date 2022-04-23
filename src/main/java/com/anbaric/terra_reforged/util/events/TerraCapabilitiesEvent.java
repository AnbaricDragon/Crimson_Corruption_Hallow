package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.PlayerMana.PlayerMana;
import com.anbaric.terra_reforged.capabilities.PlayerMana.PlayerManaAttacher.PlayerManaProvider;
import com.anbaric.terra_reforged.capabilities.PlayerMana.PlayerManaInterface;
import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraCapabilitiesEvent
{
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event)
    {
        event.register(PlayerMana.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof Player)
        {
            if (!event.getObject().getCapability(PlayerMana.PLAYER_MANA_CAPABILITY).isPresent())
            {
                event.addCapability(Reference.path("player_mana"), new PlayerManaProvider());
            }
        }
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
