package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraParticleRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class TerraBlockTorch extends Block
{
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);

    public TerraBlockTorch(Block.Properties properties)
    {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState());
    }

    public IParticleData getParticle()
    {
        if (this == TerraBlockRegistry.TORCH_GEM_RED.get()) { return TerraParticleRegistry.TORCH_FLAME_RED.get(); }
        else if (this == TerraBlockRegistry.TORCH_GEM_ORANGE.get()) { return TerraParticleRegistry.TORCH_FLAME_ORANGE.get(); }
        else if (this == TerraBlockRegistry.TORCH_GEM_YELLOW.get()) { return TerraParticleRegistry.TORCH_FLAME_YELLOW.get(); }
        else if (this == TerraBlockRegistry.TORCH_GEM_GREEN.get()) { return TerraParticleRegistry.TORCH_FLAME_GREEN.get(); }
        else if (this == TerraBlockRegistry.TORCH_GEM_BLUE.get()) { return TerraParticleRegistry.TORCH_FLAME_BLUE.get(); }
        else if (this == TerraBlockRegistry.TORCH_GEM_PURPLE.get()) { return TerraParticleRegistry.TORCH_FLAME_PURPLE.get(); }
        else if (this == TerraBlockRegistry.TORCH_GEM_WHITE.get()) { return TerraParticleRegistry.TORCH_FLAME_WHITE.get(); }
        else if (this == TerraBlockRegistry.TORCH_RAINBOW.get()) { return TerraParticleRegistry.TORCH_FLAME_RAINBOW.get(); }
        else if (this == TerraBlockRegistry.TORCH_ICE.get()) { return TerraParticleRegistry.TORCH_FLAME_ICE.get(); }
        else if (this == TerraBlockRegistry.TORCH_PINK.get()) { return TerraParticleRegistry.TORCH_FLAME_PINK.get(); }
        else if (this == TerraBlockRegistry.TORCH_BONE.get()) { return TerraParticleRegistry.TORCH_FLAME_BONE.get(); }
        else if (this == TerraBlockRegistry.TORCH_BRIGHT.get()) { return TerraParticleRegistry.TORCH_FLAME_BRIGHT.get(); }
        else if (this == TerraBlockRegistry.TORCH_DESERT.get()) { return TerraParticleRegistry.TORCH_FLAME_DESERT.get(); }
        else if (this == TerraBlockRegistry.TORCH_CORRUPT.get()) { return TerraParticleRegistry.TORCH_FLAME_CORRUPT.get(); }
        else if (this == TerraBlockRegistry.TORCH_CRIMSON.get()) { return TerraParticleRegistry.TORCH_FLAME_CRIMSON.get(); }
        else if (this == TerraBlockRegistry.TORCH_HALLOWED.get()) { return TerraParticleRegistry.TORCH_FLAME_HALLOWED.get(); }
        else if (this == TerraBlockRegistry.TORCH_JUNGLE.get()) { return TerraParticleRegistry.TORCH_FLAME_JUNGLE.get(); }
        else return this == TerraBlockRegistry.TORCH_DEMON.get() ? TerraParticleRegistry.TORCH_FLAME_DEMON.get() : ParticleTypes.FLAME;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        return facing == Direction.DOWN && !this.isValidPosition(stateIn, worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        return hasEnoughSolidSide(worldIn, pos.down(), Direction.UP);
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        double d0 = (double) pos.getX() + 0.5D;
        double d1 = (double) pos.getY() + 0.7D;
        double d2 = (double) pos.getZ() + 0.5D;
        worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        worldIn.addParticle(getParticle(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }
}
