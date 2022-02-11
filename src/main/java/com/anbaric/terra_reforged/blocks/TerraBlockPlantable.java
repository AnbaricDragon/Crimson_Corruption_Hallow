package com.anbaric.terra_reforged.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class TerraBlockPlantable extends Block
{
    private PlantType[] plantType;

    public TerraBlockPlantable(Properties properties, PlantType... plantType)
    {
        super(properties);
        this.plantType = plantType;
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable)
    {
        for (PlantType type : plantType)
        {
            if (plantable.getPlantType(world, pos) == type)
            {
                return true;
            }
        }
        return super.canSustainPlant(state, world, pos, facing, plantable);
    }
}
