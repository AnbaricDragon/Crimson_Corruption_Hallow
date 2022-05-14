package com.anbaric.terra_reforged.items.armor;

import com.anbaric.terra_reforged.items.ITerraTooltip;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TerraItemHelmetDiving extends ArmorItem implements ITerraTooltip
{
    public TerraItemHelmetDiving(ArmorMaterial material, EquipmentSlot slot, Properties properties)
    {
        super(material, slot, properties);
    }

//    @Override
//    public int getDefaultTooltipHideFlags(@NotNull ItemStack stack)
//    {
//        return ItemStack.TooltipPart.ENCHANTMENTS.getMask();
//    }

    @Override
    public List<Component> getToolTip()
    {
        List<Component> toolTip = new ArrayList<>();
        toolTip.add(new TranslatableComponent("tooltip.terra_reforged.extra_air_1").withStyle(ChatFormatting.BLUE));
        return toolTip;
    }
}
