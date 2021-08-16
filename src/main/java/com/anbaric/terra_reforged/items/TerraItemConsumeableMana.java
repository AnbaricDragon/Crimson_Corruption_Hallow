package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.capabilities.player.TerraCapabilityPlayerMana;
import com.anbaric.terra_reforged.util.TerraItemGroups;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;

import java.util.List;

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
        context.getPlayer().getCapability(TerraCapabilityPlayerMana.PLAYER_MANA).ifPresent(cap -> cap.setCurrentMana(0));
        return ActionResultType.SUCCESS;
    }

    public void onPickUpItem(PlayerEvent.ItemPickupEvent event)
    {
        System.out.println("Found Pick Up Item Event");
        PlayerEntity player = event.getPlayer();
        ItemStack stack = event.getStack();
        if (stack.getItem() == this)
        {
            player.getCapability(TerraCapabilityPlayerMana.PLAYER_MANA).ifPresent(cap ->
            {
                int manaRecovered = stack.getCount() * 20;
                int maxMana = (int) player.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).getValue();
                int currentMana = cap.getCurrentMana();
                if (currentMana + manaRecovered > maxMana)
                {
                    cap.setCurrentMana(currentMana + manaRecovered);
                }
                else
                {
                    cap.setCurrentMana(maxMana);
                }
                stack.shrink(stack.getCount());
            });
        }

    }
}
