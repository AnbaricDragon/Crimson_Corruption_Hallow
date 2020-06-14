package com.anbaric.terra_reforged.features.carvers;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

import java.util.BitSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

public class TerraJungleCarver extends TerraWorldCarver
{
    public static final BlockState JUNGLE_GRASS = TerraBlockRegistry.GRASS_JUNGLE.get().getDefaultState();
    public static final BlockState MUSHROOM_GRASS = TerraBlockRegistry.GRASS_MUSHROOM.get().getDefaultState();
    public static final BlockState MUD = TerraBlockRegistry.SOIL_MUD.get().getDefaultState();

    public TerraJungleCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> deserialize, int maxHeight)
    {
        super(deserialize, maxHeight); // The 256 is the maximum height that this carver can cave to
    }

    @Override
    protected boolean func_225556_a_(IChunk chunk, Function<BlockPos, Biome> p_225556_2_, BitSet p_225556_3_, Random p_225556_4_, BlockPos.Mutable pos, BlockPos.Mutable targetPos, BlockPos.Mutable p_225556_7_, int p_225556_8_, int p_225556_9_, int p_225556_10_, int x, int z, int p_225556_13_, int y, int p_225556_15_, AtomicBoolean p_225556_16_)
    {
        int i = p_225556_13_ + 3 | p_225556_15_ << 6 | y << 10;
        if (p_225556_3_.get(i))
        {
            return false;
        }
        else
        {
            p_225556_3_.set(i);
            pos.setPos(x, y, z);
            BlockState blockstate  = chunk.getBlockState(pos);
            BlockState blockstate1 = chunk.getBlockState(targetPos.setPos(pos).move(Direction.UP));
            if (blockstate == JUNGLE_GRASS || blockstate == MUSHROOM_GRASS)
            {
                p_225556_16_.set(true);
            }

            if (!this.canCarveBlock(blockstate, blockstate1))
            {
                return false;
            }
            else
            {
                if (y < 11)
                {
                    chunk.setBlockState(pos, LAVA.getBlockState(), false);
                }
                else
                {
                    chunk.setBlockState(pos, AIR, false);
                    if (p_225556_16_.get())
                    {
                        p_225556_7_.setPos(pos).move(Direction.DOWN);
                        if (chunk.getBlockState(p_225556_7_) == MUD)
                        {
                            chunk.setBlockState(p_225556_7_, p_225556_2_.apply(pos).getSurfaceBuilderConfig().getTop(), false);
                        }
                    }
                }

                return true;
            }
        }
    }
}