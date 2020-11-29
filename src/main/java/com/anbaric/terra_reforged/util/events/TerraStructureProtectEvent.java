package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Arrays;
import java.util.List;

public class TerraStructureProtectEvent
{
    private static List<Block> UNBREAKABLE = Arrays.asList(
            TerraBlockRegistry.DUNGEON_BLUE_BRICK.get(), TerraBlockRegistry.DUNGEON_BLUE_FLOOR_BRICK.get(), TerraBlockRegistry.DUNGEON_BLUE_FLOOR_SLAB.get(), TerraBlockRegistry.DUNGEON_BLUE_FLOOR_TILE.get(),
            TerraBlockRegistry.DUNGEON_BLUE_PILLAR.get(), TerraBlockRegistry.DUNGEON_BLUE_SLAB.get(), TerraBlockRegistry.DUNGEON_BLUE_WALL_BASE.get(), TerraBlockRegistry.DUNGEON_BLUE_WALL_BOT.get(),
            TerraBlockRegistry.DUNGEON_BLUE_WALL_MID.get(), TerraBlockRegistry.DUNGEON_BLUE_WALL_TOP.get(),
            TerraBlockRegistry.DUNGEON_GREEN_BRICK.get(), TerraBlockRegistry.DUNGEON_GREEN_FLOOR_BRICK.get(), TerraBlockRegistry.DUNGEON_GREEN_FLOOR_SLAB.get(), TerraBlockRegistry.DUNGEON_GREEN_FLOOR_TILE.get(),
            TerraBlockRegistry.DUNGEON_GREEN_PILLAR.get(), TerraBlockRegistry.DUNGEON_GREEN_SLAB.get(), TerraBlockRegistry.DUNGEON_GREEN_WALL_BASE.get(), TerraBlockRegistry.DUNGEON_GREEN_WALL_BOT.get(),
            TerraBlockRegistry.DUNGEON_GREEN_WALL_MID.get(), TerraBlockRegistry.DUNGEON_GREEN_WALL_TOP.get(),
            TerraBlockRegistry.DUNGEON_RED_BRICK.get(), TerraBlockRegistry.DUNGEON_RED_FLOOR_BRICK.get(), TerraBlockRegistry.DUNGEON_RED_FLOOR_SLAB.get(), TerraBlockRegistry.DUNGEON_RED_FLOOR_TILE.get(),
            TerraBlockRegistry.DUNGEON_RED_PILLAR.get(), TerraBlockRegistry.DUNGEON_RED_SLAB.get(), TerraBlockRegistry.DUNGEON_RED_WALL_BASE.get(), TerraBlockRegistry.DUNGEON_RED_WALL_BOT.get(),
            TerraBlockRegistry.DUNGEON_RED_WALL_MID.get(), TerraBlockRegistry.DUNGEON_RED_WALL_TOP.get(),

            TerraBlockRegistry.TEMPLE_BRICK.get(), TerraBlockRegistry.TEMPLE_BRICK_CRACKED.get(), TerraBlockRegistry.TEMPLE_BRICK_MOSSY.get());

    @SubscribeEvent
    static void onMineBlock(BlockEvent.BreakEvent event)
    {
        BlockState target = event.getState();
        boolean unbreakable = UNBREAKABLE.contains(target.getBlock());
        boolean canBreak = event.getPlayer().getHeldItemMainhand().canHarvestBlock(target);
        boolean isCreative = event.getPlayer().isCreative();

        if (unbreakable && !canBreak && !isCreative && event.isCancelable())
        {
            event.setCanceled(true);
        }
    }
}
