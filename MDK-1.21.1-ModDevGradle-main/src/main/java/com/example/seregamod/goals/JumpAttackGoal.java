package com.example.seregamod.goals;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class JumpAttackGoal extends Goal {

    protected final Mob mob;
    protected int timer;
    protected int phase;
    protected Vec3 direction;
    protected  Vec3 adjustment;

    public JumpAttackGoal(Mob mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP));
        this.adjustment = new Vec3(0, 0.3, 0);
        this.timer = 5;
        this.phase = 1;
    }

    @Override
    public boolean canUse() {
        if (timer > 0) {
            timer--;
            return false;
        }

        LivingEntity target = mob.getTarget();
        if (target == null) return false;

        double distance = mob.distanceTo(target);
        return mob.hasLineOfSight(target)
                && distance > 1.5
                && distance < 13.0;
    }

    @Override
    public void start() {
        this.mob.getNavigation().stop();
        super.start();
    }

    @Override
    public void tick() {
        if (this.timer != 0) {
            this.timer--;
            return;
        }

        LivingEntity target = this.mob.getTarget();
        mob.getLookControl().setLookAt(target, 30f, 30f);
        switch (this.phase) {
            case 1:
                double x = target.getX() - this.mob.getX();
                double y = target.getY() - this.mob.getY();
                double z = target.getZ() - this.mob.getZ();
                double distance = mob.distanceTo(target);
                this.direction = new Vec3(x, y, z).normalize();
                this.mob.setDeltaMovement(direction.add(this.adjustment).scale(Math.log(distance)*0.85));
                this.mob.hurtMarked = true;
                this.mob.hasImpulse = true;
                this.phase = 2;
                this.timer = 0;
//                mob.setTarget(null);
                return;
            case 2:
                if (mob.onGround() || mob.distanceTo(target) < 1.5) {
                    AABB searchArea = this.mob.getBoundingBox().inflate(2).expandTowards(direction.scale(4));
                    for (Entity entity : this.mob.level().getEntities(this.mob, searchArea)) {
                        entity.teleportTo(entity.getX(), entity.getY()+0.4, entity.getZ());
                        entity.setOnGround(false);
                        entity.setDeltaMovement(this.direction.add(0, 0.2, 0).scale(3));
                        entity.hurtMarked = true;
                        entity.hasImpulse = true;
                    }
                    this.mob.playSound(SoundEvents.MACE_SMASH_GROUND_HEAVY, 1.0F, 1.0F);
                    this.timer = 60;
                    this.phase = 1;
                    return;
                }

        }
        super.tick();
    }
}
