package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TerraParticleRegistry
{
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Reference.MODID);

    public static final RegistryObject<SimpleParticleType> MUSHROOM_SPORE_GLOWING = PARTICLES.register("mushroom_spore_glowing", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_RED = PARTICLES.register("torch_flame_red", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_ORANGE = PARTICLES.register("torch_flame_orange", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_YELLOW  = PARTICLES.register("torch_flame_yellow", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_GREEN = PARTICLES.register("torch_flame_green", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_BLUE = PARTICLES.register("torch_flame_blue", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_PURPLE = PARTICLES.register("torch_flame_purple", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_WHITE = PARTICLES.register("torch_flame_white", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_RAINBOW = PARTICLES.register("torch_flame_rainbow", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_ICE = PARTICLES.register("torch_flame_ice", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_PINK = PARTICLES.register("torch_flame_pink", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_BONE = PARTICLES.register("torch_flame_bone", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_BRIGHT = PARTICLES.register("torch_flame_bright", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_DEMON = PARTICLES.register("torch_flame_demon", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_CURSED = PARTICLES.register("torch_flame_cursed", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_ICHOR = PARTICLES.register("torch_flame_ichor", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_DESERT = PARTICLES.register("torch_flame_desert", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_CORAL = PARTICLES.register("torch_flame_coral", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_CORRUPT = PARTICLES.register("torch_flame_corrupt", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_CRIMSON = PARTICLES.register("torch_flame_crimson", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_HALLOWED = PARTICLES.register("torch_flame_hallowed", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TORCH_FLAME_JUNGLE = PARTICLES.register("torch_flame_jungle", () -> new SimpleParticleType(true));
}
