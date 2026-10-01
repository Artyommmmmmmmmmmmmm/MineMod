package com.example.seregamod.datagen;

import com.example.seregamod.SeregaMod;
import com.example.seregamod.block.ModBlocks;
import com.example.seregamod.item.ModItems;
import com.example.seregamod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static java.awt.AWTEventMulticaster.add;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, SeregaMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
//        tag(ModTags.Items.MODDED_TAG)
//                .add(Items.COAL)
//                .add(Items.STICK)
//                .add(Items.COMPASS);
//
        tag(ItemTags.SWORDS).add(ModItems.SEREGA_SWORD.get());
        tag(ItemTags.HOES).add(ModItems.SEREGA_HOE.get());
        tag(ItemTags.PICKAXES).add(ModItems.SEREGA_PICKAXE.get());
        tag(ItemTags.AXES).add(ModItems.SEREGA_AXE.get());
        tag(ItemTags.SHOVELS).add(ModItems.SEREGA_SHOVEL.get());


    }
}