package com.anbaric.terra_reforged.blocks;

import com.anbaric.terra_reforged.util.handlers.BiomeChangeHandler;
import com.anbaric.terra_reforged.util.handlers.BiomeHandler;
import com.anbaric.terra_reforged.util.handlers.NetworkHandler;
import com.anbaric.terra_reforged.util.init.TerraBiomeRegistry;
import com.anbaric.terra_reforged.util.packets.CorruptBiomePacket;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.fml.network.PacketDistributor;

import net.minecraft.block.AbstractBlock.Properties;

public class BlockTEST extends Block
{
    public BlockTEST(Properties properties)
    {
        super(properties);
    }

//    @Override
//    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
//    {
//        if (!world.isRemote())
//        {
//            System.out.println("Biome at " + pos + " before change is " + world.getBiome(pos).getRegistryName());
//            BiomeChangeHandler.setBiome(world, pos, Biomes.DESERT);
//            System.out.println("Biome at " + pos + " after change is " + world.getBiome(pos).getRegistryName());
//            return ActionResultType.PASS;
//        }
//        NetworkHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new CorruptBiomePacket(pos, Biomes.DESERT.getLocation()));
//        return ActionResultType.FAIL;
//    }
//
//    @Override
//    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving)
//    {
//        if (!world.isRemote())
//        {
//            System.out.println("Biome at " + pos + " is " + world.getBiome(pos));
//            BiomeChangeHandler.setBiome(world, pos, Biomes.DESERT);
//            System.out.println("Biome at " + pos + " is " + world.getBiome(pos));
//        }
//        NetworkHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new CorruptBiomePacket(pos, Biomes.DESERT.getLocation()));
//    }
}
