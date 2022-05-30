package com.anbaric.terra_reforged.items.models;

import com.anbaric.terra_reforged.items.renders.TerraStagedWingRenderer;
import com.anbaric.terra_reforged.util.handlers.LayerHandler;
import com.anbaric.terra_reforged.util.handlers.RenderHandler;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import cpw.mods.modlauncher.api.IModuleLayerManager;
import net.minecraft.client.model.ElytraModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.util.function.Function;

public class TerraStagedWingModel extends HumanoidModel<LivingEntity>
{
    public final ModelPart rightWing = body.getChild("right_wing");
    public final ModelPart leftWing = body.getChild("left_wing");

    public TerraStagedWingModel(ModelPart WingModel, Function<ResourceLocation, RenderType> renderType)
    {
        super(WingModel, renderType);
    }

    public static TerraStagedWingModel createExtendedAngelWings()
    {
        return new TerraStagedWingModel(RenderHandler.bakeLayer(LayerHandler.ANGEL_WINGS_EXTENDED), RenderType::entityCutoutNoCull);
    }

    public static TerraStagedWingModel createRestingAngelWings()
    {
        return new TerraStagedWingModel(RenderHandler.bakeLayer(LayerHandler.ANGEL_WINGS_RESTING), RenderType::entityCutoutNoCull);
    }

    private static MeshDefinition createWing(CubeListBuilder rightWing, CubeListBuilder leftWing, boolean isExtended)
    {
        MeshDefinition mesh = createMesh(CubeDeformation.NONE, 0);

        PartPose leftWingPose = isExtended ? PartPose.offsetAndRotation(-3, 4, 4, 0, 0.1745F, 0.1745F) : PartPose.offsetAndRotation(-3, 4, 4, 0, -0.0873F, -0.1745F);
        PartPose rightWingPose = isExtended ? PartPose.offsetAndRotation(3, 4, 4, 0, -0.1745F, -0.1745F) : PartPose.offsetAndRotation(3, 4, 4, 0, 0.0873F, 0.1745F);


        mesh.getRoot().getChild("body").addOrReplaceChild("left_wing", leftWing, leftWingPose);
        mesh.getRoot().getChild("body").addOrReplaceChild("right_wing", rightWing, rightWingPose);

        return mesh;
    }

    public static MeshDefinition createExtendedWingModel() {
        CubeListBuilder rightWingExtended = CubeListBuilder.create();
        CubeListBuilder leftWingExtended = CubeListBuilder.create();

        //Right Wing
        rightWingExtended.texOffs(0, 23);
        rightWingExtended.mirror();
        rightWingExtended.addBox(2, -1, -2, 21, 14, 2);

        //Left Wing
        leftWingExtended.texOffs(0, 23);
        leftWingExtended.addBox(-19, -1, -2, 21, 14, 2);

        return createWing(rightWingExtended, leftWingExtended, true);
    }

    public static MeshDefinition createRestingWingModel() {
        CubeListBuilder rightWingResting = CubeListBuilder.create();
        CubeListBuilder leftWingResting = CubeListBuilder.create();

        //Right Wing
        rightWingResting.texOffs(0, 0);
        rightWingResting.mirror();
        rightWingResting.addBox(-2, -1, -2, 13, 20, 2);

        //Left Wing
        leftWingResting.texOffs(0, 0);
        leftWingResting.addBox(-11, -1, -2, 13, 20, 2);

        return createWing(rightWingResting, leftWingResting, false);
    }

    @Override
    public void setupAnim(LivingEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        float f = 0.2618F;
        float f1 = -0.2618F;
        float f2 = 0;
        float f3 = 0;
//        if (pEntity.isFallFlying()) {
//            float f4   = 1;
//            Vec3  vec3 = pEntity.getDeltaMovement();
//            if (vec3.y < 0.0D) {
//                Vec3 vec31 = vec3.normalize();
//                f4 = 1 - (float)Math.pow(-vec31.y, 1.5D);
//            }
//
//            f = f4 * 0.35F + (1 - f4) * f;
//            f1 = f4 * (-(float)Math.PI / 2F) + (1 - f4) * f1;
//        }
//        else
        if (pEntity.isCrouching())
        {
            f = 0.7F;
            f1 = ((float)Math.PI / 12F);
            f2 = 3;
            f3 = 0.08726646F;
        }

        this.leftWing.y = f2;
        if (pEntity instanceof AbstractClientPlayer) {
            AbstractClientPlayer abstractclientplayer = (AbstractClientPlayer)pEntity;
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
}
