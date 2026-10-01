package com.example.seregamod.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import static java.lang.Math.max;

public class TestNodeEntity extends Entity {
    private TestNodeEntity link;
    public TestNodeEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
        this.noCulling = false;
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

    protected Vec3 getDirection(Entity entity) {
        double x = entity.getX() - this.getX();
        double y = entity.getY() - this.getY();
        double z = entity.getZ() - this.getZ();
        Vec3 direction = new Vec3 (x, y, z);
        return direction.normalize();
    }


    /**
     * Устанавливает связь между сущностями.
     */
    public void linkTo(TestNodeEntity link) {
        this.link = link;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide()) {
            if (!this.onGround()){
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.8f, 0));
                System.out.println("GRAvITY");
            }

            if (this.distanceToSqr(link) > 4) {
                System.out.println("physics");
                this.setDeltaMovement(
                        this.getDeltaMovement()
                        .subtract(
                        (link.getDirection(this)
                        .scale(max(this.getDeltaMovement().dot(link.getDirection(this)), 0)))
                        )
                );
            }
            System.out.println("WORK");
            this.move(
                    MoverType.SELF,
                    this.getDeltaMovement()
            );

        }
    }
}
