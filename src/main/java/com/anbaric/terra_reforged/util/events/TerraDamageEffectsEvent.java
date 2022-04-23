package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.PlayerMana.PlayerMana;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
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
    private void negateDamage(LivingAttackEvent event)
    {
        DamageSource damageType = event.getSource();
        Player player = event.getEntityLiving() instanceof Player ? (Player) event.getEntityLiving() : null;

        if (player != null)
        {
            if (damageType == DamageSource.HOT_FLOOR || damageType == DamageSource.IN_FIRE || damageType == DamageSource.ON_FIRE)
            {
                player.clearFire();
                event.setCanceled(CurioHandler.hasBauble(player, TerraTagRegistry.BURNING_NEGATORS));
            }
            else if (damageType == DamageSource.LAVA)
            {
                ItemStack lavaProtector = CurioHandler.getBaubleStack(player, TerraTagRegistry.LAVA_PROTECTORS);
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
    }

    @SubscribeEvent
    private void alterDamage(LivingDamageEvent event)
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
    private void afterDamage(LivingHurtEvent event)
    {
        Player player = event.getEntityLiving() instanceof Player ? (Player) event.getEntityLiving() : null;
        if (player == null) { return; }

        player.getCapability(PlayerMana.PLAYER_MANA_CAPABILITY).ifPresent(cap ->
        {
            boolean intentionalDamage = event.getSource().getDirectEntity() instanceof LivingEntity;
            int manaRestored = (int) (event.getAmount() * 10);
            int maxMana = (int) player.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).getValue();
            int currentMana = cap.getCurrentMana();
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

    @SubscribeEvent
    private void reduceFallDamage(LivingFallEvent event)
    {
        Player player = event.getEntityLiving() instanceof Player ? (Player) event.getEntityLiving() : null;
        if (player == null || player.getLevel().isClientSide()) { return; }
        float fallDistance = event.getDistance();

        if (CurioHandler.hasBauble(player, TerraTagRegistry.FROG_FALLERS))
        {
            event.setDistance(Math.max(fallDistance - 3, 0));
        }
    }
}
