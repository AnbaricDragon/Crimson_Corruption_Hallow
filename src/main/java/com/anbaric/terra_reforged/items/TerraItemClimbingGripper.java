package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import com.mojang.math.Vector3d;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.List;

public class TerraItemClimbingGripper extends TerraItemAccessory
{
    public TerraItemClimbingGripper()
    {
        super();
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
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.wall_sliding").withStyle(ChatFormatting.BLUE));
                return toolTip;
            }

            @Override
            public void curioTick(SlotContext slotContext)
            {
                Player player = slotContext.entity() instanceof Player ? (Player) slotContext.entity() : null;
                Level  world  = player != null ? player.getLevel() : null;
                if (world == null) { return; }

                Vec3 vecPos = player.position();
                BlockPos pos = player.getOnPos().above();
                double x = vecPos.get(Direction.Axis.X);
                double z = vecPos.get(Direction.Axis.Z);
                double xi = Math.floor(x);
                double zi = Math.floor(z);
                double dotX = Math.abs((float) (x - xi));
                double dotZ = Math.abs((float) (z - zi));
                boolean shouldStick =
                        ((world.getBlockState(pos.relative(Direction.NORTH)).isFaceSturdy(world, pos.relative(Direction.NORTH), Direction.SOUTH) || (world.getBlockState(pos.above().relative(Direction.NORTH)).isFaceSturdy(world, pos.relative(Direction.NORTH), Direction.SOUTH)) && dotZ <= 0.301) ||
                        ((world.getBlockState(pos.relative(Direction.EAST) ).isFaceSturdy(world, pos.relative(Direction.EAST) , Direction.WEST)  || (world.getBlockState(pos.above().relative(Direction.EAST) ).isFaceSturdy(world, pos.relative(Direction.EAST) , Direction.WEST) ) && dotX >= 0.699) ||
                        ((world.getBlockState(pos.relative(Direction.SOUTH)).isFaceSturdy(world, pos.relative(Direction.SOUTH), Direction.NORTH) || (world.getBlockState(pos.above().relative(Direction.SOUTH)).isFaceSturdy(world, pos.relative(Direction.SOUTH), Direction.NORTH)) && dotZ >= 0.699) ||
                        ((world.getBlockState(pos.relative(Direction.WEST) ).isFaceSturdy(world, pos.relative(Direction.WEST) , Direction.EAST)  || (world.getBlockState(pos.above().relative(Direction.WEST) ).isFaceSturdy(world, pos.relative(Direction.WEST) , Direction.EAST) ) && dotX <= 0.301)))));

                if (shouldStick && !player.isInWater() && player.isCrouching())
                {
                    Vec3 motion = player.getDeltaMovement();
                    if (motion.y <= 0)
                    {
                        if (CurioHandler.hasAllBaubles(player, TerraItemRegistry.CLIMBING_CLAWS.get(), TerraItemRegistry.CLIMBING_SPIKES.get()))
                        {
                            player.setDeltaMovement(motion.x, 0, motion.z);
                        }
                        else
                        {
                            player.setDeltaMovement(motion.add(0, -motion.y - 0.05, 0));
                        }
                    }
                }
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
}
