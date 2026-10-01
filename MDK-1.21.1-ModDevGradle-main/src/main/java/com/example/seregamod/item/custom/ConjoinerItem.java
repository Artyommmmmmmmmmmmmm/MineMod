package com.example.seregamod.item.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ConjoinerItem extends Item {
    Vec3 grabPosition;
    Vec3 desiredPosition;
    public ConjoinerItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        System.out.println("useOn");
        Level level = context.getLevel();
        Player player = context.getPlayer();
        this.grabPosition = context.getClickLocation();
        context.getPlayer().startUsingItem(context.getHand());
        return super.useOn(context);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public boolean useOnRelease(ItemStack stack) {
        System.out.println("RELEASE");
        return super.useOnRelease(stack);
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        System.out.println("TICK");

        if (level instanceof ServerLevel serverLevel && livingEntity instanceof Player player) {
            this.desiredPosition = grabPosition.add(player.getLookAngle().scale(-2));
            serverLevel.sendParticles(
                    ParticleTypes.CHERRY_LEAVES,
                    grabPosition.x, grabPosition.y, grabPosition.z,
                    2,
                    0.1, 0.1, 0.1,
                    0.1
            );
            if (!level.isClientSide()) {
                player.setDeltaMovement(desiredPosition.subtract(player.getEyePosition())
                        .scale(player.getEyePosition().distanceTo(desiredPosition)*0.1)
                );
                if (player.getDeltaMovement().length() > 1) {
                    player.setDeltaMovement(player.getDeltaMovement().normalize());
                }
                player.hurtMarked = true;
                player.hasImpulse = true;
                System.out.println(player.getDeltaMovement().length());
            }
        }
        super.onUseTick(level, livingEntity, stack, remainingUseDuration);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.seregamod.conjoiner"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
