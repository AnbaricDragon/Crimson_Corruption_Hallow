package com.anbaric.terra_reforged.items.accessories;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TerraItemBootsFairy extends TerraItemAccessory
{
    public TerraItemBootsFairy()
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
                if (!(slotContext.entity() instanceof Player))
                {
                    return false;
                }
                return !CurioHandler.hasBauble((Player) slotContext.entity(), TerraTagRegistry.BOOT_SPEEDERS);
            }

            @Override
            public List<Component> getSlotsTooltip(List<Component> tooltips)
            {
                List<Component> toolTip = tooltips;
                toolTip.add(new TranslatableComponent(""));
                toolTip.add(new TranslatableComponent("curios.modifiers.curio").withStyle(ChatFormatting.GOLD));
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.block_spawn_flowers").withStyle(ChatFormatting.BLUE));
                toolTip.add(new TranslatableComponent("tooltip.terra_reforged.extra_speed_20").withStyle(ChatFormatting.BLUE));
                return toolTip;
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
                atts.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, Reference.MODID + ":boot_speed_bonus", 0.2F, AttributeModifier.Operation.MULTIPLY_BASE));
                return atts;
            }

            @Override
            public void curioTick(SlotContext slotContext)
            {
                Player player = slotContext.entity() instanceof Player ? (Player) slotContext.entity() : null;

                Level    world   = player.getLevel();
                BlockPos dirtPos = player.getOnPos();

                if (world.getBlockState(dirtPos).is(BlockTags.DIRT) && world.getBlockState(dirtPos.above()).isAir())
                {
                    List<Block> flower      = ForgeRegistries.BLOCKS.tags().getTag(BlockTags.FLOWERS).stream().toList();
                    int         flowerIndex = world.random.nextInt(flower.size());
                    Block       flowerBlock = flower.get(flowerIndex);
                    if (flowerBlock == Blocks.WITHER_ROSE || flowerBlock == Blocks.FLOWERING_AZALEA || flowerBlock == Blocks.FLOWERING_AZALEA_LEAVES)
                    {
                        return;
                    }

                    if (flowerBlock instanceof DoublePlantBlock)
                    {
                        DoublePlantBlock.placeAt(world, flowerBlock.defaultBlockState(), dirtPos.above(), 3);
                    }
                    else
                    {
                        world.setBlockAndUpdate(dirtPos.above(), flower.get(flowerIndex).defaultBlockState());
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
