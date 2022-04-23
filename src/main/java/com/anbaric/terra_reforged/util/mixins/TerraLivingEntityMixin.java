package com.anbaric.terra_reforged.util.mixins;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public class TerraLivingEntityMixin
{
    @ModifyVariable(method = "travel(Lnet/minecraft/world/phys/Vec3;)V", at = @At(value = "STORE"), index = 8, ordinal = 0)
    public float removeSlipperiness(float x)
    {
        if (((LivingEntity) (Object) this) instanceof Player player)
        {
            if (CurioHandler.hasBauble(player, TerraTagRegistry.ICE_WALKERS))
            {
                return 0.6F;
            }
        }
        return x;
    }


}