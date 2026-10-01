package com.example.seregamod.client.renderer;

import com.example.seregamod.entity.custom.DraggerEntity;
import com.example.seregamod.entity.custom.ForwarderEntity;
import com.example.seregamod.entity.custom.SlasherEntity;
import com.example.seregamod.models.DraggerModel;
import com.example.seregamod.models.ForwarderModel;
import com.example.seregamod.models.SlasherModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import java.util.Optional;

public class ForwarderRenderer extends GeoEntityRenderer<ForwarderEntity> {
    public ForwarderRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ForwarderModel()); // Ссылка на модель
    }

    @Override
    public void renderRecursively(PoseStack poseStack, ForwarderEntity animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }
}