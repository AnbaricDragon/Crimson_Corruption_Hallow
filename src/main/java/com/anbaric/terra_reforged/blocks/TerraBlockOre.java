package com.anbaric.terra_reforged.blocks;


import com.anbaric.terra_reforged.util.handlers.EnumHandler;
import com.anbaric.terra_reforged.util.handlers.EnumHandler.*;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;

import java.util.Random;

public class TerraBlockOre extends Block
{
    public TerraBlockOre(Properties properties)
    {
        super(properties);
    }

    protected int getExperience(Random random)
    {
        if (this == TerraBlockRegistry.ORE_COAL_CORRUPT.get() || this == TerraBlockRegistry.ORE_COAL_CRIMSON.get() || this == TerraBlockRegistry.ORE_COAL_HALLOWED.get())
        {
            return MathHelper.nextInt(random, 0, 2);
        }
        else if (this == TerraBlockRegistry.ORE_DIAMOND_CORRUPT.get() || this == TerraBlockRegistry.ORE_DIAMOND_CRIMSON.get() || this == TerraBlockRegistry.ORE_DIAMOND_HALLOWED.get())
        {
            return MathHelper.nextInt(random, 3, 7);
        }
        else if (this == TerraBlockRegistry.ORE_EMERALD_CORRUPT.get() || this == TerraBlockRegistry.ORE_EMERALD_CRIMSON.get() || this == TerraBlockRegistry.ORE_EMERALD_HALLOWED.get())
        {
            return MathHelper.nextInt(random, 3, 7);
        }
        else if (this == TerraBlockRegistry.ORE_RUBY_CORRUPT.get() || this == TerraBlockRegistry.ORE_RUBY_CRIMSON.get() || this == TerraBlockRegistry.ORE_RUBY_HALLOWED.get())
        {
            return MathHelper.nextInt(random, 3, 7);
        }
        else if (this == TerraBlockRegistry.ORE_SAPPHIRE_CORRUPT.get() || this == TerraBlockRegistry.ORE_SAPPHIRE_CRIMSON.get() || this == TerraBlockRegistry.ORE_SAPPHIRE_HALLOWED.get())
        {
            return MathHelper.nextInt(random, 3, 7);
        }
        else if (this == TerraBlockRegistry.ORE_AMETHYST_CORRUPT.get() || this == TerraBlockRegistry.ORE_AMETHYST_CRIMSON.get() || this == TerraBlockRegistry.ORE_AMETHYST_HALLOWED.get())
        {
            return MathHelper.nextInt(random, 3, 7);
        }
        else if (this == TerraBlockRegistry.ORE_LAPIS_CORRUPT.get() || this == TerraBlockRegistry.ORE_LAPIS_CRIMSON.get() || this == TerraBlockRegistry.ORE_LAPIS_HALLOWED.get())
        {
            return MathHelper.nextInt(random, 2, 5);
        }
        else if (this == TerraBlockRegistry.ORE_TOPAZ_CORRUPT.get() || this == TerraBlockRegistry.ORE_TOPAZ_CRIMSON.get() || this == TerraBlockRegistry.ORE_TOPAZ_HALLOWED.get())
        {
            return MathHelper.nextInt(random, 2, 5);
        }
        else
        {
            return (this == TerraBlockRegistry.ORE_REDSTONE_CORRUPT.get() || this == TerraBlockRegistry.ORE_REDSTONE_CRIMSON.get() || this == TerraBlockRegistry.ORE_REDSTONE_HALLOWED.get()) ? 1 + random.nextInt(5) : 0;
        }
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
    {
        ItemStack result = new ItemStack(Item.getItemFromBlock(Blocks.AIR));

        for (EnumBiomeBlockType block : EnumBiomeBlockType.values())
        {

            if (block.pure == state.getBlock() || block.corrupt == state.getBlock() || block.crimson == state.getBlock() || block.hallowed == state.getBlock())
            {
                result = new ItemStack(Item.getItemFromBlock(block.pure));
            }
        }
        return result;
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch)
    {
        return silktouch == 0 ? this.getExperience(RANDOM) : 0;
    }
}
