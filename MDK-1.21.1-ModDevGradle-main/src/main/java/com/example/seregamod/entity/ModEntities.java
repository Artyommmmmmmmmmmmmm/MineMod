package com.example.seregamod.entity;
import com.example.seregamod.SeregaMod;
import com.example.seregamod.entity.custom.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, SeregaMod.MODID);
    public static final Supplier<EntityType<GrapleEntity>> GRAPLE_ENTITY =
            ENTITY_TYPES.register(
                    "graple_entity",
                    () -> EntityType.Builder
                            .of(
                                    GrapleEntity::new,
                                    MobCategory.MISC
                            )
                            .sized(
                                    0.2F,
                                    0.2F
                            )
                            .build(
                                    "seregamod:graple_entity"
                            )
            );
    public static final Supplier<EntityType<RopeGrapleEntity>> ROPE_GRAPLE_ENTITY =
            ENTITY_TYPES.register(
                    "rope_graple_entity",
                    () -> EntityType.Builder
                            .of(
                                    RopeGrapleEntity::new,
                                    MobCategory.MISC
                            )
                            .sized(
                                    0.2F,
                                    0.2F
                            )
                            .build(
                                    "seregamod:rope_graple_entity"
                            )
            );
    public static final Supplier<EntityType<RopeNodeEntity>> ROPE_NODE_ENTITY =
            ENTITY_TYPES.register(
                    "rope_node_entity",
                    () -> EntityType.Builder
                            .of(
                                    RopeNodeEntity::new,
                                    MobCategory.MISC
                            )
                            .sized(
                                    0.2F,
                                    0.2F
                            )
                            .build(
                                    "seregamod:rope_node_entity"
                            )
            );
    public static final Supplier<EntityType<DraggerEntity>> DRAGGER_ENTITY =
            ENTITY_TYPES.register(
                    "dragger_entity",
                    () -> EntityType.Builder
                            .of(
                                    DraggerEntity::new,
                                    MobCategory.MONSTER
                            )
                            .sized(
                                    0.5F,
                                    1.0F
                            )
                            .clientTrackingRange(20)
                            .build(
                                    "seregamod:dragger_entity"
                            )
            );
    public static final Supplier<EntityType<SlasherEntity>> SLASHER_ENTITY =
            ENTITY_TYPES.register(
                    "slasher_entity",
                    () -> EntityType.Builder
                            .of(
                                    SlasherEntity::new,
                                    MobCategory.MONSTER
                            )
                            .sized(
                                    0.5F,
                                    1.0F
                            )
                            .clientTrackingRange(20)
                            .build(
                                    "seregamod:slasher_entity"
                            )
            );
    public static final Supplier<EntityType<ZerglingEntity>> ZERGLING_ENTITY =
            ENTITY_TYPES.register(
                    "zergling_entity",
                    () -> EntityType.Builder
                            .of(
                                    ZerglingEntity::new,
                                    MobCategory.MONSTER
                            )
                            .sized(
                                    0.7F,
                                    1.0F
                            )
                            .clientTrackingRange(20)
                            .build(
                                    "seregamod:zergling_entity"
                            )
            );
    public static final Supplier<EntityType<ForwarderEntity>> FORWARDER_ENTITY =
            ENTITY_TYPES.register(
                    "forwarder_entity",
                    () -> EntityType.Builder
                            .of(
                                    ForwarderEntity::new,
                                    MobCategory.MONSTER
                            )
                            .sized(
                                    0.7F,
                                    1.0F
                            )
                            .clientTrackingRange(20)
                            .build(
                                    "seregamod:forwarder_entity"
                            )
            );
    public static final Supplier<EntityType<StomperEntity>> STOMPER_ENTITY =
            ENTITY_TYPES.register(
                    "stomper_entity",
                    () -> EntityType.Builder
                            .of(
                                    StomperEntity::new,
                                    MobCategory.MONSTER
                            )
                            .sized(
                                    3F,
                                    2.5F
                            )
                            .clientTrackingRange(20)
                            .build(
                                    "seregamod:stomper_entity"
                            )
            );
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
