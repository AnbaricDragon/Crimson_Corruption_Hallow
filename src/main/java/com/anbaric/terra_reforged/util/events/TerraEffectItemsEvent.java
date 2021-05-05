package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.multijump.TerraCapabilityMultiJump;
import com.anbaric.terra_reforged.util.handlers.BeeHandler;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosApi;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class TerraEffectItemsEvent
{
//    @SubscribeEvent
//    static void onFall(LivingFallEvent event)
//    {
//        if (event.getEntityLiving() instanceof PlayerEntity)
//        {
//            PlayerEntity player = (PlayerEntity) event.getEntityLiving();
//            CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.FALL_BREAKERS), player).ifPresent(found -> event.setDistance(0));
//        }
//    }

    @SubscribeEvent
    static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity player = event.player != null ? event.player : null;

        //Jump Boost Effects
        if (player != null)
        {
            World world = player.getCommandSenderWorld();
            //Tick Effects
            //Flower Boots
            CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().is(TerraTagRegistry.FLOWER_WALKERS), player).ifPresent(found -> {if (world.getBlockState(player.blockPosition().below()).is(Tags.Blocks.DIRT) && world.getBlockState(player.blockPosition()).isAir(world, player.blockPosition())) { Block flowers = BlockTags.FLOWERS.getRandomElement(world.getRandom()); if (flowers.is(Blocks.WITHER_ROSE)) { return; }world.setBlockAndUpdate(player.blockPosition(), flowers.defaultBlockState());if (flowers.is(BlockTags.TALL_FLOWERS)) { world.setBlockAndUpdate(player.blockPosition().above(), flowers.defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER));}}});
        }
    }

    //For checking if a player should take damage
    @SubscribeEvent
    static void onPlayerAttacked(LivingAttackEvent event)
    {
        PlayerEntity player = event.getEntityLiving() instanceof PlayerEntity ? (PlayerEntity) event.getEntityLiving() : null;
        if (player == null) { return; }


    }

    //For when a player has taken damage
    @SubscribeEvent
    static void onPlayerHurt(LivingDamageEvent event)
    {
        PlayerEntity player = event.getEntityLiving() instanceof PlayerEntity ? (PlayerEntity) event.getEntityLiving() : null;
        if (player == null) { return; }
        ServerWorld world  = (ServerWorld) event.getEntity().getCommandSenderWorld();

        float aggroDist = event.getSource().getEntity() instanceof LivingEntity ? event.getSource().getEntity().getEntity().distanceTo(player) : 10F;

        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().is(TerraTagRegistry.BEE_SPAWNERS) && !player.getCooldowns().isOnCooldown(stack.getItem()), player).ifPresent(found -> {
            player.getCooldowns().addCooldown(CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().is(TerraTagRegistry.BEE_SPAWNERS), player).get().right.getItem(), 100);
            player.addEffect(new EffectInstance(Effects.REGENERATION, 100));
            BeeHandler.spawnAngryBees(world, player.blockPosition(), aggroDist);
        });
        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().is(TerraTagRegistry.PANIC_GIVERS) && !player.getCooldowns().isOnCooldown(stack.getItem()), player).ifPresent(found -> {
            player.getCooldowns().addCooldown(CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().is(TerraTagRegistry.PANIC_GIVERS), player).get().right.getItem(), 100);
            player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 100));
        });
    }

    @SubscribeEvent
    static void onPlayerAttack(LivingHurtEvent event)
    {
        LivingEntity victim = event.getEntityLiving();
        PlayerEntity player = event.getSource().getEntity() instanceof PlayerEntity ? (PlayerEntity) event.getSource().getEntity() : null;
        if (player == null) { return; }

        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().is(TerraTagRegistry.ARMOR_PASSERS), player).ifPresent(found ->
            event.setAmount(CombatRules.getDamageAfterAbsorb(event.getAmount(), victim.getArmorValue() - 1, (float) victim.getAttributeValue(Attributes.ARMOR_TOUGHNESS)))
        );
    }
}
