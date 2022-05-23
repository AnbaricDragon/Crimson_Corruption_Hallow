package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.util.init.TerraParticleRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.SuspendedTownParticle;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraParticleRegisterEvent
{
    @SubscribeEvent(priority = EventPriority.LOWEST)
    static void registerParticles(ParticleFactoryRegisterEvent event)
    {
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.MUSHROOM_SPORE_GLOWING.get(), SuspendedTownParticle.Provider::new);

        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_RED.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_ORANGE.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_YELLOW .get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_GREEN.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_BLUE.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_PURPLE.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_WHITE.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_RAINBOW.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_ICE.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_PINK.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_BONE.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_BRIGHT.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_DEMON.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_CURSED.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_ICHOR.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_DESERT.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_CORAL.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_CORRUPT.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_CRIMSON.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_HALLOWED.get(), FlameParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(TerraParticleRegistry.TORCH_FLAME_JUNGLE.get(), FlameParticle.Provider::new);
    }
}
