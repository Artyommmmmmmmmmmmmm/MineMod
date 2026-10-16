package com.example.seregamod.goals;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.phys.Vec3;

public class GoalUtility {

    public static boolean isInRange(Monster mob, double distanceMin, double distanceMax) {
     return (mob.position().distanceTo(mob.getTarget().position()) < distanceMax && mob.position().distanceTo(mob.getTarget().position()) > distanceMin);
    }
    public static boolean isTargetInFront(Mob mob, Entity target,
                                   double range, double maxSideDistance) {
        // Горизонтальное направление взгляда
        Vec3 look = mob.getLookAngle();
        Vec3 lookH = new Vec3(look.x, 0, look.z).normalize();

        // Вектор к цели (тоже горизонтальный)
        Vec3 toTarget = target.position().subtract(mob.position());
        Vec3 toTargetH = new Vec3(toTarget.x, 0, toTarget.z);

        // Расстояние вдоль взгляда
        double forward = toTargetH.dot(lookH);
        if (forward < 0 || forward > range) return false;

        // Перпендикулярное отклонение (расстояние до линии взгляда)
        Vec3 perpendicular = toTargetH.subtract(lookH.scale(forward));
        double sideDistance = perpendicular.length();

        return sideDistance <= maxSideDistance;
    }
//    if (mob.onGround() && mob instanceof Player) {
//        Vec3 velocity = mob.getDeltaMovement();
//        mob.setDeltaMovement(mob.getDeltaMovement().add(velocity.x*1.83, velocity.y*0.4, velocity.z*1.83));
//    }

}
