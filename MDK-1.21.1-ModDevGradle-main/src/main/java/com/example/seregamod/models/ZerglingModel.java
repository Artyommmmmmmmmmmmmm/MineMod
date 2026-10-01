package com.example.seregamod.models;

import com.example.seregamod.SeregaMod;
import com.example.seregamod.entity.custom.DraggerEntity;
import com.example.seregamod.entity.custom.ZerglingEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ZerglingModel extends GeoModel<ZerglingEntity> {

    @Override
    public ResourceLocation getModelResource(ZerglingEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID, "geo/zergling.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ZerglingEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID, "textures/entity/zergling.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ZerglingEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID, "animations/zergling.animation.json");
    }
}