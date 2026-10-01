package com.example.seregamod.util;

import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

public class TestRenders {

    public static void drawParticleLine(ServerLevel level, Vec3 start, Vec3 end, ParticleOptions particle, int count) {
        Vec3 delta = end.subtract(start);

        for (int i = 0; i <= count; i++) {
            double t = (double) i / count;
            Vec3 point = start.add(delta.scale(t));

            level.sendParticles(
                    particle,
                    point.x, point.y, point.z,
                    1,      // количество
                    0, 0, 0, // разброс
                    0        // скорость
            );
        }
    }
}
