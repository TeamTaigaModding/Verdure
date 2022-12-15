package dev.ebo2022.verdure.common.block.properties;

import net.minecraft.util.StringRepresentable;

public enum PebblesType implements StringRepresentable {
    SAND("sand"),
    GRASS("grass"),
    STONE("stone");

    private final String name;

    PebblesType(String type) {
        this.name = type;
    }

    @Override
    public String toString() {
        return this.getSerializedName();
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
