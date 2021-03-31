package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.multijump.TerraCapabilityMultiJump;
import com.anbaric.terra_reforged.util.handlers.BeeHandler;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.advancements.criterion.RightClickBlockWithItemTrigger;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Random;
import java.util.UUID;

public class TerraEffectItemsEvent
{
    @SubscribeEvent
    static void onFall(LivingFallEvent event)
    {
        if (!(event.getEntityLiving() instanceof PlayerEntity))
        {
            return;
        }
        PlayerEntity player = ((PlayerEntity) event.getEntityLiving());

        for (int i = 0; i < player.inventory.mainInventory.size(); i++)
        {
            ItemStack targetStack = player.inventory.getStackInSlot(i);
            if (targetStack.getItem().isIn(TerraTagRegistry.FALL_BREAKERS))
            {
                event.setDistance(0.0F);
            }
        }
    }

    @SubscribeEvent
    public void onLivingHurtEvent(LivingDamageEvent event)
    {
        if(event.getEntity().world.isRemote()) return;
        if (!(event.getEntityLiving() instanceof ServerPlayerEntity)) return;

        ServerPlayerEntity player = (ServerPlayerEntity)event.getEntityLiving();
        player.sendMessage(ITextComponent.getTextComponentOrEmpty("player taking damage"), UUID.randomUUID());

        if(event.getAmount() >= player.getHealth()) {
            player.sendMessage(ITextComponent.getTextComponentOrEmpty("fatal damage"), UUID.randomUUID());
            ItemStack itemstack = null;

            for (Hand hand : Hand.values()) {
                ItemStack itemstack1 = player.getHeldItem(hand);
                if (itemstack1.getItem() == TerraItemRegistry.INGOT_COBALT.get()) {
                    itemstack = itemstack1.copy();
                    itemstack1.shrink(1);
                    break;
                }
            }

            if (itemstack != null) {
                player.sendMessage(ITextComponent.getTextComponentOrEmpty("has custom totem"), UUID.randomUUID());
                player.setHealth(1.0F);
                player.clearActivePotions();
                if(itemstack.getItem() == TerraItemRegistry.INGOT_COBALT.get()){
                    player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 9000, 1));
                    player.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 10000, 1));
                    player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 8000, 0));
                    player.addPotionEffect(new EffectInstance(Effects.HERO_OF_THE_VILLAGE, 1000000, 4));
                    ItemStack apple = new ItemStack(Items.GOLDEN_APPLE);
                    player.addItemStackToInventory(apple);
                }
                player.world.setEntityState(player, (byte) 35);
            }
        }
    }

    @SubscribeEvent
    static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity player = event.player != null ? event.player : null;

        //Accessory Effects
        int jumpModifier = 0;
        boolean redBalloon = false, cloudBalloon = false, blizzardBalloon = false, sandstormBalloon = false, honeyBalloon = false, sharkBalloon = false, fartBalloon = false;
        for (int i = 0; i < player.inventory.mainInventory.size(); i++)
        {
            Item targetStack = player.inventory.getStackInSlot(i).getItem();

            if (targetStack == TerraItemRegistry.BALLOON_RED.get()) { redBalloon = true; }
            if (targetStack.isIn(TerraTagRegistry.CLOUD_HIGH_JUMPERS)) { cloudBalloon = true; }
            if (targetStack.isIn(TerraTagRegistry.BLIZZARD_HIGH_JUMPERS)) { blizzardBalloon = true; }
            if (targetStack.isIn(TerraTagRegistry.SANDSTORM_HIGH_JUMPERS)) { sandstormBalloon = true; }
            if (targetStack.isIn(TerraTagRegistry.HONEY_HIGH_JUMPERS)) { honeyBalloon = true; }
            if (targetStack.isIn(TerraTagRegistry.TSUNAMI_HIGH_JUMPERS)) { sharkBalloon = true; }
            if (targetStack.isIn(TerraTagRegistry.FART_HIGH_JUMPERS)) { fartBalloon = true; }
        }
        if (redBalloon) { jumpModifier++; }
        if (cloudBalloon) { jumpModifier++; }
        if (blizzardBalloon) { jumpModifier++; }
        if (sandstormBalloon) { jumpModifier++; }
        if (honeyBalloon) { jumpModifier++; }
        if (sharkBalloon) { jumpModifier++; }
        if (fartBalloon) { jumpModifier++; }
        if (jumpModifier > 0) {player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 1, jumpModifier-1));}

        //Bottle Item Double Jumps
        player.getCapability(TerraCapabilityMultiJump.MULTI_JUMP_CAPABILITY).ifPresent(cap ->
        {
            boolean hasCloudItem = false, hasBlizzardItem = false, hasSandstormItem = false, hasTsunamiItem = false, hasFartItem = false;
            for (int i = 0; i < player.inventory.mainInventory.size(); i++)
            {
                ItemStack targetStack = player.inventory.getStackInSlot(i);

                if (targetStack.getItem().isIn(TerraTagRegistry.CLOUD_JUMPERS)) { hasCloudItem = true; }
                if (targetStack.getItem().isIn(TerraTagRegistry.BLIZZARD_JUMPERS)) { hasBlizzardItem = true; }
                if (targetStack.getItem().isIn(TerraTagRegistry.SANDSTORM_JUMPERS)) { hasSandstormItem = true; }
                if (targetStack.getItem().isIn(TerraTagRegistry.TSUNAMI_JUMPERS)) { hasTsunamiItem = true; }
                if (targetStack.getItem().isIn(TerraTagRegistry.FART_JUMPERS)) { hasFartItem = true; }
            }
            cap.setHasCloudItem(hasCloudItem);
            cap.setHasBlizzardItem(hasBlizzardItem);
            cap.setHasSandstormItem(hasSandstormItem);
            cap.setHasTsunamiItem(hasTsunamiItem);
            cap.setHasFartItem(hasFartItem);
            if (player.isOnGround()) { cap.resetJumps(); }
        });
    }

    @SubscribeEvent
    static void onPlayerHurt(LivingDamageEvent event)
    {
        PlayerEntity player = event.getEntityLiving() instanceof PlayerEntity ? (PlayerEntity) event.getEntityLiving() : null;
        if (player == null) { return; }
        ServerWorld world  = (ServerWorld) event.getEntity().getEntityWorld();

        float aggroDist = event.getSource().getTrueSource() instanceof LivingEntity ? event.getSource().getTrueSource().getEntity().getDistance(player) : 10F;

        //Item Checking
        boolean hasBeeItem = false, hasPanicItem = false, hasCrossItem = false;
        for (int i = 0; i < player.inventory.mainInventory.size(); i++)
        {
            Item targetStack = player.inventory.getStackInSlot(i).getItem();

            if (targetStack.getItem().isIn(TerraTagRegistry.BEE_SPAWNERS)) { hasBeeItem = true; }
            if (targetStack.getItem().isIn(TerraTagRegistry.PANIC_GIVERS)) { hasPanicItem = true; }
            if (targetStack.getItem().isIn(TerraTagRegistry.INVULN_GIVERS)) { hasCrossItem = true; }
        }

        if (hasBeeItem)
        {
            boolean hasBeeCooldown = false;
            for (int i = 0; i < player.inventory.mainInventory.size(); i++)
            {
                Item targetStack = player.inventory.getStackInSlot(i).getItem();

                if (targetStack.getItem().isIn(TerraTagRegistry.BEE_SPAWNERS))
                {
                    if (player.getCooldownTracker().hasCooldown(targetStack.getItem()))
                    {
                        hasBeeCooldown = true;
                    }
                }
            }

            if (!hasBeeCooldown)
            {
                player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 100));
                for (int i = 0; i < player.inventory.mainInventory.size(); i++)
                {
                    Item targetStack = player.inventory.getStackInSlot(i).getItem();

                    if (targetStack.getItem().isIn(TerraTagRegistry.BEE_SPAWNERS))
                    {
                        player.getCooldownTracker().setCooldown(targetStack.getItem(), 100);
                    }
                }
                BeeHandler.spawnAngryBees((ServerWorld) world, player.getPosition(), aggroDist);
            }
        }
        if (hasPanicItem)
        {
            boolean hasPanicCooldown = false;
            for (int i = 0; i < player.inventory.mainInventory.size(); i++)
            {
                Item targetStack = player.inventory.getStackInSlot(i).getItem();

                if (targetStack.getItem().isIn(TerraTagRegistry.PANIC_GIVERS))
                {
                    if (player.getCooldownTracker().hasCooldown(targetStack.getItem()))
                    {
                        hasPanicCooldown = true;
                    }
                }
            }

            if (!hasPanicCooldown)
            {
                player.addPotionEffect(new EffectInstance(Effects.SPEED, 100));
                for (int i = 0; i < player.inventory.mainInventory.size(); i++)
                {
                    Item targetStack = player.inventory.getStackInSlot(i).getItem();

                    if (targetStack.getItem().isIn(TerraTagRegistry.PANIC_GIVERS))
                    {
                        player.getCooldownTracker().setCooldown(targetStack.getItem(), 100);
                    }
                }
            }
        }
        if (hasCrossItem)
        {
            boolean hasCrossCooldown = false;
            for (int i = 0; i < player.inventory.mainInventory.size(); i++)
            {
                Item targetStack = player.inventory.getStackInSlot(i).getItem();

                if (targetStack.getItem().isIn(TerraTagRegistry.INVULN_GIVERS))
                {
                    if (player.getCooldownTracker().hasCooldown(targetStack.getItem()))
                    {
                        hasCrossCooldown = true;
                    }
                }
            }

            if (!hasCrossCooldown)
            {
                player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 40, 5, false, false));
                for (int i = 0; i < player.inventory.mainInventory.size(); i++)
                {
                    Item targetStack = player.inventory.getStackInSlot(i).getItem();

                    if (targetStack.getItem().isIn(TerraTagRegistry.INVULN_GIVERS))
                    {
                        player.getCooldownTracker().setCooldown(targetStack.getItem(), 160);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    static void onPlayerAttack(LivingHurtEvent event)
    {
        LivingEntity victim = event.getEntityLiving();
        PlayerEntity player = event.getSource().getTrueSource() instanceof PlayerEntity ? (PlayerEntity) event.getSource().getTrueSource() : null;
        if (player == null) { return; }
        int victimArmor = victim.getTotalArmorValue();

        boolean hasArmorPasser = false;
        for (int i = 0; i < player.inventory.mainInventory.size(); i++)
        {
            Item targetStack = player.inventory.getStackInSlot(i).getItem();

            if (targetStack.getItem().isIn(TerraTagRegistry.ARMOR_PASSERS)) { hasArmorPasser = true; }
        }

        if (hasArmorPasser)
        {
            event.setAmount(event.getAmount() * 1.2F);
        }
    }


}
