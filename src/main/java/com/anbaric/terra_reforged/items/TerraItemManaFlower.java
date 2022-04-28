package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.capabilities.player_mana.TerraMana;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.ThrowablePotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TerraItemManaFlower extends TerraItemAccessory
{
    public TerraItemManaFlower()
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
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.potion_mana_use").withStyle(ChatFormatting.BLUE).append(new TranslatableComponent("tooltip.terra_reforged.condition_mana").withStyle(ChatFormatting.BLUE)));
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.mana_discount_8").withStyle(ChatFormatting.BLUE));
                return toolTip;
            }

            @Override
            public void curioTick(SlotContext slotContext)
            {
                LivingEntity livingEntity = slotContext.entity();
                Player player = livingEntity instanceof Player ? (Player) livingEntity : null;
                if (player != null)
                {
                    player.getCapability(TerraMana.TERRA_MANA_CAPABILITY).ifPresent(cap -> {
                        int mana    = cap.getCurrentMana();
                        int maxMana = (int) player.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).getValue();
                        if (mana <= maxMana / 10)
                        {
                            int maxFoundPotSlot = -1;
                            int maxFoundAmp     = -1;

                            for (int slot = 0; slot < player.getInventory().getContainerSize(); slot++)
                            {
                                ItemStack stackInSlot = player.getInventory().getItem(slot);
                                if (stackInSlot.getItem() instanceof PotionItem && !(stackInSlot.getItem() instanceof ThrowablePotionItem))
                                {
                                    for (MobEffectInstance effect : PotionUtils.getPotion(stackInSlot).getEffects())
                                    {
                                        if (effect.getEffect() == TerraEffectRegistry.MANA_BURST.get())
                                        {
                                            int tempnumber = effect.getAmplifier();
                                            if (tempnumber > maxFoundAmp)
                                            {
                                                maxFoundAmp = tempnumber;
                                                maxFoundPotSlot = slot;
                                            }
                                        }
                                    }
                                }
                            }
                            if (maxFoundAmp > -1)
                            {
                                Potion potion = PotionUtils.getPotion(player.getInventory().getItem(maxFoundPotSlot));
                                for (MobEffectInstance effect : potion.getEffects())
                                {
                                    effect.applyEffect(player);
                                }
                                player.getInventory().getItem(maxFoundPotSlot).shrink(1);
                            }
                        }
                    });
                }
            }

            @Override
            public List<Component> getAttributesTooltip(List<Component> tooltips)
            {
                return new ArrayList<>();
            }

            @Override
            public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid)
            {
                Multimap<Attribute, AttributeModifier> atts = HashMultimap.create();
                atts.put(TerraAttributeRegistry.MANA_DISCOUNT.get(), new AttributeModifier(uuid, Reference.MODID + ":mana_flower_bonus", 0.08F, AttributeModifier.Operation.ADDITION));
                return atts;
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
