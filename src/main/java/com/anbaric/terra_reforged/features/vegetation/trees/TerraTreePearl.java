package com.anbaric.terra_reforged.features.vegetation.trees;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LogBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class TerraTreePearl extends Feature<NoFeatureConfig>
{
    public static final BlockState LOG_PEARL = TerraBlockRegistry.LOG_PEARL.get().getDefaultState();
    private static List<BlockState> LEAF_ARRAY = Arrays.asList
    (
        TerraBlockRegistry.LEAF_PEARL_RED.get().getDefaultState(),
        TerraBlockRegistry.LEAF_PEARL_YELLOW.get().getDefaultState(),
        TerraBlockRegistry.LEAF_PEARL_PINK.get().getDefaultState(),
        TerraBlockRegistry.LEAF_PEARL_MAGENTA.get().getDefaultState(),
        TerraBlockRegistry.LEAF_PEARL_CYAN.get().getDefaultState(),
        TerraBlockRegistry.LEAF_PEARL_BLUE.get().getDefaultState(),
        TerraBlockRegistry.LEAF_PEARL_GREEN.get().getDefaultState(),
        TerraBlockRegistry.LEAF_PEARL_PURPLE.get().getDefaultState()
    );

    public static final char[][][] PEARL_ARRAY =
    //0
  {{{'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'Y', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O'}},
    //1
   {{'O', 'O', 'O', 'O', 'O'},
    {'O', 'l', 'L', 'l', 'O'},
    {'O', 'L', 'Y', 'L', 'O'},
    {'O', 'l', 'L', 'l', 'O'},
    {'O', 'O', 'O', 'O', 'O'}},
    //2
   {{'O', 'l', 'L', 'l', 'O'},
    {'l', 'L', 'L', 'L', 'l'},
    {'L', 'L', 'Y', 'L', 'L'},
    {'l', 'L', 'L', 'L', 'l'},
    {'O', 'l', 'L', 'l', 'O'}},
    //3
   {{'O', 'l', 'L', 'l', 'O'},
    {'l', 'L', 'L', 'L', 'l'},
    {'L', 'L', 'Y', 'L', 'L'},
    {'l', 'L', 'L', 'L', 'l'},
    {'O', 'l', 'L', 'l', 'O'}},
    //4
   {{'O', 'O', 'O', 'O', 'O'},
    {'O', 'l', 'L', 'l', 'O'},
    {'O', 'L', '1', 'L', 'O'},
    {'O', 'l', 'L', 'l', 'O'},
    {'O', 'O', 'O', 'O', 'O'}},
    //5
   {{'O', 'b', 'B', 'b', 'O'},
    {'b', 'B', 'B', 'B', 'b'},
    {'B', 'B', '2', 'B', 'B'},
    {'b', 'B', 'B', 'B', 'b'},
    {'O', 'b', 'B', 'b', 'O'}},
    //6
   {{'O', 'b', 'B', 'b', 'O'},
    {'b', 'B', 'B', 'B', 'b'},
    {'B', 'B', '2', 'B', 'B'},
    {'b', 'B', 'B', 'B', 'b'},
    {'O', 'b', 'B', 'b', 'O'}},
    //7
   {{'O', 'O', 'O', 'O', 'O'},
    {'O', 'b', 'B', 'b', 'O'},
    {'O', 'B', '3', 'B', 'O'},
    {'O', 'b', 'B', 'b', 'O'},
    {'O', 'O', 'O', 'O', 'O'}},
    //8
   {{'O', 'c', 'C', 'c', 'O'},
    {'c', 'C', 'C', 'C', 'c'},
    {'C', 'C', '4', 'C', 'C'},
    {'c', 'C', 'C', 'C', 'c'},
    {'O', 'c', 'C', 'c', 'O'}},
    //9
   {{'O', 'c', 'C', 'c', 'O'},
    {'c', 'C', 'C', 'C', 'c'},
    {'C', 'C', '4', 'C', 'C'},
    {'c', 'C', 'C', 'C', 'c'},
    {'O', 'c', 'C', 'c', 'O'}},
    //10
   {{'O', 'O', 'O', 'O', 'O'},
    {'O', 'c', 'C', 'c', 'O'},
    {'O', 'C', 'C', 'C', 'O'},
    {'O', 'c', 'C', 'c', 'O'},
    {'O', 'O', 'O', 'O', 'O'}}};

    public TerraTreePearl(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory)
    {
        super(configFactory);
    }

    private boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        Block target = state.getBlock();
        return target == TerraBlockRegistry.GRASS_HALLOWED.get() || target == Blocks.DIRT || target == Blocks.GRASS_BLOCK;
    }

    private static char[][][] mutateArray(char[][][] inputArray, int canopyHeight)
    {
        char[][][] outputArray = new char[inputArray.length][inputArray[0].length][inputArray[0][0].length];

        for (int y = 0; y < inputArray.length; y++)
        {
            for (int x = 0; x < inputArray[0].length; x++)
            {
                for (int z = 0; z < inputArray[0][0].length; z++)
                {
                    Random rand = new Random();
                    int chance = rand.nextInt(8);
                    switch(inputArray[y][x][z])
                    {
                        case 'L':  case 'Y':  case 'O':  case 'X':  case 'Z':
                            outputArray[y][x][z] = inputArray[y][x][z];
                            break;

                        case 'l':
                            outputArray[y][x][z] = chance == 0 ? 'O': 'L';
                            break;

                        case 'B':
                            outputArray[y][x][z] = canopyHeight == 0 ? 'O': 'L';
                            break;

                        case 'b':
                            outputArray[y][x][z] = canopyHeight == 0 ? 'O':
                                                   chance == 0 ? 'O': 'L';
                            break;

                        case 'C':
                            outputArray[y][x][z] = canopyHeight == 2 ? 'L' : 'O';
                            break;

                        case 'c':
                            outputArray[y][x][z] = canopyHeight == 2 && chance == 0 ? 'L' : 'O';
                            break;

                        case '1':
                            outputArray[y][x][z] = canopyHeight == 0 ? 'L': 'Y';
                            break;

                        case '2':
                            outputArray[y][x][z] = canopyHeight == 0 ? 'O': 'Y';
                            break;

                        case '3':
                            outputArray[y][x][z] = canopyHeight == 0 ? 'O':
                                                   canopyHeight == 1 ? 'L': 'Y';
                            break;

                        case '4':
                            outputArray[y][x][z] = canopyHeight == 2 ? 'Y': 'O';
                            break;
                    }
                }
            }
        }
        return outputArray;
    }

    public static boolean checkSpace(IWorld world, BlockPos pos, int trunkHeight, char[][][] template)
    {
        System.out.println("Successfully checking space");

        int     arrayX     = 0, arrayY = 0, arrayZ = 0;
        int     radius     = 2;
        int     treeHeight = template.length;
        boolean canGrow = true;

        BlockPos.Mutable target = new BlockPos.Mutable();

        for (int i = 0; i <= trunkHeight; i++)
        {
            if (!world.getBlockState(pos.up(i)).canBeReplacedByLogs(world, pos))
            {
                canGrow = false;
            }
        }
        for (int y = pos.getY() + trunkHeight; y < pos.getY() + treeHeight + trunkHeight; y++)
        {
            for (int x = pos.getX() - radius; x <= pos.getX() + radius; x++)
            {
                for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; z++)
                {
                    target.setPos(x, y, z);
                    char targetChar = template[arrayY][arrayX][arrayZ];
                    if (targetChar == 'X' || targetChar == 'Y' || targetChar == 'Z')
                    {
                        if (!world.getBlockState(target).canBeReplacedByLogs(world, pos))
                        {
                            canGrow = false;
                        }
                    }
                    if (targetChar == 'L')
                    {
                        if (!world.getBlockState(target).canBeReplacedByLeaves(world, pos))
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

    public static void generateTree(IWorld world, BlockPos pos, Random rand)
    {
        int        arrayX       = 0, arrayY = 0, arrayZ = 0;
        int        trunkHeight  = rand.nextInt(3) + 1;
        int        treeHeight   = PEARL_ARRAY.length;
        int        canopyHeight = rand.nextInt(3);
        int leafColor = rand.nextInt(8);
        int        radius       = 2;
        char[][][] template     = mutateArray(PEARL_ARRAY, canopyHeight);
        boolean    hasSpace     = checkSpace(world, pos, trunkHeight, template);

        if (hasSpace)
        {
            BlockPos.Mutable target = new BlockPos.Mutable();
            for (int y = pos.getY() + trunkHeight; y < pos.getY() + treeHeight + trunkHeight; y++)
            {
                for (int x = pos.getX() - radius; x <= pos.getX() + radius; x++)
                {
                    for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; z++)
                    {
                        target.setPos(x, y, z);
                        char inputChar = template[arrayY][arrayX][arrayZ];
                        switch (inputChar)
                        {
                            case 'L':
                                world.setBlockState(target, LEAF_ARRAY.get(leafColor), 3);
                                break;
                            case 'Y':
                                world.setBlockState(target, LOG_PEARL, 3);
                                break;
                            case 'X':
                                world.setBlockState(target, LOG_PEARL.with(LogBlock.AXIS, Direction.Axis.X), 3);
                                break;
                            case 'Z':
                                world.setBlockState(target, LOG_PEARL.with(LogBlock.AXIS, Direction.Axis.Z), 3);
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
            for (int i = 0; i < trunkHeight; i++)
            {
                world.setBlockState(pos.up(i), LOG_PEARL, 3);
            }
        }
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random random, BlockPos pos, NoFeatureConfig config)
    {
        if (isValidGround(world.getBlockState(pos.down()), world, pos) && world.getBlockState(pos).getMaterial().isReplaceable())
        {
            generateTree(world, pos, random);
            return true;
        }
        return false;
    }
}