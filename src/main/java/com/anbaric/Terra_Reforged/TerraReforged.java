package com.anbaric.terra_reforged;

import com.anbaric.terra_reforged.util.Reference;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Reference.MODID)
public class TerraReforged
{
    private static final Logger LOGGER = LogManager.getLogger();

    //Plant Types
    public static PlantType BOREAL = PlantType.create("Boreal");
    public static PlantType JUNGLE = PlantType.create("Jungle");
    public static PlantType CORRUPT = PlantType.create("Corrupt");
    public static PlantType CRIMSON = PlantType.create("Crimson");
    public static PlantType HALLOWED = PlantType.create("Hallowed");
    public static PlantType MUSHROOM = PlantType.create("Mushroom");

    public static boolean debugSpreading = true;

    public TerraReforged()
    {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }
}
