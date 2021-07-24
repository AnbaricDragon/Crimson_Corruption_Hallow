package com.anbaric.terra_reforged.util.packets;

import com.anbaric.terra_reforged.util.handlers.BiomeChangeHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ChangeBiomePacket
{
    private final BlockPos pos;
    private final ResourceLocation biome;

    public ChangeBiomePacket(BlockPos pos, ResourceLocation biome)
    {
        this.pos = pos;
        this.biome = biome;
    }

    public static ChangeBiomePacket decode(PacketBuffer buffer)
    {
        return new ChangeBiomePacket(buffer.readBlockPos(), buffer.readResourceLocation());
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeBlockPos(pos);
        buffer.writeResourceLocation(biome);
    }

    public void handle(Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() ->
        {
            ClientWorld world = Minecraft.getInstance().world;
            if (world == null) { return; }
            RegistryKey<Biome> biomeKey = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, biome);
            BiomeChangeHandler.setBiomeKeyAtPos(world, pos, biomeKey);
        });
        context.get().setPacketHandled(true);
    }
}
