package com.anbaric.terra_reforged.biomes.builders;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class TerraBiomeBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{
    private final BlockState STONE;

    public TerraBiomeBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> config, BlockState stone)
    {
        super(config);
        this.STONE = stone;
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
    {
        this.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, config.getTop(), config.getUnder(), config.getUnderWaterMaterial(), seaLevel);
    }

    protected void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, BlockState top, BlockState middle, BlockState bottom, int sealevel)
    {
        int              chunkPosX = x & 15;
        int              chunkPosZ = z & 15;
        BlockPos.Mutable targetPos = new BlockPos.Mutable();
        int blockFound = startHeight;
        boolean found = false;

        for (int iter = startHeight; iter > 0; --iter)
        {
            targetPos.setPos(chunkPosX, iter, chunkPosZ);
            BlockState targetState = chunkIn.getBlockState(targetPos);

            if (targetState.getBlock() == defaultBlock.getBlock())
            {
                if(!found)
                {
                    found = true;
                    blockFound = iter;
                }
                if (iter == blockFound)
                {
                    if (chunkIn.getBlockState(targetPos.up()) != defaultFluid)
                    {
                        chunkIn.setBlockState(targetPos, top, false);
                    }
                    else
                    {
                        chunkIn.setBlockState(targetPos, bottom, false);
                    }
                }
                else if (iter >= blockFound - 4)
                {
                    chunkIn.setBlockState(targetPos, middle, false);
                }
                else
                {
                    chunkIn.setBlockState(targetPos, STONE, false);
                }
            }
        }
    }
}