package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.PotionEvent;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.List;

public class TerraItemCharmMyths extends TerraItemAccessory
{
    public TerraItemCharmMyths()
    {
        super();
        MinecraftForge.EVENT_BUS.addListener(this::addPotionSickness);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.format("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "Gives Health Regen"));
        tooltip.add(new StringTextComponent("\u00A79" + "Reduces Cooldown Of Healing Potions"));
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused)
    {
        return CurioHandler.createProvider(new ICurio()
        {
            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity)
            {
                PlayerEntity player = livingEntity instanceof PlayerEntity? (PlayerEntity) livingEntity : null;
                if (player == null) { return; }
                if (player.ticksExisted % 200 == 0 && player.getHealth() < player.getMaxHealth())
                {
                    player.heal(1);
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
                return new SoundInfo(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1.0f, 1.0f);
            }

            @Override
            public boolean canEquipFromUse(SlotContext slot)
            {
                return true;
            }
        });
    }

    private void addPotionSickness(PotionEvent.PotionAddedEvent event)
    {
        PlayerEntity player = event.getEntityLiving() instanceof PlayerEntity ? (PlayerEntity) event.getEntityLiving() : null;
        if (player != null)
        {
            boolean hasPotionReducer = CurioHandler.hasBauble(player, TerraTagRegistry.POTION_REDUCERS);

            if (event.getOldPotionEffect().getPotion() == Effects.HEALTH_BOOST)
            {
                player.addPotionEffect(new EffectInstance(TerraEffectRegistry.POTION_SICKNESS.get(), hasPotionReducer ? 45 : 60));
            }
        }
    }
}
