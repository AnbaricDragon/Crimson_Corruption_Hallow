package com.anbaric.terra_reforged.blocks.potionplants;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.ITag;
import net.minecraft.tags.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class TerraBlockDeathweed extends TerraBlockPotionPlant
{
    public TerraBlockDeathweed(Properties builder, ITag<Block> tag)
    {
        super(builder, tag);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        if (!worldIn.isClientSide)
        {
            if (!worldIn.isAreaLoaded(pos, 3))
            {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            }
            if (state.getValue(AGE) == 0 && random.nextFloat() < 0.01F)
            {
                worldIn.setBlockAndUpdate(pos, state.setValue(AGE, 1));
            }
            else
            {
                if (worldIn.getMoonBrightness() == 1.0f && !worldIn.isDay() && state.getValue(AGE) == 1) {worldIn.setBlockAndUpdate(pos, this.defaultBlockState().setValue(AGE, 2));}
                if (worldIn.getMoonBrightness() != 1.0f && state.getValue(AGE) == 2) {worldIn.setBlockAndUpdate(pos, this.defaultBlockState().setValue(AGE, 1));}
            }
        }
    }
}
