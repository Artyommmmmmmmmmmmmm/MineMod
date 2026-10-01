package com.example.seregamod.entity;

import com.example.seregamod.SeregaMod;
import com.example.seregamod.entity.ModEntities;
import com.example.seregamod.entity.custom.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = SeregaMod.MODID)
public class ModEntityAttributes {

    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(
                ModEntities.DRAGGER_ENTITY.get(),           // Ваш EntityType
                DraggerEntity.createAttributes().build()    // Построенный AttributeSupplier
        );
        event.put(
                ModEntities.SLASHER_ENTITY.get(),           // Ваш EntityType
                SlasherEntity.createAttributes().build()    // Построенный AttributeSupplier
        );
        event.put(
                ModEntities.ZERGLING_ENTITY.get(),           // Ваш EntityType
                ZerglingEntity.createAttributes().build()    // Построенный AttributeSupplier
        );
        event.put(
                ModEntities.STOMPER_ENTITY.get(),           // Ваш EntityType
                StomperEntity.createAttributes().build()    // Построенный AttributeSupplier
        );
        event.put(
                ModEntities.FORWARDER_ENTITY.get(),           // Ваш EntityType
                ForwarderEntity.createAttributes().build()    // Построенный AttributeSupplier
        );
    }

}