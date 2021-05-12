package com.anbaric.terra_reforged.entities.projectiles;

import com.anbaric.terra_reforged.util.init.TerraEntityRegistry;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class TerraProjectileSwordTerra extends ProjectileItemEntity
{
    private int pierceCount = 3;

    public TerraProjectileSwordTerra(EntityType<TerraProjectileSwordTerra> type, World world)
    {
        super(type, world);
    }

    public TerraProjectileSwordTerra(LivingEntity entity, World world)
    {
        super(TerraEntityRegistry.PROJECTILE_SWORD_TERRA.get(), entity, world);
    }

    public TerraProjectileSwordTerra(double x, double y, double z, World world)
    {
        super(TerraEntityRegistry.PROJECTILE_SWORD_TERRA.get(), x, y, z, world);
    }

    @Override
    protected Item getDefaultItem()
    {
        return TerraItemRegistry.INGOT_COBALT.get();
    }

    @Override
    public IPacket<?> createSpawnPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void onImpact(RayTraceResult result)
    {
        RayTraceResult.Type target = result.getType();
        if (target == RayTraceResult.Type.ENTITY)
        {
            if (this.pierceCount > 0)
            {
                this.onEntityHit((EntityRayTraceResult) result);
                pierceCount--;
            }
            else
            {
                this.remove();
            }
        }
        else if (target == RayTraceResult.Type.BLOCK)
        {
            this.remove();
        }
    }
}
