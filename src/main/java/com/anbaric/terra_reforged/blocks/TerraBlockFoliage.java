package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class TerraBlockFoliage extends MushroomBlock
{
    public TerraBlockFoliage(Block.Properties builder)
    {
        super(builder.notSolid());
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        BlockState soilState = worldIn.getBlockState(pos.down());
        Block soil = worldIn.getBlockState(pos.down()).getBlock();

        if (this == TerraBlockRegistry.PLANT_MOSS_RED.get())
        {
            return soil == TerraBlockRegistry.STONE_MOSS_RED.get();
        }
        if (this == TerraBlockRegistry.PLANT_MOSS_YELLOW.get())
        {
            return soil == TerraBlockRegistry.STONE_MOSS_YELLOW.get();
        }
        if (this == TerraBlockRegistry.PLANT_MOSS_GREEN.get())
        {
            return soil == TerraBlockRegistry.STONE_MOSS_GREEN.get();
        }
        if (this == TerraBlockRegistry.PLANT_MOSS_BLUE.get())
        {
            return soil == TerraBlockRegistry.STONE_MOSS_BLUE.get();
        }
        if (this == TerraBlockRegistry.PLANT_MOSS_PURPLE.get())
        {
            return soil == TerraBlockRegistry.STONE_MOSS_PURPLE.get();
        }
        if (this == TerraBlockRegistry.PLANT_MOSS_FIRE.get())
        {
            return soil == TerraBlockRegistry.STONE_MOSS_FIRE.get();
        }
        if (this == TerraBlockRegistry.PLANT_MUSHROOM_GLOWING.get())
        {
            return soil == TerraBlockRegistry.GRASS_MUSHROOM.get();
        }
        if (this == TerraBlockRegistry.PLANT_MUSHROOM_VILE.get())
        {
            return soil == TerraBlockRegistry.GRASS_CORRUPT.get();
        }
        if (this == TerraBlockRegistry.PLANT_MUSHROOM_VICIOUS.get())
        {
            return soil == TerraBlockRegistry.GRASS_CRIMSON.get();
        }

        return soilState.canSustainPlant(worldIn, pos.down(), Direction.UP, this);
    }
}
