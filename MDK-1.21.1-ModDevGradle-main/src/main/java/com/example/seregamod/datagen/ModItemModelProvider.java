package com.example.seregamod.datagen;

import com.example.seregamod.SeregaMod;
import com.example.seregamod.block.ModBlocks;
import com.example.seregamod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SeregaMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
//        basicItem(ModItems.EXTRALEG.get());
        basicItem(ModItems.SEREGA_COCKTAIL.get());
        basicItem(ModItems.PISKA.get());
        basicItem(ModItems.TNTCASTER.get());
        basicItem(ModItems.CONJOINER.get());
        basicItem(ModItems.GRAPLE.get());

        buttonItem(ModBlocks.SPREADER_BUTTON, ModBlocks.SPREADER_BLOCK);
        fenceItem(ModBlocks.SPREADER_FENCE, ModBlocks.SPREADER_BLOCK);
        wallItem(ModBlocks.SPREADER_WALL, ModBlocks.SPREADER_BLOCK);

        basicItem(ModBlocks.SPREADER_DOOR.asItem());
        handheldItem(ModItems.SEREGA_AXE.asItem());
        handheldItem(ModItems.SEREGA_HOE.asItem());
        handheldItem(ModItems.SEREGA_PICKAXE.asItem());
        handheldItem(ModItems.SEREGA_SWORD.asItem());
        handheldItem(ModItems.SEREGA_SHOVEL.asItem());
    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID,
                        "block/" + baseBlock.getId().getPath()));
    }
    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID,"item/" + item.getId().getPath()));
    }
}