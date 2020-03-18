package com.anbaric.terra_reforged.blocks.potionplants;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class TerraBlockShiverthorn extends TerraBlockPotionPlant
{
    public TerraBlockShiverthorn(Properties builder)
    {
        super(builder, 5, TerraItemRegistry.SEED_SHIVERTHORN.get());
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        Block target = state.getBlock();
        return target == Blocks.SNOW_BLOCK || target == TerraBlockRegistry.SNOW_CORRUPT.get() || target == TerraBlockRegistry.SNOW_CRIMSON.get() || target == TerraBlockRegistry.SNOW_HALLOWED.get() || target == Blocks.ICE/* || target == TerraBlockRegistry.ICE_PURPLE.get() || target == TerraBlockRegistry.ICE_RED.get() || target == TerraBlockRegistry.ICE_PINK.get() || target == Blocks.PACKED_ICE || target == TerraBlockRegistry.ICE_HARD_PURPLE.get() || target == TerraBlockRegistry.ICE_HARD_RED.get() || target == TerraBlockRegistry.ICE_HARD_PINK.get()*/;
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
                worldIn.setBlockState(pos, state.cycle(AGE));
            }
        }
    }
}
