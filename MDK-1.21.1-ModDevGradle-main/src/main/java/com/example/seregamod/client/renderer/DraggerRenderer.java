package com.example.seregamod.client.renderer;

import com.example.seregamod.entity.custom.DraggerEntity;
import com.example.seregamod.models.DraggerModel;
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

public class DraggerRenderer extends GeoEntityRenderer<DraggerEntity> {
    public DraggerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DraggerModel()); // Ссылка на модель
    }

    @Override
    public void renderRecursively(PoseStack poseStack, DraggerEntity animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        Optional<GeoBone> hammerBone = this.getGeoModel().getBone("bubbles");

        if (hammerBone.isPresent() && this.getAnimatable() instanceof DraggerEntity mob) {
            // Получаем мировую позицию кости
            Vector3d worldPos = hammerBone.get().getWorldPosition();

            // Сохраняем её в мобе для использования в tick()
            mob.setHammerWorldPos(new Vec3(worldPos.x, worldPos.y, worldPos.z));
        }
        super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }
}