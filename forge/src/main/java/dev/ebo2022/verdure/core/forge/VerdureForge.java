package dev.ebo2022.verdure.core.forge;

import dev.ebo2022.verdure.core.Verdure;
import net.minecraftforge.fml.common.Mod;

@Mod(Verdure.MOD_ID)
public class VerdureForge {
    public VerdureForge() {
        Verdure.PLATFORM.setup();
    }
}