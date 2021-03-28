package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Bus.MOD, value = {Dist.CLIENT})
public class ColorHandler
{
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerBlockColorHandlers(final ColorHandlerEvent.Block event)
    {
        event.getBlockColors().register((blockstate, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefault(), TerraBlockRegistry.WALL_OAK_LEAF.get(), TerraBlockRegistry.WALL_JUNGLE_LEAF.get(), TerraBlockRegistry.WALL_ACACIA_LEAF.get(), TerraBlockRegistry.WALL_DARKOAK_LEAF.get());
        event.getBlockColors().register((blockstate, world, pos, tintIndex) -> FoliageColors.getSpruce(), TerraBlockRegistry.WALL_SPRUCE_LEAF.get());
        event.getBlockColors().register((blockstate, world, pos, tintIndex) -> FoliageColors.getBirch(), TerraBlockRegistry.WALL_BIRCH_LEAF.get());

    }


    /**
     * Register the {@link IItemColor} handlers
     */
    @SubscribeEvent
    public static void onItemColorsInit(ColorHandlerEvent.Item event)
    {
        final BlockColors blockColors = event.getBlockColors();
        final ItemColors  itemColors  = event.getItemColors();

        // Use the Block's colour handler for an ItemBlock
        final IItemColor itemBlockColourHandler = (stack, tintIndex) -> {
            final BlockState state = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
            return blockColors.getColor(state, null, null, tintIndex);
        };

        itemColors.register(itemBlockColourHandler, TerraBlockRegistry.WALL_OAK_LEAF.get());
        itemColors.register(itemBlockColourHandler, TerraBlockRegistry.WALL_SPRUCE_LEAF.get());
        itemColors.register(itemBlockColourHandler, TerraBlockRegistry.WALL_BIRCH_LEAF.get());
        itemColors.register(itemBlockColourHandler, TerraBlockRegistry.WALL_JUNGLE_LEAF.get());
        itemColors.register(itemBlockColourHandler, TerraBlockRegistry.WALL_DARKOAK_LEAF.get());
        itemColors.register(itemBlockColourHandler, TerraBlockRegistry.WALL_ACACIA_LEAF.get());
    }
}