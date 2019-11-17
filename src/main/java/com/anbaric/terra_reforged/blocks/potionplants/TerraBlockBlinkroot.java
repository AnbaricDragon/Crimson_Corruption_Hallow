package com.anbaric.terra_reforged.blocks.potionplants;

import com.anbaric.terra_reforged.blocks.TerraBlocks;
import com.anbaric.terra_reforged.items.TerraItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.Random;

public class TerraBlockBlinkroot extends TerraBlockPotionPlant
{
    public TerraBlockBlinkroot(Properties builder)
    {
        super(builder, 0, TerraItems.SEED_BLINKROOT);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        Block target = state.getBlock();
        return target == Blocks.DIRT || target == Blocks.GRASS_BLOCK || target == TerraBlocks.GRASS_CORRUPT || target == TerraBlocks.GRASS_CRIMSON || target == TerraBlocks.GRASS_HALLOWED || target == Blocks.STONE || target == TerraBlocks.STONE_EBON || target == TerraBlocks.STONE_CRIM || target == TerraBlocks.STONE_PEARL;
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
                if (MathHelper.floor(worldIn.getGameTime() / 2000) % 2 == 0 && state.get(AGE) == 1) {worldIn.setBlockState(pos, this.getDefaultState().with(AGE, Integer.valueOf(2)));}
                if (MathHelper.floor(worldIn.getGameTime() / 2000) % 2 != 0 && state.get(AGE) == 2) {worldIn.setBlockState(pos, this.getDefaultState().with(AGE, Integer.valueOf(1)));}
            }
        }
    }
}
