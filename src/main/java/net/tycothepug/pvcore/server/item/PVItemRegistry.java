package net.tycothepug.pvcore.server.item;

import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tycothepug.pvcore.PVCore;
import net.tycothepug.pvcore.server.block.fluid.PVFluidRegistry;

import java.util.function.Supplier;

public class PVItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PVCore.MOD_ID);

    public static final RegistryObject<Item> BRINE_BUCKET = ITEMS.register("brine_bucket", () -> new BucketItem(PVFluidRegistry.BRINE_FLUID_SOURCE, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    private static <I extends Item> RegistryObject<I> registerItem(String name, Supplier<? extends I> supplier) {
        RegistryObject<I> item = ITEMS.register(name, supplier);
        return item;
    }
}
