package com.anbaric.terra_reforged.blocks.potionplants;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.ITag;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.PlantType;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class TerraBlockShiverthorn extends TerraBlockPotionPlant
{
    public TerraBlockShiverthorn(Properties builder, ITag<Block> tag)
    {
        super(builder, tag);
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos)
    {
        return TerraReforged.BOREAL;
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
            if (random.nextFloat() < 0.05F && state.get(AGE) != 2)
            {
                worldIn.setBlockState(pos, state.with(AGE, state.get(AGE) + 1));
            }
        }
    }
}
