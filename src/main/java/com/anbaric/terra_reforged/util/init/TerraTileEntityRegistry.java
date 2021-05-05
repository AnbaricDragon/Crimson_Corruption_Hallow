package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.entities.projectiles.TerraProjectileSwordTerra;
import com.anbaric.terra_reforged.entities.tiles.TerraTileEntityHeartLantern;
import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TerraTileEntityRegistry
{
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Reference.MODID);

    public static final RegistryObject<TileEntityType<TerraTileEntityHeartLantern>> LANTERN_HEART = TILE_ENTITIES.register("lantern_heart", () -> TileEntityType.Builder.of(TerraTileEntityHeartLantern::new, TerraBlockRegistry.LANTERN_HEART.get()).build(null));
}
