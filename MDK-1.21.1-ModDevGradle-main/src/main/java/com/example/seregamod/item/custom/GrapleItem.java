package com.example.seregamod.item.custom;

import com.example.seregamod.entity.ModEntities;
import com.example.seregamod.entity.custom.GrapleEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class GrapleItem extends Item {
    GrapleEntity grapleBond;

    public GrapleItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {

        if (!level.isClientSide()) {
            if (grapleBond != null) {
                this.grapleBond.discard();
                this.grapleBond = null;
            } else{
                GrapleEntity graple = new GrapleEntity(
                        ModEntities.GRAPLE_ENTITY.get(),
                        level
                );

                this.grapleBond = graple;
                graple.setPlayer(player);
                graple.setPos(player.getEyePosition());
                graple.setDeltaMovement(player.getLookAngle().scale(0.5));
                graple.hurtMarked = true;
                level.addFreshEntity(graple);
            }
        }

        return super.use(level, player, usedHand);
    }

}
