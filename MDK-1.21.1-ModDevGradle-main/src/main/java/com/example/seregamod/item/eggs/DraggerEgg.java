package com.example.seregamod.item.eggs;

import com.example.seregamod.entity.ModEntities;
import com.example.seregamod.entity.custom.DraggerEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

import java.security.cert.TrustAnchor;

public class DraggerEgg extends Item {
    public DraggerEgg(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!context.getLevel().isClientSide()) {
            DraggerEntity dragger = new DraggerEntity(
                    ModEntities.DRAGGER_ENTITY.get(),
                    context.getLevel()
            );
            dragger.setPos(context.getClickLocation());
            dragger.hurtMarked = true;
            context.getLevel().addFreshEntity(dragger);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.SUCCESS;
    }
}
