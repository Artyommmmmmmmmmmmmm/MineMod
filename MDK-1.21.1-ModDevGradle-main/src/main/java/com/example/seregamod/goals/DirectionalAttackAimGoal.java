package com.example.seregamod.goals;

import java.util.EnumSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BowItem;

public class DirectionalAttackAimGoal<T extends Mob> extends Goal {
    private final T mob;
    private double distance;

    public DirectionalAttackAimGoal(T mob, double distance) {
        this.mob = mob;
        this.distance = distance;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public boolean canUse() {
        if (this.mob.getTarget() != null) {
            double distance = this.mob.position().distanceTo(this.mob.getTarget().position());
            return distance < this.distance;
        }
        return false;
    }

    public boolean canContinueToUse() {
        if (this.mob.getTarget() != null) {
            double distance = this.mob.position().distanceTo(this.mob.getTarget().position());
            return distance < this.distance;
        }
        return false;
    }

    public void start() {
        super.start();
        this.mob.setAggressive(true);
    }

    public void stop() {
        super.stop();
        this.mob.setAggressive(false);
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity != null) {
            this.mob.getMoveControl().strafe((float) 0.05, (float) 0.0);
            this.mob.lookAt(livingentity, 30.0F, 30.0F);
        }
    }
}
