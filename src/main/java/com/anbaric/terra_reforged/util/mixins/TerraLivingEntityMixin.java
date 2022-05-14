package com.anbaric.terra_reforged.util.mixins;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class TerraLivingEntityMixin
{
    @Shadow protected abstract void playBlockFallSound();

    @Shadow protected abstract void playHurtSound(DamageSource p_21160_);

    @ModifyVariable(method = "travel(Lnet/minecraft/world/phys/Vec3;)V", at = @At(value = "STORE"), index = 8, ordinal = 0)
    public float removeSlipperiness(float x)
    {
        if (((LivingEntity) (Object) this) instanceof Player player)
        {
            if (CurioHandler.hasBauble(player, TerraTagRegistry.ICE_WALKERS) && player.level.getBlockState(player.getOnPos()).is(BlockTags.ICE))
            {
                return 0.6F;
            }
        }
        return x;
    }

    @Inject(method = "decreaseAirSupply(I)I", at = @At("HEAD"), cancellable = true)
    private void increaseAir(int air, CallbackInfoReturnable<Integer> cir)
    {
        int i = EnchantmentHelper.getRespiration((LivingEntity) (Object) this);
        i += extraAir((LivingEntity) (Object) this);
        cir.setReturnValue(i > 0 && ((LivingEntity) (Object) this).getRandom().nextInt(i + 1) > 0 ? air : air - 1);
    }

    private int extraAir(Entity entity)
    {
        Player player = entity instanceof Player ? (Player) entity : null;
        int air = 0;
        if (player != null)
        {
            if (CurioHandler.hasBauble(player, TerraItemRegistry.GEAR_DIVING.get())) { air = 2; }
            if (CurioHandler.hasBauble(player, TerraItemRegistry.GEAR_DIVING_JELLYFISH.get())) { air = 2; }
            if (CurioHandler.hasBauble(player, TerraItemRegistry.GEAR_DIVING_ARCTIC.get())) { air = 4; }
            if (player.getItemBySlot(EquipmentSlot.HEAD).getItem() == TerraItemRegistry.HELMET_DIVING.get()) { air += 1; }
        }
        return air;
    }
}