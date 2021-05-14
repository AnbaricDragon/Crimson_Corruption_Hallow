package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.packets.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkHandler
{
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(Reference.MODID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void register()
    {
        int index = 0;
        INSTANCE.registerMessage(index++, CloudJumpPacket.class, CloudJumpPacket::encode, CloudJumpPacket::new, CloudJumpPacket::handle);
        INSTANCE.registerMessage(index++, BlizzardJumpPacket.class, BlizzardJumpPacket::encode, BlizzardJumpPacket::new, BlizzardJumpPacket::handle);
        INSTANCE.registerMessage(index++, SandstormJumpPacket.class, SandstormJumpPacket::encode, SandstormJumpPacket::new, SandstormJumpPacket::handle);
        INSTANCE.registerMessage(index++, TsunamiJumpPacket.class, TsunamiJumpPacket::encode, TsunamiJumpPacket::new, TsunamiJumpPacket::handle);
        INSTANCE.registerMessage(index++, FartJumpPacket.class, FartJumpPacket::encode, FartJumpPacket::new, FartJumpPacket::handle);
        INSTANCE.registerMessage(index++, WallJumpPacket.class, WallJumpPacket::encode, WallJumpPacket::new, WallJumpPacket::handle);
        INSTANCE.registerMessage(index++, RocketJumpPacket.class, RocketJumpPacket::encode, RocketJumpPacket::new, RocketJumpPacket::handle);
//        INSTANCE.registerMessage(index++, CorruptBiomePacket.class, CorruptBiomePacket::encode, CorruptBiomePacket::decode, CorruptBiomePacket::handle);
    }
}