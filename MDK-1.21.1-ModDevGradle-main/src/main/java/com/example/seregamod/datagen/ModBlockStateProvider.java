package com.example.seregamod.datagen;

import com.example.seregamod.SeregaMod;
import com.example.seregamod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, SeregaMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SPREADER_BLOCK);
        blockWithItem(ModBlocks.TUMOR_BLOCK);

        blockWithItem(ModBlocks.SEREGA_BLOCK);
        stairsBlock(ModBlocks.SPREADER_STAIRS.get(), blockTexture(ModBlocks.SPREADER_BLOCK.get()));
        slabBlock(ModBlocks.SPREADER_SLAB.get(), blockTexture(ModBlocks.SPREADER_BLOCK.get()), blockTexture(ModBlocks.SPREADER_BLOCK.get()));
        buttonBlock(ModBlocks.SPREADER_BUTTON.get(), blockTexture(ModBlocks.SPREADER_BLOCK.get()));
        pressurePlateBlock(ModBlocks.SPREADER_PRESSURE_PLATE.get(), blockTexture(ModBlocks.SPREADER_BLOCK.get()));
        fenceBlock(ModBlocks.SPREADER_FENCE .get(), blockTexture(ModBlocks.SPREADER_BLOCK.get()));
        fenceGateBlock(ModBlocks.SPREADER_FENCE_GATE.get(), blockTexture(ModBlocks.SPREADER_BLOCK.get()));
        wallBlock(ModBlocks.SPREADER_WALL.get(), blockTexture(ModBlocks.SPREADER_BLOCK.get()));
        doorBlockWithRenderType(ModBlocks.SPREADER_DOOR.get(), modLoc("block/spreader_door_bottom"),
                modLoc("block/spreader_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.SPREADER_TRAPDOOR.get(), modLoc("block/spreader_trapdoor"),
                true, "cutout");
        blockItem(ModBlocks.SPREADER_STAIRS);
        blockItem(ModBlocks.SPREADER_SLAB);
        blockItem(ModBlocks.SPREADER_FENCE_GATE);
        blockItem(ModBlocks.SPREADER_PRESSURE_PLATE);
        blockItem(ModBlocks.SPREADER_TRAPDOOR, "_bottom");
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("seregamod:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("seregamod:block/" + deferredBlock.getId().getPath() + appendix));
    }
}