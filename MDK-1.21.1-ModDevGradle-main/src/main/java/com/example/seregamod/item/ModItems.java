package com.example.seregamod.item;

import com.example.seregamod.SeregaMod;
//import com.example.seregamod.item.custom.ExtraLegItem;
import com.example.seregamod.entity.ModEntities;
import com.example.seregamod.item.custom.*;
import com.example.seregamod.item.eggs.*;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
//import com.simibubi.create.Create;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SeregaMod.MODID);

    public static final DeferredItem<Item> PISKA = ITEMS.register("piska",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TNTCASTER = ITEMS.register("tnt_caster",
            () -> new TntCasterItem(new Item.Properties()));
    public static final DeferredItem<Item> SEREGA_COCKTAIL = ITEMS.register("serega_cocktail",
            () -> new FuelItem(new Item.Properties().food(SeregaCocktailItem.SEREGA_COCKTAIL), 100));
    public static final DeferredItem<Item> CONJOINER = ITEMS.register("conjoiner",
            () -> new ConjoinerItem(new Item.Properties()));

    public static final DeferredItem<Item> DRAGGER_EGG = ITEMS.register("dragger_egg",
            () -> new DraggerEgg(new Item.Properties()));
    public static final DeferredItem<Item> SLASHER_EGG = ITEMS.register("slasher_egg",
            () -> new SlasherEgg(new Item.Properties()));
    public static final DeferredItem<Item> ZERGLING_EGG = ITEMS.register("zergling_egg",
            () -> new ZerglingEgg(new Item.Properties()));
    public static final DeferredItem<Item> STOMPER_EGG = ITEMS.register("stomper_egg",
            () -> new StomperEgg(new Item.Properties()));
    public static final DeferredItem<Item> FORWARDER_EGG = ITEMS.register("forwarder_egg",
            () -> new ForwarderEgg(new Item.Properties()));

    public static final DeferredItem<Item> ROPE_ITEM = ITEMS.register("rope_item",
            () -> new RopeItem(new Item.Properties()));
    public static final DeferredItem<Item> GRAPLE = ITEMS.register("graple",
            () -> new GrapleItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<SwordItem> SEREGA_SWORD = ITEMS.register("serega_sword",
            () -> new SwordItem(ModToolTiers.SEREGA,
                    (new Item.Properties()).attributes(SwordItem.createAttributes(ModToolTiers.SEREGA, 3, -2.4F))));
    public static final DeferredItem<PickaxeItem> SEREGA_PICKAXE = ITEMS.register("serega_pickaxe",
            () -> new PickaxeItem(ModToolTiers.SEREGA,
                    (new Item.Properties()).attributes(PickaxeItem.createAttributes(ModToolTiers.SEREGA, 3, -2.4F))));
    public static final DeferredItem<AxeItem> SEREGA_AXE = ITEMS.register("serega_axe",
            () -> new AxeItem(ModToolTiers.SEREGA,
                    (new Item.Properties()).attributes(AxeItem.createAttributes(ModToolTiers.SEREGA, 3, -2.4F))));
    public static final DeferredItem<ShovelItem> SEREGA_SHOVEL = ITEMS.register("serega_shovel",
            () -> new ShovelItem(ModToolTiers.SEREGA,
                    (new Item.Properties()).attributes(ShovelItem.createAttributes(ModToolTiers.SEREGA, 3, -2.4F))));
    public static final DeferredItem<HoeItem> SEREGA_HOE = ITEMS.register("serega_hoe",
            () -> new HoeItem(ModToolTiers.SEREGA,
                    (new Item.Properties()).attributes(HoeItem.createAttributes(ModToolTiers.SEREGA, 3, -2.4F))));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
