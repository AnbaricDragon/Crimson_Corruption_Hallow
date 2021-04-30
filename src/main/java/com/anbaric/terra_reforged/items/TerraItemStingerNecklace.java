package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.handlers.BeeHandler;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.CombatRules;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

public class TerraItemStingerNecklace extends TerraItemAccessory
{
    public TerraItemStingerNecklace(Properties properties)
    {
        super(properties);
        MinecraftForge.EVENT_BUS.addListener(this::spawnBees);
        MinecraftForge.EVENT_BUS.addListener(this::ignoreArmor);
    }

    public void spawnBees(LivingDamageEvent event)
    {
        PlayerEntity player = event.getEntityLiving() instanceof PlayerEntity ? (PlayerEntity) event.getEntityLiving() : null;
        if (player == null) { return; }
        ServerWorld world = (ServerWorld) event.getEntity().getEntityWorld();

        float aggroDist = 10F;

        CuriosApi.getCuriosHelper().findEquippedCurio(stack -> stack.getItem() == this && !player.getCooldownTracker().hasCooldown(stack.getItem()), player).ifPresent(found ->
        {
            player.addPotionEffect(new EffectInstance(TerraEffectRegistry.HONEY.get(), 100));
            BeeHandler.spawnAngryBees(world, player.getPosition(), aggroDist);
            CuriosApi.getCuriosHelper().getCuriosHandler(player).map(ICuriosItemHandler::getCurios).map(map -> map.get("curio")).map(ICurioStacksHandler::getStacks).map(dynamicStackHandler ->
            {
                for (int i = 0; i < dynamicStackHandler.getSlots(); i++)
                {
                    ItemStack stack = dynamicStackHandler.getStackInSlot(i);
                    if (stack.getItem().isIn(TerraTagRegistry.BEE_SPAWNERS))
                    {
                        player.getCooldownTracker().setCooldown(stack.getItem(), 100);
                    }
                }
                return null;
            });
        });
    }

    public void ignoreArmor(LivingHurtEvent event)
    {
        LivingEntity victim = event.getEntityLiving();
        PlayerEntity player = event.getSource().getTrueSource() instanceof PlayerEntity ? (PlayerEntity) event.getSource().getTrueSource() : null;
        if (player == null) { return; }
        CuriosApi.getCuriosHelper().findEquippedCurio(this, player).ifPresent(found -> {
            event.setAmount(CombatRules.getDamageAfterAbsorb(event.getAmount(), victim.getTotalArmorValue() - 1, (float) victim.getAttributeValue(Attributes.ARMOR_TOUGHNESS)));
        });
    }
}
