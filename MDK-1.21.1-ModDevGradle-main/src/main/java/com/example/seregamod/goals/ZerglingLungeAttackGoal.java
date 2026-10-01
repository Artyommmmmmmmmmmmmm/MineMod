package com.example.seregamod.goals;

import com.example.seregamod.client.fx.BoneBoundEffectExecutor;
import com.example.seregamod.entity.custom.ZerglingEntity;
import com.lowdragmc.photon.client.fx.FX;
import com.lowdragmc.photon.client.fx.FXHelper;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.util.EnumSet;

public class ZerglingLungeAttackGoal extends Goal {
    private final ZerglingEntity mob;
    private double cooldown = 0;
    private double delay;
    private final double distance = 5;
    private final double damage = 5;
    private final int lungeDamageDelay = 4;
    private int lungeDamageTimer = lungeDamageDelay;

    public ZerglingLungeAttackGoal(ZerglingEntity mob) {
        this. mob = mob;
        this.delay = 10;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (this.mob.getTarget() != null ) {
            if(!this.mob.hasLineOfSight(this.mob.getTarget())) {
                return false;
            }
            if (this.delay == 0 & this.cooldown == 0) {
                this.delay = 10;
                return true;
            }
            if (GoalUtility.isInRange(this.mob, 0, distance) && delay != 0) {
                this.delay--;
            }
        }

        if (this.cooldown != 0) {
            this.cooldown--;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return this.cooldown == 0;
    }

    @Override
    public void tick() {
        if (this.mob.getTarget() != null) {
            if (this.lungeDamageTimer == this.lungeDamageDelay) {
                this.mob.setLungeActive(true);

                double x = this.mob.getTarget().getX() - this.mob.getX();
                double y = this.mob.getTarget().getY() - this.mob.getY();
                double z = this.mob.getTarget().getZ() - this.mob.getZ();
                this.mob.setDeltaMovement(new Vec3(x, y, z).scale(0.33));
            }

            this.lungeDamageTimer--;

            if (this.lungeDamageTimer == 0) {
                this.mob.setLungeActive(false);

                AABB box = this.mob.getBoundingBox().inflate(1).expandTowards(this.mob.getLookAngle().scale(3));
                if(this.mob instanceof ZerglingEntity) {
                    this.mob.playAttackAnimation();
                }
                double x = this.mob.getX() - this.mob.getTarget().getX();
                double y = this.mob.getY() - this.mob.getTarget().getY();
                double z = this.mob.getZ() - this.mob.getTarget().getZ();
                this.mob.setOnGround(false);
                this.mob.setDeltaMovement(new Vec3(x, y, z).scale(0.5));
                for (Entity mob : this.mob.level().getEntities(this.mob, box)) {
                    if (mob instanceof LivingEntity) {
                        if (!mob.getTags().contains("hivemind")) {
                            mob.hurt(this.mob.damageSources().mobAttack(this.mob), (float) this.damage);
                        }
                    }
                }
                this.cooldown = 20;
                this.lungeDamageTimer = this.lungeDamageDelay;
            }
        }
        super.tick();
    }

}
