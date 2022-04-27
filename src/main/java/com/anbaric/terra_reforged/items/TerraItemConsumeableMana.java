package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.capabilities.PlayerMana.PlayerMana;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class TerraItemConsumeableMana extends Item
{
    public TerraItemConsumeableMana()
    {
        super(new Item.Properties().tab(TerraReforged.TERRA_TOOLS_TAB).stacksTo(64));
        MinecraftForge.EVENT_BUS.addListener(this::onPickUpItem);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context)
    {
        context.getPlayer().getCapability(PlayerMana.TERRA_MANA_CAPABILITY).ifPresent(cap -> {
            System.out.println("REACHED CAPABILITY");
            cap.setCurrentMana(0);
        });
        return InteractionResult.SUCCESS;
    }

    private void onPickUpItem(EntityItemPickupEvent event)
    {
        if (event.getEntityLiving() instanceof Player)
        {
            Player player = (Player) event.getEntityLiving();
            player.getCapability(PlayerMana.TERRA_MANA_CAPABILITY).ifPresent(cap -> {
                ItemStack stack = event.getItem().getItem();
                if (!stack.isEmpty() && stack.getItem() instanceof TerraItemConsumeableMana)
                {
                    int mana    = cap.getCurrentMana();
                    System.out.println("Player's mana is " + mana + " before using item");
                    int maxMana = (int) player.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).getValue();
                    cap.setCurrentMana(Math.min(mana + (stack.getCount() * 20), maxMana));
                    System.out.println("Player's mana is " + cap.getCurrentMana() + " after using item");
                    stack.shrink(stack.getCount());
                }
            });
        }
    }
}