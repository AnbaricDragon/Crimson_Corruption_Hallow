package com.anbaric.terra_reforged.capabilities.PlayerMana;

import com.anbaric.terra_reforged.util.Reference;
import dev._100media.capabilitysyncer.core.CapabilityAttacher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import javax.annotation.Nullable;

public class PlayerMana extends CapabilityAttacher
{
    private static final Class<TerraManaCapability> CAPABILITY_CLASS = TerraManaCapability.class;
    public static final Capability<TerraManaCapability> TERRA_MANA_CAPABILITY = getCapability(new CapabilityToken<>() {});
    public static final ResourceLocation TERRA_MANA_CAPABILITY_RL = Reference.path("player_mana_capability");

    @Nullable
    public static TerraManaCapability getTerraManaCapabilityUnwrap(Player player) {
        return getTerraManaCapability(player).orElse(null);
    }

    public static LazyOptional<TerraManaCapability> getTerraManaCapability(Player player) {
        return player.getCapability(TERRA_MANA_CAPABILITY);
    }

    private static void attach(AttachCapabilitiesEvent<Entity> event, Player player) {
        genericAttachCapability(event, new TerraManaCapability(player), TERRA_MANA_CAPABILITY, TERRA_MANA_CAPABILITY_RL);
    }

    public static void register() {
        CapabilityAttacher.registerCapability(CAPABILITY_CLASS);
        CapabilityAttacher.registerPlayerAttacher(PlayerMana::attach, PlayerMana::getTerraManaCapability, true);
    }
}