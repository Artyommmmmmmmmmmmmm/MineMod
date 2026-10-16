package com.example.seregamod.entity.custom;

import com.example.seregamod.client.fx.BoneBoundEffectExecutor;
import com.example.seregamod.goals.DigToTargetGoal;
import com.example.seregamod.goals.DirectionalAttackAimGoal;
import com.example.seregamod.goals.FollowGoal;
import com.example.seregamod.goals.ZerglingLungeAttackGoal;
import com.lowdragmc.photon.client.fx.FX;
import com.lowdragmc.photon.client.fx.FXHelper;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ZerglingEntity extends Monster implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private Vec3 lastPos = Vec3.ZERO;

    public Vec3 leftClawPos;
    public Vec3 rightClawPos;
    private FX fx;
    private BoneBoundEffectExecutor effectExecutorLeft;
    private BoneBoundEffectExecutor effectExecutorRight;
    private double xr;
    private double yr;
    private double zr;
    private double xl;
    private double yl;
    private double zl;
    private double particleTimer = 5;
    private static final EntityDataAccessor<Boolean> DATA_LUNGE_ACTIVE =
            SynchedEntityData.defineId(ZerglingEntity.class, EntityDataSerializers.BOOLEAN);
//    private Vec3 swordTip;
//    private final FX fx = FXHelper.getFX(ResourceLocation.parse("seregamod:lasertrail"));
//    private BoneBoundEffectExecutor effectExecutor = new BoneBoundEffectExecutor((ClientLevel) level());;
    public ZerglingEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.addTag("hivemind");
        this.fx = FXHelper.getFX(ResourceLocation.parse("seregamod:zergling_slash_fx"));
    }
    public void setLeftClawPos(Vec3 vec) {
        this.leftClawPos = vec;
    }
    public void setRightClawPos(Vec3 vec) {
        this.rightClawPos = vec;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_LUNGE_ACTIVE, false);
    }
    public void setLungeActive(boolean active) {
        this.entityData.set(DATA_LUNGE_ACTIVE, active);
    }

    public boolean isLungeActive() {
        return this.entityData.get(DATA_LUNGE_ACTIVE);
    }



    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(
                this, Player.class, 10,
                false, false, null));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, (double)1.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(
                this));
//        this.goalSelector.addGoal(0, new PullGoal(this));
//        this.goalSelector.addGoal(0, new JumpAttackGoal(this));
        this.goalSelector.addGoal(2, new ZerglingLungeAttackGoal(this));
        this.goalSelector.addGoal(2, new DigToTargetGoal(this, 2, 0, 1, 0));
        this.goalSelector.addGoal(3, new DirectionalAttackAimGoal(this, 5));
        this.goalSelector.addGoal(4, new FollowGoal(this, 2f, true));
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

        double movement = this.position().distanceToSqr(this.lastPos);

            if (this.level().isClientSide()) {
                if (this.isLungeActive()) {
                    if (this.leftClawPos != null && this.rightClawPos != null) {
                        this.xr = this.rightClawPos.x();
                        this.yr = this.rightClawPos.y();
                        this.zr = this.rightClawPos.z();
                        this.xl = this.leftClawPos.x();
                        this.yl = this.leftClawPos.y();
                        this.zl = this.leftClawPos.z();
                    }
                    if (this.effectExecutorLeft == null) {
                        this.effectExecutorLeft = new BoneBoundEffectExecutor((ClientLevel) this.level());
                        this.effectExecutorRight = new BoneBoundEffectExecutor((ClientLevel) this.level());
                        this.effectExecutorRight.start(this.fx);
                        this.effectExecutorLeft.start(this.fx);

                    }
                    if (effectExecutorLeft != null) {
                        if (this.effectExecutorLeft.getShouldStop()) {
                            this.particleTimer = 7;
                            this.effectExecutorRight.setShouldStop(false);
                            this.effectExecutorLeft.setShouldStop(false);
                            this.effectExecutorRight.start(this.fx);
                            this.effectExecutorLeft.start(this.fx);
                        }
                        if (this.xr != 0) {
                            this.effectExecutorRight.setPos(new Vector3f((float) xr, (float) yr, (float) zr));
                            this.effectExecutorLeft.setPos(new Vector3f((float) xl, (float) yl, (float) zl));
                        }
                    }
                } else {
                    if (effectExecutorLeft != null && this.xr != 0) {
                        if (this.leftClawPos != null && this.rightClawPos != null) {
                            this.xr = this.rightClawPos.x();
                            this.yr = this.rightClawPos.y();
                            this.zr = this.rightClawPos.z();
                            this.xl = this.leftClawPos.x();
                            this.yl = this.leftClawPos.y();
                            this.zl = this.leftClawPos.z();
                        }
                        this.effectExecutorRight.setPos(new Vector3f((float) xr, (float) yr, (float) zr));
                        this.effectExecutorLeft.setPos(new Vector3f((float) xl, (float) yl, (float) zl));
                        if (particleTimer != 0){

                            this.particleTimer--;
                        }
                        if (particleTimer == 0){
                            this.effectExecutorRight.setShouldStop(true);
                            this.effectExecutorLeft.setShouldStop(true);
                        }

                    }
                }
            }


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

        this.lastPos = this.position();


        super.tick();
    }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        // Контроллер 1: IDLE (простой) — играет всегда
        controllers.add(new AnimationController<>(this, "idle", 5, state -> {
            return state.setAndContinue(RawAnimation.begin().thenLoop("idle"));
        }));

        // Контроллер 2: attack (атака) — запускается вручную
        controllers.add(new AnimationController<>(this, "walk", 5, state -> {
            if (state.isMoving()) {
                return state.setAndContinue(RawAnimation.begin().thenLoop("walk"));
            }
            return PlayState.STOP;
        }));
        controllers.add(new AnimationController<>(this, "attack", 0, state -> PlayState.STOP)
                .triggerableAnim("attack_lunge", RawAnimation.begin().thenPlay("attack_lunge"))
        );
    }

    // Метод для запуска анимации атаки
    public void playAttackAnimation() {
        this.stopTriggeredAnim("attack", "attack_lunge");
        this.triggerAnim("attack", "attack_lunge");
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
