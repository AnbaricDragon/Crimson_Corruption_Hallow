package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.util.packets.ChangeBiomePacket;
import net.minecraft.util.FastRandom;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeContainer;
import net.minecraft.world.chunk.IChunk;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.Optional;

public class BiomeChangeHandler
{
    public static void setBiomeAtPos(World world, BlockPos pos, ResourceLocation biome)
    {
        if (world.isClientSide()) { return; }
        RegistryKey<net.minecraft.world.biome.Biome> biomeKey = RegistryKey.create(Registry.BIOME_REGISTRY, biome);
        setBiomeKeyAtPos(world, pos, biomeKey);
        NetworkHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new ChangeBiomePacket(pos, biome));
    }

    public static void setBiomeKeyAtPos(World world, BlockPos pos, RegistryKey<Biome> biomeKey)
    {
        Optional<MutableRegistry<net.minecraft.world.biome.Biome>> biomeRegistry = world.registryAccess().registry(Registry.BIOME_REGISTRY);
        if (!biomeRegistry.isPresent()) { return; }
        Biome biome = biomeRegistry.get().get(biomeKey);
        if (biome == null) { return; }

        BiomeContainer biomeCollection = world.getChunk(pos).getBiomes();
        IChunk chunk = world.getChunk(pos);
        if (biomeCollection != null && biomeCollection.biomes != null)
        {
            Biome[] biomeArray = biomeCollection.biomes;
            int     biomeIndex = getBiomeIndex(pos.getX(), pos.getY(), pos.getZ(), 0L);
            if (biomeIndex < biomeArray.length)
            {
                System.out.println("Before changing Biome in Worldside: " + (world.isClientSide ? "Client" : "Server") + ", biomeArray is: " + biomeArray[biomeIndex].getRegistryName());
                //TODO Figure out why this isn't saving the biome
                biomeArray[biomeIndex] = biome;
                System.out.println("After changing Biome in Worldside: " + (world.isClientSide ? "Client" : "Server") + ", biomeArray is: " + biomeArray[biomeIndex].getRegistryName());
            }
            else
            {
                TerraReforged.LOGGER.error(String.format("Failed to set the biome at pos: %s; to: %s", pos, biome));
            }
        }
        chunk.setUnsaved(true);
    }

    private static final int WIDTH_BITS = (int) Math.round(Math.log(16.0D) / Math.log(2.0D)) - 2;

    private static int getBiomeIndex(int x, int y, int z, long seed) {
        int i = x - 2;
        int j = y - 2;
        int k = z - 2;
        int l = i >> 2;
        int i1 = j >> 2;
        int j1 = k >> 2;
        double d0 = (double) (i & 3) / 4.0D;
        double d1 = (double) (j & 3) / 4.0D;
        double d2 = (double) (k & 3) / 4.0D;
        double[] adouble = new double[8];

        for (int k1 = 0; k1 < 8; ++k1) {
            boolean flag = (k1 & 4) == 0;
            boolean flag1 = (k1 & 2) == 0;
            boolean flag2 = (k1 & 1) == 0;
            int l1 = flag ? l : l + 1;
            int i2 = flag1 ? i1 : i1 + 1;
            int j2 = flag2 ? j1 : j1 + 1;
            double d3 = flag ? d0 : d0 - 1.0D;
            double d4 = flag1 ? d1 : d1 - 1.0D;
            double d5 = flag2 ? d2 : d2 - 1.0D;
            adouble[k1] = distanceToCorner(seed, l1, i2, j2, d3, d4, d5);
        }

        int k2 = 0;
        double d6 = adouble[0];

        for (int l2 = 1; l2 < 8; ++l2) {
            if (d6 > adouble[l2]) {
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
        long lvt_11_1_ = FastRandom.next(seed, x);
        lvt_11_1_ = FastRandom.next(lvt_11_1_, y);
        lvt_11_1_ = FastRandom.next(lvt_11_1_, z);
        lvt_11_1_ = FastRandom.next(lvt_11_1_, x);
        lvt_11_1_ = FastRandom.next(lvt_11_1_, y);
        lvt_11_1_ = FastRandom.next(lvt_11_1_, z);
        double d0 = randomDouble(lvt_11_1_);
        lvt_11_1_ = FastRandom.next(lvt_11_1_, seed);
        double d1 = randomDouble(lvt_11_1_);
        lvt_11_1_ = FastRandom.next(lvt_11_1_, seed);
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
