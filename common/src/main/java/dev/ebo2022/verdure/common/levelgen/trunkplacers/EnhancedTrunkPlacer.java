package dev.ebo2022.verdure.common.levelgen.trunkplacers;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.ebo2022.verdure.core.registry.VerdureFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.RandomSource;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Adapted from the trunk placer in Wilder Wild; uses bark rather than the log
 */
public class EnhancedTrunkPlacer extends TrunkPlacer {
    public static final Codec<EnhancedTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> trunkPlacerParts(instance).and(instance.group(Codec.floatRange(0.0F, 1.0F).fieldOf("place_branch_chance").forGetter((trunkPlacer) -> trunkPlacer.logChance), IntProvider.NON_NEGATIVE_CODEC.fieldOf("max_logs").forGetter((trunkPlacer) -> trunkPlacer.maxLogs), IntProvider.NON_NEGATIVE_CODEC.fieldOf("log_height_from_top").forGetter((trunkPlacer) -> trunkPlacer.logHeightFromTop), IntProvider.NON_NEGATIVE_CODEC.fieldOf("extra_branch_length").forGetter((trunkPlacer) -> trunkPlacer.extraBranchLength))).apply(instance, EnhancedTrunkPlacer::new));

    private final IntProvider extraBranchLength;
    private final float logChance;
    private final IntProvider maxLogs;
    private final IntProvider logHeightFromTop;

    public EnhancedTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight, float logChance, IntProvider maxLogs, IntProvider logHeightFromTop, IntProvider extraBranchLength) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
        this.logChance = logChance;
        this.maxLogs = maxLogs;
        this.logHeightFromTop = logHeightFromTop;
        this.extraBranchLength = extraBranchLength;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return VerdureFeatures.ENHANCED_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> placer, Random random, int height, BlockPos startPos, TreeConfiguration config) {
        setDirtAt(level, placer, random, startPos.below(), config);
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        int maxLogs = this.maxLogs.sample(random);
        int logHeightFromTop = this.logHeightFromTop.sample(random);
        int placedLogs = 0;
        for (int i = 0; i < height; ++i) {
            int j = startPos.getY() + i;
            if (placeLog(level, placer, random, mutable.set(startPos.getX(), j, startPos.getZ()), config)
                    && i < height - 1 && random.nextFloat() < this.logChance && placedLogs < maxLogs && (height - 4) - i <= logHeightFromTop) {
                Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                this.generateExtraBranch(level, placer, random, config, mutable, j, direction, this.extraBranchLength.sample(random));
                ++placedLogs;
            }
            if (i == height - 1) {
                list.add(new FoliagePlacer.FoliageAttachment(mutable.set(startPos.getX(), j + 1, startPos.getZ()), 0, false));
            }
        }

        return list;
    }

    private void generateExtraBranch(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> placer, Random random, TreeConfiguration config, BlockPos.MutableBlockPos pos, int yOffset, Direction direction, int length) {
        int j = pos.getX();
        int k = pos.getZ();
        for (int l = 0; l < length; ++l) {
            j += direction.getStepX();
            k += direction.getStepZ();
            if (TreeFeature.validTreePos(level, pos.set(j, yOffset, k))) {
                if (config.trunkProvider.getState(random, pos.set(j, yOffset, k)).hasProperty(BlockStateProperties.AXIS)) {
                    Direction.Axis axis = direction.getStepX() != 0 ? Direction.Axis.X : Direction.Axis.Z;
                    placer.accept(pos.set(j, yOffset, k), config.trunkProvider.getState(random, pos.set(j, yOffset, k)).setValue(BlockStateProperties.AXIS, axis));
                } else {
                    placeLog(level, placer, random, pos.set(j, yOffset, k), config);
                }
            }
        }
    }

    protected static boolean placeBark(LevelSimulatedReader levelSimulatedReader, BiConsumer<BlockPos, BlockState> placer, Random random, BlockPos blockPos, TreeConfiguration treeConfiguration, Function<BlockState, BlockState> function) {
        if (TreeFeature.validTreePos(levelSimulatedReader, blockPos)) {
            placer.accept(blockPos, function.apply(treeConfiguration.trunkProvider.getState(random, blockPos)));
            return true;
        } else {
            return false;
        }
    }

}
