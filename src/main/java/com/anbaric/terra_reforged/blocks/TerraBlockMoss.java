package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.TerraReforged;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class TerraBlockMoss extends Block
{
    public Block moss;

    public TerraBlockMoss(Properties properties, Block moss)
    {
        super(properties);
        this.moss = moss;
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable)
    {
//        return plantable.getPlantType(world, pos) == TerraReforged.MOSS;
        return true;
    }

    public boolean canSpread(World worldIn, BlockPos pos)
    {
        return !worldIn.getBlockState(pos.up()).isOpaqueCube(worldIn, pos);
    }

    public void tick(BlockState state, World worldIn, BlockPos pos, Random random)
    {
        if (!worldIn.isRemote)
        {
            if (!worldIn.isAreaLoaded(pos, 3))
            {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            }
            if (!canSpread(worldIn, pos))
            {
                worldIn.setBlockState(pos, Blocks.STONE.getDefaultState());
            }
            for (int i = 0; i < 4; ++i)
            {
                BlockPos targetPos = pos.add(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
                Block targetBlock = worldIn.getBlockState(targetPos).getBlock();

                if (canSpread(worldIn, targetPos) && targetBlock == Blocks.STONE || targetBlock == Blocks.DIORITE || targetBlock == Blocks.GRANITE || targetBlock == Blocks.ANDESITE)
                {
                    worldIn.setBlockState(targetPos, this.getDefaultState());
                }
            }
        }
    }

    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state)
    {
        if (worldIn.getBlockState(pos.up()).getBlock() == this.moss)
        {
            worldIn.getWorld().setBlockState(pos.up(), Blocks.AIR.getDefaultState());
        }
        worldIn.getWorld().setBlockState(pos, Blocks.STONE.getDefaultState(), 0);
    }
}


