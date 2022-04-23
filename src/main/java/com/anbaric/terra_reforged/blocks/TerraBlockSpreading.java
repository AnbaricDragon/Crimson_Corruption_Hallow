package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.TerraReforged;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import com.anbaric.terra_reforged.util.handlers.SpreadingHandler.EnumBiomeBlockType;
import com.anbaric.terra_reforged.util.handlers.SpreadingHandler.EnumBiomeType;

import javax.annotation.Nullable;
import java.util.Random;

public class TerraBlockSpreading extends Block
{
    private EnumBiomeType biome;
    private PlantType plantType;

    public TerraBlockSpreading(Properties properties, EnumBiomeType biomeType, @Nullable PlantType plantType)
    {
        super(properties);
        this.biome = biomeType;
        this.plantType = plantType;
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable)
    {
        PlantType targetPlant = plantable.getPlantType(world, pos);
        return targetPlant == this.plantType;
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random)
    {
        if (!world.isAreaLoaded(pos, 3))
        {
            return;
        }
        if (TerraReforged.debugSpreading)
        {
            for (int i = 0; i < 4; ++i)
            {
                BlockPos targetPos = pos.offset(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
                Block targetBlock = world.getBlockState(targetPos).getBlock();

                if (checkTransformable(targetBlock))
                {
                    if (targetBlock instanceof StairBlock)
                    {
                        world.setBlock(targetPos, transformedState(biome, targetBlock).defaultBlockState()
                                        .setValue(StairBlock.FACING, world.getBlockState(targetPos).getValue(StairBlock.FACING))
                                        .setValue(StairBlock.HALF, world.getBlockState(targetPos).getValue(StairBlock.HALF))
                                        .setValue(StairBlock.SHAPE, world.getBlockState(targetPos).getValue(StairBlock.SHAPE))
                                        .setValue(StairBlock.WATERLOGGED, world.getBlockState(targetPos).getValue(StairBlock.WATERLOGGED))
                                , 3);
                        return;
                    }
                    if (targetBlock instanceof SlabBlock)
                    {
                        world.setBlock(targetPos, transformedState(biome, targetBlock).defaultBlockState()
                                        .setValue(SlabBlock.TYPE, world.getBlockState(targetPos).getValue(SlabBlock.TYPE))
                                        .setValue(SlabBlock.WATERLOGGED, world.getBlockState(targetPos).getValue(SlabBlock.WATERLOGGED))
                                , 3);
                        return;
                    }

                    world.setBlockAndUpdate(targetPos, transformedState(biome, targetBlock).defaultBlockState());
                }
            }
        }
    }

    public Block transformedState(EnumBiomeType type, Block target)
    {
        Block result = target;
        for (EnumBiomeBlockType block : EnumBiomeBlockType.values())
        {
            if (block.pure.contains(target))
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
            if (block.pure.contains(target))
            {
                check = true;
            }
        }
        return check;
    }
}
