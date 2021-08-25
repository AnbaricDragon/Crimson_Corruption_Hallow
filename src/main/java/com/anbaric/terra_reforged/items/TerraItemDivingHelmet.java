package com.anbaric.terra_reforged.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

import net.minecraft.item.Item.Properties;

public class TerraItemDivingHelmet extends ArmorItem
{
    public TerraItemDivingHelmet(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn)
    {
        super(materialIn, slot, builderIn);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        tooltip.clear();

        tooltip.add(new StringTextComponent("Diving Helmet"));
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + "When on head:" + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "+2 Armor"));
        tooltip.add(new StringTextComponent("\u00A79" + "+100% Extra Air"));
    }
}
