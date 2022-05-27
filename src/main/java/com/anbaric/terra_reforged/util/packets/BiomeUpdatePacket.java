package com.anbaric.terra_reforged.util.packets;

import com.anbaric.terra_reforged.util.handlers.BiomeChangeHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BiomeUpdatePacket
{
    private final BlockPos pos;
    private final ResourceLocation biome;

    public BiomeUpdatePacket(BlockPos pos, ResourceLocation biome)
    {
        this.biome = biome;
        this.pos = pos;
    }

    public static BiomeUpdatePacket decode(FriendlyByteBuf buf)
    {
        return new BiomeUpdatePacket(buf.readBlockPos(), buf.readResourceLocation());
    }

    public void toBytes(FriendlyByteBuf buf)
    {
        buf.writeBlockPos(pos);
        buf.writeResourceLocation(biome);
    }

    public void handle(Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() -> {
            DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> new BiomeUpdate(pos, biome));
        });
        context.get().setPacketHandled(true);
    }

    static public class BiomeUpdate implements DistExecutor.SafeCallable
    {

        private final BlockPos pos;
        private final ResourceLocation biome;

        public BiomeUpdate(BlockPos pos, ResourceLocation biome)
        {
            this.pos = pos;
            this.biome = biome;
        }

        @Override
        public Object call()
        {
            ClientLevel clientLevel = Minecraft.getInstance().level;
            if (clientLevel == null)
            {
                return null;
            }
            BiomeChangeHandler.setBiomeAtPos(clientLevel, pos, clientLevel.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).getOptional(biome).orElseThrow());
            return null;
        }
    }
}
