package com.anbaric.terra_reforged.util.mixins;

import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class TerraPayerEntityMixin
{
    @Inject(at = @At("RETURN"), method = "func_234570_el_")
    private static void addManaAttribute(CallbackInfoReturnable<AttributeModifierMap.MutableAttribute> cir)
    {
        cir.getReturnValue().createMutableAttribute(TerraAttributeRegistry.MANA_MAX.get(), 0);
    }
}
