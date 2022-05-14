package com.anbaric.terra_reforged.items.accessories;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class TerraItemEffectNegator extends TerraItemAccessory
{
    public static final Component[] EFFECT_COMPONENTS =
    {
            new TranslatableComponent("tooltip.terra_reforged.immunity_bleeding").withStyle(ChatFormatting.DARK_RED),
            new TranslatableComponent("tooltip.terra_reforged.immunity_blindness").withStyle(ChatFormatting.DARK_RED),
            new TranslatableComponent("tooltip.terra_reforged.immunity_broken_armor").withStyle(ChatFormatting.DARK_RED),
            new TranslatableComponent("tooltip.terra_reforged.immunity_confusion").withStyle(ChatFormatting.DARK_RED),
            new TranslatableComponent("tooltip.terra_reforged.immunity_cursed").withStyle(ChatFormatting.DARK_RED),
            new TranslatableComponent("tooltip.terra_reforged.immunity_poison").withStyle(ChatFormatting.DARK_RED),
            new TranslatableComponent("tooltip.terra_reforged.immunity_silenced").withStyle(ChatFormatting.DARK_RED),
            new TranslatableComponent("tooltip.terra_reforged.immunity_slow").withStyle(ChatFormatting.DARK_RED),
            new TranslatableComponent("tooltip.terra_reforged.immunity_weak").withStyle(ChatFormatting.DARK_RED)
    };

    private List<Integer> effectIndex;

    public TerraItemEffectNegator(int... effects)
    {
        super();
        effectIndex = new ArrayList<>();
        for (Integer index : effects)
        {
            this.effectIndex.add(index);
        }
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
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.immunity_grant").withStyle(ChatFormatting.BLUE));
                for (Integer index : effectIndex)
                {
                    toolTip.add(EFFECT_COMPONENTS[index]);
                }
                return toolTip;
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
