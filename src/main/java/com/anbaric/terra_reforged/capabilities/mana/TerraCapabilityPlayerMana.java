package com.anbaric.terra_reforged.capabilities.mana;

import com.anbaric.terra_reforged.capabilities.CapabilityAttacher;
import com.anbaric.terra_reforged.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.anbaric.terra_reforged.capabilities.CapabilityAttacher.genericAttachCapability;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class TerraCapabilityPlayerMana
{
    @CapabilityInject(IPlayerMana.class)
    public static final Capability<IPlayerMana> PLAYER_MANA_CAPABILITY = null;
    private static final ResourceLocation PLAYER_MANA_RESOURCELOCATION = Reference.path("mana_holder");

    public static LazyOptional<IPlayerMana> getManaHolder(PlayerEntity player)
    {
        return player.getCapability(PLAYER_MANA_CAPABILITY);
    }

    public static int getPlayersCurrentMana(PlayerEntity player)
    {
        return getManaHolder(player).map(IPlayerMana::getCurrentMana).orElse(null);
    }

    public static int getPlayersCurrentMana(ITerraManaAwarePlayer player)
    {
        return getPlayersCurrentMana(player.toVanilla());
    }

    public static int getPlayersCurrentManaCrystals(PlayerEntity player)
    {
        return getManaHolder(player).map(IPlayerMana::getManaCrystalsUsed).orElse(null);
    }

    public static int getPlayersCurrentManaCrystals(ITerraManaAwarePlayer player)
    {
        return getPlayersCurrentManaCrystals(player.toVanilla());
    }

    @SubscribeEvent
    public static void onAttachGravityHolderCap(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) event.getObject();
            genericAttachCapability(event, new ManaHolderImpl(player), TerraCapabilityPlayerMana.PLAYER_MANA_CAPABILITY, PLAYER_MANA_RESOURCELOCATION);
        }
    }

    public static void register()
    {
        CapabilityAttacher.registerCapability(IPlayerMana.class);
    }

}
