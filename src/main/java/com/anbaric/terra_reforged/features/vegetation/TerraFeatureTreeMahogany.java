package com.anbaric.terra_reforged.features.vegetation;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class TerraFeatureTreeMahogany extends Feature<NoFeatureConfig>
{
    public TerraFeatureTreeMahogany()
    {
        super(NoFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        if (world.getBlockState(pos.down()).getBlock().isIn(TerraTagRegistry.MAHOGANY_PLANTERS))
        {
            generateTree(world, pos, rand);
            return true;
        }
        return false;
    }

    public static final BlockState LOG_MAHOGANY = TerraBlockRegistry.LOG_MAHOGANY.get().getDefaultState();
    public static final BlockState LEAF_MAHOGANY = TerraBlockRegistry.LEAF_MAHOGANY.get().getDefaultState();

    public static final char[][][] MAHOGANY_ARRAY =
    {{{'O', 'j', 'k', 'j', 'k', 'j', 'O'},
    {'k', 'L', 'L', 'L', 'L', 'L', 'k'},
    {'j', 'L', 'L', 'v', 'L', 'L', 'j'},
    {'k', 'L', 'h', 'W', 'h', 'L', 'k'},
    {'j', 'L', 'L', 'v', 'L', 'L', 'j'},
    {'k', 'L', 'L', 'L', 'L', 'L', 'k'},
    {'O', 'j', 'k', 'j', 'k', 'j', 'O'}},

    {{'O', 'O', '3', 'L', '3', 'O', 'O'},
    {'O', 'L', 'L', 'L', 'L', 'L', 'O'},
    {'3', 'L', 'L', 'L', 'L', 'L', '3'},
    {'L', 'L', 'L', 'W', 'L', 'L', 'L'},
    {'3', 'L', 'L', 'L', 'L', 'L', '3'},
    {'O', 'L', 'L', 'L', 'L', 'L', 'O'},
    {'O', 'O', '3', 'L', '3', 'O', 'O'}},

    {{'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'L', 'O', 'O', 'O'},
    {'O', 'O', 'L', 'L', 'L', 'O', 'O'},
    {'O', 'L', 'L', 'L', 'L', 'L', 'O'},
    {'O', 'O', 'L', 'L', 'L', 'O', 'O'},
    {'O', 'O', 'O', 'L', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'}}};

    private static char[][][] mutateArray(char[][][] inputArray, Random rand)
    {
        char[][][] outputArray = new char[inputArray.length][inputArray[0].length][inputArray[0][0].length];
        boolean    orient      = rand.nextBoolean();

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

                        case 'a': case 'b': case 'c':
                        outputArray[y][x][z] = won ? 'W' : 'O';
                        break;

                        case 'j':
                            outputArray[y][x][z] = orient ? 'l' : 'L';
                            break;

                        case 'k':
                            outputArray[y][x][z] = orient ? 'L' : 'l';
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
                        if (!world.getBlockState(target).canBeReplacedByLogs(world, target))
                        {
                            canGrow = false;
                        }
                    }
                    else if (targetChar == 'L' || targetChar == 'l')
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
        int        trunkHeight = rand.nextInt(4) + 4;
        int        treeHeight  = MAHOGANY_ARRAY.length;
        char[][][] template    = mutateArray(MAHOGANY_ARRAY, rand);
        boolean hasSpace = checkSpace(world, pos, trunkHeight, template);

        if (hasSpace)
        {
            BlockPos.Mutable target = new BlockPos.Mutable();
            for (int y = pos.getY() + trunkHeight; y < pos.getY() + treeHeight + trunkHeight; y++)
            {
                for (int x = pos.getX() - 3; x <= pos.getX() + 3; x++)
                {
                    for (int z = pos.getZ() - 3; z <= pos.getZ() + 3; z++)
                    {
                        target.setPos(x, y, z);
                        char inputChar = template[arrayY][arrayX][arrayZ];
                        int  droop     = rand.nextInt(3) + 1;
                        switch (inputChar)
                        {
                            case 'L':
                                world.setBlockState(target, LEAF_MAHOGANY, 3);
                                break;
                            case 'W':
                                world.setBlockState(target, LOG_MAHOGANY, 3);
                                break;
                            case 'v':
                                world.setBlockState(target, LOG_MAHOGANY.with(RotatedPillarBlock.AXIS, Direction.Axis.X), 3);
                                break;
                            case 'h':
                                world.setBlockState(target, LOG_MAHOGANY.with(RotatedPillarBlock.AXIS, Direction.Axis.Z), 3);
                                break;
                            case 'l':
                                world.setBlockState(target, LEAF_MAHOGANY, 3);
                                for (int i = 1; i <= droop; i++)
                                {
                                    if (world.getBlockState(target.add(0, -i, 0)).getBlock() == Blocks.AIR)
                                    {
                                        world.setBlockState(target.add(0, -i, 0), LEAF_MAHOGANY, 3);
                                    }
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
            for (int i = 0; i < trunkHeight; i++)
            {
                world.setBlockState(pos.up(i), LOG_MAHOGANY, 3);
            }
        }
    }
}
