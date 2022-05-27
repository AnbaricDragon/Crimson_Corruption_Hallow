package com.anbaric.terra_reforged.blocks.potionplants;

import com.anbaric.terra_reforged.util.handlers.BiomeChangeHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class TESTBlock extends Block
{
    public TESTBlock(Properties p_49795_)
    {
        super(p_49795_);
    }

    @Override
    public void onPlace(BlockState p_60566_, Level level, BlockPos pos, BlockState p_60569_, boolean p_60570_)
    {
        for (BlockPos target : BlockPos.betweenClosed(pos.offset(-3, -3, -3), pos.offset(3, 3, 3)))
        {
            BiomeChangeHandler.setBiomeKeyAtPos(level, target, Biomes.DESERT);
            level.scheduleTick(target, level.getBlockState(target).getBlock(), 1);
        }

        super.onPlace(p_60566_, level, pos, p_60569_, p_60570_);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        BiomeChangeHandler.setBiomeKeyAtPos(level, pos, Biomes.DARK_FOREST);
        return super.use(state, level, pos, player, hand, hitResult);
    }
}
