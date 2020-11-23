package com.anbaric.terra_reforged.features.landscape;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.SnowyDirtBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class TerraFeatureSnowLayer extends Feature<NoFeatureConfig>
{
    public final BlockState SNOW_LAYER;
    public final BlockState ICE;

    public TerraFeatureSnowLayer(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51435_1_, BlockState ice, BlockState snowLayer)
    {
        super(p_i51435_1_);
        this.SNOW_LAYER = snowLayer;
        this.ICE = ice;
    }

    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        BlockPos.Mutable targetPos  = new BlockPos.Mutable();
        BlockPos.Mutable targetPosDown = new BlockPos.Mutable();

        for (int i = 0; i < 16; ++i)
        {
            for (int j = 0; j < 16; ++j)
            {
                int k  = pos.getX() + i;
                int l  = pos.getZ() + j;
                int i1 = worldIn.getHeight(Heightmap.Type.MOTION_BLOCKING, k, l);
                targetPos.setPos(k, i1, l);
                targetPosDown.setPos(targetPos).move(Direction.DOWN, 1);
                Biome biome = worldIn.getBiome(targetPos);
                if (biome.doesWaterFreeze(worldIn, targetPosDown, false))
                {
                    worldIn.setBlockState(targetPosDown, this.ICE, 2);
                }

                if (biome.doesSnowGenerate(worldIn, targetPos))
                {
                    worldIn.setBlockState(targetPos, this.SNOW_LAYER, 2);
                    BlockState blockstate = worldIn.getBlockState(targetPosDown);
                    if (blockstate.has(SnowyDirtBlock.SNOWY))
                    {
                        worldIn.setBlockState(targetPosDown, blockstate.with(SnowyDirtBlock.SNOWY, Boolean.valueOf(true)), 2);
                    }
                }
            }
        }
        return true;
    }
}