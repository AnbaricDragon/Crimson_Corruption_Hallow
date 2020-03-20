package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.blocks.TerraBlockOre;
import com.anbaric.terra_reforged.blocks.TerraBlockOre.OreBiomes;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class EnumHandler
{
    public enum EnumBiomeType
    {
        PURE("pure", OreBiomes.PURE),
        CORRUPT("corrupt", OreBiomes.CORRUPT),
        CRIMSON("crimson", OreBiomes.CRIMSON),
        HALLOWED("hallowed", OreBiomes.HALLOWED);

        private final String biome;
        private final TerraBlockOre.OreBiomes oreBiome;

        EnumBiomeType(String biome, TerraBlockOre.OreBiomes oreBiome)
        {
            this.biome = biome;
            this.oreBiome = oreBiome;
        }

        public OreBiomes getOreBiome()
        {
            return oreBiome;
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
        MOSSYSTONEBRICK(Blocks.MOSSY_STONE_BRICKS, TerraBlockRegistry.STONEBRICK_EBON_MOSSY.get(), TerraBlockRegistry.STONEBRICK_CRIM_MOSSY.get(), TerraBlockRegistry.STONEBRICK_PEARL_MOSSY.get());
//        CACTUS(Blocks.CACTUS, TerraBlockRegistry.CACTUS_EBON.get(), TerraBlockRegistry.CACTUS_CRIM.get(), TerraBlockRegistry.CACTUS_PEARL.get()),
//        ORECOPPER(TerraBlockRegistry.ORE_COPPER, TerraBlockRegistry.ORE_COPPER.with(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlockRegistry.ORE_COPPER.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlockRegistry.ORE_COPPER.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        ORETIN(TerraBlockRegistry.ORE_TIN, TerraBlockRegistry.ORE_TIN.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlockRegistry.ORE_TIN.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlockRegistry.ORE_TIN.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        ORELEAD(TerraBlockRegistry.ORE_LEAD, TerraBlockRegistry.ORE_LEAD.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlockRegistry.ORE_LEAD.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlockRegistry.ORE_LEAD.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        OREIRON(Blocks.IRON_ORE, TerraBlockRegistry.ORE_IRON.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CORRUPT), TerraBlockRegistry.ORE_IRON.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CRIMSON), TerraBlockRegistry.ORE_IRON.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.HALLOWED)),
//        ORESILVER(TerraBlockRegistry.ORE_SILVER, TerraBlockRegistry.ORE_SILVER.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlockRegistry.ORE_SILVER.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlockRegistry.ORE_SILVER.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        ORETUNGSTEN(TerraBlockRegistry.ORE_TUNGSTEN, TerraBlockRegistry.ORE_TUNGSTEN.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlockRegistry.ORE_TUNGSTEN.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlockRegistry.ORE_TUNGSTEN.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        OREGOLD(Blocks.GOLD_ORE, TerraBlockRegistry.ORE_GOLD.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CORRUPT), TerraBlockRegistry.ORE_GOLD.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CRIMSON), TerraBlockRegistry.ORE_GOLD.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.HALLOWED)),
//        OREPLATINUM(TerraBlockRegistry.ORE_PLATINUM, TerraBlockRegistry.ORE_PLATINUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlockRegistry.ORE_PLATINUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlockRegistry.ORE_PLATINUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        OREDEMONITE(TerraBlockRegistry.ORE_DEMONITE, TerraBlockRegistry.ORE_DEMONITE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlockRegistry.ORE_DEMONITE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlockRegistry.ORE_DEMONITE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        ORECRIMTANE(TerraBlockRegistry.ORE_CRIMTANE, TerraBlockRegistry.ORE_CRIMTANE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlockRegistry.ORE_CRIMTANE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlockRegistry.ORE_CRIMTANE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        ORECOBALT(TerraBlockRegistry.ORE_COBALT, TerraBlockRegistry.ORE_COBALT.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlockRegistry.ORE_COBALT.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlockRegistry.ORE_COBALT.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        OREPALLADIUM(TerraBlockRegistry.ORE_PALLADIUM, TerraBlockRegistry.ORE_PALLADIUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlockRegistry.ORE_PALLADIUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlockRegistry.ORE_PALLADIUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        OREMYTHRIL(TerraBlockRegistry.ORE_MYTHRIL, TerraBlockRegistry.ORE_MYTHRIL.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlockRegistry.ORE_MYTHRIL.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlockRegistry.ORE_MYTHRIL.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        OREORICHALCUM(TerraBlockRegistry.ORE_ORICHALCUM, TerraBlockRegistry.ORE_ORICHALCUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlockRegistry.ORE_ORICHALCUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlockRegistry.ORE_ORICHALCUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        OREADAMANTITE(TerraBlockRegistry.ORE_ADAMANTITE, TerraBlockRegistry.ORE_ADAMANTITE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlockRegistry.ORE_ADAMANTITE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlockRegistry.ORE_ADAMANTITE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        ORETITANIUM(TerraBlockRegistry.ORE_TITANIUM, TerraBlockRegistry.ORE_TITANIUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlockRegistry.ORE_TITANIUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlockRegistry.ORE_TITANIUM.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        ORECOAL(Blocks.COAL_ORE, TerraBlockRegistry.ORE_COAL.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CORRUPT), TerraBlockRegistry.ORE_COAL.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CRIMSON), TerraBlockRegistry.ORE_COAL.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.HALLOWED)),
//        ORELAPIS(Blocks.LAPIS_ORE, TerraBlockRegistry.ORE_LAPIS.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CORRUPT), TerraBlockRegistry.ORE_LAPIS.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CRIMSON), TerraBlockRegistry.ORE_LAPIS.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.HALLOWED)),
//        OREREDSTONE(Blocks.REDSTONE_ORE, TerraBlockRegistry.ORE_REDSTONE.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CORRUPT), TerraBlockRegistry.ORE_REDSTONE.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CRIMSON), TerraBlockRegistry.ORE_REDSTONE.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.HALLOWED)),
//        OREAMETHYST(TerraBlockRegistry.ORE_AMETHYST, TerraBlockRegistry.ORE_AMETHYST.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlockRegistry.ORE_AMETHYST.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlockRegistry.ORE_AMETHYST.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        ORETOPAZ(TerraBlockRegistry.ORE_TOPAZ, TerraBlockRegistry.ORE_TOPAZ.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlockRegistry.ORE_TOPAZ.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlockRegistry.ORE_TOPAZ.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        ORESAPPHIRE(TerraBlockRegistry.ORE_SAPPHIRE, TerraBlockRegistry.ORE_SAPPHIRE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlockRegistry.ORE_SAPPHIRE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlockRegistry.ORE_SAPPHIRE.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        ORERUBY(TerraBlockRegistry.ORE_RUBY, TerraBlockRegistry.ORE_RUBY.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CORRUPT), TerraBlockRegistry.ORE_RUBY.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.CRIMSON), TerraBlockRegistry.ORE_RUBY.withProperty(TerraBlockOres.VARIANT, EnumBiomeType.HALLOWED)),
//        OREEMERALD(Blocks.EMERALD_ORE, TerraBlockRegistry.ORE_EMERALD.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CORRUPT), TerraBlockRegistry.ORE_EMERALD.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CRIMSON), TerraBlockRegistry.ORE_EMERALD.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.HALLOWED)),
//        OREDIAMOND(Blocks.DIAMOND_ORE, TerraBlockRegistry.ORE_DIAMOND.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CORRUPT), TerraBlockRegistry.ORE_DIAMOND.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.CRIMSON), TerraBlockRegistry.ORE_DIAMOND.withProperty(TerraBlockDOres.D_VARIANT, EnumDOreType.HALLOWED)),
//        SAPLING_A(Blocks.SAPLING.withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.OAK).withProperty(BlockSapling.STAGE, 0), TerraBlockRegistry.SAPLING_EBON, TerraBlockRegistry.SAPLING_SHADE, TerraBlockRegistry.SAPLING_PEARL),
//        SAPLING_B(Blocks.SAPLING.withProperty(BlockSapling.TYPE, BlockPlanks.EnumType.OAK).withProperty(BlockSapling.STAGE, 1), TerraBlockRegistry.SAPLING_EBON, TerraBlockRegistry.SAPLING_SHADE, TerraBlockRegistry.SAPLING_PEARL),
//        TALLGRASS(Blocks.GRASS, TerraBlockRegistry.TALLGRASS_CORRUPT.get(), TerraBlockRegistry.TALLGRASS_CRIMSON.get(), TerraBlockRegistry.TALLGRASS_HALLOWED.get()),
//        REEDS(Blocks.SUGAR_CANE, TerraBlockRegistry.REED_CORRUPT.get(), TerraBlockRegistry.REED_CRIMSON.get(), TerraBlockRegistry.REED_HALLOWED.get());
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
