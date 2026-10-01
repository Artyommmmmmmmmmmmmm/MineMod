package com.example.seregamod.client.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(Registries.PARTICLE_TYPE, "seregamod");

    public static final Supplier<SimpleParticleType> SMASHER_PARTICLE =
            PARTICLE_TYPES.register("my_particle",
                    () -> new SimpleParticleType(false)
            );

    public static void register(IEventBus modBus) {
        PARTICLE_TYPES.register(modBus);
    }
}