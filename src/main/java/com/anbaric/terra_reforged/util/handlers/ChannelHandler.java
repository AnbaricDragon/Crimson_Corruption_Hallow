package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.packets.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ChannelHandler
{
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(Reference.MODID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void register()
    {
        int index = 0;
        INSTANCE.registerMessage(index++, CloudJumpPacket.class, CloudJumpPacket::toBytes, CloudJumpPacket::new, CloudJumpPacket::handle);
        INSTANCE.registerMessage(index++, BlizzardJumpPacket.class, BlizzardJumpPacket::toBytes, BlizzardJumpPacket::new, BlizzardJumpPacket::handle);
        INSTANCE.registerMessage(index++, SandstormJumpPacket.class, SandstormJumpPacket::toBytes, SandstormJumpPacket::new, SandstormJumpPacket::handle);
        INSTANCE.registerMessage(index++, TsunamiJumpPacket.class, TsunamiJumpPacket::toBytes, TsunamiJumpPacket::new, TsunamiJumpPacket::handle);
        INSTANCE.registerMessage(index++, FartJumpPacket.class, FartJumpPacket::toBytes, FartJumpPacket::new, FartJumpPacket::handle);
        INSTANCE.registerMessage(index++, WallJumpPacket.class, WallJumpPacket::toBytes, WallJumpPacket::new, WallJumpPacket::handle);
        INSTANCE.registerMessage(index++, RocketJumpPacket.class, RocketJumpPacket::toBytes, RocketJumpPacket::new, RocketJumpPacket::handle);
        INSTANCE.registerMessage(index++, CarpetJumpPacket.class, CarpetJumpPacket::toBytes, CarpetJumpPacket::new, CarpetJumpPacket::handle);
        //        INSTANCE.registerMessage(index++, TabiDashPacket.class, TabiDashPacket::toBytes, TabiDashPacket::new, TabiDashPacket::handle);
        //        INSTANCE.registerMessage(index++, ChangeBiomePacket.class, ChangeBiomePacket::encode, ChangeBiomePacket::decode, ChangeBiomePacket::handle);
        INSTANCE.messageBuilder(ManaUpdatePacket.class, index++, NetworkDirection.PLAY_TO_CLIENT).encoder(ManaUpdatePacket::toBytes).decoder(ManaUpdatePacket::read).consumer(ManaUpdatePacket::handle).add();
    }
}