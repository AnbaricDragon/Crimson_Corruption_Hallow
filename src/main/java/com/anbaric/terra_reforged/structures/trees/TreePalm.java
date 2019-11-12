package com.anbaric.terra_reforged.structures.trees;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.BigTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class TreePalm extends Tree
{
    @Nullable
    protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random)
    {
        return (AbstractTreeFeature<NoFeatureConfig>)(random.nextInt(10) == 0 ? new BigTreeFeature(NoFeatureConfig::deserialize, true) : new TreeFeature(NoFeatureConfig::deserialize, true));
    }
}