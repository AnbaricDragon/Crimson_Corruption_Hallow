package com.anbaric.terra_reforged.features.vegetation;

import net.minecraft.block.Block;
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
    public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        if (world.getBlockState(pos.down()).getBlock().isIn(Tags.Blocks.SAND))
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
}
