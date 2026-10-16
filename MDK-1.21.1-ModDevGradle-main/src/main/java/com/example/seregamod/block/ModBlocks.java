package com.example.seregamod.block;

import com.example.seregamod.SeregaMod;
import com.example.seregamod.block.custom.SpreaderBlock;
import com.example.seregamod.block.custom.TumorBlock;
import com.example.seregamod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.lang.reflect.Type;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(SeregaMod.MODID);

    public static final DeferredBlock<Block> SEREGA_BLOCK = registerBlock("serega_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST).mapColor(MapColor.COLOR_ORANGE)
            ));
    public static final DeferredBlock<Block> SPREADER_BLOCK = registerBlock("spreader_block",
            () -> new SpreaderBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST).mapColor(MapColor.COLOR_ORANGE)
            ));
    public static final DeferredBlock<Block> TUMOR_BLOCK = registerBlock("tumor_block",
            () -> new TumorBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST).mapColor(MapColor.COLOR_ORANGE)
            ));

    public static final DeferredBlock<StairBlock> SPREADER_STAIRS = registerBlock("spreader_stairs",
            () -> new StairBlock(ModBlocks.SPREADER_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST).mapColor(MapColor.COLOR_ORANGE)));
    public static final DeferredBlock<SlabBlock> SPREADER_SLAB = registerBlock("spreader_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST).mapColor(MapColor.COLOR_ORANGE)));

    public static final DeferredBlock<PressurePlateBlock> SPREADER_PRESSURE_PLATE = registerBlock("spreader_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST).mapColor(MapColor.COLOR_ORANGE)));

    public static final DeferredBlock<ButtonBlock> SPREADER_BUTTON = registerBlock("spreader_button",
            () -> new ButtonBlock(BlockSetType.IRON, 20, BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST).mapColor(MapColor.COLOR_ORANGE).noCollission()));

    public static final DeferredBlock<FenceBlock> SPREADER_FENCE = registerBlock("spreader_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST).mapColor(MapColor.COLOR_ORANGE)));

    public static final DeferredBlock<FenceGateBlock> SPREADER_FENCE_GATE = registerBlock("spreader_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST).mapColor(MapColor.COLOR_ORANGE)));

    public static final DeferredBlock<WallBlock> SPREADER_WALL = registerBlock("spreader_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST).mapColor(MapColor.COLOR_ORANGE)));

    public static final DeferredBlock<DoorBlock> SPREADER_DOOR = registerBlock("spreader_door",
            () -> new DoorBlock(BlockSetType.POLISHED_BLACKSTONE, BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST).mapColor(MapColor.COLOR_ORANGE).noOcclusion()));

    public static final DeferredBlock<TrapDoorBlock> SPREADER_TRAPDOOR = registerBlock("spreader_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.POLISHED_BLACKSTONE, BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST).mapColor(MapColor.COLOR_ORANGE).noOcclusion()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
