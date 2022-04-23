package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TerraItemBootsDunerider extends TerraItemAccessory
{
    AttributeModifier duneriderAttribute = new AttributeModifier(UUID.fromString("f3da7a13-d2ab-48c7-9c3f-68da0608f172"), Reference.MODID + ":duneriderBonus", 0.5D, AttributeModifier.Operation.MULTIPLY_BASE);

    public TerraItemBootsDunerider()
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
            public boolean canEquip(SlotContext slotContext)
            {
                if (!(slotContext.entity() instanceof Player)) { return false; }
                return !CurioHandler.hasBauble((Player) slotContext.entity(), TerraTagRegistry.BOOT_SPEEDERS);
            }

            @Override
            public List<Component> getSlotsTooltip(List<Component> tooltips)
            {
                List<Component> toolTip = tooltips;
                toolTip.add(new TranslatableComponent(""));
                toolTip.add(new TranslatableComponent("curios.modifiers.curio").withStyle(ChatFormatting.GOLD));
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.extra_speed_50").withStyle(ChatFormatting.BLUE).append(new TranslatableComponent("tooltip.terra_reforged.condition_sand").withStyle(ChatFormatting.BLUE)));
                return toolTip;
            }

            @Override
            public void curioTick(SlotContext slotContext)
            {
                Player player = slotContext.entity() instanceof Player ? (Player) slotContext.entity() : null;
                if (player == null)
                {
                    return;
                }
                Level world = player.getLevel();
                player.maxUpStep = 0.6F;
                if (!world.getBlockState(player.getOnPos().below()).is(BlockTags.SAND) && !(world.getBlockState(player.getOnPos().below()).getBlock() == Blocks.AIR && world.getBlockState(player.getOnPos().below(2)).is(BlockTags.SAND)))
                {
                    if (player.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(duneriderAttribute))
                    {
                        player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(duneriderAttribute);
                    }
                }
                else
                {
                    player.maxUpStep = 1.25F;
                    if (!player.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(duneriderAttribute))
                    {
                        player.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(duneriderAttribute);
                    }
                }
            }

            @Override
            public void onUnequip(SlotContext slotContext, ItemStack newStack)
            {
                slotContext.getWearer().maxUpStep = 0.6F;
                if (slotContext.getWearer().getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(duneriderAttribute))
                {
                    slotContext.getWearer().getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(duneriderAttribute);
                }
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
