package com.anbaric.terra_reforged;

import com.anbaric.terra_reforged.capabilities.hardmode.TerraCapabilityWorldProgression;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.events.*;
import com.anbaric.terra_reforged.util.handlers.*;
import com.anbaric.terra_reforged.util.init.*;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.DamageSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

import java.util.stream.Collectors;

//import com.anbaric.terra_reforged.util.TerraVanillaCompat;

@Mod(Reference.MODID)
public class TerraReforged
{
    public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);

    public static final boolean debugSpreading = false;

    public static PlantType BOREAL = PlantType.get("boreal");
    public static PlantType MUSHROOM = PlantType.get("mushroom");
    public static DamageSource THORNS = new DamageSource("thorns").setDamageBypassesArmor();

    public TerraReforged()
    {
        LOGGER.debug("Hello from Terra Reforged!");

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the setup method for modloading
        modEventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        modEventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        modEventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        modEventBus.addListener(this::doClientStuff);

        TerraParticleRegistry.PARTICLES.register(modEventBus);
        TerraBlockRegistry.BLOCKS.register(modEventBus);
        TerraItemRegistry.ITEMS.register(modEventBus);
        TerraEffectRegistry.EFFECTS.register(modEventBus);
        TerraEntityRegistry.ENTITIES.register(modEventBus);
        TerraTileEntityRegistry.TILE_ENTITIES.register(modEventBus);
        TerraFeatureRegistry.FEATURES.register(modEventBus);
        TerraCarverRegistry.CARVERS.register(modEventBus);
        TerraSurfaceBuilderRegistry.SURFACE_BUILDERS.register(modEventBus);
        TerraBiomeRegistry.BIOMES.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(TerraKeyRegistry.class);

        // Register events
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(TerraTestEvent.class);
        MinecraftForge.EVENT_BUS.register(TerraStructureProtectEvent.class);
        MinecraftForge.EVENT_BUS.register(TerraCapabilitiesEvent.class);
        MinecraftForge.EVENT_BUS.register(TerraDefaultBiomeAdditionsEvent.class);
        MinecraftForge.EVENT_BUS.register(TerraGuiRenderEvent.class);
        MinecraftForge.EVENT_BUS.register(new TerraJumpEvent());
        MinecraftForge.EVENT_BUS.register(new TerraDashEvent());

        MinecraftForge.EVENT_BUS.register(FeatureGenHandler.class);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(BiomeHandler::addBiomes);
        event.enqueueWork(CarverHandler::addCarvers);
        NetworkHandler.register();
        TerraCapabilityWorldProgression.register();
        FeatureGenHandler.configureFeatures();

        //        TerraVanillaCompat.setupStripping();
        //        TerraVanillaCompat.setupFlammable();
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
        EntityHandler.register(event.getMinecraftSupplier());
        TerraKeyRegistry.register();
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.CURIO.getMessageBuilder().size(6).build());
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("terra_reforged", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event)
    {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
