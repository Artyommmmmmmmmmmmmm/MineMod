package com.example.seregamod.item.eggs;

import com.example.seregamod.entity.ModEntities;
import com.example.seregamod.entity.custom.DraggerEntity;
import com.example.seregamod.entity.custom.SlasherEntity;
import com.example.seregamod.entity.custom.StomperEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

import java.security.cert.TrustAnchor;

public class StomperEgg extends Item {
    public StomperEgg(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!context.getLevel().isClientSide()) {
            StomperEntity stomper = new StomperEntity(
                    ModEntities.STOMPER_ENTITY.get(),
                    context.getLevel()
            );
            stomper.setPos(context.getClickLocation());
            stomper.hurtMarked = true;
            context.getLevel().addFreshEntity(stomper);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.SUCCESS;
    }
}
