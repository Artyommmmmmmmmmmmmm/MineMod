package com.example.seregamod.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.phys.Vec3;

public class GoalUtility {

    public static boolean isInRange(Monster mob, double distanceMin, double distanceMax) {
     return (mob.position().distanceTo(mob.getTarget().position()) < distanceMax && mob.position().distanceTo(mob.getTarget().position()) > distanceMin);
    }

//    if (mob.onGround() && mob instanceof Player) {
//        Vec3 velocity = mob.getDeltaMovement();
//        mob.setDeltaMovement(mob.getDeltaMovement().add(velocity.x*1.83, velocity.y*0.4, velocity.z*1.83));
//    }

}
