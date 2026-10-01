package com.example.seregamod.models;

import com.example.seregamod.SeregaMod;
import com.example.seregamod.entity.custom.DraggerEntity;
import com.example.seregamod.entity.custom.SlasherEntity;
import com.example.seregamod.entity.custom.StomperEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class StomperModel extends GeoModel<StomperEntity> {
    @Override
    public ResourceLocation getModelResource(StomperEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID, "geo/stomper.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StomperEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID, "textures/entity/stomper.png");
    }

    @Override
    public ResourceLocation getAnimationResource(StomperEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID, "animations/stomper.animation.json");
    }
}
