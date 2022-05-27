package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.util.packets.BiomeUpdatePacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraftforge.network.PacketDistributor;

public class BiomeChangeHandler
{
    /**
     * Modifies the biome at a location by a biome's ResourceLocation
     *
     * @param level            The level of the biome
     * @param pos              The location of the biome
     * @param resourceLocation The biome's ResourceLocation to replace with
     */
    public static void setBiomeAtPos(Level level, BlockPos pos, ResourceLocation resourceLocation)
    {
        Biome biome = level.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).get(resourceLocation);
        if (biome == null)
        {
            return;
        }
        if (level.isClientSide)
        {
            return;
        }
        setBiomeAtPos(level, pos, biome);
        ChannelHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new BiomeUpdatePacket(pos, resourceLocation));
    }

    /**
     * Modifies the biome at a location by a biome's ResourceKey
     *
     * @param level    The level of the biome
     * @param pos      The location of the biome
     * @param biomeKey The biome's ResourceKey to replace with
     */
    public static void setBiomeKeyAtPos(Level level, BlockPos pos, ResourceKey<Biome> biomeKey)
    {
        Biome biome = level.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).get(biomeKey);
        if (biome == null)
        {
            return;
        }
        if (level.isClientSide)
        {
            return;
        }
        setBiomeAtPos(level, pos, biome);
        ChannelHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new BiomeUpdatePacket(pos, biome.getRegistryName()));
    }

    /**
     * Modifies the biome at a location by another biome
     *
     * @param level The level of the biome
     * @param pos   The location of the biome
     * @param biome The other biome to replace with
     */
    public static void setBiomeAtPos(Level level, BlockPos pos, Biome biome)
    {
        ChunkAccess chunkAccess = level.getChunk(pos);
        chunkAccess.getSection(chunkAccess.getSectionIndex(pos.getY())).getBiomes().getAndSetUnchecked(pos.getX() & 3, pos.getY() & 3, pos.getZ() & 3, Holder.direct(biome));
        chunkAccess.setUnsaved(true);
    }
}
