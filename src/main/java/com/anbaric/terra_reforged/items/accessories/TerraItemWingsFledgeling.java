package com.anbaric.terra_reforged.items.accessories;

import com.anbaric.terra_reforged.items.ITerraWingItem;
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
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.caelus.api.CaelusApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TerraItemWingsFledgeling extends TerraItemAccessory implements ITerraWingItem
{
    private final int cooldown;
    public TerraItemWingsFledgeling(int boostCooldown)
    {
        super();
        this.cooldown = boostCooldown;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag unused)
    {
        return CurioHandler.createProvider(new ICurio()
        {
            private Object model;

            @Override
            public ItemStack getStack()
            {
                return stack;
            }

            @Override
            public boolean canEquip(SlotContext slotContext)
            {
                if (!(slotContext.entity() instanceof Player)) { return false; }
                return !CurioHandler.hasBauble((Player) slotContext.entity(), TerraTagRegistry.FLIGHT_GIVERS);
            }

            @Override
            public List<Component> getSlotsTooltip(List<Component> tooltips)
            {
                List<Component> toolTip = tooltips;
                toolTip.add(new TranslatableComponent(""));
                toolTip.add(new TranslatableComponent("curios.modifiers.curio").withStyle(ChatFormatting.GOLD));
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.wing_flight").withStyle(ChatFormatting.BLUE));
                return toolTip;
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
                atts.put(CaelusApi.getInstance().getFlightAttribute(), new AttributeModifier(uuid, Reference.MODID + ":wing_bonus", 1.0, AttributeModifier.Operation.ADDITION));
                return atts;
            }

            @Override
            public void curioTick(SlotContext slotContext)
            {
                LivingEntity livingEntity = slotContext.entity();
                int          ticks        = livingEntity.getFallFlyingTicks();

                if (ticks > 0 && livingEntity.isFallFlying())
                {
                    stack.elytraFlightTick(livingEntity, ticks);
                }

                CompoundTag compound = stack.getOrCreateTag();
                int cooldown = compound.getInt("boostCooldown");
                if (cooldown > 0)
                {
                    if (livingEntity.isOnGround())
                    {
                        compound.putInt("boostCooldown", 0);
                    }
                    else
                    {
                        compound.putInt("boostCooldown", cooldown - 1);
                    }
                }
                //TODO make fireworkRocketEntity with no trail attach it to player
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

    @Override
    public void setCooldown(ItemStack stack)
    {
        CompoundTag compound = stack.getOrCreateTag();
        compound.putInt("boostCooldown", wingCooldown());
    }

    @Override
    public int wingCooldown()
    {
        return cooldown;
    }
}
