package com.anbaric.terra_reforged.items.accessories;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.UUID;

public class TerraItemBootsSpeed extends TerraItemAccessory
{
    private float moveSpeed;

    public TerraItemBootsSpeed(float moveSpeed)
    {
        super();
        this.moveSpeed = moveSpeed;
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
            public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid)
            {
                Multimap<Attribute, AttributeModifier> atts = HashMultimap.create();
                atts.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, Reference.MODID + ":boot_speed_bonus", moveSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
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
