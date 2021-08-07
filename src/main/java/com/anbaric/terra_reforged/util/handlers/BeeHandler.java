package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.entities.goals.BeeItemGoal;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Optional;

public class BeeHandler
{
    public static void spawnAngryBees(ServerWorld world, BlockPos pos, float distance)
    {
        AxisAlignedBB targetBox = new AxisAlignedBB(pos.offset(1, 1, 1), pos.offset(-1,-1,-1)).inflate(distance);

        Optional<LivingEntity> foundTarget = world.getEntitiesOfClass(LivingEntity.class, targetBox, BeeHandler::isValidBeeTarget).stream().reduce((entityA, entityB) -> world.random.nextBoolean() ? entityB : entityA);

        int bees = 1 + world.random.nextInt(3);

        int maxTime = 600;

        for (int i = 0; i < bees; i++)
        {
            Entity ent = EntityType.BEE.spawn(world, null, null, pos, SpawnReason.EVENT, false, false);
            if (ent instanceof BeeEntity)
            {
                BeeEntity bee = (BeeEntity) ent;
                bee.setPos(pos.getX(), pos.getY(), pos.getZ());
                bee.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, maxTime, 1, false, false));
                bee.addEffect(new EffectInstance(TerraEffectRegistry.LOOMING_DEATH.get(), maxTime, 0, false, false));
                foundTarget.ifPresent(target ->
                { // make bee angry at target
                    bee.setTarget(target);
                    bee.targetSelector.addGoal(0, new BeeItemGoal(bee));
                });
            }
        }
    }

    public static boolean isValidBeeTarget(LivingEntity ent)
    {
        return (ent.getType() != EntityType.BEE && ent.getType() != EntityType.PLAYER) && (!ent.isInvulnerable());
    }
}
