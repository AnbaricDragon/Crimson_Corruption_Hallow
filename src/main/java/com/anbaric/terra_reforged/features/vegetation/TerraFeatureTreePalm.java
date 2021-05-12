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

public class TerraFeatureTreePalm extends Feature<NoFeatureConfig>
{
    public TerraFeatureTreePalm()
    {
        super(NoFeatureConfig.CODEC);
    }

    @Override
    public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        if (world.getBlockState(pos.down()).getBlock().isIn(TerraTagRegistry.PALM_PLANTERS))
        {
            generateTree(world, pos, rand);
            return true;
        }
        return false;
    }

    public static final BlockState LOG_PALM = TerraBlockRegistry.LOG_PALM.get().getDefaultState();
    public static final BlockState LEAF_PALM = TerraBlockRegistry.LEAF_PALM.get().getDefaultState();

    public static final char[][][] PALM_ARRAY =
    {{{'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', '1', '2', '3', 'O', 'O'},
    {'O', 'O', '0', 'O', '4', 'O', 'O'},
    {'O', 'O', '7', '6', '5', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'}},

    {{'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', '1', '2', '3', 'O', 'O'},
    {'O', 'O', '0', 'O', '4', 'O', 'O'},
    {'O', 'O', '7', '6', '5', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'}},

    {{'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', '1', '2', '3', 'O', 'O'},
    {'O', 'O', '0', 'O', '4', 'O', 'O'},
    {'O', 'O', '7', '6', '5', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'}},

    {{'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', '1', '2', '3', 'O', 'O'},
    {'O', 'O', '0', 'O', '4', 'O', 'O'},
    {'O', 'O', '7', '6', '5', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'}},

    {{'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', '1', '2', '3', 'O', 'O'},
    {'O', 'O', '0', 'O', '4', 'O', 'O'},
    {'O', 'O', '7', '6', '5', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'}},

    {{'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', '1', '2', '3', 'O', 'O'},
    {'O', 'O', '0', 'W', '4', 'O', 'O'},
    {'O', 'O', '7', '6', '5', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'}},

    {{'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'W', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'}},

    {{'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'W', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'}},

    {{'O', 'O', 'O', 'L', 'O', 'O', 'O'},
    {'O', 'L', 'O', 'O', 'O', 'L', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'L', 'O', 'O', 'W', 'O', 'O', 'L'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'L', 'O', 'O', 'O', 'L', 'O'},
    {'O', 'O', 'O', 'L', 'O', 'O', 'O'}},

    {{'O', 'O', 'O', 'l', 'O', 'O', 'O'},
    {'O', 'l', 'L', 'L', 'L', 'l', 'O'},
    {'O', 'L', 'L', 'V', 'L', 'L', 'O'},
    {'l', 'L', 'H', 'W', 'H', 'L', 'l'},
    {'O', 'L', 'L', 'V', 'L', 'L', 'O'},
    {'O', 'l', 'L', 'L', 'L', 'l', 'O'},
    {'O', 'O', 'O', 'l', 'O', 'O', 'O'}},

    {{'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'l', 'L', 'l', 'O', 'O'},
    {'O', 'O', 'L', 'L', 'L', 'O', 'O'},
    {'O', 'O', 'l', 'L', 'l', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O', 'O'}}};

    private static char[][][] mutateArray(char[][][] inputArray, Random rand)
    {
        char[][][] outputArray = new char[inputArray.length][inputArray[0].length][inputArray[0][0].length];
        int trunk = rand.nextInt(8);
        for (int y = 0; y < inputArray.length; y++)
        {
            for (int x = 0; x < inputArray[0].length; x++)
            {
                for (int z = 0; z < inputArray[0][0].length; z++)
                {
                    boolean won = rand.nextFloat() < 0.75F;

                    switch (inputArray[y][x][z])
                    {
                        case 'l':
                            outputArray[y][x][z] = won ? 'L' : 'O';
                            break;

                        case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7':
                            outputArray[y][x][z] = Character.getNumericValue(inputArray[y][x][z]) == trunk ? 'W' : 'O';
                            break;

                        default:
                            outputArray[y][x][z] = inputArray[y][x][z];
                    }
                }
            }
        }
        return outputArray;
    }

    public static void readArray(char[][][] template)
    {
        System.out.println("���������");
        for (int y = 0; y < template.length; y++)
        {
            for (int x = 0; x < template[0].length; x++)
            {
                System.out.println(template[y][x][0] + " " + template[y][x][1] + " " + template[y][x][2] + " " + template[y][x][3] + " " + template[y][x][4] + " " + template[y][x][5] + " " + template[y][x][6]);
            }
            System.out.println("--------------");
        }
    }

    public static boolean checkSpace(IWorld world, BlockPos pos, int trunkHeight, char[][][] template)
    {
        int     arrayX     = 0, arrayY = 0, arrayZ = 0;
        int     radius     = 3;
        int     treeHeight = template.length;
        boolean canGrow    = true;

        BlockPos.Mutable target = new BlockPos.Mutable();

        for (int y = pos.getY(); y < pos.getY() + treeHeight; y++)
        {
            for (int x = pos.getX() - radius; x <= pos.getX() + radius; x++)
            {
                for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; z++)
                {
                    target.setPos(x, y, z);
                    char targetChar = template[arrayY][arrayX][arrayZ];
                    if (targetChar == 'W' || targetChar == 'H' || targetChar == 'V')
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
        char[][][] template = mutateArray(PALM_ARRAY, rand);
        int        treeHeight  = PALM_ARRAY.length;
        boolean hasSpace = checkSpace(world, pos, trunkHeight, template);

        if (hasSpace)
        {
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);

            BlockPos.Mutable target = new BlockPos.Mutable();
            for (int y = pos.getY(); y < pos.getY() + treeHeight; y++)
            {
                for (int x = pos.getX() - 3; x <= pos.getX() + 3; x++)
                {
                    for (int z = pos.getZ() - 3; z <= pos.getZ() + 3; z++)
                    {
                        target.setPos(x, y, z);
                        char inputChar = template[arrayY][arrayX][arrayZ];
                        switch (inputChar)
                        {
                            case 'L':
                                world.setBlockState(target, LEAF_PALM, 3);
                                break;
                            case 'W':
                                world.setBlockState(target, LOG_PALM, 3);
                                break;
                            case 'V':
                                world.setBlockState(target, LOG_PALM.with(RotatedPillarBlock.AXIS, Direction.Axis.X), 3);
                                break;
                            case 'H':
                                world.setBlockState(target, LOG_PALM.with(RotatedPillarBlock.AXIS, Direction.Axis.Z), 3);
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
        }
    }
}
