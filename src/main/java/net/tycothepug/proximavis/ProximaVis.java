package net.tycothepug.proximavis;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tycothepug.proximavis.client.ClientProxy;
import net.tycothepug.proximavis.server.CommonProxy;
import net.tycothepug.proximavis.server.block.PVBlockRegistry;
import net.tycothepug.proximavis.server.block.fluid.PVFluidRegistry;
import net.tycothepug.proximavis.server.item.PVItemRegistry;
import net.tycothepug.proximavis.server.level.feature.PVFeatureRegistry;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Mod(ProximaVis.MOD_ID)
public class ProximaVis {
    public static final String MOD_ID = "proximavis";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public ProximaVis() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::clientSetup);
        bus.register(this);

        PVBlockRegistry.BLOCKS.register(bus);
        PVItemRegistry.ITEMS.register(bus);
        PVFluidRegistry.FLUID_DEF_REG.register(bus);
        PVFluidRegistry.FLUID_TYPE_DEF_REG.register(bus);
        PVFeatureRegistry.DEF_REG.register(bus);
        PROXY.commonInit();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> PROXY.clientInit());
    }

    public static final MobCategory GLUGG_AMBIENT = MobCategory.create("GLUGG_AMBIENT", "glugg_ambient", 20, true, false, 64);
    public static final MobCategory GLUGG_CREATURE = MobCategory.create("GLUGG_CREATURE", "glugg_creature", 10, true, false, 96);
    public static final MobCategory GLUGG_LEVIATHAN = MobCategory.create("GLUGG_LEVIATHAN", "glugg_leviathan", 5, true, false, 128);

    public static final Map<ResourceLocation, MobCategory> GLUGG_CATEGORY_MAP = new HashMap<>();

    static {
        registerEntities("unusualfishmod", Set.of("amber_goby", "bark_angelfish", "beaked_herring", "blindsailfin", "circus",
                "copperflame", "coral_skrimp", "deep_crawler", "demon_herring", "drooping_gourami",
                "duality_damselfish", "eyelash", "forkfish", "freshwater_mantis", "hatchet_fish",
                "picklefish", "porcupine_lobsta", "sailor_barb", "sea_mosquito", "sea_spider",
                "sneep_snorp", "spindlefish", "squoddle", "tribble", "triple_twirl_pleco", "wizard_jelly"), GLUGG_AMBIENT);
        registerEntities("alexscaves", Set.of("lanternfish", "tripodfish"), GLUGG_AMBIENT);
        registerEntities("unusual_prehistory", Set.of("jawless_fish"), GLUGG_AMBIENT);

        registerEntities("unusualfishmod", Set.of("gnasher", "shockcat", "celestial", "jungleshark", "rhino_tetra", "roughback_guitarfish", "spoon_shark", "zebra_cornetfish", "volt_angler"), GLUGG_CREATURE);
        registerEntities("alexscaves", Set.of("gossamer_worm"), GLUGG_CREATURE);
        registerEntities("unusual_prehistory", Set.of("lobe_finned_fish"), GLUGG_CREATURE);

        registerEntities("unusual_prehistory", Set.of("aegirocassis"), GLUGG_LEVIATHAN);
    }


    private static void registerEntities(String modid, Set<String> names, MobCategory category) {
        for (String name : names) {
            GLUGG_CATEGORY_MAP.put(new ResourceLocation(modid, name), category);
        }
    }
}
