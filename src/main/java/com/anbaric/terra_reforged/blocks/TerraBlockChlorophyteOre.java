package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Iterator;
import java.util.Random;

public class TerraBlockChlorophyteOre extends Block
{
    public TerraBlockChlorophyteOre(Properties properties)
    {
        super(properties);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        int maxOres = 10;

        if (random.nextInt(10) != 0)
        {
            return;
        }

        Iterator cubicRange = BlockPos.getAllInBoxMutable(pos.add(-pos.getX() % 16, -pos.getY(), -pos.getZ() % 16), pos.add(15 - (pos.getX() % 16), 256 - pos.getY(), 15 - (pos.getZ() % 16))).iterator();

        while (cubicRange.hasNext())
        {
            BlockPos blockpos = (BlockPos) cubicRange.next();
            if (worldIn.getBlockState(blockpos).getBlock() == this)
            {
                --maxOres;
                if (maxOres <= 0)
                {
                    return;
                }
            }
        }

        BlockPos targetPos   = pos.add(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
        Block    targetBlock = worldIn.getBlockState(targetPos).getBlock();

        if (targetBlock == TerraBlockRegistry.SOIL_MUD.get())
        {
            worldIn.setBlockState(targetPos, this.getDefaultState());
        }
    }
}
