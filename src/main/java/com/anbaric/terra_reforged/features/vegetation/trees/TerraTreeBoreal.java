package com.anbaric.terra_reforged.features.vegetation.trees;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class TerraTreeBoreal extends Feature<NoFeatureConfig>
{
    public static final BlockState LOG_BOREAL = TerraBlockRegistry.LOG_BOREAL.get().getDefaultState();
    public static final BlockState LEAF_BOREAL = TerraBlockRegistry.LEAF_BOREAL.get().getDefaultState();

    public static final char[][][] BOREAL_ARRAY =
    {{{'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'L', 'O', 'O'},
    {'O', 'L', 'W', 'L', 'O'},
    {'O', 'O', 'L', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O'}},

    {{'O', 'O', 'L', 'O', 'O'},
    {'O', 'L', 'L', 'L', 'O'},
    {'L', 'L', 'W', 'L', 'L'},
    {'O', 'L', 'L', 'L', 'O'},
    {'O', 'O', 'L', 'O', 'O'}},

    {{'O', 'L', 'L', 'L', 'O'},
    {'L', 'L', 'W', 'L', 'L'},
    {'L', 'W', 'W', 'W', 'L'},
    {'L', 'L', 'W', 'L', 'L'},
    {'O', 'L', 'L', 'L', 'O'}},

    {{'O', 'R', 'L', 'R', 'O'},
    {'R', 'L', 'G', 'L', 'R'},
    {'L', 'G', 'W', 'G', 'L'},
    {'R', 'L', 'G', 'L', 'R'},
    {'O', 'R', 'L', 'R', 'O'}},

    {{'O', 'B', 'R', 'B', 'O'},
    {'B', 'L', 'D', 'L', 'B'},
    {'R', 'D', 'W', 'D', 'R'},
    {'B', 'L', 'D', 'L', 'B'},
    {'O', 'B', 'R', 'B', 'O'}},

    {{'O', 'O', 'B', 'O', 'O'},
    {'O', 'R', 'L', 'R', 'O'},
    {'B', 'L', 'W', 'L', 'B'},
    {'O', 'R', 'L', 'R', 'O'},
    {'O', 'O', 'B', 'O', 'O'}},

    {{'O', 'O', 'O', 'O', 'O'},
    {'O', 'B', 'R', 'B', 'O'},
    {'O', 'R', 'G', 'R', 'O'},
    {'O', 'B', 'R', 'B', 'O'},
    {'O', 'O', 'O', 'O', 'O'}},

    {{'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'B', 'O', 'O'},
    {'O', 'B', 'D', 'B', 'O'},
    {'O', 'O', 'B', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O'}},

    {{'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'R', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O'}},

    {{'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'B', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O'},
    {'O', 'O', 'O', 'O', 'O'}}};

    public TerraTreeBoreal(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory)
    {
        super(configFactory);
    }

    private boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        Block target = state.getBlock();
        return target == Blocks.SNOW_BLOCK || target == TerraBlockRegistry.SNOW_CORRUPT.get() || target == TerraBlockRegistry.SNOW_CRIMSON.get() || target == TerraBlockRegistry.SNOW_HALLOWED.get() || target == Blocks.DIRT || target == Blocks.GRASS_BLOCK;
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
                    char inputChar = inputArray[y][x][z];
                    switch (canopyHeight)
                    {
                        case 0:
                            outputArray[y][x][z] = inputChar == 'R' || inputChar == 'B' ? 'O' :
                                                   inputChar == 'G' || inputChar == 'D' ? 'L' :
                                                   inputChar;
                            break;

                        case 1:
                            outputArray[y][x][z] = inputChar == 'B' ? 'O' :
                                                   inputChar == 'G' ? 'W' :
                                                   inputChar == 'R' || inputChar == 'D' ? 'L' :
                                                   inputChar;
                            break;

                        case 2:
                            outputArray[y][x][z] = inputChar == 'R' || inputChar == 'B' ? 'L' :
                                                   inputChar == 'G' || inputChar == 'D' ? 'W' :
                                                   inputChar;
                            break;
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
                    if (targetChar == 'W')
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
        int        arrayX       = 0, arrayY = 0, arrayZ = 0;
        int        trunkHeight  = rand.nextInt(3) + 1;
        int        canopyHeight = rand.nextInt(3);
        int        treeHeight   = BOREAL_ARRAY.length;
        char[][][] template     = mutateArray(BOREAL_ARRAY, canopyHeight);

        if (checkSpace(world, pos, trunkHeight, template))
        {
            BlockPos.Mutable target = new BlockPos.Mutable();
            for (int i = 0; i < trunkHeight; i++)
            {
                world.setBlockState(pos.up(i), LOG_BOREAL, 3);
            }
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
                                world.setBlockState(target, LEAF_BOREAL, 3);
                                break;
                            case 'W':
                                world.setBlockState(target, LOG_BOREAL, 3);

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