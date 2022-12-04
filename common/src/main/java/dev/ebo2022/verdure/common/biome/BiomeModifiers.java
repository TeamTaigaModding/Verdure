package dev.ebo2022.verdure.common.biome;

import com.mojang.datafixers.util.Pair;
import dev.ebo2022.verdure.core.registry.VerdureFeatures;
import dev.ebo2022.verdure.core.registry.util.RegistryUtil;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public final class BiomeModifiers {

    private BiomeModifiers() {
    }

    public static final List<FeatureAddition> FEATURE_ADDITIONS = new ArrayList<>();
    public static final List<TreeReplacement> TREE_REPLACEMENTS = new ArrayList<>();

    public static void init() {
        FEATURE_ADDITIONS.add(new FeatureAddition(biomes(Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST), GenerationStep.Decoration.VEGETAL_DECORATION, RegistryUtil.place(VerdureFeatures.Placed.MORNING_GLORIES, "morning_glories"), RegistryUtil.place(VerdureFeatures.Placed.WHITE_MORNING_GLORIES, "white_morning_glories"), RegistryUtil.place(VerdureFeatures.Placed.BLUE_MORNING_GLORIES, "blue_morning_glories")));
        FEATURE_ADDITIONS.add(new FeatureAddition(categories(Biome.BiomeCategory.FOREST, Biome.BiomeCategory.JUNGLE, Biome.BiomeCategory.TAIGA, Biome.BiomeCategory.SAVANNA , Biome.BiomeCategory.SWAMP), GenerationStep.Decoration.UNDERGROUND_ORES, RegistryUtil.place(VerdureFeatures.Placed.COARSE_DIRT_PATCH, "coarse_dirt_patch")));
        FEATURE_ADDITIONS.add(new FeatureAddition(biomes(Biomes.BIRCH_FOREST), GenerationStep.Decoration.VEGETAL_DECORATION, RegistryUtil.place(VerdureFeatures.Placed.BIRCH_TALL_GRASS, "patch_tall_grass_birch"), RegistryUtil.place(VerdureFeatures.Placed.BIRCH_GRASS, "patch_grass_birch")));
        FEATURE_ADDITIONS.add(new FeatureAddition(biomes(Biomes.BIRCH_FOREST, Biomes.MEADOW), GenerationStep.Decoration.VEGETAL_DECORATION, RegistryUtil.place(VerdureFeatures.Placed.BALMY_WILDFLOWERS, "balmy_wildflowers")));
        TREE_REPLACEMENTS.add(new TreeReplacement(Biomes.BIRCH_FOREST, VegetationPlacements.TREES_BIRCH.unwrapKey().orElseThrow(), RegistryUtil.place(VerdureFeatures.Placed.TREES_BIRCH, "trees_birch")));
    }

    public record FeatureAddition(Predicate<SelectionContext> pred, GenerationStep.Decoration step, Holder<PlacedFeature>... placement) {

        @SafeVarargs
        public FeatureAddition {
        }
    }

    public record TreeReplacement(ResourceKey<Biome> biome, ResourceKey<PlacedFeature> old, Holder<PlacedFeature> neew) {
    }

    public interface SelectionContext {

        boolean is(ResourceKey<Biome> biome);

        boolean is(Biome.BiomeCategory cat);
    }

    @SafeVarargs
    public static Predicate<SelectionContext> biomes(ResourceKey<Biome>... biomes) {
        return selectionContext -> {
            for (ResourceKey<Biome> biome : biomes) {
                if (selectionContext.is(biome))
                    return true;
            }
            return false;
        };
    }

    public static Predicate<SelectionContext> categories(Biome.BiomeCategory... categories) {
        return selectionContext -> {
          for (Biome.BiomeCategory category : categories) {
            if (selectionContext.is(category))
                return true;
          }
          return false;
        };
    }
}
