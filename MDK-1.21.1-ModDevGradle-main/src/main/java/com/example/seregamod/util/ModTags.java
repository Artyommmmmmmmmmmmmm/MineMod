package com.example.seregamod.util;


import com.example.seregamod.SeregaMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> SATURN_TAG = createTag("saturn_tags");
        public static final TagKey<Block> NEEDS_SEREGA_TOOL = createTag("needs_serega_tool");
        public static final TagKey<Block> INCORRECT_FOR_SEREGA_TOOL = createTag("incorrect_for_serega_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID, name));
        }
    }

    public static class Items {


        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(SeregaMod.MODID, name));
        }
    }
}

