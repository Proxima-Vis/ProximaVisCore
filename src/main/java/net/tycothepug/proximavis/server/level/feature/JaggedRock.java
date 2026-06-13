package net.tycothepug.proximavis.server.level.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.tycothepug.proximavis.server.level.feature.config.JaggedRockConfiguration;

public class JaggedRock extends Feature<JaggedRockConfiguration> {

    public JaggedRock(Codec<JaggedRockConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<JaggedRockConfiguration> context) {
        BlockPos pos = context.origin();
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        JaggedRockConfiguration config = context.config();

        boolean placed = false;

        for (int c = 0; c <= 3; c++) {
            for (int x = -config.radiusX; x <= config.radiusX; x++) {
                for (int z = -config.radiusZ; z <= config.radiusZ; z++) {
                    int columnErosion = random.nextInt(config.variation + 1);
                    int columnHeight = Math.max(1, config.height - columnErosion - c);

                    for (int y = 0; y < columnHeight; y++) {
                        BlockPos targetPos = pos.offset(x, y, z);

                        if (level.getBlockState(targetPos).canBeReplaced()) {
                            level.setBlock(targetPos, config.block.getState(random, targetPos), 3);
                            placed = true;
                        }
                    }
                }
            }

            Direction edgeDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            switch (edgeDir) {
                case NORTH:
                    pos = pos.offset(random.nextIntBetweenInclusive(-config.radiusX, config.radiusX), 0, -config.radiusZ);
                case SOUTH:
                    pos = pos.offset(random.nextIntBetweenInclusive(-config.radiusX, config.radiusX), 0, config.radiusZ);
                case EAST:
                    pos = pos.offset(config.radiusX, 0, random.nextIntBetweenInclusive(-config.radiusZ, config.radiusZ));
                case WEST:
                    pos = pos.offset(-config.radiusX, 0, random.nextIntBetweenInclusive(-config.radiusZ, config.radiusZ));
            }
        }

        return placed;
    }
}