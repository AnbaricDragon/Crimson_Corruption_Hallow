package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
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

        return plant.isIn(TerraTagRegistry.MOSS);
    }

    public boolean canSpread(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos.up()).isAir(worldIn, pos) || worldIn.getBlockState(pos.up()).getBlock() instanceof BushBlock;
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
            if (worldIn.getBlockState(pos.up()).isAir(worldIn, pos.up()))
            {
                worldIn.setBlockState(pos.up(), getMoss().getDefaultState());
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

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state)
    {
        if (worldIn.getBlockState(pos.up()).getBlock() == this.moss)
        {
            worldIn.getWorld().setBlockState(pos.up(), Blocks.AIR.getDefaultState());
        }
        worldIn.getWorld().setBlockState(pos, Blocks.STONE.getDefaultState(), 0);
    }
}


