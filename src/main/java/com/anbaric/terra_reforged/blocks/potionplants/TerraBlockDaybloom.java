package com.anbaric.terra_reforged.blocks.potionplants;

import com.anbaric.terra_reforged.blocks.TerraBlocks;
import com.anbaric.terra_reforged.items.TerraItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.Random;

public class TerraBlockDaybloom extends TerraBlockPotionPlant
{
    public TerraBlockDaybloom(Properties builder)
    {
        super(builder, 1, TerraItems.SEED_DAYBLOOM);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        Block target = state.getBlock();
        return target == Blocks.GRASS_BLOCK || target == TerraBlocks.GRASS_HALLOWED;
    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random)
    {
        if (!worldIn.isRemote)
        {
            if (!worldIn.isAreaLoaded(pos, 3))
            {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            }
            if (state.get(AGE) == 0 && random.nextFloat() < 0.01F)
            {
                worldIn.setBlockState(pos, state.with(AGE, 1));
            }
            else
            {
                if (worldIn.isDaytime() == true && state.get(AGE) == 1) {worldIn.setBlockState(pos, this.getDefaultState().with(AGE, Integer.valueOf(2)));}
                if (worldIn.isDaytime() == false && state.get(AGE) == 2) {worldIn.setBlockState(pos, this.getDefaultState().with(AGE, Integer.valueOf(1)));}
            }
        }
    }
}
