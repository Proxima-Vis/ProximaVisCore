package net.tycothepug.proximavis.server.level.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import org.checkerframework.common.value.qual.IntRangeFromPositive;

public class GuangdedendronConfiguration implements FeatureConfiguration {
    public static final Codec<GuangdedendronConfiguration> CODEC = RecordCodecBuilder.create((config) -> {
        return config.group(IntProvider.CODEC.fieldOf("height").forGetter((otherConfig) -> otherConfig.height)).apply(config, GuangdedendronConfiguration::new);
    });
    public final IntProvider height;

    public GuangdedendronConfiguration(IntProvider height) {
        this.height = height;
    }
}
