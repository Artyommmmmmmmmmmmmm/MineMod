package com.example.seregamod.client.renderer;

import com.example.seregamod.entity.custom.GrapleEntity;
import com.example.seregamod.entity.custom.RopeGrapleEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

public class RopeGrapleRenderer extends EntityRenderer<RopeGrapleEntity> {
    private final BlockRenderDispatcher blockRenderer;
    public RopeGrapleRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public ResourceLocation getTextureLocation(RopeGrapleEntity grapleEntity) {
        return null;
    }

    @Override
    public void render(RopeGrapleEntity p_entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();

        // Центрировать блок
        poseStack.translate(-0.5, 0, -0.5);

        // Масштабировать (опционально)
        poseStack.scale(0.5f, 0.5f, 0.5f);

        // Рендерить блок
        this.blockRenderer.renderSingleBlock(
                Blocks.DIAMOND_BLOCK.defaultBlockState(),
                poseStack,
                bufferSource,
                packedLight,
                OverlayTexture.NO_OVERLAY
        );

        poseStack.popPose();
        super.render(p_entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
