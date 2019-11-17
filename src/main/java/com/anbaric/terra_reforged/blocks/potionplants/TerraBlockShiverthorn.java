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

public class TerraBlockShiverthorn extends TerraBlockPotionPlant
{
    public TerraBlockShiverthorn(Properties builder)
    {
        super(builder, 5, TerraItems.SEED_SHIVERTHORN);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        Block target = state.getBlock();
        return target == Blocks.SNOW_BLOCK || target == TerraBlocks.SNOW_CORRUPT || target == TerraBlocks.SNOW_CRIMSON || target == TerraBlocks.SNOW_HALLOWED || target == Blocks.ICE || target == TerraBlocks.ICE_PURPLE || target == TerraBlocks.ICE_RED || target == TerraBlocks.ICE_PINK || target == Blocks.PACKED_ICE || target == TerraBlocks.ICE_HARD_PURPLE || target == TerraBlocks.ICE_HARD_RED || target == TerraBlocks.ICE_HARD_PINK;
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
            if (random.nextFloat() < 0.05F && state.get(AGE) != 2)
            {
                worldIn.setBlockState(pos, state.cycle(AGE));
            }
        }
    }
}
