package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.util.init.TerraBiomeRegistry;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.UUID;

public class TerraTestEvent
{
    @SubscribeEvent
    static void onTickWorld(TickEvent.WorldTickEvent event)
    {

    }
}
