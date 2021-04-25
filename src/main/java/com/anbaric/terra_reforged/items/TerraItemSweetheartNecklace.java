package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.handlers.BeeHandler;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;

public class TerraItemSweetheartNecklace extends TerraItemAccessory
{
    public TerraItemSweetheartNecklace(Properties properties)
    {
        super(properties);
        MinecraftForge.EVENT_BUS.addListener(this::beeInPanic);
    }

    public void beeInPanic(LivingDamageEvent event)
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
        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.PANIC_GIVERS) && !player.getCooldownTracker().hasCooldown(stack.getItem()), player).ifPresent(found -> {
            player.getCooldownTracker().setCooldown(CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.PANIC_GIVERS), player).get().right.getItem(), 100);
            player.addPotionEffect(new EffectInstance(Effects.SPEED, 60));
        });
    }
}
