package com.anbaric.terra_reforged.entities.goals;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.passive.BeeEntity;

public class BeeItemGoal extends NearestAttackableTargetGoal<LivingEntity>
{
    public BeeItemGoal(MobEntity goalOwnerIn, Class<LivingEntity> targetClassIn, boolean checkSight)
    {
        super(goalOwnerIn, targetClassIn, checkSight);
    }

    public static boolean isThingAttackable(LivingEntity ent)
    {
        return (ent.getType() != EntityType.BEE && ent.getType() != EntityType.PLAYER);
    }

    public BeeItemGoal(BeeEntity p_i225719_1_)
    {
        super(p_i225719_1_, LivingEntity.class, 10, true, false, BeeItemGoal::isThingAttackable);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean canUse()
    {
        return this.canSting() && super.canUse();
    }

    @Override
    protected void findTarget()
    {
        this.target = this.mob.level.getNearestEntity(this.targetType, this.targetConditions, this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ(), this.getTargetSearchArea(this.getFollowDistance()));
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean canContinueToUse()
    {
        boolean canSting = this.canSting();
        if (canSting && this.mob.getTarget() != null)
        {
            return super.canContinueToUse();
        }
        else
        {
            this.targetMob = null;
            return false;
        }
    }

    private boolean canSting()
    {
        BeeEntity beeentity = (BeeEntity) this.mob;
        return beeentity.isAggressive() && !beeentity.hasStung();
    }
}