package com.anbaric.terra_reforged.blocks.potionplants;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tags.ITag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class TerraBlockMoonglow extends TerraBlockPotionPlant
{
    public TerraBlockMoonglow(Properties builder, ITag<Block> tag)
    {
        super(builder, tag);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        Block target = state.getBlock();
        return target == TerraBlockRegistry.SOIL_MUD.get() || target == TerraBlockRegistry.GRASS_JUNGLE.get() || target == TerraBlockRegistry.GRASS_MUSHROOM.get();
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
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
                if (!worldIn.isDaytime() && state.get(AGE) == 1) {worldIn.setBlockState(pos, this.getDefaultState().with(AGE, 2));}
                if (worldIn.isDaytime() && state.get(AGE) == 2) {worldIn.setBlockState(pos, this.getDefaultState().with(AGE, 1));}
            }
        }
    }
}