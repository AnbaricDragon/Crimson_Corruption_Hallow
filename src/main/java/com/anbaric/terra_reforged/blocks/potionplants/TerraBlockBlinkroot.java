package com.anbaric.terra_reforged.blocks.potionplants;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.ITag;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.PlantType;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class TerraBlockBlinkroot extends TerraBlockPotionPlant
{
    public TerraBlockBlinkroot(Properties builder, ITag<Block> tag)
    {
        super(builder, tag);
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos)
    {
        return PlantType.CAVE;
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
                if (MathHelper.floor(worldIn.getGameTime() / 2000) % 2 == 0 && state.getValue(AGE) == 1) {worldIn.setBlockAndUpdate(pos, this.defaultBlockState().setValue(AGE, 2));}
                if (MathHelper.floor(worldIn.getGameTime() / 2000) % 2 != 0 && state.getValue(AGE) == 2) {worldIn.setBlockAndUpdate(pos, this.defaultBlockState().setValue(AGE, 1));}
            }
        }
    }
}
