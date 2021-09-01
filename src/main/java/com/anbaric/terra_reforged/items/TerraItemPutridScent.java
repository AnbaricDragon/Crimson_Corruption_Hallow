package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.NBTUtil;
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

public class TerraItemPutridScent extends TerraItemAccessory
{
    public TerraItemPutridScent()
    {
        super();
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.format("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "Enemies May Overlook You"));
        tooltip.add(new StringTextComponent("\u00A79" + "+5% Damage"));
        tooltip.add(new StringTextComponent("\u00A79" + "+5% Crit Chance"));

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
                CompoundNBT compound = stack.getOrCreateTag();
                AxisAlignedBB rangeAABB = new AxisAlignedBB(livingEntity.getPosition()).grow(16);
                List<MobEntity> mobList = livingEntity.world.getEntitiesWithinAABB(MobEntity.class, rangeAABB);
                ListNBT nbtList = compound.getList("terraMobList", 11);
                for (MobEntity mob : mobList)
                {
                    for (INBT inbt : nbtList)
                    {
                        UUID listUUID = NBTUtil.readUniqueId(inbt);
                        if (mob.getUniqueID().equals(listUUID))
                        {
                            mob.setAttackTarget(null);
                        }
                    }
                }
                if (livingEntity.ticksExisted % 200 == 0)
                {
                    compound.remove("terraMobList");
                    ListNBT newList = new ListNBT();
                    for (MobEntity mob : mobList)
                    {
                        if (livingEntity.world.rand.nextFloat() > (livingEntity.isCrouching() ? 0.4 : 0.8))
                        {
                            newList.add(NBTUtil.func_240626_a_(mob.getUniqueID()));
                        }
                    }
                    compound.put("terraMobList", newList);
                }
            }

            public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid)
            {
                Multimap<Attribute, AttributeModifier> atts = LinkedHashMultimap.create();
                atts.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, Reference.MODID + ":putridScentBonus", 0.05, AttributeModifier.Operation.MULTIPLY_BASE));
                atts.put(TerraAttributeRegistry.RANGED_DAMAGE.get(), new AttributeModifier(uuid, Reference.MODID + ":putridScentBonus", 0.05, AttributeModifier.Operation.MULTIPLY_BASE));
                atts.put(TerraAttributeRegistry.MINION_DAMAGE.get(), new AttributeModifier(uuid, Reference.MODID + ":putridScentBonus", 0.05, AttributeModifier.Operation.MULTIPLY_BASE));
                atts.put(TerraAttributeRegistry.MAGIC_DAMAGE.get(), new AttributeModifier(uuid, Reference.MODID + ":putridScentBonus", 0.05, AttributeModifier.Operation.MULTIPLY_BASE));
                atts.put(TerraAttributeRegistry.CRIT_CHANCE.get(), new AttributeModifier(uuid, Reference.MODID + ":putridScentBonus", 0.05, AttributeModifier.Operation.ADDITION));
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
