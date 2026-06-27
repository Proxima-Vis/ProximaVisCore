package net.tycothepug.proximavis.server.entity;

import codyhuh.unusualfishmod.core.registry.UFEntities;
import com.barlinc.unusual_prehistory.registry.UP2Entities;
import com.github.alexmodguy.alexscaves.server.entity.ACEntityRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tycothepug.proximavis.ProximaVis;
import net.tycothepug.proximavis.server.misc.PVTagRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Mod.EventBusSubscriber(modid = ProximaVis.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PVEntityRegistry {

    public static final MobCategory GLUGG_AMBIENT = MobCategory.create("GLUGG_AMBIENT", "glugg_ambient", 20, true, false, 64);
    public static final MobCategory GLUGG_CREATURE = MobCategory.create("GLUGG_CREATURE", "glugg_creature", 10, true, false, 96);
    public static final MobCategory GLUGG_LEVIATHAN = MobCategory.create("GLUGG_LEVIATHAN", "glugg_leviathan", 5, true, false, 128);

    public static final Map<ResourceLocation, MobCategory> GLUGG_CATEGORY_MAP = new HashMap<>();

    static {
        createMobCategories("unusualfishmod", Set.of("amber_goby", "bark_angelfish", "beaked_herring", "blindsailfin", "circus",
                "copperflame", "coral_skrimp", "deep_crawler", "demon_herring", "drooping_gourami",
                "duality_damselfish", "eyelash", "forkfish", "freshwater_mantis", "hatchet_fish",
                "picklefish", "porcupine_lobsta", "sailor_barb", "sea_mosquito", "sea_spider",
                "sneep_snorp", "spindlefish", "squoddle", "tribble", "triple_twirl_pleco", "wizard_jelly"), GLUGG_AMBIENT);
        createMobCategories("alexscaves", Set.of("lanternfish", "tripodfish"), GLUGG_AMBIENT);

        createMobCategories("unusualfishmod", Set.of("gnasher", "shockcat", "celestial", "jungleshark", "rhino_tetra", "roughback_guitarfish", "spoon_shark", "zebra_cornetfish", "volt_angler"), GLUGG_CREATURE);
        createMobCategories("alexscaves", Set.of("gossamer_worm"), GLUGG_CREATURE);

        createMobCategories("unusual_prehistory", Set.of("aegirocassis"), GLUGG_LEVIATHAN);
    }


    private static void createMobCategories(String modid, Set<String> names, MobCategory category) {
        for (String name : names) {
            GLUGG_CATEGORY_MAP.put(new ResourceLocation(modid, name), category);
        }
    }

    @SubscribeEvent
    public static void spawnPlacements(SpawnPlacementRegisterEvent event) {
        registerFish(event, ACEntityRegistry.LANTERNFISH.get(), 10, 1);
        registerFish(event, ACEntityRegistry.GOSSAMER_WORM.get(), 10, 1);
        registerFish(event, UFEntities.WIZARD_JELLY.get(), 10, 1);
        registerFish(event, UFEntities.SNEEPSNORP.get(), 10, 1);
        registerFish(event, ACEntityRegistry.TRIPODFISH.get(), 5, 1);
        registerFish(event, UFEntities.SEA_SPIDER.get(), 5, 1);
        registerFish(event, UFEntities.SHOCKCAT.get(), 10, 1);
        registerFish(event, UFEntities.GNASHER.get(), 10, 1);
        registerFish(event, UP2Entities.AEGIROCASSIS.get(), 10, 1);
        registerFish(event, UFEntities.PICKLEFISH.get(),20, 1);
        registerFish(event, UFEntities.VOLT_ANGLER.get(),20, 1);
        registerFish(event, UFEntities.CIRCUS_FISH.get(),10, 1);
        registerFish(event, UFEntities.SPINDLEFISH.get(), 10, 1);
        registerFish(event, UFEntities.CELESTIAL_FISH.get(), 10, 1);
        registerFish(event, UFEntities.TIGER_JUNGLE_SHARK.get(), 10, 1);
        registerFish(event, UFEntities.RHINO_TETRA.get(), 10, 1);
        registerFish(event, UFEntities.ROUGHBACK.get(), 5, 1);
        registerFish(event, UFEntities.SPOON_SHARK.get(), 10, 1);
        registerFish(event, UFEntities.ZEBRA_CORNETFISH.get(), 10, 1);

        registerDinosaurs(event, UP2Entities.LYSTROSAURUS.get());
        registerDinosaurs(event, UP2Entities.DESMATOSUCHUS.get());
        registerDinosaurs(event, UP2Entities.KENTROSAURUS.get());
        registerDinosaurs(event, UP2Entities.BRACHIOSAURUS.get());
        registerDinosaurs(event, UP2Entities.KIMMERIDGEBRACHYPTERAESCHNIDIUM.get());
        registerDinosaurs(event, UP2Entities.PTERODACTYLUS.get());
        registerDinosaurs(event, UP2Entities.MAJUNGASAURUS.get());
        registerDinosaurs(event, UP2Entities.PACHYCEPHALOSAURUS.get());
        registerDinosaurs(event, UP2Entities.DROMAEOSAURUS.get());
        registerDinosaurs(event, UP2Entities.CARNOTAURUS.get());
        registerDinosaurs(event, UP2Entities.HIBBERTOPTERUS.get());
    }

    private static void registerFish(SpawnPlacementRegisterEvent event, EntityType<?> type, int depth, int rarity) {
        event.register(
                type,
                SpawnPlacements.Type.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, level, spawnType, pos, randomSource) -> {
                    if (rarity > 1 && randomSource.nextInt(rarity) != 0) {
                        return false;
                    }

                    int floor = level.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX(), pos.getZ());

                    return level.getFluidState(pos).is(FluidTags.WATER) && (pos.getY() < (floor + depth));
                },
                SpawnPlacementRegisterEvent.Operation.REPLACE
        );
    }

    private static void registerDinosaurs(SpawnPlacementRegisterEvent event, EntityType<?> type) {
        event.register(
                type,
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, levelAccessor, spawnType, pos, randomSource) -> {
                    return levelAccessor.getBlockState(pos.below()).is(PVTagRegistry.DINOSAUR_SPAWNABLE) && levelAccessor.getFluidState(pos).isEmpty() && levelAccessor.getFluidState(pos.below()).isEmpty();
                },
                SpawnPlacementRegisterEvent.Operation.REPLACE
        );
    }
}