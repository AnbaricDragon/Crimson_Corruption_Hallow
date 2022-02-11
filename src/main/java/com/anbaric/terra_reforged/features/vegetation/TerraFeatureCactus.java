package com.anbaric.terra_reforged.features.vegetation;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.common.Tags;

import java.util.Random;

public class TerraFeatureCactus extends Feature<NoneFeatureConfiguration>
{
    private Block cactus;

    public TerraFeatureCactus(Block block)
    {
        super(NoneFeatureConfiguration.CODEC);
        this.cactus = block;
    }

    @Override
    public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        pos = topBlock(world, pos);

        if (world.getBlockState(pos.down()).getBlock().isIn(Tags.Blocks.SAND) )
        {
            int height = rand.nextInt(2) + 1;
            for (int i = 0; i < height; i++)
            {
                world.setBlockState(pos.up(i), cactus.getDefaultState(), 4);
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
            if (world.getBlockState(pos.offset(Direction.byHorizontalIndex(i))).getBlock() != Blocks.AIR)
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
            targetPos = targetPos.up();
        }
        return targetPos;
    }
}
