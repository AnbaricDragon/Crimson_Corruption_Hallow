package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.List;

public class TerraItemFrogLeg extends TerraItemAccessory
{
    public TerraItemFrogLeg()
    {
        super();
        MinecraftForge.EVENT_BUS.addListener(this::cancelFallDamage);
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.get("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "-3 Block Fall Damage"));
        tooltip.add(new StringTextComponent("\u00A79" + "+50% Jump Height"));
    }

    private void cancelFallDamage(LivingFallEvent event)
    {
        PlayerEntity player = event.getEntityLiving() instanceof PlayerEntity ? (PlayerEntity) event.getEntityLiving() : null;
        if (player == null) { return; }

        CuriosApi.getCuriosHelper().getCuriosHandler(player).map(ICuriosItemHandler::getCurios).map(map -> map.get("curio")).map(ICurioStacksHandler::getStacks).map(dynamicStackHandler ->
        {
            for (int i = 0; i < dynamicStackHandler.getSlots(); i++)
            {
                ItemStack stack = dynamicStackHandler.getStackInSlot(i);
                if (stack.getItem().is(TerraTagRegistry.FROG_BREAKERS))
                {
                    event.setDistance(event.getDistance() - 2);
                }
            }
            return null;
        });
    }
}