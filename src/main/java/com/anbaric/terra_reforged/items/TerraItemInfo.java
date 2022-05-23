package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.InfoFunctionHandler;
import com.anbaric.terra_reforged.util.handlers.ObjectRarityHandler;
import com.mojang.math.Vector3f;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TerraItemInfo extends Item
{
    protected InfoFunctionHandler.Functions[] allFunctions;

    public TerraItemInfo(InfoFunctionHandler.Functions... functions)
    {
        super(new Properties().tab(TerraReforged.TERRA_TOOLS_TAB).stacksTo(1));
        allFunctions = functions;
    }

    private int getFlagIndex(InfoFunctionHandler.Functions[] functionArray)
    {
        int result = 0;
        for (InfoFunctionHandler.Functions function : functionArray)
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
            ItemStack infoItemStack = player.getItemInHand(hand);
            int numberOfFunctions = allFunctions.length;
            if (numberOfFunctions > 1)
            {
                CompoundTag compound = infoItemStack.getOrCreateTag();
                int activeIndex = compound.getInt("active_index");

                int functionArrayIndex = 0;
                for (InfoFunctionHandler.Functions functions : allFunctions)
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
        long[] emptyLongList = new long[1];
        int[] emptyIntList = new int[1];
        emptyLongList[0] = 0;
        emptyIntList[0] = 0;
        if (!compound.contains("function_index"))
        {
            compound.putInt("function_index", flagIndex);
        }
        if (!compound.contains("active_index"))
        {
            compound.putInt("active_index", allFunctions[0].ordinal());
        }
        if (!compound.contains(InfoFunctionHandler.Functions.DPS_METER.getTag() + "_time"))
        {
            compound.putLongArray(InfoFunctionHandler.Functions.DPS_METER.getTag() + "_time", emptyLongList);
        }
        if (!compound.contains(InfoFunctionHandler.Functions.DPS_METER.getTag() + "_damage"))
        {
            compound.putIntArray(InfoFunctionHandler.Functions.DPS_METER.getTag() + "_damage", emptyIntList);
        }
        if (!compound.contains(InfoFunctionHandler.Functions.SPEED_METER.getTag() + "_position"))
        {
            Vec3 pos = entity.position();
            compound.putString(InfoFunctionHandler.Functions.SPEED_METER.getTag() + "_position", pos.x() + ":" + pos.y + ";" + pos.z);
        }
        if (entity instanceof Player && !world.isClientSide())
        {
            Player player = (Player) entity;
            if (hasFunction(InfoFunctionHandler.Functions.RADAR))
            {
                AABB      rangeAABB = new AABB(entity.getOnPos().above()).inflate(24);
                List<Mob> mobList   = entity.level.getEntitiesOfClass(Mob.class, rangeAABB);
                compound.putInt(InfoFunctionHandler.Functions.RADAR.getTag(), mobList.size());
            }
            if (hasFunction(InfoFunctionHandler.Functions.MOB_FINDER))
            {
                //TODO Add rarity levels for monsters
            }
            if (hasFunction(InfoFunctionHandler.Functions.SPEED_METER) && player.tickCount % 20 == 0)
            {
                Vec3 pos = player.position();
                String posString = compound.getString(InfoFunctionHandler.Functions.SPEED_METER.getTag() + "_position");
                int xIndex = 0, yIndex = 0;
                for (int i = 0; i < posString.length(); i++)
                {
                    if (posString.charAt(i) == ':') { xIndex = i; }
                    if (posString.charAt(i) == ';') { yIndex = i; }
                }
                Vec3 prevPos = new Vec3(Double.parseDouble(posString.substring(0, xIndex)), Double.parseDouble(posString.substring(xIndex + 1, yIndex)), Double.parseDouble(posString.substring(yIndex + 1)));
                double distance = pos.distanceTo(prevPos);
//                Reference.print("" + pos);
//                Reference.print("" + prevPos);
//                Reference.print("" + distance);
//                Reference.print("---------------------");
                compound.putDouble(InfoFunctionHandler.Functions.SPEED_METER.getTag() + "_distance", distance);
                compound.putString(InfoFunctionHandler.Functions.SPEED_METER.getTag() + "_position", pos.x() + ":" + pos.y + ";" + pos.z);
            }
            if (hasFunction(InfoFunctionHandler.Functions.METAL_FINDER) && player.tickCount % 20 == 0)
            {
                BlockPos pos = player.getOnPos();
                LinkedList<Block> blockList = new LinkedList<>();
                for (BlockPos index : BlockPos.betweenClosed(pos.offset(-8, -8, -8), pos.offset(8, Math.min(8, world.getMaxBuildHeight() - pos.getY()), 8)))
                {
                    if (index.getY() > world.getMaxBuildHeight() || index.getY() < world.getMinBuildHeight()) { continue; }
                    if (!blockList.contains(world.getBlockState(index).getBlock()))
                    {
                        blockList.add(world.getBlockState(index).getBlock());
                    }
                }
                outerLoop:
                for (ObjectRarityHandler.MetalDetectorEnum teir : ObjectRarityHandler.MetalDetectorEnum.values())
                {
                    for (Block block : blockList)
                    {
                        if (teir.getQualifier().test(block))
                        {
                            compound.putString(InfoFunctionHandler.Functions.METAL_FINDER.getTag(), teir.getString());
                            break outerLoop;
                        }
                    }
                }
            }
        }
    }

    boolean hasFunction(InfoFunctionHandler.Functions function)
    {
        return Arrays.asList(allFunctions).contains(function);
    }

    
}
