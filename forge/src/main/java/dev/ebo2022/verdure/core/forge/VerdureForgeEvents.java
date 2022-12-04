package dev.ebo2022.verdure.core.forge;

import dev.ebo2022.verdure.common.biome.BiomeModifiers;
import dev.ebo2022.verdure.core.Verdure;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = Verdure.MOD_ID)
public class VerdureForgeEvents {

    @SubscribeEvent
    public static void onEvent(BiomeLoadingEvent event) {
        BiomeModifiers.FEATURE_ADDITIONS.forEach(addition -> {
            if (addition.pred().test(new BiomeModifiers.SelectionContext() {
                @Override
                public boolean is(ResourceKey<Biome> biome) {
                    return matchesKeys(event.getName(), biome);
                }

                @Override
                public boolean is(Biome.BiomeCategory cat) {
                    return false;
                }
            })) {
                for (Holder<PlacedFeature> placement : addition.placement())
                    event.getGeneration().addFeature(addition.step(), placement);
            }
        });
        BiomeModifiers.TREE_REPLACEMENTS.forEach(replacement -> {
            if (matchesKeys(event.getName(), replacement.biome())) {
                event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION).removeIf(h -> h.is(replacement.old()));
                event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, replacement.neew());
            }
        });
    }

    public static boolean matchesKeys(ResourceLocation loc, ResourceKey<?>... keys) {
        for (ResourceKey<?> key : keys)
            if (key.location().equals(loc))
                return true;
        return false;
    }
}
