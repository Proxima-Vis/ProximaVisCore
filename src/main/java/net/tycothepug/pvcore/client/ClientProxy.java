package net.tycothepug.pvcore.client;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.tycothepug.pvcore.server.CommonProxy;
import net.tycothepug.pvcore.server.block.fluid.PVFluidRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void clientInit() {
        MinecraftForge.EVENT_BUS.register(new ClientProxy());
        ItemBlockRenderTypes.setRenderLayer(PVFluidRegistry.BRINE_FLUID_SOURCE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(PVFluidRegistry.BRINE_FLUID_FLOWING.get(), RenderType.translucent());
    }
}
