package com.anbaric.terra_reforged.features.landscape;

import com.anbaric.terra_reforged.capabilities.hardmode.TerraCapabilityWorldProgression;
import com.anbaric.terra_reforged.util.handlers.SpreadingHandler.EnumBiomeBlockType;
import com.anbaric.terra_reforged.util.handlers.SpreadingHandler.EnumBiomeType;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.BitSet;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class TerraFeatureOres extends Feature<OreFeatureConfig>
{
    public TerraFeatureOres()
    {
        super(OreFeatureConfig.CODEC);
    }

    public boolean isAltarOre(Block block)
    {
        return block == TerraBlockRegistry.ORE_COBALT_PURE.get() || block == TerraBlockRegistry.ORE_PALLADIUM_PURE.get() || block == TerraBlockRegistry.ORE_MYTHRIL_PURE.get() || block == TerraBlockRegistry.ORE_ORICHALCUM_PURE.get() || block == TerraBlockRegistry.ORE_ADAMANTITE_PURE.get() || block == TerraBlockRegistry.ORE_TITANIUM_PURE.get();
    }

    public float getDebuff(Block block)
    {
        float debuff = 0;
        if (block == TerraBlockRegistry.ORE_COBALT_PURE.get() || block == TerraBlockRegistry.ORE_PALLADIUM_PURE.get()) { debuff = 0.74F; }
        if (block == TerraBlockRegistry.ORE_MYTHRIL_PURE.get() || block == TerraBlockRegistry.ORE_ORICHALCUM_PURE.get()) { debuff = 0.49F; }
        if (block == TerraBlockRegistry.ORE_ADAMANTITE_PURE.get() || block == TerraBlockRegistry.ORE_TITANIUM_PURE.get()) { debuff = 0.24F; }
        return debuff;
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, OreFeatureConfig config)
    {
        AtomicInteger altarsBroken = new AtomicInteger();
        reader.getWorld().getCapability(TerraCapabilityWorldProgression.WORLD_PROGRESSION_CAPABILITY).ifPresent(cap -> altarsBroken.set(cap.getAltarsBroken())
        );
        Block inputBlock = config.state.getBlock();

        int newSize = isAltarOre(inputBlock) ? Math.min((int) (altarsBroken.get() * getDebuff(inputBlock)), config.size) : config.size;
        if (isAltarOre(inputBlock)) { System.out.println(newSize + " " + inputBlock); }
        float  f  = rand.nextFloat() * (float) Math.PI;
        float  veinSize = (float) newSize / 8.0F;
        int    i  = MathHelper.ceil(((float) newSize / 16.0F * 2.0F + 1.0F) / 2.0F);
        double d0 = (double) pos.getX() + Math.sin((double) f) * (double) veinSize;
        double d1 = (double) pos.getX() - Math.sin((double) f) * (double) veinSize;
        double d2 = (double) pos.getZ() + Math.cos((double) f) * (double) veinSize;
        double d3 = (double) pos.getZ() - Math.cos((double) f) * (double) veinSize;
        int    j  = 2;
        double d4 = (double) (pos.getY() + rand.nextInt(3) - 2);
        double d5 = (double) (pos.getY() + rand.nextInt(3) - 2);
        int    k  = pos.getX() - MathHelper.ceil(veinSize) - i;
        int    l  = pos.getY() - 2 - i;
        int    i1 = pos.getZ() - MathHelper.ceil(veinSize) - i;
        int    j1 = 2 * (MathHelper.ceil(veinSize) + i);
        int    k1 = 2 * (2 + i);

        for (int l1 = k; l1 <= k + j1; ++l1)
        {
            for (int i2 = i1; i2 <= i1 + j1; ++i2)
            {
                if (l <= reader.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, l1, i2))
                {
                    return this.func_207803_a(reader, rand, config, d0, d1, d2, d3, d4, d5, k, l, i1, j1, k1, newSize);
                }
            }
        }
        return false;
    }

    protected boolean func_207803_a(IWorld world, Random random, OreFeatureConfig config, double p_207803_4_, double p_207803_6_, double p_207803_8_, double p_207803_10_, double p_207803_12_, double p_207803_14_, int p_207803_16_, int p_207803_17_, int p_207803_18_, int p_207803_19_, int p_207803_20_, int newSize)
    {
        int i = 0;
        BitSet bitset = new BitSet(p_207803_19_ * p_207803_20_ * p_207803_19_);
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
        int j = newSize;
        double[] adouble = new double[j * 4];

        for(int k = 0; k < j; ++k) {
            float f = (float)k / (float)j;
            double d0 = MathHelper.lerp((double)f, p_207803_4_, p_207803_6_);
            double d2 = MathHelper.lerp((double)f, p_207803_12_, p_207803_14_);
            double d4 = MathHelper.lerp((double)f, p_207803_8_, p_207803_10_);
            double d6 = random.nextDouble() * (double)j / 16.0D;
            double d7 = ((double)(MathHelper.sin((float)Math.PI * f) + 1.0F) * d6 + 1.0D) / 2.0D;
            adouble[k * 4 + 0] = d0;
            adouble[k * 4 + 1] = d2;
            adouble[k * 4 + 2] = d4;
            adouble[k * 4 + 3] = d7;
        }

        for (int i3 = 0; i3 < j - 1; ++i3)
        {
            if (!(adouble[i3 * 4 + 3] <= 0.0D))
            {
                for (int k3 = i3 + 1; k3 < j; ++k3)
                {
                    if (!(adouble[k3 * 4 + 3] <= 0.0D))
                    {
                        double d12 = adouble[i3 * 4 + 0] - adouble[k3 * 4 + 0];
                        double d13 = adouble[i3 * 4 + 1] - adouble[k3 * 4 + 1];
                        double d14 = adouble[i3 * 4 + 2] - adouble[k3 * 4 + 2];
                        double d15 = adouble[i3 * 4 + 3] - adouble[k3 * 4 + 3];
                        if (d15 * d15 > d12 * d12 + d13 * d13 + d14 * d14)
                        {
                            if (d15 > 0.0D)
                            {
                                adouble[k3 * 4 + 3] = -1.0D;
                            }
                            else
                            {
                                adouble[i3 * 4 + 3] = -1.0D;
                            }
                        }
                    }
                }
            }
        }

        for (int j3 = 0; j3 < j; ++j3)
        {
            double d11 = adouble[j3 * 4 + 3];
            if (!(d11 < 0.0D))
            {
                double d1 = adouble[j3 * 4 + 0];
                double d3 = adouble[j3 * 4 + 1];
                double d5 = adouble[j3 * 4 + 2];
                int    l  = Math.max(MathHelper.floor(d1 - d11), p_207803_16_);
                int    l3 = Math.max(MathHelper.floor(d3 - d11), p_207803_17_);
                int    i1 = Math.max(MathHelper.floor(d5 - d11), p_207803_18_);
                int    j1 = Math.max(MathHelper.floor(d1 + d11), l);
                int    k1 = Math.max(MathHelper.floor(d3 + d11), l3);
                int    l1 = Math.max(MathHelper.floor(d5 + d11), i1);

                for (int i2 = l; i2 <= j1; ++i2)
                {
                    double d8 = ((double) i2 + 0.5D - d1) / d11;
                    if (d8 * d8 < 1.0D)
                    {
                        for (int j2 = l3; j2 <= k1; ++j2)
                        {
                            double d9 = ((double) j2 + 0.5D - d3) / d11;
                            if (d8 * d8 + d9 * d9 < 1.0D)
                            {
                                for (int k2 = i1; k2 <= l1; ++k2)
                                {
                                    double d10 = ((double) k2 + 0.5D - d5) / d11;
                                    if (d8 * d8 + d9 * d9 + d10 * d10 < 1.0D)
                                    {
                                        int l2 = i2 - p_207803_16_ + (j2 - p_207803_17_) * p_207803_19_ + (k2 - p_207803_18_) * p_207803_19_ * p_207803_20_;
                                        if (!bitset.get(l2))
                                        {
                                            bitset.set(l2);
                                            blockpos$mutable.setPos(i2, j2, k2);
                                            if (config.target.test(world.getBlockState(blockpos$mutable), random))
                                            {
                                                world.setBlockState(blockpos$mutable, biomeBlock(world.getBiome(blockpos$mutable), config.state.getBlock()), 2);
                                                ++i;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return i > 0;
    }

    public EnumBiomeType getBiomeType(Biome biome)
    {
        String biomeName = biome.getRegistryName().toString();
        if (biomeName.contains("corrupt"))       { return EnumBiomeType.CORRUPT; }
        else if (biomeName.contains("crimson"))  { return EnumBiomeType.CRIMSON; }
        else if (biomeName.contains("hallowed")) { return EnumBiomeType.HALLOWED; }
        else if (biomeName.contains("mud"))   { return EnumBiomeType.JUNGLE; }
        else { return EnumBiomeType.PURE; }
    }

    public BlockState biomeBlock(Biome biome, Block target)
    {
        Block result = target;
        for (EnumBiomeBlockType block : EnumBiomeBlockType.values())
        {
            if (block.pure == target)
            {
                result = block.getBiomeBlock(getBiomeType(biome));
            }
        }
        return result.getDefaultState();
    }
}