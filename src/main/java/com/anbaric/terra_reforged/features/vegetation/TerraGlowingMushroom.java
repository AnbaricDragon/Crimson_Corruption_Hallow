package com.anbaric.terra_reforged.features.vegetation;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class TerraGlowingMushroom extends Feature<NoFeatureConfig>
{
    public static final BlockState LOG_MUSHROOM = TerraBlockRegistry.LOG_MUSHROOM.get().getDefaultState();
    public static final BlockState CAP_MUSHROOM_NORTH_WEST = TerraBlockRegistry.MUSHROOM_CAP.get().getDefaultState().with(HugeMushroomBlock.DOWN, false).with(HugeMushroomBlock.SOUTH, false).with(HugeMushroomBlock.EAST, false);
    public static final BlockState CAP_MUSHROOM_NORTH = TerraBlockRegistry.MUSHROOM_CAP.get().getDefaultState().with(HugeMushroomBlock.DOWN, false).with(HugeMushroomBlock.SOUTH, false).with(HugeMushroomBlock.EAST, false).with(HugeMushroomBlock.WEST, false);
    public static final BlockState CAP_MUSHROOM_NORTH_EAST = TerraBlockRegistry.MUSHROOM_CAP.get().getDefaultState().with(HugeMushroomBlock.DOWN, false).with(HugeMushroomBlock.SOUTH, false).with(HugeMushroomBlock.WEST, false);
    public static final BlockState CAP_MUSHROOM_EAST = TerraBlockRegistry.MUSHROOM_CAP.get().getDefaultState().with(HugeMushroomBlock.DOWN, false).with(HugeMushroomBlock.SOUTH, false).with(HugeMushroomBlock.WEST, false).with(HugeMushroomBlock.NORTH, false);
    public static final BlockState CAP_MUSHROOM_SOUTH_EAST = TerraBlockRegistry.MUSHROOM_CAP.get().getDefaultState().with(HugeMushroomBlock.DOWN, false).with(HugeMushroomBlock.NORTH, false).with(HugeMushroomBlock.WEST, false);
    public static final BlockState CAP_MUSHROOM_SOUTH = TerraBlockRegistry.MUSHROOM_CAP.get().getDefaultState().with(HugeMushroomBlock.DOWN, false).with(HugeMushroomBlock.NORTH, false).with(HugeMushroomBlock.EAST, false).with(HugeMushroomBlock.WEST, false);
    public static final BlockState CAP_MUSHROOM_SOUTH_WEST = TerraBlockRegistry.MUSHROOM_CAP.get().getDefaultState().with(HugeMushroomBlock.DOWN, false).with(HugeMushroomBlock.NORTH, false).with(HugeMushroomBlock.EAST, false);
    public static final BlockState CAP_MUSHROOM_WEST = TerraBlockRegistry.MUSHROOM_CAP.get().getDefaultState().with(HugeMushroomBlock.DOWN, false).with(HugeMushroomBlock.SOUTH, false).with(HugeMushroomBlock.EAST, false).with(HugeMushroomBlock.NORTH, false);
    public static final BlockState CAP_MUSHROOM_UP = TerraBlockRegistry.MUSHROOM_CAP.get().getDefaultState().with(HugeMushroomBlock.DOWN, false).with(HugeMushroomBlock.SOUTH, false).with(HugeMushroomBlock.EAST, false).with(HugeMushroomBlock.WEST, false).with(HugeMushroomBlock.NORTH, false);

    public static final char[][][] MUSHROOM_ARRAY =
  {{{'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'W', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O'}},

   {{'O', 'A', 'B', 'C', 'O'},
    {'A', 'O', 'O', 'O', 'C'},
    {'H', 'O', 'W', 'O', 'D'},
    {'G', 'O', 'O', 'O', 'E'},
    {'O', 'G', 'F', 'E', 'O'}},

   {{'O', 'A', 'B', 'C', 'O'},
    {'A', 'O', 'O', 'O', 'C'},
    {'H', 'O', 'W', 'O', 'D'},
    {'G', 'O', 'O', 'O', 'E'},
    {'O', 'G', 'F', 'E', 'O'}},

   {{'O', 'A', 'B', 'C', 'O'},
    {'A', 'O', 'O', 'O', 'C'},
    {'H', 'O', 'W', 'O', 'D'},
    {'G', 'O', 'O', 'O', 'E'},
    {'O', 'G', 'F', 'E', 'O'}},

   {{'O', 'O', 'O', 'O', 'O'},
    {'O', 'A', 'B', 'C', 'O'},
    {'O', 'H', 'I', 'D', 'O'},
    {'O', 'G', 'F', 'E', 'O'},
    {'O', 'O', 'O', 'O', 'O'}}};

    public TerraGlowingMushroom(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory)
    {
        super(configFactory);
    }

    private boolean isValidGround(BlockState state, IBlockReader world, BlockPos pos)
    {
        Random rand = new Random();
        Block target = state.getBlock();
        return target == TerraBlockRegistry.GRASS_MUSHROOM.get();
    }

    private static int getRandomNumberInRange(int min, int max)
    {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public static boolean checkCapSpace(IWorld world, BlockPos pos, Random rand, int startingLayer)
    {
        int     arrayX     = 0, arrayY = startingLayer - 1, arrayZ = 0;
        int     radius     = 2;
        int     treeHeight = MUSHROOM_ARRAY.length;
        char[][][] template = MUSHROOM_ARRAY;
        boolean canGrow    = true;

        BlockPos.Mutable target = new BlockPos.Mutable();

        for (int y = pos.getY(); y < pos.getY() + (treeHeight - (startingLayer - 1)); y++)
        {
            for (int x = pos.getX() - radius; x <= pos.getX() + radius; x++)
            {
                for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; z++)
                {
                    target.setPos(x, y, z);
                    char targetChar = template[arrayY][arrayX][arrayZ];
                    if (targetChar == 'W' || targetChar == 'h' || targetChar == 'v')
                    {
                        if (!world.getBlockState(target).canBeReplacedByLogs(world, pos))
                        {
                            canGrow = false;
                        }
                    }
                    else if (targetChar != 'O')
                    {
                        if (!world.getBlockState(target).canBeReplacedByLeaves(world, target) && world.getBlockState(target).getBlock() != LOG_MUSHROOM.getBlock())
                        {
                            canGrow = false;
                        }
                    }
                    arrayZ++;
                }
                arrayX++;
                arrayZ = 0;
            }
            arrayY++;
            arrayX = 0;
        }
        return canGrow;
    }

    public static void placeBlock(IWorld world, BlockPos pos, BlockState block)
    {
        if (world.getBlockState(pos).getBlock() != LOG_MUSHROOM.getBlock())
        {
            world.setBlockState(pos, block, 3);
        }
    }

    public static int randExcept(Random rand)
    {
        int range = rand.nextInt(5) - 2;
        return range == 0 ? randExcept(rand) : range;
    }

    public static boolean generateCap(IWorld world, BlockPos pos, Random rand, int startingLayer)
    {
        System.out.println("Successfully started generating cap");
        int        arrayX      = 0, arrayY = startingLayer - 1, arrayZ = 0;
        int        capHeight  = MUSHROOM_ARRAY.length - (startingLayer-1);
        char[][][] template    = MUSHROOM_ARRAY;
        boolean hasSpace = checkCapSpace(world, pos, rand, startingLayer);

        if (hasSpace)
        {
            BlockPos.Mutable target = new BlockPos.Mutable();
            for (int y = pos.getY(); y < pos.getY() + capHeight; y++)
            {
                for (int x = pos.getX() - 2; x <= pos.getX() + 2; x++)
                {
                    for (int z = pos.getZ() - 2; z <= pos.getZ() + 2; z++)
                    {
                        target.setPos(x, y, z);
                        char inputChar = template[arrayY][arrayX][arrayZ];
                        switch (inputChar)
                        {
                            case 'A':
                                placeBlock(world, target, CAP_MUSHROOM_NORTH_WEST);
                                break;
                            case 'B':
                                placeBlock(world, target, CAP_MUSHROOM_WEST);
                                break;
                            case 'C':
                                placeBlock(world, target, CAP_MUSHROOM_SOUTH_WEST);
                                break;
                            case 'D':
                                placeBlock(world, target, CAP_MUSHROOM_SOUTH);
                                break;
                            case 'E':
                                placeBlock(world, target, CAP_MUSHROOM_SOUTH_EAST);
                                break;
                            case 'F':
                                placeBlock(world, target, CAP_MUSHROOM_EAST);
                                break;
                            case 'G':
                                placeBlock(world, target, CAP_MUSHROOM_NORTH_EAST);
                                break;
                            case 'H':
                                placeBlock(world, target, CAP_MUSHROOM_NORTH);
                                break;
                            case 'I':
                                placeBlock(world, target, CAP_MUSHROOM_UP);
                                break;
                            case 'W':
                                world.setBlockState(target, LOG_MUSHROOM, 3);
                                break;
                            case 'v':
                                world.setBlockState(target, LOG_MUSHROOM.with(LogBlock.AXIS, Direction.Axis.X), 3);
                                break;
                            case 'h':
                                world.setBlockState(target, LOG_MUSHROOM.with(LogBlock.AXIS, Direction.Axis.Z), 3);
                                break;
                        }
                        arrayZ++;
                    }
                    arrayX++;
                    arrayZ = 0;
                }
                arrayY++;
                arrayX = 0;
            }
            return true;
        }
        else return false;
    }

    public static boolean generateMushroom(IWorld world, BlockPos pos, Random rand)
    {
        BlockPos.Mutable target = new BlockPos.Mutable();
        target.setPos(pos);
        int trunkHeight = rand.nextInt(8);
        boolean canSpawn = true;
        boolean canSeeSky = world.canBlockSeeSky(pos);

        for (int trunk = 0; trunk < trunkHeight; trunk++)
        {
            if (!world.getBlockState(pos.up(trunk)).canBeReplacedByLogs(world, pos))
            {
                canSpawn = false;
            }
        }

        if (canSpawn && checkCapSpace(world, pos.up(trunkHeight), rand, 1))
        {
            for (int trunk = 0; trunk < trunkHeight; trunk++)
            {
                world.setBlockState(pos.up(trunk), LOG_MUSHROOM, 3);
            }
            generateCap(world, pos.up(trunkHeight), rand, 1);

            if (canSeeSky && trunkHeight > 2)
            {
                boolean xAxis = rand.nextBoolean();
                BlockPos branchPos = pos.add(xAxis ? randExcept(rand) : 0, getRandomNumberInRange(1, Math.max(1, trunkHeight - 2)), xAxis ? 0 : randExcept(rand));

                if (checkCapSpace(world, branchPos, rand, 4))
                {
                    generateCap(world, branchPos, rand, 4);
                }
            }

            return true;
        }
        else return false;
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random random, BlockPos pos, NoFeatureConfig config)
    {
        if (isValidGround(world.getBlockState(pos.down()), world, pos))
        {
            generateMushroom(world, pos, random);
            return true;
        }
        return false;
    }
}