package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.util.handlers.EnumHandler.EnumBiomeBlockType;
import com.anbaric.terra_reforged.util.handlers.EnumHandler.EnumBiomeType;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import javax.annotation.Nullable;
import java.util.Random;

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
        if (!worldIn.isRemote)
        {
            if (!worldIn.isAreaLoaded(pos, 3))
            {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            }
            if (TerraReforged.debugSpreading)
            {
                for (int i = 0; i < 4; ++i)
                {
                    BlockPos targetPos = pos.add(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
                    Block targetBlock = worldIn.getBlockState(targetPos).getBlock();

                    if (checkTransformable(targetBlock))
                    {
                        if (targetBlock instanceof StairsBlock)
                        {
                            worldIn.setBlockState(targetPos, transformedState(biome, targetBlock).getDefaultState()
                                    .with(StairsBlock.FACING, worldIn.getBlockState(targetPos).get(StairsBlock.FACING))
                                    .with(StairsBlock.HALF, worldIn.getBlockState(targetPos).get(StairsBlock.HALF))
                                    .with(StairsBlock.SHAPE, worldIn.getBlockState(targetPos).get(StairsBlock.SHAPE))
                                    .with(StairsBlock.WATERLOGGED, worldIn.getBlockState(targetPos).get(StairsBlock.WATERLOGGED)));
                            return;
                        }
                        if (targetBlock instanceof SlabBlock)
                        {
                            worldIn.setBlockState(targetPos, transformedState(biome, targetBlock).getDefaultState()
                                    .with(SlabBlock.TYPE, worldIn.getBlockState(targetPos).get(SlabBlock.TYPE))
                                    .with(SlabBlock.WATERLOGGED, worldIn.getBlockState(targetPos).get(SlabBlock.WATERLOGGED)));
                            return;
                        }

                        worldIn.setBlockState(targetPos, transformedState(biome, targetBlock).getDefaultState());
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


