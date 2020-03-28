package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.handlers.EnumHandler.EnumBiomeType;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class TerraBlockOre extends Block
{
    public static final EnumProperty<OreBiomes> BIOME = EnumProperty.create("biome", OreBiomes.class);

    public TerraBlockOre(Properties properties)
    {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(BIOME, OreBiomes.PURE));
    }

    protected int getExperience(Random random)
    {
        if (this == TerraBlockRegistry.ORE_COAL.get())
        {
            return MathHelper.nextInt(random, 0, 2);
        }
        else if (this == TerraBlockRegistry.ORE_DIAMOND.get())
        {
            return MathHelper.nextInt(random, 3, 7);
        }
        else if (this == TerraBlockRegistry.ORE_EMERALD.get())
        {
            return MathHelper.nextInt(random, 3, 7);
        }
        else if (this == TerraBlockRegistry.ORE_RUBY.get())
        {
            return MathHelper.nextInt(random, 3, 7);
        }
        else if (this == TerraBlockRegistry.ORE_SAPPHIRE.get())
        {
            return MathHelper.nextInt(random, 3, 7);
        }
        else if (this == TerraBlockRegistry.ORE_AMETHYST.get())
        {
            return MathHelper.nextInt(random, 3, 7);
        }
        else if (this == TerraBlockRegistry.ORE_LAPIS.get())
        {
            return MathHelper.nextInt(random, 2, 5);
        }
        else if (this == TerraBlockRegistry.ORE_TOPAZ.get())
        {
            return MathHelper.nextInt(random, 2, 5);
        }
        else
        {
            return this == TerraBlockRegistry.ORE_REDSTONE.get() ? 1 + random.nextInt(5) : 0;
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {

        return worldIn.setBlockState(pos, state.with(BIOME, OreBiomes.HALLOWED)) ? ActionResultType.SUCCESS : ActionResultType.FAIL;
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch)
    {
        return silktouch == 0 ? this.getExperience(RANDOM) : 0;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(BIOME);
    }

    public enum OreBiomes implements IStringSerializable
    {
        PURE("pure", EnumBiomeType.PURE),
        CORRUPT("corrupt", EnumBiomeType.CORRUPT),
        CRIMSON("crimson", EnumBiomeType.CRIMSON),
        HALLOWED("hallowed", EnumBiomeType.HALLOWED);

        private final String name;
        private final EnumBiomeType biome;

        OreBiomes(String name, EnumBiomeType biome)
        {
            this.name = name;
            this.biome = biome;
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
