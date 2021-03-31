package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.handlers.SpreadingHandler;
import com.anbaric.terra_reforged.world.surface_builders.TerraSBGrass;
import com.anbaric.terra_reforged.world.surface_builders.TerraSBMud;
import com.anbaric.terra_reforged.world.surface_builders.TerraSBSand;
import com.anbaric.terra_reforged.world.surface_builders.TerraSBStone;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class TerraSurfaceBuilderRegistry
{
    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, Reference.MODID);

    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> JUNGLE_GRASS = SURFACE_BUILDERS.register("jungle_grass", () -> new TerraSBMud());

    //CORRUPT
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> CORRUPT_GRASS = SURFACE_BUILDERS.register("corrupt_grass", () -> new TerraSBGrass(SpreadingHandler.EnumBiomeType.CORRUPT));
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> CORRUPT_SAND  = SURFACE_BUILDERS.register("corrupt_sand", () -> new TerraSBSand(SpreadingHandler.EnumBiomeType.CORRUPT));
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> CORRUPT_STONE  = SURFACE_BUILDERS.register("corrupt_stone", () -> new TerraSBStone(SpreadingHandler.EnumBiomeType.CORRUPT));

    //CRIMSON
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> CRIMSON_GRASS = SURFACE_BUILDERS.register("crimson_grass", () -> new TerraSBGrass(SpreadingHandler.EnumBiomeType.CRIMSON));
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> CRIMSON_SAND  = SURFACE_BUILDERS.register("crimson_sand", () -> new TerraSBSand(SpreadingHandler.EnumBiomeType.CRIMSON));
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> CRIMSON_STONE  = SURFACE_BUILDERS.register("crimson_stone", () -> new TerraSBStone(SpreadingHandler.EnumBiomeType.CRIMSON));

    //HALLOWED
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> HALLOWED_GRASS = SURFACE_BUILDERS.register("hallowed_grass", () -> new TerraSBGrass(SpreadingHandler.EnumBiomeType.HALLOWED));
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> HALLOWED_SAND  = SURFACE_BUILDERS.register("hallowed_sand", () -> new TerraSBSand(SpreadingHandler.EnumBiomeType.HALLOWED));
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> HALLOWED_STONE  = SURFACE_BUILDERS.register("hallowed_stone", () -> new TerraSBStone(SpreadingHandler.EnumBiomeType.HALLOWED));
}
