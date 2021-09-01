package com.anbaric.terra_reforged.util.mixins;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.enchantment.EnchantmentHelper.getMaxEnchantmentLevel;

@Mixin(EnchantmentHelper.class)
public class TerraEnchantmentHelperMixin
{
    @Inject(method = "getKnockbackModifier(Lnet/minecraft/entity/LivingEntity;)I", at = @At("HEAD"), cancellable = true)
    private static void increasePlayersKnockBack(LivingEntity player, CallbackInfoReturnable<Integer> cir)
    {
        cir.setReturnValue(getMaxEnchantmentLevel(Enchantments.KNOCKBACK, player) + extraKnockback(player));
    }

    private static int extraKnockback(LivingEntity entity)
    {
        PlayerEntity player = entity instanceof PlayerEntity ? (PlayerEntity) entity : null;
        int extraKnockback = 1;
        if (CurioHandler.hasBauble(player, TerraItemRegistry.GLOVE_TITAN.get())) { extraKnockback *= 2; }
        if (CurioHandler.hasBauble(player, TerraItemRegistry.GLOVE_POWER.get(), TerraItemRegistry.GLOVE_BESERKER.get())) { extraKnockback *= 2; }
        if (CurioHandler.hasBauble(player, TerraItemRegistry.GLOVE_MECHANICAL.get(), TerraItemRegistry.GLOVE_FIRE.get())) { extraKnockback *= 2; }
        return extraKnockback - 1;
    }
}
