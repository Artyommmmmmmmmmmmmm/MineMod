package com.example.seregamod.models;

import com.example.seregamod.SeregaMod;
import com.example.seregamod.entity.custom.DraggerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DraggerModel extends GeoModel<DraggerEntity> {
    @Override
    public ResourceLocation getModelResource(DraggerEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID, "geo/dragger.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DraggerEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID, "textures/entity/dragger.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DraggerEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID, "animations/dragger.animation.json");
    }
}