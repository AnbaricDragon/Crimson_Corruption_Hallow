package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.items.ITerraTooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraTooltipEvent
{
    @SubscribeEvent
    static void changeTooltips(ItemTooltipEvent event)
    {
        ItemStack stack = event.getItemStack();
        if (stack.getItem() instanceof ITerraTooltip)
        {
            ITerraTooltip terraTooltip = (ITerraTooltip) stack.getItem();
            for (Component line : terraTooltip.getToolTip())
            {
                event.getToolTip().add(line);
            }
        }
    }
}

