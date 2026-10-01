package com.example.seregamod.item.custom;

import com.example.seregamod.entity.ModEntities;
import com.example.seregamod.entity.custom.GrapleEntity;
import com.example.seregamod.entity.custom.RopeGrapleEntity;
import com.example.seregamod.entity.custom.RopeNodeEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RopeItem extends Item {

    RopeGrapleEntity grapleBond;
    RopeNodeEntity node1;
    RopeNodeEntity node2;

    public RopeItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {

        if (!level.isClientSide()) {
            if (grapleBond != null) {
                this.grapleBond.discard();
                this.node1.discard();
                this.node2.discard();
                this.grapleBond = null;
                this.node1 = null;
                this.node2 = null;
            } else{
                RopeGrapleEntity graple = new RopeGrapleEntity(
                        ModEntities.ROPE_GRAPLE_ENTITY.get(),
                        level
                );
                RopeNodeEntity ropeNode1 = new RopeNodeEntity(
                        ModEntities.ROPE_NODE_ENTITY.get(),
                        level
                );
                RopeNodeEntity ropeNode2 = new RopeNodeEntity(
                        ModEntities.ROPE_NODE_ENTITY.get(),
                        level
                );


                this.grapleBond = graple;
                this.node1 = ropeNode1;
                this.node2 = ropeNode2;
                graple.setDragged(ropeNode1);
                ropeNode1.setDragged(ropeNode2);
                ropeNode2.setDragged(player);
                graple.setPos(player.getEyePosition());
                ropeNode1.setPos(player.getEyePosition());
                ropeNode2.setPos(player.getEyePosition().add(player.getLookAngle().reverse()));
                graple.setDeltaMovement(player.getLookAngle().scale(0.5));
                graple.hurtMarked = true;
                ropeNode1.hurtMarked = true;
                ropeNode2.hurtMarked = true;
                level.addFreshEntity(graple);
                level.addFreshEntity(ropeNode1);
                level.addFreshEntity(ropeNode2);
            }
        }

        return super.use(level, player, usedHand);
    }
}
