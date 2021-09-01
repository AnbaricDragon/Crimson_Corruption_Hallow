package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.capabilities.mana.TerraCapabilityPlayerMana;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.handlers.MathHandler;
import com.anbaric.terra_reforged.util.handlers.NBTHandler;
import com.anbaric.terra_reforged.util.handlers.V3Handler;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.item.ThrowablePotionItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
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

public class TerraItemMagnetFlower extends TerraItemAccessory
{
    public TerraItemMagnetFlower()
    {
        super();
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.format("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "Auto Use Mana Potions"));
        tooltip.add(new StringTextComponent("\u00A79" + "Increases Pickup Range For Mana Stars"));
        tooltip.add(new StringTextComponent("\u00A79" + "+8% Mana Discount"));

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
                PlayerEntity player = livingEntity instanceof PlayerEntity ? (PlayerEntity) livingEntity : null;
                if (player != null)
                {
                    player.getCapability(TerraCapabilityPlayerMana.PLAYER_MANA_CAPABILITY).ifPresent(cap ->
                    {
                        int mana    = cap.getCurrentMana();
                        int maxMana = (int) player.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).getValue();
                        if (mana <= maxMana / 10)
                        {
                            int maxFoundPotSlot = -1;
                            int maxFoundAmp     = -1;

                            for (int slot = 0; slot < player.inventory.getSizeInventory(); slot++)
                            {
                                ItemStack stackInSlot = player.inventory.getStackInSlot(slot);
                                if (stackInSlot.getItem() instanceof PotionItem && !(stackInSlot.getItem() instanceof ThrowablePotionItem))
                                {
                                    for (EffectInstance effect : PotionUtils.getPotionFromItem(stackInSlot).getEffects())
                                    {
                                        if (effect.getPotion() == TerraEffectRegistry.MANA_BURST.get())
                                        {
                                            int tempnumber = effect.getAmplifier();
                                            if (tempnumber > maxFoundAmp)
                                            {
                                                maxFoundAmp = tempnumber;
                                                maxFoundPotSlot = slot;
                                            }
                                        }
                                    }
                                }
                            }
                            if (maxFoundAmp > -1)
                            {
                                Potion potion = PotionUtils.getPotionFromItem(player.inventory.getStackInSlot(maxFoundPotSlot));
                                for (EffectInstance effect : potion.getEffects())
                                {
                                    effect.performEffect(player);
                                }
                                player.inventory.getStackInSlot(maxFoundPotSlot).shrink(1);
                            }
                        }
                    });
                    int cooldown = NBTHandler.getCooldown(stack);
                    if (cooldown <= 0)
                    {
                        double x = livingEntity.getPosX();
                        double y = livingEntity.getPosY() + 0.75;
                        double z = livingEntity.getPosZ();

                        int              range  = 9;
                        List<ItemEntity> items  = player.getEntityWorld().getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
                        int              pulled = 0;
                        for (ItemEntity item : items)
                        {
                            if (((TerraItemMagnetFlower) stack.getItem()).canPullItem(item))
                            {
                                if (pulled > 200)
                                {
                                    break;
                                }

                                MathHandler.setEntityMotionFromVector(item, new V3Handler(x, y, z), 0.45F);
                                if (livingEntity.getEntityWorld().isRemote())
                                {
                                    boolean red  = livingEntity.getEntityWorld().rand.nextBoolean();
                                    float r    = red ? 1F : 0F;
                                    float b    = red ? 0F : 1F;
                                    livingEntity.getEntityWorld().addParticle(ParticleTypes.ASH, item.getPosX(), item.getPosY(), item.getPosZ(), 0, 0, 0);
                                }
                                pulled++;
                            }
                        }
                    }
                    else
                    {
                        NBTHandler.setCooldown(stack, cooldown - 1);
                    }
                }
            }

            public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid)
            {
                Multimap<Attribute, AttributeModifier> atts = LinkedHashMultimap.create();
                atts.put(TerraAttributeRegistry.MANA_DISCOUNT.get(), new AttributeModifier(uuid, Reference.MODID + ":manaFlowerBonus", 0.08, AttributeModifier.Operation.ADDITION));
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

    private boolean canPullItem(ItemEntity item)
    {
        return (!item.cannotPickup()) && item.getItem().getItem() instanceof TerraItemConsumeableMana;
    }
}
