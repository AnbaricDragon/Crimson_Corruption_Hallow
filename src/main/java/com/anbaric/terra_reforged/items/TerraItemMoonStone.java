package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

public class TerraItemMoonStone extends TerraItemAccessory
{
    AttributeModifier moonStoneAMAttribute = new AttributeModifier(UUID.fromString("b8eaddcd-4f3d-48f2-a616-a0b53e1ea12e"), Reference.MODID + ":moon_stone_AM_bonus", 4.0D, AttributeModifier.Operation.ADDITION);
    AttributeModifier moonStoneADAttribute = new AttributeModifier(UUID.fromString("3ad04f56-9b3d-4f6a-8f57-9ac88b26dbb7"), Reference.MODID + ":moon_stone_AD_bonus", 0.1D, AttributeModifier.Operation.MULTIPLY_BASE);
    AttributeModifier moonStoneASAttribute = new AttributeModifier(UUID.fromString("59e4e91c-5886-4014-b0dc-457691861d2d"), Reference.MODID + ":moon_stone_AS_bonus", 0.1D, AttributeModifier.Operation.MULTIPLY_BASE);
    AttributeModifier moonStoneKBAttribute = new AttributeModifier(UUID.fromString("7b74191b-ea0f-4c4a-b972-4016465b6916"), Reference.MODID + ":moon_stone_KB_bonus", 0.5D, AttributeModifier.Operation.MULTIPLY_BASE);

    public TerraItemMoonStone()
    {
        super();
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag unused)
    {
        return CurioHandler.createProvider(new ICurio()
        {
            @Override
            public ItemStack getStack()
            {
                return stack;
            }

            @Override
            public List<Component> getSlotsTooltip(List<Component> tooltips)
            {
                List<Component> toolTip = tooltips;
                toolTip.add(new TranslatableComponent(""));
                toolTip.add(new TranslatableComponent("curios.modifiers.curio").withStyle(ChatFormatting.GOLD));
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.condition_night").withStyle(ChatFormatting.YELLOW));
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.regen_health_medium").withStyle(ChatFormatting.BLUE));
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.extra_armor_4").withStyle(ChatFormatting.BLUE));
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.extra_damage_10").withStyle(ChatFormatting.BLUE));
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.extra_speed_attack_10").withStyle(ChatFormatting.BLUE));
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.extra_speed_mining_15").withStyle(ChatFormatting.BLUE));
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.extra_kick_5").withStyle(ChatFormatting.BLUE));
                return toolTip;
            }

            @Override
            public void curioTick(SlotContext slotContext)
            {
                Player player = slotContext.entity() instanceof Player ? (Player) slotContext.entity() : null;
                if (player != null)
                {
                    if (player.tickCount % 200 == 0 && player.getHealth() < player.getMaxHealth())
                    {
                        player.heal(1);
                    }
                    if (player.getLevel().isDay())
                    {
                        if (player.getAttribute(Attributes.ARMOR).hasModifier(moonStoneAMAttribute)) { player.getAttribute(Attributes.ARMOR).removeModifier(moonStoneAMAttribute); }
                        if (player.getAttribute(Attributes.ATTACK_DAMAGE).hasModifier(moonStoneADAttribute)) { player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(moonStoneADAttribute); }
                        if (player.getAttribute(Attributes.ATTACK_SPEED).hasModifier(moonStoneASAttribute)) { player.getAttribute(Attributes.ATTACK_SPEED).removeModifier(moonStoneASAttribute); }
                        if (player.getAttribute(Attributes.ATTACK_KNOCKBACK).hasModifier(moonStoneKBAttribute)) { player.getAttribute(Attributes.ATTACK_KNOCKBACK).removeModifier(moonStoneKBAttribute); }
                    }
                    else
                    {
                        if (!player.getAttribute(Attributes.ARMOR).hasModifier(moonStoneAMAttribute)) { player.getAttribute(Attributes.ARMOR).addTransientModifier(moonStoneAMAttribute); }
                        if (!player.getAttribute(Attributes.ATTACK_DAMAGE).hasModifier(moonStoneADAttribute)) { player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(moonStoneADAttribute); }
                        if (!player.getAttribute(Attributes.ATTACK_SPEED).hasModifier(moonStoneASAttribute)) { player.getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(moonStoneASAttribute); }
                        if (!player.getAttribute(Attributes.ATTACK_KNOCKBACK).hasModifier(moonStoneKBAttribute)) { player.getAttribute(Attributes.ATTACK_KNOCKBACK).addTransientModifier(moonStoneKBAttribute); }
                    }
                }
            }

            @Override
            public void onUnequip(SlotContext slotContext, ItemStack newStack)
            {
                LivingEntity player = slotContext.getWearer();
                if (player.getAttribute(Attributes.ARMOR).hasModifier(moonStoneAMAttribute)) { player.getAttribute(Attributes.ARMOR).removeModifier(moonStoneAMAttribute); }
                if (player.getAttribute(Attributes.ATTACK_DAMAGE).hasModifier(moonStoneADAttribute)) { player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(moonStoneADAttribute); }
                if (player.getAttribute(Attributes.ATTACK_SPEED).hasModifier(moonStoneASAttribute)) { player.getAttribute(Attributes.ATTACK_SPEED).removeModifier(moonStoneASAttribute); }
                if (player.getAttribute(Attributes.ATTACK_KNOCKBACK).hasModifier(moonStoneKBAttribute)) { player.getAttribute(Attributes.ATTACK_KNOCKBACK).removeModifier(moonStoneKBAttribute); }
            }

            @Nonnull
            @Override
            public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit)
            {
                return DropRule.DEFAULT;
            }

            @Nonnull
            @Override
            public SoundInfo getEquipSound(SlotContext slotContext)
            {
                return new SoundInfo(SoundEvents.ARMOR_EQUIP_GOLD, 1.0f, 1.0f);
            }

            @Override
            public boolean canEquipFromUse(SlotContext slot)
            {
                return true;
            }
        });
    }
}
