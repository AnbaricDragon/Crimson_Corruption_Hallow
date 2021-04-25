package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.handlers.BeeHandler;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.CombatRules;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.CuriosApi;

public class TerraItemStingerNecklace extends TerraItemAccessory
{
    public TerraItemStingerNecklace(Properties properties)
    {
        super(properties);
        MinecraftForge.EVENT_BUS.addListener(this::spawnBees);
        MinecraftForge.EVENT_BUS.addListener(this::ignoreArmor);
    }

    public void spawnBees(LivingDamageEvent event)
    {
        PlayerEntity player = event.getEntityLiving() instanceof PlayerEntity ? (PlayerEntity) event.getEntityLiving() : null;
        if (player == null) { return; }
        ServerWorld world = (ServerWorld) event.getEntity().getEntityWorld();

        float aggroDist = event.getSource().getTrueSource() instanceof LivingEntity ? event.getSource().getTrueSource().getEntity().getDistance(player) : 10F;

        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.BEE_SPAWNERS) && !player.getCooldownTracker().hasCooldown(stack.getItem()), player).ifPresent(found -> {
            player.getCooldownTracker().setCooldown(CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.BEE_SPAWNERS), player).get().right.getItem(), 100);
            player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 100));
            BeeHandler.spawnAngryBees(world, player.getPosition(), aggroDist);
        });
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
