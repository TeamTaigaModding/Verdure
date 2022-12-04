package dev.ebo2022.verdure.common.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class FlowerThicketFeature extends Feature<BlockStateConfiguration> {

    public FlowerThicketFeature(Codec<BlockStateConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<BlockStateConfiguration> featurePlaceContext) {
        BlockPos pos = featurePlaceContext.origin();
        WorldGenLevel level = featurePlaceContext.level();
        int i = 0;
        for (BlockPos pos2 : BlockPos.betweenClosed(pos.offset(-3, -3, -3), pos.offset(3, 3, 3))) {
            if (pos.closerThan(pos2, 3.0) && featurePlaceContext.config().state.canSurvive(level, pos2) && isAir(level, pos2)) {
                if (featurePlaceContext.random().nextBoolean()) {
                    level.setBlock(pos2, featurePlaceContext.config().state, 2);
                    i++;
                }
            }
        }
        return i > 0;
    }
}
