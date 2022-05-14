package com.anbaric.terra_reforged.util.mixins;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class TerraPlayerMixin
{
    @Inject(method = "isScoping()Z", at = @At("HEAD"), cancellable = true)
    public void addSniperZoom(CallbackInfoReturnable<Boolean> cir)
    {
        Player player = (Player) (Object) this;
        cir.setReturnValue(player.isUsingItem() && (player.getUseItem().is(Items.SPYGLASS) || (player.getUseItem().is(TerraTagRegistry.GUN_SNIPERS) && CurioHandler.hasBauble(player, TerraTagRegistry.GUN_SCOPERS))));
    }
}
