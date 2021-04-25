package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.CombatRules;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.CuriosApi;

public class TerraItemSharktoothNecklace extends TerraItemAccessory
{
    public TerraItemSharktoothNecklace(Properties properties)
    {
        super(properties);
        MinecraftForge.EVENT_BUS.addListener(this::ignoreArmor);
    }

    public void ignoreArmor(LivingHurtEvent event)
    {
        LivingEntity victim = event.getEntityLiving();
        PlayerEntity player = event.getSource().getTrueSource() instanceof PlayerEntity ? (PlayerEntity) event.getSource().getTrueSource() : null;
        if (player == null) { return; }
        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.ARMOR_PASSERS), player).ifPresent(found -> {
            event.setAmount(CombatRules.getDamageAfterAbsorb(event.getAmount(), victim.getTotalArmorValue() - 1, (float) victim.getAttributeValue(Attributes.ARMOR_TOUGHNESS)));
        });
    }
}
