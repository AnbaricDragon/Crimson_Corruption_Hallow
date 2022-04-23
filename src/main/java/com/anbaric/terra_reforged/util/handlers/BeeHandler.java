package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.function.Predicate;

public class BeeHandler
{
    public static void spawnAngryBees(ServerLevel world, BlockPos pos, float distance)
    {
        AABB targetBox = (new AABB(pos)).inflate(distance);

        List<LivingEntity> foundTargets = world.getEntitiesOfClass(LivingEntity.class, targetBox, VALID_BEE_TARGET);

        int bees = 1 + world.random.nextInt(3);

        int maxTime = 600;

        for (int i = 0; i < bees; i++)
        {
            Entity beeEntity = EntityType.BEE.spawn(world, null, null, pos, MobSpawnType.MOB_SUMMONED, false, false);
            if (beeEntity instanceof Bee)
            {
                Bee bee = (Bee) beeEntity;
                bee.setPos(pos.getX(), pos.getY(), pos.getZ());
                bee.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, maxTime, 1, false, false));
                bee.addEffect(new MobEffectInstance(TerraEffectRegistry.LOOMING_DEATH.get(), maxTime, 0, false, false));
                LivingEntity target = foundTargets.get(i);
                bee.setPersistentAngerTarget(target.getUUID());
            }
        }
    }

    public static final Predicate<Entity> VALID_BEE_TARGET = (entity) -> !(entity instanceof Bee && entity instanceof Player);
}
