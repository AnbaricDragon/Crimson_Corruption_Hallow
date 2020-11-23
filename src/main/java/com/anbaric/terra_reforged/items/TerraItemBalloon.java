package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.capabilities.multijump.MultiJump;
import com.anbaric.terra_reforged.util.capabilities.multijump.MultiJumpProvider;
import com.anbaric.terra_reforged.util.capabilities.multijump.TerraCapabilityMultiJump;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class TerraItemBalloon extends Item
{
    public TerraItemBalloon(Properties builder)
    {
        super(builder);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        PlayerEntity player = null;

        if (entityIn instanceof PlayerEntity)
        {
            player = (PlayerEntity) entityIn;
        }
        if (player == null || player.isSpectator())
        {
            return;
        }

        if (this == TerraItemRegistry.BALLOON_BUNDLE.get())
        {
            player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 1, 3));
        }
        else
        {
            player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 1, 1));
        }
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context)
    {
        context.getPlayer().getCapability(TerraCapabilityMultiJump.MULTI_JUMP_CAPABILITY).ifPresent(cap -> System.out.println("I guess it's working? " + cap.getCanCloudJump()));

        return ActionResultType.PASS;
    }
}