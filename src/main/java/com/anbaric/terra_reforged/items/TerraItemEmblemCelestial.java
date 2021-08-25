package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.handlers.MathHandler;
import com.anbaric.terra_reforged.util.handlers.NBTHandler;
import com.anbaric.terra_reforged.util.handlers.V3Handler;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
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
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

import top.theillusivec4.curios.api.type.capability.ICurio.DropRule;
import top.theillusivec4.curios.api.type.capability.ICurio.SoundInfo;

public class TerraItemEmblemCelestial extends TerraItemAccessory
{
    public TerraItemEmblemCelestial()
    {
        super();
        MinecraftForge.EVENT_BUS.addListener(this::onTossItem);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.format("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "+15% Magic Damage"));
        tooltip.add(new StringTextComponent("\u00A79" + "Increases Pickup Range For Mana Stars"));

    }

    private void onTossItem(ItemTossEvent event)
    {
        List<ItemStack> ring = CurioHandler.getAllBaubles(event.getPlayer(), TerraTagRegistry.ITEM_BRINGERS);
        if (!ring.isEmpty())
        {
            for (ItemStack item : ring)
            {
                NBTHandler.setCooldown(item, 100);
            }
        }
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

            public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid)
            {
                Multimap<Attribute, AttributeModifier> atts = LinkedHashMultimap.create();
                atts.put(TerraAttributeRegistry.MAGIC_DAMAGE.get(), new AttributeModifier(uuid, Reference.MODID + ":sorcererEmblemBonus", 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
                return atts;
            }

            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity)
            {
                if (livingEntity.isSpectator() || !(livingEntity instanceof PlayerEntity))
                {
                    return;
                }

                int cooldown = NBTHandler.getCooldown(stack);
                if (cooldown <= 0)
                {
                    double x = livingEntity.getPosX();
                    double y = livingEntity.getPosY() + 0.75;
                    double z = livingEntity.getPosZ();

                    int              range  = 9;
                    List<ItemEntity> items  = livingEntity.getEntityWorld().getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
                    int              pulled = 0;
                    for (ItemEntity item : items)
                    {
                        if (((TerraItemEmblemCelestial) stack.getItem()).canPullItem(item))
                        {
                            if (pulled > 200)
                            {
                                break;
                            }

                            MathHandler.setEntityMotionFromVector(item, new V3Handler(x, y, z), 0.45F);
                            if (livingEntity.getEntityWorld().isRemote())
                            {
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
        return (!item.cannotPickup()) && item.getItem().getItem().isIn(TerraTagRegistry.MANA_CONSUMEABLE);
    }
}
