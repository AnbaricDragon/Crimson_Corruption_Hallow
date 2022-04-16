package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Random;

public class TerraBlockPlantThorns extends BushBlock
{
    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final IntegerProperty GROWTH = IntegerProperty.create("growth", 0, 9);
    public static final Map<Direction, BooleanProperty> FACING_TO_PROPERTY_MAP = Util.make(Maps.newEnumMap(Direction.class), (facingMap) -> {
        facingMap.put(Direction.NORTH, NORTH);
        facingMap.put(Direction.EAST, EAST);
        facingMap.put(Direction.SOUTH, SOUTH);
        facingMap.put(Direction.WEST, WEST);
        facingMap.put(Direction.UP, UP);
        facingMap.put(Direction.DOWN, DOWN);
    });

    private final VoxelShape[] renderShapes;

    private final Float damage;

    public TerraBlockPlantThorns(Properties properties, float damage)
    {
        super(properties);
        this.damage = damage;

        this.renderShapes = this.makeShapes();
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(UP, false)
                .setValue(DOWN, false)
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false)
                .setValue(GROWTH, 0));
    }


    protected VoxelShape[] makeShapes()
    {
        VoxelShape base = Block.box(3.0D, 3.0D, 3.0D, 13.0D, 13.0D, 13.0D);
        VoxelShape D    = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 3.0D, 13.0D);
        VoxelShape U    = Block.box(3.0D, 13.0D, 3.0D, 13.0D, 16.0D, 13.0D);
        VoxelShape N    = Block.box(3.0D, 3.0D, 0.0D, 13.0D, 13.0D, 3.0D);
        VoxelShape S    = Block.box(3.0D, 3.0D, 13.0D, 13.0D, 13.0D, 16.0D);
        VoxelShape W    = Block.box(0.0D, 3.0D, 3.0D, 3.0D, 13.0D, 13.0D);
        VoxelShape E    = Block.box(13.0D, 3.0D, 3.0D, 16.0D, 13.0D, 13.0D);

        VoxelShape X = Shapes.or(E, W);
        VoxelShape Y = Shapes.or(U, D);
        VoxelShape Z = Shapes.or(N, S);

        VoxelShape[]
                avoxelshape =
                new VoxelShape[]{Block.box(0D, 0D, 0D, 0D, 0D, 0D), D, U, Y, N, Shapes.or(N, D), Shapes.or(N, U), Shapes.or(N, Y), S, Shapes.or(S, D), Shapes.or(S, U), Shapes.or(S, Y), Z, Shapes.or(Z, D), Shapes.or(Z, U), Shapes.or(Z, Y), W, Shapes.or(W, D), Shapes.or(W, U), Shapes.or(W, Y), Shapes.or(W, N), Shapes.or(W, N, D), Shapes.or(W, N, U), Shapes.or(W, N, Y), Shapes.or(W, S), Shapes.or(W, S, D), Shapes.or(W, S, U), Shapes.or(W, S, Y), Shapes.or(W, Z), Shapes.or(W, Z, D), Shapes.or(W, Z, U), Shapes.or(W, Z, Y), E, Shapes.or(E, D), Shapes.or(E, U), Shapes.or(E, Y), Shapes.or(E, N), Shapes.or(E, N, D), Shapes.or(E, N, U), Shapes.or(E, N, Y), Shapes.or(E, S), Shapes.or(E, S, D), Shapes.or(E, S, U), Shapes.or(E, S, Y), Shapes.or(E, Z), Shapes.or(E, Z, D), Shapes.or(E, Z, U), Shapes.or(E, Z, Y), X, Shapes.or(X, D), Shapes.or(X, U), Shapes.or(X, Y), Shapes.or(X, N), Shapes.or(X, N, D), Shapes.or(X, N, U), Shapes.or(X, N, Y), Shapes.or(X, S), Shapes.or(X, S, D), Shapes.or(X, S, U), Shapes.or(X, S, Y), Shapes.or(X, Z), Shapes.or(X, Z, D), Shapes.or(X, Z, U), Shapes.or(X, Z, Y)};
        for (int i = 0; i < 64; ++i)
        {
            avoxelshape[i] = Shapes.or(base, avoxelshape[i]);
        }

        return avoxelshape;
    }

    private static int getMask(Direction facing)
    {
        return 1 << facing.get3DDataValue();
    }

    protected int getIndex(BlockState state)
    {
        int i = 0;
        if (state.getValue(DOWN))
        {
            i |= getMask(Direction.DOWN);
        }
        if (state.getValue(UP))
        {
            i |= getMask(Direction.UP);
        }
        if (state.getValue(NORTH))
        {
            i |= getMask(Direction.NORTH);
        }
        if (state.getValue(SOUTH))
        {
            i |= getMask(Direction.SOUTH);
        }
        if (state.getValue(WEST))
        {
            i |= getMask(Direction.WEST);
        }
        if (state.getValue(EAST))
        {
            i |= getMask(Direction.EAST);
        }
        return i;
    }

    public boolean isPathfindable(BlockState p_51023_, BlockGetter p_51024_, BlockPos p_51025_, PathComputationType p_51026_)
    {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_)
    {
        return this.renderShapes[this.getIndex(state)];
    }

//    @Override
//    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos neighborPos, boolean p_60514_)
//    {
//        if (!canSurvive(state, world, pos))
//        {
//            world.destroyBlock(pos, true);
//        };
//    }

    @Override
    public void entityInside(BlockState p_60495_, Level world, BlockPos pos, Entity entity)
    {
        entity.hurt(TerraReforged.THORNS, this.damage);
        world.destroyBlock(pos, true);
    }

    public boolean canAttach(BlockState state, boolean solidSide)
    {
        Block   block = state.getBlock();
        boolean flag1 = block instanceof TerraBlockPlantThorns;
        return !isExceptionForConnection(state) && (solidSide || flag1);
    }

    public Block getGrass()
    {
        if (this == TerraBlockRegistry.PLANT_THORN_PURPLE.get())
        {
            return TerraBlockRegistry.GRASS_CORRUPT.get();
        }
        else if (this == TerraBlockRegistry.PLANT_THORN_RED.get())
        {
            return TerraBlockRegistry.GRASS_CRIMSON.get();
        }
        else
        {
            return TerraBlockRegistry.GRASS_JUNGLE.get();
        }
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos)
    {
        return state.getBlock() == getGrass();
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
    {
        boolean thornNeighbor = false;
        for(Direction direction : Direction.values())
        {
            BlockState offsetState = world.getBlockState(pos.relative(direction));
            if (offsetState.getBlock() instanceof TerraBlockPlantThorns)
            {
                thornNeighbor = true;
            }
        }
        System.out.println(getLowestNeighbor(world, pos));
        int growth = state.getValue(GROWTH);
        if (growth != 0)
        {
            if (growth == 9)
            {
                return getLowestNeighbor(world, pos) != 1000;
            }
            return getLowestNeighbor(world, pos) < growth;
        }
        else
        {
            BlockState blockstate1 = world.getBlockState(pos.below());
            return blockstate1.canSustainPlant(world, pos, Direction.UP, this) && mayPlaceOn(blockstate1, world, pos.below());
        }
    }


    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos)
    {
        if (!state.canSurvive(world, pos))
        {
            world.scheduleTick(pos, this, 1);
        }
        return state.setValue(FACING_TO_PROPERTY_MAP.get(dir), this.canAttach(neighborState, neighborState.isFaceSturdy(world, neighborPos, dir.getOpposite())));
    }

    public int getProperGrowth(BlockGetter world, BlockPos pos)
    {
        int     lowNei  = 0;
        boolean checked = false;

        for (int dirIndex = 0; dirIndex < 6; dirIndex++)
        {
            BlockState targetState = world.getBlockState(pos.offset(Direction.from3DDataValue(dirIndex).getNormal()));
            if (dirIndex == 0 && targetState.getBlock() == this.getGrass())
            {
                return 0;
            }
            if (targetState.getBlock() instanceof TerraBlockPlantThorns)
            {
                if (checked)
                {
                    lowNei = Math.min(lowNei, targetState.getValue(GROWTH));
                }
                else
                {
                    lowNei = targetState.getValue(GROWTH);
                    checked = true;
                }
            }
        }
        return Math.min(lowNei + 1, 9);
    }

    public int getLowestNeighbor(BlockGetter world, BlockPos pos)
    {
        int     lowNei  = 0;
        boolean checked = false;
        for (int dirIndex = 0; dirIndex < 6; dirIndex++)
        {
            BlockState targetState = world.getBlockState(pos.offset(Direction.from3DDataValue(dirIndex).getNormal()));
            if (targetState.getBlock() instanceof TerraBlockPlantThorns)
            {
                if (checked)
                {
                    lowNei = Math.min(lowNei, targetState.getValue(GROWTH));
                }
                else
                {
                    lowNei = targetState.getValue(GROWTH);
                    checked = true;
                }
            }
        }
        return checked ? lowNei : 1000;
    }

    public int getNeighborCount(BlockGetter world, BlockPos pos)
    {
        int countNei = 0;
        for (int dirIndex = 0; dirIndex < 6; dirIndex++)
        {
            BlockState targetState = world.getBlockState(pos.offset(Direction.from3DDataValue(dirIndex).getNormal()));
            if (targetState.getBlock() instanceof TerraBlockPlantThorns)
            {
                countNei++;
            }
        }
        return countNei;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        Level world     = context.getLevel();
        BlockPos     blockpos  = context.getClickedPos();
        BlockPos     blockposN = blockpos.north();
        BlockPos     blockposE = blockpos.east();
        BlockPos     blockposS = blockpos.south();
        BlockPos     blockposW = blockpos.west();
        BlockPos     blockposU = blockpos.above();
        BlockPos     blockposD = blockpos.below();
        BlockState   stateN    = world.getBlockState(blockposN);
        BlockState   stateE    = world.getBlockState(blockposE);
        BlockState   stateS    = world.getBlockState(blockposS);
        BlockState   stateW    = world.getBlockState(blockposW);
        BlockState   stateU    = world.getBlockState(blockposU);
        BlockState   stateD    = world.getBlockState(blockposD);
        return super.getStateForPlacement(context)
                .setValue(NORTH, this.canAttach(stateN, stateN.isFaceSturdy(world, blockposN, Direction.SOUTH)))
                .setValue(EAST, this.canAttach(stateE, stateE.isFaceSturdy(world, blockposE, Direction.WEST)))
                .setValue(SOUTH, this.canAttach(stateS, stateS.isFaceSturdy(world, blockposS, Direction.NORTH)))
                .setValue(WEST, this.canAttach(stateW, stateW.isFaceSturdy(world, blockposW, Direction.EAST)))
                .setValue(UP, this.canAttach(stateU, stateU.isFaceSturdy(world, blockposU, Direction.DOWN)))
                .setValue(DOWN, this.canAttach(stateD, stateD.isFaceSturdy(world, blockposD, Direction.UP)))
                .setValue(GROWTH, this.getProperGrowth(world, blockpos));
    }

    public void tick(BlockState p_51138_, ServerLevel p_51139_, BlockPos p_51140_, Random p_51141_) {
        if (!p_51139_.isAreaLoaded(p_51140_, 1)) return; // Forge: prevent growing cactus from loading unloaded chunks with block update
        if (!p_51138_.canSurvive(p_51139_, p_51140_)) {
            p_51139_.destroyBlock(p_51140_, true);
        }

    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random)
    {
        int growth = state.getValue(GROWTH);
        if (growth < 9)
        {
            int dirIndex = random.nextInt(6);
            if (dirIndex == 2 || dirIndex == 3) return;
            BlockPos dirPos = pos.offset(Direction.from3DDataValue(dirIndex).getNormal());
            if (world.getBlockState(dirPos).getBlock() == Blocks.AIR && getNeighborCount(world, dirPos) < 2)
            {
                world.setBlockAndUpdate(dirPos, this.defaultBlockState().setValue(GROWTH, growth + 1));
            }
        }
        if (world.getBlockState(pos.below()).getBlock() == this.getGrass() && growth != 0)
        {
            world.setBlockAndUpdate(pos, state.setValue(GROWTH, 0).setValue(DOWN, true));
        }
//        if (getLowestNeighbor(world, pos) < growth - 1)
//        {
//            world.setBlockAndUpdate(pos, state.setValue(GROWTH, growth - 1));
//        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_51164_)
    {
        p_51164_.add(NORTH, SOUTH, EAST, WEST, UP, DOWN, GROWTH);
    }
}