package com.example.seregamod.goals;

import com.example.seregamod.entity.custom.StomperEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class StomperStompAttackGoal extends Goal {
    private StomperEntity mob;
    private final double maxDistance = 10;
    private final double width = 2.5;
    private double cooldown = 40;
    private final double damage = 10;
    private final double stompAnimationDuration = 10;
    private double stompTimer = stompAnimationDuration;


    public StomperStompAttackGoal(StomperEntity mob) {
        this.mob = mob;
    }

    @Override
    public boolean canUse() {
        if (this.cooldown != 0) {
            this.cooldown--;
        }
        if (this.mob.getTarget() != null) {
            if (!this.mob.hasLineOfSight(this.mob.getTarget())) {
                return false;
            }

            if (GoalUtility.isInRange(this.mob, 0, maxDistance) && this.cooldown == 0) {
                this.mob.playAttackAnimation();
                return true;
            };

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
            if (this.stompTimer != 0) {
                this.stompTimer--;
            }

            if (stompTimer == 0) {
                this.mob.playSound(SoundEvents.MACE_SMASH_GROUND_HEAVY, 1.0F, 1.0F);
                Vec3 direction = this.mob.getTarget().position().subtract(this.mob.position()).normalize();
                AABB area = this.mob.getBoundingBox().inflate(2).expandTowards(direction.scale(10));
                for (Entity mob: this.mob.level().getEntities(this.mob, area)) {
                    if (GoalUtility.isTargetInFront(this.mob, mob, maxDistance, width)) {

                        if (!mob.getTags().contains("hivemind")) {
                            mob.hurt(this.mob.damageSources().mobAttack(this.mob), (float) this.damage);
                        }
                        if (mob instanceof Player) {
                            ((Player) mob).stopUsingItem();
                            for (ItemStack stack : ((LivingEntity) mob).getHandSlots()) {
                                ((Player) mob).getCooldowns().addCooldown(stack.getItem(), 30);
                            }
                        }
                        mob.setDeltaMovement(mob.getDeltaMovement().add(this.mob.getLookAngle().add(0, 0.4, 0).scale(2)));
                        if (mob.onGround() && mob instanceof Player) {
                            Vec3 velocity = mob.getDeltaMovement();
                            mob.setDeltaMovement(mob.getDeltaMovement().add(velocity.x*1.83, velocity.y*0.4, velocity.z*1.83));
                        }
                        mob.hurtMarked = true;
                    }

                }
                this.cooldown = 40;
                this.stompTimer = this.stompAnimationDuration;

            }


        }

        super.tick();
    }
}
