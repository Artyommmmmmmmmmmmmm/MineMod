package com.example.seregamod.client.renderer;

import com.example.seregamod.entity.custom.DraggerEntity;
import com.example.seregamod.entity.custom.SlasherEntity;
import com.example.seregamod.entity.custom.ZerglingEntity;
import com.example.seregamod.models.ZerglingModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import java.util.Optional;

public class ZerglingRenderer extends GeoEntityRenderer<ZerglingEntity> {
    public ZerglingRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ZerglingModel()); // Ссылка на модель
    }

    @Override
    public void renderRecursively(PoseStack poseStack, ZerglingEntity animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
        Optional<GeoBone> leftClaw = this.getGeoModel().getBone("left_frontclaw_claw");
        Optional<GeoBone> rightClaw = this.getGeoModel().getBone("right_backclaw_claw");

        if (leftClaw.isPresent() && this.getAnimatable() instanceof ZerglingEntity mob) {
            // Получаем мировую позицию кости
            Vector3d worldPos = leftClaw.get().getWorldPosition();
            // Сохраняем её в мобе для использования в tick()
            mob.setLeftClawPos(new Vec3(worldPos.x, worldPos.y, worldPos.z));
        }
        if (rightClaw.isPresent() && this.getAnimatable() instanceof ZerglingEntity mob) {
            // Получаем мировую позицию кости
            Vector3d worldPos = rightClaw.get().getWorldPosition();

            // Сохраняем её в мобе для использования в tick()
            mob.setRightClawPos(new Vec3(worldPos.x, worldPos.y, worldPos.z));
        }
    }
}