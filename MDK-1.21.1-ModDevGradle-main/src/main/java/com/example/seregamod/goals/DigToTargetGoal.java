package com.example.seregamod.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class DigToTargetGoal extends Goal {
    private Monster mob;
    private Vec3 lastPos = Vec3.ZERO;
    private double cooldown = 40;
    private double height = 2;
    private int widthLeft = 0;
    private int length = 1;
    private int widthRight = 0;

    public DigToTargetGoal(Monster mob, double height, int widthLeft, int length, int widthRight) {
        this.mob = mob;
        this.height = height;
        this.widthLeft = widthLeft;
        this.length = length;
        this.widthRight = widthRight;
    }

    @Override
    public boolean canUse() {
        if (this.cooldown != 0) {
            this.cooldown--;
        }
        double movement = this.mob.position().distanceToSqr(this.lastPos);
        this.lastPos = this.mob.position();
        return this.mob.getTarget() != null && this.mob.getNavigation().isDone() && movement < 0.1
                && !this.mob.hasLineOfSight(this.mob.getTarget()) && this.cooldown == 0;
    }

    @Override
    public boolean canContinueToUse() {
        if (this.cooldown != 0) {
            this.cooldown--;
        }
        return this.cooldown == 0;
    }

    @Override
    public void tick() {
        List<BlockPos> blocks = new ArrayList<>();
        Vec3 direction = this.mob.getTarget().position().subtract(this.mob.getTarget().position()).normalize();
//        AABB area = this.mob.getBoundingBox().inflate(0.1).expandTowards(direction.scale(3));
//        int minX = Mth.floor(area.minX);
//        int minY = Mth.floor(area.minY);
//        int minZ = Mth.floor(area.minZ);
//        int maxX = Mth.floor(area.maxX);
//        int maxY = Mth.floor(area.maxY);
//        int maxZ = Mth.floor(area.maxZ);
//
//        for (int x = minX; x <= maxX; x++) {
//            for (int y = minY; y <= maxY; y++) {
//                for (int z = minZ; z <= maxZ; z++) {
//                    BlockPos pos = new BlockPos(x, y, z);
//                    BlockState state = this.mob.level().getBlockState(pos);
//                    // Фильтруем: только твёрдые блоки, которые можно сломать
//                    if (!state.isAir() && state.isSolid()) {
//                        this.mob.level().destroyBlock(pos, false);
//                        blocks.add(pos);
//                    }
//                }
//            }
//        }
        Vec3 mobPos = this.mob.position();
        Vec3 unroundedDirection = this.mob.getTarget().position().subtract(this.mob.position());
        int x = (int)Math.floor(mobPos.x);
        int y = (int)Math.floor(mobPos.y);
        int z = (int)Math.floor(mobPos.z);
        Direction roundedDirection = Direction.getNearest(unroundedDirection.x, 0, unroundedDirection.z);
        BlockPos standBlock = new BlockPos(x, y, z);
        BlockPos block = new BlockPos(x, y, z).relative(roundedDirection);
        System.out.println(roundedDirection);
        for (int h = 0; h < this.height; h++) {
            for (int w = -this.widthLeft; w <= this.widthRight; w++) {

                if (unroundedDirection.y > 1.1) {
                    int i = 0;
                    for (Direction toJump = roundedDirection; i < 4; toJump = toJump.getClockWise()) {
                        i++;
                        if (this.mob.level().getBlockState(standBlock.relative(toJump)).isAir()) {
                            continue;
                        }
                        int newHeight = h+1;
                        for (int l = -(this.length/2); l <= 0; l++) {
                            BlockPos targetingUpWidth = standBlock.relative(toJump.getClockWise(), w);
                            BlockPos targetingUpHeight = targetingUpWidth.relative(Direction.UP, newHeight);
                            BlockPos targetingUpLong = targetingUpHeight.relative(toJump, l);
                            if (!this.mob.level().getBlockState(targetingUpLong).isAir()) {
                                this.mob.level().destroyBlock(targetingUpLong, false);
                            }
                        }
                        BlockPos targetingWidth = standBlock.relative(toJump).relative(toJump.getClockWise(), w);
                        BlockPos targetingHeight = targetingWidth.relative(Direction.UP, newHeight);
                        if (!this.mob.level().getBlockState(targetingHeight).isAir()) {
                            this.mob.level().destroyBlock(targetingHeight, false);
                            break;
                        }
                    }

                } else if (unroundedDirection.y < -1.1) {
                    int i = 0;
                    for (Direction toJump = roundedDirection; i < 4; toJump = toJump.getClockWise()) {
                        i++;
                        if (this.mob.level().getBlockState(standBlock.relative(toJump)).isAir()) {
                            continue;
                        }
                        int newHeight = h-1;
                        for (int l = -(this.length/2); l <= 0; l++) {
                            BlockPos targetingUpWidth = standBlock.relative(toJump.getClockWise(), w);
                            BlockPos targetingUpHeight = targetingUpWidth.relative(Direction.UP, newHeight);
                            BlockPos targetingUpLong = targetingUpHeight.relative(toJump, l);
                            if (!this.mob.level().getBlockState(targetingUpLong).isAir()) {
                                this.mob.level().destroyBlock(targetingUpLong, false);
                            }
                        }
                        BlockPos targetingWidth = standBlock.relative(toJump).relative(toJump.getClockWise(), w);
                        BlockPos targetingHeight = targetingWidth.relative(Direction.UP, newHeight);
                        if (!this.mob.level().getBlockState(targetingHeight).isAir()) {
                            this.mob.level().destroyBlock(targetingHeight, false);
                            break;
                        }
                    }

                } else {
                    BlockPos targetingWidth = block.relative(roundedDirection.getClockWise(), w);
                    BlockPos targetingHeight = targetingWidth.relative(Direction.UP, h);
                    if (!this.mob.level().getBlockState(targetingHeight).isAir()) {
                        this.mob.level().destroyBlock(targetingHeight, false);
                    }
                }
            }
        }
        if (this.cooldown == 0) {
            this.cooldown = 40;
        }
        super.tick();
    }
}
