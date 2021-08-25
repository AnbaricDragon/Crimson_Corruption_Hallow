package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.mana.TerraCapabilityPlayerMana;
import com.anbaric.terra_reforged.items.TerraItemManaCrystal;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.concurrent.atomic.AtomicInteger;

public class TerraPlayerChangedEvent
{
//    @SubscribeEvent
//    static void onPlayerJoinedWorld(PlayerEvent.PlayerLoggedInEvent event)
//    {
//        PlayerEntity player = event.getPlayer();
//        player.getCapability(TerraCapabilityPlayerMana.PLAYER_MANA_CAPABILITY).ifPresent(cap ->
//        {
//            int manaCrystalsUsed = 0;
//            for (AttributeModifier mods : TerraItemManaCrystal.CRYSTAL_UUIDS)
//            {
//                if (player.getAttributes().hasModifier(TerraAttributeRegistry.MANA_MAX.get(), mods.getId()))
//                {
//                    manaCrystalsUsed++;
//                }
//            }
//            cap.setManaCrystalsUsed(manaCrystalsUsed);
//        });
//    }

    @SubscribeEvent
    static void onPlayerDied(PlayerEvent.Clone event)
    {
        PlayerEntity newPlayer = event.getPlayer();
        PlayerEntity oldPlayer = event.getOriginal();
        if(event.isWasDeath())
        {
            int newCrystals = 0;
            for (AttributeModifier mods : TerraItemManaCrystal.CRYSTAL_UUIDS)
            {
                if (oldPlayer.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).hasModifier(mods))
                {
                    newCrystals++;
                    newPlayer.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).applyPersistentModifier(mods);
                }
            }
            AtomicInteger atomicCrystals = new AtomicInteger(newCrystals);
            newPlayer.getCapability(TerraCapabilityPlayerMana.PLAYER_MANA_CAPABILITY).ifPresent(cap -> cap.setManaCrystalsUsed(atomicCrystals.get()));
        }
    }
}
