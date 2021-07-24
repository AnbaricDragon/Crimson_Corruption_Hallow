package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.handlers.BeeHandler;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import net.minecraft.item.Item.Properties;

import java.util.List;

public class TerraItemSweetheartNecklace extends TerraItemAccessory
{
    public TerraItemSweetheartNecklace()
    {
        super();
        MinecraftForge.EVENT_BUS.addListener(this::beeInPanic);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.format("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "Spawns Bees When Damaged By Enemies"));
        tooltip.add(new StringTextComponent("\u00A79" + "+20% Speed When Damaged By Enemies"));
    }

    public void beeInPanic(LivingDamageEvent event)
    {
        PlayerEntity player = event.getEntityLiving() instanceof PlayerEntity ? (PlayerEntity) event.getEntityLiving() : null;
        if (player == null) { return; }
        ServerWorld world = (ServerWorld) event.getEntity().getEntityWorld();

        float aggroDist = 10F;

        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem() == this && !player.getCooldownTracker().hasCooldown(this), player).ifPresent(found ->
        {
            if (event.getSource().getImmediateSource() instanceof LivingEntity)
            {
                player.addPotionEffect(new EffectInstance(Effects.SPEED, 60));
                player.addPotionEffect(new EffectInstance(TerraEffectRegistry.HONEY.get(), 100));
                BeeHandler.spawnAngryBees(world, player.getPosition(), aggroDist);
                CuriosApi.getCuriosHelper().getCuriosHandler(player).map(ICuriosItemHandler::getCurios).map(map -> map.get("curio")).map(ICurioStacksHandler::getStacks).map(dynamicStackHandler -> {
                    for (int i = 0; i < dynamicStackHandler.getSlots(); i++)
                    {
                        ItemStack stack = dynamicStackHandler.getStackInSlot(i);
                        if (stack.getItem().isIn(TerraTagRegistry.PANIC_GIVERS) || stack.getItem().isIn(TerraTagRegistry.BEE_SPAWNERS))
                        {
                            player.getCooldownTracker().setCooldown(stack.getItem(), 100);
                        }
                    }
                    return null;
                });
            }
        });
    }
}
