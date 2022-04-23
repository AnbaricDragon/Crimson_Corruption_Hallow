package com.anbaric.terra_reforged.capabilities.PlayerMana;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

public class PlayerMana
{
    public static final Capability<PlayerManaInterface> PLAYER_MANA_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() { });

    private PlayerMana()
    {
    }
}