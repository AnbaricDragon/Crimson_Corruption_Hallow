package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
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
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.List;

public class TerraItemMoltenCharm extends TerraItemAccessory
{
    public TerraItemMoltenCharm(Properties properties)
    {
        super(properties);
        MinecraftForge.EVENT_BUS.addListener(this::onLavaSwim);
        MinecraftForge.EVENT_BUS.addListener(this::cancelFireDamage);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.format("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "Gives 7 Seconds Of Lava Immunity"));
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

    public void onLavaSwim(LivingAttackEvent event)
    {
        PlayerEntity player = event.getEntityLiving() instanceof PlayerEntity ? (PlayerEntity) event.getEntityLiving() : null;
        if (player == null) { return; }

        ItemStack lavaCharm = CurioHandler.getBauble(player, this);
        if (!lavaCharm.isEmpty())
        {
            CompoundNBT compound = lavaCharm.getOrCreateTag();
            int         charge   = compound.getInt("charge");
            if (charge > 0)
            {
                if (event.getSource() == DamageSource.LAVA)
                {
                    compound.putInt("charge", --charge);
                    compound.putInt("chargeCooldown", 40);
                }
                player.extinguish();
                event.setCanceled(event.getSource() == DamageSource.LAVA && charge > 0);
            }
        }
    }
}