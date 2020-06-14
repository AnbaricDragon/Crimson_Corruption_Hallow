package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.AirBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class TerraBlockJungleAir extends AirBlock
{
    public TerraBlockJungleAir(Properties properties)
    {
        super(properties);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        if (worldIn.getBlockState(pos.down()).getBlock() == TerraBlockRegistry.SOIL_MUD.get())
        {
            worldIn.setBlockState(pos.down(), TerraBlockRegistry.GRASS_JUNGLE.get().getDefaultState());
        }
        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
    }
}
