package com.example.seregamod.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class RopeNodeEntity extends Entity {

    private Entity draggedEntity;

    public RopeNodeEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }
    public void setDragged(Entity entity) {
        draggedEntity = entity;
        entity.hurtMarked = true;
        entity.hasImpulse = true;
    }

    @Override
    public void tick() {
        super.tick();
        if (draggedEntity != null) {
            System.out.println((draggedEntity));
            Vec3 draggedPos = draggedEntity.getEyePosition();
            Vec3 draggerPos = this.getEyePosition();

//            draggedPlayer.teleportTo(fixedPosition.x, fixedPosition.y, fixedPosition.z);


            this.setDeltaMovement(draggedPos.subtract(draggerPos)
                    .scale(draggerPos.distanceTo(draggedPos)*0.025)
            );
            if (draggerPos.distanceTo(draggedPos) > 3) {
                draggedEntity.setDeltaMovement(draggerPos.subtract(draggedPos)
                        .scale(draggedPos.distanceTo(draggerPos)*0.05)
                );
            }
            draggedEntity.hurtMarked = true;
            draggedEntity.hasImpulse = true;
            this.hurtMarked = true;
            this.hasImpulse = true;
            this.move(MoverType.SELF, this.getDeltaMovement());
        }
    }
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {

    }
}
