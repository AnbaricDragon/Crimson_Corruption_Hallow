package com.anbaric.terra_reforged.util.mixins;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public class TerraAbstractBlockMixin
{
    private static VoxelShape fullLiquidShape = Block.makeCuboidShape(0, 0, 0, 16, 14.3, 16);

    @Inject(method = "getCollisionShape(Lnet/minecraft/world/IBlockReader;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/shapes/ISelectionContext;)Lnet/minecraft/util/math/shapes/VoxelShape;", at = @At("HEAD"), cancellable = true)
    private void waterWalk(IBlockReader worldIn, BlockPos pos, ISelectionContext context, CallbackInfoReturnable<VoxelShape> cir)
    {
        if (waterWalk((AbstractBlock.AbstractBlockState) (Object) this, worldIn, pos, context, cir))
        {
            cir.setReturnValue(fullLiquidShape);
        }
    }

    private static boolean waterWalk(AbstractBlock.AbstractBlockState state, IBlockReader world, BlockPos pos, ISelectionContext context, CallbackInfoReturnable<VoxelShape> cir)
    {
        Entity entity = context.getEntity();
        if (entity instanceof PlayerEntity && state.getBlock() instanceof FlowingFluidBlock && entity.getPose() != Pose.CROUCHING)
        {
            PlayerEntity player = (PlayerEntity) entity;
            boolean isSpace = world.getBlockState(pos.up()).isAir(world, pos.up());
            boolean canWaterWalk = !player.isInWater() && state.getMaterial() == Material.WATER && CurioHandler.hasBauble(player, TerraTagRegistry.WATER_WALKERS);
            boolean canLavaWalk = !player.isInLava() && state.getMaterial() == Material.LAVA && CurioHandler.hasBauble(player, TerraTagRegistry.LAVA_WALKERS);
            return isSpace && (canLavaWalk || canWaterWalk);
        }
        return false;
    }
}