package com.example.seregamod.models;

import com.example.seregamod.SeregaMod;
import com.example.seregamod.entity.custom.DraggerEntity;
import com.example.seregamod.entity.custom.ForwarderEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ForwarderModel extends GeoModel<ForwarderEntity> {
    @Override
    public ResourceLocation getModelResource(ForwarderEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID, "geo/forwarder.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ForwarderEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID, "textures/entity/forwarder.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ForwarderEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID, "animations/forwarder.animation.json");
    }
}