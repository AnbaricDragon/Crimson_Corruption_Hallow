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

import top.theillusivec4.curios.api.type.capability.ICurio.DropRule;
import top.theillusivec4.curios.api.type.capability.ICurio.SoundInfo;

public class TerraItemAnkhCharm extends TerraItemAccessory
{
    public TerraItemAnkhCharm()
    {
        super();
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.get("curios.modifiers.charm") + "\u00A76"));
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
                if (livingEntity.hasEffect(Effects.MOVEMENT_SLOWDOWN))
                {
                    livingEntity.removeEffect(Effects.MOVEMENT_SLOWDOWN);
                }
                if (livingEntity.hasEffect(TerraEffectRegistry.CONFUSION.get()))
                {
                    livingEntity.removeEffect(TerraEffectRegistry.CONFUSION.get());
                }
                if (livingEntity.hasEffect(Effects.BLINDNESS))
                {
                    livingEntity.removeEffect(Effects.BLINDNESS);
                }
                if (livingEntity.hasEffect(Effects.WEAKNESS))
                {
                    livingEntity.removeEffect(Effects.WEAKNESS);
                }
                if (livingEntity.hasEffect(TerraEffectRegistry.BROKEN_ARMOR.get()))
                {
                    livingEntity.removeEffect(TerraEffectRegistry.BROKEN_ARMOR.get());
                }
                if (livingEntity.hasEffect(TerraEffectRegistry.BLEEDING.get()))
                {
                    livingEntity.removeEffect(TerraEffectRegistry.BLEEDING.get());
                }
                if (livingEntity.hasEffect(Effects.POISON))
                {
                    livingEntity.removeEffect(Effects.POISON);
                }
                if (livingEntity.hasEffect(TerraEffectRegistry.CURSED.get()))
                {
                    livingEntity.removeEffect(TerraEffectRegistry.CURSED.get());
                }
                if (livingEntity.hasEffect(TerraEffectRegistry.SILENCED.get()))
                {
                    livingEntity.removeEffect(TerraEffectRegistry.SILENCED.get());
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
                return new SoundInfo(SoundEvents.ARMOR_EQUIP_GENERIC, 1.0f, 1.0f);
            }

            @Override
            public boolean canEquipFromUse(SlotContext slot)
            {
                return true;
            }
        });
    }
}
