package com.anbaric.terra_reforged.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class TerraBlockWall extends Block
{
    public static final EnumProperty<WallSide> NORTH = EnumProperty.create("north", WallSide.class);
    public static final EnumProperty<WallSide> EAST = EnumProperty.create("east", WallSide.class);
    public static final EnumProperty<WallSide> SOUTH = EnumProperty.create("south", WallSide.class);
    public static final EnumProperty<WallSide> WEST = EnumProperty.create("west", WallSide.class);

    public static final EnumProperty<WallColumn> UP = EnumProperty.create("up", WallColumn.class);

    private final VoxelShape[] renderShapes;
    private final VoxelShape[] collideShapes;

    public TerraBlockWall(Properties properties)
    {
        super(properties);
        this.renderShapes = this.makeShapes(14.0D);
        this.collideShapes = this.makeShapes(22.0D);
        this.setDefaultState(this.stateContainer.getBaseState().with(NORTH, WallSide.NONE).with(EAST, WallSide.NONE).with(SOUTH, WallSide.NONE).with(WEST, WallSide.NONE).with(UP, WallColumn.PILLAR));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        IBlockReader world = context.getWorld();
        BlockPos     pos   = context.getPos();

        WallSide northSide = this.getSide(world, pos, Direction.NORTH);
        WallSide eastSide = this.getSide(world, pos, Direction.EAST);
        WallSide southSide = this.getSide(world, pos, Direction.SOUTH);
        WallSide westSide = this.getSide(world, pos, Direction.WEST);

        WallColumn pillar = this.getTop(world, pos);

        return super.getStateForPlacement(context)
                .with(NORTH, northSide)
                .with(EAST, eastSide)
                .with(SOUTH, southSide)
                .with(WEST, westSide)
                .with(UP, pillar);
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld world, BlockPos pos, BlockPos facingPos)
    {
        WallSide northSide = facing == Direction.NORTH || facing == Direction.UP || facing == Direction.DOWN ? this.getSide(world, pos, Direction.NORTH) : stateIn.get(NORTH);
        WallSide eastSide = facing == Direction.EAST || facing == Direction.UP || facing == Direction.DOWN ? this.getSide(world, pos, Direction.EAST) : stateIn.get(EAST);
        WallSide southSide = facing == Direction.SOUTH || facing == Direction.UP || facing == Direction.DOWN ? this.getSide(world, pos, Direction.SOUTH) : stateIn.get(SOUTH);
        WallSide westSide = facing == Direction.WEST || facing == Direction.UP || facing == Direction.DOWN ? this.getSide(world, pos, Direction.WEST) : stateIn.get(WEST);
        WallColumn pillar = this.getTop(world, pos);;

        return stateIn.with(NORTH, northSide).with(EAST, eastSide).with(SOUTH, southSide).with(WEST, westSide).with(UP, pillar);
    }

    public WallSide getSide(IBlockReader world, BlockPos pos, Direction facing)
    {
        BlockState sideState    = world.getBlockState(pos.offset(facing));
        Boolean    sideIsWall   = sideState.getBlock().isIn(BlockTags.WALLS);
        Boolean    sideIsOpaque = sideState.isOpaqueCube(world, pos.offset(facing));
        Boolean    sideIsGate   = sideState.getBlock() instanceof FenceGateBlock && FenceGateBlock.isParallel(sideState, facing);

        BlockState topState    = world.getBlockState(pos.up());
        Boolean    topIsWall   = topState.getBlock().isIn(BlockTags.WALLS);
        Boolean    topIsOpaque = topState.isOpaqueCube(world, pos.up());

        BlockState diaState    = world.getBlockState(pos.offset(facing).up());
        Boolean    diaIsWall   = diaState.getBlock().isIn(BlockTags.WALLS);
        Boolean    diaIsOpaque = diaState.isOpaqueCube(world, pos.offset(facing).up());

        if (sideIsWall || sideIsGate)
        {
            if ((topIsOpaque || topIsWall) && (diaIsOpaque || diaIsWall))
            {
                return WallSide.UP;
            }
            return WallSide.SIDE;
        }
        if (sideIsOpaque)
        {
            if (topIsOpaque || (topIsWall && (diaIsOpaque || diaIsWall)))
            {
                return WallSide.UP;
            }
            return WallSide.SIDE;
        }
        return WallSide.NONE;
    }

    public WallColumn getTop(IBlockReader world, BlockPos pos)
    {
        boolean topIsWall = world.getBlockState(pos.up()).getBlock().isIn(BlockTags.WALLS);
        BlockState topState = world.getBlockState(pos.up());
        Block topBlock = topState.getBlock();

        boolean N = getSide(world, pos, Direction.NORTH) != WallSide.NONE;
        boolean E = getSide(world, pos, Direction.EAST) != WallSide.NONE;
        boolean S = getSide(world, pos, Direction.SOUTH) != WallSide.NONE;
        boolean W = getSide(world, pos, Direction.WEST) != WallSide.NONE;

        boolean NSFlag = N & !E & S & !W;
        boolean EWFlag = !N & E & !S & W;
        boolean crossFlag = NSFlag || EWFlag;
        boolean loneFlag = (!N & !E & !S & W) || (!N & !E & S & !W) || (!N & E & !S & !W) || (N & !E & !S & !W);

        if (topIsWall)
        {
            if (topBlock instanceof WallBlock)
            {
                return topState.get(WallBlock.UP) ? WallColumn.PILLAR : WallColumn.UP;
            }
            else
            {
                return topState.get(TerraBlockWall.UP) == WallColumn.PILLAR ? WallColumn.PILLAR : WallColumn.UP;
            }
        }
        else if (topState.isOpaqueCube(world, pos.up()))
        {
            boolean n = getSide(world, pos.up(), Direction.NORTH) != WallSide.NONE;
            boolean e = getSide(world, pos.up(), Direction.EAST) != WallSide.NONE;
            boolean s = getSide(world, pos.up(), Direction.SOUTH) != WallSide.NONE;
            boolean w = getSide(world, pos.up(), Direction.WEST) != WallSide.NONE;

            boolean nsFlag = n & !e & s & !w;
            boolean ewFlag = !n & e & !s & w;

            if ((nsFlag && NSFlag) || (ewFlag && EWFlag))
            {
                return WallColumn.UP;
            }
            else if (!(nsFlag && NSFlag) || !(ewFlag && EWFlag))
            {
                return WallColumn.PILLAR;
            }
            else return WallColumn.NONE;
        }
        else if (topState.getMaterial() == Material.MISCELLANEOUS)
        {
            if (topBlock instanceof ScaffoldingBlock)
            {
                return WallColumn.NONE;
            }
            else if (!topBlock.isIn(BlockTags.RAILS))
            {
                return WallColumn.PILLAR;
            }
            else
            {
                return WallColumn.UP;
            }
        }
        else
        {
            return topBlock == Blocks.AIR && crossFlag ? WallColumn.NONE : WallColumn.PILLAR;
        }
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(UP, NORTH, EAST, SOUTH, WEST);
    }

    protected VoxelShape[] makeShapes(Double height)
    {
        VoxelShape n = Block.makeCuboidShape(5.0D, 0.0D, 0.0D, 11.0D, height, 5.0D);
        VoxelShape e = Block.makeCuboidShape(11.0D, 0.0D, 5.0D, 16.0D, height, 11.0D);
        VoxelShape s = Block.makeCuboidShape(5.0D, 0.0D, 11.0D, 11.0D, height, 16.0D);
        VoxelShape w = Block.makeCuboidShape(0.0D, 0.0D, 5.0D, 5.0D, height, 11.0D);

        VoxelShape N = Block.makeCuboidShape(5.0D, 0.0D, 0.0D, 11.0D, height + 2.0D, 5.0D);
        VoxelShape E = Block.makeCuboidShape(11.0D, 0.0D, 5.0D, 16.0D, height + 2.0D, 11.0D);
        VoxelShape S = Block.makeCuboidShape(5.0D, 0.0D, 11.0D, 11.0D, height + 2.0D, 16.0D);
        VoxelShape W = Block.makeCuboidShape(0.0D, 0.0D, 5.0D, 5.0D, height + 2.0D, 11.0D);

        VoxelShape u = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, height + 2.0D, 11.0D);
        VoxelShape U = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, height + 2.0D, 12.0D);

        VoxelShape base = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, height, 11.0D);

        VoxelShape[] avoxelshape = new VoxelShape[]{VoxelShapes.empty(), n, N, e, VoxelShapes.or(e, n), VoxelShapes.or(e, N), E, VoxelShapes.or(E, n), VoxelShapes.or(E, N), s, VoxelShapes.or(s, n), VoxelShapes.or(s, N), VoxelShapes.or(s, e), VoxelShapes.or(s, e, n), VoxelShapes.or(s, e, N), VoxelShapes.or(s, E), VoxelShapes.or(s, E, n), VoxelShapes.or(s, E, N), S, VoxelShapes.or(S, n), VoxelShapes.or(S, N), VoxelShapes.or(S, e), VoxelShapes.or(S, e, n), VoxelShapes.or(S, e, N), VoxelShapes.or(S, E), VoxelShapes.or(S, E, n), VoxelShapes.or(S, E, N), w, VoxelShapes.or(w, n), VoxelShapes.or(w, N), VoxelShapes.or(w, e), VoxelShapes.or(w, e, n), VoxelShapes.or(w, e, N), VoxelShapes.or(w, E), VoxelShapes.or(w, E, n), VoxelShapes.or(w, E, N), VoxelShapes.or(w, s), VoxelShapes.or(w, s, n), VoxelShapes.or(w, s, N), VoxelShapes.or(w, s, e), VoxelShapes.or(w, s, e, n), VoxelShapes.or(w, s, e, N), VoxelShapes.or(w, s, E), VoxelShapes.or(w, s, E, n), VoxelShapes.or(w, s, E, N), VoxelShapes.or(w, S), VoxelShapes.or(w, S, n), VoxelShapes.or(w, S, N), VoxelShapes.or(w, S, e), VoxelShapes.or(w, S, e, n), VoxelShapes.or(w, S, e, N), VoxelShapes.or(w, S, E), VoxelShapes.or(w, S, E, n), VoxelShapes.or(w, S, E, N), W, VoxelShapes.or(W, n), VoxelShapes.or(W, N), VoxelShapes.or(W, e), VoxelShapes.or(W, e, n), VoxelShapes.or(W, e, N), VoxelShapes.or(W, E), VoxelShapes.or(W, E, n), VoxelShapes.or(W, E, N), VoxelShapes.or(W, s), VoxelShapes.or(W, s, n), VoxelShapes.or(W, s, N), VoxelShapes.or(W, s, e), VoxelShapes.or(W, s, e, n), VoxelShapes.or(W, s, e, N), VoxelShapes.or(W, s, E), VoxelShapes.or(W, s, E, n), VoxelShapes.or(W, s, E, N), VoxelShapes.or(W, S), VoxelShapes.or(W, S, n), VoxelShapes.or(W, S, N), VoxelShapes.or(W, S, e), VoxelShapes.or(W, S, e, n), VoxelShapes.or(W, S, e, N), VoxelShapes.or(W, S, E), VoxelShapes.or(W, S, E, n), VoxelShapes.or(W, S, E, N), u, VoxelShapes.or(u, n), VoxelShapes.or(u, N), VoxelShapes.or(u, e), VoxelShapes.or(u, e, n), VoxelShapes.or(u, e, N), VoxelShapes.or(u, E), VoxelShapes.or(u, E, n), VoxelShapes.or(u, E, N), VoxelShapes.or(u, s), VoxelShapes.or(u, s, n), VoxelShapes.or(u, s, N), VoxelShapes.or(u, s, e), VoxelShapes.or(u, s, e, n), VoxelShapes.or(u, s, e, N), VoxelShapes.or(u, s, E), VoxelShapes.or(u, s, E, n), VoxelShapes.or(u, s, E, N), VoxelShapes.or(u, S), VoxelShapes.or(u, S, n), VoxelShapes.or(u, S, N), VoxelShapes.or(u, S, e), VoxelShapes.or(u, S, e, n), VoxelShapes.or(u, S, e, N), VoxelShapes.or(u, S, E), VoxelShapes.or(u, S, E, n), VoxelShapes.or(u, S, E, N), VoxelShapes.or(u, w), VoxelShapes.or(u, w, n), VoxelShapes.or(u, w, N), VoxelShapes.or(u, w, e), VoxelShapes.or(u, w, e, n), VoxelShapes.or(u, w, e, N), VoxelShapes.or(u, w, E), VoxelShapes.or(u, w, E, n), VoxelShapes.or(u, w, E, N), VoxelShapes.or(u, w, s), VoxelShapes.or(u, w, s, n), VoxelShapes.or(u, w, s, N), VoxelShapes.or(u, w, s, e), VoxelShapes.or(u, w, s, e, n), VoxelShapes.or(u, w, s, e, N), VoxelShapes.or(u, w, s, E), VoxelShapes.or(u, w, s, E, n), VoxelShapes.or(u, w, s, E, N), VoxelShapes.or(u, w, S), VoxelShapes.or(u, w, S, n), VoxelShapes.or(u, w, S, N), VoxelShapes.or(u, w, S, e), VoxelShapes.or(u, w, S, e, n), VoxelShapes.or(u, w, S, e, N), VoxelShapes.or(u, w, S, E), VoxelShapes.or(u, w, S, E, n), VoxelShapes.or(u, w, S, E, N), VoxelShapes.or(u, W), VoxelShapes.or(u, W, n), VoxelShapes.or(u, W, N), VoxelShapes.or(u, W, e), VoxelShapes.or(u, W, e, n), VoxelShapes.or(u, W, e, N), VoxelShapes.or(u, W, E), VoxelShapes.or(u, W, E, n), VoxelShapes.or(u, W, E, N), VoxelShapes.or(u, W, s), VoxelShapes.or(u, W, s, n), VoxelShapes.or(u, W, s, N), VoxelShapes.or(u, W, s, e), VoxelShapes.or(u, W, s, e, n), VoxelShapes.or(u, W, s, e, N), VoxelShapes.or(u, W, s, E), VoxelShapes.or(u, W, s, E, n), VoxelShapes.or(u, W, s, E, N), VoxelShapes.or(u, W, S), VoxelShapes.or(u, W, S, n), VoxelShapes.or(u, W, S, N), VoxelShapes.or(u, W, S, e), VoxelShapes.or(u, W, S, e, n), VoxelShapes.or(u, W, S, e, N), VoxelShapes.or(u, W, S, E), VoxelShapes.or(u, W, S, E, n), VoxelShapes.or(u, W, S, E, N), U, VoxelShapes.or(U, n), VoxelShapes.or(U, N), VoxelShapes.or(U, e), VoxelShapes.or(U, e, n), VoxelShapes.or(U, e, N), VoxelShapes.or(U, E), VoxelShapes.or(U, E, n), VoxelShapes.or(U, E, N), VoxelShapes.or(U, s), VoxelShapes.or(U, s, n), VoxelShapes.or(U, s, N), VoxelShapes.or(U, s, e), VoxelShapes.or(U, s, e, n), VoxelShapes.or(U, s, e, N), VoxelShapes.or(U, s, E), VoxelShapes.or(U, s, E, n), VoxelShapes.or(U, s, E, N), VoxelShapes.or(U, S), VoxelShapes.or(U, S, n), VoxelShapes.or(U, S, N), VoxelShapes.or(U, S, e), VoxelShapes.or(U, S, e, n), VoxelShapes.or(U, S, e, N), VoxelShapes.or(U, S, E), VoxelShapes.or(U, S, E, n), VoxelShapes.or(U, S, E, N), VoxelShapes.or(U, w), VoxelShapes.or(U, w, n), VoxelShapes.or(U, w, N), VoxelShapes.or(U, w, e), VoxelShapes.or(U, w, e, n), VoxelShapes.or(U, w, e, N), VoxelShapes.or(U, w, E), VoxelShapes.or(U, w, E, n), VoxelShapes.or(U, w, E, N), VoxelShapes.or(U, w, s), VoxelShapes.or(U, w, s, n), VoxelShapes.or(U, w, s, N), VoxelShapes.or(U, w, s, e), VoxelShapes.or(U, w, s, e, n), VoxelShapes.or(U, w, s, e, N), VoxelShapes.or(U, w, s, E), VoxelShapes.or(U, w, s, E, n), VoxelShapes.or(U, w, s, E, N), VoxelShapes.or(U, w, S), VoxelShapes.or(U, w, S, n), VoxelShapes.or(U, w, S, N), VoxelShapes.or(U, w, S, e), VoxelShapes.or(U, w, S, e, n), VoxelShapes.or(U, w, S, e, N), VoxelShapes.or(U, w, S, E), VoxelShapes.or(U, w, S, E, n), VoxelShapes.or(U, w, S, E, N), VoxelShapes.or(U, W), VoxelShapes.or(U, W, n), VoxelShapes.or(U, W, N), VoxelShapes.or(U, W, e), VoxelShapes.or(U, W, e, n), VoxelShapes.or(U, W, e, N), VoxelShapes.or(U, W, E), VoxelShapes.or(U, W, E, n), VoxelShapes.or(U, W, E, N), VoxelShapes.or(U, W, s), VoxelShapes.or(U, W, s, n), VoxelShapes.or(U, W, s, N), VoxelShapes.or(U, W, s, e), VoxelShapes.or(U, W, s, e, n), VoxelShapes.or(U, W, s, e, N), VoxelShapes.or(U, W, s, E), VoxelShapes.or(U, W, s, E, n), VoxelShapes.or(U, W, s, E, N), VoxelShapes.or(U, W, S), VoxelShapes.or(U, W, S, n), VoxelShapes.or(U, W, S, N), VoxelShapes.or(U, W, S, e), VoxelShapes.or(U, W, S, e, n), VoxelShapes.or(U, W, S, e, N), VoxelShapes.or(U, W, S, E), VoxelShapes.or(U, W, S, E, n), VoxelShapes.or(U, W, S, E, N)};

        for(int i = 0; i < avoxelshape.length; ++i) {
            avoxelshape[i] = VoxelShapes.or(base, avoxelshape[i]);
        }

        return avoxelshape;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return this.renderShapes[this.getIndex(state)];
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return this.collideShapes[this.getIndex(state)];
    }

    protected int getIndex(BlockState state)
    {
        int i = 0;
        if (state.get(UP) == WallColumn.PILLAR)
        {
            i += 162;
        }
        else if (state.get(UP) == WallColumn.UP)
        {
            i += 81;
        }
        if (state.get(WEST) == WallSide.UP)
        {
            i += 54;
        }
        else if (state.get(WEST) == WallSide.SIDE)
        {
            i += 27;
        }
        if (state.get(SOUTH) == WallSide.UP)
        {
            i += 18;
        }
        else if (state.get(SOUTH) == WallSide.SIDE)
        {
            i += 9;
        }
        if (state.get(EAST) == WallSide.UP)
        {
            i += 6;
        }
        else if (state.get(EAST) == WallSide.SIDE)
        {
            i += 3;
        }
        if (state.get(NORTH) == WallSide.UP)
        {
            i += 2;
        }
        else if (state.get(NORTH) == WallSide.SIDE)
        {
            i += 1;
        }
        return i;
    }

    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type)
    {
        return false;
    }

    public enum WallSide implements IStringSerializable
    {
        UP("up"), SIDE("side"), NONE("none");

        private final String name;

        private WallSide(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return this.getName();
        }

        public String getName()
        {
            return this.name;
        }
    }

    public enum WallColumn implements IStringSerializable
    {
        PILLAR("pillar"), UP("up"), NONE("none");

        private final String name;

        private WallColumn(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return this.getName();
        }

        public String getName()
        {
            return this.name;
        }
    }
}
