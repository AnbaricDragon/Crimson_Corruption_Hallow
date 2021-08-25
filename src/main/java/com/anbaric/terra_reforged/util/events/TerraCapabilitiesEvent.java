package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.hardmode.WorldProgressionProvider;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.PlayerManaHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraCapabilitiesEvent
{
    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) event.getEntity();
            PlayerManaHandler.getManaHolder(serverPlayer).sendPlayerUpdatePacket(serverPlayer);
        }
    }

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
