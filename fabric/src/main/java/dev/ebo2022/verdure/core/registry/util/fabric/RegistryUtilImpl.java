package dev.ebo2022.verdure.core.registry.util.fabric;

import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class RegistryUtilImpl {

    @SuppressWarnings({"UnstableApiUsage", "unchecked"})
    public static <T, R extends T> Holder<R> asHolder(Supplier<R> supplier, PollinatedRegistry<T> registry, String name) {
        return (Holder<R>) ((PollinatedRegistry.VanillaImpl<T>) registry).getRegistry().getOrCreateHolder(ResourceKey.create(registry.key(), new ResourceLocation(registry.getModId(), name)));
    }
}
