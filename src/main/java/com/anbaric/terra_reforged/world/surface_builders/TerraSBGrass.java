package com.anbaric.terra_reforged.world.surface_builders;

import com.anbaric.terra_reforged.util.handlers.SpreadingHandler;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class TerraSBGrass extends SurfaceBuilder<SurfaceBuilderConfig>
{
    public BlockState STONE;
//    public BlockState GRASS;
    public static final BlockState GRASS = Blocks.GRASS_BLOCK.getDefaultState();
    public static final BlockState DIRT = Blocks.DIRT.getDefaultState();

    public TerraSBGrass(SpreadingHandler.EnumBiomeType biomeType)
    {
        super(SurfaceBuilderConfig.CODEC);
        this.STONE = SpreadingHandler.EnumBiomeBlockType.STONE.getBiomeBlock(biomeType).getDefaultState();
//        this.GRASS = SpreadingHandler.EnumBiomeBlockType.GRASS.getBiomeBlock(biomeType).getDefaultState();
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
    {
        int              chunkPosX  = x & 15;
        int              chunkPosZ  = z & 15;
        BlockPos.Mutable targetPos  = new BlockPos.Mutable();
        int              blockFound = startHeight;
        boolean          found      = false;

        for (int iter = startHeight; iter > 0; --iter)
        {
            targetPos.setPos(chunkPosX, iter, chunkPosZ);
            BlockState targetState = chunkIn.getBlockState(targetPos);

            if (targetState.getBlock() == defaultBlock.getBlock())
            {
                if (!found)
                {
                    found = true;
                    blockFound = iter;
                }
                if (iter == blockFound)
                {
                    if (chunkIn.getBlockState(targetPos.up()) != defaultFluid)
                    {
                        chunkIn.setBlockState(targetPos, GRASS, false);
                    }
                    else
                    {
                        chunkIn.setBlockState(targetPos, DIRT, false);
                    }
                }
                else if (iter >= blockFound - 4)
                {
                    chunkIn.setBlockState(targetPos, DIRT, false);
                }
                else
                {
                    chunkIn.setBlockState(targetPos, STONE, false);
                }
            }
        }
    }
}