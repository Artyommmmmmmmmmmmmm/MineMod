package com.example.seregamod.models;

import com.example.seregamod.SeregaMod;
import com.example.seregamod.entity.custom.DraggerEntity;
import com.example.seregamod.entity.custom.SlasherEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SlasherModel extends GeoModel<SlasherEntity> {
    @Override
    public ResourceLocation getModelResource(SlasherEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID, "geo/slasher.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SlasherEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID, "textures/entity/slasher.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SlasherEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID, "animations/slasher.animation.json");
    }
}
