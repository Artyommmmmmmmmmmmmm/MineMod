package com.example.seregamod.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class RopeGrapleEntity extends Entity {

    private Entity draggedEntity;
    private Vec3 fixedPosition;

    public RopeGrapleEntity(EntityType<?> entityType, Level level) {
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


        if (fixedPosition == null) {
//            ClipContext context = new ClipContext(
//                    this.position(),
//                    this.position().add(this.getDeltaMovement()),
//                    ClipContext.Block.COLLIDER,
//                    ClipContext.Fluid.NONE,
//                    this
//            );
            if (this.horizontalCollision || this.verticalCollision){
                fixedPosition = this.position();
                this.setDeltaMovement(Vec3.ZERO);
            } else {
                this.move(MoverType.SELF, this.getDeltaMovement());
            }
        } else {
            this.setPos(fixedPosition);
        }
        if (draggedEntity != null && fixedPosition != null) {
            System.out.println((draggedEntity));
//            draggedPlayer.teleportTo(fixedPosition.x, fixedPosition.y, fixedPosition.z);
            if (fixedPosition.distanceTo(draggedEntity.getEyePosition()) > 3) {
                draggedEntity.setDeltaMovement(fixedPosition.subtract(draggedEntity.getEyePosition())
                        .scale(draggedEntity.getEyePosition().distanceTo(fixedPosition)*0.05)
                );
            }

            draggedEntity.hurtMarked = true;
            draggedEntity.hasImpulse = true;
            draggedEntity.move(MoverType.SELF, draggedEntity.getDeltaMovement());
//            draggedPlayer.setOnGround(false);
//            draggedPlayer.verticalCollision = false;
//                draggedPlayer.setDeltaMovement(new Vec3(0, 4, 0));
//            draggedPlayer.move(MoverType.SELF, draggedPlayer.getDeltaMovement());
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
