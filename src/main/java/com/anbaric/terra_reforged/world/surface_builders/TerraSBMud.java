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

public class TerraSBMud extends SurfaceBuilder<SurfaceBuilderConfig>
{
    public static final BlockState GRASS = TerraBlockRegistry.GRASS_JUNGLE.get().getDefaultState();
    public static final BlockState MUD = TerraBlockRegistry.SOIL_MUD.get().getDefaultState();

    public TerraSBMud()
    {
        super(SurfaceBuilderConfig.CODEC);
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
                        chunkIn.setBlockState(targetPos, MUD, false);
                    }
                }
                else
                {
                    chunkIn.setBlockState(targetPos, MUD, false);
                }
            }
        }
    }
}