package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.util.init.TerraEntityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import java.util.function.Supplier;

public class EntityHandler
{
    public static void register(Supplier<Minecraft> minecraft)
    {
        ItemRenderer renderer = minecraft.get().getItemRenderer();

        RenderingRegistry.registerEntityRenderingHandler(TerraEntityRegistry.PROJECTILE_SWORD_TERRA.get(), (renderManager) -> new SpriteRenderer<>(renderManager, renderer));
    }
}
