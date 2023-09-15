package com.github.teamfusion.rottencreatures.datagen.common;

import com.github.teamfusion.rottencreatures.RottenCreatures;
import com.github.teamfusion.rottencreatures.common.registries.RCBlocks;
import com.github.teamfusion.rottencreatures.common.registries.RCItems;
import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Consumer;

public class RecipeGenerator extends RecipeProvider {
    private final DataGenerator data;

    public RecipeGenerator(DataGenerator generator) {
        super(generator);
        this.data = generator;
    }

    @Override
    public void run(CachedOutput cache) {
        Path path = this.data.getOutputFolder();
        Set<ResourceLocation> recipes = Sets.newHashSet();
    }

    public static void saveRecipe(CachedOutput cache, JsonObject json, Path recipe) {
        try {
            DataProvider.saveStable(cache, json, recipe);
        } catch (IOException exception) {
            RottenCreatures.LOGGER.error("Couldn't save recipe {}", recipe, exception);
        }
    }

    public static void saveAdvancement(CachedOutput cache, JsonObject json, Path advancement) {
        try {
            DataProvider.saveStable(cache, json, advancement);
        } catch (IOException exception) {
            RottenCreatures.LOGGER.error("Couldn't save recipe advancement {}", advancement, exception);
        }
    }
}