package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
//import me.hypherionmc.rgblib.api.ColoredLightManager;
//import me.hypherionmc.rgblib.api.RGBLight;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.ModList;

import java.util.List;

public class TerraItemJellyfishNecklace extends TerraItemAccessory
{
    public TerraItemJellyfishNecklace()
    {
        super();
        if (ModList.get().isLoaded("hypcore"))
        {
//            ColoredLightManager.registerProvider(this, this::shine);
        }
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.format("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "Glows"));
    }

//    public RGBLight shine(Entity entity, ItemStack stack)
//    {
//        PlayerEntity player = entity instanceof PlayerEntity ? (PlayerEntity) entity : null;
//        if (player == null) { return null; }
//
//        if (CurioHandler.hasBauble(player, this))
//        {
//            return RGBLight.builder().color(0.5f, 0f, 0.5f, player.isInWater() ? 1.0F : 5.0F).radius(player.isInWater() ? 15 : 5).build();
//        }
//        else return null;
//    }
}
