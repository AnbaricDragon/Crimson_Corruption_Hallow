package com.anbaric.terra_reforged.util.handlers;


//import com.anbaric.terra_reforged.particles.TerraParticleMushroomSpore;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.init.TerraParticleRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.SuspendedTownParticle;
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
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.SPORE_MUSHROOM.get(), SuspendedTownParticle.Factory::new);

        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_RED.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_ORANGE.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_YELLOW .get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_GREEN.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_BLUE.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_PURPLE.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_WHITE.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_RAINBOW.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_ICE.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_BONE.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_BRIGHT.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_DEMON.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_CURSED.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_ICHOR.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_DESERT.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_CORAL.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_CORRUPT.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_CRIMSON.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_HALLOWED.get(), FlameParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(TerraParticleRegistry.TORCH_FLAME_JUNGLE.get(), FlameParticle.Factory::new);
    }
}
