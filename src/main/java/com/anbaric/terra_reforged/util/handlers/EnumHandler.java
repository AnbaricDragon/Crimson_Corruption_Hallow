package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.blocks.TerraBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class EnumHandler
{
    public enum EnumBiomeType
    {
        PURE("pure"),
        CORRUPT("corrupt"),
        CRIMSON("crimson"),
        HALLOWED("hallowed");

        private final String biome;

        private EnumBiomeType(String biome)
        {
            this.biome = biome;
        }
    }

    public static enum EnumBiomeBlockType
    {
        GRASS(Blocks.GRASS_BLOCK, TerraBlocks.GRASS_CORRUPT, TerraBlocks.GRASS_CRIMSON, TerraBlocks.GRASS_HALLOWED),
        GRASSJUNGLE(TerraBlocks.GRASS_JUNGLE, TerraBlocks.SOIL_MUD, TerraBlocks.SOIL_MUD, TerraBlocks.GRASS_JUNGLE),
        MUD(TerraBlocks.SOIL_MUD, Blocks.DIRT, Blocks.DIRT, TerraBlocks.SOIL_MUD),
        SAND(Blocks.SAND, TerraBlocks.SAND_EBON, TerraBlocks.SAND_CRIM, TerraBlocks.SAND_PEARL),
        HARDSAND(TerraBlocks.SAND_HARD, TerraBlocks.SAND_HARDEBON, TerraBlocks.SAND_HARDCRIM, TerraBlocks.SAND_HARDPEARL),
        SANDSTONE(Blocks.SANDSTONE, TerraBlocks.SANDSTONE_EBON, TerraBlocks.SANDSTONE_CRIM, TerraBlocks.SANDSTONE_PEARL),
        CUTSANDSTONE(Blocks.CUT_SANDSTONE, TerraBlocks.SANDSTONE_EBON_CUT, TerraBlocks.SANDSTONE_CRIM_CUT, TerraBlocks.SANDSTONE_PEARL_CUT),
        SMOOTHSANDSTONE(Blocks.SMOOTH_SANDSTONE, TerraBlocks.SANDSTONE_EBON_SMOOTH, TerraBlocks.SANDSTONE_CRIM_SMOOTH, TerraBlocks.SANDSTONE_PEARL_SMOOTH),
        CHISELEDSANDSTONE(Blocks.CHISELED_SANDSTONE, TerraBlocks.SANDSTONE_EBON_CHISELED, TerraBlocks.SANDSTONE_CRIM_CHISELED, TerraBlocks.SANDSTONE_PEARL_CHISELED),
        SNOW(Blocks.SNOW_BLOCK, TerraBlocks.SNOW_CORRUPT, TerraBlocks.SNOW_CRIMSON, TerraBlocks.SNOW_HALLOWED),
        SNOWLAYER(Blocks.SNOW, TerraBlocks.SNOW_CORRUPT_LAYER, TerraBlocks.SNOW_CRIMSON_LAYER, TerraBlocks.SNOW_HALLOWED_LAYER),
        ICE(Blocks.ICE, TerraBlocks.ICE_PURPLE, TerraBlocks.ICE_RED, TerraBlocks.ICE_PINK),
        HARDICE(Blocks.PACKED_ICE, TerraBlocks.ICE_HARD_PURPLE, TerraBlocks.ICE_HARD_RED, TerraBlocks.ICE_HARD_PINK),
        STONE(Blocks.STONE, TerraBlocks.STONE_EBON, TerraBlocks.STONE_CRIM, TerraBlocks.STONE_PEARL),
        COBBLE(Blocks.COBBLESTONE, TerraBlocks.COBBLESTONE_EBON, TerraBlocks.COBBLESTONE_CRIM, TerraBlocks.COBBLESTONE_PEARL),
        MOSSYCOBBLE(Blocks.MOSSY_COBBLESTONE, TerraBlocks.COBBLESTONE_EBON_MOSSY, TerraBlocks.COBBLESTONE_CRIM_MOSSY, TerraBlocks.COBBLESTONE_PEARL_MOSSY),
        STONEBRICK(Blocks.STONE_BRICKS, TerraBlocks.STONEBRICK_EBON, TerraBlocks.STONEBRICK_CRIM, TerraBlocks.STONEBRICK_PEARL),
        CRACKEDSTONEBRICK(Blocks.CRACKED_STONE_BRICKS, TerraBlocks.STONEBRICK_EBON_CRACKED, TerraBlocks.STONEBRICK_CRIM_CRACKED, TerraBlocks.STONEBRICK_PEARL_CRACKED),
        CHISELEDSTONEBRICK(Blocks.CHISELED_STONE_BRICKS, TerraBlocks.STONEBRICK_EBON_CHISELED, TerraBlocks.STONEBRICK_CRIM_CHISELED, TerraBlocks.STONEBRICK_PEARL_CHISELED),
        MOSSYSTONEBRICK(Blocks.MOSSY_STONE_BRICKS, TerraBlocks.STONEBRICK_EBON_MOSSY, TerraBlocks.STONEBRICK_CRIM_MOSSY, TerraBlocks.STONEBRICK_PEARL_MOSSY),
        CACTUS(Blocks.CACTUS, TerraBlocks.CACTUS_EBON, TerraBlocks.CACTUS_CRIM, TerraBlocks.CACTUS_PEARL);
//        //        ,
//        ORECOPPER(TerraBlocks.ORE_COPPER, TerraBlocks.ORE_COPPER.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlocks.ORE_COPPER.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlocks.ORE_COPPER.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        ORETIN(TerraBlocks.ORE_TIN, TerraBlocks.ORE_TIN.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlocks.ORE_TIN.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlocks.ORE_TIN.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        ORELEAD(TerraBlocks.ORE_LEAD, TerraBlocks.ORE_LEAD.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlocks.ORE_LEAD.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlocks.ORE_LEAD.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        OREIRON(Blocks.IRON_ORE, TerraBlocks.ORE_IRON.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CORRUPT), TerraBlocks.ORE_IRON.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CRIMSON), TerraBlocks.ORE_IRON.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.HALLOWED)),
//        ORESILVER(TerraBlocks.ORE_SILVER, TerraBlocks.ORE_SILVER.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlocks.ORE_SILVER.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlocks.ORE_SILVER.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        ORETUNGSTEN(TerraBlocks.ORE_TUNGSTEN, TerraBlocks.ORE_TUNGSTEN.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlocks.ORE_TUNGSTEN.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlocks.ORE_TUNGSTEN.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        OREGOLD(Blocks.GOLD_ORE, TerraBlocks.ORE_GOLD.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CORRUPT), TerraBlocks.ORE_GOLD.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CRIMSON), TerraBlocks.ORE_GOLD.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.HALLOWED)),
//        OREPLATINUM(TerraBlocks.ORE_PLATINUM, TerraBlocks.ORE_PLATINUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlocks.ORE_PLATINUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlocks.ORE_PLATINUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        OREDEMONITE(TerraBlocks.ORE_DEMONITE, TerraBlocks.ORE_DEMONITE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlocks.ORE_DEMONITE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlocks.ORE_DEMONITE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        ORECRIMTANE(TerraBlocks.ORE_CRIMTANE, TerraBlocks.ORE_CRIMTANE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlocks.ORE_CRIMTANE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlocks.ORE_CRIMTANE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        ORECOBALT(TerraBlocks.ORE_COBALT, TerraBlocks.ORE_COBALT.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlocks.ORE_COBALT.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlocks.ORE_COBALT.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        OREPALLADIUM(TerraBlocks.ORE_PALLADIUM, TerraBlocks.ORE_PALLADIUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlocks.ORE_PALLADIUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlocks.ORE_PALLADIUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        OREMYTHRIL(TerraBlocks.ORE_MYTHRIL, TerraBlocks.ORE_MYTHRIL.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlocks.ORE_MYTHRIL.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlocks.ORE_MYTHRIL.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        OREORICHALCUM(TerraBlocks.ORE_ORICHALCUM, TerraBlocks.ORE_ORICHALCUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlocks.ORE_ORICHALCUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlocks.ORE_ORICHALCUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        OREADAMANTITE(TerraBlocks.ORE_ADAMANTITE, TerraBlocks.ORE_ADAMANTITE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlocks.ORE_ADAMANTITE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlocks.ORE_ADAMANTITE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        ORETITANIUM(TerraBlocks.ORE_TITANIUM, TerraBlocks.ORE_TITANIUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlocks.ORE_TITANIUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlocks.ORE_TITANIUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        ORECOAL(Blocks.COAL_ORE, TerraBlocks.ORE_COAL.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CORRUPT), TerraBlocks.ORE_COAL.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CRIMSON), TerraBlocks.ORE_COAL.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.HALLOWED)),
//        ORELAPIS(Blocks.LAPIS_ORE, TerraBlocks.ORE_LAPIS.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CORRUPT), TerraBlocks.ORE_LAPIS.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CRIMSON), TerraBlocks.ORE_LAPIS.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.HALLOWED)),
//        OREREDSTONE(Blocks.REDSTONE_ORE, TerraBlocks.ORE_REDSTONE.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CORRUPT), TerraBlocks.ORE_REDSTONE.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CRIMSON), TerraBlocks.ORE_REDSTONE.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.HALLOWED)),
//        OREAMETHYST(TerraBlocks.ORE_AMETHYST, TerraBlocks.ORE_AMETHYST.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlocks.ORE_AMETHYST.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlocks.ORE_AMETHYST.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        ORETOPAZ(TerraBlocks.ORE_TOPAZ, TerraBlocks.ORE_TOPAZ.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlocks.ORE_TOPAZ.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlocks.ORE_TOPAZ.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        ORESAPPHIRE(TerraBlocks.ORE_SAPPHIRE, TerraBlocks.ORE_SAPPHIRE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlocks.ORE_SAPPHIRE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlocks.ORE_SAPPHIRE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        ORERUBY(TerraBlocks.ORE_RUBY, TerraBlocks.ORE_RUBY.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlocks.ORE_RUBY.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlocks.ORE_RUBY.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        OREEMERALD(Blocks.EMERALD_ORE, TerraBlocks.ORE_EMERALD.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CORRUPT), TerraBlocks.ORE_EMERALD.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CRIMSON), TerraBlocks.ORE_EMERALD.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.HALLOWED)),
//        OREDIAMOND(Blocks.DIAMOND_ORE, TerraBlocks.ORE_DIAMOND.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CORRUPT), TerraBlocks.ORE_DIAMOND.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CRIMSON), TerraBlocks.ORE_DIAMOND.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.HALLOWED)),
//        SAPLING_A(Blocks.SAPLING.withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.OAK).withProperty(BlockSapling.STAGE, 0), TerraBlocks.SAPLING_EBON, TerraBlocks.SAPLING_SHADE, TerraBlocks.SAPLING_PEARL),
//        SAPLING_B(Blocks.SAPLING.withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.OAK).withProperty(BlockSapling.STAGE, 1), TerraBlocks.SAPLING_EBON, TerraBlocks.SAPLING_SHADE, TerraBlocks.SAPLING_PEARL),
//        TALLGRASS(Blocks.TALLGRASS.withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS), TerraBlocks.TALLGRASS_CORRUPT, TerraBlocks.TALLGRASS_CRIMSON, TerraBlocks.TALLGRASS_HALLOWED),
//        TALLGRASS_TOP(Blocks.DOUBLE_PLANT.withProperty(net.minecraft.block.BlockDoublePlant.VARIANT, net.minecraft.block.BlockDoublePlant.EnumPlantType.GRASS).withProperty(net.minecraft.block.BlockDoublePlant.HALF, net.minecraft.block.BlockDoublePlant.EnumBlockHalf.UPPER), TerraBlocks.DOUBLEGRASS_CORRUPT.withProperty(TerraBlockDoublePlant.HALF, EnumHandler.EnumBlockHalf.UPPER), TerraBlocks.DOUBLEGRASS_CRIMSON.withProperty(TerraBlockDoublePlant.HALF, EnumHandler.EnumBlockHalf.UPPER), TerraBlocks.DOUBLEGRASS_HALLOWED.withProperty(TerraBlockDoublePlant.HALF, EnumHandler.EnumBlockHalf.UPPER)),
//        TALLGRASS_BOT(Blocks.DOUBLE_PLANT.withProperty(net.minecraft.block.BlockDoublePlant.VARIANT, net.minecraft.block.BlockDoublePlant.EnumPlantType.GRASS).withProperty(net.minecraft.block.BlockDoublePlant.HALF, net.minecraft.block.BlockDoublePlant.EnumBlockHalf.LOWER), TerraBlocks.DOUBLEGRASS_CORRUPT.withProperty(TerraBlockDoublePlant.HALF, EnumHandler.EnumBlockHalf.LOWER), TerraBlocks.DOUBLEGRASS_CRIMSON.withProperty(TerraBlockDoublePlant.HALF, EnumHandler.EnumBlockHalf.LOWER), TerraBlocks.DOUBLEGRASS_HALLOWED.withProperty(TerraBlockDoublePlant.HALF, EnumHandler.EnumBlockHalf.LOWER), TALLGRASS_TOP);

        public Block pure, corrupt, crimson, hallowed;

        private EnumBiomeBlockType(Block pure, Block corrupt, Block crimson, Block hallowed)
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
        IRON(1, "iron"),
        GOLD(2, "gold"),
        CORRUPT(3, "corrupt"),
        MOLTEN(4, "molten"),
        COBALT(5, "cobalt"),
        MYTHRIL(6, "mythril"),
        CHLOROPHYTE(7, "chlorophyte"),
        LUMINITE(8, "luminite");

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
