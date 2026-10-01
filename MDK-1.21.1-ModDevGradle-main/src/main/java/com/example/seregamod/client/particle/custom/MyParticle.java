package com.example.seregamod.client.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class MyParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    protected MyParticle(ClientLevel level, double x, double y, double z, SpriteSet sprites) {
        super(level, x, y, z);
        this.sprites = sprites;
        this.gravity = 0.0F; // Ваша частица не будет падать
        this.lifetime = 20;  // Время жизни (в тиках)
        this.setSpriteFromAge(sprites); // Установить начальную текстуру
    }

    @Override
    public void tick() {
        super.tick();
        // Меняем текстуру по мере "старения" частицы
        this.setSpriteFromAge(this.sprites);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT; // Тип рендера (для полупрозрачности)
    }

    // Провайдер, который создаёт экземпляр частицы
    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {
            return new MyParticle(level, x, y, z, this.sprites);
        }
    }
}