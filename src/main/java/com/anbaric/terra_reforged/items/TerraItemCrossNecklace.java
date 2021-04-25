package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import top.theillusivec4.curios.api.CuriosApi;

public class TerraItemCrossNecklace extends TerraItemAccessory
{
    public TerraItemCrossNecklace(Properties properties)
    {
        super(properties);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerAttacked);
    }

    public void onPlayerAttacked(LivingAttackEvent event)
    {
        PlayerEntity player = event.getEntityLiving() instanceof PlayerEntity ? (PlayerEntity) event.getEntityLiving() : null;
        if (player == null) { return; }

        CuriosApi.getCuriosHelper().findEquippedCurio(this, player).ifPresent(found ->
        {
            if (!player.isPotionActive(TerraEffectRegistry.INVINCIBILITY.get()))
            {
                player.addPotionEffect(new EffectInstance(TerraEffectRegistry.INVINCIBILITY.get(), 40, 0, false, false));
            }
            else
            {
                event.setCanceled(true);
            }
        });
    }
}
