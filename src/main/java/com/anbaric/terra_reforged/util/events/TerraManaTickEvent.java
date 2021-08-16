package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.player.TerraCapabilityPlayerMana;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraManaTickEvent
{
    private static int currentMana;
    private static double manaCount;
    private static int tickSlower;
    private static Vector3d playerPos;

    @SubscribeEvent
    static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity player = event.player;
        double maxMana = player.getAttributeValue(TerraAttributeRegistry.MANA_MAX.get());
        boolean hasManaRegenBuff = player.hasEffect(TerraEffectRegistry.MANA_REGEN.get());
        boolean hasManaRegenBand = CurioHandler.hasBauble(player, TerraItemRegistry.BAND_MANAREGEN.get());
        boolean isMoving = player.position() != playerPos;

        if (tickSlower == 5)
        {
            tickSlower = 0;
            playerPos = player.position();
        }
        player.getCapability(TerraCapabilityPlayerMana.PLAYER_MANA).ifPresent(cap ->
        {
            currentMana = cap.getCurrentMana();
            if (currentMana < maxMana && manaCount >= 120)
            {
                cap.setCurrentMana(currentMana + 1);
                manaCount -= hasManaRegenBuff ? 100 : 120;
            }
            else if (currentMana > maxMana)
            {
                cap.setCurrentMana((int) maxMana);
            }
        });
        //Underlying equation...
        //(((maxMana/7) + hasMRB + isMoving) * hasManaRegenBuff * 1.15)
        if (manaCount < 120)
        {
            manaCount += (int) (((maxMana/7) + (hasManaRegenBand ? 26 : 1) + (hasManaRegenBand ? maxMana / 2 : isMoving ? 0 : maxMana / 2)) * (hasManaRegenBuff ? 1 : (currentMana / maxMana * 0.8) + 0.2) * 1.15);
        }
        tickSlower++;
    }
}
