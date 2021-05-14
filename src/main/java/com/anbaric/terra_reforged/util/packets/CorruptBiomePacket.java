package com.anbaric.terra_reforged.util.packets;

import com.anbaric.terra_reforged.util.handlers.BiomeChangeHandler;
import com.anbaric.terra_reforged.util.handlers.BiomeHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CorruptBiomePacket
{
//    private final BlockPos pos;
//    private final ResourceLocation biome;
//
//    public CorruptBiomePacket(BlockPos pos, ResourceLocation biome)
//    {
//        this.pos = pos;
//        this.biome = biome;
//    }
//
//    public static CorruptBiomePacket decode(PacketBuffer buffer)
//    {
//        return new CorruptBiomePacket(buffer.readBlockPos(), buffer.readResourceLocation());
//    }
//
//    public void encode(PacketBuffer buffer)
//    {
//        buffer.writeBlockPos(pos);
//        buffer.writeResourceLocation(biome);
//    }
//
//    public void handle(Supplier<NetworkEvent.Context> context)
//    {
//        context.get().enqueueWork(() ->
//        {
//            ClientWorld world = Minecraft.getInstance().world;
//            if (world == null)
//            {
//                return;
//            }
//            System.out.println("Packet is being used");
//            BiomeChangeHandler.setBiome(world, pos, Biomes.DESERT);
//        });
//        context.get().setPacketHandled(true);
//    }
}
