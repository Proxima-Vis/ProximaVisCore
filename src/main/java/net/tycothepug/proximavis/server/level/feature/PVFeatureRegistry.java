package net.tycothepug.proximavis.server.level.feature;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tycothepug.proximavis.ProximaVis;
import net.tycothepug.proximavis.server.level.feature.config.GuangdedendronConfiguration;
import net.tycothepug.proximavis.server.level.feature.config.JaggedRockConfiguration;

public class PVFeatureRegistry {
    public static final DeferredRegister<Feature<?>> DEF_REG = DeferredRegister.create(ForgeRegistries.FEATURES, ProximaVis.MOD_ID);

    public static final RegistryObject<Feature<JaggedRockConfiguration>> JAGGED_ROCK = DEF_REG.register("jagged_rock", () -> new JaggedRock(JaggedRockConfiguration.CODEC));
    public static final RegistryObject<Feature<GuangdedendronConfiguration>> GUANGDEDENDRON = DEF_REG.register("guangdedendron", () -> new Guangdedendron(GuangdedendronConfiguration.CODEC));
}
