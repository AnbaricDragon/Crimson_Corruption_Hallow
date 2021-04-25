package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.effects.TerraEffectInvincibility;
import com.anbaric.terra_reforged.effects.TerraEffectLoomingDeath;
import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TerraEffectRegistry
{
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Reference.MODID);

    public static final RegistryObject<Effect> LOOMING_DEATH = EFFECTS.register("looming_death", () -> new TerraEffectLoomingDeath(EffectType.HARMFUL, 0));
    public static final RegistryObject<Effect> INVINCIBILITY = EFFECTS.register("invincibility", () -> new TerraEffectInvincibility(EffectType.BENEFICIAL, 0));
}
