package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.handlers.NBTHandler;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class TerraMagnetItemEvent
{
    @SubscribeEvent
    static void onTossItem(ItemTossEvent event)
    {
        Player player = event.getPlayer();
        List<ItemStack> manaMagnets = CurioHandler.getAllStacks(player, TerraTagRegistry.MANA_BRINGERS);

        if (!manaMagnets.isEmpty())
        {
            for (ItemStack item : manaMagnets)
            {
                NBTHandler.setCooldown(item, 100);
            }
        }
    }
}
