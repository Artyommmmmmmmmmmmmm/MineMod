package com.example.seregamod.client.fx;

import com.lowdragmc.photon.client.fx.FX;
import com.lowdragmc.photon.client.fx.FXRuntime;
import com.lowdragmc.photon.client.fx.IEffectExecutor;
import com.lowdragmc.photon.client.gameobject.IFXObject;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public final class BoneBoundEffectExecutor implements IEffectExecutor {
    private final ClientLevel level;
    private final RandomSource random = RandomSource.create(1234L);
    private FXRuntime runtime;
    private Vector3f pos;
    private boolean shouldStop = false;

    public BoneBoundEffectExecutor(ClientLevel level) {
        this.level = level;
    }

    @Override public Level getLevel() { return level; }
    @Override public RandomSource getRandomSource() { return random; }

    @Override
    public void updateFXObjectTick(IFXObject object) {
        if (runtime != null && object == runtime.root && shouldStop()) {
            runtime.destroy(false);
        }
    }

    private boolean shouldStop() {
        return this.shouldStop;
    }

    public void setPos(Vector3f vec) {
        this.pos = vec;
    }
    public void setShouldStop(boolean stop) {
        this.shouldStop = stop;
    }
    public boolean getShouldStop() {
        return this.shouldStop;
    }

    @Override
    public void updateFXObjectFrame(IFXObject object, float partialTicks) {
        if (runtime != null && object == runtime.root && pos != null) {
            runtime.root.updatePos(pos);
        }
    }

    @Override
    public void onTimelineSignal(String channel, String name,
                                 CompoundTag data, double time) {
//        handleSignal(channel, name, data);
    }

    public void start(FX fx) {
        runtime = fx.createRuntime();
        runtime.emit(this);
    }
}