package com.example.seregamod.client.renderer;

import com.example.seregamod.entity.custom.DraggerEntity;
import com.example.seregamod.entity.custom.SlasherEntity;
import com.example.seregamod.entity.custom.StomperEntity;
import com.example.seregamod.models.DraggerModel;
import com.example.seregamod.models.SlasherModel;
import com.example.seregamod.models.StomperModel;
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

public class StomperRenderer extends GeoEntityRenderer<StomperEntity> {
    public StomperRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new StomperModel()); // Ссылка на модель
    }
}