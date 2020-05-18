package com.anbaric.terra_reforged.util.handlers;


import com.anbaric.terra_reforged.particles.TerraParticleTorchFlame;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.init.TerraParticleRegistry;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleHandler
{
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticles(ParticleFactoryRegisterEvent event)
    {
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_RED.get(), TerraParticleTorchFlame.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_ORANGE.get(), TerraParticleTorchFlame.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_YELLOW .get(), TerraParticleTorchFlame.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_GREEN.get(), TerraParticleTorchFlame.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_BLUE.get(), TerraParticleTorchFlame.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_PURPLE.get(), TerraParticleTorchFlame.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_WHITE.get(), TerraParticleTorchFlame.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_RAINBOW.get(), TerraParticleTorchFlame.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_ICE.get(), TerraParticleTorchFlame.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_BONE.get(), TerraParticleTorchFlame.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_BRIGHT.get(), TerraParticleTorchFlame.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_DEMON.get(), TerraParticleTorchFlame.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_CURSED.get(), TerraParticleTorchFlame.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_ICHOR.get(), TerraParticleTorchFlame.Factory::new);
    }
}
