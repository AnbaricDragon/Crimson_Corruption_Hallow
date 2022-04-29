package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.player_mana.TerraMana;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraManaTickEvent
{
    private static int currentMana;
    private static double manaCount;
    private static int tickSlower;
    private static Vec3 playerPos;

    @SubscribeEvent
    static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        if (event.phase == TickEvent.Phase.START && !event.player.getLevel().isClientSide())
        {
            Player player = event.player;
            if (player.isSpectator()) { return; }

            double maxMana = player.getAttributeValue(TerraAttributeRegistry.MANA_MAX.get());
            boolean hasManaRegenBuff = player.hasEffect(TerraEffectRegistry.MANA_REGEN.get());
            boolean hasManaRegenBand = CurioHandler.hasBauble(player, TerraItemRegistry.BAND_MANAREGEN.get());
            boolean isMoving = player.position() != playerPos;

            //if Player is in Creative mod, instant refill of mana
            if (player.isCreative()) { player.getCapability(TerraMana.TERRA_MANA_CAPABILITY).ifPresent(cap -> { cap.setCurrentMana((int) maxMana, true); }); return; }

            //Tick 5 times slower to check the Player's position. If it's changed over the five ticks then the player is moving
            if (tickSlower == 5) { tickSlower = 0; playerPos = player.position(); }

            player.getCapability(TerraMana.TERRA_MANA_CAPABILITY).ifPresent(cap ->
            {
                currentMana = cap.getCurrentMana();
                while (manaCount >= (hasManaRegenBuff ? 100 : 120))
                {
                    manaCount -= hasManaRegenBuff ? 100 : 120;
                    if (currentMana < maxMana)
                    {
                        cap.setCurrentMana(currentMana + 1, true);
                    }
                }
                if (currentMana > maxMana)
                {
                    cap.setCurrentMana((int) maxMana, true);
                }
            });
            //Underlying equation...
            //(((maxMana/7) + hasMRB + isMoving) * hasManaRegenBuff * 1.15)
            if (manaCount < 1000)
            {
                manaCount += (int) (((maxMana/7) + (hasManaRegenBand ? 26 : 1) + (hasManaRegenBand ? maxMana / 2 : isMoving ? 0 : maxMana / 2)) * (hasManaRegenBuff ? 1 : (currentMana / maxMana * 0.4) + 0.8) * 1.15);
            }
            tickSlower++;
        }
    }
}
