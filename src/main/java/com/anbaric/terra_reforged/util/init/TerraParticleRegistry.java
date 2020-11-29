package com.anbaric.terra_reforged.util.init;

import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TerraParticleRegistry
{
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Reference.MODID);

    public static final RegistryObject<BasicParticleType> SPORE_MUSHROOM = PARTICLES.register("spore_mushroom", () -> new BasicParticleType(true));

    public static final RegistryObject<BasicParticleType> TORCH_FLAME_RED = PARTICLES.register("torch_flame_red", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> TORCH_FLAME_ORANGE = PARTICLES.register("torch_flame_orange", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> TORCH_FLAME_YELLOW  = PARTICLES.register("torch_flame_yellow", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> TORCH_FLAME_GREEN = PARTICLES.register("torch_flame_green", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> TORCH_FLAME_BLUE = PARTICLES.register("torch_flame_blue", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> TORCH_FLAME_PURPLE = PARTICLES.register("torch_flame_purple", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> TORCH_FLAME_WHITE = PARTICLES.register("torch_flame_white", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> TORCH_FLAME_RAINBOW = PARTICLES.register("torch_flame_rainbow", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> TORCH_FLAME_ICE = PARTICLES.register("torch_flame_ice", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> TORCH_FLAME_BONE = PARTICLES.register("torch_flame_bone", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> TORCH_FLAME_BRIGHT = PARTICLES.register("torch_flame_bright", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> TORCH_FLAME_DEMON = PARTICLES.register("torch_flame_demon", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> TORCH_FLAME_CURSED = PARTICLES.register("torch_flame_cursed", () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> TORCH_FLAME_ICHOR = PARTICLES.register("torch_flame_ichor", () -> new BasicParticleType(true));
}
