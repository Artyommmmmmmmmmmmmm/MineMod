package com.example.seregamod.item.eggs;

import com.example.seregamod.entity.ModEntities;
import com.example.seregamod.entity.custom.DraggerEntity;
import com.example.seregamod.entity.custom.SlasherEntity;
import com.example.seregamod.entity.custom.ZerglingEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

import java.security.cert.TrustAnchor;

public class ZerglingEgg extends Item {
    public ZerglingEgg(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!context.getLevel().isClientSide()) {
            ZerglingEntity zergling = new ZerglingEntity(
                    ModEntities.ZERGLING_ENTITY.get(),
                    context.getLevel()
            );
            zergling.setPos(context.getClickLocation());
            zergling.hurtMarked = true;
            context.getLevel().addFreshEntity(zergling);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.SUCCESS;
    }
}
