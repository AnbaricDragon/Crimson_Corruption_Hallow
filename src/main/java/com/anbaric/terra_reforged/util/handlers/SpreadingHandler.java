package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.LinkedList;

public class SpreadingHandler
{
    public enum EnumBiomeType
    {
        PURE("pure"),
        CORRUPT("corrupt"),
        CRIMSON("crimson"),
        HALLOWED("hallowed"),
        JUNGLE("jungle");

        private final String biome;

        EnumBiomeType(String biome)
        {
            this.biome = biome;
        }
    }

    public enum OreBiomes
    {
        PURE("pure", EnumBiomeType.PURE),
        CORRUPT("corrupt", EnumBiomeType.CORRUPT),
        CRIMSON("crimson", EnumBiomeType.CRIMSON),
        HALLOWED("hallowed", EnumBiomeType.HALLOWED),
        JUNGLE("jungle", EnumBiomeType.JUNGLE);

        private final String name;
        private final EnumBiomeType biome;

        OreBiomes(String name, EnumBiomeType biome)
        {
            this.name = name;
            this.biome = biome;
        }

        @Override
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
        DIRT(Blocks.DIRT, TerraBlockRegistry.GRASS_CORRUPT.get(), TerraBlockRegistry.GRASS_CRIMSON.get(), TerraBlockRegistry.GRASS_HALLOWED.get()),
        GRASS(Blocks.GRASS_BLOCK, TerraBlockRegistry.GRASS_CORRUPT.get(), TerraBlockRegistry.GRASS_CRIMSON.get(), TerraBlockRegistry.GRASS_HALLOWED.get()),
        GRASSJUNGLE(TerraBlockRegistry.GRASS_JUNGLE.get(), TerraBlockRegistry.SOIL_MUD.get(), TerraBlockRegistry.SOIL_MUD.get(), TerraBlockRegistry.GRASS_JUNGLE.get(), TerraBlockRegistry.GRASS_JUNGLE.get()),
        MUD(TerraBlockRegistry.SOIL_MUD.get(), Blocks.DIRT, Blocks.DIRT, TerraBlockRegistry.SOIL_MUD.get(), TerraBlockRegistry.GRASS_JUNGLE.get()),
        SAND(Blocks.SAND, TerraBlockRegistry.SAND_EBON.get(), TerraBlockRegistry.SAND_CRIM.get(), TerraBlockRegistry.SAND_PEARL.get()),
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
        ORECOPPER(Blocks.COPPER_ORE, TerraBlockRegistry.ORE_COPPER_CORRUPT.get(), TerraBlockRegistry.ORE_COPPER_CRIMSON.get(), TerraBlockRegistry.ORE_COPPER_HALLOWED.get(), TerraBlockRegistry.ORE_COPPER_JUNGLE.get()),
        ORETIN(TerraBlockRegistry.ORE_TIN_PURE.get(), TerraBlockRegistry.ORE_TIN_CORRUPT.get(), TerraBlockRegistry.ORE_TIN_CRIMSON.get(), TerraBlockRegistry.ORE_TIN_HALLOWED.get(), TerraBlockRegistry.ORE_TIN_JUNGLE.get()),
        ORELEAD(TerraBlockRegistry.ORE_LEAD_PURE.get(), TerraBlockRegistry.ORE_LEAD_CORRUPT.get(), TerraBlockRegistry.ORE_LEAD_CRIMSON.get(), TerraBlockRegistry.ORE_LEAD_HALLOWED.get(), TerraBlockRegistry.ORE_LEAD_JUNGLE.get()),
        OREIRON(Blocks.IRON_ORE, TerraBlockRegistry.ORE_IRON_CORRUPT.get(), TerraBlockRegistry.ORE_IRON_CRIMSON.get(), TerraBlockRegistry.ORE_IRON_HALLOWED.get(), TerraBlockRegistry.ORE_IRON_JUNGLE.get()),
        ORESILVER(TerraBlockRegistry.ORE_SILVER_PURE.get(), TerraBlockRegistry.ORE_SILVER_CORRUPT.get(), TerraBlockRegistry.ORE_SILVER_CRIMSON.get(), TerraBlockRegistry.ORE_SILVER_HALLOWED.get(), TerraBlockRegistry.ORE_SILVER_JUNGLE.get()),
        ORETUNGSTEN(TerraBlockRegistry.ORE_TUNGSTEN_PURE.get(), TerraBlockRegistry.ORE_TUNGSTEN_CORRUPT.get(), TerraBlockRegistry.ORE_TUNGSTEN_CRIMSON.get(), TerraBlockRegistry.ORE_TUNGSTEN_HALLOWED.get(), TerraBlockRegistry.ORE_TUNGSTEN_JUNGLE.get()),
        OREGOLD(Blocks.GOLD_ORE, TerraBlockRegistry.ORE_GOLD_CORRUPT.get(), TerraBlockRegistry.ORE_GOLD_CRIMSON.get(), TerraBlockRegistry.ORE_GOLD_HALLOWED.get(), TerraBlockRegistry.ORE_GOLD_JUNGLE.get()),
        OREPLATINUM(TerraBlockRegistry.ORE_PLATINUM_PURE.get(), TerraBlockRegistry.ORE_PLATINUM_CORRUPT.get(), TerraBlockRegistry.ORE_PLATINUM_CRIMSON.get(), TerraBlockRegistry.ORE_PLATINUM_HALLOWED.get(), TerraBlockRegistry.ORE_PLATINUM_JUNGLE.get()),
        OREDEMONITE(TerraBlockRegistry.ORE_DEMONITE_PURE.get(), TerraBlockRegistry.ORE_DEMONITE_CORRUPT.get(), TerraBlockRegistry.ORE_DEMONITE_CRIMSON.get(), TerraBlockRegistry.ORE_DEMONITE_HALLOWED.get(), TerraBlockRegistry.ORE_DEMONITE_JUNGLE.get()),
        ORECRIMTANE(TerraBlockRegistry.ORE_CRIMTANE_PURE.get(), TerraBlockRegistry.ORE_CRIMTANE_CORRUPT.get(), TerraBlockRegistry.ORE_CRIMTANE_CRIMSON.get(), TerraBlockRegistry.ORE_CRIMTANE_HALLOWED.get(), TerraBlockRegistry.ORE_CRIMTANE_JUNGLE.get()),
        ORECOBALT(TerraBlockRegistry.ORE_COBALT_PURE.get(), TerraBlockRegistry.ORE_COBALT_CORRUPT.get(), TerraBlockRegistry.ORE_COBALT_CRIMSON.get(), TerraBlockRegistry.ORE_COBALT_HALLOWED.get(), TerraBlockRegistry.ORE_COBALT_JUNGLE.get()),
        OREPALLADIUM(TerraBlockRegistry.ORE_PALLADIUM_PURE.get(), TerraBlockRegistry.ORE_PALLADIUM_CORRUPT.get(), TerraBlockRegistry.ORE_PALLADIUM_CRIMSON.get(), TerraBlockRegistry.ORE_PALLADIUM_HALLOWED.get(), TerraBlockRegistry.ORE_PALLADIUM_JUNGLE.get()),
        OREMYTHRIL(TerraBlockRegistry.ORE_MYTHRIL_PURE.get(), TerraBlockRegistry.ORE_MYTHRIL_CORRUPT.get(), TerraBlockRegistry.ORE_MYTHRIL_CRIMSON.get(), TerraBlockRegistry.ORE_MYTHRIL_HALLOWED.get(), TerraBlockRegistry.ORE_MYTHRIL_JUNGLE.get()),
        OREORICHALCUM(TerraBlockRegistry.ORE_ORICHALCUM_PURE.get(), TerraBlockRegistry.ORE_ORICHALCUM_CORRUPT.get(), TerraBlockRegistry.ORE_ORICHALCUM_CRIMSON.get(), TerraBlockRegistry.ORE_ORICHALCUM_HALLOWED.get(), TerraBlockRegistry.ORE_ORICHALCUM_JUNGLE.get()),
        OREADAMANTITE(TerraBlockRegistry.ORE_ADAMANTITE_PURE.get(), TerraBlockRegistry.ORE_ADAMANTITE_CORRUPT.get(), TerraBlockRegistry.ORE_ADAMANTITE_CRIMSON.get(), TerraBlockRegistry.ORE_ADAMANTITE_HALLOWED.get(), TerraBlockRegistry.ORE_ADAMANTITE_JUNGLE.get()),
        ORETITANIUM(TerraBlockRegistry.ORE_TITANIUM_PURE.get(), TerraBlockRegistry.ORE_TITANIUM_CORRUPT.get(), TerraBlockRegistry.ORE_TITANIUM_CRIMSON.get(), TerraBlockRegistry.ORE_TITANIUM_HALLOWED.get(), TerraBlockRegistry.ORE_TITANIUM_JUNGLE.get()),
        ORECOAL(Blocks.COAL_ORE, TerraBlockRegistry.ORE_COAL_CORRUPT.get(), TerraBlockRegistry.ORE_COAL_CRIMSON.get(), TerraBlockRegistry.ORE_COAL_HALLOWED.get(), TerraBlockRegistry.ORE_COAL_JUNGLE.get()),
        ORELAPIS(Blocks.LAPIS_ORE, TerraBlockRegistry.ORE_LAPIS_CORRUPT.get(), TerraBlockRegistry.ORE_LAPIS_CRIMSON.get(), TerraBlockRegistry.ORE_LAPIS_HALLOWED.get(), TerraBlockRegistry.ORE_LAPIS_JUNGLE.get()),
        OREREDSTONE(Blocks.REDSTONE_ORE, TerraBlockRegistry.ORE_REDSTONE_CORRUPT.get(), TerraBlockRegistry.ORE_REDSTONE_CRIMSON.get(), TerraBlockRegistry.ORE_REDSTONE_HALLOWED.get(), TerraBlockRegistry.ORE_REDSTONE_JUNGLE.get()),
        OREAMBER(TerraBlockRegistry.ORE_AMBER_PURE.get(), TerraBlockRegistry.ORE_AMBER_CORRUPT.get(), TerraBlockRegistry.ORE_AMBER_CRIMSON.get(), TerraBlockRegistry.ORE_AMBER_HALLOWED.get(), TerraBlockRegistry.ORE_AMBER_JUNGLE.get()),
        OREAMETHYST(TerraBlockRegistry.ORE_AMETHYST_PURE.get(), TerraBlockRegistry.ORE_AMETHYST_CORRUPT.get(), TerraBlockRegistry.ORE_AMETHYST_CRIMSON.get(), TerraBlockRegistry.ORE_AMETHYST_HALLOWED.get(), TerraBlockRegistry.ORE_AMETHYST_JUNGLE.get()),
        ORETOPAZ(TerraBlockRegistry.ORE_TOPAZ_PURE.get(), TerraBlockRegistry.ORE_TOPAZ_CORRUPT.get(), TerraBlockRegistry.ORE_TOPAZ_CRIMSON.get(), TerraBlockRegistry.ORE_TOPAZ_HALLOWED.get(), TerraBlockRegistry.ORE_TOPAZ_JUNGLE.get()),
        ORESAPPHIRE(TerraBlockRegistry.ORE_SAPPHIRE_PURE.get(), TerraBlockRegistry.ORE_SAPPHIRE_CORRUPT.get(), TerraBlockRegistry.ORE_SAPPHIRE_CRIMSON.get(), TerraBlockRegistry.ORE_SAPPHIRE_HALLOWED.get(), TerraBlockRegistry.ORE_SAPPHIRE_JUNGLE.get()),
        ORERUBY(TerraBlockRegistry.ORE_RUBY_PURE.get(), TerraBlockRegistry.ORE_RUBY_CORRUPT.get(), TerraBlockRegistry.ORE_RUBY_CRIMSON.get(), TerraBlockRegistry.ORE_RUBY_HALLOWED.get(), TerraBlockRegistry.ORE_RUBY_JUNGLE.get()),
        OREEMERALD(Blocks.EMERALD_ORE, TerraBlockRegistry.ORE_EMERALD_CORRUPT.get(), TerraBlockRegistry.ORE_EMERALD_CRIMSON.get(), TerraBlockRegistry.ORE_EMERALD_HALLOWED.get(), TerraBlockRegistry.ORE_EMERALD_JUNGLE.get()),
        OREDIAMOND(Blocks.DIAMOND_ORE, TerraBlockRegistry.ORE_DIAMOND_CORRUPT.get(), TerraBlockRegistry.ORE_DIAMOND_CRIMSON.get(), TerraBlockRegistry.ORE_DIAMOND_HALLOWED.get(), TerraBlockRegistry.ORE_DIAMOND_JUNGLE.get()),
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
        REEDS(Blocks.SUGAR_CANE, TerraBlockRegistry.REED_CORRUPT.get(), TerraBlockRegistry.REED_CRIMSON.get(), TerraBlockRegistry.REED_HALLOWED.get());

        public LinkedList<Block> pure;
        public Block corrupt, crimson, hallowed, jungle;

        EnumBiomeBlockType(Block pure, Block corrupt, Block crimson, Block hallowed, Block jungle, TagKey inputTags)
        {
            this.pure = new LinkedList<Block>();
            this.pure.add(pure);
            this.pure.addAll(ForgeRegistries.BLOCKS.tags().getTag(inputTags).stream().toList());
            this.corrupt = corrupt;
            this.crimson = crimson;
            this.hallowed = hallowed;
            this.jungle = jungle;
        }

        EnumBiomeBlockType(Block pure, Block corrupt, Block crimson, Block hallowed, TagKey inputTags)
        {
            this.pure = new LinkedList<Block>();
            this.pure.add(pure);
            this.pure.addAll(ForgeRegistries.BLOCKS.tags().getTag(inputTags).stream().toList());
            this.corrupt = corrupt;
            this.crimson = crimson;
            this.hallowed = hallowed;
            this.jungle = pure;
        }

        EnumBiomeBlockType(Block pure, Block corrupt, Block crimson, Block hallowed, Block jungle)
        {
            this.pure = new LinkedList<Block>();
            this.pure.add(pure);
            this.corrupt = corrupt;
            this.crimson = crimson;
            this.hallowed = hallowed;
            this.jungle = jungle;
        }

        EnumBiomeBlockType(Block pure, Block corrupt, Block crimson, Block hallowed)
        {
            this.pure = new LinkedList<Block>();
            this.pure.add(pure);
            this.corrupt = corrupt;
            this.crimson = crimson;
            this.hallowed = hallowed;
            this.jungle = pure;
        }

        public LinkedList<Block> getVariantBlocks()
        {
            LinkedList<Block> result = new LinkedList<>();
            result.add(this.pure.getFirst());
            result.add(this.corrupt);
            result.add(this.crimson);
            result.add(this.hallowed);
            result.add(this.jungle);
            return result;
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
                case "jungle":
                    return this.jungle;
                default:
                    return this.pure.getFirst();
            }
        }
    }
}
