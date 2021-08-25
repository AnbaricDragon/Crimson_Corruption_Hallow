package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;

import net.minecraft.item.Item.Properties;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import javax.annotation.Nonnull;
import java.util.List;

import top.theillusivec4.curios.api.type.capability.ICurio.DropRule;
import top.theillusivec4.curios.api.type.capability.ICurio.SoundInfo;

public class TerraItemCrossNecklace extends TerraItemAccessory
{
    public TerraItemCrossNecklace()
    {
        super();
//        MinecraftForge.EVENT_BUS.addListener(this::onPlayerAttacked);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.format("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "+100% Invincibility Duration"));
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused)
    {
        return CurioHandler.createProvider(new ICurio()
        {
            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity)
            {
                CompoundNBT compound = stack.getOrCreateTag();

                if (livingEntity.hurtResistantTime <= 10)
                {
                    compound.putBoolean("canApplyInvuln", true);
                }
                else
                {
                    if (compound.getBoolean("canApplyInvuln"))
                    {
                        livingEntity.hurtResistantTime += 20;
                        compound.putBoolean("canApplyInvuln", false);
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

//    public void onPlayerAttacked(LivingAttackEvent event)
//    {
//        PlayerEntity player = event.getEntityLiving() instanceof PlayerEntity ? (PlayerEntity) event.getEntityLiving() : null;
//        if (player == null) { return; }
//
//        CuriosApi.getCuriosHelper().findEquippedCurio(this, player).ifPresent(found ->
//        {
//            if (!player.isPotionActive(TerraEffectRegistry.INVINCIBILITY.get()) && !player.getCooldownTracker().hasCooldown(this) && event.getSource().getImmediateSource() instanceof LivingEntity)
//            {
//                CuriosApi.getCuriosHelper().getCuriosHandler(player).map(ICuriosItemHandler::getCurios).map(map -> map.get("curio")).map(ICurioStacksHandler::getStacks).map(dynamicStackHandler ->
//                {
//                    for (int i = 0; i < dynamicStackHandler.getSlots(); i++)
//                    {
//                        ItemStack stack = dynamicStackHandler.getStackInSlot(i);
//                        if (stack.getItem().isIn(TerraTagRegistry.INVULN_GIVERS))
//                        {
//                            player.getCooldownTracker().setCooldown(stack.getItem(), 100);
//                        }
//                    }
//                    return null;
//                });
//                player.addPotionEffect(new EffectInstance(TerraEffectRegistry.INVINCIBILITY.get(), 40, 0, false, false));
//            }
//            else
//            {
//                event.setCanceled(true);
//            }
//        });
//    }
}
