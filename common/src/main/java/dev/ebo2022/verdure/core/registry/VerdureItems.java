package dev.ebo2022.verdure.core.registry;

import dev.ebo2022.verdure.core.Verdure;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;

public class VerdureItems {

    public static final PollinatedRegistry<Item> ITEMS = PollinatedRegistry.create(Registry.ITEM, Verdure.MOD_ID);
}
