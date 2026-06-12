package net.tycothepug.proximavis.server.block.fluid;

import com.github.alexmodguy.alexscaves.server.misc.ACSoundRegistry;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tycothepug.proximavis.ProximaVis;
import net.tycothepug.proximavis.server.block.PVBlockRegistry;
import net.tycothepug.proximavis.server.item.PVItemRegistry;

public class PVFluidRegistry {
    public static final DeferredRegister<FluidType> FLUID_TYPE_DEF_REG = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, ProximaVis.MOD_ID);
    public static final DeferredRegister<Fluid> FLUID_DEF_REG = DeferredRegister.create(ForgeRegistries.FLUIDS, ProximaVis.MOD_ID);

    private static ForgeFlowingFluid.Properties brineProperties() {
        return new ForgeFlowingFluid.Properties(BRINE_FLUID_TYPE, BRINE_FLUID_SOURCE, BRINE_FLUID_FLOWING).bucket(PVItemRegistry.BRINE_BUCKET).block(PVBlockRegistry.BRINE);
    }

    public static final RegistryObject<FluidType> BRINE_FLUID_TYPE = FLUID_TYPE_DEF_REG.register("brine", () -> new BrineFluidType(FluidType.Properties.create().density(1000).viscosity(1000).pathType(BlockPathTypes.WATER).adjacentPathType(BlockPathTypes.WATER_BORDER).sound(SoundActions.BUCKET_EMPTY, ACSoundRegistry.PURPLE_SODA_UNSUBMERGE.get()).sound(SoundActions.BUCKET_FILL, ACSoundRegistry.PURPLE_SODA_SUBMERGE.get())));
    public static final RegistryObject<FlowingFluid> BRINE_FLUID_SOURCE = FLUID_DEF_REG.register("brine", () -> new ForgeFlowingFluid.Source(brineProperties()));
    public static final RegistryObject<FlowingFluid> BRINE_FLUID_FLOWING = FLUID_DEF_REG.register("brine_flowing", () -> new ForgeFlowingFluid.Flowing(brineProperties()));
}
