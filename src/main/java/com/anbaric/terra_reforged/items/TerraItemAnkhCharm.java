package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.List;

public class TerraItemAnkhCharm extends TerraItemAccessory
{
    public TerraItemAnkhCharm()
    {
        super();
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.format("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "Grants Immunity To:"));
        tooltip.add(new StringTextComponent("\u00A74" + "Confusion"));
        tooltip.add(new StringTextComponent("\u00A74" + "Slowness"));
        tooltip.add(new StringTextComponent("\u00A74" + "Weakness"));
        tooltip.add(new StringTextComponent("\u00A74" + "Broken Armor"));
        tooltip.add(new StringTextComponent("\u00A74" + "Bleeding"));
        tooltip.add(new StringTextComponent("\u00A74" + "Poison"));
        tooltip.add(new StringTextComponent("\u00A74" + "Curse"));
        tooltip.add(new StringTextComponent("\u00A74" + "Silence"));
        tooltip.add(new StringTextComponent("\u00A74" + "Blindness"));

    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused)
    {
        return CurioHandler.createProvider(new ICurio()
        {
            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity)
            {
                if (livingEntity.isPotionActive(Effects.SLOWNESS))
                {
                    livingEntity.removePotionEffect(Effects.SLOWNESS);
                }
                if (livingEntity.isPotionActive(TerraEffectRegistry.CONFUSION.get()))
                {
                    livingEntity.removePotionEffect(TerraEffectRegistry.CONFUSION.get());
                }
                if (livingEntity.isPotionActive(Effects.BLINDNESS))
                {
                    livingEntity.removePotionEffect(Effects.BLINDNESS);
                }
                if (livingEntity.isPotionActive(Effects.WEAKNESS))
                {
                    livingEntity.removePotionEffect(Effects.WEAKNESS);
                }
                if (livingEntity.isPotionActive(TerraEffectRegistry.BROKEN_ARMOR.get()))
                {
                    livingEntity.removePotionEffect(TerraEffectRegistry.BROKEN_ARMOR.get());
                }
                if (livingEntity.isPotionActive(TerraEffectRegistry.BLEEDING.get()))
                {
                    livingEntity.removePotionEffect(TerraEffectRegistry.BLEEDING.get());
                }
                if (livingEntity.isPotionActive(Effects.POISON))
                {
                    livingEntity.removePotionEffect(Effects.POISON);
                }
                if (livingEntity.isPotionActive(TerraEffectRegistry.CURSED.get()))
                {
                    livingEntity.removePotionEffect(TerraEffectRegistry.CURSED.get());
                }
                if (livingEntity.isPotionActive(TerraEffectRegistry.SILENCED.get()))
                {
                    livingEntity.removePotionEffect(TerraEffectRegistry.SILENCED.get());
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