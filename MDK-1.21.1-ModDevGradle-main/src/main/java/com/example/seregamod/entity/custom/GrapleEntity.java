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
            System.out.println((draggedPlayer));
//            draggedPlayer.teleportTo(fixedPosition.x, fixedPosition.y, fixedPosition.z);
            draggedPlayer.setDeltaMovement(fixedPosition.subtract(draggedPlayer.getEyePosition())
                    .scale(draggedPlayer.getEyePosition().distanceTo(fixedPosition)*0.025)
            );
            draggedPlayer.hurtMarked = true;
            draggedPlayer.hasImpulse = true;
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
