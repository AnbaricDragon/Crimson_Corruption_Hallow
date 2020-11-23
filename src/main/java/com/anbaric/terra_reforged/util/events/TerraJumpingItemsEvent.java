package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.capabilities.multijump.IMultiJump;
import com.anbaric.terra_reforged.util.capabilities.multijump.MultiJumpProvider;
import com.anbaric.terra_reforged.util.capabilities.multijump.TerraCapabilityMultiJump;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

import java.util.Arrays;
import java.util.List;

public class TerraJumpingItemsEvent
{
    private static List<Item> FALL_BREAKERS = Arrays.asList(TerraItemRegistry.HORSESHOE_LUCKY.get(), TerraItemRegistry.HORSESHOE_OBSIDIAN.get(), TerraItemRegistry.HORSESHOE_BALLOON_CLOUD.get(), TerraItemRegistry.HORSESHOE_BALLOON_BLIZZARD.get(), TerraItemRegistry.HORSESHOE_BALLOON_SANDSTORM.get(), TerraItemRegistry.HORSESHOE_BALLOON_SHARK.get(), TerraItemRegistry.HORSESHOE_BALLOON_HONEY.get(), TerraItemRegistry.HORSESHOE_BALLOON_FART.get());
    private static List<Item> CLOUD_JUMPERS = Arrays.asList(TerraItemRegistry.BOTTLE_CLOUD.get(), TerraItemRegistry.BALLOON_CLOUD.get(), TerraItemRegistry.BALLOON_BUNDLE.get(), TerraItemRegistry.HORSESHOE_BALLOON_CLOUD.get());
    private static List<Item> BLIZZARD_JUMPERS = Arrays.asList(TerraItemRegistry.BOTTLE_BLIZZARD.get(), TerraItemRegistry.BALLOON_BLIZZARD.get(), TerraItemRegistry.BALLOON_BUNDLE.get());
    private static List<Item> SANDSTORM_JUMPERS = Arrays.asList(TerraItemRegistry.BOTTLE_SANDSTORM.get(), TerraItemRegistry.BALLOON_SANDSTORM.get(), TerraItemRegistry.BALLOON_BUNDLE.get());
    private static List<Item> TSUNAMI_JUMPERS = Arrays.asList(TerraItemRegistry.BOTTLE_TSUNAMI.get(), TerraItemRegistry.BALLOON_SHARK.get());
    private static List<Item> FART_JUMPERS = Arrays.asList(TerraItemRegistry.BOTTLE_FART.get(), TerraItemRegistry.BALLOON_FART.get());

    @OnlyIn(Dist.CLIENT) private boolean canCloudJump;

    @OnlyIn(Dist.CLIENT) private boolean hasReleasedCloudKey;

    @SubscribeEvent
    static void onFall(LivingFallEvent event)
    {
        if (!(event.getEntityLiving() instanceof PlayerEntity))
        {
            return;
        }

        PlayerEntity player = ((PlayerEntity) event.getEntityLiving());

        for (int i = 0; i < player.inventory.mainInventory.size(); i++)
        {
            ItemStack targetStack = player.inventory.getStackInSlot(i);
            if (FALL_BREAKERS.contains(targetStack.getItem()))
            {
                event.setDistance(0.0F);
            }
        }
        player.getCapability(TerraCapabilityMultiJump.MULTI_JUMP_CAPABILITY).ifPresent(IMultiJump::resetJumps);
    }
    static int count = 0;

    @SubscribeEvent
    static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity player = event.player;

        if (count < 20)
        {
            count++;
        }
        else
        {
            count = 0;
            System.out.println("can see this");
        }

        player.getCapability(TerraCapabilityMultiJump.MULTI_JUMP_CAPABILITY).ifPresent(cap -> {
            boolean hasCloudItem = false, hasBlizzardItem = false, hasSandstormItem = false, hasTsunamiItem = false, hasFartItem = false;
            for (int i = 0; i < player.inventory.mainInventory.size(); i++)
            {
                ItemStack targetStack = player.inventory.getStackInSlot(i);

                if (CLOUD_JUMPERS.contains(targetStack.getItem()))
                {
                    hasCloudItem = true;
                }
                if (BLIZZARD_JUMPERS.contains(targetStack.getItem()))
                {
                    hasBlizzardItem = true;
                }
                if (SANDSTORM_JUMPERS.contains(targetStack.getItem()))
                {
                    hasSandstormItem = true;
                }
                if (TSUNAMI_JUMPERS.contains(targetStack.getItem()))
                {
                    hasTsunamiItem = true;
                }
                if (FART_JUMPERS.contains(targetStack.getItem()))
                {
                    hasFartItem = true;
                }
            }
            cap.setHasCloudItem(hasCloudItem);
            int oCount = 0;
            if (oCount < 20)
            {
                oCount++;
            }
            else
            {
                oCount = 0;
                System.out.println(hasCloudItem);
            }
        });
    }

    private static boolean allowJump(PlayerEntity player, boolean sneaking)
    {
        boolean performingAction = player.isPassenger();
        boolean insideLiquid     = player.isInWater() || player.isInLava();
        if (performingAction || insideLiquid)
        {
            return false;
        }

        ItemStack itemstack       = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
        boolean   fallFlyingReady = !player.isElytraFlying() && itemstack.getItem() == Items.ELYTRA && ElytraItem.isUsable(itemstack);

        if (fallFlyingReady && sneaking)
        {
            return false;
        }

        return true;
    }
}
