package dev.ebo2022.verdure.core.registry.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.ebo2022.verdure.core.registry.VerdureFeatures;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.function.Supplier;

public class RegistryUtil {

    @ExpectPlatform
    public static <T, R extends T> Holder<R> asHolder(Supplier<R> supplier, PollinatedRegistry<T> registry, String name) {
        return Platform.error();
    }

    public static <T extends ConfiguredFeature<?, ?>> Holder<T> config(Supplier<T> supplier, String name) {
        return asHolder(supplier, VerdureFeatures.CONFIGURED_FEATURES, name);
    }

    public static Holder<PlacedFeature> place(Supplier<PlacedFeature> supplier, String name) {
        return asHolder(supplier, VerdureFeatures.PLACED_FEATURES, name);
    }
}
