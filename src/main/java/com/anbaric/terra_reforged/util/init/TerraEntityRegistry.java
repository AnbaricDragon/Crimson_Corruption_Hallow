package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.entities.projectiles.TerraProjectileSwordTerra;
import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TerraEntityRegistry
{
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Reference.MODID);

    public static final RegistryObject<EntityType<TerraProjectileSwordTerra>> PROJECTILE_SWORD_TERRA = ENTITIES.register("projectile_sword_terra",
            () -> EntityType.Builder.<TerraProjectileSwordTerra>of(TerraProjectileSwordTerra::new, EntityClassification.MISC).sized(0.5F, 0.5F).build("projectile_sword_terra"));
}
