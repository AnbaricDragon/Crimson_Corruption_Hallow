package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.server.permission.context.IContext;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class TerraBlockMoss extends Block
{
    public Block moss;

    public TerraBlockMoss(Properties properties)
    {
        super(properties);
        this.moss = moss;
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable)
    {
        Block plant = plantable.getPlant(world, pos).getBlock();

        return plant.is(TerraTagRegistry.MOSS) || plant == getMoss();
    }

    public boolean canSpread(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos.above()).isAir(worldIn, pos) || worldIn.getBlockState(pos.above()).getBlock() instanceof BushBlock;
    }

    public Block getMoss()
    {
        if (this == TerraBlockRegistry.STONE_MOSS_RED.get()) { return TerraBlockRegistry.PLANT_MOSS_RED.get();}
        else if (this == TerraBlockRegistry.STONE_MOSS_YELLOW.get()) { return TerraBlockRegistry.PLANT_MOSS_YELLOW.get();}
        else if (this == TerraBlockRegistry.STONE_MOSS_GREEN.get()) { return TerraBlockRegistry.PLANT_MOSS_GREEN.get();}
        else if (this == TerraBlockRegistry.STONE_MOSS_BLUE.get()) { return TerraBlockRegistry.PLANT_MOSS_BLUE.get();}
        else if (this == TerraBlockRegistry.STONE_MOSS_PURPLE.get()) { return TerraBlockRegistry.PLANT_MOSS_PURPLE.get();}
        else return TerraBlockRegistry.PLANT_MOSS_FIRE.get();
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
            if (!canSpread(worldIn, pos))
            {
                worldIn.setBlockAndUpdate(pos, Blocks.STONE.defaultBlockState());
            }
            if (worldIn.getBlockState(pos.above()).isAir(worldIn, pos.above()))
            {
                worldIn.setBlockAndUpdate(pos.above(), getMoss().defaultBlockState());
            }
            for (int i = 0; i < 4; ++i)
            {
                BlockPos targetPos = pos.offset(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
                Block targetBlock = worldIn.getBlockState(targetPos).getBlock();

                if (canSpread(worldIn, targetPos) && targetBlock == Blocks.STONE || targetBlock == Blocks.DIORITE || targetBlock == Blocks.GRANITE || targetBlock == Blocks.ANDESITE)
                {
                    worldIn.setBlockAndUpdate(targetPos, this.defaultBlockState());
                }
            }
        }
    }

    @Override
    public void destroy(IWorld worldIn, BlockPos pos, BlockState state)
    {
        if (worldIn.getBlockState(pos.above()).getBlock() == this.moss)
        {
            worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
        }
        worldIn.setBlock(pos, Blocks.STONE.defaultBlockState(), 0);
    }
}


