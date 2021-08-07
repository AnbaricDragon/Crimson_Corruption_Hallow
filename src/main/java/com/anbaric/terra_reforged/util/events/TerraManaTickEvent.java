package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.player.TerraCapabilityPlayerMana;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.concurrent.atomic.AtomicInteger;

public class TerraManaTickEvent
{
    private static int currentMana;

    @SubscribeEvent
    static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity  player  = event.player;
        AtomicInteger maxMana = new AtomicInteger(20);

        if (player.getAttributes().hasAttribute(TerraAttributeRegistry.MANA_MAX.get()))
        {
            maxMana.set((int) player.getAttributeValue(TerraAttributeRegistry.MANA_MAX.get()));
        }

        player.getCapability(TerraCapabilityPlayerMana.PLAYER_MANA).ifPresent(cap ->
        {
            currentMana = cap.getCurrentMana();
            if (currentMana < maxMana.get())
            {
                cap.setCurrentMana(currentMana + 1);
            }
            System.out.println(maxMana.get());
        });
    }
}
