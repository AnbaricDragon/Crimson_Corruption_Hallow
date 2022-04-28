package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.player_mana.TerraMana;
import com.anbaric.terra_reforged.items.TerraItemManaCrystal;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.concurrent.atomic.AtomicInteger;

public class TerraPlayerDeathEvent
{
    @SubscribeEvent
    static void onPlayerDied(PlayerEvent.Clone event)
    {
        Player newPlayer = event.getPlayer();
        Player oldPlayer = event.getOriginal();
        if(event.isWasDeath())
        {
            int newCrystals = 0;
            for (AttributeModifier mods : TerraItemManaCrystal.CRYSTAL_UUIDS)
            {
                if (oldPlayer.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).hasModifier(mods))
                {
                    newCrystals++;
                    newPlayer.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).addPermanentModifier(mods);
                }
            }
            AtomicInteger atomicCrystals = new AtomicInteger(newCrystals);
            newPlayer.getCapability(TerraMana.TERRA_MANA_CAPABILITY).ifPresent(cap -> cap.setManaCrystalsUsed(atomicCrystals.get()));
        }
    }
}
