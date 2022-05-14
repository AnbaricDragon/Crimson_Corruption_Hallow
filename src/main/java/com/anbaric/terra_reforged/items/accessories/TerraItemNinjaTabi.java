package com.anbaric.terra_reforged.items.accessories;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.List;

public class TerraItemNinjaTabi extends TerraItemAccessory
{
    public TerraItemNinjaTabi()
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
                return !CurioHandler.hasBauble((Player) slotContext.entity(), TerraTagRegistry.TABI_DASHERS);
            }

            @Override
            public List<Component> getSlotsTooltip(List<Component> tooltips)
            {
                List<Component> toolTip = tooltips;
                toolTip.add(new TranslatableComponent(""));
                toolTip.add(new TranslatableComponent("curios.modifiers.curio").withStyle(ChatFormatting.GOLD));
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.extra_dash").withStyle(ChatFormatting.BLUE));
                return toolTip;
            }

            @Override
            public void curioTick(SlotContext slotContext)
            {
                if (slotContext.entity() instanceof Player && slotContext.entity().getLevel().isClientSide())
                {
                    CompoundTag compound = stack.getOrCreateTag();

                    int tabiCooldown = compound.getInt("tabiCooldown");
                    if (tabiCooldown > 0)
                    {
                        compound.putInt("tabiCooldown", --tabiCooldown);
                    }
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
