package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
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
import java.util.List;
import java.util.UUID;

public class TerraItemBootsTerraspark extends TerraItemAccessory
{
    public TerraItemBootsTerraspark(Properties properties)
    {
        super(properties);
        MinecraftForge.EVENT_BUS.addListener(this::halveLavaDamage);
        MinecraftForge.EVENT_BUS.addListener(this::cancelFireDamage);
        MinecraftForge.EVENT_BUS.addListener(this::onLavaSwim);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.format("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "Gives 7 Seconds Of Lava Immunity"));
        tooltip.add(new StringTextComponent("\u00A79" + "Gives 1.6 Seconds Of Lift"));
        tooltip.add(new StringTextComponent("\u00A79" + "Allows Walking On Lava"));
        tooltip.add(new StringTextComponent("\u00A79" + "+40% Speed"));
        tooltip.add(new StringTextComponent("\u00A79" + "-50% Lava Damage"));
        tooltip.add(new StringTextComponent("\u00A79" + "-100% Fire Damage"));
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
                if (livingEntity instanceof PlayerEntity)
                {
                    PlayerEntity player = (PlayerEntity) livingEntity;
                    if (!player.getEntityWorld().isRemote())
                    {
                        CompoundNBT compound = stack.getOrCreateTag();

                        int chargeCooldown = compound.getInt("chargeCooldown");
                        if (player.isInWater())
                        {
                            compound.putInt("chargeCooldown", 0);
                            compound.putInt("charge", 140);
                        }
                        if (chargeCooldown > 0)
                        {
                            compound.putInt("chargeCooldown", --chargeCooldown);
                        }
                        else
                        {
                            int charge = compound.getInt("charge");
                            if (charge < 140)
                            {
                                compound.putInt("charge", charge + 2);
                            }
                        }
                    }
                }
            }

            public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid)
            {
                Multimap<Attribute, AttributeModifier> atts = LinkedHashMultimap.create();
                atts.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, Reference.MODID + ":genericBonus", 0.40F, AttributeModifier.Operation.MULTIPLY_BASE));
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

    private void cancelFireDamage(LivingAttackEvent event)
    {
        CuriosApi.getCuriosHelper().findEquippedCurio(this, event.getEntityLiving()).ifPresent(found -> {
            DamageSource source = event.getSource();
            if (source == DamageSource.HOT_FLOOR || (source.isFireDamage() && source != DamageSource.LAVA))
            {
                event.setCanceled(true);
            }
        });
    }

    public void onLavaSwim(LivingAttackEvent event)
    {
        PlayerEntity player = event.getEntityLiving() instanceof PlayerEntity ? (PlayerEntity) event.getEntityLiving() : null;
        if (player == null) { return; }

        ItemStack lavaBoots = CurioHandler.getBauble(player, this);
        if (!lavaBoots.isEmpty())
        {
            DamageSource source = event.getSource();
            CompoundNBT compound = lavaBoots.getOrCreateTag();
            int         charge   = compound.getInt("charge");
            if (charge > 0)
            {
                if (source == DamageSource.LAVA)
                {
                    compound.putInt("charge", --charge);
                    compound.putInt("chargeCooldown", 40);
                }
                else if (source == DamageSource.HOT_FLOOR || (source.isFireDamage() && source != DamageSource.LAVA))
                {
                    event.setCanceled(true);
                    player.extinguish();
                }
                event.setCanceled(event.getSource() == DamageSource.LAVA && charge > 0);
            }
        }
    }
}
