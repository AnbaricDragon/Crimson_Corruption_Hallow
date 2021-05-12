package com.anbaric.terra_reforged.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

public class TerraItemDivingHelmet extends ArmorItem
{
    public TerraItemDivingHelmet(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn)
    {
        super(materialIn, slot, builderIn);
    }

//    @Override
//    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
//    {
//        super.addInformation(stack, worldIn, tooltip, flagIn);
//        tooltip.add(new StringTextComponent(""));
//        tooltip.add(new StringTextComponent("\u00A76" + "When worn on head:" + "\u00A76"));
//        tooltip.add(new StringTextComponent("\u00A79" + "+100% Extra Air"));
//    }
}
