package dev.ebo2022.verdure.core.registry.util;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;

import java.util.function.Supplier;

public class ModSoundType extends SoundType {

    public static final ModSoundType ROCK = new ModSoundType(1.0F, 2.0F, () -> SoundEvents.STONE_BREAK, () -> SoundEvents.STONE_STEP, () -> SoundEvents.STONE_PLACE, () -> SoundEvents.STONE_HIT, () -> SoundEvents.STONE_FALL);


    private final Supplier<SoundEvent> breakSound;
    private final Supplier<SoundEvent> stepSound;
    private final Supplier<SoundEvent> placeSound;
    private final Supplier<SoundEvent> hitSound;
    private final Supplier<SoundEvent> fallSound;

    public ModSoundType(float volume, float pitch, Supplier<SoundEvent> breakSound, Supplier<SoundEvent> stepSound, Supplier<SoundEvent> placeSound, Supplier<SoundEvent> hitSound, Supplier<SoundEvent> fallSound) {
        super(volume, pitch, (SoundEvent) null, (SoundEvent) null, (SoundEvent) null, (SoundEvent) null, (SoundEvent) null);
        this.breakSound = breakSound;
        this.stepSound = stepSound;
        this.placeSound = placeSound;
        this.hitSound = hitSound;
        this.fallSound = fallSound;
    }

    @Override
    public SoundEvent getBreakSound() {
        return this.breakSound.get();
    }

    @Override
    public SoundEvent getStepSound() {
        return this.stepSound.get();
    }

    @Override
    public SoundEvent getPlaceSound() {
        return this.placeSound.get();
    }

    @Override
    public SoundEvent getHitSound() {
        return this.hitSound.get();
    }

    @Override
    public SoundEvent getFallSound() {
        return this.fallSound.get();
    }
}
