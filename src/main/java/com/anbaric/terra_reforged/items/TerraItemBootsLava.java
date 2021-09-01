package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;

import net.minecraft.item.Item.Properties;
import top.theillusivec4.curios.api.type.capability.ICurio.DropRule;
import top.theillusivec4.curios.api.type.capability.ICurio.SoundInfo;

import java.util.List;

public class TerraItemBootsLava extends TerraItemAccessory
{
    public TerraItemBootsLava()
    {
        super();
        MinecraftForge.EVENT_BUS.addListener(this::cancelFireDamage);
        MinecraftForge.EVENT_BUS.addListener(this::halveLavaDamage);
        MinecraftForge.EVENT_BUS.addListener(this::onLavaSwim);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.format("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "Allows Walking On Fluids"));
        tooltip.add(new StringTextComponent("\u00A79" + "Gives 7 Seconds Of Lava Immunity"));
        tooltip.add(new StringTextComponent("\u00A79" + "-50% Lava Damage"));
        tooltip.add(new StringTextComponent("\u00A79" + "-100% Fire Damage"));
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused)
    {
        return CurioHandler.createProvider(new ICurio()
        {
            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity)
            {
                if (livingEntity instanceof PlayerEntity)
                {
                    CompoundNBT compound = stack.getOrCreateTag();
                    boolean isWet = livingEntity.isInWater();

                    int chargeCooldown = compound.getInt("chargeCooldown");
                    if (chargeCooldown > 0)
                    {
                        compound.putInt("chargeCooldown", isWet ? 0 : --chargeCooldown);
                    }
                    else
                    {
                        int charge = compound.getInt("charge");
                        if (charge < 140)
                        {
                            compound.putInt("charge", isWet ? 140 : charge + 2);
                        }
                    }
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

    private void cancelFireDamage(LivingAttackEvent event)
    {
        CuriosApi.getCuriosHelper().findEquippedCurio(this, event.getEntityLiving()).ifPresent(found ->
        {
            DamageSource source = event.getSource();
            if (source.isFireDamage() && source != DamageSource.LAVA)
            {
                event.getEntityLiving().extinguish();
                event.setCanceled(true);
            }
        });
    }

    public void halveLavaDamage(LivingDamageEvent event)
    {
        PlayerEntity player = event.getEntityLiving() instanceof PlayerEntity ? (PlayerEntity) event.getEntityLiving() : null;
        if (player == null)
        {
            return;
        }

        CuriosApi.getCuriosHelper().findEquippedCurio(this, player).ifPresent(found -> {
            if (event.getSource() == DamageSource.LAVA)
            {
                event.setAmount(2.0F);
            }
        });
    }

    public void onLavaSwim(LivingAttackEvent event)
    {
        PlayerEntity player = event.getEntityLiving() instanceof PlayerEntity ? (PlayerEntity) event.getEntityLiving() : null;
        if (player == null) { return; }

        ItemStack lavaBoots = CurioHandler.getBaubleStack(player, this);
        if (!lavaBoots.isEmpty())
        {
            DamageSource source = event.getSource();
            CompoundNBT compound = lavaBoots.getOrCreateTag();
            int         charge   = compound.getInt("charge");
            if (charge > 0)
            {
                if (source == DamageSource.LAVA)
                {
                    player.extinguish();
                    compound.putInt("charge", --charge);
                    compound.putInt("chargeCooldown", 40);
                }
                event.setCanceled(event.getSource() == DamageSource.LAVA && charge > 0);
            }
        }
    }
}