package dev.ebo2022.verdure.core.registry.util.forge;

import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Holder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RegistryUtilImpl {
    public static <T, R extends T> Holder<R> asHolder(Supplier<R> supplier, PollinatedRegistry<T> registry, String name) {
        return ((RegistryObject<R>) supplier).getHolder().orElseThrow();
    }
}
