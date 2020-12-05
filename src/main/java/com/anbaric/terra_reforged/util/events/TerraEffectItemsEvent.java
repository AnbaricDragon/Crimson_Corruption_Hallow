package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.multijump.TerraCapabilityMultiJump;
import com.anbaric.terra_reforged.util.handlers.BeeHandler;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Arrays;
import java.util.List;

public class TerraEffectItemsEvent
{
    private static List<Item> FALL_BREAKERS = Arrays.asList(TerraItemRegistry.HORSESHOE_LUCKY.get(), TerraItemRegistry.HORSESHOE_OBSIDIAN.get(), TerraItemRegistry.HORSESHOE_BALLOON_CLOUD.get(), TerraItemRegistry.HORSESHOE_BALLOON_BLIZZARD.get(), TerraItemRegistry.HORSESHOE_BALLOON_SANDSTORM.get(), TerraItemRegistry.HORSESHOE_BALLOON_SHARK.get(), TerraItemRegistry.HORSESHOE_BALLOON_HONEY.get(), TerraItemRegistry.HORSESHOE_BALLOON_FART.get());
    private static List<Item> HIGH_JUMPERS = Arrays.asList(TerraItemRegistry.BALLOON_RED.get(), TerraItemRegistry.BALLOON_CLOUD.get(), TerraItemRegistry.BALLOON_BLIZZARD.get(), TerraItemRegistry.BALLOON_SANDSTORM.get(), TerraItemRegistry.BALLOON_HONEY.get(), TerraItemRegistry.BALLOON_PUFFERFISH.get(), TerraItemRegistry.BALLOON_SHARK.get(), TerraItemRegistry.BALLOON_FART.get(), TerraItemRegistry.BALLOON_BUNDLE.get(), TerraItemRegistry.HORSESHOE_BALLOON_CLOUD.get(), TerraItemRegistry.HORSESHOE_BALLOON_BLIZZARD.get(), TerraItemRegistry.HORSESHOE_BALLOON_SANDSTORM.get(), TerraItemRegistry.HORSESHOE_BALLOON_SHARK.get(), TerraItemRegistry.HORSESHOE_BALLOON_FART.get(), TerraItemRegistry.HORSESHOE_BALLOON_HONEY.get());
    private static List<Item> CLOUD_JUMPERS = Arrays.asList(TerraItemRegistry.BOTTLE_CLOUD.get(), TerraItemRegistry.BALLOON_CLOUD.get(), TerraItemRegistry.HORSESHOE_BALLOON_CLOUD.get(), TerraItemRegistry.BALLOON_BUNDLE.get());
    private static List<Item> BLIZZARD_JUMPERS = Arrays.asList(TerraItemRegistry.BOTTLE_BLIZZARD.get(), TerraItemRegistry.BALLOON_BLIZZARD.get(), TerraItemRegistry.HORSESHOE_BALLOON_BLIZZARD.get(), TerraItemRegistry.BALLOON_BUNDLE.get());
    private static List<Item> SANDSTORM_JUMPERS = Arrays.asList(TerraItemRegistry.BOTTLE_SANDSTORM.get(), TerraItemRegistry.BALLOON_SANDSTORM.get(), TerraItemRegistry.HORSESHOE_BALLOON_SANDSTORM.get(), TerraItemRegistry.BALLOON_BUNDLE.get());
    private static List<Item> TSUNAMI_JUMPERS = Arrays.asList(TerraItemRegistry.BOTTLE_TSUNAMI.get(), TerraItemRegistry.BALLOON_SHARK.get(), TerraItemRegistry.HORSESHOE_BALLOON_SHARK.get());
    private static List<Item> FART_JUMPERS = Arrays.asList(TerraItemRegistry.BOTTLE_FART.get(), TerraItemRegistry.BALLOON_FART.get(), TerraItemRegistry.HORSESHOE_BALLOON_FART.get());
    private static List<Item> BEE_SPAWNERS = Arrays.asList(TerraItemRegistry.HONEYCOMB.get(), TerraItemRegistry.BALLOON_HONEY.get(), TerraItemRegistry.HORSESHOE_BALLOON_HONEY.get());

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
            if (FALL_BREAKERS.contains(targetStack.getItem()))
            {
                event.setDistance(0.0F);
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
            if (targetStack == TerraItemRegistry.BALLOON_CLOUD.get() || targetStack == TerraItemRegistry.HORSESHOE_BALLOON_CLOUD.get() || targetStack == TerraItemRegistry.BALLOON_BUNDLE.get()) { cloudBalloon = true; }
            if (targetStack == TerraItemRegistry.BALLOON_BLIZZARD.get() || targetStack == TerraItemRegistry.HORSESHOE_BALLOON_BLIZZARD.get() || targetStack == TerraItemRegistry.BALLOON_BUNDLE.get()) { blizzardBalloon = true; }
            if (targetStack == TerraItemRegistry.BALLOON_SANDSTORM.get() || targetStack == TerraItemRegistry.HORSESHOE_BALLOON_SANDSTORM.get() || targetStack == TerraItemRegistry.BALLOON_BUNDLE.get()) { sandstormBalloon = true; }
            if (targetStack == TerraItemRegistry.BALLOON_HONEY.get() || targetStack == TerraItemRegistry.HORSESHOE_BALLOON_HONEY.get()) { honeyBalloon = true; }
            if (targetStack == TerraItemRegistry.BALLOON_PUFFERFISH.get() || targetStack == TerraItemRegistry.BALLOON_SHARK.get() || targetStack == TerraItemRegistry.HORSESHOE_BALLOON_SHARK.get()) { sharkBalloon = true; }
            if (targetStack == TerraItemRegistry.BALLOON_FART.get() || targetStack == TerraItemRegistry.HORSESHOE_BALLOON_FART.get()) { fartBalloon = true; }
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
                if (BLIZZARD_JUMPERS.contains(targetStack.getItem())) { hasBlizzardItem = true; }
                if (SANDSTORM_JUMPERS.contains(targetStack.getItem())) { hasSandstormItem = true; }
                if (TSUNAMI_JUMPERS.contains(targetStack.getItem())) { hasTsunamiItem = true; }
                if (FART_JUMPERS.contains(targetStack.getItem())) { hasFartItem = true; }
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

        //Bee Item Spawning
        boolean hasBeeItem = false;
        for (int i = 0; i < player.inventory.mainInventory.size(); i++)
        {
            Item targetStack = player.inventory.getStackInSlot(i).getItem();

            if (targetStack.getItem().isIn(TerraTagRegistry.BEE_SPAWNERS))
            {
                hasBeeItem = true;
            }
        }

        if (hasBeeItem)
        {
            boolean hasBeeCooldown = false;
            for (int i = 0; i < player.inventory.mainInventory.size(); i++)
            {
                Item targetStack = player.inventory.getStackInSlot(i).getItem();

                if (BEE_SPAWNERS.contains(targetStack.getItem()))
                {
                    if (player.getCooldownTracker().hasCooldown(targetStack.getItem()))
                    {
                        hasBeeCooldown = true;
                    }
                }
            }

            if (!hasBeeCooldown)
            {
                for (int i = 0; i < player.inventory.mainInventory.size(); i++)
                {
                    Item targetStack = player.inventory.getStackInSlot(i).getItem();

                    if (BEE_SPAWNERS.contains(targetStack.getItem()))
                    {
                        player.getCooldownTracker().setCooldown(targetStack.getItem(), 100);
                    }
                }

                if (world instanceof ServerWorld)
                {
                    BeeHandler.spawnAngryBees((ServerWorld) world, player.getPosition(), aggroDist);
                }
            }
        }
    }
}
