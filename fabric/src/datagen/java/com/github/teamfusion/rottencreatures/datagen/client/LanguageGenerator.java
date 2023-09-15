package com.github.teamfusion.rottencreatures.datagen.client;

import com.github.teamfusion.rottencreatures.ConfigEntries;
import com.github.teamfusion.rottencreatures.RottenCreatures;
import com.github.teamfusion.rottencreatures.common.registries.RCBlocks;
import com.github.teamfusion.rottencreatures.common.registries.RCEntityTypes;
import com.github.teamfusion.rottencreatures.common.registries.RCItems;
import com.github.teamfusion.rottencreatures.common.registries.RCMobEffects;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

public final class LanguageGenerator implements DataProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private final Map<String, String> data = new TreeMap<>();
    private final DataGenerator generator;

    public LanguageGenerator(DataGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void run(CachedOutput cache) throws IOException {
        this.addTranslations();
        Path path = this.generator.getOutputFolder().resolve("assets/rottencreatures/lang/en_us.json");
        DataProvider.saveStable(cache, GSON.toJsonTree(this.data), path);
    }

    @Override
    public String getName() {
        return "Language: en_us";
    }

    private void addTranslations() {
        // Entities
        this.entity(RCEntityTypes.BURNED.get(), "Burned");
        this.entity(RCEntityTypes.FROSTBITTEN.get(), "Frostbitten");
        this.entity(RCEntityTypes.SWAMPY.get(), "Swampy");
        this.entity(RCEntityTypes.MUMMY.get(), "Mummy");
        this.entity(RCEntityTypes.SCARAB.get(), "Scarab");
        this.entity(RCEntityTypes.FLYING_SCARAB.get(), "Flying Scarab");
        this.entity(RCEntityTypes.GLACIAL_HUNTER.get(), "Glacial Hunter");
        this.entity(RCEntityTypes.HUNTER_WOLF.get(), "Hunter's Wolf");
        this.entity(RCEntityTypes.DEAD_BEARD.get(), "Dead Beard");
        this.entity(RCEntityTypes.ZOMBIE_LACKEY.get(), "Zombie Lackey");
        this.entity(RCEntityTypes.SKELETON_LACKEY.get(), "Skeleton Lackey");
        this.entity(RCEntityTypes.IMMORTAL.get(), "Immortal");
        this.entity(RCEntityTypes.ZAP.get(), "Zap");

        // Items
        this.item(RCItems.BURNED_SPAWN_EGG.get(), "Burned Spawn Egg");
        this.item(RCItems.FROSTBITTEN_SPAWN_EGG.get(), "Frostbitten Spawn Egg");
        this.item(RCItems.SWAMPY_SPAWN_EGG.get(), "Swampy Spawn Egg");
        this.item(RCItems.MUMMY_SPAWN_EGG.get(), "Mummy Spawn Egg");
        this.item(RCItems.GLACIAL_HUNTER_SPAWN_EGG.get(), "Glacial Hunter Spawn Egg");
        this.item(RCItems.DEAD_BEARD_SPAWN_EGG.get(), "Dead Beard Spawn Egg");
        this.item(RCItems.IMMORTAL_SPAWN_EGG.get(), "Immortal Spawn Egg");
        this.item(RCItems.SPEAR.get(), "Spear");

        // Alchemy
        this.effect(RCMobEffects.FREEZE.get(), "Freeze");
        this.potion("Corrupted", true, false, false);
        this.potion("Freeze", false, true, true);
        this.add("item.minecraft.tipped_arrow.effect.freeze", "Arrow of Freeze");
        this.add("item.minecraft.tipped_arrow.effect.long_freeze", "Arrow of Freeze");
        this.add("item.minecraft.tipped_arrow.effect.strong_freeze", "Arrow of Freeze");

        this.effect(RCMobEffects.CHANNELLED.get(), "Channelled");

        // Misc
        this.add("itemGroup.rottencreatures.rottencreatures", "Rotten Creatures");

        // Config
        this.addConfig("mobSpawns", "Mob Spawns");
        this.addConfig("mobSpawns", "burned_weight", ConfigEntries.BURNED_WEIGHT.name());
        this.addConfig("mobSpawns", "frostbitten_weight", ConfigEntries.FROSTBITTEN_WEIGHT.name());
        this.addConfig("mobSpawns", "glacial_hunter_weight", ConfigEntries.GLACIAL_HUNTER_WEIGHT.name());
        this.addConfig("mobSpawns", "swampy_weight", ConfigEntries.SWAMPY_WEIGHT.name());
        this.addConfig("mobSpawns", "mummy_weight", ConfigEntries.MUMMY_WEIGHT.name());
        this.addConfig("mobSpawns", "dead_beard_weight", ConfigEntries.DEAD_BEARD_WEIGHT.name());
        this.addConfig("mobSpawns", "immortal_chance", ConfigEntries.IMMORTAL_CHANCE.name());
    }

    private void block(Block entry, String name) {
        this.add(entry.getDescriptionId(), name);
    }

    private void item(Item entry, String name) {
        this.add(entry.getDescriptionId(), name);
    }

    private void entity(EntityType<?> entry, String name) {
        this.add(entry.getDescriptionId(), name);
    }

    private void effect(MobEffect entry, String name) {
        this.add(entry.getDescriptionId(), name);
    }

    private void potion(String entry, boolean isContainer, boolean hasLong, boolean hasStrong) {
        for (PotionType type : PotionType.values()) {
            String name = isContainer ? entry + " " + type.name : type.name + " of " + entry;
            this.add("item.minecraft." + type.id + ".effect." + entry.toLowerCase(), name);
            if (hasLong) {
                this.add("item.minecraft." + type.id + ".effect." + "long_" + entry.toLowerCase(), name);
            }
            if (hasStrong) {
                this.add("item.minecraft." + type.id + ".effect." + "strong_" + entry.toLowerCase(), name);
            }
        }
    }

    private void addConfig(String key, String sub, String value) {
        this.add("text.autoconfig." + RottenCreatures.MOD_ID + ".option." + key + (sub != null ? "." + sub : ""), value);
    }

    private void addConfig(String key, String value) {
        this.addConfig(key, null, value);
    }

    private void add(String key, String value) {
        if (this.data.put(key, value) != null) throw new IllegalStateException("Duplicate translation key " + key);
    }

    enum PotionType {
        POTION("potion", "Potion"),
        SPLASH("splash_potion", "Splash Potion"),
        LINGERING("lingering_potion", "Lingering Potion");

        final String id;
        final String name;

        PotionType(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}