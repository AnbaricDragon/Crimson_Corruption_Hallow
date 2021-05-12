package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.TerraReforged;
import net.minecraft.util.FastRandom;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeContainer;
import net.minecraft.world.chunk.IChunk;

import java.util.Optional;

public class BiomeChangeHandler
{
    public static void setBiome(World world, BlockPos pos, RegistryKey<Biome> biomeKey)
    {
        System.out.println("Preworked biome check --- " + world.getBiome(pos).getRegistryName());
        Optional<MutableRegistry<Biome>> biomeRegistry = world.func_241828_r().func_230521_a_(Registry.BIOME_KEY);
        if (!biomeRegistry.isPresent())
        {
            return;
        }
        Biome biome = biomeRegistry.get().getValueForKey(biomeKey);
        if (biome == null)
        {
            return;
        }
        else
        {
            System.out.println("Biome to be placed is " + biome.getRegistryName());
        }
        BiomeContainer biomeCollection = world.getChunk(pos).getBiomes();
        IChunk chunk = world.getChunk(pos);
        if (biomeCollection != null && biomeCollection.biomes != null)
        {
            Biome[] biomeArray = biomeCollection.biomes;
            int     biomeIndex = getBiomeIndex(pos.getX(), pos.getY(), pos.getZ(), 0L);
            if (biomeIndex < biomeArray.length)
            {
                System.out.println("BiomeArray is first " + biomeArray[biomeIndex].getRegistryName());
                //TODO Figure out why this isn't saving the biome
                biomeArray[biomeIndex] = biome;
                System.out.println("BiomeArray is now " + biomeArray[biomeIndex].getRegistryName());
            }
            else
            {
                TerraReforged.LOGGER.error(String.format("Failed to set biome at pos: %s; to biome: %s", pos, biome));
            }
        }
        chunk.setModified(true);
        System.out.println("Postworked biome check --- " + world.getBiome(pos).getRegistryName());
    }

    private static final int WIDTH_BITS = (int) Math.round(Math.log(16.0D) / Math.log(2.0D)) - 2;

    public static int getBiomeIndex(int x, int y, int z, long seed)
    {
        int      i       = x - 2;
        int      j       = y - 2;
        int      k       = z - 2;
        int      l       = i >> 2;
        int      i1      = j >> 2;
        int      j1      = k >> 2;
        double   d0      = (double) (i & 3) / 4.0D;
        double   d1      = (double) (j & 3) / 4.0D;
        double   d2      = (double) (k & 3) / 4.0D;
        double[] adouble = new double[8];

        for (int k1 = 0; k1 < 8; ++k1)
        {
            boolean flag  = (k1 & 4) == 0;
            boolean flag1 = (k1 & 2) == 0;
            boolean flag2 = (k1 & 1) == 0;
            int     l1    = flag ? l : l + 1;
            int     i2    = flag1 ? i1 : i1 + 1;
            int     j2    = flag2 ? j1 : j1 + 1;
            double  d3    = flag ? d0 : d0 - 1.0D;
            double  d4    = flag1 ? d1 : d1 - 1.0D;
            double  d5    = flag2 ? d2 : d2 - 1.0D;
            adouble[k1] = distanceToCorner(seed, l1, i2, j2, d3, d4, d5);
        }

        int    k2 = 0;
        double d6 = adouble[0];

        for (int l2 = 1; l2 < 8; ++l2)
        {
            if (d6 > adouble[l2])
            {
                k2 = l2;
                d6 = adouble[l2];
            }
        }

        int i3 = (k2 & 4) == 0 ? l : l + 1;
        int j3 = (k2 & 2) == 0 ? i1 : i1 + 1;
        int k3 = (k2 & 1) == 0 ? j1 : j1 + 1;

        int arrayIndex = i3 & BiomeContainer.HORIZONTAL_MASK;
        arrayIndex |= (k3 & BiomeContainer.HORIZONTAL_MASK) << WIDTH_BITS;
        return arrayIndex | MathHelper.clamp(j3, 0, BiomeContainer.VERTICAL_MASK) << WIDTH_BITS + WIDTH_BITS;
    }

    private static double distanceToCorner(long seed, int x, int y, int z, double scaleX, double scaleY, double scaleZ)
    {
        long lvt_11_1_ = FastRandom.mix(seed, x);
        lvt_11_1_ = FastRandom.mix(lvt_11_1_, y);
        lvt_11_1_ = FastRandom.mix(lvt_11_1_, z);
        lvt_11_1_ = FastRandom.mix(lvt_11_1_, x);
        lvt_11_1_ = FastRandom.mix(lvt_11_1_, y);
        lvt_11_1_ = FastRandom.mix(lvt_11_1_, z);
        double d0 = randomDouble(lvt_11_1_);
        lvt_11_1_ = FastRandom.mix(lvt_11_1_, seed);
        double d1 = randomDouble(lvt_11_1_);
        lvt_11_1_ = FastRandom.mix(lvt_11_1_, seed);
        double d2 = randomDouble(lvt_11_1_);
        return square(scaleZ + d2) + square(scaleY + d1) + square(scaleX + d0);
    }

    private static double randomDouble(long seed)
    {
        double d0 = (double) ((int) Math.floorMod(seed >> 24, 1024L)) / 1024.0D;
        return (d0 - 0.5D) * 0.9D;
    }

    private static double square(double x)
    {
        return x * x;
    }
}
