package com.anbaric.terra_reforged;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;
import java.util.Set;

public class TEST
{
//    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
//    {
//        Block target = state.getBlock();
//        if (this == TerraBlockRegistry.SAPLING_BOREAL.get()) {return target == Blocks.SNOW_BLOCK || target == TerraBlockRegistry.SNOW_CORRUPT.get() || target == TerraBlockRegistry.SNOW_CRIMSON.get() || target == TerraBlockRegistry.SNOW_HALLOWED.get() || target == Blocks.DIRT || target == Blocks.GRASS_BLOCK;}
//        else if (this == TerraBlockRegistry.SAPLING_PALM.get()) {return target == Blocks.SAND || target == TerraBlockRegistry.SAND_EBON.get() || target == TerraBlockRegistry.SAND_CRIM.get() || target == TerraBlockRegistry.SAND_PEARL.get() || target == Blocks.DIRT || target == Blocks.GRASS_BLOCK;}
//        else if (this == TerraBlockRegistry.SAPLING_MAHOGANY.get()) {return target == TerraBlockRegistry.SOIL_MUD.get() || target == TerraBlockRegistry.GRASS_JUNGLE.get() || target == TerraBlockRegistry.GRASS_MUSHROOM.get();}
//        else if (this == TerraBlockRegistry.SAPLING_EBON.get()) {return target == TerraBlockRegistry.GRASS_CORRUPT.get() || target == Blocks.DIRT || target == Blocks.GRASS_BLOCK;}
//        else if (this == TerraBlockRegistry.SAPLING_SHADE.get()) {return target == TerraBlockRegistry.GRASS_CRIMSON.get() || target == Blocks.DIRT || target == Blocks.GRASS_BLOCK;}
//        else return this == TerraBlockRegistry.SAPLING_PEARL.get() ? target == TerraBlockRegistry.GRASS_HALLOWED.get() || target == Blocks.DIRT || target == Blocks.GRASS_BLOCK : target == Blocks.GRASS_BLOCK || target == Blocks.DIRT;
//    }


}
