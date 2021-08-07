package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.List;
import java.util.Random;

import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import javax.annotation.Nonnull;

import top.theillusivec4.curios.api.type.capability.ICurio.DropRule;
import top.theillusivec4.curios.api.type.capability.ICurio.SoundInfo;

public class TerraItemStarveil extends TerraItemAccessory
{
    public TerraItemStarveil()
    {
        super();
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerAttacked);
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.get("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "Shoots Stars When Damaged By Enemies"));
        tooltip.add(new StringTextComponent("\u00A79" + "+100% Invincibility When Damaged By Enemies"));
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused)
    {
        return CurioHandler.createProvider(new ICurio()
        {
            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity)
            {
                CompoundNBT compound = stack.getOrCreateTag();

                if (livingEntity.invulnerableTime <= 10)
                {
                    compound.putBoolean("canApplyInvuln", true);
                }
                else
                {
                    if (compound.getBoolean("canApplyInvuln"))
                    {
                        livingEntity.invulnerableTime += 20;
                        compound.putBoolean("canApplyInvuln", false);
                    }
                }
            }

            @Nonnull
            @Override
            public DropRule getDropRule(LivingEntity livingEntity)
            {
                return DropRule.DEFAULT;
            }

            @Nonnull
            @Override
            public SoundInfo getEquipSound(SlotContext slotContext)
            {
                return new SoundInfo(SoundEvents.ARMOR_EQUIP_GENERIC, 1.0f, 1.0f);
            }

            @Override
            public boolean canEquipFromUse(SlotContext slot)
            {
                return true;
            }
        });
    }

    public void onPlayerAttacked(LivingAttackEvent event)
    {
        PlayerEntity player = event.getEntityLiving() instanceof PlayerEntity ? (PlayerEntity) event.getEntityLiving() : null;
        if (player == null) { return; }
        World world = player.getCommandSenderWorld();

        CuriosApi.getCuriosHelper().findEquippedCurio(this, player).ifPresent(found ->
        {
            if (!player.getCooldowns().isOnCooldown(this) && event.getSource().getDirectEntity() instanceof LivingEntity)
            {
                ServerWorld server = (ServerWorld) world;
                spawnArrows(server, player.blockPosition(), player.getRandom());

                CuriosApi.getCuriosHelper().getCuriosHandler(player).map(ICuriosItemHandler::getCurios).map(map -> map.get("curio")).map(ICurioStacksHandler::getStacks).map(dynamicStackHandler ->
                {
                    for (int i = 0; i < dynamicStackHandler.getSlots(); i++)
                    {
                        ItemStack stack = dynamicStackHandler.getStackInSlot(i);
                        if (stack.getItem().is(TerraTagRegistry.STAR_SPAWNERS))
                        {
                            player.getCooldowns().addCooldown(stack.getItem(), 100);
                        }
                    }
                    return null;
                });
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
            world.addFreshEntity(arrowEntity);
        }
    }
}
