package com.example.seregamod.item;

import com.example.seregamod.SeregaMod;
import com.example.seregamod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.awt.*;
import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SeregaMod.MODID);

    public static final Supplier<CreativeModeTab> MODDED_TAB = CREATIVE_MODE_TAB.register("modded_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PISKA.get()))
            .title(Component.translatable("creativetab.seregamod.modded_tab"))
            .displayItems((ItemDisplayParameters, output) -> {
                output.accept(ModItems.PISKA);
                output.accept(ModBlocks.SEREGA_BLOCK);
                output.accept(ModItems.TNTCASTER);

                output.accept(ModBlocks.SPREADER_BLOCK);
                output.accept(ModBlocks.TUMOR_BLOCK);

                output.accept(ModItems.SEREGA_COCKTAIL);
                output.accept(ModItems.GRAPLE);
                output.accept(ModItems.CONJOINER);
                output.accept(ModBlocks.SPREADER_WALL);
                output.accept(ModBlocks.SPREADER_FENCE);
                output.accept(ModBlocks.SPREADER_TRAPDOOR);
                output.accept(ModBlocks.SPREADER_STAIRS);
                output.accept(ModBlocks.SPREADER_DOOR);
                output.accept(ModBlocks.SPREADER_BUTTON);
                output.accept(ModBlocks.SPREADER_PRESSURE_PLATE);
                output.accept(ModBlocks.SPREADER_SLAB);
                output.accept(ModBlocks.SPREADER_FENCE_GATE);
                output.accept(ModItems.SEREGA_HOE);
                output.accept(ModItems.SEREGA_AXE);
                output.accept(ModItems.SEREGA_PICKAXE);
                output.accept(ModItems.SEREGA_SHOVEL);
                output.accept(ModItems.SEREGA_SWORD);

                output.accept(ModItems.DRAGGER_EGG);
                output.accept(ModItems.SLASHER_EGG);
                output.accept(ModItems.ZERGLING_EGG);
                output.accept(ModItems.STOMPER_EGG);
                output.accept(ModItems.FORWARDER_EGG);

                output.accept(ModItems.ROPE_ITEM);

            })
            .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
