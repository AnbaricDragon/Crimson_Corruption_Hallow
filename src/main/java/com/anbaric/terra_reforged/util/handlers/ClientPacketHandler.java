package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.util.packets.ManaUpdatePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class ClientPacketHandler
{
    public static void handleManaStatus(ManaUpdatePacket packet)
    {
        Entity entity = Minecraft.getInstance().world.getEntityByID(packet.entityId);
        if (entity == null)
        {
            return;
        }
        if (!(entity instanceof PlayerEntity))
        {
            throw new IllegalStateException("Entity is not a player");
        }

        PlayerManaHandler.getManaHolder((PlayerEntity) entity).deserializeNBT(packet.tag);
    }
}
