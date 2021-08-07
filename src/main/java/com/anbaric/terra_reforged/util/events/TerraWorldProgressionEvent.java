package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.hardmode.TerraCapabilityWorldProgression;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraWorldProgressionEvent
{
    @SubscribeEvent
    static void onBreakAltar(BlockEvent.BreakEvent event)
    {
        Block block = event.getState().getBlock();
        //TODO make Demon Alter
        if (block == TerraBlockRegistry.ORE_CHLOROPHYTE.get())
        {
            event.getPlayer().level.getCapability(TerraCapabilityWorldProgression.WORLD_PROGRESSION).ifPresent(cap -> {
                cap.setAltarsBroken(cap.getAltarsBroken() + 1);
                System.out.println(cap.getAltarsBroken());
            });
        }
    }
}