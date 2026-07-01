package net.tycothepug.pvcore.server.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.registries.RegistryObject;

public class BrineBlock extends LiquidBlock {
    public BrineBlock(RegistryObject<FlowingFluid> flowingFluid, BlockBehaviour.Properties properties) {
        super (flowingFluid, properties);
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource randomSource) {
        if (randomSource.nextInt(400) == 0) {
            //level.playLocalSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, bleh, SoundSource.BLOCKS, 0.5F, randomSource.nextFloat() * 0.4F + 0.8F, false);
        }
        boolean top = level.getFluidState(pos.above()).isEmpty();
        if (randomSource.nextInt(top ? 10 : 40) == 0) {
            float height = top ? state.getFluidState().getHeight(level, pos) : randomSource.nextFloat();
            //level.addParticle(ACParticleRegistry.ACID_BUBBLE.get(), pos.getX() + randomSource.nextFloat(), pos.getY() + height, pos.getZ() + randomSource.nextFloat(), (randomSource.nextFloat() - 0.5F) * 0.1F, 0.05F + randomSource.nextFloat() * 0.1F, (randomSource.nextFloat() - 0.5F) * 0.1F);
        }
    }

    public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState state2, boolean isMoving) {
        super.onPlace(state, worldIn, pos, state2, isMoving);
    }

    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }
}
