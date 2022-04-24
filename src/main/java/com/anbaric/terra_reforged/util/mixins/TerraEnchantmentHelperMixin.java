package com.anbaric.terra_reforged.util.mixins;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.world.item.enchantment.EnchantmentHelper.getEnchantmentLevel;

@Mixin(EnchantmentHelper.class)
public class TerraEnchantmentHelperMixin
{
    @Inject(method = "getKnockbackBonus(Lnet/minecraft/world/entity/LivingEntity;)I", at = @At("HEAD"), cancellable = true)
    private static void increasePlayersKnockBack(LivingEntity entity, CallbackInfoReturnable<Integer> cir)
    {
        if (entity instanceof Player)
        {
            cir.setReturnValue(getEnchantmentLevel(Enchantments.KNOCKBACK, entity) * (int) (entity.getAttribute(Attributes.ATTACK_KNOCKBACK).getValue() + 1));
        }
        else
        {
            cir.setReturnValue(getEnchantmentLevel(Enchantments.KNOCKBACK, entity));
        }
    }
}
