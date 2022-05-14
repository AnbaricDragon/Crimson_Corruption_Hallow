package com.anbaric.terra_reforged.items.accessories;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TerraItemFrogGear extends TerraItemAccessory
{
    public TerraItemFrogGear()
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
            public boolean canEquip(SlotContext slotContext)
            {
                if (!(slotContext.entity() instanceof Player)) { return false; }
                return !CurioHandler.hasBauble((Player) slotContext.entity(), TerraTagRegistry.FROG_FALLERS, TerraTagRegistry.WALL_GRIPPERS, TerraTagRegistry.FROG_HIGH_JUMPERS, TerraTagRegistry.WATER_SPEEDERS);
            }

            @Override
            public List<Component> getSlotsTooltip(List<Component> tooltips)
            {
                List<Component> toolTip = tooltips;
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.less_fall_distance").withStyle(ChatFormatting.BLUE));
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.extra_speed_water_30").withStyle(ChatFormatting.BLUE));
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.wall_sticking").withStyle(ChatFormatting.BLUE));
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.jump_height_50").withStyle(ChatFormatting.BLUE));
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

                if (!player.isOnGround() && !player.isCreative() && shouldStick && !player.isInWater())
                {
                    Vec3 motion = player.getDeltaMovement();
                    if (motion.y <= 0)
                    {
                        if (player.isCrouching())
                        {
                            player.setDeltaMovement(motion.x, 0, motion.z);
                        }
                    }
                }
            }

            @Override
            public List<Component> getAttributesTooltip(List<Component> tooltips)
            {
                return new ArrayList<>();
            }

            @Override
            public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid)
            {
                Multimap<Attribute, AttributeModifier> atts = HashMultimap.create();
                atts.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, Reference.MODID + ":frog_gear_bonus", 0.30F, AttributeModifier.Operation.MULTIPLY_BASE));
                return atts;
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
