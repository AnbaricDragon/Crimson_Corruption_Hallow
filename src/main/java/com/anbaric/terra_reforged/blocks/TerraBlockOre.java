package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.handlers.EnumHandler.EnumBiomeType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

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
        if (this == TerraBlocks.ORE_COAL)
        {
            return MathHelper.nextInt(random, 0, 2);
        }
        else if (this == TerraBlocks.ORE_DIAMOND)
        {
            return MathHelper.nextInt(random, 3, 7);
        }
        else if (this == TerraBlocks.ORE_EMERALD)
        {
            return MathHelper.nextInt(random, 3, 7);
        }
        else if (this == TerraBlocks.ORE_RUBY)
        {
            return MathHelper.nextInt(random, 3, 7);
        }
        else if (this == TerraBlocks.ORE_SAPPHIRE)
        {
            return MathHelper.nextInt(random, 3, 7);
        }
        else if (this == TerraBlocks.ORE_AMETHYST)
        {
            return MathHelper.nextInt(random, 3, 7);
        }
        else if (this == TerraBlocks.ORE_LAPIS)
        {
            return MathHelper.nextInt(random, 2, 5);
        }
        else if (this == TerraBlocks.ORE_TOPAZ)
        {
            return MathHelper.nextInt(random, 2, 5);
        }
        else
        {
            return this == TerraBlocks.ORE_REDSTONE ? 1 + random.nextInt(5) : 0;
        }
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch)
    {
        return silktouch == 0 ? this.getExperience(RANDOM) : 0;
    }

    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
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
