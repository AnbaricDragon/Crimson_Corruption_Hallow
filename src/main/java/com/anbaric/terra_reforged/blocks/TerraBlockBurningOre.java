package com.anbaric.terra_reforged.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TerraBlockBurningOre extends Block
{
    protected static final VoxelShape COLLISION_SHAPE = Block.box(1.0D, 1.0D, 1.0D, 15.0D, 15.0D, 15.0D);
    private final float burn;

    public TerraBlockBurningOre(Properties properties, float burnDamage)
    {
        super(properties);
        this.burn = burnDamage;
    }

    public VoxelShape getCollisionShape(BlockState p_51176_, BlockGetter p_51177_, BlockPos p_51178_, CollisionContext p_51179_)
    {
        return COLLISION_SHAPE;
    }

    @Override
    public void entityInside(BlockState p_60495_, Level p_60496_, BlockPos p_60497_, Entity entity)
    {
        //TODO Make this check for curios and armor
        if (!entity.fireImmune() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entity)) {
            entity.hurt(DamageSource.HOT_FLOOR, burn);
        }

        super.entityInside(p_60495_, p_60496_, p_60497_, entity);
    }
}
