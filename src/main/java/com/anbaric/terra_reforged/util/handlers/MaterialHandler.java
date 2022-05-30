package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public class MaterialHandler
{
    public enum Armor implements ArmorMaterial
    {
        MINING("mining", 10, new int[]{1, 1, 1, 1}, 10, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items.LEATHER)),
        OAK("oak", 3, new int[]{1, 1, 1, 1}, 1, SoundEvents.AXE_STRIP, 0.2F, 0.0F, () -> Ingredient.of(Items.OAK_PLANKS)),
        BIRCH("birch", 3, new int[]{1, 1, 1, 1}, 1, SoundEvents.AXE_STRIP, 0.2F, 0.0F, () -> Ingredient.of(Items.BIRCH_PLANKS)),
        SPRUCE("spruce", 3, new int[]{1, 1, 1, 1}, 1, SoundEvents.AXE_STRIP, 0.2F, 0.0F, () -> Ingredient.of(Items.SPRUCE_PLANKS)),
        JUNGLEWOOD("jungle_wood", 3, new int[]{1, 1, 1, 1}, 1, SoundEvents.AXE_STRIP, 0.2F, 0.0F, () -> Ingredient.of(Items.JUNGLE_PLANKS)),
        ACACIA("acacia", 3, new int[]{1, 2, 1, 1}, 1, SoundEvents.AXE_STRIP, 0.2F, 0.0F, () -> Ingredient.of(Items.ACACIA_PLANKS)),
        DARK_OAK("dark_oak", 3, new int[]{1, 2, 1, 1}, 1, SoundEvents.AXE_STRIP, 0.2F, 0.0F, () -> Ingredient.of(Items.DARK_OAK_PLANKS)),
        BOREAL("boreal", 3, new int[]{1, 2, 1, 1}, 1, SoundEvents.AXE_STRIP, 0.2F, 0.0F, () -> Ingredient.of(TerraBlockRegistry.PLANK_BOREAL.get())),
        PALM("palm", 3, new int[]{1, 2, 1, 1}, 1, SoundEvents.AXE_STRIP, 0.2F, 0.0F, () -> Ingredient.of(TerraBlockRegistry.PLANK_PALM.get())),
        MAHOGANY("mahogany", 3, new int[]{1, 2, 1, 1}, 1, SoundEvents.AXE_STRIP, 0.2F, 0.0F, () -> Ingredient.of(TerraBlockRegistry.PLANK_MAHOGANY.get())),
        EBONWOOD("ebon_wood", 3, new int[]{1, 2, 1, 1}, 1, SoundEvents.AXE_STRIP, 0.2F, 0.0F, () -> Ingredient.of(TerraBlockRegistry.PLANK_EBON.get())),
        SHADEWOOD("shade_wood", 3, new int[]{1, 2, 1, 1}, 1, SoundEvents.AXE_STRIP, 0.2F, 0.0F, () -> Ingredient.of(TerraBlockRegistry.PLANK_SHADE.get())),
        PEARLWOOD("pearl_wood", 3, new int[]{2, 2, 2, 2}, 1, SoundEvents.AXE_STRIP, 0.2F, 0.0F, () -> Ingredient.of(TerraBlockRegistry.PLANK_PEARL.get())),
        RAIN("rain", 10, new int[]{1, 1, 1, 1}, 3, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        SNOW("snow", 10, new int[]{1, 1, 1, 1}, 3, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        PINK_SNOW("pink_snow", 10, new int[]{1, 1, 1, 1}, 3, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        ANGLER("angler", 20, new int[]{0, 0, 0, 0}, 3, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        CACTUS("cactus", 5, new int[]{1, 1, 1, 1}, 5, SoundEvents.WOOL_BREAK, 0.0F, 0.0F, () -> Ingredient.of(Items.CACTUS)),
        COPPER("copper", 12, new int[]{2, 3, 2, 1}, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.3F, 0.0F, () -> Ingredient.of(Items.COPPER_INGOT)),
        TIN("tin", 12, new int[]{2, 3, 2, 1}, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.3F, 0.0F, () -> Ingredient.of(TerraItemRegistry.INGOT_TIN.get())),
        PUMPKIN("pumpkin", 3, new int[]{1, 2, 2, 1}, 10, SoundEvents.AXE_STRIP, 0.0F, 0.0F, () -> Ingredient.of(Items.PUMPKIN)),
        NINJA("ninja", 10, new int[]{1, 1, 1, 1}, 10, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        //    IRON("iron", 15, new int[]{2, 6, 5, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        LEAD("lead", 15, new int[]{2, 6, 5, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.4F, 0.0F, () -> Ingredient.of(TerraItemRegistry.INGOT_LEAD.get())),
        SILVER("silver", 17, new int[]{3, 6, 5, 2}, 15, SoundEvents.ARMOR_EQUIP_IRON, 0.4F, 0.0F, () -> Ingredient.of(TerraItemRegistry.INGOT_SILVER.get())),
        TUNGSTEN("tungsten", 17, new int[]{3, 6, 5, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.4F, 0.0F, () -> Ingredient.of(TerraItemRegistry.INGOT_TUNGSTEN.get())),
        PLATINUM("platinum", 19, new int[]{3, 6, 5, 3}, 20, SoundEvents.ARMOR_EQUIP_IRON, 0.8F, 0.0F, () -> Ingredient.of(TerraItemRegistry.INGOT_PLATINUM.get())),
        FOSSIL("fossil", 15, new int[]{1, 2, 3, 1}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(TerraBlockRegistry.STONE_FOSSIL.get())),
        BEE("bee", 15, new int[]{1, 2, 3, 1}, 9, SoundEvents.HONEY_BLOCK_BREAK, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        OBSIDIAN("obsidian", 25, new int[]{2, 6, 6, 2}, 5, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> Ingredient.of(Items.OBSIDIAN)),
        GLADIATOR("gladiator", 15, new int[]{2, 6, 6, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.5F, () -> Ingredient.of(Items.IRON_INGOT)),
        METEOR("meteor", 20, new int[]{2, 5, 5, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        JUNGLE("jungle", 20, new int[]{2, 5, 4, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        OLD_JUNGLE("old_jungle", 20, new int[]{2, 5, 4, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        NECRO("necro", 18, new int[]{2, 5, 4, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        SHADOW("shadow", 22, new int[]{2, 5, 4, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        OLD_SHADOW("old_shadow", 22, new int[]{2, 5, 4, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        CRIMSON("crimson", 22, new int[]{2, 5, 4, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        MOLTEN("molten", 28, new int[]{3, 6, 6, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.25F, 0.1F, () -> Ingredient.of(Items.IRON_INGOT)),
        SPIDER("spider", 15, new int[]{3, 4, 5, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.SPIDER_EYE)),
        COBALT("cobalt", 15, new int[]{3, 7, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.4F, 0.0F, () -> Ingredient.of(TerraItemRegistry.INGOT_COBALT.get())),
        PALLADIUM("palladium", 15, new int[]{3, 6, 6, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.4F, 0.0F, () -> Ingredient.of(TerraItemRegistry.INGOT_PALLADIUM.get())),
        MYTHRIL("mythril", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.6F, 0.0F, () -> Ingredient.of(TerraItemRegistry.INGOT_MYTHRIL.get())),
        ORICHALCUM("orichalcum", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.6F, 0.0F, () -> Ingredient.of(TerraItemRegistry.INGOT_ORICHALCUM.get())),
        ADAMANTITE("adamantite", 15, new int[]{3, 7, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.8F, 0.0F, () -> Ingredient.of(TerraItemRegistry.INGOT_ADAMANTITE.get())),
        TITANIUM("titanium", 15, new int[]{3, 7, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.8F, 0.0F, () -> Ingredient.of(TerraItemRegistry.INGOT_TITANIUM.get())),
        CRYSTAL("crystal", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.2F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        FROST("frost", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        FORBIDDEN("forbidden", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        SQUIRE("squire", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        MONK("monk", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        HUNTRESS("huntress", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        APPRENTICE("apprentice", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        HALLOWED("hallowed", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        OLD_HALLOWED("old_hallowed", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        CHLOROPHYTE("clorophyte", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        TURTLE("turtle", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        TIKI("tiki", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        BEETLE("beetle", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        SHROOMITE("shroomite", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        SPECTRE("spectre", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        SPOOKY("spooky", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        DRAGON("dragon", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        TITAN("titan", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        SPECTRAL("spectral", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        VALHALLA("valhalla", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        SHINOBI("shinobi", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        RED_RIDING("red_riding", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        DARK_ARTIST("dark_artist", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        SOLAR("solar", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        VORTEX("vortex", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        NEBULA("nebula", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        STARDUST("stardust", 15, new int[]{3, 6, 6, 3}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.1F, 0.0F, () -> Ingredient.of(Items.IRON_INGOT)),
        AMETHYST("amethyst", 3, new int[]{1, 1, 1, 1}, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        TOPAZ("topaz", 3, new int[]{1, 1, 1, 1}, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        SAPPHIRE("sapphire", 3, new int[]{1, 1, 1, 1}, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        EMERALD("emerald", 3, new int[]{1, 1, 1, 1}, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        RUBY("ruby", 3, new int[]{1, 1, 1, 1}, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        AMBER("amber", 3, new int[]{1, 1, 1, 1}, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        DIAMOND("diamond", 3, new int[]{1, 1, 1, 1}, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        MYSTIC("mystic", 3, new int[]{1, 1, 1, 1}, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        BUCKET("bucket", 3, new int[]{1, 0, 0, 0}, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        GOGGLES("goggles", 3, new int[]{1, 1, 1, 1}, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        DIVING(Reference.path("diving").toString(), 3, new int[]{1, 1, 1, 1}, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        NIGHT("night", 3, new int[]{1, 1, 1, 1}, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        VIKING("viking", 3, new int[]{1, 1, 1, 1}, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        ULTRABRIGHT("ultrabright", 3, new int[]{1, 1, 1, 1}, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        FLINX("flinx", 3, new int[]{1, 1, 1, 1}, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR)),
        GI("gi", 3, new int[]{1, 1, 1, 1}, 10, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(Items.AIR));


        private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
        private final String name;
        private final int durabilityMultiplier;
        private final int[] slotProtections;
        private final int enchantmentValue;
        private final SoundEvent sound;
        private final float toughness;
        private final float knockbackResistance;
        private final LazyLoadedValue<Ingredient> repairMaterial;

        private Armor(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial)
        {
            this.name = name;
            this.durabilityMultiplier = durabilityMultiplier;
            this.slotProtections = new int[]{slotProtections[3], slotProtections[2], slotProtections[1], slotProtections[0]};
            this.enchantmentValue = enchantmentValue;
            this.sound = soundEvent;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
            this.repairMaterial = new LazyLoadedValue<>(repairMaterial);
        }

        public int getDurabilityForSlot(EquipmentSlot p_40484_)
        {
            return HEALTH_PER_SLOT[p_40484_.getIndex()] * this.durabilityMultiplier;
        }

        public int getDefenseForSlot(EquipmentSlot p_40487_)
        {
            return this.slotProtections[p_40487_.getIndex()];
        }

        public int getEnchantmentValue()
        {
            return this.enchantmentValue;
        }

        public SoundEvent getEquipSound()
        {
            return this.sound;
        }

        public Ingredient getRepairIngredient()
        {
            return this.repairMaterial.get();
        }

        public String getName()
        {
            return this.name;
        }

        public float getToughness()
        {
            return this.toughness;
        }

        public float getKnockbackResistance()
        {
            return this.knockbackResistance;
        }
    }
}
