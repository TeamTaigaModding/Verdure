package dev.ebo2022.verdure.core.registry.fabric;

import dev.ebo2022.verdure.core.Verdure;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;

import java.util.function.Supplier;

public class VerdureFeaturesImpl {
    @SuppressWarnings({"unchecked", "UnstableApiUsage"})
    public static <T, R extends T> Holder<R> holder(String id, Supplier<R> feature, PollinatedRegistry<T> registry) {
        PollinatedRegistry.VanillaImpl<T> impl = (PollinatedRegistry.VanillaImpl<T>) registry;
        registry.register(id, feature);
        final ResourceKey<T> key = ResourceKey.create(registry.key(), Verdure.location(id));
        return (Holder<R>) impl.getRegistry().getOrCreateHolder(key);
    }
}
