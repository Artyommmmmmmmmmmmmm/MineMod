package com.example.seregamod.block.entity;

import com.example.seregamod.block.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class SpreaderBlockEntity extends BlockEntity {

    // Единственный блок, который хранит BlockEntity
    @Nullable
    private BlockPos storedBlockPos = null;

    public SpreaderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SPREADER_BE.get(), pos, state);
    }

    // ========== Геттеры и сеттеры ==========
    @Nullable
    public BlockPos getStoredBlock() {
        return storedBlockPos;
    }

    public void setStoredBlock(@Nullable BlockPos state) {
        this.storedBlockPos = state;
        setChanged(); // Пометить, что данные изменились (для сохранения)

        // Отправить обновление клиенту (если нужно)
        if (level != null && !level.isClientSide()) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (storedBlockPos != null) {
            tag.putLong("StoredBlockPos", storedBlockPos.asLong());
        }
    }

    // ЗАГРУЗКА
    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("StoredBlockPos")) {
            storedBlockPos = BlockPos.of(tag.getLong("StoredBlockPos"));
        } else {
            storedBlockPos = null;
        }
    }

}