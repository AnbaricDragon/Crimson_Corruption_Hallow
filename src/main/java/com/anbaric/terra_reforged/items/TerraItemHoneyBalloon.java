package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.handlers.BeeHandler;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import javax.annotation.Nonnull;
import java.util.List;

public class TerraItemHoneyBalloon extends TerraItemAccessory
{
    public TerraItemHoneyBalloon()
    {
        super();
        MinecraftForge.EVENT_BUS.addListener(this::spawnBees);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag unused)
    {
        return CurioHandler.createProvider(new ICurio()
        {
            @Override
            public ItemStack getStack()
            {
                return stack;
            }

            @Override
            public List<Component> getSlotsTooltip(List<Component> tooltips)
            {
                List<Component> toolTip = tooltips;
                toolTip.add(new TranslatableComponent(""));
                toolTip.add(new TranslatableComponent("curios.modifiers.curio").withStyle(ChatFormatting.GOLD));
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.entity_spawn_bee").withStyle(ChatFormatting.BLUE));
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.jump_height_50").withStyle(ChatFormatting.BLUE));
                return toolTip;
            }

            @Nonnull
            @Override
            public DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit)
            {
                return DropRule.DEFAULT;
            }

            @Nonnull
            @Override
            public SoundInfo getEquipSound(SlotContext slotContext)
            {
                return new SoundInfo(SoundEvents.ARMOR_EQUIP_GOLD, 1.0f, 1.0f);
            }

            @Override
            public boolean canEquipFromUse(SlotContext slot)
            {
                return true;
            }
        });
    }

    public void spawnBees(LivingDamageEvent event)
    {
        Player player = event.getEntityLiving() instanceof Player ? (Player) event.getEntityLiving() : null;
        if (player == null) { return; }
        ServerLevel world = (ServerLevel) event.getEntity().getLevel();

        float aggroDist = event.getSource().getDirectEntity() instanceof LivingEntity ? event.getSource().getDirectEntity().distanceTo(player) : 10F;

        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem() == this && !player.getCooldowns().isOnCooldown(stack.getItem()), player).ifPresent(found ->
        {
            CuriosApi.getCuriosHelper().getCuriosHandler(player).map(ICuriosItemHandler::getCurios).map(map -> map.get("curio")).map(ICurioStacksHandler::getStacks).map(dynamicStackHandler ->
            {
                for (int i = 0; i < dynamicStackHandler.getSlots(); i++)
                {
                    ItemStack stack = dynamicStackHandler.getStackInSlot(i);
                    if (ForgeRegistries.ITEMS.tags().getTag(TerraTagRegistry.BEE_SPAWNERS).contains(stack.getItem()))
                    {
                        player.getCooldowns().addCooldown(this, 100);
                    }
                }
                return null;
            });
            player.addEffect(new MobEffectInstance(TerraEffectRegistry.HONEY.get(), 100));
            BeeHandler.spawnAngryBees(world, player.getOnPos().above(), aggroDist);
        });
    }
}
