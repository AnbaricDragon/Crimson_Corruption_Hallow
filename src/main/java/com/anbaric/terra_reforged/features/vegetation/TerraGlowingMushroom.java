package com.anbaric.terra_reforged.features.vegetation;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.*;
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
  {{{'O', 'A', 'B', 'C', 'O'},
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

    private boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        Block target = state.getBlock();
        return target == TerraBlockRegistry.GRASS_MUSHROOM.get();
    }

    public static boolean checkSpace(IWorld world, BlockPos pos, int trunkHeight, char[][][] template)
    {
        System.out.println("Successfully checking space");

        int     arrayX     = 0, arrayY = 0, arrayZ = 0;
        int     radius     = 2;
        int     treeHeight = template.length;
        boolean canGrow = true;
        int blockage = 0;

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
                    if (target.getY() > world.getMaxHeight() || target.getY() < 1) {return false;}
                    if (targetChar == 'W')
                    {
                        if (!world.getBlockState(target).canBeReplacedByLogs(world, pos))
                        {
                            canGrow = false;
                        }
                    }
                    else if (targetChar != 'O')
                    {
                        if (!world.getBlockState(target).canBeReplacedByLeaves(world, target))
                        {
                            blockage++;
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
        return blockage < 4 && canGrow;
    }

    public static boolean checkBranchSpace(IWorld world, BlockPos pos, int offshootLength, int offshootHeight, Direction offshootDir, char[][][] template)
    {
        System.out.println("Successfully checking branch space");
        int     arrayX     = 0, arrayY = 2, arrayZ = 0;
        int     radius     = 2;
        boolean canGrow = true;
        BlockPos.Mutable target = new BlockPos.Mutable();

        target = target.setPos(pos.getX(), pos.getY(), pos.getZ());

        for (int j = 0; j <= offshootLength; j++)
        {
            if (!world.getBlockState(target.move(offshootDir, j)).canBeReplacedByLogs(world, pos))
            {
                canGrow = false;
            }
        }
        for (int i = 0; i <= offshootHeight; i++)
        {
            if (!world.getBlockState(target.move(Direction.UP, i)).canBeReplacedByLogs(world, pos))
            {
                canGrow = false;
            }
        }
        for (int y = target.getY(); y <= target.getY() + 1; y++)
        {
            for (int x = target.getX() - radius; x <= target.getX() + radius; x++)
            {
                for (int z = target.getZ() - radius; z <= target.getZ() + radius; z++)
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
                    else if (targetChar != 'O')
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

    public static void placeBlock(IWorld world, BlockPos pos, BlockState block)
    {
        if (world.getBlockState(pos).canBeReplacedByLeaves(world, pos))
        {
            world.setBlockState(pos, block, 3);
        }
    }

    private static int getRandomNumberInRange(int min, int max) {

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static void generateTree(IWorld world, BlockPos pos, Random rand)
    {
        int        arrayX      = 0, arrayY = 0, arrayZ = 0;
        int        trunkHeight = rand.nextInt(7) + 1;
        int        treeHeight  = MUSHROOM_ARRAY.length;
        int        radius      = 2;
        char[][][] template    = MUSHROOM_ARRAY;
        boolean    hasSpace    = checkSpace(world, pos, trunkHeight, template);
        boolean canSeeSky = world.canBlockSeeSky(pos);

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
            for (int i = 0; i < trunkHeight; i++)
            {
                world.setBlockState(pos.up(i), LOG_MUSHROOM, 3);
            }
//            target.setPos(pos.getX(), pos.getY(), pos.getZ());
//            if (canSeeSky && trunkHeight > 2)
//            {
//                int offshootHeight = rand.nextInt(2);
//                int offshootPos = getRandomNumberInRange(1, trunkHeight - 2);
//                int offshootLength = rand.nextInt(2) + 1;
//                Direction offshootDir = Direction.byHorizontalIndex(rand.nextInt(4));
//                boolean canSpawnBranch = checkBranchSpace(world, pos.add(0, offshootPos, 0), offshootLength, offshootHeight, offshootDir, template);
//
//                target.add(0, offshootPos, 0);
//
//                for (int j = 0; j <= offshootLength; j++)
//                {
//                    world.setBlockState(target.move(offshootDir, j), LOG_MUSHROOM.with(LogBlock.AXIS, offshootDir.getAxis()), 3);
//                }
//                for (int i = 0; i <= offshootHeight; i++)
//                {
//                    world.setBlockState(target.move(Direction.UP), LOG_MUSHROOM, 3);
//                }
//                arrayY = 2;
//                BlockPos branchCapPos = target;
//                for (int y = branchCapPos.getY(); y <= branchCapPos.getY() + 1; y++)
//                {
//                    for (int x = branchCapPos.getX() - radius; x <= branchCapPos.getX() + radius; x++)
//                    {
//                        for (int z = branchCapPos.getZ() - radius; z <= branchCapPos.getZ() + radius; z++)
//                        {
//                            target.setPos(x, y, z);
//                            char targetChar = template[arrayY][arrayX][arrayZ];
//                            target.setPos(x, y, z);
//                            char inputChar = template[arrayY][arrayX][arrayZ];
//                            switch (inputChar)
//                            {
//                                case 'A':
//                                    placeBlock(world, target, CAP_MUSHROOM_NORTH_WEST);
//                                    break;
//                                case 'B':
//                                    placeBlock(world, target, CAP_MUSHROOM_WEST);
//                                    break;
//                                case 'C':
//                                    placeBlock(world, target, CAP_MUSHROOM_SOUTH_WEST);
//                                    break;
//                                case 'D':
//                                    placeBlock(world, target, CAP_MUSHROOM_SOUTH);
//                                    break;
//                                case 'E':
//                                    placeBlock(world, target, CAP_MUSHROOM_SOUTH_EAST);
//                                    break;
//                                case 'F':
//                                    placeBlock(world, target, CAP_MUSHROOM_EAST);
//                                    break;
//                                case 'G':
//                                    placeBlock(world, target, CAP_MUSHROOM_NORTH_EAST);
//                                    break;
//                                case 'H':
//                                    placeBlock(world, target, CAP_MUSHROOM_NORTH);
//                                    break;
//                                case 'I':
//                                    placeBlock(world, target, CAP_MUSHROOM_UP);
//                                    break;
//                                case 'W':
//                                    world.setBlockState(target, LOG_MUSHROOM, 3);
//                                    break;
//                                case 'v':
//                                    world.setBlockState(target, LOG_MUSHROOM.with(LogBlock.AXIS, Direction.Axis.X), 3);
//                                    break;
//                                case 'h':
//                                    world.setBlockState(target, LOG_MUSHROOM.with(LogBlock.AXIS, Direction.Axis.Z), 3);
//                                    break;
//                            }
//                            arrayZ++;
//                        }
//                        arrayX++;
//                        arrayZ = 0;
//                    }
//                    arrayY++;
//                    arrayX = 0;
//                }
//            }
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