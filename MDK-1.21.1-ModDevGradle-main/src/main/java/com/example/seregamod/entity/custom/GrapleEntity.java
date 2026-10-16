package com.example.seregamod.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.checkerframework.checker.units.qual.N;

public class GrapleEntity extends Entity {
    private Vec3 fixedPosition;
    private Player draggedPlayer;

    public GrapleEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public void setPlayer(Player player) {
        draggedPlayer = player;
        draggedPlayer.hurtMarked = true;
        draggedPlayer.hasImpulse = true;
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
        if (draggedPlayer != null && fixedPosition != null) {
//            draggedPlayer.setDeltaMovement(fixedPosition.subtract(draggedPlayer.getEyePosition())
//                    .scale(draggedPlayer.getEyePosition().distanceTo(fixedPosition)*0.025)
//            );
//            if (draggedPlayer.getDeltaMovement().length() > 3) {
//                draggedPlayer.setDeltaMovement(draggedPlayer.getDeltaMovement().normalize().scale(2));
//            }
            if (this.distanceTo(draggedPlayer) > 3) {
                draggedPlayer.setDeltaMovement(this.position().subtract(draggedPlayer.position()));
                System.out.println(draggedPlayer.getDeltaMovement().normalize().dot(draggedPlayer.getLookAngle()));
//                Vec3 direction = draggedPlayer.position().subtract(this.position()).normalize();
//                double projection = draggedPlayer.getDeltaMovement().dot(direction);
//                if (projection > 0.2) {
//                    double maxProjection = Math.min(projection, draggedPlayer.getDeltaMovement().length());
//                    draggedPlayer.setDeltaMovement(draggedPlayer.getDeltaMovement().subtract(direction.scale(maxProjection)));
//                    System.out .println(projection);
//                }
                draggedPlayer.hurtMarked = true;
            } else {
                draggedPlayer.setDeltaMovement(Vec3.ZERO);
            }
//            draggedPlayer.hurtMarked = true;
//            draggedPlayer.hasImpulse = true;
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
