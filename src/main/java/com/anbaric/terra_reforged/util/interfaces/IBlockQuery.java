package com.anbaric.terra_reforged.util.interfaces;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public interface IBlockQuery
{
    boolean matches(IWorld world, BlockPos pos);
}