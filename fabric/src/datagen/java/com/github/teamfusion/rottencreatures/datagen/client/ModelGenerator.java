package com.github.teamfusion.rottencreatures.datagen.client;

import com.github.teamfusion.rottencreatures.RottenCreatures;
import com.github.teamfusion.rottencreatures.common.registries.RCBlocks;
import com.github.teamfusion.rottencreatures.common.registries.RCItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.Registry;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

public final class ModelGenerator extends FabricModelProvider {
    private static final ModelTemplate SPAWN_EGG = createItem("template_spawn_egg");

    public ModelGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateItemModels(ItemModelGenerators gen) {
        gen.generateFlatItem(RCItems.BURNED_SPAWN_EGG.get(), SPAWN_EGG);
        gen.generateFlatItem(RCItems.FROSTBITTEN_SPAWN_EGG.get(), SPAWN_EGG);
        gen.generateFlatItem(RCItems.SWAMPY_SPAWN_EGG.get(), SPAWN_EGG);
        gen.generateFlatItem(RCItems.MUMMY_SPAWN_EGG.get(), SPAWN_EGG);
        gen.generateFlatItem(RCItems.GLACIAL_HUNTER_SPAWN_EGG.get(), SPAWN_EGG);
        gen.generateFlatItem(RCItems.DEAD_BEARD_SPAWN_EGG.get(), SPAWN_EGG);
        gen.generateFlatItem(RCItems.IMMORTAL_SPAWN_EGG.get(), SPAWN_EGG);
    }

    private static ModelTemplate createItem(String key) {
        return new ModelTemplate(Optional.of(new ResourceLocation("minecraft", "item/" + key)), Optional.empty());
    }

    private static ModelTemplate create(String key) {
        return new ModelTemplate(Optional.of(new ResourceLocation(RottenCreatures.MOD_ID, "block/" + key)), Optional.empty());
    }

    private static ModelTemplate create(Block block) {
        return new ModelTemplate(Optional.of(new ResourceLocation(RottenCreatures.MOD_ID, "block/" + Registry.BLOCK.getKey(block).getPath())), Optional.empty());
    }
}
