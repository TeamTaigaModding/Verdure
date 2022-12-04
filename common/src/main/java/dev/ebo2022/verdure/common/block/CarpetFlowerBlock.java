package dev.ebo2022.verdure.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecation")
public class CarpetFlowerBlock extends FlowerBlock {

    private static final VoxelShape SHAPE = box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D);

    public CarpetFlowerBlock(MobEffect mobEffect, int i, Properties properties) {
        super(mobEffect, i, properties);
    }

    @Override
    public OffsetType getOffsetType() {
        return OffsetType.NONE;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    public boolean canBeReplaced(BlockState blockState, BlockPlaceContext blockPlaceContext) {
        return true;
    }
}
