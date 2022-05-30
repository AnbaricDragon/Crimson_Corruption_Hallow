package com.anbaric.terra_reforged.items.renders;

import com.anbaric.terra_reforged.items.models.TerraWingModel;
import com.anbaric.terra_reforged.util.Reference;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class TerraWingRenderer implements ICurioRenderer
{
    private final ResourceLocation texture;
    private final TerraWingModel model;

    public TerraWingRenderer(String texturePath, TerraWingModel model)
    {
        this(Reference.path(String.format("textures/entity/wings/%s.png", texturePath)), model);
    }

    public TerraWingRenderer(ResourceLocation texture, TerraWingModel model)
    {
        this.texture = texture;
        this.model = model;
    }

    protected ResourceLocation getTexture()
    {
        return texture;
    }

    protected TerraWingModel getModel()
    {
        return model;
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource multiBufferSource, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
    {
        TerraWingModel model = getModel();
        poseStack.translate(0, -0.1D, 0);
        poseStack.scale(1.1F, 1.1F, 1.1F);
        model.setupAnim(slotContext.entity(), limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        model.prepareMobModel(slotContext.entity(), limbSwing, limbSwingAmount, partialTicks);
        ICurioRenderer.followBodyRotations(slotContext.entity(), model);
        render(poseStack, multiBufferSource, light, stack.hasFoil());
    }

    protected void render(PoseStack matrixStack, MultiBufferSource buffer, int light, boolean hasFoil)
    {
        RenderType     renderType    = model.renderType(getTexture());
        VertexConsumer vertexBuilder = ItemRenderer.getFoilBuffer(buffer, renderType, false, hasFoil);
        model.renderToBuffer(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }
}