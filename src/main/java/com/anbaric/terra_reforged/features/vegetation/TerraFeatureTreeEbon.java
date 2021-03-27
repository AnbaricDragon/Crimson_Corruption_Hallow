package com.anbaric.terra_reforged.features.vegetation;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class TerraFeatureTreeEbon extends Feature<NoFeatureConfig>
{
    public TerraFeatureTreeEbon()
    {
        super(NoFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        if (world.getBlockState(pos.down()).getBlock().isIn(TerraTagRegistry.EBON_PLANTERS))
        {
            generateTree(world, pos, rand);
            return true;
        }
        return false;
    }

    public static final BlockState LOG_EBON = TerraBlockRegistry.LOG_EBON.get().getDefaultState();
    public static final BlockState LEAF_EBON = TerraBlockRegistry.LEAF_EBON.get().getDefaultState();

    public static final char[][][] EBON_ARRAY =
    {{{'2', 'L', 'a', 'L', '2'},
    {'L', 'L', 'a', 'L', 'L'},
    {'d', 'd', 'W', 'b', 'b'},
    {'L', 'L', 'c', 'L', 'L'},
    {'2', 'L', 'c', 'L', '2'}},

    {{'O', 'L', 'L', 'L', 'O'},
    {'L', 'L', 'L', 'L', 'L'},
    {'L', 'L', 'W', 'L', 'L'},
    {'L', 'L', 'L', 'L', 'L'},
    {'O', 'L', 'L', 'L', 'O'}},

    {{'O', 'O', 'O', 'O', 'O'},
    {'O', '3', 'e', '3', 'O'},
    {'O', 'h', 'W', 'f', 'O'},
    {'O', '3', 'g', '3', 'O'},
    {'O', 'O', 'O', 'O', 'O'}},

    {{'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'L', 'O', 'O'},
    {'O', 'L', 'L', 'L', 'O'},
    {'O', 'O', 'L', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O'}}};

    private static char[][][] mutateArray(char[][][] inputArray, Random rand)
    {
        char[][][] outputArray = new char[inputArray.length][inputArray[0].length][inputArray[0][0].length];
        float lowChance = rand.nextFloat();
        float highChance = rand.nextFloat();

        for (int y = 0; y < inputArray.length; y++)
        {
            for (int x = 0; x < inputArray[0].length; x++)
            {
                for (int z = 0; z < inputArray[0][0].length; z++)
                {
                    float   chance = Character.getNumericValue(inputArray[y][x][z]) * 0.25F;
                    boolean won    = rand.nextFloat() < chance;
                    switch (inputArray[y][x][z])
                    {
                        case '1': case '2': case '3':
                        outputArray[y][x][z] = won ? 'L' : 'O';
                        break;

                        case 'a':
                            outputArray[y][x][z] = lowChance < 0.2F ? 'v' : 'L';
                            break;

                        case 'b':
                            outputArray[y][x][z] = lowChance > 0.2F && lowChance < 0.4F ? 'h' : 'L';
                            break;

                        case 'c':
                            outputArray[y][x][z] = lowChance > 0.4F && lowChance < 0.6F ? 'v' : 'L';
                            break;

                        case 'd':
                            outputArray[y][x][z] = lowChance > 0.6F && lowChance < 0.8F ? 'h' : 'L';
                            break;

                        case 'e':
                            outputArray[y][x][z] = highChance < 0.2F ? 'v' : 'L';
                            break;

                        case 'f':
                            outputArray[y][x][z] = highChance > 0.2F && highChance < 0.4F ? 'h' : 'L';
                            break;

                        case 'g':
                            outputArray[y][x][z] = highChance > 0.4F && highChance < 0.6F ? 'v' : 'L';
                            break;

                        case 'h':
                            outputArray[y][x][z] = highChance > 0.6F && highChance < 0.8F ? 'h' : 'L';
                            break;

                        default:
                            outputArray[y][x][z] = inputArray[y][x][z];
                    }
                }
            }
        }
        return outputArray;
    }

    public static boolean checkSpace(IWorld world, BlockPos pos, int trunkHeight, char[][][] template)
    {
        int     arrayX     = 0, arrayY = 0, arrayZ = 0;
        int     radius     = 2;
        int     treeHeight = template.length;
        boolean canGrow    = true;

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
                    if (targetChar == 'W' || targetChar == 'h' || targetChar == 'v')
                    {
                        if (!world.getBlockState(target).canBeReplacedByLogs(world, pos))
                        {
                            canGrow = false;
                        }
                    }
                    else if (targetChar == 'L')
                    {
                        if (!world.getBlockState(target).canBeReplacedByLeaves(world, target))
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
        int        arrayX      = 0, arrayY = 0, arrayZ = 0;
        int        trunkHeight = rand.nextInt(4) + 1;
        int        treeHeight  = EBON_ARRAY.length;
        char[][][] template    = mutateArray(EBON_ARRAY, rand);
        boolean hasSpace = checkSpace(world, pos, trunkHeight, template);

        if (hasSpace)
        {
            BlockPos.Mutable target = new BlockPos.Mutable();
            for (int y = pos.getY() + trunkHeight; y < pos.getY() + treeHeight + trunkHeight; y++)
            {
                for (int x = pos.getX() - 2; x <= pos.getX() + 2; x++)
                {
                    for (int z = pos.getZ() - 2; z <= pos.getZ() + 2; z++)
                    {
                        target.setPos(x, y, z);
                        char inputChar = template[arrayY][arrayX][arrayZ];
                        switch (inputChar)
                        {
                            case 'L':
                                world.setBlockState(target, LEAF_EBON, 3);
                                break;
                            case 'W':
                                world.setBlockState(target, LOG_EBON, 3);
                                break;
                            case 'v':
                                world.setBlockState(target, LOG_EBON.with(RotatedPillarBlock.AXIS, Direction.Axis.X), 3);
                                break;
                            case 'h':
                                world.setBlockState(target, LOG_EBON.with(RotatedPillarBlock.AXIS, Direction.Axis.Z), 3);
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
                world.setBlockState(pos.up(i), LOG_EBON, 3);
            }
        }
    }
}
