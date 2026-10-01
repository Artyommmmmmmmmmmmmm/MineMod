package com.example.seregamod.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import org.jetbrains.annotations.NotNull;

public class SeregaCocktailItem extends Item {
    public static final FoodProperties SEREGA_COCKTAIL = new FoodProperties.Builder().nutrition(10).saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 60), 1.0f).build();

    public SeregaCocktailItem(Properties properties) {
        super(properties.food(SEREGA_COCKTAIL));  // Передаем FoodProperties
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.DRINK;
    }


}
