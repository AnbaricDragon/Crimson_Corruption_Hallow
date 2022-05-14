package com.anbaric.terra_reforged.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface ITerraTooltip
{
    List<Component> getToolTip();
}
