package net.tycothepug.pvcore.server.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
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
    public static final BlockBehaviour.Properties DEEP_REEF_POD_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops().lightLevel(state -> 15).strength(2F, 11.0F).sound(SoundType.FROGLIGHT);
    public static final BlockBehaviour.Properties ICE_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).requiresCorrectToolForDrops().strength(0.5F, 0.5F).sound(SoundType.GLASS).friction(0.98F);
    public static final BlockBehaviour.Properties ENGERSLATE_PROPERTIES = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).requiresCorrectToolForDrops().strength(1.5F, 6F).sound(SoundType.STONE);

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PVCore.MOD_ID);

    public static final RegistryObject<Block> DEEP_REEF_POD = registerBlock("deep_reef_pod", () -> new Block(DEEP_REEF_POD_PROPERTIES));
    public static final RegistryObject<Block> BRINE_SALT = registerBlock("brine_salt", () -> new BrineSalt(BlockBehaviour.Properties.copy(Blocks.MOSS_CARPET).noOcclusion()));
    public static final RegistryObject<Block> ENERGIUM_ICE = registerBlock("energium_ice", () -> new Block(ICE_PROPERTIES));
    public static final RegistryObject<Block> ENGERSLATE = registerBlock("engerslate", () -> new Block(ENGERSLATE_PROPERTIES));
    public static final RegistryObject<Block> ENGERSLATE_STAIRS = registerBlock("engerslate_stairs", () -> new StairBlock(ENGERSLATE.get().defaultBlockState(), ENGERSLATE_PROPERTIES));
    public static final RegistryObject<Block> ENGERSLATE_SLAB = registerBlock("engerslate_slab", () -> new SlabBlock(ENGERSLATE_PROPERTIES));
    public static final RegistryObject<Block> ENGERSLATE_WALL = registerBlock("engerslate_wall", () -> new WallBlock(ENGERSLATE_PROPERTIES));
    public static final RegistryObject<Block> COBBLED_ENGERSLATE = registerBlock("cobbled_engerslate", () -> new Block(ICE_PROPERTIES));
    public static final RegistryObject<Block> COBBLED_ENGERSLATE_STAIRS = registerBlock("cobbled_engerslate_stairs", () -> new StairBlock(ENGERSLATE.get().defaultBlockState(), ENGERSLATE_PROPERTIES));
    public static final RegistryObject<Block> COBBLED_ENGERSLATE_SLAB = registerBlock("cobbled_engerslate_slab", () -> new SlabBlock(ICE_PROPERTIES));
    public static final RegistryObject<Block> COBBLED_ENGERSLATE_WALL = registerBlock("cobbled_engerslate_wall", () -> new WallBlock(ICE_PROPERTIES));


    public static final RegistryObject<LiquidBlock> BRINE = BLOCKS.register("brine", () -> new BrineBlock(PVFluidRegistry.BRINE_FLUID_SOURCE, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).noCollission().strength(100.0F).noLootTable().replaceable().liquid().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<LiquidBlock> PRIMORDIAL_LAVA = BLOCKS.register("primordial_lava", () -> new PrimordialLavaBlock(PVFluidRegistry.PRIMORDIAL_LAVA_FLUID_SOURCE, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).noCollission().strength(100.0F).noLootTable().replaceable().liquid().lightLevel((state) -> 15).pushReaction(PushReaction.DESTROY)));

    private static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier) {
        RegistryObject<B> block = BLOCKS.register(name, supplier);
        PVItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }
}