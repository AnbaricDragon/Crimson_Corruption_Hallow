package com.anbaric.terra_reforged.blocks.potionplants;

import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TerraBlockPotionPlant extends BushBlock
{
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    public static VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.box(6.0D, 0.0D, 6.0D, 10.0D, 6.0D, 10.0D), Block.box(5.0D, 0.0D, 5.0D, 11.0D, 8.0D, 11.0D), Block.box(4.0D, 0.0D, 4.0D, 12.0D, 10.0D, 12.0D)};

    public Tag.Named<Block> tag;

    protected TerraBlockPotionPlant(BlockBehaviour.Properties builder, Tag.Named<Block> tag)
    {
        super(builder);
        this.tag = tag;
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }

    public boolean isInPlanter(BlockState state, LevelReader worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos.below()).is(TerraTagRegistry.GENERAL_PLANTERS);
    }

    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity)
    {
        if (entity instanceof Ravager && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(world, entity))
        {
            world.destroyBlock(pos, true, entity);
        }

        super.entityInside(state, world, pos, entity);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        state.add(AGE);
    }
}