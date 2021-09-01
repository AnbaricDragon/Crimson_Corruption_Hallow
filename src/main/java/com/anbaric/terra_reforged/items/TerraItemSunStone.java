package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import net.minecraft.block.Blocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

public class TerraItemSunStone extends TerraItemAccessory
{
    AttributeModifier sunStoneAMAttribute = new AttributeModifier(UUID.fromString("b8eaddcd-4f3d-48f2-a616-a0b53e1ea12e"), Reference.MODID + ":sunStoneAMBonus", 4.0D, AttributeModifier.Operation.ADDITION);
    AttributeModifier sunStoneADAttribute = new AttributeModifier(UUID.fromString("3ad04f56-9b3d-4f6a-8f57-9ac88b26dbb7"), Reference.MODID + ":sunStoneADBonus", 0.1D, AttributeModifier.Operation.MULTIPLY_BASE);
    AttributeModifier sunStoneASAttribute = new AttributeModifier(UUID.fromString("59e4e91c-5886-4014-b0dc-457691861d2d"), Reference.MODID + ":sunStoneASBonus", 0.1D, AttributeModifier.Operation.MULTIPLY_BASE);
    AttributeModifier sunStoneKBAttribute = new AttributeModifier(UUID.fromString("7b74191b-ea0f-4c4a-b972-4016465b6916"), Reference.MODID + ":sunStoneKBBonus", 0.5D, AttributeModifier.Operation.MULTIPLY_BASE);

    public TerraItemSunStone()
    {
        super();
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.format("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A76" + "During the daytime:" + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "Gives Health Regen"));
        tooltip.add(new StringTextComponent("\u00A79" + "+4 Armor"));
        tooltip.add(new StringTextComponent("\u00A79" + "+10% Damage"));
        tooltip.add(new StringTextComponent("\u00A79" + "+10% Attack Speed"));
        tooltip.add(new StringTextComponent("\u00A79" + "+15% Mining Speed"));
        tooltip.add(new StringTextComponent("\u00A79" + "+50% Knockback"));
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused)
    {
        return CurioHandler.createProvider(new ICurio()
        {
            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity)
            {
                PlayerEntity player = livingEntity != null ? (PlayerEntity) livingEntity : null;
                if (player == null)
                {
                    return;
                }
                World world = player.getEntityWorld();
                if (!world.isDaytime())
                {
                    if (player.getAttribute(Attributes.ARMOR).hasModifier(sunStoneAMAttribute)) { player.getAttribute(Attributes.ARMOR).removeModifier(sunStoneAMAttribute); }
                    if (player.getAttribute(Attributes.ATTACK_DAMAGE).hasModifier(sunStoneADAttribute)) { player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(sunStoneADAttribute); }
                    if (player.getAttribute(Attributes.ATTACK_SPEED).hasModifier(sunStoneASAttribute)) { player.getAttribute(Attributes.ATTACK_SPEED).removeModifier(sunStoneASAttribute); }
                    if (player.getAttribute(Attributes.ATTACK_KNOCKBACK).hasModifier(sunStoneKBAttribute)) { player.getAttribute(Attributes.ATTACK_KNOCKBACK).removeModifier(sunStoneKBAttribute); }
                }
                else
                {
                    if (!player.getAttribute(Attributes.ARMOR).hasModifier(sunStoneAMAttribute)) { player.getAttribute(Attributes.ARMOR).applyNonPersistentModifier(sunStoneAMAttribute); }
                    if (!player.getAttribute(Attributes.ATTACK_DAMAGE).hasModifier(sunStoneADAttribute)) { player.getAttribute(Attributes.ATTACK_DAMAGE).applyNonPersistentModifier(sunStoneADAttribute); }
                    if (!player.getAttribute(Attributes.ATTACK_SPEED).hasModifier(sunStoneASAttribute)) { player.getAttribute(Attributes.ATTACK_SPEED).applyNonPersistentModifier(sunStoneASAttribute); }
                    if (!player.getAttribute(Attributes.ATTACK_KNOCKBACK).hasModifier(sunStoneKBAttribute)) { player.getAttribute(Attributes.ATTACK_KNOCKBACK).applyNonPersistentModifier(sunStoneKBAttribute); }
                }
            }

            @Override
            public void onUnequip(SlotContext slotContext, ItemStack newStack)
            {
                LivingEntity player = slotContext.getWearer();
                if (player.getAttribute(Attributes.ARMOR).hasModifier(sunStoneAMAttribute)) { player.getAttribute(Attributes.ARMOR).removeModifier(sunStoneAMAttribute); }
                if (player.getAttribute(Attributes.ATTACK_DAMAGE).hasModifier(sunStoneADAttribute)) { player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(sunStoneADAttribute); }
                if (player.getAttribute(Attributes.ATTACK_SPEED).hasModifier(sunStoneASAttribute)) { player.getAttribute(Attributes.ATTACK_SPEED).removeModifier(sunStoneASAttribute); }
                if (player.getAttribute(Attributes.ATTACK_KNOCKBACK).hasModifier(sunStoneKBAttribute)) { player.getAttribute(Attributes.ATTACK_KNOCKBACK).removeModifier(sunStoneKBAttribute); }
            }

            @Nonnull
            @Override
            public DropRule getDropRule(LivingEntity livingEntity)
            {
                return DropRule.DEFAULT;
            }

            @Nonnull
            @Override
            public SoundInfo getEquipSound(SlotContext slotContext)
            {
                return new SoundInfo(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1.0f, 1.0f);
            }

            @Override
            public boolean canEquipFromUse(SlotContext slot)
            {
                return true;
            }
        });
    }
}
