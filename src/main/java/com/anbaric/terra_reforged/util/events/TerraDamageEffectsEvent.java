package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.PlayerMana.PlayerMana;
import com.anbaric.terra_reforged.util.handlers.RevengeHandler;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraDamageEffectsEvent
{
    @SubscribeEvent
    static void negateDamage(LivingAttackEvent event)
    {
        DamageSource damageType = event.getSource();
        LivingEntity target = event.getEntityLiving();
        LivingEntity attacker = event.getSource().getDirectEntity() instanceof LivingEntity ? (LivingEntity) event.getSource().getDirectEntity() : null;
        boolean isTargetAPlayer = target instanceof Player;
        boolean isAttackerAPlayer = attacker instanceof Player;

        if (isTargetAPlayer)
        {
            Player player = (Player) target;
            if (damageType == DamageSource.HOT_FLOOR || damageType == DamageSource.IN_FIRE || damageType == DamageSource.ON_FIRE)
            {
                player.clearFire();
                event.setCanceled(CurioHandler.hasBauble(player, TerraTagRegistry.BURNING_NEGATORS));
            }
            else if (damageType == DamageSource.LAVA)
            {
                ItemStack lavaProtector = CurioHandler.getStack(player, TerraTagRegistry.LAVA_PROTECTORS);
                if (!lavaProtector.isEmpty())
                {
                    CompoundTag  compound = lavaProtector.getOrCreateTag();
                    int          charge   = compound.getInt("charge");
                    if (charge > 0)
                    {
                        player.clearFire();
                        compound.putInt("charge", --charge);
                        compound.putInt("chargeCooldown", 40);
                        event.setCanceled(true);
                    }
                }
            }
        }
        if (isAttackerAPlayer)
        {
            Player player = (Player) attacker;
            if (CurioHandler.hasBauble(player, TerraTagRegistry.FIRE_STARTERS) && !target.fireImmune())
            {
                target.setSecondsOnFire(target.getRandom().nextInt(3) + 1);
            }
        }
    }

    @SubscribeEvent
    static void alterDamage(LivingDamageEvent event)
    {
        DamageSource damageType = event.getSource();
        Player player = event.getEntityLiving() instanceof Player ? (Player) event.getEntityLiving() : null;
        float damage = event.getAmount();

        if (player != null)
        {
            if (damageType == DamageSource.LAVA)
            {
                event.setAmount(CurioHandler.hasBauble(player, TerraTagRegistry.LAVA_RESISTORS) ? damage / 2 : damage);
            }
        }
    }

    @SubscribeEvent
    static void afterDamage(LivingHurtEvent event)
    {
        LivingEntity target = event.getEntityLiving();
        LivingEntity attacker = event.getSource().getDirectEntity() instanceof LivingEntity ? (LivingEntity) event.getSource().getDirectEntity() : null;
        boolean isTargetAPlayer = target instanceof Player;
        boolean isAttackerAPlayer = attacker instanceof Player;

        if (isTargetAPlayer)
        {
            Player player = (Player) target;
            if (CurioHandler.hasBauble(player, TerraTagRegistry.MANA_RESTORERS))
            {
                player.getCapability(PlayerMana.TERRA_MANA_CAPABILITY).ifPresent(cap -> {
                    boolean intentionalDamage = event.getSource().getDirectEntity() instanceof LivingEntity;
                    int     manaRestored      = (int) (event.getAmount() * 10);
                    int     maxMana           = (int) player.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).getValue();
                    int     currentMana       = cap.getCurrentMana();
                    if (intentionalDamage)
                    {
                        if (currentMana + manaRestored <= maxMana)
                        {
                            cap.setCurrentMana(currentMana + manaRestored);
                        }
                        else
                        {
                            cap.setCurrentMana(maxMana);
                        }
                    }
                });
            }
            if (CurioHandler.hasBauble(player, TerraTagRegistry.BEE_SPAWNERS) && event.getSource() instanceof EntityDamageSource)
            {
                if (!player.getCooldowns().isOnCooldown(CurioHandler.getStack(player, TerraTagRegistry.BEE_SPAWNERS).getItem()))
                {
                    float aggroDist = event.getSource().getDirectEntity() instanceof LivingEntity ? event.getSource().getDirectEntity().distanceTo(player) : 10F;
                    RevengeHandler.spawnAngryBees((ServerLevel) player.getLevel(), player.getOnPos().above(), aggroDist, CurioHandler.countBaubles(player, TerraTagRegistry.BEE_SPAWNERS));
                    player.addEffect(new MobEffectInstance(TerraEffectRegistry.HONEY.get(), 100));
                    for (ItemStack item : CurioHandler.getAllStacks(player, TerraTagRegistry.BEE_SPAWNERS))
                    {
                        player.getCooldowns().addCooldown(item.getItem(), 100);
                    }
                }
            }
            if (CurioHandler.hasBauble(player, TerraTagRegistry.STAR_SPAWNERS))
            {
                if (!player.getCooldowns().isOnCooldown(CurioHandler.getStack(player, TerraTagRegistry.STAR_SPAWNERS).getItem()))
                {
                    RevengeHandler.spawnArrows((ServerLevel) player.getLevel(), player.getOnPos().above(), player.getRandom());
                    for (ItemStack item : CurioHandler.getAllStacks(player, TerraTagRegistry.STAR_SPAWNERS))
                    {
                        player.getCooldowns().addCooldown(item.getItem(), 100);
                    }
                }
            }
            if (CurioHandler.hasBauble(player, TerraTagRegistry.PANIC_GIVERS))
            {
                if (!player.getCooldowns().isOnCooldown(CurioHandler.getStack(player, TerraTagRegistry.PANIC_GIVERS).getItem()))
                {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60));
                    for (ItemStack item : CurioHandler.getAllStacks(player, TerraTagRegistry.PANIC_GIVERS))
                    {
                        player.getCooldowns().addCooldown(item.getItem(), 100);
                    }
                }
            }
        }
        if (isAttackerAPlayer)
        {
            Player player = (Player) attacker;
            LivingEntity victim = target;

            if (CurioHandler.hasBauble(player, TerraTagRegistry.ARMOR_PASSERS))
            {
                event.setAmount(CombatRules.getDamageAfterAbsorb(event.getAmount(), (float) Math.max(victim.getArmorValue() - CurioHandler.countBaubles(player, TerraTagRegistry.ARMOR_PASSERS) * 3, 0), (float) victim.getAttribute(Attributes.ARMOR_TOUGHNESS).getValue()));
            }
        }
    }

    @SubscribeEvent
    static void reduceFallDamage(LivingFallEvent event)
    {
        Player player = event.getEntityLiving() instanceof Player ? (Player) event.getEntityLiving() : null;
        if (player == null || player.getLevel().isClientSide()) { return; }
        float fallDistance = event.getDistance();

        if (CurioHandler.hasBauble(player, TerraTagRegistry.FALL_PROTECTORS))
        {
            event.setCanceled(true);
            return;
        }
        if (CurioHandler.hasBauble(player, TerraTagRegistry.FROG_FALLERS))
        {
            event.setDistance(Math.max(fallDistance - 3, 0));
        }
    }
}
