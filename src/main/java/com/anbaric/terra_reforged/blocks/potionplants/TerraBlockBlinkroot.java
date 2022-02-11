package com.anbaric.terra_reforged.blocks.potionplants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.PlantType;

import java.util.Random;

public class TerraBlockBlinkroot extends TerraBlockPotionPlant
{
    public TerraBlockBlinkroot(BlockBehaviour.Properties builder, Tag.Named<Block> tag)
    {
        super(builder, tag);
    }

    @Override
    public PlantType getPlantType(BlockGetter world, BlockPos pos)
    {
        return PlantType.CAVE;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
    {
        return (isValidPosition(state, world, pos) || isInPlanter(state, world, pos)) || super.canSurvive(state, world, pos);
    }

    public boolean isValidPosition(BlockState state, LevelReader world, BlockPos pos)
    {
        return world.getBlockState(pos.below()).isSolidRender(world, pos);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random)
    {
        if (!world.isClientSide)
        {
            if (!world.isAreaLoaded(pos, 3))
            {
                return;
            }
            if (state.getValue(AGE) == 0)
            {
                world.setBlockAndUpdate(pos, state.cycle(AGE));
            }
            else
            {
                boolean isTime = Math.floor(world.getGameTime() / 2000) % 2 == 0;
                if (state.getValue(AGE) == 1 && isTime)
                {
                    world.setBlockAndUpdate(pos, state.setValue(AGE, 2));
                }
                if (state.getValue(AGE) == 2 && !isTime)
                {
                    world.setBlockAndUpdate(pos, state.setValue(AGE, 1));
                }
            }
        }
    }
}
