package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class RevengeHandler
{
    public static final Predicate<Entity> VALID_BEE_TARGET = (entity) -> !(entity instanceof Bee && entity instanceof Player);

    public static void spawnAngryBees(ServerLevel world, BlockPos pos, float distance, int beeCurioCount)
    {
        AABB targetBox = (new AABB(pos)).inflate(distance);

        List<LivingEntity> foundTargets = world.getEntitiesOfClass(LivingEntity.class, targetBox, VALID_BEE_TARGET);

        int bees = beeCurioCount + world.random.nextInt(beeCurioCount*3);

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
                LivingEntity target = foundTargets.get(0);
                bee.setTarget(target);
            }
        }
    }

    public static void spawnArrows(ServerLevel world, BlockPos pos, Random rand)
    {
        for (int i = 0; i < 3; i++)
        {
            int offsetY = pos.getY() + rand.nextInt(5) + 15;
            BlockPos      arrowPos    = new BlockPos(pos.getX() + 5 - rand.nextInt(10), offsetY, pos.getZ() + 5 - rand.nextInt(10));
            AbstractArrow arrowEntity = new Arrow(world, arrowPos.getX(), arrowPos.getY(), arrowPos.getZ());

            double d1 = pos.getX() - arrowPos.getX();
            double d2 = pos.getY() - offsetY;
            double d3 = pos.getZ() - arrowPos.getZ();
            float f = Mth.sqrt((float) (d1 * d1 + d3 * d3)) * 0.2F;
            arrowEntity.shoot(d1, d2 + f, d3, 3.0F, 0.0F);
            world.addFreshEntity(arrowEntity);
        }
    }
}
