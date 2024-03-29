package com.anbaric.terra_reforged.items.accessories;

import com.anbaric.terra_reforged.items.TerraItemConsumeableMana;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.handlers.MathHandler;
import com.anbaric.terra_reforged.util.handlers.NBTHandler;
import com.anbaric.terra_reforged.util.handlers.V3Handler;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.List;

public class TerraItemMagnetCelestial extends TerraItemAccessory
{
    public TerraItemMagnetCelestial()
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
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.more_pickup_range").withStyle(ChatFormatting.BLUE).append(new TranslatableComponent("tooltip.terra_reforged.condition_mana").withStyle(ChatFormatting.BLUE)));
                return toolTip;
            }

            @Override
            public void curioTick(SlotContext slotContext)
            {
                LivingEntity player = slotContext.entity();
                if (player.isSpectator() || !(player instanceof Player)) { return; }

                int cooldown = NBTHandler.getCooldown(stack);
                if (cooldown <= 0)
                {
                    double x = player.getX();
                    double y = player.getY() + 0.75;
                    double z = player.getZ();

                    int range = CurioHandler.countBaubles((Player) player, TerraTagRegistry.MANA_BRINGERS) * 5;
                    List<ItemEntity> items  = player.getLevel().getEntitiesOfClass(ItemEntity.class, new AABB(x - range, y - range, z - range, x + range, y + range, z + range));
                    int              pulled = 0;
                    for (ItemEntity item : items)
                    {
                        if (((TerraItemMagnetCelestial) stack.getItem()).canPullItem(item))
                        {
                            if (pulled > 200) { break; }

                            MathHandler.setEntityMotionFromVector(item, new V3Handler(x, y, z), 0.45F);
                            if (player.getLevel().isClientSide())
                            {
                                player.getLevel().addParticle(ParticleTypes.ASH, item.getX(), item.getY(), item.getZ(), 0, 0, 0);
                            }
                            pulled++;
                        }
                    }
                }
                else
                {
                    NBTHandler.setCooldown(stack, cooldown - 1);
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

    private boolean canPullItem(ItemEntity item)
    {
        return !item.hasPickUpDelay() && item.getItem().getItem() instanceof TerraItemConsumeableMana;
    }
}
