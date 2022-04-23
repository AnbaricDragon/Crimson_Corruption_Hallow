package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.handlers.SpreadingHandler.EnumBiomeBlockType;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

public class TerraBlockOre extends OreBlock
{
    public TerraBlockOre(BlockBehaviour.Properties properties, UniformInt xpRange)
    {
        super(properties.requiresCorrectToolForDrops(), xpRange);
    }

    public TerraBlockOre(BlockBehaviour.Properties properties)
    {
        super(properties);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player)
    {
        ItemStack result = new ItemStack((Blocks.AIR));

        for (EnumBiomeBlockType blockType : EnumBiomeBlockType.values())
        {
            if (blockType.getVariantBlocks().contains(state.getBlock()))
            {
                result = new ItemStack(Item.byBlock(blockType.pure.getFirst()));
            }
        }
        return result;
    }
}
