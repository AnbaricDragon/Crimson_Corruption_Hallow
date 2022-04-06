package com.anbaric.terra_reforged.blocks.potionplants;

import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class TerraBlockPotionPlant extends BushBlock
{
    public static final int MAX_AGE = 2;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    public static VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.box(6.0D, 0.0D, 6.0D, 10.0D, 6.0D, 10.0D), Block.box(5.0D, 0.0D, 5.0D, 11.0D, 8.0D, 11.0D), Block.box(4.0D, 0.0D, 4.0D, 12.0D, 10.0D, 12.0D)};
    protected TagKey<Block> tagKey;

    public TerraBlockPotionPlant(Properties properties, TagKey<Block> tag)
    {
        super(properties);
        this.tagKey = tag;
        this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), 0));
    }

    public VoxelShape getShape(BlockState p_52297_, BlockGetter p_52298_, BlockPos p_52299_, CollisionContext p_52300_)
    {
        return SHAPE_BY_AGE[p_52297_.getValue(this.getAgeProperty())];
    }

    public BlockState getStateForAge(int age)
    {
        return this.defaultBlockState().setValue(this.getAgeProperty(), age);
    }

    public boolean isMaxAge(BlockState p_52308_)
    {
        return p_52308_.getValue(this.getAgeProperty()) >= this.getMaxAge();
    }

    public int getMaxAge()
    {
        return MAX_AGE;
    }

    public IntegerProperty getAgeProperty()
    {
        return AGE;
    }

    protected int getAge(BlockState p_52306_)
    {
        return p_52306_.getValue(this.getAgeProperty());
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos)
    {
        return state.is(tagKey);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
    {
        BlockPos blockpos = pos.below();
        if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        {
            this.mayPlaceOn(world.getBlockState(blockpos), world, blockpos);
        }
        return this.mayPlaceOn(world.getBlockState(blockpos), world, blockpos);
    }

    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity)
    {
        if (entity instanceof Ravager && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(world, entity))
        {
            world.destroyBlock(pos, true, entity);
        }

        super.entityInside(state, world, pos, entity);
    }

    protected ItemLike getBaseSeedId()
    {
        //TODO Add potion plant seeds
        return Items.WHEAT_SEEDS;
    }

    public ItemStack getCloneItemStack(BlockGetter p_52254_, BlockPos p_52255_, BlockState p_52256_)
    {
        return new ItemStack(this.getBaseSeedId());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_52286_)
    {
        p_52286_.add(AGE);
    }

}
