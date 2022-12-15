package dev.ebo2022.verdure.core.registry;

import dev.ebo2022.verdure.common.block.CarpetFlowerBlock;
import dev.ebo2022.verdure.common.block.FullFlowerBlock;
import dev.ebo2022.verdure.common.block.PebblesBlock;
import dev.ebo2022.verdure.common.block.RockBlock;
import dev.ebo2022.verdure.core.Verdure;
import dev.ebo2022.verdure.core.registry.util.ModSoundType;
import gg.moonflower.pollen.api.registry.PollinatedBlockRegistry;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import java.util.function.Supplier;

public class VerdureBlocks {

    public static final PollinatedBlockRegistry BLOCKS = PollinatedRegistry.createBlock(VerdureItems.ITEMS);

    public static final Supplier<Block> MORNING_GLORY = BLOCKS.registerWithItem("morning_glory", () -> new CarpetFlowerBlock(MobEffects.ABSORPTION, 8, Properties.FLOWER), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final Supplier<Block> BLUE_MORNING_GLORY = BLOCKS.registerWithItem("blue_morning_glory", () -> new CarpetFlowerBlock(MobEffects.ABSORPTION, 8, Properties.FLOWER), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final Supplier<Block> WHITE_MORNING_GLORY = BLOCKS.registerWithItem("white_morning_glory", () -> new CarpetFlowerBlock(MobEffects.ABSORPTION, 8, Properties.FLOWER), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    public static final Supplier<Block> BALMY_WILDFLOWERS = BLOCKS.registerWithItem("balmy_wildflowers", () -> new FullFlowerBlock(MobEffects.ABSORPTION, 8, Properties.FLOWER), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final Supplier<Block> GLOOM_WILDFLOWERS = BLOCKS.registerWithItem("gloom_wildflowers", () -> new FullFlowerBlock(MobEffects.ABSORPTION, 8, Properties.FLOWER), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final Supplier<Block> PASTURE_WILDFLOWERS = BLOCKS.registerWithItem("pasture_wildflowers", () -> new FullFlowerBlock(MobEffects.ABSORPTION, 8, Properties.FLOWER), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));


    public static final Supplier<Block> PEBBLES = BLOCKS.registerWithItem("pebbles", () -> new PebblesBlock(BlockBehaviour.Properties.of(Material.DECORATION).noOcclusion().sound(ModSoundType.ROCK).instabreak().noCollission()), new Item.Properties().tab(Verdure.TAB));
    public static final Supplier<Block> ROCK = BLOCKS.registerWithItem("rock", () -> new RockBlock(BlockBehaviour.Properties.of(Material.DECORATION).noOcclusion().sound(ModSoundType.ROCK).instabreak().noCollission()), new Item.Properties().tab(Verdure.TAB));
    public static final class Properties {
        public static final BlockBehaviour.Properties FLOWER = BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().noOcclusion().sound(SoundType.GRASS);
    }
}
