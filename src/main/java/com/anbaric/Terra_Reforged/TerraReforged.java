package com.anbaric.terra_reforged;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.TerraCompat;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MODID)
public class TerraReforged
{
    private static final Logger LOGGER = LogManager.getLogger();

    //New Types
    public static PlantType MOSS = PlantType.create("Moss");
    public static DamageSource THORNS = new DamageSource("thorns").setDamageBypassesArmor();

    public static boolean debugSpreading = true;

    public TerraReforged()
    {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loadComplete);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        TerraCompat.setup();

    }

    private void loadComplete(final FMLLoadCompleteEvent event)
    {
        //ModCompatibility.setup();
    }
}
