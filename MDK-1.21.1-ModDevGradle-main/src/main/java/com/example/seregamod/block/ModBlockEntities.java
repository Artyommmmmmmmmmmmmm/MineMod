package com.example.seregamod.block;

import com.example.seregamod.block.ModBlocks;
import com.example.seregamod.block.entity.SpreaderBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, "seregamod");

    public static final Supplier<BlockEntityType<SpreaderBlockEntity>> SPREADER_BE =
            BLOCK_ENTITIES.register("spreader_be",
                    () -> BlockEntityType.Builder.of(
                            SpreaderBlockEntity::new,
                            ModBlocks.SPREADER_BLOCK.get()
                    ).build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}