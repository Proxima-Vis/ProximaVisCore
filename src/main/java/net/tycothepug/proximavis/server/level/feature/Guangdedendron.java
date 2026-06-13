package net.tycothepug.proximavis.server.level.feature;

import com.barlinc.unusual_prehistory.registry.UP2Blocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.tycothepug.proximavis.server.level.feature.config.GuangdedendronConfiguration;

public class Guangdedendron extends Feature<GuangdedendronConfiguration> {

    public Guangdedendron(Codec<GuangdedendronConfiguration> codec) {
        super(codec);
    }

    public static final EnumProperty<BambooLeaves> LEAVES = BlockStateProperties.BAMBOO_LEAVES;
    public static final IntegerProperty STAGE = BlockStateProperties.STAGE;

    public boolean place(FeaturePlaceContext<GuangdedendronConfiguration> context) {
        BlockPos pos = context.origin();
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        GuangdedendronConfiguration config = context.config();
        BambooLeaves leaves = BambooLeaves.NONE;

        BlockState guangdedendronNone = UP2Blocks.GUANGDEDENDRON.get().defaultBlockState().setValue(LEAVES, BambooLeaves.NONE);
        BlockState guangdedendronSmall = UP2Blocks.GUANGDEDENDRON.get().defaultBlockState().setValue(LEAVES, BambooLeaves.SMALL);
        BlockState guangdedendronLarge = UP2Blocks.GUANGDEDENDRON.get().defaultBlockState().setValue(LEAVES, BambooLeaves.LARGE).setValue(STAGE, 1);

        boolean placed = false;

        for (int y = 0; y <= config.height; y++) {
            BlockPos targetPos = pos.offset(0, y, 0);

            if (level.getBlockState(targetPos).canBeReplaced()) {
                level.setBlock(targetPos, guangdedendronNone, 3);
                placed = true;
            }
        }
        BlockPos targetPos = pos.offset(0, config.height, 0);
        if (level.getBlockState(targetPos).is(UP2Blocks.GUANGDEDENDRON.get())) {
            level.setBlock(targetPos, guangdedendronLarge, 3);
            if (level.getBlockState(targetPos.below(2)).is(UP2Blocks.GUANGDEDENDRON.get())) {
                level.setBlock(targetPos.below(), guangdedendronSmall, 3);
                level.setBlock(targetPos.below(2), guangdedendronSmall, 3);
            }
        }

        return placed;
    }
}
