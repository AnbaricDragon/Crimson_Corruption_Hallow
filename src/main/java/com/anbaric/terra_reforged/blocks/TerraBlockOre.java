package com.anbaric.terra_reforged.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

public class TerraBlockOre extends Block
{
    public TerraBlockOre(Properties p_49795_)
    {
        super(p_49795_);
    }


    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player)
    {
        return super.getCloneItemStack(state, target, level, pos, player);
    }
}
