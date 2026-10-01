package com.example.seregamod.item.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import net.minecraft.world.level.Level;

import java.util.List;

public class TntCasterItem extends Item {
    public TntCasterItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity interactionTarget, InteractionHand usedHand) {
        Level level = interactionTarget.level();
        if (!level.isClientSide()) {
            BlockPos position = BlockPos.containing(interactionTarget.position());
            level.removeBlock(position, false);

            // Создаем зажженный TNT
            CustomTntItem tnt = new CustomTntItem(
                    level,
                    position.getX() + 0.5,
                    position.getY(),
                    position.getZ() + 0.5,
                    player
            );
            tnt.setFuse(15);
            tnt.setDeltaMovement(0, 0.5, 0);
            level.addFreshEntity(tnt);
            interactionTarget.kill();
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!level.isClientSide()) {
            BlockPos position = context.getClickedPos();
            level.removeBlock(position, false);

            // Создаем зажженный TNT
            CustomTntItem tnt = new CustomTntItem(
                    level,
                    position.getX() + 0.5,
                    position.getY(),
                    position.getZ() + 0.5,
                    context.getPlayer()
            );
            tnt.setFuse(15);
            tnt.setDeltaMovement(0, 0.5, 0);
            level.addFreshEntity(tnt);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.seregamod.tntcaster.shift_down"));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.seregamod.tntcaster"));
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
