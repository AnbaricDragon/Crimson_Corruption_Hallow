package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.handlers.EnumHandler.EnumBiomeBlockType;
import com.anbaric.terra_reforged.util.handlers.EnumHandler.EnumBiomeType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraftforge.common.PlantType;

import java.util.List;
import java.util.Random;

public class TerraBlockSand extends FallingBlock implements IGrowable
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
        return plantable.getPlantType(world, pos) == PlantType.Desert;
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

            for (int i = 0; i < 4; ++i)
            {
                BlockPos targetPos = pos.add(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
                Block targetBlock = worldIn.getBlockState(targetPos).getBlock();

                if (checkTransformable(targetBlock) && i == 0)
                {
                    worldIn.setBlockState(targetPos, transformedState(biome, targetBlock).getDefaultState());
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

    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
    {
        return worldIn.getBlockState(pos.up()).isAir();
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        BlockPos topPos = pos.up();
        BlockState grassState = TerraBlocks.GRASS_CORRUPT.getDefaultState();

        for (int i = 0; i < 128; ++i)
        {
            BlockPos targetPos = topPos;
            int j = 0;

            while (true)
            {
                if (j >= i / 16)
                {
                    BlockState targetState = worldIn.getBlockState(targetPos);
                    if (targetState.getBlock() == grassState.getBlock() && rand.nextInt(10) == 0)
                    {
                        ((IGrowable) grassState.getBlock()).grow(worldIn, rand, targetPos, targetState);
                    }

                    if (!targetState.isAir())
                    {
                        break;
                    }

                    BlockState flowerState;
                    if (rand.nextInt(8) == 0)
                    {
                        List<ConfiguredFeature<?>> list = worldIn.getBiome(targetPos).getFlowers();
                        if (list.isEmpty())
                        {
                            break;
                        }

                        flowerState = ((FlowersFeature) ((DecoratedFeatureConfig) (list.get(0)).config).feature.feature).getRandomFlower(rand, targetPos);
                    }
                    else
                    {
                        flowerState = grassState;
                    }

                    if (flowerState.isValidPosition(worldIn, targetPos))
                    {
                        worldIn.setBlockState(targetPos, flowerState, 3);
                    }
                    break;
                }

                targetPos = targetPos.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
                if (worldIn.getBlockState(targetPos.down()).getBlock() != this || worldIn.getBlockState(targetPos).func_224756_o(worldIn, targetPos))
                {
                    break;
                }
                ++j;
            }
        }
    }
}


