package com.anbaric.terra_reforged.util.mixins;

import com.anbaric.terra_reforged.items.TerraItemFlipper;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import com.google.gson.internal.bind.JsonTreeReader;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlock;
import net.minecraftforge.common.extensions.IForgeBlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class TerraLivingEntityMixin extends Entity
{
    public TerraLivingEntityMixin(EntityType<?> entityTypeIn, World worldIn)
    {
        super(entityTypeIn, worldIn);
    }

    //    @ModifyVariable(method = "travel(Lnet/minecraft/util/math/vector/Vector3d;)V", at = @At("HEAD"))
//    private void reduceSlippage(IWorldReader world, BlockPos pos, Entity entity, CallbackInfoReturnable<Float> cir)
//    {
//        float f3 = this.world.getBlockState(this.getPositionUnderneath()).getSlipperiness(world, this.getPositionUnderneath(), this);
//        if (iceWalk(entity))
//        {
//            cir.setReturnValue(0.6F);
//        }
//    }

    @Inject(method = "decreaseAirSupply(I)I", at = @At("HEAD"), cancellable = true)
    private void increaseAir(int air, CallbackInfoReturnable<Integer> cir)
    {
        int i = EnchantmentHelper.getRespirationModifier((LivingEntity) (Object) this);
        i += extraAir((LivingEntity) (Object) this);
        cir.setReturnValue(i > 0 && this.rand.nextInt(i + 1) > 0 ? air : air - 1);
    }

    private int extraAir(Entity entity)
    {
        int result = 0;
        PlayerEntity player = entity instanceof PlayerEntity ? (PlayerEntity) entity : null;
        if (player != null)
        {
            if (player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == TerraItemRegistry.HELMET_DIVING.get()) { result = 1; }
            if (CurioHandler.hasBauble(player, TerraItemRegistry.GEAR_DIVING.get())) { result = 2; }
            if (CurioHandler.hasBauble(player, TerraItemRegistry.GEAR_DIVING_JELLYFISH.get())) { result = 2; }
            if (CurioHandler.hasBauble(player, TerraItemRegistry.GEAR_DIVING_ARCTIC.get())) { result = 3; }
        }
        return result;
    }

    private boolean iceWalk(Entity entity)
    {
        PlayerEntity player = entity instanceof PlayerEntity ? (PlayerEntity) entity : null;
        if (player != null)
        {
            return CurioHandler.hasBauble(player, TerraItemRegistry.BOOTS_ICE.get());
        }
        else return false;
    }
}