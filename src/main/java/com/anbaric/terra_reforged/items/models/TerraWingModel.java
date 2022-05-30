package com.anbaric.terra_reforged.items.models;

import com.anbaric.terra_reforged.util.handlers.LayerHandler;
import com.anbaric.terra_reforged.util.handlers.RenderHandler;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.util.function.Function;

public class TerraWingModel extends HumanoidModel<LivingEntity>
{
    public final ModelPart rightWing = body.getChild("right_wing");
    public final ModelPart leftWing = body.getChild("left_wing");

    public TerraWingModel(ModelPart part, Function<ResourceLocation, RenderType> renderType)
    {
        super(part, renderType);
    }

    @Override
    protected Iterable<ModelPart> headParts()
    {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelPart> bodyParts()
    {
        return ImmutableList.of(body);
    }

    public static TerraWingModel createFledgelingWing()
    {
        return new TerraWingModel(RenderHandler.bakeLayer(LayerHandler.FLEDGELING_WINGS), RenderType::entityTranslucent);
    }

    private static MeshDefinition createWing(CubeListBuilder rightWing, CubeListBuilder leftWing)
    {
        MeshDefinition mesh = createMesh(CubeDeformation.NONE, 0);

        mesh.getRoot().getChild("body").addOrReplaceChild("left_wing", leftWing, PartPose.offsetAndRotation(5, 0, 0, 0.25F, 0, -0.25F));
        mesh.getRoot().getChild("body").addOrReplaceChild("right_wing", rightWing, PartPose.offsetAndRotation(-5, 0, 0, 0.25F, 0, 0.25F));

        return mesh;
    }

    public static MeshDefinition createWingModel() {
        CubeListBuilder rightWing = CubeListBuilder.create();
        CubeListBuilder leftWing = CubeListBuilder.create();

        //Right Wing
        rightWing.texOffs(22, 0);
        rightWing.mirror();
        rightWing.addBox(0, 0, 2, 10, 20, 2);

        //Left Wing
        leftWing.texOffs(22, 0);
        leftWing.addBox(-10, 0, 2, 10, 20, 2);

        return createWing(rightWing, leftWing);
    }

    @Override
    public void setupAnim(LivingEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        float f = 0.2617994F;
        float f1 = -0.2617994F;
        float f2 = 0;
        float f3 = 0;
        if (pEntity.isFallFlying()) {
            float f4   = 1;
            Vec3  vec3 = pEntity.getDeltaMovement();
            if (vec3.y < 0.0D) {
                Vec3 vec31 = vec3.normalize();
                f4 = 1 - (float)Math.pow(-vec31.y, 1.5D);
            }

            f = f4 * 0.34906584F + (1 - f4) * f;
            f1 = f4 * (-(float)Math.PI / 2F) + (1 - f4) * f1;
        } else if (pEntity.isCrouching()) {
            f = 0.6981317F;
            f1 = (-(float)Math.PI / 4F);
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