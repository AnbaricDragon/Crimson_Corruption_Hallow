package com.anbaric.terra_reforged.capabilities.hardmode;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class TerraCapabilityWorldProgression
{
    @CapabilityInject(IWorldProgression.class)
    public static final Capability<IWorldProgression> WORLD_PROGRESSION = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IWorldProgression.class, new WorldProgressionStorage(), WorldProgression::new);
    }

    public static class WorldProgressionStorage implements IStorage<IWorldProgression>
    {
        @Override
        public INBT writeNBT(Capability<IWorldProgression> capability, IWorldProgression instance, Direction side)
        {
            CompoundNBT tag = new CompoundNBT();
            tag.putInt("orbs_broken", instance.getOrbsBroken());
            tag.putInt("altars_broken", instance.getAltarsBroken());
            tag.putBoolean("has_found_angler", instance.getHasFoundAngler());
            tag.putBoolean("has_found_goblin", instance.getHasFoundGoblin());
            tag.putBoolean("has_found_stylist", instance.getHasFoundStylist());
            tag.putBoolean("has_found_mechanic", instance.getHasFoundMechanic());
            tag.putBoolean("has_found_wizard", instance.getHasFoundWizard());
            tag.putBoolean("has_found_tax_collector", instance.getHasFoundTaxCollector());
            tag.putBoolean("has_beat_goblin_invasion", instance.getHasBeatGoblinInvasion());
            tag.putBoolean("has_beat_pirate_invasion", instance.getHasBeatPirateInvasion());
            tag.putBoolean("has_beat_pumpkin_invasion", instance.getHasBeatPumpkinInvasion());
            tag.putBoolean("has_beat_frost_invasion", instance.getHasBeatFrostInvasion());
            tag.putBoolean("has_king_slime_died", instance.getHasKingSlimeDied());
            tag.putBoolean("has_eye_of_cthulhu_died", instance.getHasEyeOfCthulhuDied());
            tag.putBoolean("has_eater_of_worlds_died", instance.getHasEaterOfWorldsDied());
            tag.putBoolean("has_brain_of_cthulhu_died", instance.getHasBrainOfCthulhuDied());
            tag.putBoolean("has_queen_bee_died", instance.getHasQueenBeeDied());
            tag.putBoolean("has_skeletron_died", instance.getHasSkeletronDied());
            tag.putBoolean("has_wall_of_flesh_died", instance.getHasWallOfFleshDied());
            tag.putBoolean("has_queen_slime_died", instance.getHasQueenSlimeDied());
            tag.putBoolean("has_twins_died", instance.getHasTwinsDied());
            tag.putBoolean("has_destroyer_died", instance.getHasDestroyerDied());
            tag.putBoolean("has_skeletron_prime_died", instance.getHasSkeletronPrimeDied());
            tag.putBoolean("has_plantera_died", instance.getHasPlanteraDied());
            tag.putBoolean("has_golem_died", instance.getHasGolemDied());
            tag.putBoolean("has_empress_of_light_died", instance.getHasEmpressOfLightDied());
            tag.putBoolean("has_duke_fishron_died", instance.getHasDukeFishronDied());
            tag.putBoolean("has_lunatic_cultist_died", instance.getHasLunaticCultistDied());
            tag.putBoolean("has_moon_lord_died", instance.getHasMoonLordDied());

            return tag;
        }

        @Override
        public void readNBT(Capability<IWorldProgression> capability, IWorldProgression instance, Direction side, INBT nbt)
        {
            CompoundNBT tag = (CompoundNBT) nbt;
            instance.setOrbsBroken(tag.getInt("orbs_broken"));
            instance.setAltarsBroken(tag.getInt("altars_broken"));
            instance.setHasFoundAngler(tag.getBoolean("has_found_angler"));
            instance.setHasFoundGoblin(tag.getBoolean("has_found_goblin"));
            instance.setHasFoundStylist(tag.getBoolean("has_found_stylist"));
            instance.setHasFoundMechanic(tag.getBoolean("has_found_mechanic"));
            instance.setHasFoundWizard(tag.getBoolean("has_found_wizard"));
            instance.setHasFoundTaxCollector(tag.getBoolean("has_found_tax_collector"));
            instance.setHasBeatGoblinInvasion(tag.getBoolean("has_beat_goblin_invasion"));
            instance.setHasBeatPirateInvasion(tag.getBoolean("has_beat_pirate_invasion"));
            instance.setHasBeatPumpkinInvasion(tag.getBoolean("has_beat_pumpkin_invasion"));
            instance.setHasBeatFrostInvasion(tag.getBoolean("has_beat_frost_invasion"));
            instance.setHasKingSlimeDied(tag.getBoolean("has_king_slime_died"));
            instance.setHasEyeOfCthulhuDied(tag.getBoolean("has_eye_of_cthulhu_died"));
            instance.setHasEaterOfWorldsDied(tag.getBoolean("has_eater_of_worlds_died"));
            instance.setHasBrainOfCthulhuDied(tag.getBoolean("has_brain_of_cthulhu_died"));
            instance.setHasQueenBeeDied(tag.getBoolean("has_queen_bee_died"));
            instance.setHasSkeletronDied(tag.getBoolean("has_skeletron_died"));
            instance.setHasWallOfFleshDied(tag.getBoolean("has_wall_of_flesh_died"));
            instance.setHasQueenSlimeDied(tag.getBoolean("has_queen_slime_died"));
            instance.setHasTwinsDied(tag.getBoolean("has_twins_died"));
            instance.setHasDestroyerDied(tag.getBoolean("has_destroyer_died"));
            instance.setHasSkeletronPrimeDied(tag.getBoolean("has_skeletron_prime_died"));
            instance.setHasPlanteraDied(tag.getBoolean("has_plantera_died"));
            instance.setHasGolemDied(tag.getBoolean("has_golem_died"));
            instance.setHasEmpressOfLightDied(tag.getBoolean("has_empress_of_light_died"));
            instance.setHasDukeFishronDied(tag.getBoolean("has_duke_fishron_died"));
            instance.setHasLunaticCultistDied(tag.getBoolean("has_lunatic_cultist_died"));
            instance.setHasMoonLordDied(tag.getBoolean("has_moon_lord_died"));
        }
    }
}
