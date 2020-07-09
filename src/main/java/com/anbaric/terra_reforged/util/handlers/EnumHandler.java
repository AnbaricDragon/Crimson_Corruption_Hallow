package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.IStringSerializable;

public class EnumHandler
{
    public enum EnumBiomeType
    {
        PURE("pure"),
        CORRUPT("corrupt"),
        CRIMSON("crimson"),
        HALLOWED("hallowed");

        private final String biome;

        EnumBiomeType(String biome)
        {
            this.biome = biome;
        }
    }

    public enum OreBiomes implements IStringSerializable
    {
        PURE("pure", EnumBiomeType.PURE),
        CORRUPT("corrupt", EnumBiomeType.CORRUPT),
        CRIMSON("crimson", EnumBiomeType.CRIMSON),
        HALLOWED("hallowed", EnumBiomeType.HALLOWED);

        private final String name;
        private final EnumBiomeType biome;

        OreBiomes(String name, EnumBiomeType biome)
        {
            this.name = name;
            this.biome = biome;
        }

        public String toString()
        {
            return this.getName();
        }

        public String getName()
        {
            return this.name;
        }
    }

    public enum EnumBiomeBlockType
    {
        GRASS(Blocks.GRASS_BLOCK, TerraBlockRegistry.GRASS_CORRUPT.get(), TerraBlockRegistry.GRASS_CRIMSON.get(), TerraBlockRegistry.GRASS_HALLOWED.get()),
        GRASSJUNGLE(TerraBlockRegistry.GRASS_JUNGLE.get(), TerraBlockRegistry.SOIL_MUD.get(), TerraBlockRegistry.SOIL_MUD.get(), TerraBlockRegistry.GRASS_JUNGLE.get()),
        MUD(TerraBlockRegistry.SOIL_MUD.get(), Blocks.DIRT, Blocks.DIRT, TerraBlockRegistry.SOIL_MUD.get()),
        SAND(Blocks.SAND, TerraBlockRegistry.SAND_EBON.get(), TerraBlockRegistry.SAND_CRIM.get(), TerraBlockRegistry.SAND_PEARL.get()),
        HARDSAND(TerraBlockRegistry.SAND_HARD.get(), TerraBlockRegistry.SAND_HARDEBON.get(), TerraBlockRegistry.SAND_HARDCRIM.get(), TerraBlockRegistry.SAND_HARDPEARL.get()),
        SANDSTONE(Blocks.SANDSTONE, TerraBlockRegistry.SANDSTONE_EBON.get(), TerraBlockRegistry.SANDSTONE_CRIM.get(), TerraBlockRegistry.SANDSTONE_PEARL.get()),
        CUTSANDSTONE(Blocks.CUT_SANDSTONE, TerraBlockRegistry.SANDSTONE_EBON_CUT.get(), TerraBlockRegistry.SANDSTONE_CRIM_CUT.get(), TerraBlockRegistry.SANDSTONE_PEARL_CUT.get()),
        SMOOTHSANDSTONE(Blocks.SMOOTH_SANDSTONE, TerraBlockRegistry.SANDSTONE_EBON_SMOOTH.get(), TerraBlockRegistry.SANDSTONE_CRIM_SMOOTH.get(), TerraBlockRegistry.SANDSTONE_PEARL_SMOOTH.get()),
        CHISELEDSANDSTONE(Blocks.CHISELED_SANDSTONE, TerraBlockRegistry.SANDSTONE_EBON_CHISELED.get(), TerraBlockRegistry.SANDSTONE_CRIM_CHISELED.get(), TerraBlockRegistry.SANDSTONE_PEARL_CHISELED.get()),
        SNOW(Blocks.SNOW_BLOCK, TerraBlockRegistry.SNOW_CORRUPT.get(), TerraBlockRegistry.SNOW_CRIMSON.get(), TerraBlockRegistry.SNOW_HALLOWED.get()),
        SNOWLAYER(Blocks.SNOW, TerraBlockRegistry.SNOW_CORRUPT_LAYER.get(), TerraBlockRegistry.SNOW_CRIMSON_LAYER.get(), TerraBlockRegistry.SNOW_HALLOWED_LAYER.get()),
        ICE(Blocks.ICE, TerraBlockRegistry.ICE_PURPLE.get(), TerraBlockRegistry.ICE_RED.get(), TerraBlockRegistry.ICE_PINK.get()),
        HARDICE(Blocks.PACKED_ICE, TerraBlockRegistry.ICE_HARD_PURPLE.get(), TerraBlockRegistry.ICE_HARD_RED.get(), TerraBlockRegistry.ICE_HARD_PINK.get()),
        STONE(Blocks.STONE, TerraBlockRegistry.STONE_EBON.get(), TerraBlockRegistry.STONE_CRIM.get(), TerraBlockRegistry.STONE_PEARL.get()),
        COBBLE(Blocks.COBBLESTONE, TerraBlockRegistry.COBBLESTONE_EBON.get(), TerraBlockRegistry.COBBLESTONE_CRIM.get(), TerraBlockRegistry.COBBLESTONE_PEARL.get()),
        MOSSYCOBBLE(Blocks.MOSSY_COBBLESTONE, TerraBlockRegistry.COBBLESTONE_EBON_MOSSY.get(), TerraBlockRegistry.COBBLESTONE_CRIM_MOSSY.get(), TerraBlockRegistry.COBBLESTONE_PEARL_MOSSY.get()),
        STONEBRICK(Blocks.STONE_BRICKS, TerraBlockRegistry.STONEBRICK_EBON.get(), TerraBlockRegistry.STONEBRICK_CRIM.get(), TerraBlockRegistry.STONEBRICK_PEARL.get()),
        CRACKEDSTONEBRICK(Blocks.CRACKED_STONE_BRICKS, TerraBlockRegistry.STONEBRICK_EBON_CRACKED.get(), TerraBlockRegistry.STONEBRICK_CRIM_CRACKED.get(), TerraBlockRegistry.STONEBRICK_PEARL_CRACKED.get()),
        CHISELEDSTONEBRICK(Blocks.CHISELED_STONE_BRICKS, TerraBlockRegistry.STONEBRICK_EBON_CHISELED.get(), TerraBlockRegistry.STONEBRICK_CRIM_CHISELED.get(), TerraBlockRegistry.STONEBRICK_PEARL_CHISELED.get()),
        MOSSYSTONEBRICK(Blocks.MOSSY_STONE_BRICKS, TerraBlockRegistry.STONEBRICK_EBON_MOSSY.get(), TerraBlockRegistry.STONEBRICK_CRIM_MOSSY.get(), TerraBlockRegistry.STONEBRICK_PEARL_MOSSY.get()),
        CACTUS(Blocks.CACTUS, TerraBlockRegistry.CACTUS_EBON.get(), TerraBlockRegistry.CACTUS_CRIM.get(), TerraBlockRegistry.CACTUS_PEARL.get()),
        ORECOPPER(TerraBlockRegistry.ORE_COPPER_PURE.get(), TerraBlockRegistry.ORE_COPPER_CORRUPT.get(), TerraBlockRegistry.ORE_COPPER_CRIMSON.get(), TerraBlockRegistry.ORE_COPPER_HALLOWED.get()),
        ORETIN(TerraBlockRegistry.ORE_TIN_PURE.get(), TerraBlockRegistry.ORE_TIN_CORRUPT.get(), TerraBlockRegistry.ORE_TIN_CRIMSON.get(), TerraBlockRegistry.ORE_TIN_HALLOWED.get()),
        ORELEAD(TerraBlockRegistry.ORE_LEAD_PURE.get(), TerraBlockRegistry.ORE_LEAD_CORRUPT.get(), TerraBlockRegistry.ORE_LEAD_CRIMSON.get(), TerraBlockRegistry.ORE_LEAD_HALLOWED.get()),
        OREIRON(Blocks.IRON_ORE, TerraBlockRegistry.ORE_IRON_CORRUPT.get(), TerraBlockRegistry.ORE_IRON_CRIMSON.get(), TerraBlockRegistry.ORE_IRON_HALLOWED.get()),
        ORESILVER(TerraBlockRegistry.ORE_SILVER_PURE.get(), TerraBlockRegistry.ORE_SILVER_CORRUPT.get(), TerraBlockRegistry.ORE_SILVER_CRIMSON.get(), TerraBlockRegistry.ORE_SILVER_HALLOWED.get()),
        ORETUNGSTEN(TerraBlockRegistry.ORE_TUNGSTEN_PURE.get(), TerraBlockRegistry.ORE_TUNGSTEN_CORRUPT.get(), TerraBlockRegistry.ORE_TUNGSTEN_CRIMSON.get(), TerraBlockRegistry.ORE_TUNGSTEN_HALLOWED.get()),
        OREGOLD(Blocks.GOLD_ORE, TerraBlockRegistry.ORE_GOLD_CORRUPT.get(), TerraBlockRegistry.ORE_GOLD_CRIMSON.get(), TerraBlockRegistry.ORE_GOLD_HALLOWED.get()),
        OREPLATINUM(TerraBlockRegistry.ORE_PLATINUM_PURE.get(), TerraBlockRegistry.ORE_PLATINUM_CORRUPT.get(), TerraBlockRegistry.ORE_PLATINUM_CRIMSON.get(), TerraBlockRegistry.ORE_PLATINUM_HALLOWED.get()),
        OREDEMONITE(TerraBlockRegistry.ORE_DEMONITE_PURE.get(), TerraBlockRegistry.ORE_DEMONITE_CORRUPT.get(), TerraBlockRegistry.ORE_DEMONITE_CRIMSON.get(), TerraBlockRegistry.ORE_DEMONITE_HALLOWED.get()),
        ORECRIMTANE(TerraBlockRegistry.ORE_CRIMTANE_PURE.get(), TerraBlockRegistry.ORE_CRIMTANE_CORRUPT.get(), TerraBlockRegistry.ORE_CRIMTANE_CRIMSON.get(), TerraBlockRegistry.ORE_CRIMTANE_HALLOWED.get()),
        ORECOBALT(TerraBlockRegistry.ORE_COBALT_PURE.get(), TerraBlockRegistry.ORE_COBALT_CORRUPT.get(), TerraBlockRegistry.ORE_COBALT_CRIMSON.get(), TerraBlockRegistry.ORE_COBALT_HALLOWED.get()),
        OREPALLADIUM(TerraBlockRegistry.ORE_PALLADIUM_PURE.get(), TerraBlockRegistry.ORE_PALLADIUM_CORRUPT.get(), TerraBlockRegistry.ORE_PALLADIUM_CRIMSON.get(), TerraBlockRegistry.ORE_PALLADIUM_HALLOWED.get()),
        OREMYTHRIL(TerraBlockRegistry.ORE_MYTHRIL_PURE.get(), TerraBlockRegistry.ORE_MYTHRIL_CORRUPT.get(), TerraBlockRegistry.ORE_MYTHRIL_CRIMSON.get(), TerraBlockRegistry.ORE_MYTHRIL_HALLOWED.get()),
        OREORICHALCUM(TerraBlockRegistry.ORE_ORICHALCUM_PURE.get(), TerraBlockRegistry.ORE_ORICHALCUM_CORRUPT.get(), TerraBlockRegistry.ORE_ORICHALCUM_CRIMSON.get(), TerraBlockRegistry.ORE_ORICHALCUM_HALLOWED.get()),
        OREADAMANTITE(TerraBlockRegistry.ORE_ADAMANTITE_PURE.get(), TerraBlockRegistry.ORE_ADAMANTITE_CORRUPT.get(), TerraBlockRegistry.ORE_ADAMANTITE_CRIMSON.get(), TerraBlockRegistry.ORE_ADAMANTITE_HALLOWED.get()),
        ORETITANIUM(TerraBlockRegistry.ORE_TITANIUM_PURE.get(), TerraBlockRegistry.ORE_TITANIUM_CORRUPT.get(), TerraBlockRegistry.ORE_TITANIUM_CRIMSON.get(), TerraBlockRegistry.ORE_TITANIUM_HALLOWED.get()),
        ORECOAL(Blocks.COAL_ORE, TerraBlockRegistry.ORE_COAL_CORRUPT.get(), TerraBlockRegistry.ORE_COAL_CRIMSON.get(), TerraBlockRegistry.ORE_COAL_HALLOWED.get()),
        ORELAPIS(Blocks.LAPIS_ORE, TerraBlockRegistry.ORE_LAPIS_CORRUPT.get(), TerraBlockRegistry.ORE_LAPIS_CRIMSON.get(), TerraBlockRegistry.ORE_LAPIS_HALLOWED.get()),
        OREREDSTONE(Blocks.REDSTONE_ORE, TerraBlockRegistry.ORE_REDSTONE_CORRUPT.get(), TerraBlockRegistry.ORE_REDSTONE_CRIMSON.get(), TerraBlockRegistry.ORE_REDSTONE_HALLOWED.get()),
        OREAMETHYST(TerraBlockRegistry.ORE_AMETHYST_PURE.get(), TerraBlockRegistry.ORE_AMETHYST_CORRUPT.get(), TerraBlockRegistry.ORE_AMETHYST_CRIMSON.get(), TerraBlockRegistry.ORE_AMETHYST_HALLOWED.get()),
        ORETOPAZ(TerraBlockRegistry.ORE_TOPAZ_PURE.get(), TerraBlockRegistry.ORE_TOPAZ_CORRUPT.get(), TerraBlockRegistry.ORE_TOPAZ_CRIMSON.get(), TerraBlockRegistry.ORE_TOPAZ_HALLOWED.get()),
        ORESAPPHIRE(TerraBlockRegistry.ORE_SAPPHIRE_PURE.get(), TerraBlockRegistry.ORE_SAPPHIRE_CORRUPT.get(), TerraBlockRegistry.ORE_SAPPHIRE_CRIMSON.get(), TerraBlockRegistry.ORE_SAPPHIRE_HALLOWED.get()),
        ORERUBY(TerraBlockRegistry.ORE_RUBY_PURE.get(), TerraBlockRegistry.ORE_RUBY_CORRUPT.get(), TerraBlockRegistry.ORE_RUBY_CRIMSON.get(), TerraBlockRegistry.ORE_RUBY_HALLOWED.get()),
        OREEMERALD(Blocks.EMERALD_ORE, TerraBlockRegistry.ORE_EMERALD_CORRUPT.get(), TerraBlockRegistry.ORE_EMERALD_CRIMSON.get(), TerraBlockRegistry.ORE_EMERALD_HALLOWED.get()),
        OREDIAMOND(Blocks.DIAMOND_ORE, TerraBlockRegistry.ORE_DIAMOND_CORRUPT.get(), TerraBlockRegistry.ORE_DIAMOND_CRIMSON.get(), TerraBlockRegistry.ORE_DIAMOND_HALLOWED.get()),
        STAIRSTONE(Blocks.STONE_STAIRS, TerraBlockRegistry.STAIR_STONE_EBON.get(), TerraBlockRegistry.STAIR_STONE_CRIM.get(), TerraBlockRegistry.STAIR_STONE_PEARL.get()),
        STAIRBRICKSTONE(Blocks.STONE_BRICK_STAIRS, TerraBlockRegistry.STAIR_STONEBRICK_EBON.get(), TerraBlockRegistry.STAIR_STONEBRICK_CRIM.get(), TerraBlockRegistry.STAIR_STONEBRICK_PEARL.get()),
        STAIRBRICKMOSSYSTONE(Blocks.MOSSY_STONE_BRICK_STAIRS, TerraBlockRegistry.STAIR_STONEBRICK_EBON_MOSSY.get(), TerraBlockRegistry.STAIR_STONEBRICK_CRIM_MOSSY.get(), TerraBlockRegistry.STAIR_STONEBRICK_PEARL_MOSSY.get()),
        STAIRCOBBLESTONE(Blocks.COBBLESTONE_STAIRS, TerraBlockRegistry.STAIR_COBBLESTONE_EBON.get(), TerraBlockRegistry.STAIR_COBBLESTONE_CRIM.get(), TerraBlockRegistry.STAIR_COBBLESTONE_PEARL.get()),
        STAIRCOBBLEMOSSYSTONE(Blocks.MOSSY_COBBLESTONE_STAIRS, TerraBlockRegistry.STAIR_COBBLESTONE_EBON_MOSSY.get(), TerraBlockRegistry.STAIR_COBBLESTONE_CRIM_MOSSY.get(), TerraBlockRegistry.STAIR_COBBLESTONE_PEARL_MOSSY.get()),
        STAIRSANDSTONE(Blocks.SANDSTONE_STAIRS, TerraBlockRegistry.STAIR_SANDSTONE_EBON.get(), TerraBlockRegistry.STAIR_SANDSTONE_CRIM.get(), TerraBlockRegistry.STAIR_SANDSTONE_PEARL.get()),
        STAIRSANDSTONESMOOTH(Blocks.SMOOTH_SANDSTONE_STAIRS, TerraBlockRegistry.STAIR_SANDSTONE_EBON_SMOOTH.get(), TerraBlockRegistry.STAIR_SANDSTONE_CRIM_SMOOTH.get(), TerraBlockRegistry.STAIR_SANDSTONE_PEARL_SMOOTH.get()),
        SLABSTONE(Blocks.STONE_SLAB, TerraBlockRegistry.SLAB_STONE_EBON.get(), TerraBlockRegistry.SLAB_STONE_CRIM.get(), TerraBlockRegistry.SLAB_STONE_PEARL.get()),
        SLABSTONESMOOTH(Blocks.SMOOTH_STONE_SLAB, TerraBlockRegistry.SLAB_STONE_EBON_SMOOTH.get(), TerraBlockRegistry.SLAB_STONE_CRIM_SMOOTH.get(), TerraBlockRegistry.SLAB_STONE_PEARL_SMOOTH.get()),
        SLABBRICKSTONE(Blocks.STONE_BRICK_SLAB, TerraBlockRegistry.SLAB_STONEBRICK_EBON.get(), TerraBlockRegistry.SLAB_STONEBRICK_CRIM.get(), TerraBlockRegistry.SLAB_STONEBRICK_PEARL.get()),
        SLABBRICKSTONEMOSSY(Blocks.MOSSY_STONE_BRICK_SLAB, TerraBlockRegistry.SLAB_STONEBRICK_EBON_MOSSY.get(), TerraBlockRegistry.SLAB_STONEBRICK_CRIM_MOSSY.get(), TerraBlockRegistry.SLAB_STONEBRICK_PEARL_MOSSY.get()),
        SLABCOBBLESTONE(Blocks.COBBLESTONE_SLAB, TerraBlockRegistry.SLAB_COBBLESTONE_EBON.get(), TerraBlockRegistry.SLAB_COBBLESTONE_CRIM.get(), TerraBlockRegistry.SLAB_COBBLESTONE_PEARL.get()),
        SLABCOBBLESTONEMOSSY(Blocks.MOSSY_COBBLESTONE_SLAB, TerraBlockRegistry.SLAB_COBBLESTONE_EBON_MOSSY.get(), TerraBlockRegistry.SLAB_COBBLESTONE_CRIM_MOSSY.get(), TerraBlockRegistry.SLAB_COBBLESTONE_PEARL_MOSSY.get()),
        SLABSANDSTONE(Blocks.SANDSTONE_SLAB, TerraBlockRegistry.SLAB_SANDSTONE_EBON.get(), TerraBlockRegistry.SLAB_SANDSTONE_CRIM.get(), TerraBlockRegistry.SLAB_SANDSTONE_PEARL.get()),
        SLABSANDSTONECUT(Blocks.CUT_SANDSTONE_SLAB, TerraBlockRegistry.SLAB_SANDSTONE_EBON_CUT.get(), TerraBlockRegistry.SLAB_SANDSTONE_CRIM_CUT.get(), TerraBlockRegistry.SLAB_SANDSTONE_PEARL_CUT.get()),
        SLABSANDSTONESMOOTH(Blocks.SMOOTH_SANDSTONE_SLAB, TerraBlockRegistry.SLAB_SANDSTONE_EBON_SMOOTH.get(), TerraBlockRegistry.SLAB_SANDSTONE_CRIM_SMOOTH.get(), TerraBlockRegistry.SLAB_SANDSTONE_PEARL_SMOOTH.get()),
//        SAPLING_A(Blocks.SAPLING.withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.OAK).withProperty(BlockSapling.STAGE, 0), TerraBlockRegistry.SAPLING_EBON, TerraBlockRegistry.SAPLING_SHADE, TerraBlockRegistry.SAPLING_PEARL),
//        SAPLING_B(Blocks.SAPLING.withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.OAK).withProperty(BlockSapling.STAGE, 1), TerraBlockRegistry.SAPLING_EBON, TerraBlockRegistry.SAPLING_SHADE, TerraBlockRegistry.SAPLING_PEARL),
        REEDS(Blocks.SUGAR_CANE, TerraBlockRegistry.REED_CORRUPT.get(), TerraBlockRegistry.REED_CRIMSON.get(), TerraBlockRegistry.REED_HALLOWED.get());
//        TALLGRASS_BOT(Blocks.DOUBLE_PLANT.withProperty(net.minecraft.block.BlockDoublePlant.VARIANT, net.minecraft.block.BlockDoublePlant.EnumPlantType.GRASS).withProperty(net.minecraft.block.BlockDoublePlant.HALF, net.minecraft.block.BlockDoublePlant.EnumBlockHalf.LOWER), TerraBlockRegistry.DOUBLEGRASS_CORRUPT.withProperty(TerraBlockDoublePlant.HALF, EnumHandler.EnumBlockHalf.LOWER), TerraBlockRegistry.DOUBLEGRASS_CRIMSON.withProperty(TerraBlockDoublePlant.HALF, EnumHandler.EnumBlockHalf.LOWER), TerraBlockRegistry.DOUBLEGRASS_HALLOWED.withProperty(TerraBlockDoublePlant.HALF, EnumHandler.EnumBlockHalf.LOWER), TALLGRASS_TOP);

        public Block pure, corrupt, crimson, hallowed;

        EnumBiomeBlockType(Block pure, Block corrupt, Block crimson, Block hallowed)
        {
            this.pure = pure;
            this.corrupt = corrupt;
            this.crimson = crimson;
            this.hallowed = hallowed;
        }

        public Block getBiomeBlock(EnumBiomeType biome)
        {
            switch (biome.biome)
            {
                case "corrupt":
                    return this.corrupt;
                case "crimson":
                    return this.crimson;
                case "hallowed":
                    return this.hallowed;
                default:
                    return this.pure;
            }
        }
    }

    public static enum EnumBlockTier
    {
        WOOD(0, "wood"),
        STONE(1, "stone"),
        IRON(2, "iron"),
        DIAMOND(3, "diamond"),
        CORRUPT(4, "corrupt"),
        MOLTEN(5, "molten"),
        COBALT(6, "cobalt"),
        MYTHRIL(7, "mythril"),
        CHLOROPHYTE(8, "chlorophyte"),
        LUMINITE(9, "luminite");

        private final int tier;
        private final int hardness;
        private final String name;

        private EnumBlockTier(int tier, String name)
        {
            this.tier = tier;
            this.name = name;
            this.hardness = tier * tier;
        }

        public int getHardness()
        {
            return this.hardness;
        }

        public int getLevel()
        {
            return this.tier;
        }

        public int getBlastResist()
        {
            return this.hardness * 3;
        }
    }
}
