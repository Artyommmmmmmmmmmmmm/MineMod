package com.example.seregamod.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;

public class CustomTntItem extends PrimedTnt {
    public CustomTntItem(Level level, double x, double y, double z,
                         LivingEntity owner) {
        super(level, x, y, z, owner);
    }

    @Override
    protected void explode() {
        Level level = this.level();
        PrimedTnt tnt1 = new PrimedTnt(
                level,
                this.getX() + 0.5,
                this.getY(),
                this.getZ() + 0.5,
                null
        );
        PrimedTnt tnt2 = new PrimedTnt(
                level,
                this.getX() + 0.5,
                this.getY(),
                this.getZ() + 0.5,
                null
        );
        PrimedTnt tnt3 = new PrimedTnt(
                level,
                this.getX() + 0.5,
                this.getY(),
                this.getZ() + 0.5,
                null
        );
        PrimedTnt tnt4 = new PrimedTnt(
                level,
                this.getX() + 0.5,
                this.getY(),
                this.getZ() + 0.5,
                null
        );
        tnt1.setDeltaMovement(0.6, 0.5, 0.6);
        tnt2.setDeltaMovement(-0.6, 0.5, 0.6);
        tnt3.setDeltaMovement(0.6, 0.5, -0.6);
        tnt4.setDeltaMovement(-0.6, 0.5, -0.6);
        tnt1.setFuse(40);
        tnt2.setFuse(40);
        tnt3.setFuse(40);
        tnt4.setFuse(40);
        level.addFreshEntity(tnt1);
        level.addFreshEntity(tnt2);
        level.addFreshEntity(tnt3);
        level.addFreshEntity(tnt4);
    }
}
