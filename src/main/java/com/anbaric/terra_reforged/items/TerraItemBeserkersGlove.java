package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

public class TerraItemBeserkersGlove extends TerraItemAccessory
{
    public TerraItemBeserkersGlove()
    {
        super();
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.format("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "Enemies May Target You"));
        tooltip.add(new StringTextComponent("\u00A79" + "+3 Armor"));
        tooltip.add(new StringTextComponent("\u00A79" + "+12% Attack Speed"));
        tooltip.add(new StringTextComponent("\u00A79" + "+100% Knockback"));

    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused)
    {
        return CurioHandler.createProvider(new ICurio()
        {
            @Override
            public boolean showAttributesTooltip(String identifier)
            {
                return false;
            }

            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity)
            {
                if (livingEntity.ticksExisted % 100 == 0 )
                {
                    AxisAlignedBB rangeAABB = new AxisAlignedBB(livingEntity.getPosition()).grow(20);
                    List<MobEntity> mobList = livingEntity.world.getEntitiesWithinAABB(MobEntity.class, rangeAABB);
                    for (MobEntity mob : mobList)
                    {
                        if (livingEntity.world.rand.nextFloat() > 0.6)
                        {
                            mob.setAttackTarget(livingEntity);
                        }
                    }
                }
            }

            public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid)
            {
                Multimap<Attribute, AttributeModifier> atts = LinkedHashMultimap.create();
                atts.put(Attributes.ARMOR, new AttributeModifier(uuid, Reference.MODID + ":beserkerGloveBonus", 3.0, AttributeModifier.Operation.ADDITION));
                atts.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(uuid, Reference.MODID + ":beserkerGloveBonus", 2.0, AttributeModifier.Operation.MULTIPLY_TOTAL));
                atts.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, Reference.MODID + ":beserkerGloveBonus", 0.12, AttributeModifier.Operation.MULTIPLY_BASE));
                return atts;
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
