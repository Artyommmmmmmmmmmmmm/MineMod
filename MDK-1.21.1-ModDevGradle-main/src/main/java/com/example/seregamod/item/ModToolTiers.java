package com.example.seregamod.item;

import com.example.seregamod.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier SEREGA = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_SEREGA_TOOL,
            1000, 4f, 3f, 30, () -> Ingredient.of(ModItems.PISKA));
}
