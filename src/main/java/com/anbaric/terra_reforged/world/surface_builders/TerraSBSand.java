package com.anbaric.terra_reforged.world.surface_builders;

import com.anbaric.terra_reforged.util.handlers.SpreadingHandler;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class TerraSBSand extends SurfaceBuilder<SurfaceBuilderConfig>
{
    public BlockState STONE;
    public BlockState SAND;
    public BlockState SANDSTONE;
    public BlockState HARD_SAND;

    public TerraSBSand(SpreadingHandler.EnumBiomeType biomeType)
    {
        super(SurfaceBuilderConfig.field_237203_a_);
        this.STONE = SpreadingHandler.EnumBiomeBlockType.STONE.getBiomeBlock(biomeType).getDefaultState();
        this.SAND = SpreadingHandler.EnumBiomeBlockType.SAND.getBiomeBlock(biomeType).getDefaultState();
        this.SANDSTONE = SpreadingHandler.EnumBiomeBlockType.SANDSTONE.getBiomeBlock(biomeType).getDefaultState();
        this.HARD_SAND = SpreadingHandler.EnumBiomeBlockType.HARDSAND.getBiomeBlock(biomeType).getDefaultState();
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
                if (iter >= blockFound - 2)
                {
                    chunkIn.setBlockState(targetPos, SAND, false);
                }
                else if (iter >= blockFound - 5)
                {
                    chunkIn.setBlockState(targetPos, HARD_SAND, false);
                }
                else if (iter >= blockFound - (random.nextInt(3) + 6))
                {
                    chunkIn.setBlockState(targetPos, SANDSTONE, false);
                }
                else
                {
                    chunkIn.setBlockState(targetPos, STONE, false);
                }
            }
        }
    }
}
