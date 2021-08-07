package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TerraAttributeRegistry
{
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, Reference.MODID);

    public static final RegistryObject<Attribute> MANA_MAX = ATTRIBUTES.register("mana_max", () -> new RangedAttribute("attribute.name.terra_reforged.max_mana", 20.0D, 20.0D, 400.0D).setSyncable(true));
    public static final RegistryObject<Attribute> MANA_DISCOUNT = ATTRIBUTES.register("mana_discount", () -> new RangedAttribute("attribute.name.terra_reforged.mana_discount", 0.0D, 0.0D, 1.0D).setSyncable(true));
}
