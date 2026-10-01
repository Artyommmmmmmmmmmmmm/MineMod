package com.example.seregamod.entity.custom;

import com.example.seregamod.goals.DirectionalAttackAimGoal;
import com.example.seregamod.goals.FollowGoal;
import com.example.seregamod.goals.StomperStompAttackGoal;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class StomperEntity extends Monster implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    //    private Vec3 swordTip;
//    private final FX fx = FXHelper.getFX(ResourceLocation.parse("seregamod:lasertrail"));
//    private BoneBoundEffectExecutor effectExecutor = new BoneBoundEffectExecutor((ClientLevel) level());;
    public StomperEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.addTag("hivemind");
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(
                this, Player.class, 10,
                false, false, null));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new StomperStompAttackGoal(this));
        this.goalSelector.addGoal(3, new DirectionalAttackAimGoal(this, 10));
        this.goalSelector.addGoal(4, new FollowGoal(this, 1f, true));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, (double)1.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(
                this));
        super.registerGoals();

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.23F)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ARMOR, 10.0);
    }

    @Override
    public void tick() {

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
                .triggerableAnim("stomp_attack", RawAnimation.begin().thenPlay("stomp_attack"))
        );
    }

    // Метод для запуска анимации атаки
    public void playAttackAnimation() {
        this.stopTriggeredAnim("attack", "stomp_attack");
        this.triggerAnim("attack", "stomp_attack");
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
