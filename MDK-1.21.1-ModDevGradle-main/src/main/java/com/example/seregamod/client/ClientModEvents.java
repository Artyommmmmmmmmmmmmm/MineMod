package com.example.seregamod.client;

import com.example.seregamod.client.renderer.*;
import com.example.seregamod.entity.ModEntities;
import com.example.seregamod.entity.custom.DraggerEntity;
import com.example.seregamod.entity.custom.RopeNodeEntity;
import com.example.seregamod.entity.custom.SlasherEntity;
import com.example.seregamod.entity.custom.StomperEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = "seregamod", value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void RegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
                ModEntities.GRAPLE_ENTITY.get(),
                GrapleRenderer::new
        );
        event.registerEntityRenderer(
                ModEntities.DRAGGER_ENTITY.get(),
                DraggerRenderer::new
        );
        event.registerEntityRenderer(
                ModEntities.SLASHER_ENTITY.get(),
                SlasherRenderer::new
        );
        event.registerEntityRenderer(
                ModEntities.ROPE_GRAPLE_ENTITY.get(),
                RopeGrapleRenderer::new
        );
        event.registerEntityRenderer(
                ModEntities.ROPE_NODE_ENTITY.get(),
                RopeNodeRenderer::new
        );
        event.registerEntityRenderer(
                ModEntities.ZERGLING_ENTITY.get(),
                ZerglingRenderer::new
        );
        event.registerEntityRenderer(
                ModEntities.STOMPER_ENTITY.get(),
                StomperRenderer::new
        );
        event.registerEntityRenderer(
                ModEntities.FORWARDER_ENTITY.get(),
                ForwarderRenderer::new
        );
    }

}
