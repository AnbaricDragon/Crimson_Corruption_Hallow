package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.entities.projectiles.TerraProjectileSwordTerra;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.items.ItemHandlerHelper;

public class TerraProjectileSword extends SwordItem
{
    public TerraProjectileSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn)
    {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);
    }

    private void attackEntity(AttackEntityEvent evt)
    {
        if (!evt.getPlayer().world.isRemote)
        {
            trySpawnProjectile(evt.getPlayer());
        }
    }

    public void trySpawnProjectile(PlayerEntity player)
    {
        if (!player.getHeldItemMainhand().isEmpty() && player.getHeldItemMainhand().getItem() == this && player.getCooledAttackStrength(0) == 1)
        {
            TerraProjectileSwordTerra projectile = new TerraProjectileSwordTerra(player, player.world);
            Vector3d vector3d = player.getLook(1.0F);
            Vector3f vector3f = new Vector3f(vector3d);
            projectile.shoot((double)vector3f.getX(), (double)vector3f.getY(), (double)vector3f.getZ(), 1.0F, 0.0F);
            player.getHeldItemMainhand().damageItem(1, player, p -> p.sendBreakAnimation(Hand.MAIN_HAND));
            player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.BLOCK_METAL_STEP, SoundCategory.PLAYERS, 0.4F, 1.4F);
        }
    }
}
