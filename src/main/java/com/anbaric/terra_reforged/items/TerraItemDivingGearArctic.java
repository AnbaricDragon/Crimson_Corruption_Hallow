package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
//import net.hypherionmc.hypcore.api.APIUtils;
//import net.hypherionmc.hypcore.api.ColoredLightManager;
//import net.hypherionmc.hypcore.api.Light;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.ModList;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

import top.theillusivec4.curios.api.type.capability.ICurio.DropRule;
import top.theillusivec4.curios.api.type.capability.ICurio.SoundInfo;

public class TerraItemDivingGearArctic extends TerraItemAccessory
{
    public TerraItemDivingGearArctic()
    {
        super();
        if (ModList.get().isLoaded("hypcore"))
        {
//            ColoredLightManager.registerProvider(this, this::shine);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.get("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "Grants Pefect Control On Ice"));
        tooltip.add(new StringTextComponent("\u00A79" + "Glows"));
        tooltip.add(new StringTextComponent("\u00A79" + "+50% Swimming Speed"));
        tooltip.add(new StringTextComponent("\u00A79" + "+200% Extra Air"));
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused)
    {
        return CurioHandler.createProvider(new ICurio()
        {
            @Override
            public boolean showAttributesTooltip(String identifier)
            {
                return false;
            }

            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity)
            {
                if (livingEntity.hasEffect(TerraEffectRegistry.CHILLED.get()))
                {
                    livingEntity.removeEffect(TerraEffectRegistry.CHILLED.get());
                }
            }

            public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid)
            {
                Multimap<Attribute, AttributeModifier> atts = LinkedHashMultimap.create();
                atts.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, Reference.MODID + ":flipperBonus", 0.50F, AttributeModifier.Operation.MULTIPLY_BASE));
                return atts;
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

//    public Light shine(Entity entity, ItemStack stack)
//    {
//        PlayerEntity player = entity instanceof PlayerEntity ? (PlayerEntity) entity : null;
//        if (player == null) { return null; }
//
//        if (CurioHandler.hasBauble(player, this))
//        {
//            return (Light) Light.builder().pos(APIUtils.entityPos(entity)).color(0f, 0.85f, 1.0f, player.isInWater() ? 1.0F : 5.0F).radius(player.isInWater() ? 20 : 5).build();
//        }
//        else return null;
//    }
}
