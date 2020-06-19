package com.anbaric.terra_reforged.features.carvers;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

import java.util.Set;
import java.util.function.Function;

public class TerraWorldCarver extends CaveWorldCarver
{
    public static final WorldCarver<ProbabilityConfig> TERRA_CAVES = register("terra_caves", new TerraWorldCarver(ProbabilityConfig::deserialize, 256));
    public static final WorldCarver<ProbabilityConfig> TERRA_CAVERNS = register("terra_caverns", new TerraCavernCarver(ProbabilityConfig::deserialize, 256));
    public static final WorldCarver<ProbabilityConfig> TERRA_JUNGLE_CAVES = register("terra_jungle_caves", new TerraJungleCarver(ProbabilityConfig::deserialize, 256));
    public static final WorldCarver<ProbabilityConfig> TERRA_SNOW_CAVES = register("terra_snow_caves", new TerraSnowCarver(ProbabilityConfig::deserialize, 256));

    protected Set<Block> carvableTerraBlocks = ImmutableSet.of(
        TerraBlockRegistry.SOIL_MUD.get(), TerraBlockRegistry.SAND_HARD.get(), TerraBlockRegistry.GRASS_JUNGLE.get(),
        TerraBlockRegistry.GRASS_CORRUPT.get(), TerraBlockRegistry.GRASS_CRIMSON.get(), TerraBlockRegistry.GRASS_HALLOWED.get(),
        TerraBlockRegistry.STONE_EBON.get(), TerraBlockRegistry.STONE_CRIM.get(), TerraBlockRegistry.STONE_PEARL.get(),
        TerraBlockRegistry.SAND_EBON.get(), TerraBlockRegistry.SAND_CRIM.get(), TerraBlockRegistry.SAND_PEARL.get(),
        TerraBlockRegistry.SAND_HARDEBON.get(), TerraBlockRegistry.SAND_HARDCRIM.get(), TerraBlockRegistry.SAND_HARDPEARL.get(),
        TerraBlockRegistry.SANDSTONE_EBON.get(), TerraBlockRegistry.SANDSTONE_CRIM.get(), TerraBlockRegistry.SANDSTONE_PEARL.get(),
        Blocks.SNOW_BLOCK, TerraBlockRegistry.SNOW_CORRUPT.get(), TerraBlockRegistry.SNOW_CRIMSON.get(), TerraBlockRegistry.SNOW_HALLOWED.get(),
        Blocks.ICE, TerraBlockRegistry.ICE_PURPLE.get(), TerraBlockRegistry.ICE_RED.get(), TerraBlockRegistry.ICE_PINK.get(),
        Blocks.PACKED_ICE, TerraBlockRegistry.ICE_HARD_PURPLE.get(), TerraBlockRegistry.ICE_HARD_RED.get(), TerraBlockRegistry.ICE_HARD_PINK.get());

    public TerraWorldCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> deserialize, int maxHeight)
    {
        super(deserialize, maxHeight); // The 256 is the maximum height that this carver can cave to
    }

    @Override
    protected boolean canCarveBlock(BlockState blockState, BlockState aboveBlockState)
    {
        return carvableTerraBlocks.contains(blockState.getBlock()) || carvableBlocks.contains(blockState.getBlock());
    }
}