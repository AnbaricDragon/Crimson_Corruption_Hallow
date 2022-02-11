package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class TerraBlockGlowingMushroom extends BushBlock
{

    public static final IntegerProperty STAGE = BlockStateProperties.AGE_1;
    protected static final VoxelShape MUSHROOM_SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);

    public TerraBlockGlowingMushroom(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, 0));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        return MUSHROOM_SHAPE;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
    {
        return (isValidPosition(state, world, pos) || super.canSurvive(state, world, pos);
    }

    public boolean isValidPosition(BlockState state, LevelReader world, BlockPos pos)
    {
        return world.getBlockState(pos.below()).is(TerraBlockRegistry.GRASS_MUSHROOM.get());
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random)
    {
        if (world.getMaxLocalRawBrightness(pos.above()) >= 9 && random.nextInt(7) == 0)
        {
            if (!world.isAreaLoaded(pos, 1))
            {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light
            }
            this.advanceShroom(world, pos, state, random);
        }
    }

    public void advanceShroom(ServerLevel world, BlockPos pos, BlockState state, Random random)
    {
        if (state.getValue(STAGE) == 0)
        {
            world.setBlock(pos, state.cycle(STAGE), 4);
        }
        else
        {
            if (!net.minecraftforge.event.ForgeEventFactory.saplingGrowTree(world, random, pos))
            {
                return;
            }
            this.treeGrower.growTree(world, world.getChunkSource().getGenerator(), pos, state, random);
        }
    }
}
