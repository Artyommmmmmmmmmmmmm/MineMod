package com.example.seregamod.client.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class SmasherParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    protected SmasherParticle(ClientLevel level, double x, double y, double z,
                              double xSpeed, double ySpeed, double zSpeed,
                              SpriteSet sprites) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.sprites = sprites;

        // Параметры из Snowstorm:
        // "max_lifetime": 2 (секунды) -> 40 тиков
        this.lifetime = 40;

        // "particle_initial_speed": 4
        // Так как мы уже получили скорость через xSpeed/ySpeed/zSpeed из провайдера,
        // мы можем либо использовать их, либо задать свою:
        this.xd = xSpeed * 4; // Умножаем на скорость из Snowstorm
        this.yd = ySpeed * 4;
        this.zd = zSpeed * 4;

        // "linear_drag_coefficient": 1 (аналог friction)
        this.friction = 0.98f; // Базовое трение в Minecraft

        // "size": [0.3, 0.3] -> quadSize
        this.quadSize = 0.3f;

        // Установка начального спрайта для анимации
        this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick() {
        super.tick();

        // Аналог "flipbook" анимации из Snowstorm.
        // В Java Edition мы просто меняем спрайт в зависимости от возраста частицы.
        // spriteSet содержит все кадры из JSON, и setSpriteFromAge будет переключать их.
        this.setSpriteFromAge(this.sprites);
    }

    @Override
    public ParticleRenderType getRenderType() {
        // "material": "particles_alpha" -> PARTICLE_SHEET_TRANSLUCENT
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    // Провайдер, который создаёт частицу
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
            return new SmasherParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
        }
    }
}
