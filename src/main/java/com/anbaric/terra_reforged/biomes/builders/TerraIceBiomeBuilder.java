package com.anbaric.terra_reforged.biomes.builders;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraSurfaceBuilderRegistry;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.OctavesNoiseGenerator;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class TerraIceBiomeBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{
    private final BlockState SNOW;
    private final BlockState ICE;
    private final BlockState PACKED_ICE;

    public TerraIceBiomeBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> function, BlockState snow, BlockState ice, BlockState hardIce)
    {
        super(function);
        this.SNOW = snow;
        this.ICE = ice;
        this.PACKED_ICE = hardIce;
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
    {
        int              chunkPosX = x & 15;
        int              chunkPosZ = z & 15;
        BlockPos.Mutable targetPos = new BlockPos.Mutable();

        for (int iter = startHeight-1; iter >= 0; --iter)
        {
            targetPos.setPos(chunkPosX, iter, chunkPosZ);

            if (iter > startHeight - 4)
            {
                chunkIn.setBlockState(targetPos, SNOW, false);
            }
            else
            {
                if (!chunkIn.getBlockState(targetPos).isAir())
                {
                    if (random.nextFloat() > 0.4)
                    {
                        chunkIn.setBlockState(targetPos, PACKED_ICE, false);
                    }
                    else
                    {
                        chunkIn.setBlockState(targetPos, ICE, false);
                    }
                }
            }
        }
    }
}
