package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.hardmode.TerraCapabilityWorldProgression;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraTestEvent
{
    @SubscribeEvent
    static void onBreakAltar(BlockEvent.BreakEvent event)
    {
        Block block = event.getState().getBlock();
        if (block == TerraBlockRegistry.ORE_CHLOROPHYTE.get())
        {
            System.out.println("Breaking Chlorophyte");
            event.getPlayer().world.getCapability(TerraCapabilityWorldProgression.WORLD_PROGRESSION_CAPABILITY).ifPresent
            (
                cap -> {
                    cap.setAltarsBroken(cap.getAltarsBroken() + 1);
                    System.out.println("There are " + cap.getAltarsBroken() + " altars broken");
                }
            );
        }
    }
}
