package com.anbaric.terra_reforged.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.List;
import java.util.Random;

public class TerraItemMagmaStone extends TerraItemAccessory
{
    public TerraItemMagmaStone(Properties properties)
    {
        super(properties);
        MinecraftForge.EVENT_BUS.addListener(this::setMeleeFire);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.format("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "Sets Melee Targets On Fire"));
    }

    private void setMeleeFire(LivingAttackEvent event)
    {
        Random       rand   = event.getEntityLiving().world.rand;
        LivingEntity victim = event.getEntityLiving();
        PlayerEntity player = event.getSource().getImmediateSource() instanceof PlayerEntity ? (PlayerEntity) event.getSource().getImmediateSource() : null;
        if (player == null)
        {
            return;
        }
        CuriosApi.getCuriosHelper().findEquippedCurio(this, event.getEntityLiving()).ifPresent(found -> {
            victim.setFire(rand.nextInt(5) + 2);
        });
    }
}
