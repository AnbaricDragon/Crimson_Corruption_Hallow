package com.anbaric.terra_reforged.items.renders;

import com.anbaric.terra_reforged.items.models.TerraFlyingCarpetModel;
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

public class TerraFlyingCarpetRenderer implements ICurioRenderer
{
    private final ResourceLocation texture;
    private final TerraFlyingCarpetModel extendedModel;

    public TerraFlyingCarpetRenderer(String texturePath, TerraFlyingCarpetModel extendedModel)
    {
        this(Reference.path(String.format("textures/entity/%s.png", texturePath)), extendedModel);
    }

    public TerraFlyingCarpetRenderer(ResourceLocation texture, TerraFlyingCarpetModel extendedModel)
    {
        this.texture = texture;
        this.extendedModel = extendedModel;
    }

    protected ResourceLocation getTexture()
    {
        return texture;
    }

    protected TerraFlyingCarpetModel getModel()
    {
        return extendedModel;
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource multiBufferSource, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
    {
        if (slotContext.entity().getDeltaMovement().y == 0)
        {
            TerraFlyingCarpetModel model = getModel();
            poseStack.translate(0, 0, 0);
            poseStack.scale(2, 2, 2);
            model.setupAnim(slotContext.entity(), limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            model.prepareMobModel(slotContext.entity(), limbSwing, limbSwingAmount, partialTicks);
            render(model, poseStack, multiBufferSource, light, stack.hasFoil());
        }
    }

    protected void render(TerraFlyingCarpetModel model, PoseStack matrixStack, MultiBufferSource buffer, int light, boolean hasFoil)
    {
        RenderType     renderType    = model.renderType(getTexture());
        VertexConsumer vertexBuilder = ItemRenderer.getFoilBuffer(buffer, renderType, false, hasFoil);
        model.renderToBuffer(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }
}
