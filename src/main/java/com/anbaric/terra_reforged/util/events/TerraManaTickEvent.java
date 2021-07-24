package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.player.TerraCapabilityPlayerMana;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraManaTickEvent
{
    @SubscribeEvent
    static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity player = event.player;
        player.getCapability(TerraCapabilityPlayerMana.PLAYER_MANA).ifPresent(cap ->
        {
            int currentMana = cap.getCurrentMana();
            if (currentMana < 400)
            {
                cap.setCurrentMana(currentMana + 1);
            }
            System.out.println(currentMana);
        });
    }
}
