package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.capabilities.mana.TerraCapabilityPlayerMana;
import com.anbaric.terra_reforged.util.TerraItemGroups;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
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
        super(new Item.Properties().group(TerraItemGroups.TERRA_TOOLS_TAB).maxStackSize(64));
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
            player.getCapability(TerraCapabilityPlayerMana.PLAYER_MANA_CAPABILITY).ifPresent(cap ->
            {
                ItemStack stack = event.getItem().getItem();
                if (!stack.isEmpty() && stack.getItem() instanceof TerraItemConsumeableMana)
                {
                    int mana = cap.getCurrentMana();
                    int maxMana = (int) player.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).getValue();
                    cap.setCurrentMana(Math.min(mana + (stack.getCount() * 20), maxMana));
                    stack.shrink(stack.getCount());
                }
            });
        }
    }
}
