package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.capabilities.player_mana.TerraMana;
import com.anbaric.terra_reforged.util.Reference;
import com.anbaric.terra_reforged.util.init.TerraAttributeRegistry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class TerraItemManaCrystal extends Item
{
    public TerraItemManaCrystal(Properties properties)
    {
        super(properties);
    }

    public static final AttributeModifier[] CRYSTAL_UUIDS =
    {
        new AttributeModifier(UUID.fromString("0420d489-5d7a-49c6-ba59-b91817fb31dd"), "manaCrystal1", 20.0D, AttributeModifier.Operation.ADDITION),
        new AttributeModifier(UUID.fromString("5b29210c-d761-494b-bf1e-937486f1b561"), "manaCrystal2", 20.0D, AttributeModifier.Operation.ADDITION),
        new AttributeModifier(UUID.fromString("0235fde8-ccf5-4b52-b8d9-10e24dc58294"), "manaCrystal3", 20.0D, AttributeModifier.Operation.ADDITION),
        new AttributeModifier(UUID.fromString("f95b3eab-e0cf-4a92-9983-b5e1de0004cb"), "manaCrystal4", 20.0D, AttributeModifier.Operation.ADDITION),
        new AttributeModifier(UUID.fromString("f35e8499-7c17-4501-90ad-d19eeca7d158"), "manaCrystal5", 20.0D, AttributeModifier.Operation.ADDITION),
        new AttributeModifier(UUID.fromString("02e885f5-df17-452e-ae40-416eba758b70"), "manaCrystal6", 20.0D, AttributeModifier.Operation.ADDITION),
        new AttributeModifier(UUID.fromString("02ba1f7c-8cc4-42f3-b144-c42c4f175656"), "manaCrystal7", 20.0D, AttributeModifier.Operation.ADDITION),
        new AttributeModifier(UUID.fromString("5dabcd9f-2257-4cc4-b88e-655b20139bdf"), "manaCrystal8", 20.0D, AttributeModifier.Operation.ADDITION),
        new AttributeModifier(UUID.fromString("6e7f68a3-ee9a-4b5d-adc2-decc5e72d583"), "manaCrystal9", 220.0D, AttributeModifier.Operation.ADDITION)
    };

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
        ItemStack     itemstack = player.getItemInHand(hand);
        AtomicBoolean itemUsed  = new AtomicBoolean(false);
        player.getCapability(TerraMana.TERRA_MANA_CAPABILITY).ifPresent(cap ->
        {
            Reference.print("Capability exists");
            int crystalsUsed = cap.getManaCrystalsUsed();
            if (crystalsUsed < 9)
            {
                cap.setManaCrystalsUsed(crystalsUsed + 1);
                if (!player.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).hasModifier(CRYSTAL_UUIDS[crystalsUsed]))
                {
                    player.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).addPermanentModifier(CRYSTAL_UUIDS[crystalsUsed]);
                }
                itemUsed.set(true);
            }
        });
        if (itemUsed.get())
        {
            itemstack.shrink(1);
            return InteractionResultHolder.consume(itemstack);
        }
        else
        {
            player.getCapability(TerraMana.TERRA_MANA_CAPABILITY).ifPresent(cap ->
            {
                cap.setManaCrystalsUsed(0);
                cap.setCurrentMana(0, true);
                for (AttributeModifier modifier : CRYSTAL_UUIDS)
                {
                    player.getAttribute(TerraAttributeRegistry.MANA_MAX.get()).removeModifier(modifier);
                }
            });
            itemstack.setCount(10);
            return InteractionResultHolder.pass(itemstack);
        }
    }
}
