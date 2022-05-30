package com.anbaric.terra_reforged.items.renders;

import com.anbaric.terra_reforged.items.models.TerraStagedWingModel;
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

public class TerraStagedWingRenderer implements ICurioRenderer
{
    private final ResourceLocation texture;
    private final TerraStagedWingModel extendedModel;
    private final TerraStagedWingModel restingModel;

    public TerraStagedWingRenderer(String texturePath, TerraStagedWingModel extendedModel, TerraStagedWingModel restingModel)
    {
        this(Reference.path(String.format("textures/entity/wings/%s.png", texturePath)), extendedModel, restingModel);
    }

    public TerraStagedWingRenderer(ResourceLocation texture, TerraStagedWingModel extendedModel, TerraStagedWingModel restingModel)
    {
        this.texture = texture;
        this.extendedModel = extendedModel;
        this.restingModel = restingModel;
    }

    protected ResourceLocation getTexture()
    {
        return texture;
    }

    protected TerraStagedWingModel getModel(boolean isFlying)
    {
        return isFlying ? extendedModel : restingModel;
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource multiBufferSource, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
    {
        TerraStagedWingModel model = getModel(slotContext.entity().isFallFlying());
        model.setupAnim(slotContext.entity(), limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        model.prepareMobModel(slotContext.entity(), limbSwing, limbSwingAmount, partialTicks);
        renderLayerParent.getModel().copyPropertiesTo(model);
        render(model, poseStack, multiBufferSource, light, stack.hasFoil());
    }

    protected void render(TerraStagedWingModel model, PoseStack matrixStack, MultiBufferSource buffer, int light, boolean hasFoil)
    {
        RenderType     renderType    = model.renderType(getTexture());
        VertexConsumer vertexBuilder = ItemRenderer.getFoilBuffer(buffer, renderType, false, hasFoil);
        model.renderToBuffer(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }
}
