package net.tycothepug.pvcore.server.misc;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.tycothepug.pvcore.PVCore;

public class PVTagRegistry {

    public static final TagKey<Block> DINOSAUR_SPAWNABLE = registerBlockTag("dinosaur_spawnable");

    private static TagKey<Block> registerBlockTag(String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(PVCore.MOD_ID, name));
    }
}
