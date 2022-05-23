package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.capabilities.player_mana.TerraMana;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.handlers.InfoFunctionHandler;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraPlayerTickEvent
{
    private static int currentMana;
    private static double manaCount;
    private static int tickSlower;
    private static Vec3 playerPos;

    @SubscribeEvent
    static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        if (!event.player.getLevel().isClientSide())
        {
            if (event.phase == TickEvent.Phase.START)
            {
                Player player = event.player;
                if (player.isSpectator())
                {
                    return;
                }

                double  maxMana          = player.getAttributeValue(TerraAttributeRegistry.MANA_MAX.get());
                boolean hasManaRegenBuff = player.hasEffect(TerraEffectRegistry.MANA_REGEN.get());
                boolean hasManaRegenBand = CurioHandler.hasBauble(player, TerraItemRegistry.BAND_MANAREGEN.get());
                boolean isMoving         = player.position() != playerPos;

                //if Player is in Creative mod, instant refill of mana
                if (player.isCreative())
                {
                    player.getCapability(TerraMana.TERRA_MANA_CAPABILITY).ifPresent(cap -> {
                        cap.setCurrentMana((int) maxMana, true);
                    });
                    return;
                }

                //Tick 5 times slower to check the Player's position. If it's changed over the five ticks then the player is moving
                if (tickSlower == 5)
                {
                    tickSlower = 0;
                    playerPos = player.position();
                }

                player.getCapability(TerraMana.TERRA_MANA_CAPABILITY).ifPresent(cap -> {
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
                    manaCount += (int) (((maxMana / 7) + (hasManaRegenBand ? 26 : 1) + (hasManaRegenBand ? maxMana / 2 : isMoving ? 0 : maxMana / 2)) * (hasManaRegenBuff ? 1 : (currentMana / maxMana * 0.4) + 0.8) * 1.15);
                }
                tickSlower++;
            }

            ItemStack offHand = event.player.getOffhandItem();
            if (offHand.getItem() == Items.CLOCK && offHand.getOrCreateTag().isEmpty())
            {
                offHand.getOrCreateTag().putInt("function_index", 1 << InfoFunctionHandler.Functions.CLOCK.ordinal());
                offHand.getOrCreateTag().putInt("active_index", InfoFunctionHandler.Functions.CLOCK.ordinal());
            }
            else if (offHand.getItem() == Items.COMPASS && offHand.getOrCreateTag().isEmpty())
            {
                offHand.getOrCreateTag().putInt("function_index", 1 << InfoFunctionHandler.Functions.COMPASS.ordinal());
                offHand.getOrCreateTag().putInt("active_index", InfoFunctionHandler.Functions.COMPASS.ordinal());
            }
            //        else if (offHand.is(TerraTagRegistry.DPS_TELLERS))
            //        {
            //            int[] timeArray
            //        }
            if (offHand.is(TerraTagRegistry.DPS_TELLERS))
            {
                CompoundTag compound = offHand.getOrCreateTag();
                long[] storedTimes = compound.getLongArray(InfoFunctionHandler.Functions.DPS_METER.getTag() + "_time");
                int[] storedDamages = compound.getIntArray(InfoFunctionHandler.Functions.DPS_METER.getTag() + "_damage");
                long[] middleTimes = storedTimes;
                int[] middleDamages = storedDamages;
                int newArrayLength = storedTimes.length;
                for (int i = 0; i < storedTimes.length; i++)
                {
                    if (storedTimes[i] == 0)
                    {
                        newArrayLength--;
                    }
                    else if (event.player.level.getGameTime() - storedTimes[i] > 100)
                    {
                        middleTimes[i] = 0;
                        middleDamages[i] = 0;
                        newArrayLength--;
                    }
                }
                long[] newTimes = new long[newArrayLength];
                int[] newDamages = new int[newArrayLength];
                int newArrayIndex = 0;
                for (int i = 0; i < storedTimes.length; i++)
                {
                    if (event.player.level.getGameTime() - storedTimes[i] < 100)
                    {
                        newTimes[newArrayIndex] = storedTimes[i];
                        newDamages[newArrayIndex] = storedDamages[i];
                        newArrayIndex++;
                    }
                }
                compound.putLongArray(InfoFunctionHandler.Functions.DPS_METER.getTag() + "_time", newTimes);
                compound.putIntArray(InfoFunctionHandler.Functions.DPS_METER.getTag() + "_damage", newDamages);
            }
        }
    }
}
