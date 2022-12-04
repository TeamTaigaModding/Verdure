package dev.ebo2022.verdure.core.fabric;

import com.mojang.datafixers.util.Either;
import dev.ebo2022.verdure.common.biome.BiomeModifiers;
import dev.ebo2022.verdure.core.Verdure;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

/**
 * @author ebo2022
 * Created: 8/17/22
 */
public class VerdureFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Verdure.PLATFORM.setup();

        final BiomeModification modification = BiomeModifications.create(Verdure.location("biome_modifications"));
        BiomeModifiers.FEATURE_ADDITIONS.forEach(addition -> modification.add(ModificationPhase.ADDITIONS, selectionContext -> addition.pred().test(new SelectionContextImpl(selectionContext)), modificationContext -> {
            for (Holder<PlacedFeature> placement : addition.placement()) {
                Either<ResourceKey<PlacedFeature>, PlacedFeature> either = placement.unwrap();
                if (either.left().isPresent()) {
                    modificationContext.getGenerationSettings().addFeature(addition.step(), either.left().get());
                } else {
                    modificationContext.getGenerationSettings().addBuiltInFeature(addition.step(), either.right().get());
                }
            }
        }));
        BiomeModifiers.TREE_REPLACEMENTS.forEach(replacement -> modification.add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(replacement.biome()), modificationContext -> {
            BiomeModificationContext.GenerationSettingsContext generation = modificationContext.getGenerationSettings();
            generation.removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, replacement.old());
            Either<ResourceKey<PlacedFeature>, PlacedFeature> either = replacement.neew().unwrap();
            if (either.left().isPresent()) {
                generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, either.left().get());
            } else {
                generation.addBuiltInFeature(GenerationStep.Decoration.VEGETAL_DECORATION, either.right().get());
            }
        }));
    }

    private static class SelectionContextImpl implements BiomeModifiers.SelectionContext {

        private final BiomeSelectionContext parent;

        private SelectionContextImpl(BiomeSelectionContext parent) {
            this.parent = parent;
        }

        @Override
        public boolean is(ResourceKey<Biome> biome) {
            return this.parent.getBiomeKey() == biome;
        }

        @Override
        public boolean is(Biome.BiomeCategory cat) {
            return Biome.getBiomeCategory(this.parent.getBiomeRegistryEntry()) == cat;
        }
    }
}
