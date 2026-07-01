package net.tycothepug.pvcore;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tycothepug.pvcore.client.ClientProxy;
import net.tycothepug.pvcore.server.CommonProxy;
import net.tycothepug.pvcore.server.block.PVBlockRegistry;
import net.tycothepug.pvcore.server.block.fluid.PVFluidRegistry;
import net.tycothepug.pvcore.server.item.PVItemRegistry;
import org.slf4j.Logger;

@Mod(PVCore.MOD_ID)
public class PVCore {
    public static final String MOD_ID = "pvcore";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public PVCore() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::clientSetup);
        bus.register(this);

        PVBlockRegistry.BLOCKS.register(bus);
        PVItemRegistry.ITEMS.register(bus);
        PVFluidRegistry.FLUID_DEF_REG.register(bus);
        PVFluidRegistry.FLUID_TYPE_DEF_REG.register(bus);
        PROXY.commonInit();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> PROXY.clientInit());
    }
}
