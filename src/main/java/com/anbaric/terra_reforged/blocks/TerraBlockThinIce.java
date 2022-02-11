package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class TerraBlockThinIce extends Block
{
    public TerraBlockThinIce(Properties properties)
    {
        super(properties);
    }

    public void destroyNotIce(Level world, BlockPos pos)
    {
        int i;
        for (i = 0; world.getBlockState(pos.below(i)).getBlock() == TerraBlockRegistry.ICE_THIN.get(); i++)
        {
            world.playSound(null, pos.below(i), SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
            world.setBlockAndUpdate(pos.below(i), Blocks.AIR.defaultBlockState());
        }
    }

    @Override
    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, float fallDistance)
    {
        Vec3 entityPos = entity.position();

        double x    = entityPos.x;
        double z    = entityPos.z;
        double xi   = Math.floor(entityPos.x);
        double zi   = Math.floor(entityPos.z);
        double dotX = Math.abs((float) (x - xi));
        double dotZ = Math.abs((float) (z - zi));

        if (entity instanceof Player)
        {
            //TODO CURIOS
            //            if (CurioHandler.hasBauble((PlayerEntity) entityIn, TerraItemRegistry.BOOTS_FROSTSPARK.get(), TerraItemRegistry.BOOTS_ICE.get()))
            //            {
            //                return;
            //            }
            if (fallDistance >= 0.25)
            {
                destroyNotIce(world, pos);

                if (dotZ <= 0.300)
                {
                    destroyNotIce(world, pos.north());
                    if (dotX <= 0.300)
                    {
                        destroyNotIce(world, pos.offset(-1, 0, -1));
                    }
                }
                if (dotX >= 0.700)
                {
                    destroyNotIce(world, pos.east());
                    if (dotZ <= 0.300)
                    {
                        destroyNotIce(world, pos.offset(1, 0, -1));
                    }
                }
                if (dotZ >= 0.700)
                {
                    destroyNotIce(world, pos.south());
                    if (dotX >= 0.700)
                    {
                        destroyNotIce(world, pos.offset(1, 0, 1));
                    }
                }
                if (dotX <= 0.300)
                {
                    destroyNotIce(world, pos.west());
                    if (dotZ >= 0.700)
                    {
                        destroyNotIce(world, pos.offset(-1, 0, 1));
                    }
                }
            }
        }
        super.fallOn(world, state, pos, entity, fallDistance);
    }

    public boolean skipRendering(BlockState selfState, BlockState otherState, Direction facing)
    {
        return otherState.is(this) || super.skipRendering(selfState, otherState, facing);
    }
}
