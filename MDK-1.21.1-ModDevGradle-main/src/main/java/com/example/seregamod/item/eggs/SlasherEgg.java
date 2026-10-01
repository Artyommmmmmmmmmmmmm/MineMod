package com.example.seregamod.item.eggs;

import com.example.seregamod.entity.ModEntities;
import com.example.seregamod.entity.custom.DraggerEntity;
import com.example.seregamod.entity.custom.SlasherEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

import java.security.cert.TrustAnchor;

public class SlasherEgg extends Item {
    public SlasherEgg(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!context.getLevel().isClientSide()) {
            SlasherEntity slasher = new SlasherEntity(
                    ModEntities.SLASHER_ENTITY.get(),
                    context.getLevel()
            );
            slasher.setPos(context.getClickLocation());
            slasher.hurtMarked = true;
            context.getLevel().addFreshEntity(slasher);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.SUCCESS;
    }
}
