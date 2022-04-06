package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Bus.MOD, value = {Dist.CLIENT})
public class ColorHandler
{
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerBlockColorHandlers(final ColorHandlerEvent.Block event)
    {
        BlockColors blockColors = event.getBlockColors();

        //        blockColors.register((blockstate, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColors.getDefault(), TerraBlockRegistry.WALL_OAK_LEAF.get(), TerraBlockRegistry.WALL_JUNGLE_LEAF.get(), TerraBlockRegistry.WALL_ACACIA_LEAF.get(), TerraBlockRegistry.WALL_DARKOAK_LEAF.get());
        //        blockColors.register((blockstate, world, pos, tintIndex) -> FoliageColor.getEvergreenColor(), TerraBlockRegistry.WALL_SPRUCE_LEAF.get());
        //        blockColors.register((blockstate, world, pos, tintIndex) -> FoliageColor.getBirchColor(), TerraBlockRegistry.WALL_BIRCH_LEAF.get());
        blockColors.register((blockstate, world, pos, tintIndex) ->
        {
//            int x = Math.abs(pos.getX());
//            int z = Math.abs(pos.getZ());
//            int r = (int) (x * 13) % 511 > ((z%255)+1) ? (x * 13) - 255 : (x * 13);
//            int g = (r + 83) % 255;
//            int b = (g + 83) % 255;
//            return r << 16 | g << 8 | b;

            return world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0.5D, 1.0D);
        },  TerraBlockRegistry.GRASS_JUNGLE.get());
    }

    public static int float_to_int(float value) {
        return ByteBuffer.allocateDirect(Float.BYTES).order(ByteOrder.nativeOrder()).putFloat(0, value).getInt(0);
    }

    /**
     * Register the {@link IItemColor} handlers
     */
    @SubscribeEvent
    public static void onItemColorsInit(ColorHandlerEvent.Item event)
    {
        final BlockColors blockColors = event.getBlockColors();
        final ItemColors  itemColors  = event.getItemColors();


        itemColors.register((stack, tintIndex) -> {
            BlockState blockstate = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
            return blockColors.getColor(blockstate, (BlockAndTintGetter)null, (BlockPos)null, tintIndex);
        }, TerraBlockRegistry.GRASS_JUNGLE.get());
//        itemColors.register(color, TerraBlockRegistry.WALL_OAK_LEAF.get());
//        itemColors.register(color, TerraBlockRegistry.WALL_SPRUCE_LEAF.get());
//        itemColors.register(color, TerraBlockRegistry.WALL_BIRCH_LEAF.get());
//        itemColors.register(color, TerraBlockRegistry.WALL_JUNGLE_LEAF.get());
//        itemColors.register(color, TerraBlockRegistry.WALL_DARKOAK_LEAF.get());
//        itemColors.register(color, TerraBlockRegistry.WALL_ACACIA_LEAF.get());
    }
}