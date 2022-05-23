package com.anbaric.terra_reforged;

import com.anbaric.terra_reforged.capabilities.player_mana.TerraMana;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.events.*;
import com.anbaric.terra_reforged.util.handlers.ChannelHandler;
import com.anbaric.terra_reforged.util.init.*;
import com.mojang.logging.LogUtils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Reference.MODID)
public class TerraReforged
{
    public static PlantType BOREAL = PlantType.get("boreal");
    public static PlantType MUSHROOM = PlantType.get("mushroom");
    public static DamageSource THORNS = new DamageSource("thorns").bypassArmor();
    public static DamageSource MINION = new DamageSource("minion");

    //TODO JUNGLE VARIANTS FOR ORES IN TAGS

    public static final CreativeModeTab TERRA_BLOCKS_TAB = new CreativeModeTab("terra_blocks_tab") { public ItemStack makeIcon() { return new ItemStack(TerraBlockRegistry.GRASS_JUNGLE.get()); }};
    public static final CreativeModeTab TERRA_DECORATIONS_TAB = new CreativeModeTab("terra_decorations_tab") { public ItemStack makeIcon() { return new ItemStack(TerraBlockRegistry.LEAF_PEARL_CYAN.get()); }};
    public static final CreativeModeTab TERRA_MATERIALS_TAB = new CreativeModeTab("terra_materials_tab") { public ItemStack makeIcon() { return new ItemStack(TerraItemRegistry.INGOT_COBALT.get()); }};
    public static final CreativeModeTab TERRA_MECHANICS_TAB = new CreativeModeTab("terra_mechanics_tab") { public ItemStack makeIcon() { return new ItemStack(Items.REDSTONE); }};
    public static final CreativeModeTab TERRA_MONSTERS_TAB = new CreativeModeTab("terra_monsters_tab") { public ItemStack makeIcon() { return new ItemStack(Items.FOX_SPAWN_EGG); }};
    public static final CreativeModeTab TERRA_TOOLS_TAB = new CreativeModeTab("terra_tools_tab") { public ItemStack makeIcon() { return new ItemStack(Items.NETHERITE_PICKAXE); }};
    public static final CreativeModeTab TERRA_WEAPONS_TAB = new CreativeModeTab("terra_weapons_tab") { public ItemStack makeIcon() { return new ItemStack(Items.NETHERITE_SWORD); }};

    public static final boolean debugSpreading = true;

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public TerraReforged()
    {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        TerraParticleRegistry.PARTICLES.register(modEventBus);
        TerraBlockRegistry.BLOCKS.register(modEventBus);
        TerraItemRegistry.ITEMS.register(modEventBus);
        TerraEffectRegistry.EFFECTS.register(modEventBus);
        TerraAttributeRegistry.ATTRIBUTES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(TerraEffectNegationEvent.class);
        MinecraftForge.EVENT_BUS.register(TerraPlayerTickEvent.class);
        MinecraftForge.EVENT_BUS.register(TerraCapabilitiesEvent.class);
        MinecraftForge.EVENT_BUS.register(TerraMagnetItemEvent.class);
        MinecraftForge.EVENT_BUS.register(TerraDamageEffectsEvent.class);
        MinecraftForge.EVENT_BUS.register(TerraTooltipEvent.class);
        MinecraftForge.EVENT_BUS.register(new TerraJumpEvent());
        MinecraftForge.EVENT_BUS.register(new TerraDashEvent());
        modEventBus.register(TerraAttributeAdditionEvent.class);
        modEventBus.register(TerraParticleRegisterEvent.class);
        TerraMana.register();
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        TerraOverlayRegistry.register();
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        ChannelHandler.register();
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.CURIO.getMessageBuilder().size(5).build());
        // Some example code to dispatch IMC to another mod
        InterModComms.sendTo("terra_reforged", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // Some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
