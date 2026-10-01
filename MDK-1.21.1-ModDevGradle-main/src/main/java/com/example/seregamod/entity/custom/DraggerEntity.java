package com.example.seregamod.entity.custom;

import com.example.seregamod.client.fx.BoneBoundEffectExecutor;
import com.example.seregamod.client.particle.ModParticles;
import com.example.seregamod.goals.JumpAttackGoal;
import com.example.seregamod.goals.PullGoal;
import com.lowdragmc.photon.client.fx.BlockEffectExecutor;
import com.lowdragmc.photon.client.fx.EntityEffectExecutor;
import com.lowdragmc.photon.client.fx.FX;
import com.lowdragmc.photon.client.fx.FXHelper;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class DraggerEntity extends Monster implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private Vec3 hammerWorldPos = null;
    private BoneBoundEffectExecutor effectExecutor;
    public DraggerEntity(EntityType<? extends Monster> entityType, Level level) {

        super(entityType, level);
        this.addTag("hivemind");
    }


    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(
                this, Player.class, 10,
                false, false, null));
        this.goalSelector.addGoal(0, new PullGoal(this));
//        this.goalSelector.addGoal(0, new JumpAttackGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(
                this, 1.0f, true
        ));
        super.registerGoals();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.23F)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ARMOR, 2.0);
    }

    @Override
    public void tick() {
        if (this.getTarget() != null) {
            Path path = this.getNavigation().getPath();
            if (path != null) {
                for (int i = 0; i < path.getNodeCount(); i++) {
                    if (this.level() instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(
                                ParticleTypes.CHERRY_LEAVES,
                                path.getNode(i).x, path.getNode(i).y, path.getNode(i).z,
                                2,
                                0.1, 0.1, 0.1,
                                0.1
                        );
                    }
                }
            }
        }
        if (this.level().isClientSide() && this.hammerWorldPos != null) {
            FX fx = FXHelper.getFX(ResourceLocation.parse("seregamod:simspaceworld"));
            double x = this.hammerWorldPos.x();
            double y = this.hammerWorldPos.y();
            double z = this.hammerWorldPos.z();
//            double x = this.getX();
//            double y = this.getY();
//            double z = this.getZ();

            if (fx != null) {
                System.out.println(x);System.out.println(y);System.out.println(z);
                if (this.effectExecutor == null){
                    this.effectExecutor = new BoneBoundEffectExecutor((ClientLevel) level());
                    effectExecutor.start(fx);
                }

                effectExecutor.setPos(new Vector3f((float) x, (float) y, (float) z));
            }
        }

        super.tick();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        // Контроллер 1: IDLE (простой) — играет всегда
        controllers.add(new AnimationController<>(this, "idle", 5, state -> {
            return state.setAndContinue(RawAnimation.begin().thenLoop("IDLE"));
        }));

        // Контроллер 2: attack (атака) — запускается вручную
        controllers.add(new AnimationController<>(this, "attack", 0, state -> PlayState.STOP)
                .triggerableAnim("attack", RawAnimation.begin().thenPlay("attack"))
                );
    }

    // Метод для запуска анимации атаки
    public void playAttackAnimation() {
        this.stopTriggeredAnim("attack", "attack");
        this.triggerAnim("attack", "attack");
    }

    public void setHammerWorldPos(Vec3 vec) {
        this.hammerWorldPos = vec;
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        this.playAttackAnimation();
        return super.doHurtTarget(target);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
