package com.anbaric.terra_reforged.items.accessories;

import com.anbaric.terra_reforged.TerraReforged;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class TerraItemRadar extends Item
{
    public TerraItemRadar()
    {
        super(new Properties().tab(TerraReforged.TERRA_TOOLS_TAB).stacksTo(1));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int p_41407_, boolean p_41408_)
    {
        if (entity.tickCount % 100 == 0)
        {
            CompoundTag compound  = stack.getOrCreateTag();
            AABB        rangeAABB = new AABB(entity.getOnPos().above()).inflate(24);
            List<Mob>   mobList   = entity.level.getEntitiesOfClass(Mob.class, rangeAABB);
            compound.putInt("radar_mob_count", mobList.size());
        }
    }
}
