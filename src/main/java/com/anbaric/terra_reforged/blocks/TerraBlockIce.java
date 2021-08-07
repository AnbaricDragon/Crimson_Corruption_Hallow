package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.util.handlers.SpreadingHandler.EnumBiomeBlockType;
import com.anbaric.terra_reforged.util.handlers.SpreadingHandler.EnumBiomeType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;
import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class TerraBlockIce extends Block
{
    private EnumBiomeType biome;

    public TerraBlockIce(Properties properties, EnumBiomeType biome)
    {
        super(properties);
        this.biome = biome;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side)
    {
        return adjacentBlockState.getBlock() == this || super.skipRendering(state, adjacentBlockState, side);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, @Nullable IPlantable plantable)
    {
        return plantable.getPlantType(world, pos) == TerraReforged.BOREAL;
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        if (!worldIn.isClientSide)
        {
             if (!worldIn.isAreaLoaded(pos, 3))
            {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            }

            if (TerraReforged.debugSpreading)
            {
                for (int i = 0; i < 4; ++i)
                {
                    BlockPos targetPos   = pos.offset(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
                    Block    targetBlock = worldIn.getBlockState(targetPos).getBlock();

                    if (checkTransformable(targetBlock) && i == 0)
                    {
                        if (targetBlock instanceof StairsBlock)
                        {
                            worldIn.setBlockAndUpdate(targetPos, transformedState(biome, targetBlock).defaultBlockState()
                                    .setValue(StairsBlock.FACING, worldIn.getBlockState(targetPos).getValue(StairsBlock.FACING))
                                    .setValue(StairsBlock.HALF, worldIn.getBlockState(targetPos).getValue(StairsBlock.HALF))
                                    .setValue(StairsBlock.SHAPE, worldIn.getBlockState(targetPos).getValue(StairsBlock.SHAPE))
                                    .setValue(StairsBlock.WATERLOGGED, worldIn.getBlockState(targetPos).getValue(StairsBlock.WATERLOGGED)));
                            return;
                        }
                        if (targetBlock instanceof SlabBlock)
                        {
                            worldIn.setBlockAndUpdate(targetPos, transformedState(biome, targetBlock).defaultBlockState()
                                    .setValue(SlabBlock.TYPE, worldIn.getBlockState(targetPos).getValue(SlabBlock.TYPE))
                                    .setValue(SlabBlock.WATERLOGGED, worldIn.getBlockState(targetPos).getValue(SlabBlock.WATERLOGGED)));
                            return;
                        }

                        worldIn.setBlockAndUpdate(targetPos, transformedState(biome, targetBlock).defaultBlockState());
                    }
                }
            }
        }
    }

    public Block transformedState(EnumBiomeType type, Block target)
    {
        Block result = target;
        for (EnumBiomeBlockType block : EnumBiomeBlockType.values())
        {
            if (block.pure == target)
            {
                result = block.getBiomeBlock(type);
            }
        }
        return result;
    }

    public boolean checkTransformable(Block target)
    {
        boolean check = false;
        for (EnumBiomeBlockType block : EnumBiomeBlockType.values())
        {
            if (block.pure == target)
            {
                check = true;
            }
        }
        return check;
    }
}


