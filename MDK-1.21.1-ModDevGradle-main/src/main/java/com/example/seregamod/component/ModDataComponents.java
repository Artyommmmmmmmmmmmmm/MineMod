package com.example.seregamod.component;

import com.example.seregamod.SeregaMod;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class ModDataComponents {

    // Регистрация кастомного компонента данных
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, SeregaMod.MODID);

    // Пример компонента для хранения boolean (включён ли полёт)
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> FLIGHT_ENABLED =
            DATA_COMPONENT_TYPES.register("flight_enabled",
                    () -> DataComponentType.<Boolean>builder()
                            .persistent(Codec.BOOL)  // Для сохранения в NBT
                            .networkSynchronized(ByteBufCodecs.BOOL)  // Для синхронизации с клиентом
                            .build());


    // Регистрация в главном классе мода
    public static void register(IEventBus modEventBus) {
        DATA_COMPONENT_TYPES.register(modEventBus);
    }
}