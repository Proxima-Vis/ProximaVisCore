package net.tycothepug.proximavis.server.level.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class JaggedRockConfiguration implements FeatureConfiguration {
    public static final Codec<JaggedRockConfiguration> CODEC = RecordCodecBuilder.create((config) -> {
        return config.group(BlockStateProvider.CODEC.fieldOf("block").forGetter((otherConfig) -> {
            return otherConfig.block;
        }), Codec.INT.fieldOf("radius_x").forGetter((otherConfig) -> {
            return otherConfig.radiusX;
        }), Codec.INT.fieldOf("radius_z").forGetter((otherConfig) -> {
            return otherConfig.radiusZ;
        }), Codec.INT.fieldOf("variation").forGetter((otherConfig) -> {
            return otherConfig.variation;
        }), Codec.INT.fieldOf("height").forGetter((otherConfig) -> {
            return otherConfig.height;
        })).apply(config, JaggedRockConfiguration::new);
    });
    public final BlockStateProvider block;
    public final int radiusX;
    public final int radiusZ;
    public final int variation;
    public final int height;

    public JaggedRockConfiguration(BlockStateProvider block, int radiusX, int radiusZ, int variation, int height) {
        this.block = block;
        this.radiusX = radiusX;
        this.radiusZ = radiusZ;
        this.variation = variation;
        this.height = height;
    }
}