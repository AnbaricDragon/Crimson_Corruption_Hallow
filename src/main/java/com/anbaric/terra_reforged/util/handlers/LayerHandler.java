package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class LayerHandler
{
    public static final ModelLayerLocation FLEDGELING_WINGS = createLayerLocation("fledgeling_wings");

    public static ModelLayerLocation createLayerLocation(String name) {
        return new ModelLayerLocation(new ResourceLocation(Reference.MODID, name), name);
    }
}
