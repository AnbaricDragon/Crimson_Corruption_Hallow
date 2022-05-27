package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.util.handlers.ChannelHandler;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import com.anbaric.terra_reforged.util.packets.WingBoostPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraWingBoostEvent
{
    @SubscribeEvent
    void onPlayerPressesKey(InputEvent.KeyInputEvent event)
    {
        if ((Minecraft.getInstance().screen instanceof ChatScreen) || (Minecraft.getInstance().screen != null))
        {
            return;
        }
        Player player = Minecraft.getInstance().player;
        LevelAccessor world  = Minecraft.getInstance().level;
        if (player == null || world == null)
        {
            return;
        }
        if (!CurioHandler.hasBauble(player, TerraTagRegistry.FLIGHT_GIVERS))
        {
            return;
        }
        int jumpKey = Minecraft.getInstance().options.keyJump.getKey().getValue();
        if (event.getKey() == jumpKey && player.isFallFlying())
        {
            ItemStack itemstack = CurioHandler.getStack(player, TerraTagRegistry.FLIGHT_GIVERS);
            CompoundTag compound = itemstack.getOrCreateTag();
            int cooldown = compound.getInt("boostCooldown");
            if (cooldown == 0)
            {
                ChannelHandler.INSTANCE.sendToServer(new WingBoostPacket());
            }
        }
    }
}
