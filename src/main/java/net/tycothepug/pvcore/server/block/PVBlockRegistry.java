package net.tycothepug.pvcore.server.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tycothepug.pvcore.PVCore;
import net.tycothepug.pvcore.server.block.fluid.PVFluidRegistry;
import net.tycothepug.pvcore.server.item.PVItemRegistry;

import java.util.function.Supplier;

public class PVBlockRegistry {
    public static final BlockBehaviour.Properties DEEP_REEF_POD_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).requiresCorrectToolForDrops().lightLevel(state -> 15).strength(2F, 11.0F).sound(SoundType.FROGLIGHT);

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PVCore.MOD_ID);
    public static final RegistryObject<Block> DEEP_REEF_POD = registerBlock("deep_reef_pod", () -> new Block(DEEP_REEF_POD_PROPERTIES));
    public static final RegistryObject<Block> BRINE_SALT = registerBlock("brine_salt", () -> new BrineSalt(BlockBehaviour.Properties.copy(Blocks.MOSS_CARPET).noOcclusion()));

    public static final RegistryObject<LiquidBlock> BRINE = BLOCKS.register("brine", () -> new BrineBlock(PVFluidRegistry.BRINE_FLUID_SOURCE, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).noCollission().strength(100.0F).noLootTable().replaceable().liquid().pushReaction(PushReaction.DESTROY)));

    private static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier) {
        RegistryObject<B> block = BLOCKS.register(name, supplier);
        PVItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }
}