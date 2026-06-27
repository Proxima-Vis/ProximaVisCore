package net.tycothepug.proximavis;

import com.mojang.logging.LogUtils;
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
}
