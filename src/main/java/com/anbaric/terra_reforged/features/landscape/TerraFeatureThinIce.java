package com.anbaric.terra_reforged.features.landscape;

import com.anbaric.terra_reforged.util.handlers.SpreadingHandler.EnumBiomeBlockType;
import com.anbaric.terra_reforged.util.handlers.SpreadingHandler.EnumBiomeType;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.LightType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Random;

public class TerraFeatureThinIce extends Feature<NoFeatureConfig>
{
    private static final BlockState THIN_ICE = TerraBlockRegistry.ICE_THIN.get().defaultBlockState();

    public TerraFeatureThinIce()
    {
        super(NoFeatureConfig.CODEC);
    }

    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        int topBlockPos = generator.getSpawnHeight();
        BlockPos locationPos = new BlockPos(pos.getX(), topBlockPos, pos.getZ());
        int height = rand.nextInt(18) + 10;
        int radius = 4;

        while (!reader.getBlockState(locationPos).isAir(reader, locationPos))
        {
            if (locationPos.getY() > 0)
            {
                locationPos = locationPos.below();
            }
        }
        while (!reader.getBlockState(locationPos).canOcclude())
        {
            if (locationPos.getY() > 0)
            {
                locationPos = locationPos.below();
            }
        }

        BlockPos.Mutable workingPos = locationPos.mutable();

        reader.setBlock(pos.above(50), THIN_ICE, 3);

        for (int yPos = locationPos.getY() + 1; yPos >= locationPos.getY() - height; yPos--)
        {
            workingPos.set(locationPos.getX(), yPos, locationPos.getZ());
            if (!reader.getBlockState(workingPos).isAir(reader, workingPos) && reader.getBlockState(workingPos).getBlock() != Blocks.BEDROCK && reader.getBlockState(workingPos).getBlock() != Blocks.WATER)
            {
                reader.setBlock(workingPos, THIN_ICE, 3);
            }
//            for (int xPos = locationPos.getX() - radius; xPos <= locationPos.getX() + radius; xPos++)
//            {
//                for (int zPos = locationPos.getZ() - radius; zPos <= locationPos.getZ() + radius; zPos++)
//                {
//                    workingPos.setPos(xPos, yPos, zPos);
//                    if (!reader.getBlockState(workingPos).isAir(reader, workingPos) && reader.getBlockState(workingPos).getBlock() != Blocks.BEDROCK && reader.getBlockState(workingPos).getBlock() != Blocks.WATER)
//                    {
//                        reader.setBlockState(workingPos, THIN_ICE, 3);
//                    }
//                }
//            }
        }
        return true;
    }
}
