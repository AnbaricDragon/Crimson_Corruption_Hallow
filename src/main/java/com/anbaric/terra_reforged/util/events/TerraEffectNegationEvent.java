package com.anbaric.terra_reforged.util.events;

import com.anbaric.terra_reforged.util.init.TerraEffectRegistry;
import com.anbaric.terra_reforged.util.init.TerraTagRegistry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.CuriosApi;

public class TerraEffectNegationEvent
{
    @SubscribeEvent
    static void isPotionApplicable(PotionEvent.PotionApplicableEvent event)
    {
        MobEffect    effect = event.getPotionEffect().getEffect();
        LivingEntity entity = event.getEntityLiving();
        Event.Result result = Event.Result.DEFAULT;

        if (entity instanceof Player)
        {
            Player player = (Player) entity;
            if (effect == TerraEffectRegistry.BLEEDING.get() && hasAnyItemFromTag(player, TerraTagRegistry.BLEEDING_NEGATORS)) { result = Event.Result.DENY; }
            if (effect == MobEffects.BLINDNESS && hasAnyItemFromTag(player, TerraTagRegistry.BLINDNESS_NEGATORS)) { result = Event.Result.DENY; }
            if (effect == TerraEffectRegistry.BROKEN_ARMOR.get() && hasAnyItemFromTag(player, TerraTagRegistry.BROKEN_ARMOR_NEGATORS)) { result = Event.Result.DENY; }
            if (effect == TerraEffectRegistry.CONFUSION.get() && hasAnyItemFromTag(player, TerraTagRegistry.CONFUSED_NEGATORS)) { result = Event.Result.DENY; }
            if (effect == TerraEffectRegistry.CURSED.get() && hasAnyItemFromTag(player, TerraTagRegistry.CURSED_NEGATORS)) { result = Event.Result.DENY; }
            if (effect == MobEffects.POISON && hasAnyItemFromTag(player, TerraTagRegistry.POISON_NEGATORS)) { result = Event.Result.DENY; }
            if (effect == TerraEffectRegistry.SILENCED.get() && hasAnyItemFromTag(player, TerraTagRegistry.SILENCED_NEGATORS)) { result = Event.Result.DENY; }
            if (effect == MobEffects.MOVEMENT_SLOWDOWN || effect == MobEffects.DIG_SLOWDOWN && hasAnyItemFromTag(player, TerraTagRegistry.SLOW_NEGATORS)) { result = Event.Result.DENY; }
            if (effect == MobEffects.WEAKNESS && hasAnyItemFromTag(player, TerraTagRegistry.WEAK_NEGATORS)) { result = Event.Result.DENY; }
        }

        event.setResult(result);
    }

    private static boolean hasAnyItemFromTag(Player player, TagKey tag)
    {
        return CuriosApi.getCuriosHelper().findFirstCurio(player, itemStack -> ForgeRegistries.ITEMS.tags().getTag(tag).contains(itemStack)).isPresent();
    }
}
