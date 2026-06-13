package net.tycothepug.proximavis.server.level.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class GuangdedendronConfiguration implements FeatureConfiguration {
    public static final Codec<GuangdedendronConfiguration> CODEC = RecordCodecBuilder.create((config) -> {
        return config.group(Codec.INT.fieldOf("height").forGetter((otherConfig) -> {
            return otherConfig.height;
        })).apply(config, GuangdedendronConfiguration::new);
    });
    public final int height;

    public GuangdedendronConfiguration(int height) {
        this.height = height;
    }
}
