package com.anbaric.terra_reforged.items.models;

import com.anbaric.terra_reforged.util.handlers.LayerHandler;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nonnull;

public class TerraFlyingCarpetModel<T extends LivingEntity> extends AgeableListModel<T>
{
    public final ModelPart carpet_base;
    public final ModelPart front_tassle_1;
    public final ModelPart front_tassle_2;
    public final ModelPart front_tassle_3;
    public final ModelPart front_tassle_4;
    public final ModelPart front_tassle_5;
    public final ModelPart front_tassle_6;
    public final ModelPart front_tassle_7;
    public final ModelPart front_tassle_8;
    public final ModelPart front_tassle_9;
    public final ModelPart front_tassle_10;
    public final ModelPart front_tassle_11;
    public final ModelPart front_tassle_12;
    public final ModelPart front_tassle_13;
    public final ModelPart front_tassle_14;
    public final ModelPart front_tassle_15;
    public final ModelPart front_tassle_16;
    public final ModelPart front_tassle_17;
    public final ModelPart front_tassle_18;
    public final ModelPart front_tassle_19;
    public final ModelPart front_tassle_20;
    public final ModelPart back_tassle_1;
    public final ModelPart back_tassle_2;
    public final ModelPart back_tassle_3;
    public final ModelPart back_tassle_4;
    public final ModelPart back_tassle_5;
    public final ModelPart back_tassle_6;
    public final ModelPart back_tassle_7;
    public final ModelPart back_tassle_8;
    public final ModelPart back_tassle_9;
    public final ModelPart back_tassle_10;
    public final ModelPart back_tassle_11;
    public final ModelPart back_tassle_12;
    public final ModelPart back_tassle_13;
    public final ModelPart back_tassle_14;
    public final ModelPart back_tassle_15;
    public final ModelPart back_tassle_16;
    public final ModelPart back_tassle_17;
    public final ModelPart back_tassle_18;
    public final ModelPart back_tassle_19;
    public final ModelPart back_tassle_20;

    public TerraFlyingCarpetModel(ModelPart carpetModel)
    {
        this.carpet_base = carpetModel.getChild("carpet_base");
        this.front_tassle_1 = carpetModel.getChild("carpet_base").getChild("front_tassle_1");
        this.front_tassle_2 = carpetModel.getChild("carpet_base").getChild("front_tassle_2");
        this.front_tassle_3 = carpetModel.getChild("carpet_base").getChild("front_tassle_3");
        this.front_tassle_4 = carpetModel.getChild("carpet_base").getChild("front_tassle_4");
        this.front_tassle_5 = carpetModel.getChild("carpet_base").getChild("front_tassle_5");
        this.front_tassle_6 = carpetModel.getChild("carpet_base").getChild("front_tassle_6");
        this.front_tassle_7 = carpetModel.getChild("carpet_base").getChild("front_tassle_7");
        this.front_tassle_8 = carpetModel.getChild("carpet_base").getChild("front_tassle_8");
        this.front_tassle_9 = carpetModel.getChild("carpet_base").getChild("front_tassle_9");
        this.front_tassle_10 = carpetModel.getChild("carpet_base").getChild("front_tassle_10");
        this.front_tassle_11 = carpetModel.getChild("carpet_base").getChild("front_tassle_11");
        this.front_tassle_12 = carpetModel.getChild("carpet_base").getChild("front_tassle_12");
        this.front_tassle_13 = carpetModel.getChild("carpet_base").getChild("front_tassle_13");
        this.front_tassle_14 = carpetModel.getChild("carpet_base").getChild("front_tassle_14");
        this.front_tassle_15 = carpetModel.getChild("carpet_base").getChild("front_tassle_15");
        this.front_tassle_16 = carpetModel.getChild("carpet_base").getChild("front_tassle_16");
        this.front_tassle_17 = carpetModel.getChild("carpet_base").getChild("front_tassle_17");
        this.front_tassle_18 = carpetModel.getChild("carpet_base").getChild("front_tassle_18");
        this.front_tassle_19 = carpetModel.getChild("carpet_base").getChild("front_tassle_19");
        this.front_tassle_20 = carpetModel.getChild("carpet_base").getChild("front_tassle_20");
        this.back_tassle_1 = carpetModel.getChild("carpet_base").getChild("back_tassle_1");
        this.back_tassle_2 = carpetModel.getChild("carpet_base").getChild("back_tassle_2");
        this.back_tassle_3 = carpetModel.getChild("carpet_base").getChild("back_tassle_3");
        this.back_tassle_4 = carpetModel.getChild("carpet_base").getChild("back_tassle_4");
        this.back_tassle_5 = carpetModel.getChild("carpet_base").getChild("back_tassle_5");
        this.back_tassle_6 = carpetModel.getChild("carpet_base").getChild("back_tassle_6");
        this.back_tassle_7 = carpetModel.getChild("carpet_base").getChild("back_tassle_7");
        this.back_tassle_8 = carpetModel.getChild("carpet_base").getChild("back_tassle_8");
        this.back_tassle_9 = carpetModel.getChild("carpet_base").getChild("back_tassle_9");
        this.back_tassle_10 = carpetModel.getChild("carpet_base").getChild("back_tassle_10");
        this.back_tassle_11 = carpetModel.getChild("carpet_base").getChild("back_tassle_11");
        this.back_tassle_12 = carpetModel.getChild("carpet_base").getChild("back_tassle_12");
        this.back_tassle_13 = carpetModel.getChild("carpet_base").getChild("back_tassle_13");
        this.back_tassle_14 = carpetModel.getChild("carpet_base").getChild("back_tassle_14");
        this.back_tassle_15 = carpetModel.getChild("carpet_base").getChild("back_tassle_15");
        this.back_tassle_16 = carpetModel.getChild("carpet_base").getChild("back_tassle_16");
        this.back_tassle_17 = carpetModel.getChild("carpet_base").getChild("back_tassle_17");
        this.back_tassle_18 = carpetModel.getChild("carpet_base").getChild("back_tassle_18");
        this.back_tassle_19 = carpetModel.getChild("carpet_base").getChild("back_tassle_19");
        this.back_tassle_20 = carpetModel.getChild("carpet_base").getChild("back_tassle_20");
    }

    public static TerraFlyingCarpetModel createFlyingCarpet()
    {
        return new TerraFlyingCarpetModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(LayerHandler.FLYING_CARPET));
    }

    @Override
    @Nonnull
    protected Iterable<ModelPart> headParts()
    {
        return ImmutableList.of();
    }

    @Override
    @Nonnull
    protected Iterable<ModelPart> bodyParts()
    {
        return ImmutableList.of(this.carpet_base, this.front_tassle_1, this.front_tassle_2, this.front_tassle_3, this.front_tassle_4, this.front_tassle_5, this.front_tassle_6, this.front_tassle_7, this.front_tassle_8, this.front_tassle_9, this.front_tassle_10, this.front_tassle_11, this.front_tassle_12, this.front_tassle_13, this.front_tassle_14, this.front_tassle_15, this.front_tassle_16, this.front_tassle_17, this.front_tassle_18, this.front_tassle_19, this.front_tassle_20, this.back_tassle_1, this.back_tassle_2, this.back_tassle_3, this.back_tassle_4, this.back_tassle_5, this.back_tassle_6, this.back_tassle_7, this.back_tassle_8, this.back_tassle_9, this.back_tassle_10, this.back_tassle_11, this.back_tassle_12, this.back_tassle_13, this.back_tassle_14, this.back_tassle_15, this.back_tassle_16, this.back_tassle_17, this.back_tassle_18, this.back_tassle_19, this.back_tassle_20);
    }

    public static LayerDefinition createCarpetLayer()
    {
        MeshDefinition mesh = new MeshDefinition();

        mesh.getRoot().addOrReplaceChild("carpet_base", CubeListBuilder.create().texOffs(0, 0).addBox(-10, 0, -15, 20, 1, 30), PartPose.offset(0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("front_tassle_1", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(9.5F, 0.5F, -15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("front_tassle_2", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(8.5F, 0.5F, -15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("front_tassle_3", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(7.5F, 0.5F, -15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("front_tassle_4", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(6.5F, 0.5F, -15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("front_tassle_5", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(5.5F, 0.5F, -15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("front_tassle_6", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(4.5F, 0.5F, -15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("front_tassle_7", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(3.5F, 0.5F, -15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("front_tassle_8", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(2.5F, 0.5F, -15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("front_tassle_9", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(1.5F, 0.5F, -15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("front_tassle_10", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(0.5F, 0.5F, -15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("front_tassle_11", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-0.5F, 0.5F, -15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("front_tassle_12", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-1.5F, 0.5F, -15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("front_tassle_13", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-2.5F, 0.5F, -15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("front_tassle_14", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-3.5F, 0.5F, -15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("front_tassle_15", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-4.5F, 0.5F, -15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("front_tassle_16", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-5.5F, 0.5F, -15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("front_tassle_17", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-6.5F, 0.5F, -15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("front_tassle_18", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-7.5F, 0.5F, -15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("front_tassle_19", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-8.5F, 0.5F, -15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("front_tassle_20", CubeListBuilder.create().texOffs(120, 0).addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-9.5F, 0.5F, -15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("back_tassle_1", CubeListBuilder.create().texOffs(112, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(9.5F, 0.5F, 15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("back_tassle_2", CubeListBuilder.create().texOffs(112, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(8.5F, 0.5F, 15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("back_tassle_3", CubeListBuilder.create().texOffs(112, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(7.5F, 0.5F, 15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("back_tassle_4", CubeListBuilder.create().texOffs(112, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(6.5F, 0.5F, 15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("back_tassle_5", CubeListBuilder.create().texOffs(112, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(5.5F, 0.5F, 15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("back_tassle_6", CubeListBuilder.create().texOffs(112, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(4.5F, 0.5F, 15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("back_tassle_7", CubeListBuilder.create().texOffs(112, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(3.5F, 0.5F, 15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("back_tassle_8", CubeListBuilder.create().texOffs(112, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(2.5F, 0.5F, 15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("back_tassle_9", CubeListBuilder.create().texOffs(112, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(1.5F, 0.5F, 15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("back_tassle_10", CubeListBuilder.create().texOffs(112, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(0.5F, 0.5F, 15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("back_tassle_11", CubeListBuilder.create().texOffs(112, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-0.5F, 0.5F, 15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("back_tassle_12", CubeListBuilder.create().texOffs(112, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-1.5F, 0.5F, 15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("back_tassle_13", CubeListBuilder.create().texOffs(112, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-2.5F, 0.5F, 15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("back_tassle_14", CubeListBuilder.create().texOffs(112, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-3.5F, 0.5F, 15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("back_tassle_15", CubeListBuilder.create().texOffs(112, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-4.5F, 0.5F, 15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("back_tassle_16", CubeListBuilder.create().texOffs(112, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-5.5F, 0.5F, 15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("back_tassle_17", CubeListBuilder.create().texOffs(112, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-6.5F, 0.5F, 15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("back_tassle_18", CubeListBuilder.create().texOffs(112, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-7.5F, 0.5F, 15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("back_tassle_19", CubeListBuilder.create().texOffs(112, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-8.5F, 0.5F, 15.5F, -0, 0, 0));
        mesh.getRoot().getChild("carpet_base").addOrReplaceChild("back_tassle_20", CubeListBuilder.create().texOffs(112, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, new CubeDeformation(0)), PartPose.offsetAndRotation(-9.5F, 0.5F, 15.5F, -0, 0, 0));

        return LayerDefinition.create(mesh, 128, 32);
    }

    @Override
    public void setupAnim(LivingEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch)
    {
        if (pEntity instanceof AbstractClientPlayer)
        {
            AbstractClientPlayer abstractclientplayer = (AbstractClientPlayer) pEntity;
            float clientOffset = 0.0F;

            this.front_tassle_1.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.front_tassle_20.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.front_tassle_2.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.front_tassle_19.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.front_tassle_3.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.front_tassle_18.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.front_tassle_4.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.front_tassle_17.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.front_tassle_5.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.front_tassle_16.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.front_tassle_6.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.front_tassle_15.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.front_tassle_7.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.front_tassle_14.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.front_tassle_8.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.front_tassle_13.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.front_tassle_9.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.front_tassle_12.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.front_tassle_10.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.front_tassle_11.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.back_tassle_1.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.back_tassle_20.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.back_tassle_2.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.back_tassle_19.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.back_tassle_3.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.back_tassle_18.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.back_tassle_4.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.back_tassle_17.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.back_tassle_5.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.back_tassle_16.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.back_tassle_6.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.back_tassle_15.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.back_tassle_7.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.back_tassle_14.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.back_tassle_8.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.back_tassle_13.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.back_tassle_9.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.back_tassle_12.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.back_tassle_10.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+=1F/4F)) / (8F/5F);
            this.back_tassle_11.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (clientOffset+1F/4F)) / (8F/5F);
        }
        else
        {
            float serveroffset = 0.0F;

            this.front_tassle_1.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.front_tassle_20.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.front_tassle_2.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.front_tassle_19.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.front_tassle_3.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.front_tassle_18.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.front_tassle_4.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.front_tassle_17.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.front_tassle_5.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.front_tassle_16.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.front_tassle_6.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.front_tassle_15.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.front_tassle_7.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.front_tassle_14.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.front_tassle_8.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.front_tassle_13.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.front_tassle_9.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.front_tassle_12.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.front_tassle_10.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.front_tassle_11.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.back_tassle_1.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.back_tassle_20.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.back_tassle_2.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.back_tassle_19.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.back_tassle_3.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.back_tassle_18.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.back_tassle_4.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.back_tassle_17.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.back_tassle_5.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.back_tassle_16.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.back_tassle_6.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.back_tassle_15.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.back_tassle_7.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.back_tassle_14.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.back_tassle_8.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.back_tassle_13.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.back_tassle_9.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.back_tassle_12.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.back_tassle_10.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+=1F/4F)) / (8F/5F);
            this.back_tassle_11.xRot = (float) Math.sin((pAgeInTicks / 20F) * 2*Math.PI + (serveroffset+1F/4F)) / (8F/5F);
        }
    }
}
