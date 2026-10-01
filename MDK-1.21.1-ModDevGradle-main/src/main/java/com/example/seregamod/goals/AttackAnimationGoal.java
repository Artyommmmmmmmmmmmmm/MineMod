package com.example.seregamod.goals;

import com.example.seregamod.entity.custom.ForwarderEntity;
import com.example.seregamod.entity.custom.ZerglingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class AttackAnimationGoal extends Goal {
    private ForwarderEntity mob;
    private boolean canContinue = true;
    private double cooldown = 20;

    public AttackAnimationGoal(ForwarderEntity mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (this.cooldown != 0) {
            this.cooldown--;
        }
        return this.cooldown == 0;
    }

    @Override
    public boolean canContinueToUse() {
        return false;
    }

    @Override
    public void tick() {
     if (this.cooldown == 0) {
         this.mob.playAttackAnimation();
         this.cooldown = 20;
     }
     System.out.println("attack");
     AABB area = this.mob.getBoundingBox().inflate(2).expandTowards(this.mob.getTarget().position().subtract(this.mob.position()).scale(4));
     for (Entity mob : this.mob.level().getEntities(this.mob, area)) {
         mob.hurt(this.mob.damageSources().mobAttack(this.mob), (float) 5);
     }

        super.tick();
    }
}
