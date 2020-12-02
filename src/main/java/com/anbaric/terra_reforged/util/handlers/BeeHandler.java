package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

import java.util.Optional;
import java.util.Random;

public class BeeHandler
{
    public static void spawnAngryBees(ServerWorld world, BlockPos pos, float distance)
    {
        AxisAlignedBB targetBox = new AxisAlignedBB(pos.add(1, 1, 1), pos.add(-1,-1,-1)).grow(distance);

        Optional<LivingEntity> foundTarget = world.getEntitiesWithinAABB(LivingEntity.class, targetBox, BeeHandler::isValidBeeTarget).stream().reduce((entityA, entityB) -> world.rand.nextBoolean() ? entityB : entityA);

        int bees = 2 + world.rand.nextInt(3);

        int maxTime = 600;

        for (int i = 0; i < bees; i++)
        {
            Entity ent = EntityType.BEE.spawn(world, null, null, pos, SpawnReason.EVENT, false, false);
            if (ent instanceof BeeEntity)
            {
                BeeEntity bee = (BeeEntity) ent;
                bee.setPosition(pos.getX(), pos.getY(), pos.getZ());
                bee.addPotionEffect(new EffectInstance(Effects.SPEED, maxTime, 1, false, false));
                bee.addPotionEffect(new EffectInstance(TerraEffectRegistry.LOOMING_DEATH.get(), maxTime, 0, false, false));
                foundTarget.ifPresent(target ->
                { // make bee angry at target
                    bee.setAttackTarget(target);
                    bee.targetSelector.addGoal(0, new BeeGoalHandler(bee));
                });
            }
        }
    }

    public static boolean isValidBeeTarget(LivingEntity ent)
    {
        return (ent.getType() != EntityType.BEE && ent.getType() != EntityType.PLAYER) && (!ent.isInvulnerable());
    }
}
