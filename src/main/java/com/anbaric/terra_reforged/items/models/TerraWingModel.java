package com.anbaric.terra_reforged.items.models;

import com.anbaric.terra_reforged.util.handlers.LayerHandler;
import com.anbaric.terra_reforged.util.handlers.RenderHandler;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.util.function.Function;

public class TerraWingModel extends HumanoidModel<LivingEntity>
{
    public final ModelPart rightWing = body.getChild("right_wing");
    public final ModelPart leftWing = body.getChild("left_wing");

    public TerraWingModel(ModelPart part, Function<ResourceLocation, RenderType> renderType, float xOffset, float zOffset, float rotation)
    {
        super(part, renderType);
    }

    protected Iterable<ModelPart> headParts()
    {
        return ImmutableList.of();
    }

    protected Iterable<ModelPart> bodyParts()
    {
        return ImmutableList.of(this.leftWing, this.rightWing);
    }

    public static TerraWingModel createFledgelingWings()
    {
        return new TerraWingModel(RenderHandler.bakeLayer(LayerHandler.FLEDGELING_WINGS), RenderType::entityCutoutNoCull, 3, -3, -0.5F)
        {
            @Override
            public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
                float f = 0.2617994F;
                float f1 = -0.2617994F;
                float f2 = 0.0F;
                float f3 = 0.0F;
                if (entity.isFallFlying()) {
                    float f4 = 1.0F;
                    Vec3 vec3 = entity.getDeltaMovement();
                    if (vec3.y < 0.0D) {
                        Vec3 vec31 = vec3.normalize();
                        f4 = 1.0F - (float)Math.pow(-vec31.y, 1.5D);
                    }

                    f = f4 * 0.34906584F + (1.0F - f4) * f;
                    f1 = f4 * (-(float)Math.PI / 2F) + (1.0F - f4) * f1;
                } else if (entity.isCrouching()) {
                    f = 0.6981317F;
                    f1 = (-(float)Math.PI / 4F);
                    f2 = 3.0F;
                    f3 = 0.08726646F;
                }

                this.leftWing.y = f2;
                if (entity instanceof AbstractClientPlayer) {
                    AbstractClientPlayer abstractclientplayer = (AbstractClientPlayer)entity;
                    abstractclientplayer.elytraRotX += (f - abstractclientplayer.elytraRotX) * 0.1F;
                    abstractclientplayer.elytraRotY += (f3 - abstractclientplayer.elytraRotY) * 0.1F;
                    abstractclientplayer.elytraRotZ += (f1 - abstractclientplayer.elytraRotZ) * 0.1F;
                    this.leftWing.xRot = abstractclientplayer.elytraRotX;
                    this.leftWing.yRot = abstractclientplayer.elytraRotY;
                    this.leftWing.zRot = abstractclientplayer.elytraRotZ;
                } else {
                    this.leftWing.xRot = f;
                    this.leftWing.zRot = f1;
                    this.leftWing.yRot = f3;
                }

                this.rightWing.yRot = -this.leftWing.yRot;
                this.rightWing.y = this.leftWing.y;
                this.rightWing.xRot = this.leftWing.xRot;
                this.rightWing.zRot = -this.leftWing.zRot;
            }
        };
    }

    public static MeshDefinition createWing()
    {
        CubeDeformation cubedeformation = new CubeDeformation(1.0F);
        MeshDefinition mesh = new MeshDefinition();

        mesh.getRoot().addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(22, 0).addBox(-10.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, cubedeformation), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.2617994F, 0.0F, -0.2617994F));
        mesh.getRoot().addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(22, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, cubedeformation), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.2617994F, 0.0F, 0.2617994F));
        return mesh;
    }
}