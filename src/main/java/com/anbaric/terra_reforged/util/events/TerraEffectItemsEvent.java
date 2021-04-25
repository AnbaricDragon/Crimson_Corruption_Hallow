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
            World world = player.getEntityWorld();
            //Tick Effects
            //Flower Boots
            CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.FLOWER_WALKERS), player).ifPresent(found -> {if (world.getBlockState(player.getPosition().down()).isIn(Tags.Blocks.DIRT) && world.getBlockState(player.getPosition()).isAir(world, player.getPosition())) { Block flowers = BlockTags.FLOWERS.getRandomElement(world.getRandom()); if (flowers.matchesBlock(Blocks.WITHER_ROSE)) { return; }world.setBlockState(player.getPosition(), flowers.getDefaultState());if (flowers.isIn(BlockTags.TALL_FLOWERS)) { world.setBlockState(player.getPosition().up(), flowers.getDefaultState().with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER));}}});
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
        ServerWorld world  = (ServerWorld) event.getEntity().getEntityWorld();

        float aggroDist = event.getSource().getTrueSource() instanceof LivingEntity ? event.getSource().getTrueSource().getEntity().getDistance(player) : 10F;

        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.BEE_SPAWNERS) && !player.getCooldownTracker().hasCooldown(stack.getItem()), player).ifPresent(found -> {
            player.getCooldownTracker().setCooldown(CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.BEE_SPAWNERS), player).get().right.getItem(), 100);
            player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 100));
            BeeHandler.spawnAngryBees(world, player.getPosition(), aggroDist);
        });
        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.PANIC_GIVERS) && !player.getCooldownTracker().hasCooldown(stack.getItem()), player).ifPresent(found -> {
            player.getCooldownTracker().setCooldown(CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.PANIC_GIVERS), player).get().right.getItem(), 100);
            player.addPotionEffect(new EffectInstance(Effects.SPEED, 100));
        });
    }

    @SubscribeEvent
    static void onPlayerAttack(LivingHurtEvent event)
    {
        LivingEntity victim = event.getEntityLiving();
        PlayerEntity player = event.getSource().getTrueSource() instanceof PlayerEntity ? (PlayerEntity) event.getSource().getTrueSource() : null;
        if (player == null) { return; }

        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem().isIn(TerraTagRegistry.ARMOR_PASSERS), player).ifPresent(found ->
            event.setAmount(CombatRules.getDamageAfterAbsorb(event.getAmount(), victim.getTotalArmorValue() - 1, (float) victim.getAttributeValue(Attributes.ARMOR_TOUGHNESS)))
        );
    }
}
