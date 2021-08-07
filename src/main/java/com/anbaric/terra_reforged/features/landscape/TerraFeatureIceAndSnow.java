package com.anbaric.terra_reforged.features.landscape;

import com.anbaric.terra_reforged.util.handlers.SpreadingHandler.EnumBiomeBlockType;
import com.anbaric.terra_reforged.util.handlers.SpreadingHandler.EnumBiomeType;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowyDirtBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.LightType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

import java.util.Random;

public class TerraFeatureIceAndSnow extends Feature<NoFeatureConfig>
{
    private BlockState snow, ice;

    public TerraFeatureIceAndSnow(EnumBiomeType biomeType)
    {
        super(NoFeatureConfig.CODEC);
        this.snow = biomeBlock(biomeType, Blocks.SNOW);
        this.ice = biomeBlock(biomeType, Blocks.ICE);
    }

    public BlockState biomeBlock(EnumBiomeType type, Block target)
    {
        Block result = target;
        for (EnumBiomeBlockType block : EnumBiomeBlockType.values())
        {
            if (block.pure == target)
            {
                result = block.getBiomeBlock(type);
            }
        }
        return result.defaultBlockState();
    }

    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        BlockPos.Mutable blockpos$mutable  = new BlockPos.Mutable();
        BlockPos.Mutable blockpos$mutable1 = new BlockPos.Mutable();

        for (int i = 0; i < 16; ++i)
        {
            for (int j = 0; j < 16; ++j)
            {
                int k  = pos.getX() + i;
                int l  = pos.getZ() + j;
                int i1 = reader.getHeight(Heightmap.Type.MOTION_BLOCKING, k, l);
                blockpos$mutable.set(k, i1, l);
                blockpos$mutable1.set(blockpos$mutable).move(Direction.DOWN, 1);
                Biome biome = reader.getBiome(blockpos$mutable);
                if (biome.shouldFreeze(reader, blockpos$mutable1, false))
                {
                    reader.setBlock(blockpos$mutable1, ice, 2);
                }

                if (biome.shouldSnow(reader, blockpos$mutable))
                {
                    reader.setBlock(blockpos$mutable, snow, 2);
                    BlockState blockstate = reader.getBlockState(blockpos$mutable1);
                    if (blockstate.hasProperty(SnowyDirtBlock.SNOWY))
                    {
                        reader.setBlock(blockpos$mutable1, blockstate.setValue(SnowyDirtBlock.SNOWY, true), 2);
                    }
                }
            }
        }

        return true;
    }
}
