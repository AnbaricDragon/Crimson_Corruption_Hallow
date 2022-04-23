package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.Random;

public class TerraBlockChlorophyteOre extends Block
{
    public TerraBlockChlorophyteOre(Properties properties)
    {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, Random random)
    {
        if (random.nextFloat() < 0.95F)
        {
            return;
        }

        int maxOres = 10;

        for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-pos.getX() % 16, -pos.getY(), -pos.getZ() % 16), pos.offset(15 - (pos.getX() % 16), 256 - pos.getY(), 15 - (pos.getZ() % 16))))
        {
            if (world.getBlockState(blockpos).is(this))
            {
                --maxOres;
                if (maxOres <= 0)
                {
                    return;
                }
            }
        }

        BlockPos targetPos   = pos.offset(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
        Block    targetBlock = world.getBlockState(targetPos).getBlock();

        if (targetBlock == TerraBlockRegistry.SOIL_MUD.get())
        {
            world.setBlock(targetPos, this.defaultBlockState(), 3);
        }
    }
}