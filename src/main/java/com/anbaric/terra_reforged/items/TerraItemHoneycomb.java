package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.handlers.BeeHandler;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;

import net.minecraft.item.Item.Properties;

public class TerraItemHoneycomb extends TerraItemAccessory
{
    public TerraItemHoneycomb(Properties properties)
    {
        super(properties);
        MinecraftForge.EVENT_BUS.addListener(this::spawnBees);
    }

    public void spawnBees(LivingDamageEvent event)
    {
        PlayerEntity player = event.getEntityLiving() instanceof PlayerEntity ? (PlayerEntity) event.getEntityLiving() : null;
        if (player == null) { return; }
        ServerWorld world = (ServerWorld) event.getEntity().getCommandSenderWorld();

        float aggroDist = event.getSource().getEntity() instanceof LivingEntity ? event.getSource().getEntity().getEntity().distanceTo(player) : 10F;

        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem() == this && !player.getCooldowns().isOnCooldown(stack.getItem()), player).ifPresent(found -> {
            player.getCooldowns().addCooldown(CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem() == this, player).get().right.getItem(), 100);
            player.addEffect(new EffectInstance(TerraEffectRegistry.HONEY.get(), 100));
            BeeHandler.spawnAngryBees(world, player.blockPosition(), aggroDist);
        });
    }
}
