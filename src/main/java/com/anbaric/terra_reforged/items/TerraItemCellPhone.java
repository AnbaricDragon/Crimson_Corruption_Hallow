package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.handlers.InfoFunctionHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class TerraItemCellPhone extends TerraItemInfo
{

    public TerraItemCellPhone(InfoFunctionHandler.Functions... functions)
    {
        super(functions);
    }

    @Override
    public int getUseDuration(ItemStack pStack)
    {
        return 12000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
        if (hand.equals(InteractionHand.OFF_HAND))
        {
            ItemStack infoItemStack     = player.getItemInHand(hand);
            int       numberOfFunctions = allFunctions.length;
            if (numberOfFunctions > 1)
            {
                CompoundTag compound    = infoItemStack.getOrCreateTag();
                int         activeIndex = compound.getInt("active_index");

                int functionArrayIndex = 0;
                for (InfoFunctionHandler.Functions functions : allFunctions)
                {
                    if (activeIndex == functions.ordinal())
                    {
                        compound.putInt("active_index", allFunctions[(functionArrayIndex + 1) % numberOfFunctions].ordinal());
                        return InteractionResultHolder.success(infoItemStack);
                    }
                    functionArrayIndex++;
                }
            }
        }
        else if (hand.equals(InteractionHand.MAIN_HAND))
        {
            if (!world.isClientSide && player instanceof ServerPlayer && !player.isPassenger() && !player.isVehicle() && player.canChangeDimensions())
            {
                ServerPlayer serverPlayer          = (ServerPlayer) player;
                ServerLevel  respawnWorld          = serverPlayer.server.getLevel(serverPlayer.getRespawnDimension());
                BlockPos     spawn                 = serverPlayer.getRespawnPosition();
                float        spawnAngle            = serverPlayer.getRespawnAngle();
                boolean      forcedSpawn           = false;
                boolean      preserveAnchorCharges = false;
                Optional<Vec3> maybeSpawn = serverPlayer.findRespawnPositionAndUseSpawnBlock(respawnWorld, spawn, spawnAngle, forcedSpawn, preserveAnchorCharges);
                if (maybeSpawn.isPresent())
                {
                    BlockPos pos = new BlockPos(maybeSpawn.get());
                    serverPlayer.teleportTo(respawnWorld, pos.getX(), pos.getY(), pos.getZ(), spawnAngle, serverPlayer.getYRot());
                }
            }
        }
        return super.use(world, player, hand);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity livingEntity, int pTimeLeft)
    {
        if (livingEntity instanceof Player)
        {
            Player player = (Player) livingEntity;
            boolean   flag      = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
            ItemStack itemstack = player.getProjectile(stack);

            int i = this.getUseDuration(stack) - pTimeLeft;
            if (i <= 0)
            {
                return;
            }

            if (i > 25 && !world.isClientSide && player instanceof ServerPlayer && !player.isPassenger() && !player.isVehicle() && player.canChangeDimensions())
            {
                ServerPlayer serverPlayer          = (ServerPlayer) player;
                ServerLevel  respawnWorld          = serverPlayer.server.getLevel(serverPlayer.getRespawnDimension());
                BlockPos     spawn                 = serverPlayer.getRespawnPosition();
                float        spawnAngle            = serverPlayer.getRespawnAngle();
                boolean      forcedSpawn           = false;
                boolean      preserveAnchorCharges = false;
                Optional<Vec3> maybeSpawn = serverPlayer.findRespawnPositionAndUseSpawnBlock(respawnWorld, spawn, spawnAngle, forcedSpawn, preserveAnchorCharges);
                if (maybeSpawn.isPresent())
                {
                    BlockPos pos = new BlockPos(maybeSpawn.get());
                    serverPlayer.teleportTo(respawnWorld, pos.getX(), pos.getY(), pos.getZ(), spawnAngle, serverPlayer.getYRot());
                }
                else
                {
                    serverPlayer.teleportTo(respawnWorld, spawn.getX(), spawn.getY(), spawn.getZ(), spawnAngle, serverPlayer.getYRot());
                }
            }
        }
    }
}
