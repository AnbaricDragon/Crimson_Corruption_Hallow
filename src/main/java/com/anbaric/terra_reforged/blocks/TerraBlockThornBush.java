package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;

import java.util.Map;
import java.util.Random;

public class TerraBlockThornBush extends Block
{
    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final Map<Direction, BooleanProperty> FACING_TO_PROPERTY_MAP = Util.make(Maps.newEnumMap(Direction.class), (facingMap) -> {
        facingMap.put(Direction.NORTH, NORTH);
        facingMap.put(Direction.EAST, EAST);
        facingMap.put(Direction.SOUTH, SOUTH);
        facingMap.put(Direction.WEST, WEST);
        facingMap.put(Direction.UP, UP);
        facingMap.put(Direction.DOWN, DOWN);
    });

    public static final IntegerProperty GROWTH = IntegerProperty.create("growth", 0, 9);

    private final VoxelShape[] renderShapes;

    private final Float damage;

    public TerraBlockThornBush(Properties properties, Float damage)
    {
        super(properties);
        this.damage = damage;

        this.renderShapes = this.makeShapes();
        this.setDefaultState(this.stateContainer.getBaseState().with(UP, false).with(DOWN, false).with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(GROWTH, 0));
    }

    protected VoxelShape[] makeShapes()
    {
        VoxelShape base = Block.makeCuboidShape(3.0D, 3.0D, 3.0D, 13.0D, 13.0D, 13.0D);
        VoxelShape D    = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 3.0D, 13.0D);
        VoxelShape U    = Block.makeCuboidShape(3.0D, 13.0D, 3.0D, 13.0D, 16.0D, 13.0D);
        VoxelShape N    = Block.makeCuboidShape(3.0D, 3.0D, 0.0D, 13.0D, 13.0D, 3.0D);
        VoxelShape S    = Block.makeCuboidShape(3.0D, 3.0D, 13.0D, 13.0D, 13.0D, 16.0D);
        VoxelShape W    = Block.makeCuboidShape(0.0D, 3.0D, 3.0D, 3.0D, 13.0D, 13.0D);
        VoxelShape E    = Block.makeCuboidShape(13.0D, 3.0D, 3.0D, 16.0D, 13.0D, 13.0D);

        VoxelShape X = VoxelShapes.or(E, W);
        VoxelShape Y = VoxelShapes.or(U, D);
        VoxelShape Z = VoxelShapes.or(N, S);

        VoxelShape[] avoxelshape = new VoxelShape[]{VoxelShapes.empty(), D, U, Y, N, VoxelShapes.or(N, D), VoxelShapes.or(N, U), VoxelShapes.or(N, Y), S, VoxelShapes.or(S, D), VoxelShapes.or(S, U), VoxelShapes.or(S, Y), Z, VoxelShapes.or(Z, D), VoxelShapes.or(Z, U), VoxelShapes.or(Z, Y), W, VoxelShapes.or(W, D), VoxelShapes.or(W, U), VoxelShapes.or(W, Y), VoxelShapes.or(W, N), VoxelShapes.or(W, N, D), VoxelShapes.or(W, N, U), VoxelShapes.or(W, N, Y), VoxelShapes.or(W, S), VoxelShapes.or(W, S, D), VoxelShapes.or(W, S, U), VoxelShapes.or(W, S, Y), VoxelShapes.or(W, Z), VoxelShapes.or(W, Z, D), VoxelShapes.or(W, Z, U), VoxelShapes.or(W, Z, Y), E, VoxelShapes.or(E, D), VoxelShapes.or(E, U), VoxelShapes.or(E, Y), VoxelShapes.or(E, N), VoxelShapes.or(E, N, D), VoxelShapes.or(E, N, U), VoxelShapes.or(E, N, Y), VoxelShapes.or(E, S), VoxelShapes.or(E, S, D), VoxelShapes.or(E, S, U), VoxelShapes.or(E, S, Y), VoxelShapes.or(E, Z), VoxelShapes.or(E, Z, D), VoxelShapes.or(E, Z, U), VoxelShapes.or(E, Z, Y), X, VoxelShapes.or(X, D), VoxelShapes.or(X, U), VoxelShapes.or(X, Y), VoxelShapes.or(X, N), VoxelShapes.or(X, N, D), VoxelShapes.or(X, N, U), VoxelShapes.or(X, N, Y), VoxelShapes.or(X, S), VoxelShapes.or(X, S, D), VoxelShapes.or(X, S, U), VoxelShapes.or(X, S, Y), VoxelShapes.or(X, Z), VoxelShapes.or(X, Z, D), VoxelShapes.or(X, Z, U), VoxelShapes.or(X, Z, Y)};
        for (int i = 0; i < 64; ++i)
        {
            avoxelshape[i] = VoxelShapes.or(base, avoxelshape[i]);
        }

        return avoxelshape;
    }

    private static int getMask(Direction facing)
    {
        return 1 << facing.getIndex();
    }

    protected int getIndex(BlockState state)
    {
        int i = 0;
        if (state.get(DOWN))
        {
            i |= getMask(Direction.DOWN);
        }
        if (state.get(UP))
        {
            i |= getMask(Direction.UP);
        }
        if (state.get(NORTH))
        {
            i |= getMask(Direction.NORTH);
        }
        if (state.get(SOUTH))
        {
            i |= getMask(Direction.SOUTH);
        }
        if (state.get(WEST))
        {
            i |= getMask(Direction.WEST);
        }
        if (state.get(EAST))
        {
            i |= getMask(Direction.EAST);
        }
        return i;
    }

    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type)
    {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return this.renderShapes[this.getIndex(state)];
    }

    public boolean canAttach(BlockState state, boolean solidSide)
    {
        Block   block = state.getBlock();
        boolean flag  = state.isSolid();
        boolean flag1 = block instanceof TerraBlockThornBush;
        return !cannotAttach(block) && solidSide || flag || flag1;
    }

    public Block getGrass()
    {
        if (this == TerraBlockRegistry.PLANT_THORN_PURPLE.get() || this == TerraBlockRegistry.PLANT_THORN_RED.get())
        {
            return Blocks.GRASS_BLOCK;
        }
        else
        {
            return TerraBlockRegistry.GRASS_JUNGLE.get();
        }
    }

    public int getProperGrowth(IBlockReader worldIn, BlockPos pos)
    {
        int     lowNei  = 0;
        boolean checked = false;

        for (int dirIndex = 0; dirIndex < 6; dirIndex++)
        {
            BlockState targetState = worldIn.getBlockState(pos.offset(Direction.byIndex(dirIndex)));
            if (dirIndex == 0 && targetState.getBlock() == this.getGrass())
            {
                return 0;
            }
            if (targetState.getBlock() instanceof TerraBlockThornBush)
            {
                if (checked)
                {
                    lowNei = Math.min(lowNei, targetState.get(GROWTH));
                }
                else
                {
                    lowNei = targetState.get(GROWTH);
                    checked = true;
                }
            }
        }
        return Math.min(lowNei + 1, 9);
    }

    public int getLowestNeighbor(IBlockReader worldIn, BlockPos pos)
    {
        int     lowNei  = 0;
        boolean checked = false;
        for (int dirIndex = 0; dirIndex < 6; dirIndex++)
        {
            BlockState targetState = worldIn.getBlockState(pos.offset(Direction.byIndex(dirIndex)));
            if (targetState.getBlock() instanceof TerraBlockThornBush)
            {
                if (checked)
                {
                    lowNei = Math.min(lowNei, targetState.get(GROWTH));
                }
                else
                {
                    lowNei = targetState.get(GROWTH);
                    checked = true;
                }
            }
        }
        return lowNei;
    }

    public int getNeighborCount(IBlockReader worldIn, BlockPos pos)
    {
        int countNei = 0;
        for (int dirIndex = 0; dirIndex < 6; dirIndex++)
        {
            BlockState targetState = worldIn.getBlockState(pos.offset(Direction.byIndex(dirIndex)));
            if (targetState.getBlock() instanceof TerraBlockThornBush)
            {
                countNei++;
            }
        }
        return countNei;
    }

    public String getBiome()
    {
        if (this == TerraBlockRegistry.PLANT_THORN_PURPLE.get()) { return "corrupt"; }
        else if (this == TerraBlockRegistry.PLANT_THORN_PURPLE.get()) { return "crimson"; }
        else return this == TerraBlockRegistry.PLANT_THORN_PURPLE.get() ? "mud" : "blabber";
    }

    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        IBlockReader world     = context.getWorld();
        BlockPos     blockpos  = context.getPos();
        BlockPos     blockposN = blockpos.north();
        BlockPos     blockposE = blockpos.east();
        BlockPos     blockposS = blockpos.south();
        BlockPos     blockposW = blockpos.west();
        BlockPos     blockposU = blockpos.up();
        BlockPos     blockposD = blockpos.down();
        BlockState   stateN    = world.getBlockState(blockposN);
        BlockState   stateE    = world.getBlockState(blockposE);
        BlockState   stateS    = world.getBlockState(blockposS);
        BlockState   stateW    = world.getBlockState(blockposW);
        BlockState   stateU    = world.getBlockState(blockposU);
        BlockState   stateD    = world.getBlockState(blockposD);
        return super.getStateForPlacement(context)
                .with(NORTH, this.canAttach(stateN, stateN.isSolidSide(world, blockposN, Direction.SOUTH)))
                .with(EAST, this.canAttach(stateE, stateE.isSolidSide(world, blockposE, Direction.WEST)))
                .with(SOUTH, this.canAttach(stateS, stateS.isSolidSide(world, blockposS, Direction.NORTH)))
                .with(WEST, this.canAttach(stateW, stateW.isSolidSide(world, blockposW, Direction.EAST)))
                .with(UP, this.canAttach(stateU, stateU.isSolidSide(world, blockposU, Direction.DOWN)))
                .with(DOWN, this.canAttach(stateD, stateD.isSolidSide(world, blockposD, Direction.UP)))
                .with(GROWTH, this.getProperGrowth(world, blockpos));
    }

    public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        return state.with(FACING_TO_PROPERTY_MAP.get(facing), this.canAttach(facingState, facingState.isSolidSide(worldIn, facingPos, facing.getOpposite())));
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
    {
        entityIn.attackEntityFrom(TerraReforged.THORNS, this.damage);
        worldIn.destroyBlock(pos, false);
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving)
    {
        if (!isValidPosition(state, worldIn, pos))
        {
            worldIn.destroyBlock(pos, false);
        }
    }

    //for placing block
    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        if (state.get(GROWTH) == 0)
        {
            return worldIn.getBlockState(pos.down()).getBlock() == this.getGrass();
        }
        boolean thornNeighbor = false;
        for (int dirIndex = 0; dirIndex < 6; dirIndex++)
        {
            BlockState targetState = worldIn.getBlockState(pos.offset(Direction.byIndex(dirIndex)));
            if (targetState.getBlock() instanceof TerraBlockThornBush)
            {
                thornNeighbor = true;
            }
        }
        return thornNeighbor && getLowestNeighbor(worldIn, pos) < state.get(GROWTH);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        int growth = state.get(GROWTH);
        if (!isValidPosition(state, worldIn, pos))
        {
            worldIn.destroyBlock(pos, false);
        }
        else if (growth < 9)
        {
            int      dirIndex = random.nextInt(6);
            BlockPos dirPos   = pos.offset(Direction.byIndex(dirIndex));
            if (worldIn.getBlockState(dirPos).getBlock() == Blocks.AIR && getNeighborCount(worldIn, dirPos) < 2 && worldIn.getBiome(dirPos).getRegistryName().toString().contains(this.getBiome()))
            {
                worldIn.setBlockState(dirPos, this.getDefaultState().with(GROWTH, growth + 1));
            }
        }
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST, GROWTH);
    }
}
