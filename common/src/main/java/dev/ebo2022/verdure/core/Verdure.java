package dev.ebo2022.verdure.core;

import dev.ebo2022.verdure.common.biome.BiomeModifiers;
import dev.ebo2022.verdure.core.registry.VerdureBlocks;
import dev.ebo2022.verdure.core.registry.VerdureFeatures;
import dev.ebo2022.verdure.core.registry.VerdureItems;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.client.RenderTypeRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

/**
 * @author ebo2022
 * Created: 8/24/22
 */
public class Verdure {

    public static final String MOD_ID = "verdure";
    public static final Platform PLATFORM = Platform.builder(MOD_ID)
            .clientInit(() -> Verdure::clientInit)
            .clientPostInit(() -> Verdure::clientPostInit)
            .commonInit(Verdure::commonInit)
            .commonPostInit(Verdure::commonPostInit)
            .build();

    public static void clientInit() {
    }

    public static void clientPostInit(Platform.ModSetupContext ctx) {
        RenderTypeRegistry.register(VerdureBlocks.BLUE_MORNING_GLORY.get(), RenderType.cutout());
        RenderTypeRegistry.register(VerdureBlocks.MORNING_GLORY.get(), RenderType.cutout());
        RenderTypeRegistry.register(VerdureBlocks.WHITE_MORNING_GLORY.get(), RenderType.cutout());
        RenderTypeRegistry.register(VerdureBlocks.BALMY_WILDFLOWERS.get(), RenderType.cutout());
        RenderTypeRegistry.register(VerdureBlocks.GLOOM_WILDFLOWERS.get(), RenderType.cutout());
        RenderTypeRegistry.register(VerdureBlocks.PASTURE_WILDFLOWERS.get(), RenderType.cutout());
    }

    public static void commonInit() {
        BiomeModifiers.init();
        VerdureBlocks.BLOCKS.register(PLATFORM);
        VerdureItems.ITEMS.register(PLATFORM);
        VerdureFeatures.FEATURES.register(PLATFORM);
        VerdureFeatures.CONFIGURED_FEATURES.register(PLATFORM);
        VerdureFeatures.PLACED_FEATURES.register(PLATFORM);
        VerdureFeatures.TRUNK_PLACER_TYPES.register(PLATFORM);
    }

    public static void commonPostInit(Platform.ModSetupContext ctx) {
    }

    public static ResourceLocation location(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
