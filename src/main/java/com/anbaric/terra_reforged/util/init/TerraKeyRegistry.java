package com.anbaric.terra_reforged.util.init;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class TerraKeyRegistry
{
    public static KeyBinding KEY_HOOK = new KeyBinding("terra_reforged.key.grappling_hook", GLFW.GLFW_KEY_LEFT_CONTROL, "key.categories.terra_reforged");

    public static void register()
    {
        ClientRegistry.registerKeyBinding(KEY_HOOK);
    }
}
