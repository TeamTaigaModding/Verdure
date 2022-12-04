package dev.ebo2022.verdure.core.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.ebo2022.verdure.common.levelgen.feature.DitheredDiskFeature;
import dev.ebo2022.verdure.common.levelgen.feature.FlowerThicketFeature;
import dev.ebo2022.verdure.common.levelgen.feature.LargeLandPatchFeature;
import dev.ebo2022.verdure.common.levelgen.trunkplacers.EnhancedTrunkPlacer;
import dev.ebo2022.verdure.core.Verdure;
import dev.ebo2022.verdure.core.registry.util.RegistryUtil;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class VerdureFeatures {

    public static final PollinatedRegistry<Feature<?>> FEATURES = PollinatedRegistry.create(Registry.FEATURE, Verdure.MOD_ID);
    public static final PollinatedRegistry<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = PollinatedRegistry.create(BuiltinRegistries.CONFIGURED_FEATURE, Verdure.MOD_ID);
    public static final PollinatedRegistry<PlacedFeature> PLACED_FEATURES = PollinatedRegistry.create(BuiltinRegistries.PLACED_FEATURE, Verdure.MOD_ID);
    public static final PollinatedRegistry<TrunkPlacerType<?>> TRUNK_PLACER_TYPES = PollinatedRegistry.create(Registry.TRUNK_PLACER_TYPES, Verdure.MOD_ID);

    public static final Supplier<Feature<BlockStateConfiguration>> LARGE_LAND_PATCH = FEATURES.register("large_land_patch", () -> new LargeLandPatchFeature(BlockStateConfiguration.CODEC));
    public static final Supplier<Feature<DiskConfiguration>> DITHERED_DISK = FEATURES.register("dithered_disk", () -> new DitheredDiskFeature(DiskConfiguration.CODEC));
    public static final Supplier<Feature<BlockStateConfiguration>> FLOWER_THICKET = FEATURES.register("flower_thicket", () -> new FlowerThicketFeature(BlockStateConfiguration.CODEC));
    public static final Supplier<TrunkPlacerType<EnhancedTrunkPlacer>> ENHANCED_TRUNK_PLACER = TRUNK_PLACER_TYPES.register("enhanced", () -> new TrunkPlacerType<>(EnhancedTrunkPlacer.CODEC));

    public static class Configs {
        public static final TreeConfiguration.TreeConfigurationBuilder BIRCH_OVERRIDE_CONFIG = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.BIRCH_LOG),
                new EnhancedTrunkPlacer(8, 4, 3, 0.15F, UniformInt.of(1, 2), UniformInt.of(1, 3), ConstantInt.of(1)),
                BlockStateProvider.simple(Blocks.BIRCH_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1)
        ).ignoreVines().dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT));

    }

    public static class Configured {

        public static final Supplier<ConfiguredFeature<BlockStateConfiguration, ?>> BALMY_WILDFLOWERS = CONFIGURED_FEATURES.register("balmy_wildflowers", () -> new ConfiguredFeature<>(FLOWER_THICKET.get(), new BlockStateConfiguration(VerdureBlocks.BALMY_WILDFLOWERS.get().defaultBlockState())));
        public static final Supplier<ConfiguredFeature<BlockStateConfiguration, ?>> GLOOM_WILDFLOWERS = CONFIGURED_FEATURES.register("gloom_wildflowers", () -> new ConfiguredFeature<>(FLOWER_THICKET.get(), new BlockStateConfiguration(VerdureBlocks.GLOOM_WILDFLOWERS.get().defaultBlockState())));
        public static final Supplier<ConfiguredFeature<BlockStateConfiguration, ?>> PASTURE_WILDFLOWERS = CONFIGURED_FEATURES.register("pasture_wildflowers", () -> new ConfiguredFeature<>(FLOWER_THICKET.get(), new BlockStateConfiguration(VerdureBlocks.PASTURE_WILDFLOWERS.get().defaultBlockState())));

        public static final Supplier<ConfiguredFeature<RandomFeatureConfiguration, ?>> BIRCH_TREES_OVERRIDE = CONFIGURED_FEATURES.register("birch_trees_override", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(), RegistryUtil.place(Placed.BIRCH_OVERRIDE_CHECKED, "birch_override"))));
        public static final Supplier<ConfiguredFeature<TreeConfiguration, ?>> BIRCH_OVERRIDE = CONFIGURED_FEATURES.register("birch_override", () -> new ConfiguredFeature<>(Feature.TREE, Configs.BIRCH_OVERRIDE_CONFIG.build()));
        public static final Supplier<ConfiguredFeature<BlockStateConfiguration, ?>> MORNING_GLORIES = CONFIGURED_FEATURES.register("morning_glories", () -> new ConfiguredFeature<>(LARGE_LAND_PATCH.get(), new BlockStateConfiguration(VerdureBlocks.MORNING_GLORY.get().defaultBlockState())));
        public static final Supplier<ConfiguredFeature<BlockStateConfiguration, ?>> WHITE_MORNING_GLORIES = CONFIGURED_FEATURES.register("white_morning_glories", () -> new ConfiguredFeature<>(LARGE_LAND_PATCH.get(), new BlockStateConfiguration(VerdureBlocks.WHITE_MORNING_GLORY.get().defaultBlockState())));
        public static final Supplier<ConfiguredFeature<BlockStateConfiguration, ?>> BLUE_MORNING_GLORIES = CONFIGURED_FEATURES.register("blue_morning_glories", () -> new ConfiguredFeature<>(LARGE_LAND_PATCH.get(), new BlockStateConfiguration(VerdureBlocks.BLUE_MORNING_GLORY.get().defaultBlockState())));
        public static final Supplier<ConfiguredFeature<DiskConfiguration, ?>> COARSE_DIRT_PATCH = CONFIGURED_FEATURES.register("coarse_dirt_patch", () -> new ConfiguredFeature<>(VerdureFeatures.DITHERED_DISK.get(), new DiskConfiguration(Blocks.COARSE_DIRT.defaultBlockState(), UniformInt.of(3, 5), 1, List.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState(), Blocks.PODZOL.defaultBlockState(), Blocks.COARSE_DIRT.defaultBlockState(), Blocks.MYCELIUM.defaultBlockState()))));

    }

    public static class Placed {

        public static final Supplier<PlacedFeature> BIRCH_OVERRIDE_CHECKED = register("birch_override", RegistryUtil.config(Configured.BIRCH_OVERRIDE, "birch_override"), List.of(PlacementUtils.filteredByBlockSurvival(Blocks.BIRCH_SAPLING)));
        public static final Supplier<PlacedFeature> MORNING_GLORIES = register("morning_glories", RegistryUtil.config(Configured.MORNING_GLORIES, "morning_glories"), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, RarityFilter.onAverageOnceEvery(3), BiomeFilter.biome()));
        public static final Supplier<PlacedFeature> WHITE_MORNING_GLORIES = register("white_morning_glories", RegistryUtil.config(Configured.WHITE_MORNING_GLORIES, "white_morning_glories"), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, RarityFilter.onAverageOnceEvery(3), BiomeFilter.biome()));
        public static final Supplier<PlacedFeature> BLUE_MORNING_GLORIES = register("blue_morning_glories", RegistryUtil.config(Configured.BLUE_MORNING_GLORIES, "blue_morning_glories"), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, RarityFilter.onAverageOnceEvery(3), BiomeFilter.biome()));
        public static final Supplier<PlacedFeature> COARSE_DIRT_PATCH = register("coarse_dirt_patch", RegistryUtil.config(Configured.COARSE_DIRT_PATCH, "coarse_dirt_patch"), List.of(RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        public static final Supplier<PlacedFeature> TREES_BIRCH = register("trees_birch", RegistryUtil.config(Configured.BIRCH_TREES_OVERRIDE, "birch_trees_override"), VegetationPlacements.treePlacement(PlacementUtils.countExtra(8, 0.1f, 1)));
        public static final Supplier<PlacedFeature> BIRCH_TALL_GRASS = register("patch_tall_grass_birch", VegetationFeatures.PATCH_TALL_GRASS, List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        public static final Supplier<PlacedFeature> BIRCH_GRASS = register("patch_grass_birch", VegetationFeatures.PATCH_GRASS_JUNGLE, VegetationPlacements.worldSurfaceSquaredWithCount(20));
        public static final Supplier<PlacedFeature> BALMY_WILDFLOWERS = register("balmy_wildflowers", RegistryUtil.config(Configured.BALMY_WILDFLOWERS, "balmy_wildflowers"), List.of(RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        public static final Supplier<PlacedFeature> GLOOM_WILDFLOWERS = register("gloom_wildflowers", RegistryUtil.config(Configured.GLOOM_WILDFLOWERS, "gloom_wildflowers"), List.of(RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        public static final Supplier<PlacedFeature> PASTURE_WILDFLOWERS = register("pasture_wildflowers", RegistryUtil.config(Configured.PASTURE_WILDFLOWERS, "pasture_wildflowers"), List.of(RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        
        public static Supplier<PlacedFeature> register(String id, Holder<? extends ConfiguredFeature<?, ?>> config, List<PlacementModifier> modifiers) {
            return PLACED_FEATURES.register(id, () -> new PlacedFeature(Holder.hackyErase(config), modifiers));
        }
    }
}
