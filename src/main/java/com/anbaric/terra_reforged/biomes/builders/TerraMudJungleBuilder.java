package com.anbaric.terra_reforged.biomes.builders;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraSurfaceBuilderRegistry;
import com.mojang.datafixers.Dynamic;
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

public class TerraMudJungleBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{

    private static final BlockState AIR = Blocks.AIR.getDefaultState();
    private static final BlockState SOIL_MUD = TerraBlockRegistry.SOIL_MUD.get().getDefaultState();
    protected long field_205552_a;
    protected OctavesNoiseGenerator noiseGen;

    public TerraMudJungleBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> function)
    {
        super(function);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
    {
        SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, TerraSurfaceBuilderRegistry.MUD_JUNGLE_CONFIG);

        int              chunkSealevel = seaLevel + 1;
        int              chunkPosX     = x & 15;
        int              chunkPosZ     = z & 15;
        int              l             = (int) (noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        BlockPos.Mutable targetPos     = new BlockPos.Mutable();
        int              i1            = -1;
        BlockState       blockstate    = SOIL_MUD;
        BlockState       blockstate1   = SOIL_MUD;

        for (int iter = 127; iter >= 0; --iter)
        {
            targetPos.setPos(chunkPosX, iter, chunkPosZ);
            BlockState targetState = chunkIn.getBlockState(targetPos);
            if (targetState.getBlock() != null && !targetState.isAir())
            {
                if (targetState.getBlock() == defaultBlock.getBlock())
                {
                    if (i1 == -1)
                    {
                        if (l <= 0)
                        {
                            blockstate = AIR;
                            blockstate1 = SOIL_MUD;
                        }
                        else if (iter >= chunkSealevel - 4 && iter <= chunkSealevel + 1)
                        {
                            blockstate = SOIL_MUD;
                            blockstate1 = SOIL_MUD;
                        }

                        if (iter < chunkSealevel && (blockstate == null || blockstate.isAir()))
                        {
                            blockstate = defaultFluid;
                        }

                        i1 = l;
                        if (iter >= chunkSealevel - 1)
                        {
                            chunkIn.setBlockState(targetPos, blockstate, false);
                        }
                        else
                        {
                            chunkIn.setBlockState(targetPos, blockstate1, false);
                        }
                    }
                    else if (i1 > 0)
                    {
                        --i1;
                        chunkIn.setBlockState(targetPos, blockstate1, false);
                    }
                }
            }
            else
            {
                i1 = -1;
            }
        }
    }
}
