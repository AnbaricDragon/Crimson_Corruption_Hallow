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

public class TerraFeatureTreeMahoganyLarge extends Feature<NoFeatureConfig>
{
    public TerraFeatureTreeMahoganyLarge()
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

    static final char[][][] trunkSeed =
    //0
    {{{'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'Y', 'y', 'O', 'O'},
    {'O', 'Y', 'Y', 'Y', 'O', 'O'},
    {'O', 'O', 'Y', 'Y', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O'}},
    //1
    {{'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O'},
    {'O', 'Y', 'Y', 'y', 'O', 'O'},
    {'O', 'Y', 'C', 'Y', 'O', 'O'},
    {'O', 'O', 'Y', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O', 'O'}}};

    static final char[][][] canopySeed =
    //0
    {{{'O','O','O','s','S','s','S','s','O','O','O'},
    {'O','s','S','S','S','X','S','S','S','s','O'},
    {'O','S','S','S','O','X','O','S','S','S','O'},
    {'s','S','S','O','O','X','O','O','S','S','s'},
    {'S','S','O','O','O','O','O','O','O','S','S'},
    {'s','Z','Z','Z','O','O','O','Z','Z','Z','s'},
    {'S','S','O','O','O','O','O','O','O','S','S'},
    {'s','S','S','O','O','X','O','O','S','S','s'},
    {'O','S','S','S','O','X','O','S','S','S','O'},
    {'O','s','S','S','S','X','S','S','S','s','O'},
    {'O','O','O','s','S','s','S','s','O','O','O'}},

    {{'O','O','O','O','O','O','O','O','O','O','O'},
    {'O','O','O','O','S','S','S','O','O','O','O'},
    {'O','O','S','S','S','S','S','S','S','O','O'},
    {'O','O','S','S','X','S','X','S','S','O','O'},
    {'O','S','S','Z','Y','S','Y','Z','S','S','O'},
    {'O','S','S','S','S','S','S','S','S','S','O'},
    {'O','S','S','Z','Y','S','Y','Z','S','S','O'},
    {'O','O','S','S','X','S','X','S','S','O','O'},
    {'O','O','S','S','S','S','S','S','S','O','O'},
    {'O','O','O','O','S','S','S','O','O','O','O'},
    {'O','O','O','O','O','O','O','O','O','O','O'}},

    {{'O','O','O','O','O','O','O','O','O','O','O'},
    {'O','O','O','O','O','O','O','O','O','O','O'},
    {'O','O','O','O','O','O','O','O','O','O','O'},
    {'O','O','O','O','S','S','S','O','O','O','O'},
    {'O','O','O','S','S','S','S','S','O','O','O'},
    {'O','O','O','S','S','S','S','S','O','O','O'},
    {'O','O','O','S','S','S','S','S','O','O','O'},
    {'O','O','O','O','S','S','S','O','O','O','O'},
    {'O','O','O','O','O','O','O','O','O','O','O'},
    {'O','O','O','O','O','O','O','O','O','O','O'},
    {'O','O','O','O','O','O','O','O','O','O','O'}}};

    static char[][][] blocks = new char[40][6][6];

    public static void rotateArray()
    {
        for (int i = 0; i < trunkSeed.length; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                for (int x = 0; x < 6 / 2; x++)
                {
                    for (int y = x; y < 6-x-1; y++)
                    {
                        char temp = trunkSeed[i][x][y];

                        trunkSeed[i][x][y] = trunkSeed[i][y][6-1-x];
                        trunkSeed[i][y][6-1-x] = trunkSeed[i][6-1-x][6-1-y];
                        trunkSeed[i][6-1-x][6-1-y] = trunkSeed[i][6-1-y][x];
                        trunkSeed[i][6-1-y][x] = temp;
                    }
                }
            }
        }
    }

    public static void mutateArray(int variTrunk, Random rand)
    {
        int variSpin = rand.nextInt(4);
        for (int k = 0; k <= variSpin; k++)
        {
            rotateArray();
        }
        for (int l = 0; l < variTrunk; l = l + 2)
        {
            for (int i = 0; i < trunkSeed.length; i++)
            {
                for (int j = 0; j < trunkSeed[i].length; j++)
                {
                    for (int k = 0; k < trunkSeed[i][j].length; k++)
                    {
                        char charIndex = trunkSeed[i][j][k];
                        //If leaf or log, leaves it alone
                        if (l < 2)
                        {
                            if (charIndex == 'Y' || charIndex == 'O' || charIndex == 'X' || charIndex == 'Z' || charIndex == 'C') { blocks[i + l][j][k] = charIndex; }
                            else if (charIndex == 'y' || charIndex == 'w')  { blocks[i + l][j][k] = 'Y'; }
                            else if (charIndex == 'z')  { blocks[i + l][j][k] = 'Z'; }
                            else if (charIndex == 'x')  { blocks[i + l][j][k] = 'X'; }
                            else { blocks[i + l][j][k] = 'O'; }
                        }
                        else
                        {
                            if (charIndex == 'Y' || charIndex == 'O' || charIndex == 'X' || charIndex == 'Z' || charIndex == 'C') { blocks[i + l][j][k] = charIndex; }
                            else if (charIndex == 'x' || charIndex == 'z' || charIndex == 'w')  { blocks[i + l][j][k] = 'O'; }
                            else if (charIndex == 'y')
                            {
                                float variBlock = rand.nextFloat();
                                blocks[i + l][j][k] = variBlock > 0.5F ? 'Y' : 'O';
                            }
                            else { blocks[i + l][j][k] = 'O'; }
                        }
                    }
                }
            }
            rotateArray();
        }
    }

    public static boolean generateTree(IWorld world, BlockPos posD, Random rand)
    {
        BlockPos pos = posD.add(1, 0, 1);
        //Randomness Generator
        int variTrunk = (rand.nextInt(13) * 2) + 10;
        int maxHeight = variTrunk + 3;
        mutateArray(variTrunk, rand);

        //Setup
        int treeScale = blocks[0].length * blocks[0][0].length;
        boolean isClear = true;
        int layerIndex = 0, index2 = 0, index3 = 0;

        //Calculates offset from center
        int treeWdth = 6;
        int offset = 3;

        int baseY = pos.getY();
        int baseX = pos.getX();
        int baseZ = pos.getZ();

        BlockPos canopyPos = new BlockPos(0, 0, 0);

        //Checking if Tree can fit
        if (baseY + maxHeight < world.getHeight())
        {
            for (int y = baseY; y < (baseY + maxHeight); y++)
            {
                for (int x = baseX - offset; x < baseX - offset + treeWdth; x++)
                {
                    for (int z = baseZ - offset; z < baseZ - offset + treeWdth; z++)
                    {
                        //Setup
                        BlockPos checkerPos = new BlockPos(x, y, z);
                        char indexChar = blocks[layerIndex][index2][index3];

                        //Checking only potential blocks
                        if (indexChar == 'C')
                        {
                            canopyPos = checkerPos;
                        }
                        if (!world.getBlockState(checkerPos).canBeReplacedByLogs(world, pos) && indexChar != 'O')
                        {
                            isClear = false;
                        }
                        if (checkerPos.getY() >= world.getHeight())
                        {
                            isClear = false;
                        }

                        index3++;
                    }
                    index3 = 0;
                    index2++;
                }
                index2 = 0;
                layerIndex++;
            }
            layerIndex = 0;

            for (int y = canopyPos.getY(); y < canopyPos.getY() + 2; y++)
            {
                for (int x = canopyPos.getX() - 5; x < canopyPos.getX() - 5 + 11; x++)
                {
                    for (int z = canopyPos.getZ() - 5; z < canopyPos.getZ() - 5 + 11; z++)
                    {
                        //Setup
                        BlockPos checkerPos = new BlockPos(x, y, z);
                        char indexChar = canopySeed[layerIndex][index2][index3];

                        //Checking only potential blocks
                        if (!world.getBlockState(checkerPos).canBeReplacedByLeaves(world, pos) && indexChar != 'O')
                        {
                            isClear = false;
                        }
                        if (checkerPos.getY() >= world.getHeight())
                        {
                            isClear = false;
                        }

                        index3++;
                    }
                    index3 = 0;
                    index2++;
                }
                index2 = 0;
                layerIndex++;
            }
            layerIndex = 0;


            //Generating Tree if all is well
            if (isClear)
            {
                //Generating trunk
                for (int y = baseY; y < (baseY + maxHeight); y++)
                {
                    for (int x = baseX - offset; x < baseX - offset + treeWdth; x++)
                    {
                        for (int z = baseZ - offset; z < baseZ - offset + treeWdth; z++)
                        {
                            //Setup
                            BlockPos placerPos = new BlockPos(x, y, z);
                            BlockState placerState = world.getBlockState(placerPos);
                            char indexChar = blocks[layerIndex][index2][index3];

                            if (placerState.canBeReplacedByLogs(world, pos))
                            {
                                if (indexChar == 'Y' || indexChar == 'C')
                                {
                                    world.setBlockState(placerPos, LOG_MAHOGANY, 3);
                                }
                                else if (indexChar == 'X')
                                {
                                    world.setBlockState(placerPos, LOG_MAHOGANY.with(RotatedPillarBlock.AXIS, Direction.Axis.X), 3);
                                }
                                else if (indexChar == 'Z')
                                {
                                    world.setBlockState(placerPos, LOG_MAHOGANY.with(RotatedPillarBlock.AXIS, Direction.Axis.Z), 3);
                                }
                            }
                            index3++;
                        }
                        index3 = 0;
                        index2++;
                    }
                    index2 = 0;
                    layerIndex++;
                }
                layerIndex = 0;

                //Generating canopy
                for (int y = canopyPos.getY(); y < canopyPos.getY() + 3; y++)
                {
                    for (int x = canopyPos.getX() - 5; x < canopyPos.getX() - 5 + 11; x++)
                    {
                        for (int z = canopyPos.getZ() - 5; z < canopyPos.getZ() - 5 + 11; z++)
                        {
                            //Setup
                            BlockPos placerPos = new BlockPos(x, y, z);
                            BlockState placerState = world.getBlockState(placerPos);
                            int droop = rand.nextInt(3) + 1;
                            char indexChar = canopySeed[layerIndex][index2][index3];

                            if (placerState.canBeReplacedByLeaves(world, pos))
                            {
                                if (indexChar == 'Y' || indexChar == 'C')
                                {
                                    world.setBlockState(placerPos, LOG_MAHOGANY, 3);
                                }
                                else if (indexChar == 'X')
                                {
                                    world.setBlockState(placerPos, LOG_MAHOGANY.with(RotatedPillarBlock.AXIS, Direction.Axis.X), 3);
                                }
                                else if (indexChar == 'Z')
                                {
                                    world.setBlockState(placerPos, LOG_MAHOGANY.with(RotatedPillarBlock.AXIS, Direction.Axis.Z), 3);
                                }
                                if (indexChar == 'S')
                                {
                                    world.setBlockState(placerPos, LEAF_MAHOGANY, 3);
                                }
                                else if (indexChar == 's')
                                {
                                    world.setBlockState(placerPos, LEAF_MAHOGANY, 3);

                                    for (int i = 1; i <= droop; i++)
                                    {
                                        if (world.getBlockState(placerPos.add(0, -i, 0)).getBlock() == Blocks.AIR)
                                        {
                                            world.setBlockState(placerPos.add(0, -i, 0), LEAF_MAHOGANY, 3);
                                        }
                                    }
                                }
                            }
                            index3++;
                        }
                        index3 = 0;
                        index2++;
                    }
                    index2 = 0;
                    layerIndex++;
                }
                layerIndex = 0;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
}
