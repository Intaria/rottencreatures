package com.github.teamfusion.rottencreatures.common.registries;

import com.github.teamfusion.platform.CoreRegistry;
import com.github.teamfusion.rottencreatures.RottenCreatures;
import com.github.teamfusion.rottencreatures.common.item.SpearItem;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class RCItems {
    public static final CoreRegistry<Item> ITEMS = CoreRegistry.create(Registry.ITEM, RottenCreatures.MOD_ID);

    // Spawns
    public static final Supplier<Item> BURNED_SPAWN_EGG = create("burned_spawn_egg", spawnEgg(RCEntityTypes.BURNED, 5969689, 14244138));
    public static final Supplier<Item> FROSTBITTEN_SPAWN_EGG = create("frostbitten_spawn_egg", spawnEgg(RCEntityTypes.FROSTBITTEN, 5864108, 8967167));
    public static final Supplier<Item> SWAMPY_SPAWN_EGG = create("swampy_spawn_egg", spawnEgg(RCEntityTypes.SWAMPY, 3351821, 7227939));
    public static final Supplier<Item> MUMMY_SPAWN_EGG = create("mummy_spawn_egg", spawnEgg(RCEntityTypes.MUMMY, 13333578, 7956784));
    public static final Supplier<Item> GLACIAL_HUNTER_SPAWN_EGG = create("glacial_hunter_spawn_egg", spawnEgg(RCEntityTypes.GLACIAL_HUNTER, 7554081, 15651988));
    public static final Supplier<Item> DEAD_BEARD_SPAWN_EGG = create("dead_beard_spawn_egg", spawnEgg(RCEntityTypes.DEAD_BEARD, 2369569, 1270579));
    public static final Supplier<Item> IMMORTAL_SPAWN_EGG = create("immortal_spawn_egg", spawnEgg(RCEntityTypes.IMMORTAL, 1583154, 3381149));

    // MISC
    public static final Supplier<Item> SPEAR = create("spear", () -> new SpearItem(new Item.Properties()));

    private static <T extends Item> Supplier<T> create(String key, Supplier<T> item) {
        return ITEMS.register(key, item);
    }

    @ExpectPlatform
    private static Supplier<Item> spawnEgg(Supplier<? extends EntityType<? extends Mob>> entity, int background, int highlight) {
        throw new AssertionError();
    }
}