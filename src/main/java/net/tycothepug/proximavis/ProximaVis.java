package net.tycothepug.proximavis;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ProximaVis.MOD_ID)
public class ProximaVis {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "proximavis";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public ProximaVis(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static final MobCategory GLUGG_AMBIENT = MobCategory.create("GLUGG_AMBIENT", "glugg_ambient", 30, true, false, 128);
    public static final MobCategory GLUGG_CREATURE = MobCategory.create("GLUGG_CREATURE", "glugg_creature", 20, true, false, 128);
    public static final MobCategory GLUGG_LEVIATHAN = MobCategory.create("GLUGG_LEVIATHAN", "glugg_leviathan", 5, true, false, 128);

    public static final Map<ResourceLocation, MobCategory> GLUGG_CATEGORY_MAP = new HashMap<>();

    static {
        registerEntities("unusualfishmod", Set.of("amber_goby", "bark_angelfish", "beaked_herring", "blindsailfin", "circus",
                "copperflame", "coral_skrimp", "deep_crawler", "demon_herring", "drooping_gourami",
                "duality_damselfish", "eyelash", "forkfish", "freshwater_mantis", "hatchet_fish",
                "picklefish", "porcupine_lobsta", "sailor_barb", "sea_mosquito", "sea_spider",
                "sneep_snorp", "spindlefish", "squoddle", "tribble", "triple_twirl_pleco", "wizard_jelly", "copperflame", "picklefish"), GLUGG_AMBIENT);
        registerEntities("alexscaves", Set.of("lanternfish", "tripodfish"), GLUGG_AMBIENT);

        registerEntities("unusualfishmod", Set.of("gnasher", "shockcat"), GLUGG_CREATURE);
        registerEntities("alexscaves", Set.of("gossamer_worm"), GLUGG_CREATURE);

        registerEntities("unusual_prehistory", Set.of("aegirocassis"), GLUGG_LEVIATHAN);
    }

    private static void registerEntities(String modid, Set<String> names, MobCategory category) {
        for (String name : names) {
            GLUGG_CATEGORY_MAP.put(new ResourceLocation(modid, name), category);
        }
    }
}
