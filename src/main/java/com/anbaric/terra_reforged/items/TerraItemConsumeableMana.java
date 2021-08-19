package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.capabilities.mana.TerraCapabilityPlayerMana;
import com.anbaric.terra_reforged.util.TerraItemGroups;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class TerraItemConsumeableMana extends Item
{
    public TerraItemConsumeableMana()
    {
        super(new Item.Properties().tab(TerraItemGroups.TERRA_TOOLS_TAB).stacksTo(64));
        MinecraftForge.EVENT_BUS.addListener(this::onPickUpItem);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context)
    {
        context.getPlayer().getCapability(TerraCapabilityPlayerMana.PLAYER_MANA_CAPABILITY).ifPresent(cap -> cap.setCurrentMana(0));
        return ActionResultType.SUCCESS;
    }

    private void onPickUpItem(EntityItemPickupEvent event)
    {
        if (event.getEntityLiving() instanceof PlayerEntity )
        {
            PlayerEntity player = (PlayerEntity)event.getEntityLiving();
            System.out.println(player.getDisplayName() + " picked up Item " + event.getItem().getItem().getItem());
            player.getCapability(TerraCapabilityPlayerMana.PLAYER_MANA_CAPABILITY).ifPresent(cap ->
            {
                ItemStack stack = event.getItem().getItem();
                if (!stack.isEmpty() && stack.getItem() instanceof TerraItemConsumeableMana)
                {
                    int newMana = cap.getCurrentMana() + (20 * stack.getCount());
                    System.out.println("Player has " + cap.getCurrentMana() + " mana, and should soon have " + newMana + " mana");

                    cap.setCurrentMana(newMana);
                    stack.shrink(stack.getCount());
                }
            });
        }
    }
}
