package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.packets.ManaUpdatePacket;
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
//        INSTANCE.registerMessage(index++, CloudJumpPacket.class, CloudJumpPacket::encode, CloudJumpPacket::new, CloudJumpPacket::handle);
//        INSTANCE.registerMessage(index++, BlizzardJumpPacket.class, BlizzardJumpPacket::encode, BlizzardJumpPacket::new, BlizzardJumpPacket::handle);
//        INSTANCE.registerMessage(index++, SandstormJumpPacket.class, SandstormJumpPacket::encode, SandstormJumpPacket::new, SandstormJumpPacket::handle);
//        INSTANCE.registerMessage(index++, TsunamiJumpPacket.class, TsunamiJumpPacket::encode, TsunamiJumpPacket::new, TsunamiJumpPacket::handle);
//        INSTANCE.registerMessage(index++, FartJumpPacket.class, FartJumpPacket::encode, FartJumpPacket::new, FartJumpPacket::handle);
//        INSTANCE.registerMessage(index++, WallJumpPacket.class, WallJumpPacket::encode, WallJumpPacket::new, WallJumpPacket::handle);
//        INSTANCE.registerMessage(index++, RocketJumpPacket.class, RocketJumpPacket::encode, RocketJumpPacket::new, RocketJumpPacket::handle);
//        INSTANCE.registerMessage(index++, CarpetJumpPacket.class, CarpetJumpPacket::encode, CarpetJumpPacket::decode, CarpetJumpPacket::handle);
//        INSTANCE.registerMessage(index++, TabiDashPacket.class, TabiDashPacket::encode, TabiDashPacket::new, TabiDashPacket::handle);
//        INSTANCE.registerMessage(index++, ChangeBiomePacket.class, ChangeBiomePacket::encode, ChangeBiomePacket::decode, ChangeBiomePacket::handle);
        INSTANCE.messageBuilder(ManaUpdatePacket.class, index++, NetworkDirection.PLAY_TO_CLIENT).encoder(ManaUpdatePacket::toBytes).decoder(ManaUpdatePacket::read).consumer(ManaUpdatePacket::handle).add();
    }
//    private static SimpleChannel INSTANCE;
//
//    private static int packetID = 0;
//    private static int getID() { return packetID++; }
//
//    public static void register() {
//        SimpleChannel net = NetworkRegistry.ChannelBuilder
//                .named(Reference.path("messages"))
//                .networkProtocolVersion(() -> "1.0")
//                .clientAcceptedVersions(s -> true)
//                .serverAcceptedVersions(s -> true)
//                .simpleChannel();
//
//        INSTANCE = net;
//
//        net.messageBuilder(ServerManaUpdatePacket.class, getID(), NetworkDirection.PLAY_TO_SERVER)
//                .decoder(ServerManaUpdatePacket::new)
//                .encoder(ServerManaUpdatePacket::toBytes)
//                .consumer(ServerManaUpdatePacket::handle)
//                .add();
//
//        net.messageBuilder(ManaUpdatePacket.class, getID(), NetworkDirection.PLAY_TO_CLIENT)
//                .decoder(ManaUpdatePacket::new)
//                .encoder(ManaUpdatePacket::toBytes)
//                .consumer(ManaUpdatePacket::handle)
//                .add();
//    }
//
//    public static <MSG> void sendToServer(MSG message) {
//        INSTANCE.sendToServer(message);
//    }
//
//    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
//        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
//    }

}
