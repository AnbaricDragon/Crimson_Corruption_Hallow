package com.anbaric.terra_reforged.items.accessories;

import com.anbaric.terra_reforged.TerraReforged;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.Arrays;
import java.util.List;

public class TerraItemInfo extends Item
{
    private Functions[] allFunctions;

    public TerraItemInfo(Functions... functions)
    {
        super(new Properties().tab(TerraReforged.TERRA_TOOLS_TAB).stacksTo(1));
        allFunctions = functions;
    }

    private int getFlagIndex(Functions[] functionArray)
    {
        int result = 0;
        for (Functions function : functionArray)
        {
            result += 1 << function.ordinal();
        }
        return result;
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level world, Player player)
    {
        CompoundTag compound = stack.getOrCreateTag();
        compound.putInt("function_index", getFlagIndex(allFunctions));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
        if (hand.equals(InteractionHand.OFF_HAND))
        {
            ItemStack infoItemStack     = player.getItemInHand(hand);
            int       numberOfFunctions = allFunctions.length;
            if (numberOfFunctions > 1)
            {
                CompoundTag compound    = infoItemStack.getOrCreateTag();
                int         activeIndex = compound.getInt("active_index");

                int functionArrayIndex = 0;
                for (Functions functions : allFunctions)
                {
                    if (activeIndex == functions.ordinal())
                    {
                        compound.putInt("active_index", allFunctions[(functionArrayIndex + 1) % numberOfFunctions].ordinal());
                        return InteractionResultHolder.success(infoItemStack);
                    }
                    functionArrayIndex++;
                }
            }
        }
        return super.use(world, player, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int p_41407_, boolean p_41408_)
    {
        CompoundTag compound  = stack.getOrCreateTag();
        int flagIndex = getFlagIndex(allFunctions);
        if (!compound.contains("function_index"))
        {
            compound.putInt("function_index", flagIndex);
        }
        if (!compound.contains("active_index"))
        {
            compound.putInt("active_index", allFunctions[0].ordinal());
        }
        if (entity.tickCount % 20 == 0 && entity instanceof Player)
        {
            Player player = (Player) entity;
            if (hasFunction(Functions.RADAR))
            {
                AABB      rangeAABB = new AABB(entity.getOnPos().above()).inflate(24);
                List<Mob> mobList   = entity.level.getEntitiesOfClass(Mob.class, rangeAABB);
                compound.putInt(Functions.RADAR.getTag(), mobList.size());
            }
            if (hasFunction(Functions.TALLY_COUNTER))
            {
                //                int mobKills = 0;
                //                if (!world.isClientSide())
                //                {
                //                    ServerPlayer serverPlayer = (ServerPlayer) player;
                //                    mobKills = serverPlayer.getStats().getValue(Stats.ENTITY_KILLED.);
                //                }
                //                compound.putInt(Functions.MOB_COUNTER.getTag(), Stats.MOB_KILLS);
            }
            if (hasFunction(Functions.MOB_FINDER))
            {
                //TODO Add rarity levels for monsters
            }
            if (hasFunction(Functions.DPS_METER))
            {

            }
            if (hasFunction(Functions.SPEED_METER))
            {

            }
            if (hasFunction(Functions.METAL_FINDER))
            {

            }
        }
    }

    boolean hasFunction(Functions function)
    {
        return Arrays.asList(allFunctions).contains(function);
    }

    public enum Functions
    {
        DEPTH_METER("depth_counter"),
        CLOCK("time_counter"),
        COMPASS("location_counter"),
        RADAR("radar_counter"),
        TALLY_COUNTER("kill_counter"),
        MOB_FINDER("rare_mob_name"),
        DPS_METER("dps_counter"),
        SPEED_METER("speed_counter"),
        METAL_FINDER("rare_metal_name"),
        FISH_FINDER("fish_name"),
        WEATHER("weather_name"),
        MOON_FINDER("moon_phase_name");

        private String tag;

        Functions(String tagPreset)
        {
            this.tag = tagPreset;
        }

        public String getTag()
        {
            return this.tag;
        }
    }
}
