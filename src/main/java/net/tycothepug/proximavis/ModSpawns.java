package net.tycothepug.proximavis;

import com.github.alexmodguy.alexscaves.server.entity.ACEntityRegistry;
import codyhuh.unusualfishmod.core.registry.UFEntities;
import com.barlinc.unusual_prehistory.registry.UP2Entities;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModSpawns {

    @Mod.EventBusSubscriber(modid = "proximavis", bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModBusEvents {

        @SubscribeEvent
        public static void onSpawnPlacementRegister(SpawnPlacementRegisterEvent event) {
            registerAquatic(event, ACEntityRegistry.LANTERNFISH.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 10, 1);
            registerAquatic(event, ACEntityRegistry.GOSSAMER_WORM.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 10, 0);
            registerAquatic(event, UFEntities.WIZARD_JELLY.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 10, 1);
            registerAquatic(event, UFEntities.SNEEPSNORP.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 10, 1);
            registerAquatic(event, ACEntityRegistry.TRIPODFISH.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 5, 0);
            registerAquatic(event, UFEntities.SEA_SPIDER.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 5, 0);
            registerAquatic(event, UFEntities.SHOCKCAT.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 10, 1);
            registerAquatic(event, UFEntities.GNASHER.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 10, 1);
            registerAquatic(event, UP2Entities.AEGIROCASSIS.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 10, 1);

            registerAquatic(event, UFEntities.PICKLEFISH.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 20, 0);
            registerAquatic(event, UFEntities.VOLT_ANGLER.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 20, 0);

            registerAquatic(event, UFEntities.CIRCUS_FISH.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 10, 0);
            registerAquatic(event, UFEntities.SPINDLEFISH.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 10, 0);
            registerAquatic(event, UFEntities.CELESTIAL_FISH.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 10, 0);
            registerAquatic(event, UFEntities.TIGER_JUNGLE_SHARK.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 10, 0);
            registerAquatic(event, UFEntities.RHINO_TETRA.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 10, 0);
            registerAquatic(event, UFEntities.ROUGHBACK.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 5, 0);
            registerAquatic(event, UFEntities.SPOON_SHARK.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 10, 0);
            registerAquatic(event, UFEntities.ZEBRA_CORNETFISH.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 10, 0);
            registerAquatic(event, UP2Entities.JAWLESS_FISH.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 10, 0);
            registerAquatic(event, UP2Entities.LOBE_FINNED_FISH.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, 10, 0);
        }

        private static void registerAquatic(SpawnPlacementRegisterEvent event, EntityType<?> type, Heightmap.Types heightmapType, int depthOffset, int rarity) {
            event.register(
                    type,
                    SpawnPlacements.Type.IN_WATER,
                    heightmapType,
                    (entityType, level, spawnType, pos, randomSource) -> {
                        if (rarity > 1 && randomSource.nextInt(rarity) != 0) {
                            return false;
                        }

                        int floorY = level.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX(), pos.getZ());

                        if (pos.getY() > floorY + depthOffset) {
                            return false;
                        }

                        if (heightmapType == Heightmap.Types.OCEAN_FLOOR) {
                            if (pos.getY() != floorY + 1) {
                                return false;
                            }
                            BlockState blockBelow = level.getBlockState(pos.below());
                            if (!blockBelow.isFaceSturdy(level, pos.below(), Direction.UP)) {
                                return false;
                            }
                        }

                        return level.getFluidState(pos).is(FluidTags.WATER);
                    },
                    SpawnPlacementRegisterEvent.Operation.REPLACE
            );
        }
    }
}