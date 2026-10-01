package com.example.seregamod.goals;

import com.example.seregamod.util.TestRenders;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class PullGoal extends Goal {

    protected final Mob mob;

    public PullGoal(Mob mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        if (this.mob.getTarget() != null) {
            return this.mob.hasLineOfSight(this.mob.getTarget()) &&
                    !(this.mob.position().distanceTo(this.mob.getTarget().position()) < 1.5) &&
                    !(this.mob.position().distanceTo(this.mob.getTarget().position()) > 13);
        }
        return false;
    }

    @Override
    public void tick() {
        if (this.mob.getTarget() != null && this.mob.level() instanceof ServerLevel  serverLevel) {
            Vec3 mobPos = this.mob.position().add(0, 1.1, 0);
            Vec3 targetPos = this.mob.getTarget().position().add(0, 1.1, 0);
            TestRenders.drawParticleLine(serverLevel, mobPos, targetPos, ParticleTypes.END_ROD, 10);
            this.mob.getTarget().hurtMarked = true;
            this.mob.getTarget().setDeltaMovement(this.mob.getTarget().getDeltaMovement()
                    .add(new Vec3 (
                            mobPos.x - targetPos.x,
                            mobPos.y - targetPos.y,
                            mobPos.z - targetPos.z
                            ).normalize().scale(0.2)
                    )

            );
        }
        super.tick();
    }
}
