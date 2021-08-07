package com.anbaric.terra_reforged.features.vegetation;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.Tags;

import java.util.Random;

public class TerraFeatureCactus extends Feature<NoFeatureConfig>
{
    private Block cactus;

    public TerraFeatureCactus(Block block)
    {
        super(NoFeatureConfig.CODEC);
        this.cactus = block;
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        pos = topBlock(world, pos);

        if (world.getBlockState(pos.below()).getBlock().is(Tags.Blocks.SAND) )
        {
            int height = rand.nextInt(2) + 1;
            for (int i = 0; i < height; i++)
            {
                world.setBlock(pos.above(i), cactus.defaultBlockState(), 4);
            }
            return true;
        }
        return false;
    }

    private boolean adjCheck(ISeedReader world, BlockPos pos)
    {
        boolean adjCheck = true;

        for (int i = 0; i <= 3; i++)
        {
            if (world.getBlockState(pos.relative(Direction.from2DDataValue(i))).getBlock() != Blocks.AIR)
            {
                adjCheck = false;
            }
        }
        return adjCheck;
    }

    private BlockPos topBlock(ISeedReader world, BlockPos pos)
    {
        BlockPos targetPos = pos;
        while (!adjCheck(world, targetPos))
        {
            targetPos = targetPos.above();
        }
        return targetPos;
    }
}
