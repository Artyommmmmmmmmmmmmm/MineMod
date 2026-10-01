package com.example.seregamod.item.eggs;

import com.example.seregamod.entity.ModEntities;
import com.example.seregamod.entity.custom.DraggerEntity;
import com.example.seregamod.entity.custom.ForwarderEntity;
import com.example.seregamod.entity.custom.SlasherEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

import java.security.cert.TrustAnchor;

public class ForwarderEgg extends Item {
    public ForwarderEgg(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!context.getLevel().isClientSide()) {
            ForwarderEntity forwarder = new ForwarderEntity(
                    ModEntities.FORWARDER_ENTITY.get(),
                    context.getLevel()
            );
            forwarder.setPos(context.getClickLocation());
            forwarder.hurtMarked = true;
            context.getLevel().addFreshEntity(forwarder);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.SUCCESS;
    }
}
