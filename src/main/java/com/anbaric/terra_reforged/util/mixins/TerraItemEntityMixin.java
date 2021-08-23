package com.anbaric.terra_reforged.util.mixins;

import net.minecraft.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemEntity.class)
public interface TerraItemEntityMixin
{
    @Accessor
    int getAge();

    @Accessor
    void setAge(int age);

    @Accessor
    int getPickupDelay();
}