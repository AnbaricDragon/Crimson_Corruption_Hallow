package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.item.Item.Properties;
import top.theillusivec4.curios.api.type.capability.ICurio.DropRule;
import top.theillusivec4.curios.api.type.capability.ICurio.SoundInfo;

public class TerraItemBootsDunerider extends TerraItemAccessory
{
    AttributeModifier duneriderAttribute = new AttributeModifier(UUID.fromString("f3da7a13-d2ab-48c7-9c3f-68da0608f172"), Reference.MODID + ":duneriderBonus", 0.5D, AttributeModifier.Operation.MULTIPLY_BASE);

    public TerraItemBootsDunerider(Properties properties)
    {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.format("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "+50% Speed While On Sand"));
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
                player.stepHeight = 0.6F;

                if (!world.getBlockState(player.getPosition().down()).isIn(BlockTags.SAND) && !(world.getBlockState(player.getPosition().down()).getBlock() == Blocks.AIR && world.getBlockState(player.getPosition().down(2)).isIn(BlockTags.SAND)))
                {
                    if (player.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(duneriderAttribute))
                    {
                        player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(duneriderAttribute);
                    }
                }
                else
                {
                    player.stepHeight = 1.25F;
                    if (!player.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(duneriderAttribute))
                    {
                        player.getAttribute(Attributes.MOVEMENT_SPEED).applyNonPersistentModifier(duneriderAttribute);
                    }
                }
            }

            @Override
            public void onUnequip(SlotContext slotContext, ItemStack newStack)
            {
                slotContext.getWearer().stepHeight = 0.6F;
                if (slotContext.getWearer().getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(duneriderAttribute))
                {
                    slotContext.getWearer().getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(duneriderAttribute);
                }
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
