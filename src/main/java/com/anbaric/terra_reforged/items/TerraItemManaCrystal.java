package com.anbaric.terra_reforged.items;

import net.minecraft.item.Item;

public class TerraItemManaCrystal extends Item
{
    public TerraItemManaCrystal(Properties properties)
    {
        super(properties);
    }

//    @Override
//    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
//    {
//        ItemStack itemstack = playerIn.getHeldItem(handIn);
//        AtomicBoolean itemUsed = new AtomicBoolean(false);
//        playerIn.getCapability(PlayerManaStorage.PLAYER_MANA).ifPresent(cap ->
//        {
//            if (cap.getManaCrystalsUsed() < 10)
//            {
//                cap.setManaCrystalsUsed(cap.getManaCrystalsUsed() + 1);
//                cap.setMaxMana(cap.getMaxMana() + 20);
//                itemUsed.set(true);
//            }
//        });
//        if (itemUsed.get())
//        {
//            itemstack.shrink(1);
//            return ActionResult.resultConsume(itemstack);
//        }
//        else
//        {
//            playerIn.getCapability(PlayerManaStorage.PLAYER_MANA).ifPresent(cap ->
//            {
//                cap.setManaCrystalsUsed(1);
//                cap.setMaxMana(20);
//                cap.setMana(0);
//            });
//            return ActionResult.resultPass(itemstack);
//        }
//    }
}
