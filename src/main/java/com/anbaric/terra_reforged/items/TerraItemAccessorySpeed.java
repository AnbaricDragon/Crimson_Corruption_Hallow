package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.Reference;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class TerraItemAccessorySpeed extends TerraItemAccessory
{
    private float moveSpeed;

    public TerraItemAccessorySpeed(float moveSpeed)
    {
        super();
        this.moveSpeed = moveSpeed;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack)
    {
        Multimap<Attribute, AttributeModifier> result = super.getAttributeModifiers(slotContext, uuid, stack);
        float moveSpeedBonus = this.moveSpeed;
        result.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, Reference.path("power_glove_attack_damage").toString(), moveSpeedBonus, AttributeModifier.Operation.MULTIPLY_BASE));
        return result;
    }
}
