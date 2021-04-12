package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.entities.projectiles.TerraProjectileSwordTerra;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.items.ItemHandlerHelper;

public class TerraProjectileSword extends SwordItem
{
    private  Multimap<Attribute, AttributeModifier> spearAttributes;

    public TerraProjectileSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn)
    {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack)
    {
        if (spearAttributes == null) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", this.getAttackDamage(), AttributeModifier.Operation.ADDITION));
            builder.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", (double) +3.0F, AttributeModifier.Operation.ADDITION));

            spearAttributes = builder.build();
        }
        return slot == EquipmentSlotType.MAINHAND ? spearAttributes : super.getAttributeModifiers(slot,stack);
    }
}
