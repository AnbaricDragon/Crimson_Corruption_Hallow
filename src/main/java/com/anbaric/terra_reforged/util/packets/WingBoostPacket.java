package com.anbaric.terra_reforged.util.packets;

import com.anbaric.terra_reforged.items.ITerraWingItem;
import com.anbaric.terra_reforged.util.events.TerraDashEvent;
import com.anbaric.terra_reforged.util.events.TerraJumpEvent;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class WingBoostPacket
{
    public WingBoostPacket()
    {

    }

    public WingBoostPacket(FriendlyByteBuf buffer)
    {

    }

    public void toBytes(FriendlyByteBuf buffer)
    {

    }

    public static WingBoostPacket read(FriendlyByteBuf buf)
    {
        return new WingBoostPacket();
    }

    public void handle(Supplier<NetworkEvent.Context> context)
    {
        ServerPlayer player = context.get().getSender();
        ServerLevel level = context.get().getSender().getLevel();
        if (player != null && level != null && !level.isClientSide())
        {
            context.get().enqueueWork(() ->
            {
                ItemStack wingStack = CurioHandler.getStack(player, TerraTagRegistry.FLIGHT_GIVERS);
                if (wingStack.getItem() instanceof ITerraWingItem wingItem)
                {
                    wingItem.setCooldown(wingStack);
                }
                ItemStack fakeRocket = new ItemStack(Items.FIREWORK_ROCKET);
                fakeRocket.getOrCreateTag().putByte("Flight", (byte)1);
                FireworkRocketEntity fireworkrocketentity = new FireworkRocketEntity(level, fakeRocket, player);
                fireworkrocketentity.setSilent(true);
                level.addFreshEntity(fireworkrocketentity);
            });
        }
        context.get().setPacketHandled(true);
    }
}