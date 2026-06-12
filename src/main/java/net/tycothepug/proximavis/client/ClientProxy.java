package net.tycothepug.proximavis.client;

import codyhuh.unusualfishmod.client.ClientEvents;
import com.github.alexmodguy.alexscaves.server.block.fluid.ACFluidRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tycothepug.proximavis.server.CommonProxy;
import net.tycothepug.proximavis.server.block.PVBlockRegistry;
import net.tycothepug.proximavis.server.block.fluid.PVFluidRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void clientInit() {
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        ItemBlockRenderTypes.setRenderLayer(PVFluidRegistry.BRINE_FLUID_SOURCE.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(PVFluidRegistry.BRINE_FLUID_FLOWING.get(), RenderType.translucent());
    }
}
