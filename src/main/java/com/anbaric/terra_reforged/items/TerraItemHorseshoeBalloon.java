package com.anbaric.terra_reforged.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.List;

public class TerraItemHorseshoeBalloon extends TerraItemAccessory
{
    public TerraItemHorseshoeBalloon()
    {
        super();
        MinecraftForge.EVENT_BUS.addListener(this::cancelFallDamage);
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.get("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "+1 Extra Jump"));
        tooltip.add(new StringTextComponent("\u00A79" + "+50% Jump Height"));
        tooltip.add(new StringTextComponent("\u00A79" + "-100% Fall Damage"));
    }

    private void cancelFallDamage(LivingFallEvent event)
    {
        CuriosApi.getCuriosHelper().findEquippedCurio(this, event.getEntityLiving()).ifPresent(found -> event.setCanceled(true));
    }
}
