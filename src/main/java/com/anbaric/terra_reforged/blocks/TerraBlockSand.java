package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.util.handlers.SpreadingHandler.EnumBiomeBlockType;
import com.anbaric.terra_reforged.util.handlers.SpreadingHandler.EnumBiomeType;
import net.minecraft.block.*;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.PlantType;

import java.util.Random;

public class TerraBlockSand extends FallingBlock
{
    private EnumBiomeType biome;

    public TerraBlockSand(AbstractBlock.Properties properties, EnumBiomeType biome)
    {
        super(properties);
        this.biome = biome;
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable)
    {
        Boolean reed  = false;
        Block   plant = plantable.getPlant(world, pos.above()).getBlock();
        if (plant == Blocks.SUGAR_CANE || plant instanceof TerraBlockReeds)
        {
            for (Direction direction : Direction.Plane.HORIZONTAL)
            {
                BlockState  blockstate  = world.getBlockState(pos.relative(direction));
                FluidState fluidstate = world.getFluidState(pos.relative(direction));
                if (fluidstate.is(FluidTags.WATER) || blockstate.getBlock() == Blocks.FROSTED_ICE)
                {
                    reed = true;
                }
            }
        }
        return plantable.getPlantType(world, pos) == PlantType.DESERT || reed;
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        if (!worldIn.isClientSide)
        {
            this.checkFallable(worldIn, pos);
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

    private void checkFallable(World worldIn, BlockPos pos)
    {
        if (worldIn.isEmptyBlock(pos.below()) || isFree(worldIn.getBlockState(pos.below())) && pos.getY() >= 0)
        {
            if (!worldIn.isClientSide)
            {
                FallingBlockEntity fallingblockentity = new FallingBlockEntity(worldIn, (double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, worldIn.getBlockState(pos));
                this.falling(fallingblockentity);
                worldIn.addFreshEntity(fallingblockentity);
            }
        }
    }
}


