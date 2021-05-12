package com.anbaric.terra_reforged.util.handlers;

import com.anbaric.terra_reforged.util.init.TerraBlockRegistry;
import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraftforge.common.Tags;

import java.util.function.Supplier;

public enum MaterialHandler implements IItemTier
{
    //Metal
    COPPER(2, 150, 5.0F, 1.5F, 6, () -> { return Ingredient.fromTag(TerraTagRegistry.INGOT_COPPER);}),
    TIN(2, 150, 5.0F, 1.5F, 6, () -> { return Ingredient.fromTag(TerraTagRegistry.INGOT_TIN);}),
    LEAD(2, 250, 6.0F, 1.5F, 8, () -> { return Ingredient.fromTag(TerraTagRegistry.INGOT_LEAD);}),
    SILVER(2, 250, 6.5F, 1.5F, 16, () -> { return Ingredient.fromTag(TerraTagRegistry.INGOT_SILVER);}),
    TUNGSTEN(2, 400, 6.5F, 1.5F, 10, () -> { return Ingredient.fromTag(TerraTagRegistry.INGOT_TUNGSTEN);}),
    GOLD(3, 500, 5.0F, 10.0F, 14, () -> { return Ingredient.fromTag(Tags.Items.INGOTS_GOLD);}),
    PLATINUM(3, 550, 10.0F, 1.5F, 14, () -> { return Ingredient.fromTag(TerraTagRegistry.INGOT_PLATINUM);}),
    DEMONITE(4, 600, 15.0F, 1.5F, 14, () -> { return Ingredient.fromItems(TerraItemRegistry.INGOT_DEMONITE.get());}),
    CRIMTANE(4, 600, 15.0F, 1.5F, 14, () -> { return Ingredient.fromItems(TerraItemRegistry.INGOT_CRIMTANE.get());}),
    MOLTEN(5, 800, 8.0F, 1.5F, 16, () -> { return Ingredient.fromItems(TerraItemRegistry.INGOT_HELLSTONE.get());}),
    COBALT(6, 900, 20.0F, 1.5F, 18, () -> { return Ingredient.fromItems(TerraItemRegistry.INGOT_COBALT.get());}),
    PALLADIUM(6, 950, 20.0F, 1.5F, 14, () -> { return Ingredient.fromItems(TerraItemRegistry.INGOT_PALLADIUM.get());}),
    MYTHRIL(7, 1000, 25.0F, 1.5F, 18, () -> { return Ingredient.fromTag(TerraTagRegistry.INGOT_MYTHRIL);}),
    ORICHALCUM(7, 1000, 25.0F, 1.5F, 14, () -> { return Ingredient.fromItems(TerraItemRegistry.INGOT_ORICHALCUM.get());}),
    ADAMANTITE(8, 1200, 30.0F, 1.5F, 18, () -> { return Ingredient.fromItems(TerraItemRegistry.INGOT_ADAMANTITE.get());}),
    TITANIUM(8, 1200, 30.0F, 1.5F, 14, () -> { return Ingredient.fromTag(TerraTagRegistry.INGOT_TITANIUM);}),
    SPECTRE(9, 1500, 35.0F, 1.5F, 25, () -> { return Ingredient.fromItems(TerraItemRegistry.INGOT_SPECTRE.get());}),
    CHLOROPHYTE(9, 1500, 35.0F, 1.5F, 10, () -> { return Ingredient.fromItems(TerraItemRegistry.INGOT_CHLOROPHYTE.get());}),
    HALLOWED(9, 1500, 35.0F, 1.5F, 25, () -> { return Ingredient.fromItems(TerraItemRegistry.INGOT_HALLOWED.get());}),
    SHROOMITE(9, 1500, 50.0F, 1.5F, 10, () -> { return Ingredient.fromItems(TerraItemRegistry.INGOT_SHROOMITE.get());}),
    LUMINITE(10, 2000, 40.0F, 1.5F, 10, () -> { return Ingredient.fromItems(TerraItemRegistry.INGOT_LUMINITE.get());}),

    //Misc
    CACTUS(0, 40, 1.8F, 0.0F, 15, () -> { return Ingredient.fromItems(Blocks.CACTUS);}),
    CANDY(3, 500, 12.0F, 1.5F, 14, () -> null),
    FOSSIL(3, 500, 12.0F, 1.5F, 14, () -> null),
    BONE(3, 500, 14.0F, 1.5F, 14, () -> null),
    SHARK(3, 500, 18.0F, 1.5F, 14, () -> null),
    TERRA(0, 1500, 0F, 1.5F, 20, () -> null),
    JUNGLE(10, 2000, 40.0F, 1.5F, 10, () -> { return Ingredient.fromItems(TerraBlockRegistry.PLANT_THORN_JUNGLE.get());});

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyValue<Ingredient> repairMaterial;

    private MaterialHandler(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn)
    {
        this.harvestLevel = harvestLevelIn;
        this.maxUses = maxUsesIn;
        this.efficiency = efficiencyIn;
        this.attackDamage = attackDamageIn;
        this.enchantability = enchantabilityIn;
        this.repairMaterial = new LazyValue<>(repairMaterialIn);
    }

    public int getMaxUses()
    {
        return this.maxUses;
    }

    public float getEfficiency()
    {
        return this.efficiency;
    }

    public float getAttackDamage()
    {
        return this.attackDamage;
    }

    public int getHarvestLevel()
    {
        return this.harvestLevel;
    }

    public int getEnchantability()
    {
        return this.enchantability;
    }

    public Ingredient getRepairMaterial()
    {
        return this.repairMaterial.getValue();
    }
}