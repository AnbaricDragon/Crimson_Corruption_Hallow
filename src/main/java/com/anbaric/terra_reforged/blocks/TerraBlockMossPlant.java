package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class TerraBlockMossPlant extends BushBlock implements IPlantable
{
    public TerraBlockMossPlant(Properties properties)
    {
        super(properties);
    }

    @Override
    public PlantType getPlantType(BlockGetter world, BlockPos pos)
    {
        return TerraReforged.MOSS;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos)
    {
        return state.is(TerraTagRegistry.MOSS_PLANTERS);
    }
}
