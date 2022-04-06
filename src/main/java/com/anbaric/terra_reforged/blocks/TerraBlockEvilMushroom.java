package com.anbaric.terra_reforged.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class TerraBlockEvilMushroom extends BushBlock
{
    protected static final float AABB_OFFSET = 3.0F;
    protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
    protected TagKey<Block> tagKey;

    public TerraBlockEvilMushroom(Properties properties, TagKey<Block> tag)
    {
        super(properties);
        this.tagKey = tag;
    }

    public VoxelShape getShape(BlockState p_54889_, BlockGetter p_54890_, BlockPos p_54891_, CollisionContext p_54892_)
    {
        return SHAPE;
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random)
    {
        if (random.nextInt(25) == 0 || world.getRawBrightness(pos, 0) < 9)
        {
            int surroundingMushroomCount = 7;

            for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, -1, -4), pos.offset(4, 1, 4)))
            {
                if (world.getBlockState(blockpos).is(this))
                {
                    --surroundingMushroomCount;
                    if (surroundingMushroomCount <= 0)
                    {
                        return;
                    }
                }
            }

            BlockPos targetPos = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);

            for (int tries = 0; tries < 4; ++tries)
            {
                if (world.isEmptyBlock(targetPos) && state.canSurvive(world, targetPos))
                {
                    pos = targetPos;
                }
                targetPos = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
            }

            if (world.isEmptyBlock(targetPos) && state.canSurvive(world, targetPos))
            {
                world.setBlock(targetPos, state, 2);
            }
        }
    }

    protected boolean mayPlaceOn(BlockState p_54894_, BlockGetter p_54895_, BlockPos p_54896_)
    {
        BlockPos   blockpos   = p_54896_.below();
        BlockState blockstate = p_54895_.getBlockState(blockpos);
        return blockstate.is(tagKey);
    }

    public boolean canSurvive(BlockState p_54880_, LevelReader p_54881_, BlockPos p_54882_)
    {
        return mayPlaceOn(p_54880_, p_54881_, p_54882_);
    }
}