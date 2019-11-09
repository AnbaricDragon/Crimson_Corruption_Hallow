package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.util.handlers.EnumHandler.EnumBiomeBlockType;
import com.anbaric.terra_reforged.util.handlers.EnumHandler.EnumBiomeType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.PlantType;

import java.util.Random;

public class TerraBlockSand extends FallingBlock
{
    private EnumBiomeType biome;

    public TerraBlockSand(Block.Properties properties, EnumBiomeType biome)
    {
        super(properties);
        this.biome = biome;
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable)
    {
        PlantType targetPlant = plantable.getPlantType(world, pos);
        return targetPlant == PlantType.Desert || targetPlant == PlantType.Beach;
    }

    public void tick(BlockState state, World worldIn, BlockPos pos, Random random)
    {
        if (!worldIn.isRemote)
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
                    BlockPos targetPos   = pos.add(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
                    Block    targetBlock = worldIn.getBlockState(targetPos).getBlock();

                    if (checkTransformable(targetBlock) && i == 0)
                    {
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

    private void checkFallable(World worldIn, BlockPos pos) {
        if (worldIn.isAirBlock(pos.down()) || canFallThrough(worldIn.getBlockState(pos.down())) && pos.getY() >= 0) {
            if (!worldIn.isRemote) {
                FallingBlockEntity fallingblockentity = new FallingBlockEntity(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, worldIn.getBlockState(pos));
                this.onStartFalling(fallingblockentity);
                worldIn.addEntity(fallingblockentity);
            }

        }
    }
}

