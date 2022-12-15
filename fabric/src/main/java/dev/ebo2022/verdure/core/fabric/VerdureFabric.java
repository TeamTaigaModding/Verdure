package dev.ebo2022.verdure.core.fabric;

import dev.ebo2022.verdure.core.Verdure;
import dev.ebo2022.verdure.core.registry.VerdureFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;

/**
 * @author ebo2022
 * Created: 8/17/22
 */
public class VerdureFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Verdure.PLATFORM.setup();
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.BiomeCategory.FOREST, Biome.BiomeCategory.TAIGA, Biome.BiomeCategory.SAVANNA, Biome.BiomeCategory.SWAMP, Biome.BiomeCategory.PLAINS), GenerationStep.Decoration.VEGETAL_DECORATION, VerdureFeatures.Placed.ROCK_KEY);
    }
}
