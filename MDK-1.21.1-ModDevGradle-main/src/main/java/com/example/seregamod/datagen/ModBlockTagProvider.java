package com.example.seregamod.datagen;

import com.example.seregamod.SeregaMod;
import com.example.seregamod.block.ModBlocks;
import com.example.seregamod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SeregaMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
//        tag(BlockTags.MINEABLE_WITH_PICKAXE)
//                .add(ModBlocks.BISMUTH_BLOCK.get())
//                .add(ModBlocks.BISMUTH_ORE.get())
//                .add(ModBlocks.BISMUTH_DEEPSLATE_ORE.get());
//
//        tag(BlockTags.NEEDS_IRON_TOOL)
//                .add(ModBlocks.BISMUTH_DEEPSLATE_ORE.get());
        tag(ModTags.Blocks.SATURN_TAG)
                .add(ModBlocks.SPREADER_BUTTON.get())
                .add(ModBlocks.SPREADER_DOOR.get())
                .add(ModBlocks.SPREADER_FENCE.get())
                .add(ModBlocks.SPREADER_PRESSURE_PLATE.get())
                .add(ModBlocks.SPREADER_FENCE_GATE.get())
                .add(ModBlocks.SPREADER_SLAB.get())
                .add(ModBlocks.SPREADER_STAIRS.get())
                .add(ModBlocks.SPREADER_TRAPDOOR.get())
                .add(ModBlocks.SPREADER_WALL.get());

        tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.SEREGA_BLOCK.get());

        tag(BlockTags.FENCES).add(ModBlocks.SPREADER_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.SPREADER_FENCE_GATE.get());
        tag(BlockTags.WALLS).add(ModBlocks.SPREADER_WALL.get());

        tag(ModTags.Blocks.NEEDS_SEREGA_TOOL).addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_SEREGA_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(BlockTags.NEEDS_IRON_TOOL);

    }
}