package com.anbaric.terra_reforged.features.trees;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class TerraTreePearl extends Tree
{
    public static final BlockState LOG = TerraBlockRegistry.LOG_PEARL.get().getDefaultState();
    public static final BlockState LEAF = TerraBlockRegistry.LEAF_PEARL_BLUE.get().getDefaultState();

    public static final TreeFeatureConfig BOREAL_TREE_CONFIG = new TreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(LOG),
            new SimpleBlockStateProvider(LEAF),
            new BlobFoliagePlacer(2, 0)).baseHeight(5).heightRandA(0).foliageHeight(3).ignoreVines()
            .setSapling((IPlantable) TerraBlockRegistry.SAPLING_PEARL.get()).build();

    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean b)
    {
        return Feature.NORMAL_TREE.withConfiguration(BOREAL_TREE_CONFIG);
    }
}
