package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.util.handlers.SpreadingHandler.EnumBiomeBlockType;
import com.anbaric.terra_reforged.util.handlers.SpreadingHandler.EnumBiomeType;
import net.minecraft.block.*;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import javax.annotation.Nullable;
import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class TerraBlockSpreading extends Block
{
    private EnumBiomeType biome;
    private PlantType plantType;

    public TerraBlockSpreading(Properties properties, EnumBiomeType biome, @Nullable PlantType plantType)
    {
        super(properties);
        this.biome = biome;
        this.plantType = plantType;
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, @Nullable IPlantable plantable)
    {
        PlantType targetPlant = plantable.getPlantType(world, pos);
        return targetPlant == this.plantType;
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
                    BlockPos targetPos = pos.offset(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
                    Block targetBlock = worldIn.getBlockState(targetPos).getBlock();

                    if (checkTransformable(targetBlock))
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


