package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.TerraReforged;
import com.anbaric.terra_reforged.util.handlers.EnumHandler.EnumBiomeBlockType;
import com.anbaric.terra_reforged.util.handlers.EnumHandler.EnumBiomeType;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import javax.annotation.Nullable;
import java.util.Random;

public class TerraBlockSpreading extends Block
{
    private EnumBiomeType biome;
    private PlantType plantType;

    public TerraBlockSpreading(Properties properties, EnumBiomeType biome, @Nullable PlantType plantType)
    {
        super(properties);
        this.biome = biome;
        this.plantType = plantType;
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, @Nullable IPlantable plantable)
    {
        PlantType targetPlant = plantable.getPlantType(world, pos);
        return targetPlant == this.plantType;
    }

    public void tick(BlockState state, World worldIn, BlockPos pos, Random random)
    {
        if (!worldIn.isRemote)
        {
            if (!worldIn.isAreaLoaded(pos, 3))
            {
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            }
            if (TerraReforged.debugSpreading)
            {
                for (int i = 0; i < 4; ++i)
                {
                    BlockPos targetPos = pos.add(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
                    Block targetBlock = worldIn.getBlockState(targetPos).getBlock();

                    if (targetBlock instanceof TerraBlockOre)
                    {
                        worldIn.setBlockState(targetPos, targetBlock.getDefaultState().with(TerraBlockOre.BIOME, this.biome.getOreBiome()));
                    }
//                    if (targetBlock == Blocks.IRON_ORE) { worldIn.setBlockState(targetPos, TerraBlockRegistry.ORE_IRON.get().getDefaultState().with(TerraBlockOre.BIOME, this.biome.getOreBiome())); }
//                    if (targetBlock == Blocks.GOLD_ORE) { worldIn.setBlockState(targetPos, TerraBlockRegistry.ORE_GOLD.get().getDefaultState().with(TerraBlockOre.BIOME, this.biome.getOreBiome())); }
//                    if (targetBlock == Blocks.COAL_ORE) { worldIn.setBlockState(targetPos, TerraBlockRegistry.ORE_COAL.get().getDefaultState().with(TerraBlockOre.BIOME, this.biome.getOreBiome())); }
//                    if (targetBlock == Blocks.LAPIS_ORE) { worldIn.setBlockState(targetPos, TerraBlockRegistry.ORE_LAPIS.get().getDefaultState().with(TerraBlockOre.BIOME, this.biome.getOreBiome())); }
//                    if (targetBlock == Blocks.REDSTONE_ORE) { worldIn.setBlockState(targetPos, TerraBlockRegistry.ORE_REDSTONE.get().getDefaultState().with(TerraBlockOre.BIOME, this.biome.getOreBiome())); }
//                    if (targetBlock == Blocks.DIAMOND_ORE) { worldIn.setBlockState(targetPos, TerraBlockRegistry.ORE_DIAMOND.get().getDefaultState().with(TerraBlockOre.BIOME, this.biome.getOreBiome())); }
//                    if (targetBlock == Blocks.EMERALD_ORE) { worldIn.setBlockState(targetPos, TerraBlockRegistry.ORE_EMERALD.get().getDefaultState().with(TerraBlockOre.BIOME, this.biome.getOreBiome())); }
                    if (checkTransformable(targetBlock))
                    {
                        worldIn.setBlockState(targetPos, transformedState(biome, targetBlock).getDefaultState());
                    }
                }
            }
        }
    }

    public Block transformedState(EnumBiomeType type, Block target)
    {
        Block result = target;
        for (EnumBiomeBlockType block : EnumBiomeBlockType.values())
        {
            if (block.pure == target)
            {
                result = block.getBiomeBlock(type);
            }
        }
        return result;
    }

    public boolean checkTransformable(Block target)
    {
        boolean check = false;
        for (EnumBiomeBlockType block : EnumBiomeBlockType.values())
        {
            if (block.pure == target)
            {
                check = true;
            }
        }
        return check;
    }
}


