package com.example.seregamod.client.particle;

import com.example.seregamod.client.particle.custom.MyParticle;
import com.example.seregamod.client.particle.custom.SmasherParticle;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = "seregamod", value = Dist.CLIENT)
public class ClientParticleEvents {
    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.SMASHER_PARTICLE.get(), SmasherParticle.Provider::new);
    }
}