package com.anbaric.terra_reforged.util.mixins;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public class TerraBlockBehaviourMixin
{
    private static VoxelShape fullLiquidShape = Block.box(0, 0, 0, 16, 14.3, 16);

    @Inject(method = "getCollisionShape(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;", at = @At(value = "HEAD"), cancellable = true)
    private void waterWalk(BlockGetter world, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> callback)
    {
        if (waterWalk((BlockBehaviour.BlockStateBase) (Object) this, world, pos, context))
        {
            callback.setReturnValue(fullLiquidShape);
        }
    }

    private static boolean waterWalk(BlockBehaviour.BlockStateBase state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        if (context instanceof EntityCollisionContext entityContext)
        {
            Entity entity = entityContext.getEntity();
            if (entity instanceof Player && state.getBlock() instanceof LiquidBlock && entity.getPose() != Pose.CROUCHING)
            {
                Player player = (Player) entity;
                boolean isSpace = world.getBlockState(pos.above()).isAir();
                boolean canWaterWalk = !player.isInWater() && state.getMaterial() == Material.WATER && (CurioHandler.hasBauble(player, TerraTagRegistry.WATER_WALKERS) || player.hasEffect(TerraEffectRegistry.WATER_WALKING.get()));
                boolean canLavaWalk = !player.isInLava() && state.getMaterial() == Material.LAVA && (CurioHandler.hasBauble(player, TerraTagRegistry.LAVA_WALKERS) || player.getActiveEffects().stream().anyMatch(potion -> potion.getEffect() == TerraEffectRegistry.WATER_WALKING.get() && potion.getAmplifier() == 1));
                return isSpace && (canLavaWalk || canWaterWalk);
            }
        }
        return false;
    }
}