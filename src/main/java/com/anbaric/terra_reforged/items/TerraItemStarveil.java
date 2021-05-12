package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.entities.goals.BeeItemGoal;
import com.anbaric.terra_reforged.util.handlers.BeeHandler;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.List;
import java.util.Random;

import net.minecraft.item.Item.Properties;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

public class TerraItemStarveil extends TerraItemAccessory
{
    public TerraItemStarveil(Properties properties)
    {
        super(properties);
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerAttacked);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.format("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "Shoots Stars When Damaged By Enemies"));
        tooltip.add(new StringTextComponent("\u00A79" + "+100% Invincibility When Damaged By Enemies"));
    }

    public void onPlayerAttacked(LivingAttackEvent event)
    {
        PlayerEntity player = event.getEntityLiving() instanceof PlayerEntity ? (PlayerEntity) event.getEntityLiving() : null;
        if (player == null) { return; }
        World world = player.getEntityWorld();

        CuriosApi.getCuriosHelper().findEquippedCurio(this, player).ifPresent(found ->
        {
            if (!player.isPotionActive(TerraEffectRegistry.INVINCIBILITY.get()) && !player.getCooldownTracker().hasCooldown(this) && event.getSource().getImmediateSource() instanceof LivingEntity)
            {
                ServerWorld server = (ServerWorld) world;
                spawnArrows(server, player.getPosition(), player.getRNG());
                player.addPotionEffect(new EffectInstance(TerraEffectRegistry.INVINCIBILITY.get(), 40, 0, false, false));
                CuriosApi.getCuriosHelper().getCuriosHandler(player).map(ICuriosItemHandler::getCurios).map(map -> map.get("curio")).map(ICurioStacksHandler::getStacks).map(dynamicStackHandler ->
                {
                    for (int i = 0; i < dynamicStackHandler.getSlots(); i++)
                    {
                        ItemStack stack = dynamicStackHandler.getStackInSlot(i);
                        if (stack.getItem().isIn(TerraTagRegistry.INVULN_GIVERS))
                        {
                            player.getCooldownTracker().setCooldown(stack.getItem(), 100);
                        }
                    }
                    return null;
                });
            }
            else
            {
                event.setCanceled(true);
            }
        });
    }

    public static void spawnArrows(ServerWorld world, BlockPos pos, Random rand)
    {
        for (int i = 0; i < 3; i++)
        {
            int offsetY = pos.getY() + rand.nextInt(5) + 15;
            BlockPos arrowPos = new BlockPos(pos.getX() + 5 - rand.nextInt(10), offsetY, pos.getZ() + 5 - rand.nextInt(10));
            AbstractArrowEntity arrowEntity = new ArrowEntity(world, arrowPos.getX(), arrowPos.getY(), arrowPos.getZ());

            double d1 = pos.getX() - arrowPos.getX();
            double d2 = pos.getY() - offsetY;
            double d3 = pos.getZ() - arrowPos.getZ();
            float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
            arrowEntity.shoot(d1, d2 + f, d3, 3.0F, 0.0F);
            world.addEntity(arrowEntity);
        }
    }
}
