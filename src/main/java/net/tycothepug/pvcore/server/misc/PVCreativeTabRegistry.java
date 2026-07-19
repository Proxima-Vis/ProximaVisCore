package net.tycothepug.pvcore.server.misc;

import net.minecraft.client.gui.screens.inventory.CreativeInventoryListener;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tycothepug.pvcore.PVCore;
import net.tycothepug.pvcore.server.block.PVBlockRegistry;
import net.tycothepug.pvcore.server.item.CustomTabBehavior;
import net.tycothepug.pvcore.server.item.PVItemRegistry;

public class PVCreativeTabRegistry {

    public static final DeferredRegister<CreativeModeTab> TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PVCore.MOD_ID);

    public static final RegistryObject<CreativeModeTab> PROXIMA_VIS_CORE = TAB.register("proxima_vis_core", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.pvcore.proxima_vis_core"))
            .icon(() -> new ItemStack(PVBlockRegistry.DEEP_REEF_POD.get()))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .displayItems((enabledFeatures, output) -> {
                add(output, PVBlockRegistry.DEEP_REEF_POD.get());
                add(output, PVBlockRegistry.BRINE_SALT.get());
                add(output, PVItemRegistry.BRINE_BUCKET.get());
                add(output, PVItemRegistry.PRIMORDIAL_LAVA_BUCKET.get());
                add(output, PVBlockRegistry.ENERGIUM_ICE.get());
                add(output, PVBlockRegistry.ENGERSLATE.get());
                add(output, PVBlockRegistry.ENGERSLATE_STAIRS.get());
                add(output, PVBlockRegistry.ENGERSLATE_SLAB.get());
                add(output, PVBlockRegistry.ENGERSLATE_WALL.get());
                add(output, PVBlockRegistry.COBBLED_ENGERSLATE.get());
                add(output,PVBlockRegistry.COBBLED_ENGERSLATE_STAIRS.get());
                add(output,PVBlockRegistry.COBBLED_ENGERSLATE_SLAB.get());
                add(output, PVBlockRegistry.COBBLED_ENGERSLATE_WALL.get());
            })
            .build());

    private static void add(CreativeModeTab.Output tab, ItemLike itemLike) {
        if (itemLike instanceof CustomTabBehavior customTabBehavior) {
            customTabBehavior.fillItemCategory(tab);
        } else {
            tab.accept(itemLike);
        }
    }
}