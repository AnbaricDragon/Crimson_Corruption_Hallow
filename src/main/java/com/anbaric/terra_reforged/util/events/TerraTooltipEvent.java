package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.util.init.TerraItemRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TerraTooltipEvent
{
//    private static final StringTextComponent curioCharm = new StringTextComponent("\u00A76" + I18n.format("curios.modifiers.charm") + "\u00A76");
//
    @SubscribeEvent(priority = EventPriority.LOWEST)
    static void onWriteTooltip(ItemTooltipEvent event)
    {
        Item item = event.getItemStack().getItem();
        if (item == TerraItemRegistry.HELMET_DIVING.get())
        {
            event.getToolTip().clear();

            event.getToolTip().add(new StringTextComponent("Diving Helmet"));
            event.getToolTip().add(new StringTextComponent(""));
            event.getToolTip().add(new StringTextComponent("\u00A76" + "When on head:" + "\u00A76"));
            event.getToolTip().add(new StringTextComponent("\u00A79" + "+2 Armor"));
            event.getToolTip().add(new StringTextComponent("\u00A79" + "+100% Extra Air"));
        }
    }
//        else if (item instanceof TerraItemBalloonBundle)
//        {
//            event.getToolTip().add(curioCharm);
//            event.getToolTip().add(new StringTextComponent("\u00A79" + "-3 Block Fall Damage"));
//            event.getToolTip().add(new StringTextComponent("\u00A79" + "+150% Jump Height"));
//        }
//        else if (item instanceof TerraItemHoneyBalloon)
//        {
//            event.getToolTip().add(curioCharm);
//            event.getToolTip().add(new StringTextComponent("\u00A79" + "+50% Jump Height"));
//            event.getToolTip().add(new StringTextComponent("\u00A79" + "Spawns Bees When Damaged"));
//        }
//        else if (item instanceof TerraItemBalloon)
//        {
//            event.getToolTip().add(curioCharm);
//            event.getToolTip().add(new StringTextComponent("\u00A79" + "+50% Jump Height"));
//        }
//        else if (item instanceof TerraItemBootsAmphibian)
//        {
//            event.getToolTip().add(new StringTextComponent("\u00A79" + "+50% Jump Height"));
//            event.getToolTip().add(new StringTextComponent("\u00A79" + "-3 Block Fall Damage"));
//        }
//        else if (item instanceof TerraItemBootsDunerider)
//        {
//            event.getToolTip().add(curioCharm);
//            event.getToolTip().add(new StringTextComponent("\u00A79" + "+50% Speed While On Sand"));
//        }
//        else if (item instanceof TerraItemBootsFairy)
//        {
//            event.getToolTip().add(new StringTextComponent("\u00A79" + "Spawns Flowers On Grass"));
//        }
//        else if (item instanceof TerraItemBootsFlower)
//        {
//            event.getToolTip().add(curioCharm);
//            event.getToolTip().add(new StringTextComponent("\u00A79" + "Spawns Flowers On Grass"));
//        }
////        TODO add Frostspark boots
////        else if (item instanceof TerraItemBootsFrostspark)
////        {
////
////        }
////        TODO add Ice Skates
////        else if (item instanceof TerraItemBootsSkates)
////        {
////
////        }
//        else if (item instanceof TerraItemBootsLava)
//        {
//            event.getToolTip().add(curioCharm);
//            event.getToolTip().add(new StringTextComponent("\u00A79" + "Allows Walking On Lava"));
//            event.getToolTip().add(new StringTextComponent("\u00A79" + "Gives 7 Seconds Of Lava Immunity"));
//            event.getToolTip().add(new StringTextComponent("\u00A79" + "-50% Lava Damage"));
//            event.getToolTip().add(new StringTextComponent("\u00A79" + "-100% Fire Damage"));
//        }
//        else if (item instanceof TerraItemBootsObsidian)
//        {
//            event.getToolTip().add(curioCharm);
//            event.getToolTip().add(new StringTextComponent("\u00A79" + "Allows Walking On Water"));
//            event.getToolTip().add(new StringTextComponent("\u00A79" + "-100% Fire Damage"));
//        }
////        TODO add Spectre Boots
////        else if (item instanceof TerraItemBootsSpectre)
////        {
////
////        }
////        TODO add Terraspark Boots
////        else if (item instanceof TerraItemBootsSpectre)
////        {
////
////        }
//        else if (item instanceof TerraItemBootsWater)
//        {
//            event.getToolTip().add(curioCharm);
//            event.getToolTip().add(new StringTextComponent("\u00A79" + "Allows Walking On Water"));
//        }
//        else if (item instanceof TerraItemBootsWater)
//        {
//            event.getToolTip().add(curioCharm);
//            event.getToolTip().add(new StringTextComponent("\u00A79" + "Allows Walking On Water"));
//        }
//    }
}
