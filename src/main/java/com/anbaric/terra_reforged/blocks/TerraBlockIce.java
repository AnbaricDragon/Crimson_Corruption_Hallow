package com.anbaric.terra_reforged.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class TerraBlockIce extends TerraBlockPlantable
{
    public TerraBlockIce(Properties properties, PlantType... plantType)
    {
        super(properties, plantType);
    }

    public boolean skipRendering(BlockState selfState, BlockState otherState, Direction facing)
    {
        return otherState.is(this) || super.skipRendering(selfState, otherState, facing);
    }
}
